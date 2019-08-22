package com.core.tool;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

/**
 * 
 * @author Administrator
 *
 */
//@lombok.extern.slf4j.Slf4j
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
			e.printStackTrace();
//			log.error(e.toString());
		} catch (Exception e) {
			e.printStackTrace();
//			log.error(e.toString());
		} finally {
		}
		
		return map_yaml;
	}
	
	/**
	 * <pre>
	 * Can be mapped to an object .
	 * Or that, only you can't think of it, he can't do it without him .
	 * </pre>
	 * @param map_yaml
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static final String getYamlValue(Map<String, Object> map_yaml, String name) {
		if (map_yaml == null || map_yaml.size() == 0 || name == null || name.length() == 0) {
			return null;
		}
		
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
