package com.bittrade.batch.scheduled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bittrade.batch.enumer.ParamConfigEnum.ParamKeyEnum;
import com.bittrade.batch.general.GeneralMethod;
import com.bittrade.common.constant.IConstant;
import com.bittrade.common.utils.HttpClientResult;
import com.bittrade.common.utils.HttpClientUtils;
import com.bittrade.common.utils.RedisKeyUtil;
import com.bittrade.currency.api.service.ITParamConfigService;
import com.bittrade.pojo.model.TParamConfig;

import redis.clients.jedis.JedisCluster;

/**
 * 获取okex法币汇率
 * 
 * @author zale
 * 
 */
@Component
public class OkexRateScheduled {

	private static final Logger		LOG	= LoggerFactory.getLogger( OkexRateScheduled.class );

	@Reference
	private ITParamConfigService	paramConfigService;

	@Autowired
	private JedisCluster			jedisCluster;

	// @Scheduled(cron = "0 0 0/2 * * ?") // 两个小时执行一次
	public void rate() {
		try {
			LOG.info( "获取okex法币汇率开始" );
			TParamConfig config = GeneralMethod.qryParamConfigInfo( paramConfigService, ParamKeyEnum.OKEX_USD_TO_CNY_RATE_KEY.getKey() );
			String rateUrl = config.getParamValue();
			HttpClientResult result = HttpClientUtils.doGet( rateUrl );
			if (IConstant.OKEX_SUCCESS_CODE == result.getCode()) {
				jedisCluster.set( RedisKeyUtil.USD_TO_CNY_RATE_KEY, result.getContent() );
			} else {
				throw new Exception( "获取okex法币汇率异常，resultCode=" + result.getCode() + ",resultContent=" + result.getContent() );
			}
			LOG.info( "获取okex法币汇率结束" );
		} catch (Exception e) {
			LOG.error( "RateScheduled.rate.error=" + e.getMessage(), e );
		}
	}

}
