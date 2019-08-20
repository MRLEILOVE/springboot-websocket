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

	private static /* final */String CFG_FILE = "data_2";
	private static /* final */String CFG_FILE_DB2Types_2 = "configuration--DB2Types_2";

	// get(*_key) = value .
	private static /* final */String jdbc_URL = "jdbc.URL";
	private static /* final */String jdbc_username = "jdbc.username";
	private static /* final */String jdbc_password = "jdbc.password";
	private static /* final */String DB_NAME = "DB_name";
	private static /* final */String BASE_OUT_PATH = "base_out_path";
	private static /* final */String FRAMEWORK_PACKAGE = "framework_package";
	private static /* final */String POJO_BASE_PACKAGE = "POJO_base_package";
	private static /* final */String DEFAULT_BASE_PACKAGE = "default_base_package";
	private static /* final */String API_BASE_PACKAGE = "API_base_package";
	private static /* final */String BASE_PACKAGE = "base_package";
	@SuppressWarnings("unused")
	private static /* final */String MODULE_NAME = "module_name";
	private static /* final */String REMOVE_FROM_BASE_PATH = "remove_from_base_path";
	private static /* final */String _POJOOutPath, _DefaultOutPath, _APIOutPath, outPath;
	private static /* final */String objectsIgnore = "objects.ignore";
	private static /* final */String objectFilter = "object.filter";
	private static /* final */String columnsIgnore = "columns.ignore";
	
	private static /* final */boolean isPackageLowerCase;
	private static /* final */boolean isUseModuleName;

	private static /* final */String DTO = "DTO";
	private static /* final */String VO = "VO";
	private static /* final */String DAO = "DAO";
	
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
			Document document = reader.read(new File("./cfg/" + CFG_FILE_DB2Types_2 + ".xml"));
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
			propsLoad(CFG_FILE, props);

			jdbc_URL = props.getProperty(jdbc_URL);
			jdbc_username = props.getProperty(jdbc_username);
			jdbc_password = props.getProperty(jdbc_password);
			DB_NAME = props.getProperty(DB_NAME);
			BASE_OUT_PATH = props.getProperty(BASE_OUT_PATH);
			FRAMEWORK_PACKAGE = props.getProperty(FRAMEWORK_PACKAGE);
			POJO_BASE_PACKAGE = props.getProperty(POJO_BASE_PACKAGE);
			DEFAULT_BASE_PACKAGE = props.getProperty(DEFAULT_BASE_PACKAGE);
			API_BASE_PACKAGE = props.getProperty(API_BASE_PACKAGE);
			BASE_PACKAGE = props.getProperty(BASE_PACKAGE);
