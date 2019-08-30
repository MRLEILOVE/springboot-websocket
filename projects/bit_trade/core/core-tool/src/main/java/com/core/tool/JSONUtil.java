/**  
 * Project Name:core-tool  
 * File Name:DateUtil.java  
 * Package Name:com.core.tool  
 * DateTime: Jul 18, 2019 6:03:17 PM <br />
 * Copyright (c) 2019, 仙灵科技 All Rights Reserved.  
 *  
*/

package com.core.tool;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.core.common.constant.IConstant;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

/**
 * <p>
 *   JSON实用类
 * </p>
 * ClassName:JSONUtil <br/>
 * Description: TODO 添加描述. <br/>
 * DateTime: Jul 18, 2019 6:03:17 PM <br />
 * 
 * @author Administrator
 * @version
 * @since JDK 1.8
 * @see
 */
public class JSONUtil {
	
	/**
	 * 
	 * toString:(这里用一句话描述这个方法的作用). <br/>  
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>  
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>  
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>  
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>  
	 *  
	 * @author Administrator  
	 * @param obj
	 * @return  
	 * @since JDK 1.8
	 */
	public static final String toString(Object obj) {
		return JSON.toJSONStringWithDateFormat( obj, IConstant.DATETIME_PATTERN, SerializerFeature.WriteDateUseDateFormat );
	}

	public static final String			DEFAULT_FAIL	= "\"Parse failed\"";
	private static final ObjectMapper	objectMapper	= new ObjectMapper();
	private static final ObjectWriter	objectWriter	= objectMapper.writerWithDefaultPrettyPrinter();

	public static void marshal(File file, Object value) throws Exception {
		try {
			objectWriter.writeValue( file, value );
		} catch (JsonGenerationException e) {
			throw new Exception( e );
		} catch (JsonMappingException e) {
			throw new Exception( e );
		} catch (IOException e) {
			throw new Exception( e );
		}
	}

	public static void marshal(OutputStream os, Object value) throws Exception {
		try {
			objectWriter.writeValue( os, value );
		} catch (JsonGenerationException e) {
			throw new Exception( e );
		} catch (JsonMappingException e) {
			throw new Exception( e );
		} catch (IOException e) {
			throw new Exception( e );
		}
	}

	public static String marshal(Object value) throws Exception {
		try {
			return objectWriter.writeValueAsString( value );
		} catch (JsonGenerationException e) {
			throw new Exception( e );
		} catch (JsonMappingException e) {
			throw new Exception( e );
		} catch (IOException e) {
			throw new Exception( e );
		}
	}

	public static byte[] marshalBytes(Object value) throws Exception {
		try {
			return objectWriter.writeValueAsBytes( value );
		} catch (JsonGenerationException e) {
			throw new Exception( e );
		} catch (JsonMappingException e) {
			throw new Exception( e );
		} catch (IOException e) {
			throw new Exception( e );
		}
	}

	public static <T> T unmarshal(File file, Class<T> valueType) throws Exception {
		try {
			return objectMapper.readValue( file, valueType );
		} catch (JsonParseException e) {
			throw new Exception( e );
		} catch (JsonMappingException e) {
			throw new Exception( e );
		} catch (IOException e) {
			throw new Exception( e );
		}
	}

	public static <T> T unmarshal(InputStream is, Class<T> valueType) throws Exception {
		try {
			return objectMapper.readValue( is, valueType );
		} catch (JsonParseException e) {
			throw new Exception( e );
		} catch (JsonMappingException e) {
			throw new Exception( e );
		} catch (IOException e) {
			throw new Exception( e );
		}
	}

	public static <T> T unmarshal(String str, Class<T> valueType) throws Exception {
		try {
			return objectMapper.readValue( str, valueType );
		} catch (JsonParseException e) {
			throw new Exception( e );
		} catch (JsonMappingException e) {
			throw new Exception( e );
		} catch (IOException e) {
			throw new Exception( e );
		}
	}

	public static <T> T unmarshal(byte[] bytes, Class<T> valueType) throws Exception {
		try {
			if (bytes == null) {
				bytes = new byte[0];
			}
			return objectMapper.readValue( bytes, 0, bytes.length, valueType );
		} catch (JsonParseException e) {
			throw new Exception( e );
		} catch (JsonMappingException e) {
			throw new Exception( e );
		} catch (IOException e) {
			throw new Exception( e );
		}
	}
	
}
