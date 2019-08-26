package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.base.enums.DictDataEnum;
import com.jdcloud.base.enums.DictTypeEnum;
import com.jdcloud.provider.pojo.SysDictData;

import java.util.List;

/**
 * <p>
 * 字典数据表 服务类
 * </p>
 *
 * @author ourblue
 * @since 2018-11-09
 */
public interface SysDictDataService extends IService<SysDictData> {
	/**
	 * 根据条件查询字典数据
	 * 
	 * @param dictData
	 * @return 字典数据集合信息
	 */
	public Page<SysDictData> selectDictDataList(SysDictData dictData, Page<SysDictData> page);

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
	public String selectDictLabel(String dictType, String dictValue);

	/**
	 * 根据字典数据ID查询信息
	 * 
	 * @param dictCode
	 * @return 字典数据
	 */
	public SysDictData selectDictDataById(Integer dictCode);

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
	public int deleteDictDataByIds(String ids);

	/**
	 * 新增保存字典数据信息
	 * 
	 * @param dictData
	 *            字典数据信息
	 * @return 结果
	 */
	public int insertDictData(SysDictData dictData);

	/**
	 * 修改保存字典数据信息
	 * 
	 * @param dictData
	 * @return 结果
	 */
	public int updateDictData(SysDictData dictData);

}