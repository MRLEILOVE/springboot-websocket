package com.bittrade.admin.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.core.common.constant.GlobalConstant;
import com.core.tool.HttpUtil;

/**
 * 获取地址类
 * 
 * @author who ?
 */
public class AddressUtil {
	private static final Logger	log		= LoggerFactory.getLogger( AddressUtil.class );

	public static final String	IP_URL	= "http://ip.taobao.com/service/getIpInfo.php";

	public static String getRealAddressByIP(String ip) {
		String address = "XX XX";

		// 内网不查询
		if (GlobalConstant.LOCALHOST_IP_16.equals( ip ) || IpUtil.internalIp( ip )) {
			return "内网IP";
		}
		String rspStr = HttpUtil.sendPost( IP_URL, "ip=" + ip );
		if (StringUtils.isEmpty( rspStr )) {
			log.error( "获取地理位置异常 {}", ip );
			return address;
		}
		return ip;
//		JSONObject obj;
//		try {
//			obj = JSON.unmarshal( rspStr, JSONObject.class );
//			JSONObject data = obj.getObj( "data" );
//			String region = data.getStr( "region" );
//			String city = data.getStr( "city" );
//			hotAddress = region + " " + city;
//		} catch (Exception e) {
//			log.error( "获取地理位置异常 {}", ip );
//		}
//		return hotAddress;
	}
}
