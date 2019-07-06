package com.core.framework.base.service;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.core.framework.DTO.PageDTO;
import com.core.framework.base.DAO.IBaseDAO;
import com.core.framework.base.DTO.BaseDTO;
import com.core.framework.base.VO.BaseVO;
import com.core.framework.base.model.BaseModel;

/**
 * 
 * @author Administrator
 *
 * @param <Model>
 * @param <DTO>
 * @param <VO>
 * @param <DAO>
 */
public abstract interface IBaseService<Model extends BaseModel<Model>, DTO extends BaseDTO<DTO>, VO extends BaseVO<VO>, DAO extends IBaseDAO<Model, DTO, VO>>
		extends IService<Model> {

	/**
	 * 
	 * @param model
	 * @return
	 */
	public int add(Model model);

	/**
	 * 
	 * @param model
	 * @return
	 */
	public int addWithSelective(Model model);

	/**
	 * 
	 * @param PK
	 * @return
	 */
	public int removeByPK(Serializable PK);

	/**
	 * 
	 * @param model
	 * @return
	 */
	public int remove(Model model);

	/**
	 * 
	 * @param model
	 * @return
	 */
	public int modifyByPK(Model model);

	/**
	 * 
	 * @param model
	 * @return
	 */
	public int modifyWithSelectiveByPK(Model model);

	/**
	 * 
	 * @param model
	 * @param condiModel
	 * @return
	 */
	public int modify(Model model, Model condiModel);

	/**
	 * 
	 * @param model
	 * @param condiModel
	 * @return
	 */
	public int modifyWithSelective(Model model, Model condiModel);

	/**
	 * 
	 * @param PK
	 * @return
	 */
	public Model getByPK(Serializable PK);

	/**
	 * 
	 * @param model
	 * @return
	 */
	public List<DTO> get(Model model);

	/**
	 * 
	 * @return
	 */
	public List<DTO> gets();

	/**
	 * 
	 * @param model
	 * @param page
	 * @param size
	 * @return
	 */
	public PageDTO<DTO> getsWithPage(Model model, int page, int size);

}
