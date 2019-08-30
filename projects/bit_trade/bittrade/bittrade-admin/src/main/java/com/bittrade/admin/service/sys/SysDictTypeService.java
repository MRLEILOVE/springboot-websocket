package com.bittrade.admin.service.sys;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bittrade.admin.model.domain.SysDictType;

/**
 * <p>
 * 字典类型表 服务类
 * </p>
 *
 * @author who ?
 * @since 2018-11-09
 */
public interface SysDictTypeService extends IService<SysDictType> {
	
	/**
	 * 根据条件分页查询字典类型
	 * 
	 * @param dictType
	 * @return 字典类型集合信息
	 */
	public Page<SysDictType> selectDictTypeList(SysDictType dictType, Page<SysDictType> page);

	/**
	 * 根据所有字典类型
	 * 
	 * @return 字典类型集合信息
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
	 * @throws Exception
	 */
	public int deleteDictTypeByIds(String ids) throws Exception;

	/**
	 * 新增保存字典类型信息
	 * 
	 * @param dictType
	 * @return 结果
	 */
	public int insertDictType(SysDictType dictType);

	/**
	 * 修改保存字典类型信息
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
	public String checkDictTypeUnique(SysDictType dictType);
}
