package com.jdcloud.provider.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.base.Preconditions;
import com.jdcloud.base.constant.GlobalConstant.Number;
import com.jdcloud.base.enums.AgntEnum;
import com.jdcloud.base.enums.AgntEnum.AgntType;
import com.jdcloud.provider.dto.AgntCountDto;
import com.jdcloud.provider.dto.AgntDto;
import com.jdcloud.provider.dto.AgntUserDto;
import com.jdcloud.provider.exception.AdminException;
import com.jdcloud.provider.mapper.AgntUserMapper;
import com.jdcloud.provider.model.dto.UserDto;
import com.jdcloud.provider.model.service.UacUserFeignApi;
import com.jdcloud.provider.pojo.AgntUser;
import com.jdcloud.provider.pojo.AgntUserConsumer;
import com.jdcloud.provider.pojo.UserLabour;
import com.jdcloud.provider.pojo.vo.AgntUserVo;
import com.jdcloud.provider.pojo.vo.ConsumerVo;
import com.jdcloud.provider.pojo.vo.ContractVo;
import com.jdcloud.provider.service.AgntUserConsumerService;
import com.jdcloud.provider.service.AgntUserService;
import com.jdcloud.provider.service.UserLabourService;
import com.jdcloud.provider.shiro.service.PasswordService;
import com.jdcloud.provider.utils.ShiroUtils;
import com.jdcloud.util.wrapper.WrapMapper;
import com.jdcloud.util.wrapper.Wrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ourblue
 * @since 2018-11-19
 */
@Slf4j
@Service
public class AgntUserServiceImpl extends ServiceImpl<AgntUserMapper, AgntUser> implements AgntUserService {

	@Autowired
	private PasswordService			passwordService;
	@Autowired
	private UacUserFeignApi			uacUserFeignApi;
	@Autowired
	private AgntUserConsumerService	agntUserConsumerService;
	@Autowired
	private UserLabourService		userLabourService;

	@Override
	public Integer selectSubAgntCountById(Integer agntId) {
		return baseMapper.selectSubAgntCountById( agntId );
	}

	@Override
	public Page<ConsumerVo> selectConsumerListById(Page<ConsumerVo> page, ConsumerVo cv) {
		page.setRecords( baseMapper.selectConsumerListById( page, cv ) );
		return page;
	}

	@Override
	public Page<ContractVo> selectConsumerOrderListById(Page<ContractVo> page, ContractVo cv) {
		page.setRecords( baseMapper.selectConsumerOrderListById( page, cv ) );
		return page;
	}

	@Override
	public Page<AgntUserVo> selectAgntListById(Page<AgntUserVo> page, AgntUserVo av) {
		page.setRecords( baseMapper.selectAgntListById( page, av ) );
		return page;
	}

	@Override
	public Page<AgntUser> selectAgntUserList(Page<AgntUser> page, AgntUser au) {
		page.setRecords( baseMapper.selectAgntUserList( page, au ) );
		return page;
	}

	@Override
	public Wrapper<String> insertAgntUser(AgntUser agntUser) {

		/**
		 * 0、代理关系判断 1、代理等级判断 2、账户类型 3、邀请码不能重复 4、创建人 5、代理登录名称不能有重复判断 6、父亲列表处理 7、用户密码加盐
		 * 8、上级代理判断 9、如果是平台用户，判断平台用户是否存在
		 */
		//代理关系判断
		if (AgntType.PLATFORM_ACCOUNT.getCode().toString().equals( agntUser.getUserType() )) {
			// 查询代理关系
			AgntUserConsumer consumer = new AgntUserConsumer();
			consumer.setUserId( agntUser.getUserId() );
			if (null != agntUserConsumerService.selectOne( new EntityWrapper<>( consumer ) )){
				return WrapMapper.error("该用户为通过邀请链接注册用户，为避免计算资金重复，设置成为代理前请先在用户信息详情中删除邀请人。");
			}

			// 查询个人邀请关系
			UserLabour userLabour = new UserLabour();
			userLabour.setUserId( agntUser.getUserId() );
			if (null != userLabourService.selectOne( new EntityWrapper<>( userLabour ) )){
				return WrapMapper.error("该用户为通过邀请链接注册用户，为避免计算资金重复，设置成为代理前请先在用户信息详情中删除邀请人。");
			}
		}
		
		AgntUser lastAu = checkAgntUser( agntUser );
		// 父亲列表处理
		String ancestors = lastAu.getParentId() == null ? Number.ZERO_0 + "" : lastAu.getAncestors() + "," + lastAu.getAgntId();
		agntUser.setAncestors( ancestors );
		agntUser.setSalt( ShiroUtils.randomSalt() );
		agntUser.setPassword( passwordService.encryptPassword( agntUser.getLoginName(), agntUser.getPassword(), agntUser.getSalt() ) );
		agntUser.setCreateBy( ShiroUtils.getLoginName() );
		agntUser.setCreateTime( new Date() );
		agntUser.setInvitationCode( getRandomCode() );
		baseMapper.insert( agntUser );
		return WrapMapper.ok();
	}

