package com.wallet.biz.service;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wallet.biz.pojo.ParameterConfig;


/**
 * <p>
 * 参数配置表 服务类
 * </p>
 *
 * @author yongheng
 * @since 2018-10-25
 */
public interface ParameterConfigService extends IService<ParameterConfig> {
    /**
     * 根据配置表的币种key获取汇率
     * @param key 币种 -》参数配置表的key
     * @return
     */
    BigDecimal getRateByKey(String key);
}
