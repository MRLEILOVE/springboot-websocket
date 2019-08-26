package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.dto.Recharge;
import com.jdcloud.provider.dto.RechargeStatistics;
import com.jdcloud.provider.dto.StatisticsDto;
import com.jdcloud.provider.pojo.RechargeOrder;
import com.jdcloud.provider.pojo.SysUser;
import com.jdcloud.provider.pojo.vo.RechargeConfigVo;
import com.jdcloud.util.wrapper.Wrapper;

import java.util.List;

/**
 * <p>
 * 充值服务类
 * </p>
 *
 * @author yongheng
 * @since 2018-10-25
 */
public interface RechargeOrderService extends IService<RechargeOrder> {

	/**
	 * 分页查询充值订单列表<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/23 16:37
	 */
	Page<Recharge> selectRechargeOrderList(Recharge recharge);

	/**
	 * 充值审核通过 <br>
	 * 
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/24 15:34
	 */
	Wrapper<String> auditPass(String[] orderNos, SysUser user);

	/**
	 * 充值审核拒绝<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/24 18:52
	 */
	Wrapper<String> auditRefuse(RechargeOrder rechargeOrder, SysUser user);

	/**
	 * 金额调整<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/28 11:12
	 */
	Wrapper<String> correctedAmount(RechargeOrder rechargeOrder, SysUser user);

	/**
	 * 充值记录列表<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/28 16:02
	 */
	Page<Recharge> selectRechargeRecordList(Recharge recharge);

	/**
	 * 充值记录统计<br>
	 * 
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/28 16:37
	 */
	RechargeStatistics rechargeStatistics();

	/**
	 * 充值记录导出<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/28 18:11
	 */
	List<Recharge> selectRechargeRecordExportList(Recharge recharge);

	/**
	 * 用户充值统计<br>
	 * 
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/12/19 10:59
	 */
	StatisticsDto statisticsByUserId(Long userId);

	/**
	 * 查询充值待审核数<br>
	 * 
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/12/19 16:24
	 */
	Integer queryAuditingCount();

	/**
	 * 跳转自动审核设置页面<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2019/1/8 17:01
	 */
	RechargeConfigVo goRechargeConfig();

	/**
	 * 修改自动充值设置<br>
	 * 
	 * @return:
	 * @Author: yongheng
	 * @Date: 2019/1/8 17:04
	 */
	Wrapper<String> rechargeConfig(RechargeConfigVo rechargeConfigVo, SysUser user);
}
