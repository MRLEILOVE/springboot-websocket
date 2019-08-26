package com.jdcloud.provider.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jdcloud.base.enums.BetaEnum;
import com.jdcloud.base.enums.UserEnum;
import com.jdcloud.provider.dto.IndexDataDto;
import com.jdcloud.provider.dto.SumRechargeDto;
import com.jdcloud.provider.dto.UserAccountDto;
import com.jdcloud.provider.mapper.UserAccountMapper;
import com.jdcloud.provider.pojo.UserAccount;
import com.jdcloud.provider.pojo.UserLabour;
import com.jdcloud.provider.pojo.UserVo;
import com.jdcloud.provider.pojo.vo.UserAccountVo;
import com.jdcloud.provider.service.AgntUserService;
import com.jdcloud.provider.service.ContractPerpetualService;
import com.jdcloud.provider.service.UserAccountService;
import com.jdcloud.provider.service.UserLabourService;
import com.jdcloud.util.date.DateTimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Pattern;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author helen
 * @since 2018-10-23
 */
@Slf4j
@Service
public class UserAccountServiceImpl extends ServiceImpl<UserAccountMapper, UserAccount> implements UserAccountService {

//	@Autowired
//	private UacUserFeignApi				uacUserFeignApi;
//
//	@Autowired
//	private BizUseAccountFeignApi		bizUseAccountFeignApi;

	@Autowired
	private AgntUserService				agntUserService;

	@Autowired
	private ContractPerpetualService	contractPerpetualService;

	@Autowired
	private UserLabourService userLabourService;

	@Override
	public int updateAccountById(UserAccount userAccount) {
		return baseMapper.updateAccountById( userAccount );
	}

	/**
	 * 用户查询<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/30 11:27
	 */
	@Override
	public List<UserAccount> queryUser(String keyword) {
		return baseMapper.queryUser( keyword );
	}

	@Override
	public Page<UserVo> selectUserList(Page<UserVo> page, UserAccountDto userDto) {
		userDto = selectParams( userDto );// 查询条件整理
		List<UserAccountVo> users = baseMapper.selectUserList( page, userDto );
		JSONArray jsons = new JSONArray();
		for (UserAccountVo u : users) {
			jsons.add( u.getUserId().toString() );
		}
//		Wrapper wrapper = bizUseAccountFeignApi.queryCalcFloatPLList( jsons.toString() );
//		Map<String, Object> idMap = null;
//		if (wrapper.getCode() == 200) {
//			idMap = (Map<String, Object>) wrapper.getResult();
//		}
		List<UserVo> list = new ArrayList<>();
		for (UserAccountVo u : users) {
			// 计算用户账户的浮动资产信息
			UserVo uVo = toCalculate( u, null );
			list.add( uVo );
		}
		page.setRecords( list );
		return page;
	}

	@Override
	public List<UserVo> selectContractMicroExportList(UserAccountDto userDto) {
		userDto = selectParams( userDto );// 查询条件整理
		List<UserAccountVo> users = baseMapper.selectUserList( userDto );
		JSONArray jsons = new JSONArray();
		for (UserAccountVo u : users) {
			jsons.add( u.getUserId().toString() );
		}
//		Wrapper wrapper = bizUseAccountFeignApi.queryCalcFloatPLList( jsons.toString() );
//		Map<String, Object> idMap = null;
//		if (wrapper.getCode() == 200) {
//			idMap = (Map<String, Object>) wrapper.getResult();
//		}
		List<UserVo> list = new ArrayList<>();
		for (UserAccountVo u : users) {
			// 计算用户账户的浮动资产信息
			UserVo uVo = toCalculate( u, null );
			String internalAccountStr = UserEnum.Internal.INTERNAL_ACCOUNT.getCode() == u.getInternalAccount() ? "是" : "否";
			uVo.setInternalAccountStr( internalAccountStr );
			list.add( uVo );
		}
		return list;
	}

	@Override
	public UserVo selectUserById(Long userId) {
		UserAccountVo userVo = baseMapper.selectUserById( userId );
		JSONArray jsons = new JSONArray();
		jsons.add( userId.toString() );
//		Wrapper wrapper = bizUseAccountFeignApi.queryCalcFloatPLList( jsons.toString() );
//		Map<String, Object> idMap = null;
//		if (wrapper.getCode() == 200) {
//			idMap = (Map<String, Object>) wrapper.getResult();
//		}
		UserVo uVo = new UserVo();

		uVo = toCalculate( userVo, null );
		UserAccount account = null;
		if (uVo != null && uVo.getUserId() != null) {
			EntityWrapper entity = new EntityWrapper();
			entity.eq("user_id",uVo.getUserId());
			UserLabour labour = userLabourService.selectOne(entity);
			if (labour != null) {
				entity = new EntityWrapper();
				entity.eq("user_id",labour.getParentId());
				account = this.selectOne(entity);
			}
		}
		if (account != null) {
			String realName = account.getRealName() == null ?"--":account.getRealName();
			uVo.setLabourName(account.getLoginName()+" ( " + realName + " ) ");
		} else {
			uVo.setLabourName("--");
		}
		return uVo;
	}