//			MODULE_NAME = props.getProperty(MODULE_NAME);
			String str_isRemoveFromBasePath = props.getProperty(REMOVE_FROM_BASE_PATH);
			if (str_isRemoveFromBasePath != null && str_isRemoveFromBasePath.length() > 0) {
				isRemoveFromBasePath = Boolean.parseBoolean(str_isRemoveFromBasePath);
			}
			objectsIgnore = props.getProperty(objectsIgnore);
			objectFilter = props.getProperty(objectFilter);
			columnsIgnore = props.getProperty(columnsIgnore);
			
			isPackageLowerCase = Boolean.parseBoolean(props.getProperty("is_package_lower_case"));
			isUseModuleName = Boolean.parseBoolean(props.getProperty("is_use_module_name"));
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
//		if (objectFilter != null && objectFilter.length() > 0) {
//			if (objectFilter.startsWith("*") && objectFilter.endsWith("*")) {
//				filterType = 1;
//				filterValue = objectFilter.substring(1, objectFilter.length() - 1);
//			} else if (objectFilter.startsWith("*")) {
//				filterType = 2;
//				filterValue = objectFilter.substring(1);
//			} else if (objectFilter.endsWith("*")) {
//				filterType = 3;
//				filterValue = objectFilter.substring(0, objectFilter.length() - 1);
//			} else {
//				filterType = 4;
//				filterValue = objectFilter;
//			};
//		}
		
		// column_ignore .
		if (columnsIgnore != null && columnsIgnore.length() > 0) {
			String[] strArr_columnIgnore = columnsIgnore.split(",");
			
			for (int i = 0; i < strArr_columnIgnore.length; i++) {
				map_columnIgnore.put(strArr_columnIgnore[i].trim(), "");
			}
		}
		
		// .
		if (isPackageLowerCase) { // Boolean.TRUE.toString().equals()
			DTO = DTO.toLowerCase();
			VO = VO.toLowerCase();
			DAO = DAO.toLowerCase();
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
		try {
			return map_types.get(name).javaType;
		} catch (Exception e) {
			System.err.println("private static final String getPropertyType(String name) ===" + name);
			e.printStackTrace();
			throw e;
		}
	}
	private static final String getJDBCType(String name) {
		try {
			return map_types.get(name).jdbcType;
		} catch (Exception e) {
			System.err.println("private static final String getJDBCType(String name) ===" + name);
			e.printStackTrace();
			throw e;
		}
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
//	private static /*final */byte filterType;
//	private static /*final */String filterValue;

	@Data
	public static final class Struct {
		private String className;
		private String variableName;
		private String tableName;
		private String tableComment;
		private String moduleName;

		@Data
		public static final class Item {
			private String javaType;
			private String jdbcType;
			private String name;
			private String columnName;
			private String upperColumnName;
			private String methodName;
			private boolean isAutoIncrement;
			private String comment;

			private Item(String javaType, String jdbcType, String name, String columnName, String upperColumnName, String methodName, boolean isAutoIncrement, String comment) {
				this.javaType = javaType;
				this.jdbcType = jdbcType;
				this.name = name;
				this.columnName = columnName;
				this.upperColumnName = upperColumnName;
				this.methodName = methodName;
				this.isAutoIncrement = isAutoIncrement;
				this.comment = comment;
			};
		};

		private List<Item> list_item;
		private List<Item> list_itemPK;
//		private List<Item> list_filterItem;

		private Struct(String className, String variableName, String tableName, String tableComment, String moduleName, 
				List<Item> list_item, List<Item> list_itemPK) {
			this.className = className;
			this.variableName = variableName;
			this.tableName = tableName;
			this.tableComment = tableComment;
			this.moduleName = moduleName;
			
			this.list_item = list_item;
			this.list_itemPK = list_itemPK;
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

	@SuppressWarnings("unused")
	private static final String getModuleNameFromTableName(String tableName) {
		String str_moduleName;

		int i_fromIndex, i_toIndex;
		i_fromIndex = tableName.indexOf('_') + 1;
		i_toIndex = tableName.indexOf('_', i_fromIndex);

		str_moduleName = tableName.substring(i_fromIndex, i_toIndex);
		
		return str_moduleName;
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
		String str_SQL = "SELECT table_name, table_comment FROM information_schema.tables WHERE table_schema = ?";
		if (objectFilter != null && objectFilter.length() > 0) {
			str_SQL += " AND table_name LIKE ?";
		}
		str_SQL += ";";
		
		Connection con = DriverManager.getConnection(jdbc_URL, jdbc_username, jdbc_password);
		PreparedStatement pstmt = con.prepareStatement(str_SQL);
		pstmt.setString(1, DB_NAME);
		if (objectFilter != null && objectFilter.length() > 0) {
			pstmt.setString(2, objectFilter);
		}
		ResultSet rs = pstmt.executeQuery();
		rs.last();
		System.out.println("rs.getRow()=" + rs.getRow());
		rs.beforeFirst();

		PreparedStatement pstmt_tbl = con.prepareStatement(
				"SELECT column_name, UPPER(data_type) AS data_type, column_key, extra, column_comment FROM information_schema.columns WHERE table_schema = ? AND table_name = ?"
				);
		pstmt_tbl.setString(1, DB_NAME);
		ResultSet rs_tbl;
		
		map_struct = new HashMap<>(/* 16 */);
		while (rs.next()) {
			String str_tblNm = rs.getString("table_name");
			// System.out.println("str_tblName=" + str_tblName);
			String str_clsName = tableName2ClassName(str_tblNm);
			String str_tblComment;
			String str_moduleName = null;

			/**/ {
//				boolean flag_pass = false;
				// ignore .
				if (map_objectIgnore != null && map_objectIgnore.containsKey(str_clsName)) {
					continue;
				}
				// filter .
//				if (filterType > 0) {
//					switch (filterType) {
//					case 1:
//						if (str_tblName.contains(filterValue)) {
//							flag_pass = true;
//						}
//						break;
//					case 2:
//						if (str_tblName.endsWith(filterValue)) {
//							flag_pass = true;
//						}
//						break;
//					case 3:
//						if (str_tblName.startsWith(filterValue)) {
//							flag_pass = true;
//						}
//						break;
//					case 4:
//						if (str_tblName.equals(filterValue)) {
//							flag_pass = true;
//						}
//						break;
//					default:
//						break;
//					}
//					if (!flag_pass) {
//						continue;
//					}
//				}
			}
			
			
			str_tblComment = rs.getString("table_comment");
//			str_moduleName = getModuleNameFromTableName(str_tblNm);
			
			
			pstmt_tbl.setString(2, str_tblNm);
			rs_tbl = pstmt_tbl.executeQuery();

			rs_tbl.last();
			int i_rowCnt = rs_tbl.getRow();
			rs_tbl.beforeFirst();
			System.out.println("str_tblNm=" + str_tblNm + ", i_rowCnt=" + i_rowCnt);
			List<Struct.Item> list_structItem = new ArrayList<>(i_rowCnt);
			List<Struct.Item> list_structItemPK = new ArrayList<>(i_rowCnt);
			for (; rs_tbl.next(); ) {
				String str_columnName = rs_tbl.getString("column_name");
				String str_upperColumnName = str_columnName.toUpperCase();
				String str_columnTypeName = rs_tbl.getString("data_type");
				String str_columnKey = rs_tbl.getString("column_key");
				String str_extra = rs_tbl.getString("extra");
				String str_columnComment = rs_tbl.getString("column_comment");

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
				boolean b_isAutoIncrement = str_extra != null && str_extra.equals("auto_increment");

				Struct.Item item = new Struct.Item(str_javaType, str_jdbcType, str_name, str_columnName, str_upperColumnName, str_methodName, b_isAutoIncrement, str_columnComment);

//				list_structItem.add(item);

				if (map_columnIgnore.containsKey(str_columnName)) { // 当然， 是可以提到前面点。
					System.err.println("column \"" + str_columnName + "\" is be ignore .");
					
					continue;
				}

				if (str_columnKey != null && str_columnKey.equals("PRI")) {
					list_structItemPK.add(item);
				} else {
					list_structItem.add(item);
				}
			}
			// System.out.println("\r\n");
			String str_varName = toLowerStart(str_clsName);
			map_struct.put(str_tblNm,
					new Struct(
							str_clsName, str_varName, str_tblNm, str_tblComment, str_moduleName, 
							list_structItem, list_structItemPK
							));

			rs_tbl.close();
			rs_tbl = null;

			// break; // for test .
		}

		pstmt_tbl.close();
		pstmt_tbl = null;
		
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
		configuration.setDirectoryForTemplateLoading(new File(/*BASE_OUT_PATH + */"templates"/* + '\\' */));
		configuration.setObjectWrapper(new DefaultObjectWrapper());
		configuration.setDefaultEncoding("UTF-8");

		{
			// 是否移除输出目录
			if (isRemoveFromBasePath) {
				File f = new File(BASE_OUT_PATH);
	//			File[] objArr_file = f.listFiles();
	//			for (int i = 0; i < objArr_file.length; i++) {
	//				if (objArr_file[i].isDirectory()) { // need recursion .
	//					Runtime.getRuntime().exec("cmd.exe /C rmdir /s /q \"" + objArr_file[i].getAbsolutePath() + "\"");
	//				} else if (objArr_file[i].isFile()) {
	//					objArr_file[i].delete();
	//				}
	//			}
				if (f.exists()) {
	//				f.delete();
					Runtime.getRuntime().exec("cmd.exe /C rmdir /s /q \"" + f.getAbsolutePath() + "\"");
					Thread.sleep(200);
				}
			}
			String str_relativePath = POJO_BASE_PACKAGE.replace('.', File.separatorChar) + File.separatorChar;
			_POJOOutPath = BASE_OUT_PATH + str_relativePath;
			makeSurePathExist(_POJOOutPath);
			str_relativePath = DEFAULT_BASE_PACKAGE.replace('.', File.separatorChar) + File.separatorChar;
			_DefaultOutPath = BASE_OUT_PATH + str_relativePath;
			makeSurePathExist(_DefaultOutPath);
			str_relativePath = API_BASE_PACKAGE.replace('.', File.separatorChar) + File.separatorChar;
			_APIOutPath = BASE_OUT_PATH + str_relativePath;
			makeSurePathExist(_APIOutPath);
			str_relativePath = BASE_PACKAGE.replace('.', File.separatorChar) + File.separatorChar;
			outPath = BASE_OUT_PATH + str_relativePath;
			makeSurePathExist(outPath);
		}

		_model(configuration);
		_DTO(configuration);
		_VO(configuration);
		_DefaultDAO(configuration);
		_DefaultDAO_SQL(configuration);
		_DAO(configuration);
		_DAO_SQL(configuration);
		_defaultService(configuration);
		_defaultServiceImpl(configuration);
		_service(configuration);
		_serviceImpl(configuration);
		_controller(configuration);
		// _JSP();

		System.out.println("恭喜，你可以开始了 !~~");
		Runtime.getRuntime().exec("cmd.exe /C start explorer.exe \"" + System.getProperty("user.dir") + File.separatorChar + BASE_OUT_PATH + "\"");
	}

	private static final String getPOJOOutPath(String name) {
		String str_path = _POJOOutPath + name + File.separator;
//		if (MODULE_NAME != null && MODULE_NAME.length() > 0) {
//			str_path += MODULE_NAME + File.separatorChar;
//		}
		if (isUseModuleName) {
			str_path += DB_NAME + File.separatorChar;
		}
		return str_path;
	}

	private static final String getDefaultOutPath(String name) {
		String str_path = _DefaultOutPath + name + File.separator;
//		if (MODULE_NAME != null && MODULE_NAME.length() > 0) {
//			str_path += MODULE_NAME + File.separatorChar;
//		}
		if (isUseModuleName) {
			str_path += DB_NAME + File.separatorChar;
		}
		return str_path;
	}

	private static final String getAPIOutPath(String name) {
		String str_path = _APIOutPath + name + File.separator;
//		if (MODULE_NAME != null && MODULE_NAME.length() > 0) {
//			str_path += MODULE_NAME + File.separatorChar;
//		}
		if (isUseModuleName) {
			str_path += DB_NAME + File.separatorChar;
		}
		return str_path;
	}

	private static final String getDAOOutPath(String name) {
		String str_path = BASE_OUT_PATH + name + File.separator;
//		if (MODULE_NAME != null && MODULE_NAME.length() > 0) {
//			str_path += MODULE_NAME + File.separatorChar;
//		}
		if (isUseModuleName) {
			str_path += DB_NAME + File.separatorChar;
		}
		return str_path;
	}

	private static final String getOutPath(String name) {
		String str_path = outPath + name + File.separator;
//		if (MODULE_NAME != null && MODULE_NAME.length() > 0) {
//			str_path += MODULE_NAME + File.separatorChar;
//		}
		if (isUseModuleName) {
			str_path += DB_NAME + File.separatorChar;
		}
		return str_path;
	}

	private static final void setBase(Map<String, Object> map) {
		map.put("FRAMEWORK_PACKAGE", FRAMEWORK_PACKAGE);
		map.put("POJO_BASE_PKG", POJO_BASE_PACKAGE);
		map.put("DEFAULT_BASE_PKG", DEFAULT_BASE_PACKAGE);
		map.put("API_BASE_PKG", API_BASE_PACKAGE);
		map.put("BASE_PKG", BASE_PACKAGE);
//		if (MODULE_NAME != null && MODULE_NAME.length() > 0) {
//			map.put("module_name", '.' + MODULE_NAME);
//		}
		if (DB_NAME != null && DB_NAME.length() > 0) {
			if (isUseModuleName) {
				map.put("module_name", '.' + DB_NAME);
			} else {
				map.put("module_name", "");
			}
		}
		map.put("DTO", DTO);
		map.put("VO", VO);
		map.put("DAO", DAO);
	}
	
//	@SuppressWarnings("serial")
	private static void _model(Configuration configuration) throws TemplateNotFoundException,
	MalformedTemplateNameException, ParseException, IOException, TemplateException {
		// Entity .
		String str_path = getPOJOOutPath("model");
		makeSurePathExist(str_path);
		Template template = configuration.getTemplate("Model-template.java");

		Map<String, Object> paramMap = new HashMap<String, Object>();
		setBase(paramMap);
		for (String tblName : map_struct.keySet()) {
			Struct stc = map_struct.get(tblName);

//			paramMap.put("name", struct.className);
//			paramMap.put("comment", struct.tableComment);
			paramMap.put("struct", stc);

//			List<Map<String, String>> nameList = new ArrayList<Map<String, String>>();
//			for (int i = 0; i < stc.list_filterItem.size(); i++) {
//				final Struct.Item itm = stc.list_filterItem.get(i);
//
//				nameList.add(new HashMap<String, String>() {
//					{
//						put("javaType", itm.javaType);
//						put("name", itm.name);
//						put("methodName", itm.methodName);
//						put("comment", itm.comment);
//					}
//				});
//			}
//			paramMap.put("properties", nameList);

			Writer writer = new OutputStreamWriter(new FileOutputStream(str_path + stc.className + ".java"), "UTF-8");
			template.process(paramMap, writer);
			writer.flush();
			writer.close();
			writer = null;
		}
	}

//	@SuppressWarnings("serial")
	private static void _DTO(Configuration configuration) throws TemplateNotFoundException,
	MalformedTemplateNameException, ParseException, IOException, TemplateException {
		// DTO .
		String str_path = getPOJOOutPath(DTO);
		makeSurePathExist(str_path);
		Template template = configuration.getTemplate("DTO-template.java");

		Map<String, Object> paramMap = new HashMap<String, Object>();
		setBase(paramMap);
		for (String tblName : map_struct.keySet()) {
			Struct stc = map_struct.get(tblName);

//			paramMap.put("name", stc.className);
			paramMap.put("struct", stc);

//			List<Map<String, String>> nameList = new ArrayList<Map<String, String>>();
//			for (int i = 0; i < stc.list_filterItem.size(); i++) {
//				final Struct.Item itm = stc.list_filterItem.get(i);
//
//				nameList.add(new HashMap<String, String>() {
//					{
//						put("javaType", itm.javaType);
//						put("name", itm.name);
//						put("methodName", itm.methodName);
//					}
//				});
//			}
//			paramMap.put("properties", nameList);

			Writer writer = new OutputStreamWriter(new FileOutputStream(str_path + stc.className + "DTO.java"), "UTF-8");
			template.process(paramMap, writer);
			writer.flush();
			writer.close();
			writer = null;
		}
	}

//	@SuppressWarnings("serial")
	private static void _VO(Configuration configuration) throws TemplateNotFoundException,
	MalformedTemplateNameException, ParseException, IOException, TemplateException {
		// VO .
		String str_path = getPOJOOutPath(VO);
		makeSurePathExist(str_path);
		Template template = configuration.getTemplate("VO-template.java");

		Map<String, Object> paramMap = new HashMap<String, Object>();
		setBase(paramMap);
		for (String tblName : map_struct.keySet()) {
			Struct stc = map_struct.get(tblName);

//			paramMap.put("name", stc.className);
			paramMap.put("struct", stc);

//			List<Map<String, String>> nameList = new ArrayList<Map<String, String>>();
//			for (int i = 0; i < stc.list_filterItem.size(); i++) {
//				final Struct.Item itm = stc.list_filterItem.get(i);
//
//				nameList.add(new HashMap<String, String>() {
//					{
//						put("javaType", itm.javaType);
//						put("name", itm.name);
//						put("methodName", itm.methodName);
//					}
//				});
//			}
//			paramMap.put("properties", nameList);

			Writer writer = new OutputStreamWriter(new FileOutputStream(str_path + stc.className + "VO.java"), "UTF-8");
			template.process(paramMap, writer);
			writer.flush();
			writer.close();
			writer = null;
		}
	}

	private static void _DefaultDAO(Configuration configuration) throws TemplateNotFoundException,
	MalformedTemplateNameException, ParseException, IOException, TemplateException {
		// DAO .
		String str_path = getDefaultOutPath("DAO");
		makeSurePathExist(str_path);
		Template template = configuration.getTemplate("DefaultDAO-template.java");

		Map<String, Object> paramMap = new HashMap<String, Object>();
		setBase(paramMap);
		for (String tblName : map_struct.keySet()) {
			Struct stc = map_struct.get(tblName);

			paramMap.put("struct", stc);

			Writer writer = new OutputStreamWriter(new FileOutputStream(str_path + "IDefault" + stc.className + "DAO.java"), "UTF-8");
			template.process(paramMap, writer);
			writer.flush();
			writer.close();
			writer = null;
		}
	}

//	@SuppressWarnings("serial")
    private static void _DefaultDAO_SQL(Configuration configuration) throws TemplateNotFoundException,
	MalformedTemplateNameException, ParseException, IOException, TemplateException {
		// DAO_XML .
		String str_path = getDAOOutPath("__default_SQL");
		makeSurePathExist(str_path);
		Template template = configuration.getTemplate("DefaultDAO_SQL-template.xml");

		Map<String, Object> paramMap = new HashMap<String, Object>();
		setBase(paramMap);
		for (String tblName : map_struct.keySet()) {
			Struct stc = map_struct.get(tblName);

			{
            paramMap.put("struct", stc);

//            List<Map<String, String>> nameList = new ArrayList<Map<String, String>>();
//            for (int i = 0; i < stc.list_filterItem.size(); i++) {
//                final Struct.Item itm = stc.list_filterItem.get(i);
//
//                nameList.add(new HashMap<String, String>() {
//                    {
//                        put("javaType", itm.javaType);
//                        put("name", itm.name);
//                        put("jdbcType", itm.jdbcType);
//                        put("columnName", itm.columnName);
//                    }
//                });
//            }
//            paramMap.put("properties", nameList);
			}

			Writer writer = new OutputStreamWriter(new FileOutputStream(str_path + "IDefault" + stc.className + "DAO.xml"), "UTF-8");
			template.process(paramMap, writer);
			writer.flush();
			writer.close();
			writer = null;
		}
	}

	private static void _DAO(Configuration configuration) throws TemplateNotFoundException,
	MalformedTemplateNameException, ParseException, IOException, TemplateException {
		// DAO .
		String str_path = getOutPath(DAO);
		makeSurePathExist(str_path);
		Template template = configuration.getTemplate("DAO-template.java");

		Map<String, Object> paramMap = new HashMap<String, Object>();
		setBase(paramMap);
		for (String tblName : map_struct.keySet()) {
			Struct stc = map_struct.get(tblName);

			paramMap.put("struct", stc);

			Writer writer = new OutputStreamWriter(new FileOutputStream(str_path + "I" + stc.className + "DAO.java"), "UTF-8");
			template.process(paramMap, writer);
			writer.flush();
			writer.close();
			writer = null;
		}
	}

//	@SuppressWarnings("serial")
    private static void _DAO_SQL(Configuration configuration) throws TemplateNotFoundException,
	MalformedTemplateNameException, ParseException, IOException, TemplateException {
		// DAO_SQL .
		String str_path = getDAOOutPath("SQL");
		makeSurePathExist(str_path);
		Template template = configuration.getTemplate("DAO_SQL-template.xml");

		Map<String, Object> paramMap = new HashMap<String, Object>();
		setBase(paramMap);
		for (String tblName : map_struct.keySet()) {
			Struct stc = map_struct.get(tblName);

			{
            paramMap.put("struct", stc);

//            List<Map<String, String>> nameList = new ArrayList<Map<String, String>>();
//            for (int i = 0; i < stc.list_filterItem.size(); i++) {
//                final Struct.Item itm = stc.list_filterItem.get(i);
//
//                nameList.add(new HashMap<String, String>() {
//                    {
//                        put("javaType", itm.javaType);
//                        put("name", itm.name);
//                        put("jdbcType", itm.jdbcType);
//                        put("columnName", itm.columnName);
//                    }
//                });
//            }
//            paramMap.put("properties", nameList);
			}

			Writer writer = new OutputStreamWriter(new FileOutputStream(str_path + "I" + stc.className + "DAO.xml"), "UTF-8");
			template.process(paramMap, writer);
			writer.flush();
			writer.close();
			writer = null;
		}
	}

	private static void _defaultService(Configuration configuration) throws TemplateNotFoundException,
	MalformedTemplateNameException, ParseException, IOException, TemplateException {
		// Service .
		String str_path = getDefaultOutPath("service");
		makeSurePathExist(str_path);
		Template template = configuration.getTemplate("DefaultService-template.java");

		Map<String, Object> paramMap = new HashMap<String, Object>();
		setBase(paramMap);
		for (String tblName : map_struct.keySet()) {
			Struct stc = map_struct.get(tblName);

			paramMap.put("struct", stc);

			Writer writer = new OutputStreamWriter(new FileOutputStream(str_path + "IDefault" + stc.className + "Service.java"), "UTF-8");
			template.process(paramMap, writer);
			writer.flush();
			writer.close();
			writer = null;
		}
	}

	private static void _defaultServiceImpl(Configuration configuration) throws TemplateNotFoundException,
	MalformedTemplateNameException, ParseException, IOException, TemplateException {
		// ServiceImpl .
		String str_path = getDefaultOutPath("service" + File.separator + "impl");
		makeSurePathExist(str_path);
		Template template = configuration.getTemplate("DefaultServiceImpl-template.java");

		Map<String, Object> paramMap = new HashMap<String, Object>();
		setBase(paramMap);
		for (String tblName : map_struct.keySet()) {
			Struct stc = map_struct.get(tblName);

			paramMap.put("struct", stc);

			Writer writer = new OutputStreamWriter(new FileOutputStream(str_path + "Default" + stc.className + "ServiceImpl.java"), "UTF-8");
			template.process(paramMap, writer);
			writer.flush();
			writer.close();
			writer = null;
		}
	}

	private static void _service(Configuration configuration) throws TemplateNotFoundException,
	MalformedTemplateNameException, ParseException, IOException, TemplateException {
		// Service .
		String str_path = getAPIOutPath("service");
		makeSurePathExist(str_path);
		Template template = configuration.getTemplate("Service-template.java");

		Map<String, Object> paramMap = new HashMap<String, Object>();
		setBase(paramMap);
		for (String tblName : map_struct.keySet()) {
			Struct stc = map_struct.get(tblName);

			paramMap.put("struct", stc);

			Writer writer = new OutputStreamWriter(new FileOutputStream(str_path + "I" + stc.className + "Service.java"), "UTF-8");
			template.process(paramMap, writer);
			writer.flush();
			writer.close();
			writer = null;
		}
	}

	private static void _serviceImpl(Configuration configuration) throws TemplateNotFoundException,
	MalformedTemplateNameException, ParseException, IOException, TemplateException {
		// ServiceImpl .
		String str_path = getOutPath("service\\impl");
		makeSurePathExist(str_path);
		Template template = configuration.getTemplate("ServiceImpl-template.java");

		Map<String, Object> paramMap = new HashMap<String, Object>();
		setBase(paramMap);
		for (String tblName : map_struct.keySet()) {
			Struct stc = map_struct.get(tblName);

			paramMap.put("struct", stc);

			Writer writer = new OutputStreamWriter(new FileOutputStream(str_path + stc.className + "ServiceImpl.java"), "UTF-8");
			template.process(paramMap, writer);
			writer.flush();
			writer.close();
			writer = null;
		}
	}

	private static void _controller(Configuration configuration) throws TemplateNotFoundException,
	MalformedTemplateNameException, ParseException, IOException, TemplateException {
		// Controller .
		String str_path = getOutPath("controller");
		makeSurePathExist(str_path);
		Template template = configuration.getTemplate("Controller-template.java");

		Map<String, Object> paramMap = new HashMap<String, Object>();
		setBase(paramMap);
		for (String tblName : map_struct.keySet()) {
			Struct stc = map_struct.get(tblName);

			paramMap.put("struct", stc);

			Writer writer = new OutputStreamWriter(new FileOutputStream(str_path + stc.className + "Controller.java"), "UTF-8");
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
