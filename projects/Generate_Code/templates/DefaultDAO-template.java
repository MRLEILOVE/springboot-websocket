/**
 * This code is generated automatically. Please do not edit it.
 */
package ${API_BASE_PKG}.__default.DAO${module_name};

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import ${POJO_BASE_PKG}.${DTO}${module_name}.${struct.className}DTO;
import ${POJO_BASE_PKG}.${VO}${module_name}.${struct.className}VO;
import ${POJO_BASE_PKG}.model${module_name}.${struct.className};
import ${FRAMEWORK_PACKAGE}.base.DAO.IBaseDAO;
//import ${FRAMEWORK_PACKAGE}.DTO.PageDTO;

/**
 * 
 * @author Administrator
 *
 */
public abstract interface IDefault${struct.className}DAO extends IBaseDAO<${struct.className}, ${struct.className}DTO, ${struct.className}VO> {
	
	/**
	 * 
	 * @param ${struct.variableName}
	 * @return
	 */
	public int add(${struct.className} ${struct.variableName});
	
	/**
	 * 
	 * @param ${struct.variableName}
	 * @return
	 */
	public int addWithSelective(${struct.className} ${struct.variableName});
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public int removeByPK(Serializable PK);
	
	/**
	 * 
	 * @param ${struct.variableName}
	 * @return
	 */
	public int remove(${struct.className} ${struct.variableName});
	
	/**
	 * 
	 * @param ${struct.variableName}
	 * @return
	 */
	public int modifyByPK(${struct.className} ${struct.variableName});
	
	/**
	 * 
	 * @param ${struct.variableName}
	 * @return
	 */
	public int modifyWithSelectiveByPK(${struct.className} ${struct.variableName});
	
	/**
	 * 
	 * @param ${struct.variableName}
	 * @param condi${struct.className}
	 * @return
	 */
	public int modify(@Param(value="${struct.variableName}") ${struct.className} ${struct.variableName}, @Param(value="condi${struct.className}") ${struct.className} condi${struct.className});
	
	/**
	 * 
	 * @param ${struct.variableName}
	 * @param condi${struct.className}
	 * @return
	 */
	public int modifyWithSelective(@Param(value="${struct.variableName}") ${struct.className} ${struct.variableName}, @Param(value="condi${struct.className}") ${struct.className} condi${struct.className});
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public ${struct.className} getByPK(Serializable PK);
	
	/**
	 * 
	 * @param ${struct.variableName}
	 * @return
	 */
	public List<${struct.className}DTO> get(${struct.className} ${struct.variableName});
	
	/**
	 * 
	 * @return
	 */
	public List<${struct.className}DTO> gets();
	
	/**
	 * 
	 * @param ${struct.variableName}
	 * @return
	 */
	public int getCntWithPage(@Param(value="${struct.variableName}") ${struct.className} ${struct.variableName});
	
	/**
	 * 
	 * @param ${struct.variableName}
	 * @param page
	 * @param size
	 * @return
	 */
	public List<${struct.className}DTO> getsWithPage(@Param(value="${struct.variableName}") ${struct.className} ${struct.variableName}, @Param(value="page") int page, @Param(value="size") int size);
	
}
