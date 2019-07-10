package com;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import lombok.AllArgsConstructor;
import lombok.Data;

public class Generate_Mapper {
	
	private static final String PKG_NM = "com.corn.paycore.api.model.";
	private static final String DAO_PKG = "com.corn.adminweb.server.mapper.";
	private static final String DTO_PKG = "com.corn.paycore.api.DTO.";
	
	private static final String SELECT_SPLIT = ", ";
	private static final String WHERE_SPLIT = "AND ";
	
	private static final ClassLoader CL = Generate_Mapper.class.getClassLoader();
	private static final Map<String, Boolean> map_cls = new HashMap<>();
	private static final Map<String, List<Method>> map_objMth_JoinColumn = new HashMap<>();
	
	/**
	 * 
	 * 类 PropertyType 的实现描述：TODO 类实现描述 
	 * @author Administrator Mar 21, 2018 5:42:13 PM
	 */
	private static /*final */enum PropertyType {
		ID(1, "ID"), COLUMN(2, "COLUMN");
		
		private int type;
		private String desc;
		private PropertyType(int type, String desc) {
			this.type = type;
			this.desc = desc;
		}
	}
	
	/**
	 * 
	 * 类 JoinType 的实现描述：TODO 类实现描述 
	 * @author Administrator Mar 21, 2018 6:35:53 PM
	 */
	private static /*final */enum JoinType {
		/**
		 * association
		 */
		MANY_TO_ONE(1, "ManyToOne"), 
		/**
		 * collection
		 */
		ONE_TO_MANY(2, "OneToMany");
		
		private int type;
		private String desc;
		private JoinType(int type, String desc) {
			this.type = type;
			this.desc = desc;
		}
	}
	
	@Data
	@AllArgsConstructor
	private static final class StructItem {
		private PropertyType propertyType;
		private String propertyName;
		private String columnName;
	}
	
	@Data
	@AllArgsConstructor
	private static final class Struct {
		private Struct parent;
		private JoinType joinType;
		private String className;
		private String simpleClassName;
		private String propertyName;
		private String tableName;
		private String _PKName;
		private String joinColumnName;
		private List<StructItem> list_column;
		private List<Struct> list_class;
//		public void setParent(Struct parent) {
//			this.parent = parent;
//		}
		private void appendChild(Struct child) {
			this.getList_class().add(child);
			child.setParent(this);
		}
//		@Override
		public String toString() {
			return ReflectionToStringBuilder.toString(this);
		}
	}
	
	/**
	 * 
	 * 类 SQL 的实现描述：TODO 类实现描述 
	 * @author Administrator Mar 21, 2018 8:32:15 PM
	 */
	@Data
	private static final class SQL {
		private StringBuilder select;
		private StringBuilder from;
		private StringBuilder where;
		private String getSQL() {
			return select.append(from).append(where == null ? "" : where).toString();
		}
	}
	
	/**
	 * ----------------------------------------------------------------------------------------
	 * ----------------------------------------------------------------------------------------
	 * ----------------------------------------------------------------------------------------
	 */
	
	private Class<?> getClass(String name) {
		Class<?> cls = null;
		try {
			cls = CL.loadClass(name);
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException name=" + name);
			e.printStackTrace();
		}
		return cls;
	}
	
	private String getPropertyName(String methodName) {
		String str_methodName = methodName.replace("get", "");
		char c = str_methodName.charAt(0);
		return (char) (c + 32) + str_methodName.substring(1);
	}
	
	private String getGenericType(Method mth) {
		Type type = mth.getGenericReturnType();
		String str_type = type.getTypeName();
		return str_type.substring(str_type.indexOf('<') + 1, str_type.lastIndexOf('>'));
	}
	
	private void add2Struct(PropertyType propertyType, Method mth, Struct stc) {
		String str_mthNm = mth.getName();
		String str_propNm = getPropertyName(str_mthNm);
		Column clm = mth.getDeclaredAnnotation(Column.class);
		String str_clmNm = clm.name();
		
		StructItem stcItem = new StructItem(propertyType, str_propNm, str_clmNm);
		stc.getList_column().add(stcItem);
	}
	
