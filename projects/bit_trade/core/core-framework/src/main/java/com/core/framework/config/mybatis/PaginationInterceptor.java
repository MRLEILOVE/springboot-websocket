/*
 * Copyright (c) 2011-2020, baomidou (jobob@qq.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * https://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.core.framework.config.mybatis;

import static java.util.stream.Collectors.joining;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.StatementType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.MybatisDefaultParameterHandler;
import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.core.parser.SqlInfo;
import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.baomidou.mybatisplus.core.toolkit.ExceptionUtils;
import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.handlers.AbstractSqlParserHandler;
import com.baomidou.mybatisplus.extension.plugins.pagination.DialectModel;
import com.baomidou.mybatisplus.extension.toolkit.JdbcUtils;
import com.baomidou.mybatisplus.extension.toolkit.SqlParserUtils;
import com.core.common.DTO.PageDTO;
import com.core.common.constant.IConstant;
import com.core.tool.MathematicsUtil;

import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 分页拦截器
 * 
 * <pre>
 *   copy from com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor .
 *   Of course, you can inherit it, whatever you want to play.
 * </pre>
 *
 * @author
 * @since
 */
@Setter
@Accessors(chain = true)
@Intercepts({
		@Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class, Integer.class }) })
public class PaginationInterceptor extends AbstractSqlParserHandler implements Interceptor {

	/**
	 * COUNT SQL 解析
	 */
	private ISqlParser countSqlParser;
	/**
	 * 溢出总行数 或 总页数，设置为总行数、总页数。
	 */
	private boolean overflow = true; // false
	/**
	 * 单页限制 500 条，小于或等于 0 如 -1 不受限制
	 */
	private int limit = 500; // L
	/**
	 * 方言类型
	 */
	private String dialectType;
	/**
	 * 方言实现类
	 */
	private String dialectClazz;
	/**
	 * 方法名后缀。
	 */
	private String methodSuffix;

	private PageDTO<?> getPageDTO(Object paramObj) {
		PageDTO<?> pageDTO = null;
		
		if (paramObj instanceof PageDTO) {
			pageDTO = (PageDTO<?>) paramObj;
		} else if (paramObj instanceof Map) {
			for (Object arg : ((Map<?, ?>) paramObj).values()) {
				if (arg instanceof PageDTO) {
					pageDTO = (PageDTO<?>) arg;
					break;
				}
			}
		}
		
		return pageDTO;
	}

	/**
	 * 拼接多个排序方法
	 *
	 * @param columns   ignore
	 * @param orderWord ignore
	 */
	private static String concatOrderBuilder(String[] columns, String orderWord) {
		if (ArrayUtils.isNotEmpty(columns)) {
			return Arrays.stream(columns).filter(StringUtils::isNotEmpty).map(i -> i + orderWord)
					.collect(joining(StringPool.COMMA));
		}
		return StringUtils.EMPTY;
	}

	/**
	 * 查询SQL拼接Order By
	 *
	 * @param originalSql 需要拼接的SQL
	 * @param page        pageDTO对象
	 * @param orderBy     是否需要拼接Order By
	 * @return ignore
	 */
	private static String concatOrderBy(String originalSql, PageDTO<?> pageDTO, boolean orderBy) {
//		if (orderBy && (ArrayUtils.isNotEmpty(pageDTO.ascs()) || ArrayUtils.isNotEmpty(pageDTO.descs()))) {
//			StringBuilder buildSql = new StringBuilder(originalSql);
//			String ascStr = concatOrderBuilder(pageDTO.ascs(), " ASC");
//			String descStr = concatOrderBuilder(pageDTO.descs(), " DESC");
//			if (StringUtils.isNotEmpty(ascStr) && StringUtils.isNotEmpty(descStr)) {
//				ascStr += ", ";
//			}
//			if (StringUtils.isNotEmpty(ascStr) || StringUtils.isNotEmpty(descStr)) {
//				buildSql.append(" ORDER BY ").append(ascStr).append(descStr);
//			}
//			return buildSql.toString();
//		}
		return originalSql;
	}

