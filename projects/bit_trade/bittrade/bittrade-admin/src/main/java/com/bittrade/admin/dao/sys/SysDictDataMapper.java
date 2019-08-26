package com.bittrade.admin.dao.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bittrade.admin.model.domain.SysDictData;

/**
 * <p>
 * 字典数据表 Mapper 接口
 * </p>
 *
 * @author ourblue
 * @since 2018-11-09
 */
public interface SysDictDataMapper extends BaseMapper<SysDictData> {

	/**
	 * 根据条件分页查询字典数据
	 * 
	 * @param dictData
	 * @return 字典数据集合信息
	 */
	public List<SysDictData> selectDictDataList(Page<SysDictData> page, @Param("dictData") SysDictData dictData);

	/**
	 * 根据字典类型查询字典数据
	 * 
	 * @param dictType
	 * @return 字典数据集合信息
	 */
	public List<SysDictData> selectDictDataByType(String dictType);

	/**
	 * 根据字典类型和字典键值查询字典数据信息
	 * 
	 * @param dictType
	 * @param dictValue
	 * @return 字典标签
	 */
	public String selectDictLabel(@Param("dictType") String dictType, @Param("dictValue") String dictValue);

	/**
	 * 根据字典数据ID查询信息
	 * 
	 * @param dictCode
	 * @return 字典数据
	 */
	public SysDictData selectDictDataById(Integer dictCode);

	/**
	 * 查询字典数据
	 * 
	 * @param dictType
	 * @return 字典数据
	 */
	public int countDictDataByType(String dictType);

	/**
	 * 通过字典ID删除字典数据信息
	 * 
	 * @param dictCode
	 * @return 结果
	 */
	public int deleteDictDataById(Integer dictCode);

	/**
	 * 批量删除字典数据
	 * 
	 * @param ids
	 * @return 结果
	 */
	public int deleteDictDataByIds(String[] ids);

	/**
	 * 新增字典数据信息
	 * 
	 * @param dictData
	 * @return 结果
	 */
	public int insertDictData(SysDictData dictData);

	/**
	 * 修改字典数据信息
	 * 
	 * @param dictData
	 * @return 结果
	 */
	public int updateDictData(SysDictData dictData);

	/**
	 * 同步修改字典类型
	 * 
	 * @param oldDictType
	 * @param newDictType
	 * @return 结果
	 */
	public int updateDictDataType(@Param("oldDictType") String oldDictType, @Param("newDictType") String newDictType);

}
