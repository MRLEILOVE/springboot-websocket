package com.core.tool;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Administrator
 *
 */
@Slf4j
public class YamlUtil {
	
	/**
	 * 
	 * @param fileName
	 * @return
	 */
	public static final Map<String, Object> loadYaml(String fileName) {
		Map<String, Object> map_yaml = null;
		
		try {
			InputStream is = YamlUtil.class.getClassLoader().getResourceAsStream(fileName);
			
			Yaml yaml = new Yaml();
			map_yaml = yaml.load(is);
			
			is.close();
			is = null;
		} catch (IOException e) {
			log.error(e.toString());
		} finally {
		}
		
		return map_yaml;
	}
	
	/**
	 * 
	 * @param map_yaml
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static final String getYamlValue(Map<String, Object> map_yaml, String name) {
		int i_idx;
		if ((i_idx = name.indexOf('.')) == -1) {
			return map_yaml.get(name).toString();
		}
		
		String str_currentName = name.substring(0, i_idx);
		Map<String, Object> map_yaml_ = (Map<String, Object>) map_yaml.get(str_currentName);
		String str_nextName = name.substring(i_idx + 1);
		return getYamlValue(map_yaml_, str_nextName);
	}
	
}
