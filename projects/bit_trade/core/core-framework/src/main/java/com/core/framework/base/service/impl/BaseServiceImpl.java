package com.core.framework.base.service.impl;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.core.common.DTO.PageDTO;
import com.core.common.constant.IConstant;
import com.core.framework.base.DAO.IBaseDAO;
import com.core.framework.base.DTO.BaseDTO;
import com.core.framework.base.VO.BaseVO;
import com.core.framework.base.interfaces.ICreateTime;
import com.core.framework.base.interfaces.IUpdateTime;
import com.core.framework.base.model.BaseModel;
import com.core.framework.base.service.IBaseService;
import com.core.tool.MathematicsUtil;

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
			LocalDateTime now = LocalDateTime.now();
			((ICreateTime) model).setCreateTime(now);
		}
	}

	private void setUpdateTime(Model model) {
		if (model instanceof IUpdateTime/* && model.getClass() == ICreateAndModifyTime.class*/) { // equals() ?
			LocalDateTime now = LocalDateTime.now();
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
		
		if (page <= 0) {
			page = IConstant.PAGE_INDEX;
		}
		if (size <= 0) {
			size = IConstant.PAGE_SIZE;
		}
		
		int i_totalPage = 0, i_totalSize;
		List<Model> list_data = null;
		i_totalSize = baseDAO.getCntBy(model);
		if (i_totalSize > 0) {
			if (size > i_totalSize) {
				size = i_totalSize;
			}
			i_totalPage = MathematicsUtil.getTotalPage(size, i_totalSize);
			list_data = baseDAO.getsByPage(model, MathematicsUtil.getPageBeginIndex(page, size), size);
		}
		
		pageDTO = new PageDTO<Model>(page, size, i_totalPage, i_totalSize, list_data);
		
		return pageDTO;
	}
	
	@Override
	public PageDTO<Model> getsByPagination(Model model) {
		return getsByPage(model, model.getCurrent(), model.getSize());
	}

}
