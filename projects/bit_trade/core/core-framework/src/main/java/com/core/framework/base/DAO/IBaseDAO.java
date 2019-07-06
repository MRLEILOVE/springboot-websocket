package com.core.framework.base.DAO;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author Administrator
 *
 * @param <Model>
 * @param <DTO>
 * @param <VO>
 */
public abstract interface IBaseDAO<Model, DTO, VO> extends com.baomidou.mybatisplus.core.mapper.BaseMapper<Model> {

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
	 * @return
	 */
	public int getCntWithPage(Model model);

	/**
	 * 
	 * @param model
	 * @param page
	 * @param size
	 * @return
	 */
	public List<DTO> getsWithPage(Model model, int page, int size);

}
