package com;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.tree.DefaultElement;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import lombok.Data;

/**
 * 
 * @author Administrator
 * @date 2017年9月14日_下午6:28:24
 *
 */
public class Generate_Code {

	private static /* final */String jdbc_URL = "jdbc.URL";
	private static /* final */String BASE_PATH = "base_path";
	private static /* final */String FRAMEWORK_PACKAGE = "framework_package";
	private static /* final */String BASE_PACKAGE = "base_package";
	private static /* final */String MODULE_NAME = "module_name";
	private static /* final */String REMOVE_FROM_BASE_PATH = "remove_from_base_path";
	private static /* final */String outPath;
	private static /* final */String objectsIgnore = "objects.ignore";
	private static /* final */String objectFilter = "object.filter";
	private static /* final */String columnsIgnore = "columns.ignore";

	static {
		map_types = new HashMap<>();
		map_columnIgnore = new HashMap<>();

		load_DB2Types(null);
		loadData(null);
		initializeData();
	};

	@SuppressWarnings("unused")
	private static final void getCommandArguments() {
		List<String> list_args = java.lang.management.ManagementFactory.getRuntimeMXBean().getInputArguments();
		System.out.println(list_args.size());
		for (int i = 0; i < list_args.size(); i++) {
			System.out.println(list_args.get(i));
		}
	}

