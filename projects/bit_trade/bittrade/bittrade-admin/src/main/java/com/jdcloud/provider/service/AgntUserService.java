package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.dto.AgntCountDto;
import com.jdcloud.provider.dto.AgntDto;
import com.jdcloud.provider.dto.AgntUserDto;
import com.jdcloud.provider.pojo.AgntUser;
import com.jdcloud.provider.pojo.vo.AgntUserVo;
import com.jdcloud.provider.pojo.vo.ConsumerVo;
import com.jdcloud.provider.pojo.vo.ContractVo;
import com.jdcloud.util.wrapper.Wrapper;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author ourblue
 * @since 2018-11-19
 */
public interface AgntUserService extends IService<AgntUser> {

	/**
	 * query所有下级代理总数
	 * 
	 * @param agntId
	 * @return
	 */
	public Integer selectSubAgntCountById(Integer agntId);

	/**
	 * query代理下的客户列表
	 * 
	 * @param page
	 * @param cv
	 * @return
	 */
	public Page<ConsumerVo> selectConsumerListById(Page<ConsumerVo> page, ConsumerVo cv);

	/**
	 * query用户合约订单
	 * 
	 * @param page
	 * @param cv
	 * @return
	 */
	public Page<ContractVo> selectConsumerOrderListById(Page<ContractVo> page, ContractVo cv);

	/**
	 * query代理list
	 * 
	 * @param page
	 * @param av
	 * @return
	 */
	public Page<AgntUserVo> selectAgntListById(Page<AgntUserVo> page, AgntUserVo av);

	/**
	 * add代理商账号
	 * 
	 * @param agntUser
	 */
	Wrapper<String> insertAgntUser(AgntUser agntUser);

	/**
	 * edit代理商
	 * 
	 * @param agntUser
	 * @return
	 */
	Integer editAgntUser(AgntUser agntUser);

	/**
	 * query代理list
	 * 
	 * @param page
	 * @param au
	 * @return
	 */
	public Page<AgntUser> selectAgntUserList(Page<AgntUser> page, AgntUser au);

	/**
	 * 查询当前用户的上级代理
	 * 
	 * @param userId
	 * @return
	 */
	AgntUser selectAgntById(Long userId);

	/**
	 * 功能描述: 查询上级代理<br>
	 * 
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/12/8 18:44
	 */
	Wrapper<List<AgntDto>> queryAgnt(AgntDto agntDto);

	/**
	 * 代理商统计<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/12/10 11:02
	 */
	AgntCountDto agntStatistics();

	/**
	 * 代理商列表<br>
	 * 
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/12/10 14:02
	 */
	Page<AgntUserVo> selectAgntList(Page<AgntUserVo> page, AgntUserVo av);

	/**
	 * 代理商导出<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/12/10 18:45
	 */
	List<AgntUserVo> selectAgntListExport(AgntUserVo av);

	/**
	 * 查询代理商详情<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2019/1/10 11:52
	 */
	AgntUserDto selectByAgntId(Integer agntId);

	/**
	 * 删除代理商<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2019/1/10 16:23
	 */
	Wrapper<String> delAgntUser(AgntUser agntUser);

	/**
	 * 修改代理商等级<br>
	 * 
	 * @return:
	 * @Author: yongheng
	 * @Date: 2019/1/11 14:04
	 */
	Wrapper<String> editAgntLevel(AgntUser agntUser);
}
