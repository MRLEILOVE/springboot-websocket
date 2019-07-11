package com.core.framework.base.service;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.core.framework.DTO.PageDTO;
import com.core.framework.base.DTO.BaseDTO;
import com.core.framework.base.VO.BaseVO;
import com.core.framework.base.model.BaseModel;

/**
 * 
 * @author Administrator
 * <p>
 *   下面的类在传泛型的时候，会有引用不到的问题。 比如：下面的DAO类和SQL应该都放在具体业务项目里面，这样Service接口就会引用不到DAO类了。<br />
 *   现有三种解决方法：<br />
 *     1、 把DAO类和SQL也放在Service接口所在的api项目。<br />
 *     2、 泛型传上一层的接口，比如IDefaultTUserDAO，但是这样会要多创建一套IDefault*DAO的代理类（如：mybatis代理类），而且在注入IDefault*DAO类型的时候还需要明确指定哪个实现（比如：使用javax.annotation.Resource(JSR250)或者javax.inject.Named/javax.inject.Inject(JSR330)或者Spring的Qualifier注解），而且这个baseDAO(Mapper)还会忽略I*DAO类的方法（因为不是I*DAO这个类型）。<br />
 *     3、 接口的泛型类型由下面具体实现类来传递，而不是在接口中自己指定。<br />
 * </p>
 *
 * @param <Model>
 * @param <DTO>
 * @param <VO>
 * @param <DAO>
 */
public abstract interface IBaseService<Model extends BaseModel<Model>, DTO extends BaseDTO<DTO>, VO extends BaseVO<VO>/*, DAO extends IBaseDAO<Model, DTO, VO>*/>
		extends IService<Model> {

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
	public int remove(Model model);

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
	public int modify(Model model, Model condiModel);

	/**
	 * 修改根据实体（会使用数据库默认值）
	 * @param model
	 * @param condiModel
	 * @return
	 */
	public int modifyWithSelective(Model model, Model condiModel);

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
	public Model get(Model model);

	/**
	 * 查询多个根据实体
	 * @param model
	 * @return
	 */
	public List<Model> gets(Model model);

	/**
	 * 查询所有
	 * @return
	 */
	public List<Model> gets();

	/**
	 * 查询多个根据实体和分页
	 * @param model
	 * @param page
	 * @param size
	 * @return
	 */
	public PageDTO<Model> getsWithPage(Model model, int page, int size);

}