	private static final void propsLoad(String fileName, Properties props) {
		// InputStream is =
		// Thread.currentThread().getContextClassLoader().getResourceAsStream("cfg\\data.properties");
		// // args
		// InputStream is =
		// Generate_Code.class.getResourceAsStream("d:\\data.properties"); // args
		// InputStream is =
		// Generate_Code.class.getClassLoader().getResourceAsStream("./data.properties");
		// // args
		InputStream is = null;
		try {
			is = new FileInputStream(".\\cfg\\" + fileName + ".xml"); // ..\\

			// props.load(is);
			props.loadFromXML(is);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				is = null;
			}
		}
		;
	};

	private static final void load_DB2Types(String[] args) {
		try {
			SAXReader reader = new SAXReader();
			Document document = reader.read(new File("./cfg/configuration--DB2Types.xml"));
			Element root = document.getRootElement();
			List<?> list_item = root.selectNodes("/items/item");
			for (int i = 0; i < list_item.size(); i++) {
				DefaultElement de = (DefaultElement) list_item.get(i);
				
				String str_key = de.attributeValue("key");
				String str_javaType = de.attributeValue("javaType");
				String str_jdbcType = de.attributeValue("jdbcType");
				
				map_types.put(str_key, new Types(str_javaType, str_jdbcType));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (map_types == null || map_types.size() == 0) {
			System.err.println("props_DB2Types is null or empty .");

			System.exit(0);
		}
	}

	private static final void loadData(String[] args) {
		// getCommandArguments();

		try {
			Properties props = new Properties();
			propsLoad("data", props);

			jdbc_URL = props.getProperty(jdbc_URL);
			BASE_PATH = props.getProperty(BASE_PATH);
			FRAMEWORK_PACKAGE = props.getProperty(FRAMEWORK_PACKAGE);
			BASE_PACKAGE = props.getProperty(BASE_PACKAGE);
			MODULE_NAME = props.getProperty(MODULE_NAME);
			String str_isRemoveFromBasePath = props.getProperty(REMOVE_FROM_BASE_PATH);
			if (str_isRemoveFromBasePath != null && str_isRemoveFromBasePath.length() > 0) {
				isRemoveFromBasePath = Boolean.parseBoolean(str_isRemoveFromBasePath);
			}
			objectsIgnore = props.getProperty(objectsIgnore);
			objectFilter = props.getProperty(objectFilter);
			columnsIgnore = props.getProperty(columnsIgnore);
		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
		}
		;
	};

	private static boolean isRemoveFromBasePath;
	
	private static final void initializeData() {
		// objectsIgnore .
		if (objectsIgnore != null && objectsIgnore.length() > 0) {
			String[] strArr_objectIgnore = objectsIgnore.split(",");

			map_objectIgnore = new HashMap<String, String>(strArr_objectIgnore.length);
			for (int i = 0; i < strArr_objectIgnore.length; i++) {
				map_objectIgnore.put(strArr_objectIgnore[i], null);
			}
		}
		
		// objectFilter .
		if (objectFilter != null && objectFilter.length() > 0) {
			if (objectFilter.startsWith("*") && objectFilter.endsWith("*")) {
				filterType = 1;
				filterValue = objectFilter.substring(1, objectFilter.length() - 1);
			} else if (objectFilter.startsWith("*")) {
				filterType = 2;
				filterValue = objectFilter.substring(1);
			} else if (objectFilter.endsWith("*")) {
				filterType = 3;
				filterValue = objectFilter.substring(0, objectFilter.length() - 1);
			} else {
				filterType = 4;
				filterValue = objectFilter;
			};
		}
		
		// column_ignore .
		if (columnsIgnore != null && columnsIgnore.length() > 0) {
			String[] strArr_columnIgnore = columnsIgnore.split(",");
			
			for (int i = 0; i < strArr_columnIgnore.length; i++) {
				map_columnIgnore.put(strArr_columnIgnore[i].trim(), "");
			}
		}
	};

	@SuppressWarnings({ "serial", "unused" })
	private static final Map<String, String> map_Java2PropertyType = new HashMap<String, String>() {
		{
			put("java.lang.Boolean", "boolean");
			put("java.lang.Character", "char");
			put("java.lang.Short", "short");
			put("java.lang.Integer", "int");
			put("java.lang.Long", "long");
			put("java.lang.Float", "float");
			put("java.lang.Double", "double");
			put("java.lang.String", "String");
			put("java.sql.Timestamp", "java.util.Date");
		}
	};
	// @SuppressWarnings("serial")
	// private static final Map<String, String> map_DB2PropertyType = new
	// HashMap<String, String>() {
	// {
	// put("BIT", "byte"); // boolean .
	// put("TINYINT", "byte"); // byte .
	// put("BOOLEAN", "boolean");
	// put("CHAR", "String"); // char
	// put("SMALLINT", "short");
	// put("INT", "int");
	// put("INT UNSIGNED", "int");
	// put("BIGINT", "long");
	// put("DECIMAL UNSIGNED", "int");
	// put("DECIMAL", "int");
	// put("FLOAT", "float");
	// put("DOUBLE", "double");
	// put("VARCHAR", "String");
	//// put("NVARCHAR", "String"); // Oracle
	// put("DATETIME", "java.util.Date");
	// put("TIMESTAMP", "java.util.Date");
	// put("DATE", "java.util.Date");
	// }
	// };
	// @SuppressWarnings("serial")
	// private static final Map<String, String> map_DB2JDBCType = new
	// HashMap<String, String>() {
	// {
	// put("BIT", "BIT");
	// put("TINYINT", "TINYINT"); // byte .
	// put("BOOLEAN", "BOOLEAN");
	// put("CHAR", "CHAR");
	// put("SMALLINT", "SMALLINT");
	// put("INT", "INTEGER");
	// put("INT UNSIGNED", "INTEGER");
	// put("BIGINT", "BIGINT");
	// put("DECIMAL UNSIGNED", "DECIMAL");
	// put("DECIMAL", "DECIMAL");
	// put("FLOAT", "FLOAT");
	// put("DOUBLE", "DOUBLE");
	// put("VARCHAR", "VARCHAR");
	//// put("NVARCHAR", "String"); // Oracle
	// put("DATETIME", "TIMESTAMP");
	// put("TIMESTAMP", "TIMESTAMP");
	// put("DATE", "TIMESTAMP");
	// }
	// };
	private static final class Types {
		private String javaType, jdbcType;
		private Types(String javaType, String jdbcType) {
			this.javaType = javaType;
			this.jdbcType = jdbcType;
		}
	};
	private static final Map<String, Types> map_types;
	private static final String getPropertyType(String name) {
		return map_types.get(name).javaType;
	}
	private static final String getJDBCType(String name) {
		return map_types.get(name).jdbcType;
	}
//	@SuppressWarnings("serial")
	private static final Map<String, String> map_columnIgnore;// = new HashMap<String, String>() {
//		{
//			put("ID", "");
//			put("create_user_ID", "");
//			put("create_time", "");
//			put("modify_user_ID", "");
//			put("modify_time", "");
//		}
	//};
	private static /* final */Map<String, String> map_objectIgnore;
	private static /*final */byte filterType;
	private static /*final */String filterValue;

	@Data
	public static final class Struct {
		private String className;
		private String variableName;
		private String tableName;

		@Data
		public static final class Item {
			private String javaType;
			private String jdbcType;
			private String name;
			private String columnName;
			private String methodName;

			private Item(String javaType, String jdbcType, String name, String columnName, String methodName) {
				this.javaType = javaType;
				this.jdbcType = jdbcType;
				this.name = name;
				this.columnName = columnName;
				this.methodName = methodName;
			};
		};

		private List<Item> list_item;
		private List<Item> list_filterItem;

		private Struct(String className, String variableName, String tableName, List<Item> list_item,
				List<Item> list_filterItem) {
			this.className = className;
			this.variableName = variableName;
			this.tableName = tableName;
			this.list_item = list_item;
			this.list_filterItem = list_filterItem;
		};
	};

	private static /* final */Map<String, Struct> map_struct;

	private static final String toUpperStart(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	private static final String toLowerStart(String str) {
		return str.substring(0, 1).toLowerCase() + str.substring(1);
	}

	private static final String columnName2PropertyName(String columnName) {
		String str_propertyName;

		String[] strArr_tblName = columnName.split("_");
		StringBuilder strBud = new StringBuilder(strArr_tblName[0]);
		for (int i = 1; i < strArr_tblName.length; i++) {
			strBud.append(toUpperStart(strArr_tblName[i]));
		}

		str_propertyName = strBud.toString();

		return str_propertyName;
	}

	private static final String tableName2ClassName(String tableName) {
		String str_className;

		StringBuilder strBud = new StringBuilder();
		// tableName = tableName.substring(2);
		String[] strArr_tblName = tableName.split("_");
		for (int i = 0; i < strArr_tblName.length; i++) {
			strBud.append(toUpperStart(strArr_tblName[i]));
		}

		str_className = strBud.toString();

		return str_className;
	}

	private static final boolean makeSurePathExist(String path) {
		boolean flag = false;

		File f = new File(path);
		if (!f.exists()) {
			flag = f.mkdirs();
		}

		return flag;
	}

	static void getDBSchema() throws SQLException {
		Connection con = DriverManager.getConnection(jdbc_URL);
		// SELECT * FROM information_schema.tables WHERE table_schema = 'crm';
		PreparedStatement pstmt = con.prepareStatement("SHOW TABLES");
		ResultSet rs = pstmt.executeQuery();

		// SELECT * FROM information_schema.columns WHERE table_schema = 'crm' AND table_name = 'sys_user';
//		PreparedStatement pstmt_tbl = con.prepareStatement("SELECT * FROM ? WHERE 0 = 1");
		
		map_struct = new HashMap<>(/* 16 */);
		while (rs.next()) {
			String str_tblName = rs.getString(1);
			// System.out.println("str_tblName=" + str_tblName);
			String str_clsName = tableName2ClassName(str_tblName);

			/**/ {
				boolean flag_pass = false;
				// ignore .
				if (map_objectIgnore != null && map_objectIgnore.containsKey(str_clsName)) {
					continue;
				}
				// filter .
				if (filterType > 0) {
					switch (filterType) {
					case 1:
						if (str_tblName.contains(filterValue)) {
							flag_pass = true;
						}
						break;
					case 2:
						if (str_tblName.endsWith(filterValue)) {
							flag_pass = true;
						}
						break;
					case 3:
						if (str_tblName.startsWith(filterValue)) {
							flag_pass = true;
						}
						break;
					case 4:
						if (str_tblName.equals(filterValue)) {
							flag_pass = true;
						}
						break;
					default:
						break;
					}
					if (!flag_pass) {
						continue;
					}
				}
			}

			
//			pstmt_tbl.setString(1, str_tblName);
			PreparedStatement pstmt_tbl = con.prepareStatement("SELECT * FROM " + str_tblName + " WHERE 0 = 1");
			ResultSet rs_tbl = pstmt_tbl.executeQuery();
			ResultSetMetaData rsmd = rs_tbl.getMetaData();

			List<Struct.Item> list_structItem = new ArrayList<>(rsmd.getColumnCount());
			List<Struct.Item> list_structFilterItem = new ArrayList<>(rsmd.getColumnCount());
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				String str_columnName = rsmd.getColumnName(i);
				String str_columnTypeName = rsmd.getColumnTypeName(i);

				// System.out.println(str_columnName + '=' + str_columnTypeName + ',' +
				// rsmd.getColumnClassName(i));
				String str_javaType = getPropertyType(str_columnTypeName); // getColumnClassName
				if (str_javaType == null) {
					System.err.println("str_javaType is null, str_columnTypeName=" + str_columnTypeName);
				}
				String str_jdbcType = getJDBCType(str_columnTypeName);
				if (str_jdbcType == null) {
					System.err.println("str_jdbcType is null, str_columnTypeName=" + str_columnTypeName);
				}
				String str_name = columnName2PropertyName(str_columnName);
				String str_methodName = toUpperStart(str_name);

				Struct.Item item = new Struct.Item(str_javaType, str_jdbcType, str_name, str_columnName,
						str_methodName);

				list_structItem.add(item);

				if (map_columnIgnore.containsKey(str_columnName)) {
					System.err.println("column \"" + str_columnName + "\" is be ignore .");
					
					continue;
				}

				list_structFilterItem.add(item);
			}
			// System.out.println("\r\n");
			String str_varName = toLowerStart(str_clsName);
			map_struct.put(str_tblName,
					new Struct(str_clsName, str_varName, str_tblName, list_structItem, list_structFilterItem));

			rs_tbl.close();
			rs_tbl = null;
			pstmt_tbl.close();
			pstmt_tbl = null;

			// break; // for test .
		}

//		pstmt_tbl.close();
//		pstmt_tbl = null;
		
		rs.close();
		rs = null;
		pstmt.close();
		pstmt = null;
		con.close();
		con = null;
	}

	@SuppressWarnings({ "deprecation" })
	static void generate() throws Exception {
		Configuration configuration = new Configuration();
		configuration.setDirectoryForTemplateLoading(new File(BASE_PATH + "templates"/* + '\\' */));
		configuration.setObjectWrapper(new DefaultObjectWrapper());
		configuration.setDefaultEncoding("UTF-8");

		String str_relativePath = BASE_PACKAGE.replace('.', '\\') + '\\';
		outPath = BASE_PATH + str_relativePath + "";
		makeSurePathExist(outPath);
		// 是否移除输出目录
		if (isRemoveFromBasePath) {
			File f = new File(outPath);
			File[] objArr_file = f.listFiles();
			for (int i = 0; i < objArr_file.length; i++) {
				if (objArr_file[i].isDirectory()) { // need recursion .
					Runtime.getRuntime().exec("cmd.exe /C rmdir /s /q \"" + objArr_file[i].getAbsolutePath() + "\"");
				} else if (objArr_file[i].isFile()) {
					objArr_file[i].delete();
				}
			}
		}

		_entity(configuration);
		_DTO(configuration);
		_VO(configuration);
		_DAO(configuration);
		_DAO_XML(configuration);
		_service(configuration);
		_serviceImpl(configuration);
		_controller(configuration);
		// _JSP();

		System.out.println("恭喜，生成成功~~");
		Runtime.getRuntime().exec("cmd.exe /C start explorer.exe \"" + System.getProperty("user.dir") + outPath + "\"");
	}

	private static final String getOutPath(String name) {
		String str_path = outPath + name + "\\";
		if (MODULE_NAME != null && MODULE_NAME.length() > 0) {
			str_path += MODULE_NAME + '\\';
		}
		return str_path;
	}

	private static final void setBase(Map<String, Object> map) {
		map.put("FRAMEWORK_PACKAGE", FRAMEWORK_PACKAGE);
		map.put("BASE_PKG", BASE_PACKAGE);
		if (MODULE_NAME != null && MODULE_NAME.length() > 0) {
			map.put("module_name", '.' + MODULE_NAME);
		}
	}
	
	@SuppressWarnings("serial")
	private static void _entity(Configuration configuration) throws TemplateNotFoundException,
	MalformedTemplateNameException, ParseException, IOException, TemplateException {
		// Entity .
		Template template = configuration.getTemplate("Entity-template.java");
		String str_path = getOutPath("entity");
		makeSurePathExist(str_path);

		Map<String, Object> paramMap = new HashMap<String, Object>();
		setBase(paramMap);
		for (String tblName : map_struct.keySet()) {
			Struct struct = map_struct.get(tblName);

			paramMap.put("name", struct.className);

			List<Map<String, String>> nameList = new ArrayList<Map<String, String>>();
			for (int i = 0; i < struct.list_filterItem.size(); i++) {
				final Struct.Item itm = struct.list_filterItem.get(i);

				nameList.add(new HashMap<String, String>() {
					{
						put("javaType", itm.javaType);
						put("name", itm.name);
						put("methodName", itm.methodName);
					}
				});
			}
			paramMap.put("properties", nameList);

			Writer writer = new OutputStreamWriter(new FileOutputStream(str_path + struct.className + ".java"),
					"UTF-8");
			template.process(paramMap, writer);
			writer.flush();
			writer.close();
			writer = null;
		}
	}

	@SuppressWarnings("serial")
	private static void _DTO(Configuration configuration) throws TemplateNotFoundException,
	MalformedTemplateNameException, ParseException, IOException, TemplateException {
		// DTO .
		Template template = configuration.getTemplate("DTO-template.java");
		String str_path = getOutPath("dto");
		makeSurePathExist(str_path);

		Map<String, Object> paramMap = new HashMap<String, Object>();
		setBase(paramMap);
		for (String tblName : map_struct.keySet()) {
			Struct struct = map_struct.get(tblName);

			paramMap.put("name", struct.className);

			List<Map<String, String>> nameList = new ArrayList<Map<String, String>>();
			for (int i = 0; i < struct.list_filterItem.size(); i++) {
				final Struct.Item itm = struct.list_filterItem.get(i);

				nameList.add(new HashMap<String, String>() {
					{
						put("javaType", itm.javaType);
						put("name", itm.name);
						put("methodName", itm.methodName);
					}
				});
			}
			paramMap.put("properties", nameList);

			Writer writer = new OutputStreamWriter(new FileOutputStream(str_path + struct.className + "DTO.java"),
					"UTF-8");
			template.process(paramMap, writer);
			writer.flush();
			writer.close();
			writer = null;
		}
	}

	@SuppressWarnings("serial")
	private static void _VO(Configuration configuration) throws TemplateNotFoundException,
	MalformedTemplateNameException, ParseException, IOException, TemplateException {
		// DTO .
		Template template = configuration.getTemplate("VO-template.java");
		String str_path = getOutPath("vo");
		makeSurePathExist(str_path);

		Map<String, Object> paramMap = new HashMap<String, Object>();
		setBase(paramMap);
		for (String tblName : map_struct.keySet()) {
			Struct struct = map_struct.get(tblName);

			paramMap.put("name", struct.className);

			List<Map<String, String>> nameList = new ArrayList<Map<String, String>>();
			for (int i = 0; i < struct.list_filterItem.size(); i++) {
				final Struct.Item itm = struct.list_filterItem.get(i);

				nameList.add(new HashMap<String, String>() {
					{
						put("javaType", itm.javaType);
						put("name", itm.name);
						put("methodName", itm.methodName);
					}
				});
			}
			paramMap.put("properties", nameList);

			Writer writer = new OutputStreamWriter(new FileOutputStream(str_path + struct.className + "VO.java"),
					"UTF-8");
			template.process(paramMap, writer);
			writer.flush();
			writer.close();
			writer = null;
		}
	}

	private static void _DAO(Configuration configuration) throws TemplateNotFoundException,
	MalformedTemplateNameException, ParseException, IOException, TemplateException {
		// DAO .
		Template template = configuration.getTemplate("DAO-template.java");
		String str_path = getOutPath("dao");
		makeSurePathExist(str_path);

		Map<String, Object> paramMap = new HashMap<String, Object>();
		setBase(paramMap);
		for (String tblName : map_struct.keySet()) {
			Struct struct = map_struct.get(tblName);

			paramMap.put("struct", struct);

			Writer writer = new OutputStreamWriter(new FileOutputStream(str_path + "I" + struct.className + "DAO.java"),
					"UTF-8");
			template.process(paramMap, writer);
			writer.flush();
			writer.close();
			writer = null;
		}
	}

	private static void _DAO_XML(Configuration configuration) throws TemplateNotFoundException,
	MalformedTemplateNameException, ParseException, IOException, TemplateException {
		// DAO_XML .
		Template template = configuration.getTemplate("DAO_XML-template.xml");
		String str_path = getOutPath("dao\\mapper");
		makeSurePathExist(str_path);

		Map<String, Object> paramMap = new HashMap<String, Object>();
		setBase(paramMap);
		for (String tblName : map_struct.keySet()) {
			Struct struct = map_struct.get(tblName);

			paramMap.put("struct", struct);

			Writer writer = new OutputStreamWriter(new FileOutputStream(str_path + "I" + struct.className + "DAO.xml"),
					"UTF-8");
			template.process(paramMap, writer);
			writer.flush();
			writer.close();
			writer = null;
		}
	}

	private static void _service(Configuration configuration) throws TemplateNotFoundException,
	MalformedTemplateNameException, ParseException, IOException, TemplateException {
		// Service .
		Template template = configuration.getTemplate("Service-template.java");
		String str_path = getOutPath("service");
		makeSurePathExist(str_path);

		Map<String, Object> paramMap = new HashMap<String, Object>();
		setBase(paramMap);
		for (String tblName : map_struct.keySet()) {
			Struct struct = map_struct.get(tblName);

			paramMap.put("struct", struct);

			Writer writer = new OutputStreamWriter(
					new FileOutputStream(str_path + "I" + struct.className + "Service.java"), "UTF-8");
			template.process(paramMap, writer);
			writer.flush();
			writer.close();
			writer = null;
		}
	}

	private static void _serviceImpl(Configuration configuration) throws TemplateNotFoundException,
	MalformedTemplateNameException, ParseException, IOException, TemplateException {
		// ServiceImpl .
		Template template = configuration.getTemplate("ServiceImpl-template.java");
		String str_path = getOutPath("service\\impl");
		makeSurePathExist(str_path);

		Map<String, Object> paramMap = new HashMap<String, Object>();
		setBase(paramMap);
		for (String tblName : map_struct.keySet()) {
			Struct struct = map_struct.get(tblName);

			paramMap.put("struct", struct);

			Writer writer = new OutputStreamWriter(
					new FileOutputStream(str_path + struct.className + "ServiceImpl.java"), "UTF-8");
			template.process(paramMap, writer);
			writer.flush();
			writer.close();
			writer = null;
		}
	}

	private static void _controller(Configuration configuration) throws TemplateNotFoundException,
	MalformedTemplateNameException, ParseException, IOException, TemplateException {
		// Controller .
		Template template = configuration.getTemplate("Controller-template.java");
		String str_path = getOutPath("controller");
		makeSurePathExist(str_path);

		Map<String, Object> paramMap = new HashMap<String, Object>();
		setBase(paramMap);
		for (String tblName : map_struct.keySet()) {
			Struct struct = map_struct.get(tblName);

			paramMap.put("struct", struct);

			Writer writer = new OutputStreamWriter(
					new FileOutputStream(str_path + struct.className + "Controller.java"), "UTF-8");
			template.process(paramMap, writer);
			writer.flush();
			writer.close();
			writer = null;
		}
	}

	public static void main(String[] args) throws Exception {
		getDBSchema();
		generate();
	}

}
