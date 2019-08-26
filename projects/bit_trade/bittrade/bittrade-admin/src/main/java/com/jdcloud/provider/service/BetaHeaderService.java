package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.pojo.BetaHeader;
import com.jdcloud.provider.pojo.vo.BetaHeaderVo;
import com.jdcloud.util.wrapper.Wrapper;

/**
 * <p>
 * beta狗头像表 服务类
 * </p>
 *
 * @author ourblue
 * @since 2019-04-11
 */
public interface BetaHeaderService extends IService<BetaHeader> {

    Page<BetaHeader> betaHeaderPageList(Page<BetaHeader> page);

    int deletebetaHeaderByIds(String ids);

    Wrapper<String> saveOrUpdate(BetaHeaderVo vo);
}