	private String getUniqueColumnNameAtColumn(Struct s, StructItem si) {
		return s.tableName + '_' + si.columnName;
	}
	
	private String getUniqueColumnNameAtSQL(Struct s, StructItem si) {
		return s.tableName + '.' + si.columnName + ' ' + s.tableName + '_' + si.columnName;
	}
	
	private String findJoinColumnNameAtMany(String className, String returnTypeName) {
		String str_joinColumnName = null;
		
		if (map_objMth_JoinColumn.containsKey(className)) {
			List<Method> list_method = map_objMth_JoinColumn.get(className);
			for (int i = 0; i < list_method.size(); i++) {
				Method m = list_method.get(i);
				
				Class<?> cls = m.getReturnType();
				if (cls.getName().equals(returnTypeName)) {
					str_joinColumnName = m.getDeclaredAnnotation(JoinColumn.class).name();
					
					break;
				}
			}
		}
		
		return str_joinColumnName;
	}
	
	/**
	 * ----------------------------------------------------------------------------------------
	 * ----------------------------------------------------------------------------------------
	 * ----------------------------------------------------------------------------------------
	 */
	
	private Struct recycle(String clsNm) {
		Struct stc = null;
		
		// no recycle .
		if (map_cls.containsKey(clsNm)) {
			return null;
		}
		map_cls.put(clsNm, true);
		System.out.println("clsNm=" + clsNm);
		
		
		Class<?> cls = getClass(clsNm);
		
		Method mthArr[] = cls.getDeclaredMethods();
		if (mthArr != null && mthArr.length > 0) {
			map_objMth_JoinColumn.put(clsNm, new ArrayList</*Method*/>()); // buffer cache .
			
			String str_simpleName = cls.getSimpleName(), str_tblNm;
			if (cls.isAnnotationPresent(Table.class)) {
				str_tblNm = cls.getDeclaredAnnotation(Table.class).name();
			} else {
				str_tblNm = str_simpleName;
			}
			stc = new Struct(null, null, clsNm, str_simpleName, null, str_tblNm, null, null, new ArrayList<StructItem>(), new ArrayList<Struct>());
			
			for (int i = 0; i < mthArr.length; i++) {
				Method mth = mthArr[i];
				
				/*  */ if (mth.isAnnotationPresent(Id.class) && mth.isAnnotationPresent(Column.class)) {								// id
					add2Struct(PropertyType.ID, mth, stc);
					
					// set PK name .
					Column clm_ID = mth.getDeclaredAnnotation(Column.class);
					stc.set_PKName(clm_ID.name());
				} else if (mth.isAnnotationPresent(Column.class)) {																						// result
					add2Struct(PropertyType.COLUMN, mth, stc);
				} else if (mth.isAnnotationPresent(ManyToOne.class) && mth.isAnnotationPresent(JoinColumn.class)) {		// association
					map_objMth_JoinColumn.get(clsNm).add(mth); // buffer cache .
					System.out.println("================ @ManyToOne | @JoinColumn =" + cls.getName() + ',' + ' ' + mth.getName());
					
					Class<?> cls_rtnType = mth.getReturnType();
					String str_clsRtn = cls_rtnType.getName();
					
					if (str_clsRtn.startsWith(PKG_NM)) {
						Struct _stc = recycle(str_clsRtn);
						if (_stc != null) {
							String str_propName = getPropertyName(mth.getName());
							JoinColumn joinColumn = mth.getDeclaredAnnotation(JoinColumn.class);
							String str_joinColumnName = joinColumn.name();
							
							_stc.setJoinType(JoinType.MANY_TO_ONE);
							_stc.setPropertyName(str_propName);
							_stc.setJoinColumnName(str_joinColumnName);
							
							stc.appendChild(_stc);
						}
					}
				} else if (mth.isAnnotationPresent(OneToMany.class)) {																				// collection
					System.out.println("================ @OneToMany =" + cls.getName() + ',' + ' ' + mth.getName());
					
					String str_genericType = getGenericType(mth);
					
					if (str_genericType.startsWith(PKG_NM)) {
						Struct _stc = recycle(str_genericType);
						if (_stc != null) {
							String str_propName = getPropertyName(mth.getName());
							String str_joinColumnName = findJoinColumnNameAtMany(str_genericType, clsNm); 
							
							_stc.setJoinType(JoinType.ONE_TO_MANY);
							_stc.setPropertyName(str_propName);
							_stc.setJoinColumnName(str_joinColumnName);
							
							stc.appendChild(_stc);
						}
					}
				}
			} // end for .
			
		} // end if .
		
		return stc;
	}
	
