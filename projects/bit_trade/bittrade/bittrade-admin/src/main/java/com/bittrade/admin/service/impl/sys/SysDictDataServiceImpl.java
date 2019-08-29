package com.bittrade.admin.service.impl.sys;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bittrade.admin.dao.sys.ISysDictDataDAO;
import com.bittrade.admin.service.sys.SysDictDataService;
import com.bittrade.admin.util.RedisKeyUtil;
import com.bittrade.pojo.model.SysDictData;
import com.core.tool.ConvertUtil;
import com.google.common.collect.Lists;

/**
 * <p>
 * 字典数据表 服务实现类
 * </p>
 *
 * @author who ?
 * @since 2018-11-09
 */
@Service
public class SysDictDataServiceImpl extends ServiceImpl<ISysDictDataDAO, SysDictData> implements SysDictDataService {

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
		boolean b_l = redisTemplate.delete(ArrayUtils.toStringArray(dictDataCacheKeys.toArray()));
		return i > 0 && b_l ? 1 : 0;
	}

	@Override
	public int insertDictData(SysDictData dictData) {
		int i = baseMapper.insertDictData(dictData);
		redisTemplate.opsForValue().set(RedisKeyUtil.DICT_DATA_CACHE_PREF + dictData.getDictType() + ":" + dictData.getDictCode(),
				dictData.getDictValue());
		return i > 0 ? 1 : 0;
	}

	@Override
	public int updateDictData(SysDictData dictData) {
		int i = baseMapper.updateDictData(dictData);
		redisTemplate.opsForValue().set(RedisKeyUtil.DICT_DATA_CACHE_PREF + dictData.getDictType() + ":" + dictData.getDictCode(),
				dictData.getDictValue());
		return i > 0 ? 1 : 0;
	}

}
