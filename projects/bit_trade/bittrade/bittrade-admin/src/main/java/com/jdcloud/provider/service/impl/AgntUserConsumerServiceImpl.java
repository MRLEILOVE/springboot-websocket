package com.jdcloud.provider.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jdcloud.provider.dto.AgntCountDto;
import com.jdcloud.provider.mapper.AgntUserConsumerMapper;
import com.jdcloud.provider.model.service.BizUseAccountFeignApi;
import com.jdcloud.provider.pojo.AgntUser;
import com.jdcloud.provider.pojo.AgntUserConsumer;
import com.jdcloud.provider.pojo.UserAccount;
import com.jdcloud.provider.pojo.vo.ConsumerVo;
import com.jdcloud.provider.service.AgntUserConsumerService;
import com.jdcloud.provider.service.AgntUserService;
import com.jdcloud.provider.service.UserAccountService;
import com.jdcloud.util.wrapper.WrapMapper;
import com.jdcloud.util.wrapper.Wrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ourblue
 * @since 2018-11-27
 */
@Service
public class AgntUserConsumerServiceImpl extends ServiceImpl<AgntUserConsumerMapper, AgntUserConsumer> implements AgntUserConsumerService {

	@Autowired
	private BizUseAccountFeignApi	bizUseAccountFeignApi;

	@Autowired
	private UserAccountService		userAccountService;

	@Autowired
	private AgntUserService			agntUserService;

	@Override
	public Integer selectSubAgntUCountbyId(Integer agntId) {
		return baseMapper.selectSubAgntUCountbyId( agntId );
	}

	/**
	 * 代理商客户统计<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/12/10 11:32
	 */
	@Override
	public List<AgntCountDto> agntCustomerCount() {
		return baseMapper.agntCustomerCount();
	}

	/**
	 * 代理客户列表<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/12/10 15:30
	 */
	@Override
	public Page<ConsumerVo> selectConsumerList(Page<ConsumerVo> page, ConsumerVo cv) {
		List<ConsumerVo> consumerVos = baseMapper.selectConsumerList( page, cv );
		JSONArray jsons = new JSONArray();
		for (ConsumerVo consumerVo : consumerVos) {
			jsons.add( consumerVo.getUserId().toString() );
		}

		// 计算净值
		Wrapper wrapper = bizUseAccountFeignApi.queryCalcFloatPLList( jsons.toString() );
		if (wrapper != null && wrapper.getResult() != null) {
			Map<String, Object> map = (Map<String, Object>) wrapper.getResult();
			for (ConsumerVo consumerVo : consumerVos) {
				// 计算净值 = 账户余额 + 浮动盈亏 + 占用保证金
				BigDecimal balance = new BigDecimal( consumerVo.getBalance() );
				BigDecimal usedMargin = new BigDecimal( consumerVo.getUsedMargin() );
				BigDecimal netValue = balance.add( usedMargin ).add( new BigDecimal( map.get( consumerVo.getUserId().toString() ).toString() ) );
				consumerVo.setNetWorth( netValue.toString() );
			}
		}
		page.setRecords( consumerVos );
		return page;
	}

	/**
	 * 代理客户导出 <br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/12/10 18:58
	 */
	@Override
	public List<ConsumerVo> selectConsumerListExport(ConsumerVo cv) {
		List<ConsumerVo> consumerVos = baseMapper.selectConsumerList( cv );
		JSONArray jsons = new JSONArray();
		for (ConsumerVo consumerVo : consumerVos) {
			jsons.add( consumerVo.getUserId().toString() );
		}
		// 计算净值
		Wrapper wrapper = bizUseAccountFeignApi.queryCalcFloatPLList( jsons.toString() );
		if (wrapper != null && wrapper.getResult() != null) {
			Map<String, Object> map = (Map<String, Object>) wrapper.getResult();
			for (ConsumerVo consumerVo : consumerVos) {
				// 计算净值 = 账户余额 + 浮动盈亏 + 占用保证金
				BigDecimal balance = new BigDecimal( consumerVo.getBalance() );
				BigDecimal usedMargin = new BigDecimal( consumerVo.getUsedMargin() );
				BigDecimal netValue = balance.add( usedMargin ).add( new BigDecimal( map.get( consumerVo.getUserId().toString() ).toString() ) );
				consumerVo.setNetWorth( netValue.toString() );
			}
		}
		return consumerVos;
	}

	/**
	 * 根据代理id查询客户量<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2019/1/10 14:00
	 */
	@Override
	public Integer selectAgntConsumerCount(Integer agntId) {
		return baseMapper.selectAgntConsumerCount( agntId );
	}

	/**
	 * 修改邀请人<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2019/1/10 18:01
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Wrapper<String> editInviter(AgntUserConsumer agntConsumer) {
		AgntUserConsumer consumer = new AgntUserConsumer();
		consumer.setUserId( agntConsumer.getUserId() );
		AgntUserConsumer agntUserConsumer = baseMapper.selectOne( consumer );

		AgntUser agntUser = new AgntUser();
		agntUser.setUserId( agntConsumer.getUserId() );
		agntUser = agntUserService.selectOne( new EntityWrapper<>( agntUser ) );
		if (agntUser != null && agntUser.getAgntId().compareTo( agntConsumer.getAgntId() ) == 0) {
			return WrapMapper.error( "邀请人不能选择自己的代理商账号" );
		}

		if (agntUserConsumer == null) {
			UserAccount account = new UserAccount();
			account.setUserId( agntConsumer.getUserId() );
			UserAccount userAccount = userAccountService.selectOne( new EntityWrapper<>( account ) );

			agntUserConsumer = new AgntUserConsumer();
			agntUserConsumer.setUserId( agntConsumer.getUserId() );
			agntUserConsumer.setAgntId( agntConsumer.getAgntId() );
			agntUserConsumer.setUserAccount( userAccount.getLoginName() );
			agntUserConsumer.setUserPhone( userAccount.getLoginName() );
			baseMapper.insertAgntConsumer( agntUserConsumer );
		} else {
			agntUserConsumer.setAgntId( agntConsumer.getAgntId() );
			baseMapper.updateByConsumerId( agntUserConsumer );
		}
		return WrapMapper.ok();
	}

	/**
	 * 删除邀请人<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2019/1/11 17:43
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Wrapper<String> delInviter(AgntUserConsumer agntConsumer) {
		baseMapper.delInviter( agntConsumer );
		return WrapMapper.ok();
	}
}
