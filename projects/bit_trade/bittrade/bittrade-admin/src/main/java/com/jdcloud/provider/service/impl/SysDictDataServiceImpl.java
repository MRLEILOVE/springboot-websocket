package com.jdcloud.provider.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.jdcloud.base.enums.DictDataEnum;
import com.jdcloud.base.enums.DictTypeEnum;
import com.jdcloud.core.redis.RedisKeyUtil;
import com.jdcloud.core.redis.RedisTemplate;
import com.jdcloud.provider.mapper.SysDictDataMapper;
import com.jdcloud.provider.pojo.SysDictData;
import com.jdcloud.provider.service.SysDictDataService;
import com.jdcloud.util.Convert;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 字典数据表 服务实现类
 * </p>
 *
 * @author ourblue
 * @since 2018-11-09
 */
@Service
public class SysDictDataServiceImpl extends ServiceImpl<SysDictDataMapper, SysDictData> implements SysDictDataService {

	private final RedisTemplate redisTemplate;

	@Autowired
	public SysDictDataServiceImpl(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@Override
	public Page<SysDictData> selectDictDataList(SysDictData dictData, Page<SysDictData> page) {
		return page.setRecords( baseMapper.selectDictDataList( page, dictData ) );
	}

	@Override
	public List<SysDictData> selectDictDataByType(String dictType) {
		return baseMapper.selectDictDataByType( dictType );
	}

	@Override
	public String selectDictLabel(String dictType, String dictValue) {
		return baseMapper.selectDictLabel( dictType, dictValue );
	}

	@Override
	public SysDictData selectDictDataById(Integer dictCode) {
		return baseMapper.selectDictDataById( dictCode );
	}

	@Override
	public int deleteDictDataById(Integer dictCode) {
		return baseMapper.deleteDictDataById( dictCode );
	}

	@Override
	public int deleteDictDataByIds(String ids) {
		String[] idList = ConvertUtil.toStrArray(ids);
		List<String> dictDataCacheKeys = Lists.newArrayList();
		// 循环保存字典缓存 key
		for (String id : idList) {
			SysDictData sysDictData = baseMapper.selectDictDataById(Integer.valueOf(id));
			dictDataCacheKeys.add(RedisKeyUtil.DICT_DATA_CACHE_PREF + sysDictData.getDictType() + ":" + sysDictData.getDictCode());
		}
		int i = baseMapper.deleteDictDataByIds(idList);
		long l = redisTemplate.delete(ArrayUtils.toStringArray(dictDataCacheKeys.toArray()));
		return i > 0 && l > 0 ? 1 : 0;
	}

	@Override
	public int insertDictData(SysDictData dictData) {
		int i = baseMapper.insertDictData(dictData);
		boolean b = redisTemplate.set(RedisKeyUtil.DICT_DATA_CACHE_PREF + dictData.getDictType() + ":" + dictData.getDictCode(),
				dictData.getDictValue());
		return i > 0 && b ? 1 : 0;
	}

	@Override
	public int updateDictData(SysDictData dictData) {
		int i = baseMapper.updateDictData(dictData);
		boolean b = redisTemplate.set(RedisKeyUtil.DICT_DATA_CACHE_PREF + dictData.getDictType() + ":" + dictData.getDictCode(),
				dictData.getDictValue());
		return i > 0 && b ? 1 : 0;
	}

}