package com.core.framework.base.DAO;

import java.io.Serializable;
import java.util.List;

import com.core.common.DTO.PageDTO;

/**
 * 菜不够可以加。
 * @author Administrator
 *
 * @param <Model>
 * @param <DTO>
 * @param <VO>
 */
public abstract interface IBaseDAO<Model, DTO, VO> extends com.baomidou.mybatisplus.core.mapper.BaseMapper<Model> {

	/**
	 * 新增（不会使用数据库默认值）
	 * @param model
	 * @return
	 */
	public int add(Model model);

	/**
	 * 新增（会使用数据库默认值）
	 * @param model
	 * @return
	 */
	public int addWithSelective(Model model);

	/**
	 * 移除根据主键
	 * @param PK
	 * @return
	 */
	public int removeByPK(Serializable PK);

	/**
	 * 移除根据实体
	 * @param model
	 * @return
	 */
	public int removeBy(Model model);

	/**
	 * 修改根据主键
	 * @param model
	 * @return
	 */
	public int modifyByPK(Model model);

	/**
	 * 修改根据主键（会使用数据库默认值）
	 * @param model
	 * @return
	 */
	public int modifyWithSelectiveByPK(Model model);

	/**
	 * 修改根据实体
	 * @param model
	 * @param condiModel
	 * @return
	 */
	public int modifyBy(Model model, Model condiModel);

	/**
	 * 修改根据实体（会使用数据库默认值）
	 * @param model
	 * @param condiModel
	 * @return
	 */
	public int modifyWithSelectiveBy(Model model, Model condiModel);

	/**
	 * 查询一个根据主键
	 * @param PK
	 * @return
	 */
	public Model getByPK(Serializable PK);

	/**
	 * 查询一个根据实体（默认取第一个）
	 * @param model
	 * @return
	 */
	public Model getBy(Model model);

	/**
	 * 查询一个（默认取第一个）
	 * @param model
	 * @return
	 */
	public Model get();

	/**
	 * 查询多个根据实体
	 * @param model
	 * @return
	 */
	public List<Model> getsBy(Model model);

	/**
	 * 查询所有
	 * @return
	 */
	public List<Model> gets();

	/**
	 * 查询数据总行数
	 * @param model
	 * @return
	 */
	public int getCntBy(Model model);

	/**
	 * 查询多个根据实体和分页
	 * @param model
	 * @param page 要改成 Current or Index ？
	 * @param size
	 * @return
	 */
	public List<Model> getsByPage(Model model, int page, int size);

	/**
	 * 查询一个根据实体（默认取第一个）
	 * @param _DTO
	 * @return
	 */
	public DTO getDTOBy(DTO _DTO);

	/**
	 * 查询多个根据实体
	 * @param _DTO
	 * @return
	 */
	public List<DTO> getsDTOBy(DTO _DTO);

	/**
	 * 查询多个根据实体和分页
	 * @param _DTO
	 * @param pageDTO
	 * @return
	 */
	public List<DTO> getsDTOBy(DTO _DTO, PageDTO<DTO> pageDTO);

}