	/**
	 * 计算用户账户的浮动资产信息
	 * 
	 * @param u
	 * @param idMap
	 * @return
	 */
	public UserVo toCalculate(UserAccountVo u, Map<String, Object> idMap) {
		UserVo uVo = new UserVo();
		BeanUtils.copyProperties( u, uVo );
		BigDecimal occupyAssets = u.getFrozenAmount().add( u.getUsedMargin() );// 总冻结
		BigDecimal calcFloatPL = BigDecimal.ZERO;
		if (idMap != null) {
			if (idMap.get( u.getUserId().toString() ) != null) {
				calcFloatPL = new BigDecimal( idMap.get( u.getUserId().toString() ).toString() );
			}
			BigDecimal totalAssets = u.getBalance().add( u.getUsedMargin() ).add( u.getFrozenAmount() );// 固定总资产
			totalAssets = totalAssets.add( calcFloatPL );// 浮动总资产
			BigDecimal netValue = u.getBalance().add( u.getUsedMargin() ).add( calcFloatPL );// 净值
			uVo.setNetValue( netValue );// 净值
			Integer count = contractPerpetualService.selectContractPerpetualCount( u.getUserId() );
			BigDecimal riskRatio = BigDecimal.ONE;
			if (u.getUsedMargin().compareTo( BigDecimal.ZERO ) == 0 || count == 0) {
				uVo.setRiskRatio( "--" );// 风险率
			} else {
				if (u.getUsedMargin().compareTo( BigDecimal.ZERO ) != 0) {
					riskRatio = netValue.divide( u.getUsedMargin(), 4, BigDecimal.ROUND_HALF_UP );// 风险率
				}
				riskRatio = riskRatio.multiply( BigDecimal.valueOf( 100 ) );
				uVo.setRiskRatio( riskRatio + "%" );// 风险率
			}
			uVo.setTotalAssets( totalAssets );// 总资产
			uVo.setOccupyAssets( occupyAssets );// 总冻结
		}
		uVo.setIsAuthStr( uVo.getIsAuthStr() );
		return uVo;
	}

	/**
	 * 查询条件整理
	 * 
	 * @param userDto
	 * @return
	 */
	public UserAccountDto selectParams(UserAccountDto userDto) {
		Map<String, Object> map = userDto.getParams();
		if (map != null && map.get( "condition" ) != null) {
			Pattern pattern = Pattern.compile( "^[-\\+]?[\\d]*$" );
			String condition = (String) map.get( "condition" );
			if (pattern.matcher( condition ).matches()) {
				map.put( "type", 1 );// 用户名查询
			} else {
				map.put( "type", 2 );// 姓名查询
			}
		}
		userDto.setParams( map );
		// if (userDto.getIsAuth() != null && !userDto.getIsAuth().equals(1)) {
		// Wrapper wrapper=uacUserFeignApi.queryRejectAuthenticationList();
		// if(wrapper.getCode() == 200){
		// userDto.setIds((Set<Long>) wrapper.getResult());
		// }
		// }
		return userDto;
	}

	/**
	 * 查询注册数据<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/12/19 16:04
	 */
	@Override
	public IndexDataDto queryRegisterCount() {
		// 昨天的开始时间
		Date beginTime = DateTimeUtils.getDays(-1);

		Calendar c = Calendar.getInstance();
		c.setTime( new Date() );
		c.add( Calendar.DAY_OF_MONTH, -1 );
		c.set( Calendar.HOUR_OF_DAY, 23 );
		c.set( Calendar.MINUTE, 59 );
		c.set( Calendar.SECOND, 59 );
		c.set( Calendar.MILLISECOND, 999 );
		// 昨天的结束时间
		Date endTime = c.getTime();
		return baseMapper.queryRegisterCount( beginTime, endTime );
	}

	@Override
	public IndexDataDto userMonitor() {
		// 昨天的开始时间
		Date beginTime = DateTimeUtils.getDays(-1);

		Calendar c = Calendar.getInstance();
		c.setTime( new Date() );
		c.add( Calendar.DAY_OF_MONTH, -1 );
		c.set( Calendar.HOUR_OF_DAY, 23 );
		c.set( Calendar.MINUTE, 59 );
		c.set( Calendar.SECOND, 59 );
		c.set( Calendar.MILLISECOND, 999 );
		// 昨天的结束时间
		Date endTime = c.getTime();
		return baseMapper.userMonitor( beginTime, endTime );
	}

	@Override
	public BigDecimal sumRecharge(SumRechargeDto sumRechargeDto) {
		return baseMapper.sumRecharge(sumRechargeDto);
	}

	/**
	 * 验证内部账号
	 * @param userId
	 * @return int
	 * c
	 * 2019-7-1
	 */
	@Override
	public int eqinternalAccount(Long userId) {
		EntityWrapper entity = new  EntityWrapper();
		entity.eq("user_id",userId);
		entity.eq("internal_account",BetaEnum.internalAccount.internal_account.getCode());
		int count = baseMapper.selectCount(entity);
		return count;
	}
}