	@Override
	public Integer editAgntUser(AgntUser agntUser) {
		if (StringUtils.isNotEmpty( agntUser.getLoginName() )) {
			AgntUser auParam = new AgntUser();
			auParam.setLoginName( agntUser.getLoginName() );
			Preconditions.checkArgument( null == baseMapper.selectOne( auParam ), "账号已存在" );
		}
		agntUser.setUpdateTime( new Date() );
		agntUser.setUpdateBy( ShiroUtils.getLoginName() );
		if (StringUtils.isNotEmpty( agntUser.getPassword() )) {
			AgntUser au = baseMapper.selectById( agntUser.getAgntId() );
			agntUser.setSalt( au.getSalt() );
			agntUser.setLoginName( au.getLoginName() );
			agntUser.setPassword( passwordService.encryptPassword( agntUser.getLoginName(), agntUser.getPassword(), agntUser.getSalt() ) );
		}
		return baseMapper.updateById( agntUser );
	}

	/**
	 * check新增代理
	 *
	 * @param agntUser
	 * @return
	 */
	public AgntUser checkAgntUser(AgntUser agntUser) {
		Preconditions.checkArgument( StringUtils.isNotBlank( agntUser.getUserType() ), "用户类型不能为空" );
		Preconditions.checkArgument( null != AgntEnum.AgntType.parse( agntUser.getUserType() ), "用户类型不正确" );
		// 如果是平台用户需要校验平台用户ID
		if (agntUser.getUserType().equals( AgntType.PLATFORM_ACCOUNT.getCode().toString() )) {
			checkPlateUserExist( agntUser );
		}
		Preconditions.checkArgument( null != (agntUser.getParentId()), "上级代理不能空" );
		Preconditions.checkArgument( StringUtils.isNotBlank( agntUser.getPassword() ), "用户密码不能为空" );
		Preconditions.checkArgument( StringUtils.isNotBlank( agntUser.getLoginName() ), "登录账号不能为空" );
		Preconditions.checkArgument( !checkAgntUserExist( agntUser.getLoginName() ), "账号已存在" );
		// Preconditions.checkArgument( StringUtils.isNotBlank( agntUser.getAgntName()
		// ), "代理商姓名不能为空" );
		Preconditions.checkArgument( null != agntUser.getAgntLevel(), "代理商等级不能为空" );
		// 检查或者生成上级代理信息，并返回上级代理
		return checkLastAgntCorrect( agntUser );
	}

	/**
	 * gener递归生成不重复邀请码
	 *
	 * @return
	 */
	public String getRandomCode() {
		AgntUser au = new AgntUser();
		au.setInvitationCode( RandomStringUtils.randomNumeric( 6 ) );
		if (null != baseMapper.selectOne( au )) {
			getRandomCode();
		}
		return au.getInvitationCode();
	}

	/**
	 * check登录名称是否存在
	 *
	 * @param loginName
	 * @return
	 */
	public Boolean checkAgntUserExist(String loginName) {
		AgntUser au = new AgntUser();
		au.setLoginName( loginName );
		return null == baseMapper.selectOne( au ) ? false : true;

	}

