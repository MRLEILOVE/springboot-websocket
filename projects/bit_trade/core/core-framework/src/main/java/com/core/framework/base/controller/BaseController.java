package com.core.framework.base.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.core.framework.DTO.PageDTO;
import com.core.framework.base.DAO.IBaseDAO;
import com.core.framework.base.DTO.BaseDTO;
import com.core.framework.base.VO.BaseVO;
import com.core.framework.base.model.BaseModel;
import com.core.framework.base.service.IBaseService;

/**
 * 
 * <pre>
 * 	1. request pattern: *.xml or *.json .
 * 	2. 调用接口的时候修改accept信息， 改为application/json (postman之类的工具) 。
 * </pre>
 * 
 * @author Administrator
 *
 * @param <Model>
 * @param <DTO>
 * @param <VO>
 * @param <DAO>
 * @param <Service>
 */
public abstract class BaseController<Model extends BaseModel<Model>, DTO extends BaseDTO<DTO>, VO extends BaseVO<VO>, DAO extends IBaseDAO<Model, DTO, VO>, Service extends IBaseService<Model, DTO, VO>> {

	@Autowired
	protected Service baseService;

	@RequestMapping(value = "/add", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public int add(Model model) {
		return baseService.add(model);
	}

	@RequestMapping(value = "/addWithSelective", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public int addWithSelective(Model model) {
		return baseService.addWithSelective(model);
	}

	@RequestMapping(value = "/removeByPK", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public int removeByPK(Serializable PK) {
		return baseService.removeByPK(PK);
	}

	@RequestMapping(value = "/remove", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public int remove(Model model) {
		return baseService.remove(model);
	}

	@RequestMapping(value = "/modifyByPK", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public int modifyByPK(Model model) {
		return baseService.modifyByPK(model);
	}

	@RequestMapping(value = "/modifyWithSelectiveByPK", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public int modifyWithSelectiveByPK(Model model) {
		return baseService.modifyWithSelectiveByPK(model);
	}

	@RequestMapping(value = "/modify", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public int modify(Model model, Model condiModel) {
		return baseService.modify(model, condiModel);
	}

	@RequestMapping(value = "/modifyWithSelective", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public int modifyWithSelective(Model model, Model condiModel) {
		return baseService.modifyWithSelective(model, condiModel);
	}

	@RequestMapping(value = "/getByPK", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Model getByPK(int PK) {
		return baseService.getByPK(PK);
	}

	@RequestMapping(value = "/get", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Model get(Model model) {
		return baseService.get(model);
	}

	@RequestMapping(value = "/getsBy", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Model> getsBy(Model model) {
		return baseService.getsBy(model);
	}

	@RequestMapping(value = "/gets", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Model> gets() {
		return baseService.gets();
	}

	@RequestMapping(value = "/getsWithPage", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public PageDTO<Model> getsWithPage(
			Model model, 
			@RequestParam(required = false, defaultValue = "1") int page, 
			@RequestParam(required = false, defaultValue = "10") int size
			) {
		return baseService.getsWithPage(model, page, size);
	}

}
