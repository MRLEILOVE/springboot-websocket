package com.bittrade.admin.dao.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bittrade.admin.model.domain.SysDictType;

/**
 * <p>
 * 字典类型表 Mapper 接口
 * </p>
 *
 * @author ourblue
 * @since 2018-11-09
 */
public interface ISysDictTypeDAO extends BaseMapper<SysDictType> {

	/**
	 * 根据条件分页查询字典类型
	 * 
	 * @param dictType
	 * @return 字典类型集合信息
	 */
	public List<SysDictType> selectDictTypeList(Page<SysDictType> page , @Param("dictType") SysDictType dictType);
	
	/**
	 * 根据所有字典类型
	 * 
	 */
	public List<SysDictType> selectDictTypeAll();

	/**
	 * 根据字典类型ID查询信息
	 * 
	 * @param dictId
	 * @return 字典类型
	 */
	public SysDictType selectDictTypeById(Integer dictId);

	/**
	 * 通过字典ID删除字典信息
	 * 
	 * @param dictId
	 * @return 结果
	 */
	public int deleteDictTypeById(Integer dictId);

	/**
	 * 批量删除字典类型
	 * 
	 * @param ids
	 * @return 结果
	 */
	public int deleteDictTypeByIds(Integer[] ids);

	/**
	 * 新增字典类型信息
	 * 
	 * @param dictType
	 * @return 结果
	 */
	public int insertDictType(SysDictType dictType);

	/**
	 * 修改字典类型信息
	 * 
	 * @param dictType
	 * @return 结果
	 */
	public int updateDictType(SysDictType dictType);

	/**
	 * 校验字典类型称是否唯一
	 * 
	 * @param dictType
	 * @return 结果
	 */
	public SysDictType checkDictTypeUnique(String dictType);

}