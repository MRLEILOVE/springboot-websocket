package com.core.framework.base.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.core.framework.DTO.PageDTO;
import com.core.framework.base.DAO.IBaseDAO;
import com.core.framework.base.DTO.BaseDTO;
import com.core.framework.base.VO.BaseVO;
import com.core.framework.base.interfaces.ICreateTime;
import com.core.framework.base.interfaces.IUpdateTime;
import com.core.framework.base.model.BaseModel;
import com.core.framework.base.service.IBaseService;

/**
 * 
 * @author Administrator
 * <p>
 *   你确定这样的泛型位置， 换来换去， 你很爽？
 * </p>
 *
 * @param <Model>
 * @param <DTO>
 * @param <VO>
 * @param <DAO>
 */
public abstract class BaseServiceImpl<Model extends BaseModel<Model>, DTO extends BaseDTO<DTO>, VO extends BaseVO<VO>, DAO extends IBaseDAO<Model, DTO, VO>> extends ServiceImpl<DAO, Model> implements IBaseService<Model, DTO, VO> {
	
	@Autowired
	protected DAO baseDAO; // super.baseMapper

	private void setCreateTime/*AndModify*/(Model model) {
		if (model instanceof ICreateTime/* && model.getClass() == ICreateAndModifyTime.class*/) { // equals() ?
			Date now = new Date();
			((ICreateTime) model).setCreateTime(now);
		}
	}

	private void setUpdateTime(Model model) {
		if (model instanceof IUpdateTime/* && model.getClass() == ICreateAndModifyTime.class*/) { // equals() ?
			Date now = new Date();
			((IUpdateTime) model).setUpdateTime(now);
		}
	}
	
	@Override
	public int add(Model model) {
		setCreateTime(model);
		return baseDAO.add(model);
	}

	@Override
	public int addWithSelective(Model model) {
		setCreateTime(model);
		return baseDAO.addWithSelective(model);
	}

	@Override
	public int removeByPK(Serializable PK) {
		return baseDAO.removeByPK(PK);
	}

	@Override
	public int removeBy(Model model) {
		return baseDAO.removeBy(model);
	}

	@Override
	public int modifyByPK(Model model) {
		setUpdateTime(model);
		return baseDAO.modifyByPK(model);
	}

	@Override
	public int modifyWithSelectiveByPK(Model model) {
		setUpdateTime(model);
		return baseDAO.modifyWithSelectiveByPK(model);
	}

	@Override
	public int modifyBy(Model model, Model condiModel) {
		setUpdateTime(model);
		return baseDAO.modifyBy(model, condiModel);
	}

	@Override
	public int modifyWithSelectiveBy(Model model, Model condiModel) {
		setUpdateTime(model);
		return baseDAO.modifyWithSelectiveBy(model, condiModel);
	}

	@Override
	public Model getByPK(Serializable PK) {
		return baseDAO.getByPK(PK);
	}

	@Override
	public Model getBy(Model model) {
		return baseDAO.getBy(model);
	}
	
	@Override
	public Model get() {
		return baseDAO.get();
	}

	@Override
	public List<Model> getsBy(Model model) {
		return baseDAO.getsBy(model);
	}

	@Override
	public List<Model> gets() {
		return baseDAO.gets();
	}

	@Override
	public PageDTO<Model> getsByPage(Model model, int page, int size) {
		PageDTO<Model> pageDTO;
		
		int i_totalPage, i_totalSize;
		i_totalSize = baseDAO.getCntBy(model);
		i_totalPage = (i_totalSize - 1) / size + 1;
		List<Model> list_data = baseDAO.getsByPage(model, (page - 1) * size, size);
		
		pageDTO = new PageDTO<Model>(page, size, i_totalPage, i_totalSize, list_data);
		
		return pageDTO;
	}

}
