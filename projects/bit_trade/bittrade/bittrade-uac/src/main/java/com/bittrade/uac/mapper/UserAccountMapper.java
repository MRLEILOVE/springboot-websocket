package com.bittrade.uac.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bittrade.uac.model.pojo.UserAccount;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author helen
 * @since 2018-11-03
 */
public interface UserAccountMapper extends BaseMapper<UserAccount> {

	int updateAccountById(UserAccount userAccount);

	/**
	 * 提现申请更新钱包<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/12/15 14:51
	 */
	int updateWithdrawAccount(UserAccount userAccount);

	/**
	 * updateUserAccount:(更新用户钱包). <br/>
	 * 
	 * @author fate
	 */
	int updateUserAccount(UserAccount userAccount);

	/**
	 * 查询体验金持仓订单<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/12/17 16:27
	 */
	int queryHoldOrder(Long userId);
}
