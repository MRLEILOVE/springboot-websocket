package com.core.framework.base.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.core.framework.DTO.PageDTO;
import com.core.framework.base.DAO.IBaseDAO;
import com.core.framework.base.DTO.BaseDTO;
import com.core.framework.base.VO.BaseVO;
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
	protected DAO _DAO; // super.baseMapper

	@Override
	public int add(Model model) {
		return _DAO.add(model);
	}

	@Override
	public int addWithSelective(Model model) {
		return _DAO.addWithSelective(model);
	}

	@Override
	public int removeByPK(Serializable PK) {
		return _DAO.removeByPK(PK);
	}

	@Override
	public int remove(Model model) {
		return _DAO.remove(model);
	}

	@Override
	public int modifyByPK(Model model) {
		return _DAO.modifyByPK(model);
	}

	@Override
	public int modifyWithSelectiveByPK(Model model) {
		return _DAO.modifyWithSelectiveByPK(model);
	}

	@Override
	public int modify(Model model, Model condiModel) {
		return _DAO.modify(model, condiModel);
	}

	@Override
	public int modifyWithSelective(Model model, Model condiModel) {
		return _DAO.modifyWithSelective(model, condiModel);
	}

	@Override
	public Model getByPK(Serializable PK) {
		return _DAO.getByPK(PK);
	}

	@Override
	public List<DTO> get(Model model) {
		return _DAO.get(model);
	}

	@Override
	public List<DTO> gets() {
		return _DAO.gets();
	}

	@Override
	public PageDTO<DTO> getsWithPage(Model model, int page, int size) {
		PageDTO<DTO> pageDTO;
		
		int i_totalPage, i_totalSize;
		i_totalSize = _DAO.getCntWithPage(model);
		i_totalPage = (i_totalSize - 1) / size + 1;
		List<DTO> list_data = _DAO.getsWithPage(model, (page - 1) * size, size);
		
		pageDTO = new PageDTO<DTO>(page, size, i_totalPage, i_totalSize, list_data);
		
		return pageDTO;
	}

}