	private void print(Struct stc) {
		System.out.println(stc);
	}
	
	/**
	 * ----------------------------------------------------------------------------------------
	 * ----------------------------------------------------------------------------------------
	 * ----------------------------------------------------------------------------------------
	 */
	
	private void writeXML_resultMap_recycle(Struct stc, Element resultMap) {
		List<StructItem> list_column = stc.getList_column();
		if (list_column != null && list_column.size() > 0) {
			for (int i = 0; i < list_column.size(); i++) {
				StructItem si = list_column.get(i);
				
				Element ele = 
						si.propertyType == PropertyType.ID ? resultMap.addElement("id") : 
							si.propertyType == PropertyType.COLUMN ? resultMap.addElement("result") : null
									;
				ele.addAttribute("property", si.propertyName);
				ele.addAttribute("column", getUniqueColumnNameAtColumn(stc, si));
			}
		}
		
		// relation .
		List<Struct> list_class = stc.getList_class();
		if (list_class != null && list_class.size() > 0) {
			for (int i = 0; i < list_class.size(); i++) {
				Struct s = list_class.get(i);
				
				if (s.joinType == JoinType.MANY_TO_ONE) {		// association
					Element ele_association = resultMap.addElement("association");
					ele_association.addAttribute("property", s.propertyName);
					ele_association.addAttribute("javaType", DTO_PKG + s.simpleClassName + "DTO");
					
					writeXML_resultMap_recycle(s, ele_association);
				}
				if (s.joinType == JoinType.ONE_TO_MANY) {		// collection
					Element ele_association = resultMap.addElement("collection");
					ele_association.addAttribute("property", s.propertyName);
					ele_association.addAttribute("javaType", DTO_PKG + s.simpleClassName + "DTO");
					
					writeXML_resultMap_recycle(s, ele_association);
				}
			}
		}
	}
	
	private void getSQL_SELECT(Struct stc, SQL _SQL) {
		List<StructItem> list_column = stc.getList_column();
		if (list_column != null && list_column.size() > 0) {
			for (int i = 0; i < list_column.size(); i++) {
				StructItem si = list_column.get(i);
				
				String str_columnName = getUniqueColumnNameAtSQL(stc,  si);
				
				// select .
				_SQL.getSelect().append(str_columnName).append(SELECT_SPLIT);
			}
			// from .
			if (stc.getParent() == null) {
				_SQL.getFrom().append(stc.tableName).append(' ');
			} else {
				Struct stc_parent = stc.getParent();
				
				_SQL.getFrom().
					append("LEFT OUTER JOIN ").append(stc.tableName).
					append(" ON ")
					;
				if (stc.joinType == JoinType.MANY_TO_ONE) {
					_SQL.getFrom().
						append(stc_parent.tableName).append('.').append(stc.joinColumnName).append('=').append(stc.tableName).append('.').append(stc.get_PKName()).append(' ')
						;
				} else if (stc.joinType == JoinType.ONE_TO_MANY) {
					_SQL.getFrom().
						append(stc_parent.tableName).append('.').append(stc_parent.get_PKName()).append('=').append(stc.tableName).append('.').append(stc.joinColumnName).append(' ')
						;
				}
			}
		}
		
		List<Struct> list_class = stc.getList_class();
		if (list_class != null && list_class.size() > 0) {
			for (int i = 0; i < list_class.size(); i++) {
				Struct s = list_class.get(i);
				
				getSQL_SELECT(s, _SQL);
			}
		}
	}
	
