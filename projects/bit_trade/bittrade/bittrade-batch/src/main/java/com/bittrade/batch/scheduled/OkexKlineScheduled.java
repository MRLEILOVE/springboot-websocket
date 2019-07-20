package com.bittrade.batch.scheduled;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONArray;
import com.bittrade.batch.enumer.ParamConfigEnum.ParamKeyEnum;
import com.bittrade.batch.enumer.ParamConfigEnum.ParamValue;
import com.bittrade.batch.general.GeneralMethod;
import com.bittrade.common.constant.IConstant;
import com.bittrade.common.utils.HttpClientResult;
import com.bittrade.common.utils.HttpClientUtils;
import com.bittrade.currency.api.service.ITKlineService;
import com.bittrade.currency.api.service.ITParamConfigService;
import com.bittrade.pojo.model.TKline;

/**
 * 获取okex法币汇率
 * 
 * @author zale
 * 
 */
@Component
public class OkexKlineScheduled {

	private static final Logger		LOG					= LoggerFactory.getLogger( OkexKlineScheduled.class );

	@Reference
	private ITKlineService			klineService;

	@Reference
	private ITParamConfigService	paramConfigService;

	// 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
	private ExecutorService			cachedThreadPool	= Executors.newCachedThreadPool();

	/**
	 * 拉取交易对历史K线数据
	 */
	@Scheduled(cron = "0/10 * * * * ?")
	public void kline() {
		try {
			LOG.info( "========================开始拉取OKEX的K先历史数据========================" );
			String klineSwitch = GeneralMethod.qryParamConfigInfo( paramConfigService, ParamKeyEnum.OKEX_KLINE_HISTORY_SWITCH_KEY.getKey() )
					.getParamValue();
			if (ParamValue.OFF.getKey().equals( klineSwitch )) {
				return;
			}
			String[] symbols = GeneralMethod.qryParamConfigInfo( paramConfigService, ParamKeyEnum.OKEX_SYMBOL_KLINE_HISTORY_DATA_KEY.getKey() )
					.getParamValue().split( "," );
			for (String symbol : symbols) {
				cachedThreadPool.execute( new Runnable() {
					@Override
					public void run() {
						try {
							Thread.currentThread().setName( symbol + "_KlineDataHandleThreadName" );
							LOG.info( Thread.currentThread().getName() + "拉取交易对" + symbol + "_K线数据开始" );
							synchronized (Object.class) {
								symbolKlineDataHandle( symbol );
							}
							LOG.info( Thread.currentThread().getName() + "拉取交易对" + symbol + "_K线数据结束" );
						} catch (Exception e) {
							LOG.error( Thread.currentThread().getName() + "error" + e.getMessage(), e );
						}
					}
				} );
			}
			LOG.info( "========================结束拉取OKEX的K先历史数据========================" );
		} catch (Exception e) {
			LOG.error( "OkexKlineScheduled.OkexKlineScheduled.error=" + e.getMessage(), e );
		}
	}

	/**
	 * 交易对K线数据处理
	 * 
	 * @param symbol
	 * @throws Exception
	 */
	private void symbolKlineDataHandle(String symbol) throws Exception {
		Date date = new Date();
		String[] granularitys = GeneralMethod.qryParamConfigInfo( paramConfigService, ParamKeyEnum.OKEX_GRANULARITYS_KEY.getKey() ).getParamValue()
				.split( "," );
		for (int i = 0; i < granularitys.length; i++) {
			int granularity = Integer.parseInt( granularitys[ i ] );
			String klineUrl = GeneralMethod.qryParamConfigInfo( paramConfigService, ParamKeyEnum.OKEX_KLINE_URL_KEY.getKey() ).getParamValue();
			klineUrl = MessageFormat.format( klineUrl, symbol, granularity );

			HttpClientResult result = HttpClientUtils.doGet( klineUrl );
			if (IConstant.OKEX_SUCCESS_CODE == result.getCode()) {
				// okex返回数据处理，并入库
				JSONArray jsonArray = JSONArray.parseArray( result.getContent() );
				List<TKline> addList = new ArrayList<TKline>();
				for (int j = 0; j < jsonArray.size(); j++) {
					JSONArray object = JSONArray.parseArray( jsonArray.get( j ).toString() );
					Date time = isoToUtc( object.getString( 0 ) );
					BigDecimal open = object.getBigDecimal( 1 );
					BigDecimal high = object.getBigDecimal( 2 );
					BigDecimal low = object.getBigDecimal( 3 );
					BigDecimal close = object.getBigDecimal( 4 );
					BigDecimal volume = object.getBigDecimal( 5 );

					TKline kline = new TKline();
					kline.setClose( close );
					kline.setGranularity( granularity );
					kline.setHigh( high );
					kline.setLow( low );
					kline.setOpen( open );
					kline.setSymbol( symbol );
					kline.setTime( time );
					kline.setVolume( volume );
					kline.setCreateTime( date );

					TKline qryKline = new TKline();
					qryKline.setSymbol( symbol );
					qryKline.setGranularity( granularity );
					qryKline.setTime( time );
					TKline resultKline = klineService.getBy( qryKline );
					if (null == resultKline) {
						addList.add( kline );
					} else {
						if (j == 0) { // 只有第一条才需要更新
							kline.setId( resultKline.getId() );
							kline.setCreateTime( null );
							kline.setUpdateTime( date );
							boolean bool = klineService.updateById( kline );
							LOG.info( "更新t_kLine：id=" + kline.getId() + "，是否更新成功：" + bool );
						}
					}
				}
				if (addList.size() > 0) {
					boolean bool = klineService.saveBatch( addList );
					LOG.info( "插入t_kLine表是否成功：" + bool + "插入条数：" + addList.size() );
				}
			} else {
				LOG.error( "OkexKlineScheduled.OkexKlineScheduled.http.request.okex.result.code=" + result.getCode() + ",result.getContent="
						+ result.getContent() );
			}
		}
	}

	/**
	 * isoToUtc:(iso转utc). <br/>
	 * 
	 * @author zale
	 * @param iso
	 * @throws ParseException
	 */
	private static Date isoToUtc(String iso) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'" );
		format.setTimeZone( TimeZone.getTimeZone( "UTC" ) );
		return format.parse( iso );
	}

}
