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
import com.core.framework.base.interfaces.IModifyTime;
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
public abstract class BaseServiceImpl<Model extends BaseModel<Model>, DTO extends BaseDTO<DTO>, VO extends BaseVO<VO>, DAO extends IBaseDAO<Model, DTO, VO>> extends ServiceImpl<DAO, Model> implements IBaseService<Model, DTO, VO, DAO> {
	
	@Autowired
	protected DAO baseDAO; // super.baseMapper

	private void setCreateTime/*AndModify*/(Model model) {
		if (model instanceof ICreateTime/* && model.getClass() == ICreateAndModifyTime.class*/) { // equals() ?
			Date now = new Date();
			((ICreateTime) model).setCreateTime(now);
		}
	}

	private void setModifyTime(Model model) {
		if (model instanceof IModifyTime/* && model.getClass() == ICreateAndModifyTime.class*/) { // equals() ?
			Date now = new Date();
			((IModifyTime) model).setModifyTime(now);
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
	public int remove(Model model) {
		return baseDAO.remove(model);
	}

	@Override
	public int modifyByPK(Model model) {
		setModifyTime(model);
		return baseDAO.modifyByPK(model);
	}

	@Override
	public int modifyWithSelectiveByPK(Model model) {
		setModifyTime(model);
		return baseDAO.modifyWithSelectiveByPK(model);
	}

	@Override
	public int modify(Model model, Model condiModel) {
		setModifyTime(model);
		return baseDAO.modify(model, condiModel);
	}

	@Override
	public int modifyWithSelective(Model model, Model condiModel) {
		setModifyTime(model);
		return baseDAO.modifyWithSelective(model, condiModel);
	}

	@Override
	public Model getByPK(Serializable PK) {
		return baseDAO.getByPK(PK);
	}

	@Override
	public List<DTO> get(Model model) {
		return baseDAO.get(model);
	}

	@Override
	public List<DTO> gets() {
		return baseDAO.gets();
	}

	@Override
	public PageDTO<DTO> getsWithPage(Model model, int page, int size) {
		PageDTO<DTO> pageDTO;
		
		int i_totalPage, i_totalSize;
		i_totalSize = baseDAO.getCntWithPage(model);
		i_totalPage = (i_totalSize - 1) / size + 1;
		List<DTO> list_data = baseDAO.getsWithPage(model, (page - 1) * size, size);
		
		pageDTO = new PageDTO<DTO>(page, size, i_totalPage, i_totalSize, list_data);
		
		return pageDTO;
	}

}