	/**
	 * 查询总记录条数 和 算总页数
	 *
	 * @param sql             count sql
	 * @param mappedStatement MappedStatement
	 * @param boundSql        BoundSql
	 * @param page            PageDTO
	 * @param connection      Connection
	 */
	private void queryTotalSize(String sql, MappedStatement mappedStatement, BoundSql boundSql,
			PageDTO<?> pageDTO, Connection connection) {
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			DefaultParameterHandler parameterHandler = new MybatisDefaultParameterHandler(mappedStatement, boundSql.getParameterObject(), boundSql);
			parameterHandler.setParameters(statement);
			int totalSize = 0;
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					totalSize = resultSet.getInt(1);
				}
			}
			pageDTO.setTotalSize(totalSize);
			/*
			 * 溢出总行数，设置为总行数
			 */
			if (overflow && pageDTO.getSize() > totalSize) {
				// 设置为总行数
				pageDTO.setSize(totalSize);
			}
			/*
			 * 溢出总页数，设置为总页数
			 */
			int totalPage = MathematicsUtil.getTotalPage(pageDTO.getSize(), totalSize);
			pageDTO.setTotalPage(totalPage);
			if (overflow && pageDTO.getPage() > totalPage) {
				// 设置为总页数
				pageDTO.setPage(totalPage);
			}
		} catch (Exception e) {
			throw ExceptionUtils.mpe("Error: Method queryTotalSize execution error of sql : \n %s \n", e, sql);
		}
	}

	/**
	 * Physical Page Interceptor for all the queries with parameter
	 * {@link RowBounds}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		StatementHandler statementHandler = PluginUtils.realTarget(invocation.getTarget());
		MetaObject metaObject = SystemMetaObject.forObject(statementHandler);

		// SQL 解析
		this.sqlParser(metaObject);

		MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
		// 拦截以.ByPage结尾的请求，分页功能的统一实现
//		if (mappedStatement.getId().matches( ".+" + methodSuffix + "$" )) { // ByPage
//			org.apache.ibatis.executor.parameter.ParameterHandler parameterHandler = 
//					(org.apache.ibatis.executor.parameter.ParameterHandler) metaObject.getValue( "delegate.parameterHandler" );
//			Object obj_parameterObject;
//			obj_parameterObject = parameterHandler.getParameterObject();
//			System.out.println( "obj_parameterObject=" + obj_parameterObject );
//			obj_parameterObject = statementHandler.getBoundSql().getParameterObject();
//			System.out.println( "obj_parameterObject=" + obj_parameterObject );
//		}
		// 先判断是不是SELECT操作 (2019-04-10 00:37:31 跳过存储过程)
		if (SqlCommandType.SELECT != mappedStatement.getSqlCommandType()
				|| StatementType.CALLABLE == mappedStatement.getStatementType()) {
			return invocation.proceed();
		}

		// 针对定义了rowBounds，做为mapper接口方法的参数
		BoundSql boundSql = (BoundSql) metaObject.getValue("delegate.boundSql");
		Object paramObj = boundSql.getParameterObject();
		if (paramObj == null) {
			return invocation.proceed();
		}

		// 判断参数里是否有page对象
		PageDTO<?> pageDTO = getPageDTO(paramObj);

		/*
		 * 不需要分页的场合
		 */
		if (null == pageDTO) {
			return invocation.proceed();
		}

		if (pageDTO.getPage() <= 0) {
			pageDTO.setPage(IConstant.PAGE_INDEX);
		}
		if (pageDTO.getSize() <= 0) {
			pageDTO.setSize(IConstant.PAGE_SIZE);
		}
		/*
		 * 处理单页条数限制
		 */
		if (limit > 0 && limit < pageDTO.getSize()) {
			pageDTO.setSize(limit);
		}

		String originalSql = boundSql.getSql();
		Connection connection = (Connection) invocation.getArgs()[0];
		DbType dbType = StringUtils.isNotEmpty(dialectType) ? DbType.getDbType(dialectType)
				: JdbcUtils.getDbType(connection.getMetaData().getURL());

		boolean orderBy = true;
//		if (page.isSearchCount()) {
			SqlInfo sqlInfo = SqlParserUtils.getOptimizeCountSql(true, countSqlParser, originalSql);
			orderBy = sqlInfo.isOrderBy();
			this.queryTotalSize(sqlInfo.getSql(), mappedStatement, boundSql, pageDTO, connection);
			if (pageDTO.getTotalSize() <= 0) {
				return null;
			}
//		}

		String buildSql = concatOrderBy(originalSql, pageDTO, orderBy);
		DialectModel model = DialectFactory.buildPaginationSql(pageDTO, buildSql, dbType, dialectClazz);
		Configuration configuration = mappedStatement.getConfiguration();
		List<ParameterMapping> mappings = new ArrayList<>(boundSql.getParameterMappings());
		Map<String, Object> additionalParameters = (Map<String, Object>) metaObject.getValue("delegate.boundSql.additionalParameters");
		model.consumers(mappings, configuration, additionalParameters);
		metaObject.setValue("delegate.boundSql.sql", model.getDialectSql());
		metaObject.setValue("delegate.boundSql.parameterMappings", mappings);
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		if (target instanceof StatementHandler) {
			return Plugin.wrap(target, this);
		}
		return target;
	}

	@Override
	public void setProperties(Properties prop) {
		String dialectType = prop.getProperty("dialectType");
		String dialectClazz = prop.getProperty("dialectClazz");
		String methodSuffix = prop.getProperty("methodSuffix");
		if (StringUtils.isNotEmpty(dialectType)) {
			this.dialectType = dialectType;
		}
		if (StringUtils.isNotEmpty(dialectClazz)) {
			this.dialectClazz = dialectClazz;
		}
		if (StringUtils.isNotEmpty(methodSuffix)) {
			this.methodSuffix = methodSuffix;
		}
	}

}
