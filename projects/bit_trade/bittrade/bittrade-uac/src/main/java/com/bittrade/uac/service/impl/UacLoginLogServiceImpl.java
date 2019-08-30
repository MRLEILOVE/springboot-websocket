package com.bittrade.uac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bittrade.uac.service.UacLoginLogService;
import com.bittrade.uac.model.pojo.LoginLog;
import com.bittrade.uac.mapper.LoginLogMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author oublue
 * @since 2018-10-18
 */
@Service
public class UacLoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements UacLoginLogService {

}