	/**
	 * check平台账号是否存在
	 *
	 * @param agntUser
	 */
	public void checkPlateUserExist(AgntUser agntUser) {
		Preconditions.checkArgument( null != agntUser.getUserId(), "平台账户用户是空" );
		Wrapper<UserDto> userDtoWrapper = uacUserFeignApi.queryFuserById( agntUser.getUserId() );
		Preconditions.checkArgument( null != userDtoWrapper, "RPC异常，请联系管理员" );
		UserDto userDto = userDtoWrapper.getResult();
		Preconditions.checkArgument( null != userDto, "此用户不在本平台" );
		Preconditions.checkArgument( StringUtils.isNotBlank( userDto.getRealName() ), "未认证用户不能作为代理商" );
		// 设置账户密码
		agntUser.setLoginName( userDto.getLoginName() );
		agntUser.setAgntName( userDto.getRealName() );
	}

	/**
	 * check上级代理
	 *
	 * @param agntUser
	 * @return
	 */
	public AgntUser checkLastAgntCorrect(AgntUser agntUser) {
		AgntUser lastAu = new AgntUser();
		// 查询上级代理
		if (Number.ZERO_0 == agntUser.getParentId()) {
			lastAu.setParentId( null );
			lastAu.setAgntLevel( Number.ZERO_0 );
		} else {
			lastAu = baseMapper.selectById( agntUser.getParentId() );
		}
		Preconditions.checkArgument( null != lastAu, "不存在的上级代理" );
		// 校验代理等级有问题
		if (agntUser.getAgntLevel() - 1 != lastAu.getAgntLevel()) {
			log.error( "<== checkAgntUser.agntLevel" + "[ currentAgntLevel : " + agntUser.getAgntLevel() + "] [ lastAgntLevel : "
					+ lastAu.getAgntLevel() + "]" );
			throw new AdminException( "代理等级设置有误" );
		}
		return lastAu;
	}

	@Override
	public AgntUser selectAgntById(Long userId) {
		return baseMapper.selectAgntById( userId );
	}

	/**
	 * 查询所有上级代理<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/12/8 18:48
	 */
	@Override
	public Wrapper<List<AgntDto>> queryAgnt(AgntDto agntDto) {
		List<AgntUser> agntUsers = baseMapper.queryAgnt( agntDto );
		List<AgntDto> list = new ArrayList<>();
		for (AgntUser agntUser : agntUsers) {
			AgntDto agnt = new AgntDto();
			agnt.setLoginName( agntUser.getLoginName() );
			agnt.setRealName( agntUser.getAgntName() );
			agnt.setUserId( agntUser.getAgntId() );
			list.add( agnt );
		}
		return WrapMapper.ok( list );
	}

	/**
	 * 代理商统计<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/12/10 11:02
	 */
	@Override
	public AgntCountDto agntStatistics() {
		AgntCountDto agntCount = new AgntCountDto();
		// 统计代理商数量
		List<AgntCountDto> agntCounts = baseMapper.agntCount();
		for (AgntCountDto agntCountDto : agntCounts) {
			switch (agntCountDto.getAgntLevel()) {
			case 1:
				agntCount.setFirstAgnt( agntCountDto.getAgntCount() );
				break;
			case 2:
				agntCount.setSecondAgnt( agntCountDto.getAgntCount() );
				break;
			case 3:
				agntCount.setThirdAgnt( agntCountDto.getAgntCount() );
				break;
			default:
				break;
			}
		}

		// 统计代理商客户数量
		List<AgntCountDto> agntCustomerCounts = agntUserConsumerService.agntCustomerCount();
		for (AgntCountDto agntCustomerCount : agntCustomerCounts) {
			switch (agntCustomerCount.getAgntLevel()) {
			case 1:
				agntCount.setFirstAgntCustomer( agntCustomerCount.getAgntCount() );
				break;
			case 2:
				agntCount.setSecondAgntCustomer( agntCustomerCount.getAgntCount() );
				break;
			case 3:
				agntCount.setThirdAgntCustomer( agntCustomerCount.getAgntCount() );
				break;
			default:
				break;
			}
			agntCount.setTotalInvitation( agntCount.getTotalInvitation() + agntCustomerCount.getAgntCount() );
		}
		return agntCount;
	}

