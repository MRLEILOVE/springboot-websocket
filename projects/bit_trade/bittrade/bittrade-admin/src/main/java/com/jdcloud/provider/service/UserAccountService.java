package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.dto.IndexDataDto;
import com.jdcloud.provider.dto.SumRechargeDto;
import com.jdcloud.provider.dto.UserAccountDto;
import com.jdcloud.provider.pojo.UserAccount;
import com.jdcloud.provider.pojo.UserVo;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 用户资金服务类
 * </p>
 *
 * @author helen
 * @since 2018-10-23
 */
public interface UserAccountService extends IService<UserAccount> {

	int updateAccountById(UserAccount userAccount);

	/**
	 * 查询用户 <br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/30 11:26
	 */
	List<UserAccount> queryUser(String keyword);

	/**
	 * 查询用户信息列表
	 * 
	 * @param page
	 * @param userDto
	 * @return
	 */
	Page<UserVo> selectUserList(Page<UserVo> page, UserAccountDto userDto);

	/**
	 * Excel导出
	 * 
	 * @param userDto
	 * @return
	 */
	List<UserVo> selectContractMicroExportList(UserAccountDto userDto);

	/**
	 * 账户信息详情
	 * 
	 * @param userId
	 * @return
	 */
	UserVo selectUserById(Long userId);

	/**
	 * 查询注册数据<br>
	 * 
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/12/19 16:04
	 */
	IndexDataDto queryRegisterCount();

	/** 
	 * @Description: 统计用户监控数据
	 * @Author: senghor
	 * @Date: 2019/5/27 0027 21:03
	 */
    IndexDataDto userMonitor();

	/**
	 * 统计今日消耗的β通证
	 */
	BigDecimal sumRecharge(SumRechargeDto sumRechargeDto);

	/**
	 * 判断内部账号
	 */
	int eqinternalAccount(Long userId);

}