	private void writeXML(Struct stc) {
		Document doc = DocumentHelper.createDocument();
		doc.addDocType("mapper", "-//mybatis.org//DTD Mapper 3.0//EN", "http://mybatis.org/dtd/mybatis-3-mapper.dtd");
		
		Element root = DocumentHelper.createElement("mapper");
		root.addAttribute("namespace", DAO_PKG + 'I' + stc.simpleClassName + "DAO");
		
		String str_resultMap_ID = "resultMap_" + stc.tableName;
		
		// resultMap begin .
		Element ele_resultMap = DocumentHelper.createElement("resultMap");
		ele_resultMap.addAttribute("id", str_resultMap_ID);
		ele_resultMap.addAttribute("type", DTO_PKG + stc.simpleClassName + "DTO");
		root.add(ele_resultMap);
		writeXML_resultMap_recycle(stc, ele_resultMap);
		// resultMap end .
		
		// select begin .
		Element ele_select_getsWithPage = DocumentHelper.createElement("select");
		ele_select_getsWithPage.addAttribute("id", "getsWithPage");
		ele_select_getsWithPage.addAttribute("resultMap", str_resultMap_ID);
		root.add(ele_select_getsWithPage);
		SQL _SQL = new SQL();
		_SQL.setSelect(new StringBuilder().append("SELECT "));
		_SQL.setFrom(new StringBuilder().append("FROM "));
//		_SQL.setWhere(new StringBuilder().append("WHERE "));
		getSQL_SELECT(stc, _SQL);
		_SQL.getSelect().delete(_SQL.getSelect().length() - SELECT_SPLIT.length(), _SQL.getSelect().length()).append(' ');
//		_SQL.getWhere().delete(_SQL.getWhere().length() - WHERE_SPLIT.length(), _SQL.getWhere().length());
		ele_select_getsWithPage.addCDATA(_SQL.getSQL());
		// select end .
		
		doc.add(root);
		
		OutputStream outputStream = null;
		XMLWriter xmlWriter = null;
		try {
			outputStream = new FileOutputStream(String.format("out\\I%sDAO.xml", stc.simpleClassName));
//			DocumentType dt = new DOMDocumentType();
			OutputFormat outputFormat = OutputFormat.createPrettyPrint();
			outputFormat.setEncoding("UTF-8");
			
			xmlWriter = new XMLWriter(outputStream, outputFormat);
			xmlWriter.write(doc);
			
			xmlWriter.flush();
			outputStream.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (xmlWriter != null) {
					xmlWriter.close();
					xmlWriter = null;
				}
				if (outputStream != null) {
					outputStream.close();
					outputStream = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void loadClass() {
		File dir = new File("src/main/java/com/corn/paycore/api/model");
		File fArr[] = dir.listFiles();
		for (int i = 0; i < fArr.length; i++) {
			File f = fArr[i];
			
			if (f.exists() && f.canRead() && f.isFile()) {
				String str_name = f.getName().replace(".java", "");
				
				map_cls.clear();
				Struct stc = recycle(PKG_NM + str_name); // 1
				writeXML(stc); // 2
			}
		}
		
//		map_cls.clear();
//		Struct stc = recycle("com.corn.paycore.api.model.Admin"); // 1
////		print(stc);
//		writeXML(stc); // 2
	}
	
	public static void main(String[] args) {
		new Generate_Mapper().loadClass();
		
//		System.out.println(new Generate_Mapper().getPropertyName("getAbc"));
	}

}