	/**
	 * 代理商列表<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/12/10 14:04
	 */
	@Override
	public Page<AgntUserVo> selectAgntList(Page<AgntUserVo> page, AgntUserVo av) {
		page.setRecords( baseMapper.selectAgntList( page, av ) );
		return page;
	}

	/**
	 * 代理商导出<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/12/10 18:45
	 */
	@Override
	public List<AgntUserVo> selectAgntListExport(AgntUserVo av) {
		List<AgntUserVo> agntUserVos = baseMapper.selectAgntList( av );
		for (AgntUserVo agntUserVo : agntUserVos) {
			switch (agntUserVo.getAgntLevel()) {
			case 1:
				agntUserVo.setAgntLevelStr( "一级代理" );
				break;
			case 2:
				agntUserVo.setAgntLevelStr( "二级代理" );
				break;
			case 3:
				agntUserVo.setAgntLevelStr( "三级代理" );
				break;
			default:
				break;
			}
		}
		return agntUserVos;
	}

	/**
	 * 查询代理商详情<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2019/1/10 11:52
	 */
	@Override
	public AgntUserDto selectByAgntId(Integer agntId) {
		// 查询代理信息
		AgntUser agntUser = this.selectById( agntId );
		AgntUserDto agntUserDto = new AgntUserDto();
		BeanUtils.copyProperties( agntUser, agntUserDto );

		// 查询下级代理数量
		Integer lowerAgntCount = baseMapper.selectSubAgntCountById( agntId );
		agntUserDto.setLowerAgntCount( lowerAgntCount );

		// 查询代理客户数量
		Integer agntConsumerCount = agntUserConsumerService.selectAgntConsumerCount( agntId );
		agntUserDto.setAgntCustomerCount( agntConsumerCount );

		agntUserDto.setParentName( "--" );
		// 查询上级代理名称
		AgntUser parentAgntUser = this.selectById( agntUserDto.getParentId() );
		if (parentAgntUser != null) {
			agntUserDto.setParentName( parentAgntUser.getAgntName() );
		}

		switch (agntUserDto.getAgntLevel()) {
		case 1:
			agntUserDto.setAgntLevelStr( "一级代理" );
			break;
		case 2:
			agntUserDto.setAgntLevelStr( "二级代理" );
			break;
		case 3:
			agntUserDto.setAgntLevelStr( "三级代理" );
			break;
		default:
			break;
		}

		return agntUserDto;
	}

	/**
	 * 删除代理商<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2019/1/10 16:23
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Wrapper<String> delAgntUser(AgntUser agntUser) {
		Integer agntId = agntUser.getAgntId();
		// 查询下级代理数量
		Integer lowerAgntCount = baseMapper.selectSubAgntCountById( agntId );
		Preconditions.checkArgument( lowerAgntCount == 0, "存在下级代理，无法删除！" );

		// 查询代理客户数量
		Integer agntConsumerCount = agntUserConsumerService.selectAgntConsumerCount( agntId );
		Preconditions.checkArgument( agntConsumerCount == 0, "存在代理客户，无法删除！" );

		Integer row = baseMapper.deleteById( agntId );
		Preconditions.checkArgument( row == 1, "删除失败" );

		return WrapMapper.ok();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Wrapper<String> editAgntLevel(AgntUser agntUser) {
		AgntUser agnt = baseMapper.selectById( agntUser.getAgntId() );
		agnt.setAgntLevel( agntUser.getAgntLevel() );
		if (agntUser.getParentId() != null) {
			AgntUser parentAgnt = baseMapper.selectById( agntUser.getParentId() );
			if (parentAgnt == null) {
				return WrapMapper.error( "上级代理有误" );
			}

			if (parentAgnt.getAgntId().compareTo( agnt.getAgntId() ) == 0) {
				return WrapMapper.error( "上级代理不能选择自己" );
			}

			agnt.setParentId( parentAgnt.getAgntId() );
			agnt.setAncestors( parentAgnt.getAncestors() + "," + parentAgnt.getAgntId() );
		} else {
			agnt.setParentId( 0 );
			agnt.setAncestors( "0" );
		}
		baseMapper.updateById( agnt );
		return WrapMapper.ok();
	}
}