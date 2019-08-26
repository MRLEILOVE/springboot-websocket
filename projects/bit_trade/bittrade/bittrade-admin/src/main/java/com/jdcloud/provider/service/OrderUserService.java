package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.dto.OrderUserDto;
import com.jdcloud.provider.dto.OrderUserListDto;
import com.jdcloud.provider.pojo.OrderUser;
import com.jdcloud.provider.pojo.vo.OrderUserDetailsVo;
import com.jdcloud.util.wrapper.Wrapper;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author helen
 * @since 2019-03-03
 */
public interface OrderUserService extends IService<OrderUser> {
  /**
   * 查询购买出售单列表
   *
   * @param page
   * @param orderUserListDto
   * @return
   */
  Page<OrderUser> selectOrderUserPage(Page<OrderUser> page, OrderUserListDto orderUserListDto);

  OrderUserDetailsVo selectOrderUserDetails(Integer id);

  Page<OrderUserDetailsVo> selectOrderUserDetailsPage(Page<OrderUserDetailsVo> page, OrderUserListDto orderUserListDto);

  Wrapper updateOrderUserCoinRelease(OrderUserDto addContractDto);

  Wrapper updateOrderUserCoinCancel(OrderUserDto addContractDto);

  List<OrderUserDetailsVo> OrderUserExcelList(OrderUserListDto orderUserListDto);

}
