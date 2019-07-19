package com.bittrade.batch.scheduled;

import java.text.MessageFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bittrade.batch.enumer.ParamConfigEnum.ParamKeyEnum;
import com.bittrade.batch.enumer.ParamConfigEnum.ParamStatus;
import com.bittrade.batch.general.GeneralMethod;
import com.bittrade.common.constant.IConstant;
import com.bittrade.common.utils.HttpClientResult;
import com.bittrade.common.utils.HttpClientUtils;
import com.bittrade.common.utils.RedisKeyUtil;
import com.bittrade.currency.api.service.ITParamConfigService;
import com.bittrade.pojo.dto.OkexTickerDto;
import com.bittrade.pojo.model.TParamConfig;

import redis.clients.jedis.JedisCluster;

@Component
public class OkexSymbolTickerScheduled {

	private static final Logger		LOG					= LoggerFactory.getLogger( OkexSymbolTickerScheduled.class );

	@Reference
	private ITParamConfigService	paramConfigService;

	@Autowired
	private JedisCluster			jedisCluster;

	// 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
	private ExecutorService			cachedThreadPool	= Executors.newCachedThreadPool();

	/**
	 * 获取交易对ticker信息
	 */
	// @Scheduled(cron = "0/1 * * * * ?")
	public void symbolTicker() {
		try {
			String[] symbols = GeneralMethod.qryParamConfigInfo( paramConfigService, ParamKeyEnum.OKEX_SYMBOL_KLINE_HISTORY_DATA_KEY.getKey() )
					.getParamValue().split( "," );
			for (String symbol : symbols) {
				cachedThreadPool.execute( new Runnable() {
					@Override
					public void run() {
						try {
							Thread.currentThread().setName( symbol + "_tickerDataHandlerThreadName" );
							LOG.info( Thread.currentThread().getName() + "获取交易对" + symbol + "ticker信息开始" );
							synchronized (Object.class) {
								tickerDataHandler( symbol );
							}
							LOG.info( Thread.currentThread().getName() + "获取交易对" + symbol + "ticker信息结束" );
						} catch (Exception e) {
							LOG.error( Thread.currentThread().getName() + "error" + e.getMessage(), e );
						}
					}
				} );
			}
		} catch (Exception e) {
			LOG.error( "获取交易对ticker信息异常，线程名称：" + Thread.currentThread().getName() + e.getMessage(), e );
		}
	}

	private void tickerDataHandler(String symbol) throws Exception {
		String tickerHttpUrl = GeneralMethod.qryParamConfigInfo( paramConfigService, ParamKeyEnum.OKEX_TICKER_HTTP_URL_KEY.getKey() ).getParamValue();
		tickerHttpUrl = MessageFormat.format( tickerHttpUrl, symbol );

		HttpClientResult result = HttpClientUtils.doGet( tickerHttpUrl );
		if (IConstant.OKEX_SUCCESS_CODE == result.getCode()) {
			JSONObject jsonObject = (JSONObject) JSON.parse( result.getContent() );
			OkexTickerDto okexTickerDto = JSONObject.parseObject( jsonObject.toString(), OkexTickerDto.class );
			jedisCluster.setex( RedisKeyUtil.getOkexSymbolLast( okexTickerDto.getInstrument_id() ), 30, String.valueOf( okexTickerDto.getLast() ) );
		} else {
			throw new Exception( "获取" + symbol + "ticker信息异常，resultCode=" + result.getCode() + ",resultContent=" + result.getContent() );
		}
	}
}
