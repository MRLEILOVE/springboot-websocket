package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.dto.AgntCountDto;
import com.jdcloud.provider.pojo.AgntUserConsumer;
import com.jdcloud.provider.pojo.vo.ConsumerVo;
import com.jdcloud.util.wrapper.Wrapper;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author ourblue
 * @since 2018-11-27
 */
public interface AgntUserConsumerService extends IService<AgntUserConsumer> {

	/**
	 * query代理邀请客户数
	 * 
	 * @param agntId
	 * @return
	 */
	public Integer selectSubAgntUCountbyId(Integer agntId);

	/**
	 * 代理商客户数量统计<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/12/10 11:32
	 */
	List<AgntCountDto> agntCustomerCount();

	/**
	 * 代理客户列表<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/12/10 15:29
	 */
	Page<ConsumerVo> selectConsumerList(Page<ConsumerVo> page, ConsumerVo cv);

	/**
	 * 代理客户导出 <br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/12/10 18:58
	 */
	List<ConsumerVo> selectConsumerListExport(ConsumerVo cv);

	/**
	 * 根据代理id查询客户量<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2019/1/10 14:00
	 */
	Integer selectAgntConsumerCount(Integer agntId);

	/**
	 * 修改邀请人<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2019/1/10 18:01
	 */
	Wrapper<String> editInviter(AgntUserConsumer agntUser);

	/**
	 * 删除邀请人<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2019/1/11 17:43
	 */
	Wrapper<String> delInviter(AgntUserConsumer agntConsumer);
}
