package com.bittrade.admin.util;

import com.bittrade.admin.enums.ConstantEnum.MessageType;
import com.bittrade.admin.exception.BusinessException;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * redis：Key管理工具类
 *
 * @author ourblue
 * @date 2018-09-05
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RedisKeyUtil {
    private static final String USER_REGISTER_SEND_SMS = "user:register:send:sms";
    private static final String USER_FINDPASS_SEND_SMS = "user_findpass_send_sms";
    private static final String USER_UPDATEPAYPASS_SEND_SMS = "user:updatepaypass:send:sms";
    private static final String USER_UPDATEQRCODE_SEND_SMS = "user:updateqrcode:send:sms";
    private static final String SYMBOL_KEY = "symbol";
    private static final String SYMBOL_TICKER_KEY = "symbol:ticker";
    private static final String K_LINE_KEY = "k:line";
    private static final String K_LINE_START_TIME_KEY = "k:line:start:time";
    private static final String NEW_K_LINE_TICKER_KEY = "new:k:line:ticker";
    private static final String RATE = "rate";
    private static final String REPEAT_SUBMIT_KEY = "repeat:submit:key:order";
    private static final String K_LINE_CONFIG_KEY = "k:line:config:key:supported:resolutions";
    private static final String K_LINE_URL_KEY = "k:line:url:key";
    private static final String K_LINE_GRANULARITYS_1_KEY = "k:line:granularitys:1:key";
    private static final String K_LINE_GRANULARITYS_2_KEY = "k:line:granularitys:2:key";
    private static final String SOCKET_TICKER_CONNECT_KEY = "socket:ticker:connect:key";
    private static final String MY_CHANNEL_KEY = "my:channel:key";
    private static final String WEBSOCKET_REQUEST_KEY = "websocket:request";
    private static final String LAST_SYMBOL_TICKER_TIME = "last:symbol:ticker:time";
    private static final String SYMBOL_LAST_TICKER_KEY = "symbol:last:ticker:key";
    private static final String OKEX_WEB_SOCKET_HEART_BEAT = "okex:web:socket:heart:beat";
    private static final String HB_WEB_SOCKET_HEART_BEAT = "hb:web:socket:heart:beat";
    private static final String OKEX_MICRO_TICKER = "okex:micro:ticker";
    private static final String HB_KLINE_TIME = "hb:kline:time";
    private static final String MICRO_TICKER_TIME = "micro:ticker:time";
    private static final String PUSH_KEY = "push";
    public static final String Quotation_Health_TEMPLATE = "Quotation:Health:Fluctuation";
    public static final String HB_SOCKET_TIME_EXPIRED = "hb:socket:time:expired";

    // 灰度发布
    public static final String GRAY_RELEASE = "grayRelease";

    // 今日奖励金前10
    public static final String TODAY_BOUNTY_TOP10 = "todayBountyTop10";

    // 用户ID
    public static final String KEY_USER_ID = "KEY_USER_ID";

    // 微合约ID
    public static final String KEY_MICRO_ID = "KEY_MICRO_ID";

    // 永续合约ID
    public static final String KEY_PERPETUAL_ID = "KEY_PERPETUAL_ID";

    // 体验金清零
    public static final String TOKEN_CLEARING = "token:clearing";

    // 微合约交割
    public static final String DELIVERY_MICRO = "deliveryMicro:";

    // 微合约交割完成
    public static final String DELIVERY_MICRO_FINISH = "deliveryMicro:finish:";

    public static String getHbSocketTimeExpired() {
        return HB_SOCKET_TIME_EXPIRED;
    }

    public static String getHbKlineTime(String period) {
        return HB_KLINE_TIME + ":" + period;
    }

    public static String getMicroTickerTime(String symbol) {
        return MICRO_TICKER_TIME + ":" + symbol;
    }

    public static String getOkexMicroTicker(String symbol) {
        return OKEX_MICRO_TICKER + ":" + symbol;
    }

    public static String getOkexWebSocketHeartBeat() {
        return OKEX_WEB_SOCKET_HEART_BEAT;
    }

    public static String getHbWebSocketHeartBeat() {
        return HB_WEB_SOCKET_HEART_BEAT;
    }

    public static String getSymbolLastTickerKey(String symbol) {
        return SYMBOL_LAST_TICKER_KEY + ":" + symbol;
    }

    public static String getLastSymbolTickerTime(String symbol) {
        return LAST_SYMBOL_TICKER_TIME + ":" + symbol;
    }

    public static String getSymbolTickerKey(String symbol) {
        return SYMBOL_TICKER_KEY + ":" + symbol;
    }

    public static String getMyChannelKey() {
        return MY_CHANNEL_KEY;
    }

    public static String getSocketTickerConnectKey() {
        return SOCKET_TICKER_CONNECT_KEY;
    }

    public static String getkLineGranularitys1Key() {
        return K_LINE_GRANULARITYS_1_KEY;
    }

    public static String getkLineGranularitys2Key() {
        return K_LINE_GRANULARITYS_2_KEY;
    }

    public static String getkLineUrlKey() {
        return K_LINE_URL_KEY;
    }

    public static String getkLineConfigKey() {
        return K_LINE_CONFIG_KEY;
    }

    // 平仓防重复提交KEY
    public static String getRepeatSubmitKey(String orderId) {
        return REPEAT_SUBMIT_KEY + ":" + orderId;
    }

    public static String getRate(String name) {
        return RATE + ":" + name;
    }

    // 发送短信对应的redis key
    public static String getUserRedisKey(Integer type, String phone) {

        if (MessageType.USER_REGISTER.getCode().equals(type) ) {
            return getRegisterSms(phone);

        } else if (MessageType.FIND_PASSWORD.getCode().equals(type)) {
            return getFindPassSms(phone);

        } else if (MessageType.CHANGE_LOGINPWD.getCode().equals(type)) {
            return getUpdatePaypassSms(phone);

        }else if (MessageType.UPDATE_RECEIVING.getCode().equals(type)) {
            return getUpdateQrcodeSms(phone);
        }else {
            throw new BusinessException("错误的类型");
        }
    }

    // 注册短信redis key
    public static String getRegisterSms(String phone) {
        return USER_REGISTER_SEND_SMS + ":" + phone;
    }

    // 忘记密码redis key
    public static String getFindPassSms(String phone) {
        return USER_FINDPASS_SEND_SMS + ":" + phone;
    }

    // 修改支付密码redis key
    public static String getUpdatePaypassSms(String phone) {
        return USER_UPDATEPAYPASS_SEND_SMS + ":" + phone;
    }

    // 修改收付款二维码redis key
    public static String getUpdateQrcodeSms(String phone) {
        return USER_UPDATEQRCODE_SEND_SMS + ":" + phone;
    }

    public static String getUserSendSms(String phone) {
        return USER_REGISTER_SEND_SMS + ":" + phone;
    }

    // 获取某个交易对行情信息redis_key
    public static String getSymbol(String symbol) {
        return SYMBOL_KEY + ":" + symbol;
    }

    // 获取某个推送信息redis_key
    public static String getPushKey(String push) {
        return PUSH_KEY + ":" + push;
    }

    // 获取K线数据redis_key
    public static String getKlineKey(String symbol, String granularity) {
        return K_LINE_KEY + ":" + symbol + ":" + granularity;
    }

    // 获取K线数据redis_key
    public static String getKlineStartTimeKey(String symbol, Integer granularity) {
        return K_LINE_START_TIME_KEY + ":" + symbol + ":" + granularity;
    }

    public static String getNewKlineTickerKey() {
        return NEW_K_LINE_TICKER_KEY;
    }

    // 配置表redis_key前缀
    public static final String PARAMETER_CONFIG_PREF = "config:";

    // 字典数据缓存redis_key前缀
    public static final String DICT_DATA_CACHE_PREF = "dict:";

    // 用户持有公狗总价值缓存redis_key前缀
    public static final String USER_MALE_DOG_TOTAL_WORTH_CACHE_PREF = "userMaleDogTotalWorth:comboGroupId:";

    // 用户推广海报URL地址redis_key前缀
    public static final String LABOUR_POSTER_PATH = "poster:";
    // 用户套餐领养redis_key前缀
    public static final String ADOPTION = "adoption:";
    // 配置表redis_key
    public static final String PARAMETER_CONFIG = "parameterConfig";
    // 蓝鲸汇率
    public static final String LANJING_INTERFACE_RATE = "lanjing_interfaceRate";

    // WebSocket配置Redis Key
    public static String getWebsocketRequestKey() {
        return WEBSOCKET_REQUEST_KEY;
    }

	// 上传凭证redis_key 的前缀
	public static final String UPLOAD_CREDENTIALS = "credentials:";
	// 配置表ComboGroup的id做前缀,value=comboGroup,key是all表示存入所有
	public static final String COMBO_GROUP_ID = "comboGroup:id:";
	// 配置表PersonalCard的userid做前缀,value=PersonalCard对象
	public static final String PERSONAL_CARD_USERID = "personalCard:userid:";
	// 配置表BetaReservation的userid做前缀,value=SetList<BetaReservation>集合
	public static final String BETA_RESERVATION = "reservation:userid:";
	// 用户访问次数
    public static final String ACCESS_LIMIT_USERID = "access:limit:userid:";
    // IP访问次数
    public static final String ACCESS_LIMIT_IP = "access:limit:ip:";

	// 各种剩余公狗数量
	public static final String MALE_REMAIN = "maleRemain:comboid:";
	// 套餐的预约人数
	public static final String MALE_RESERVE = "maleReserve:comboid:";
    // 套餐剩余公狗数量后台设置
    public static final String MALE_REMAIN_SYSTEM = "maleRemainSystem:comboid:";
    // 套餐的预约人数后台设置
    public static final String MALE_RESERVE_SYSTEM = "maleReserveSystem:comboid:";


	// 用户待转让数量
	public static final String BETA_TURN_COUNT = "betaOrderCount:userid:";
	// 用户领养数量
	public static final String BETA_GET_COUNT = "betaGetCount:userid:";
	// 购买狗窝商品，为保证分布式数据一致性
	public static final String HOUSE_PRODUCT = "houseProduct:orderNo:";
	// 套餐饱和度
	public static final String	COMBO_GROUP_SATURATION = "comboGroup:saturation";
    // 下级实名团队人数统计
    public static final String	AUTH_TEAM_COUNT = "authTeamCount:";

    // 保存用户是否进入抢狗
    public static final String DOPTION_USER_ID = "doption:userid:";

    // 保存用户是否已抢到狗
    public static final String GRAB_USER_ID = "grab:userid:";

    // 母狗图片
    public static final String BETAFEMINA_IMG = "betaFemina:img:";

    // 短信发送失败
    public static final String SEND_SMS_FAIL_STATUS = "sendSmsStatus:fail:";


	// 配置表UserLabour的userid做前缀,value=List<UserLabour>
	public static final String USER_LABOUR_USERID = "userLabour:userid:";
	// 配置表UserLabour的parentid和userid做前缀,value=List<Long>
	public static final String USER_LABOUR_PARENTID = "userLabour:parentid:";
	// 配置表BetaAccount的userid和钱包类型做前缀,value=Map<String, BigDecimal>
	public static final String BETA_ACCOUNT_USERID_TYPE = "betaAccount:userid:type:";
	// 配置表UserStatistic的userid做前缀,value=UserStatistic
	public static final String USER_STATISTIC_USERID = "userStatistic:userid:";
	// 配置表BetaOrder的userid做前缀,value=List<BetaOrder>
	public static final String BETA_ORDER_USERID = "betaOrder:userid:";
    // 配置表AccountConfig的id做前缀,value=accountConfig,key是all表示存入所有
    public static final String ACCOUNT_CONFIG_ID = "accountConfig:id:";
    // 配置表AccountConfig的type做前缀
    public static final String ACCOUNT_CONFIG_TYPE = "accountConfig:type:";

    public static final String USER_REGISTER_GAME = "user:game:";
}