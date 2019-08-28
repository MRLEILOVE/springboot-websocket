package com.bittrade.admin.service.impl.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bittrade.admin.dao.sys.ISysDictDataDAO;
import com.bittrade.admin.dao.sys.ISysDictTypeDAO;
import com.bittrade.admin.model.domain.SysDictType;
import com.bittrade.admin.service.sys.SysDictTypeService;
import com.core.common.constant.GlobalConstant.UserConstant;
import com.core.tool.ConvertUtil;

/**
 * <p>
 * 字典类型表 服务实现类
 * </p>
 *
 * @author who ?
 * @since 2018-11-09
 */
@Service
public class SysDictTypeServiceImpl extends ServiceImpl<ISysDictTypeDAO, SysDictType> implements SysDictTypeService {

	@Autowired
	private ISysDictDataDAO dictDataMapper;

	@Override
	public Page<SysDictType> selectDictTypeList(SysDictType dictType, Page<SysDictType> page) {
		return page.setRecords( baseMapper.selectDictTypeList( page, dictType ) );
	}

	@Override
	public List<SysDictType> selectDictTypeAll() {
		return baseMapper.selectDictTypeAll();
	}

	@Override
	public SysDictType selectDictTypeById(Integer dictId) {
		return baseMapper.selectDictTypeById( dictId );
	}

	@Override
	public int deleteDictTypeById(Integer dictId) {
		return baseMapper.deleteDictTypeById( dictId );
	}

	@Override
	public int deleteDictTypeByIds(String ids) throws Exception {
		Integer[] dictIds = ConvertUtil.toIntArray( ids );
		for (Integer dictId : dictIds) {
			SysDictType dictType = selectDictTypeById( dictId );
			if (dictDataMapper.countDictDataByType( dictType.getDictType() ) > 0) {
				throw new Exception( String.format( "%1$s已分配,不能删除", dictType.getDictName() ) );
			}
		}

		return baseMapper.deleteDictTypeByIds( dictIds );
	}

	@Override
	public int insertDictType(SysDictType dictType) {
		return baseMapper.insertDictType( dictType );
	}

	@Override
	public int updateDictType(SysDictType dictType) {
		SysDictType oldDict = baseMapper.selectDictTypeById( dictType.getDictId() );
		dictDataMapper.updateDictDataType( oldDict.getDictType(), dictType.getDictType() );
		return baseMapper.updateDictType( dictType );
	}

	@Override
	public String checkDictTypeUnique(SysDictType dict) {
		Integer dictId = StringUtils.isEmpty( dict.getDictId() ) ? -1 : dict.getDictId();
		SysDictType dictType = baseMapper.checkDictTypeUnique( dict.getDictType() );
		if (null != dictType && dictType.getDictId().longValue() != dictId.longValue()) {
			return UserConstant.DICT_TYPE_NOT_UNIQUE;
		}
		return UserConstant.DICT_TYPE_UNIQUE;
	}
}
