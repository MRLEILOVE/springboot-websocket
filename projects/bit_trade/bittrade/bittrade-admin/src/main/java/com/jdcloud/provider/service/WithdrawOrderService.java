package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.dto.StatisticsDto;
import com.jdcloud.provider.dto.Withdraw;
import com.jdcloud.provider.dto.WithdrawDetail;
import com.jdcloud.provider.dto.WithdrawStatistics;
import com.jdcloud.provider.pojo.SysUser;
import com.jdcloud.provider.pojo.WithdrawOrder;
import com.jdcloud.util.wrapper.Wrapper;

import java.util.List;

/**
 * <p>
 * 提现服务类
 * </p>
 *
 * @author helen
 * @since 2018-10-27
 */
public interface WithdrawOrderService extends IService<WithdrawOrder> {

	/**
	 * 提现列表<br>
	 * 
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/26 10:26
	 */
	Page<Withdraw> selectWithdrawOrderList(Withdraw withdraw);

	/**
	 * 提现详情<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/26 16:03
	 */
	WithdrawDetail selectWithdrawDetail(String orderNo);

	/**
	 * 提现审核: <br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/26 19:59
	 */
	Wrapper<String> withdrawAudit(Withdraw withdraw, SysUser user);

	/**
	 * 充值记录列表 <br>
	 * 
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/28 18:39
	 */
	Page<Withdraw> selectWithdrawRecordList(Withdraw withdraw);

	/**
	 * 提现记录统计 <br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/28 18:43
	 */
	WithdrawStatistics withdrawStatistics();

	/**
	 * 提现记录导出 <br>
	 * 
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/28 18:50
	 */
	List<Withdraw> selectWithdrawRecordExportList(Withdraw withdraw);

	/**
	 * 用户提现统计<br>
	 * 
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/12/19 11:13
	 */
	StatisticsDto statisticsByUserId(Long userId);

	/**
	 * 充值待审核数<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/12/19 16:30
	 */
	Integer queryAuditingCount();
}
