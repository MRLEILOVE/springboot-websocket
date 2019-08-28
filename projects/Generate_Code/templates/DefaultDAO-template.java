/**
 * This code is generated automatically. Please do not edit it.
 */
package ${DEFAULT_BASE_PKG}.DAO${module_name};

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import ${POJO_BASE_PKG}.${DTO}${module_name}.${struct.className}DTO;
import ${POJO_BASE_PKG}.${VO}${module_name}.${struct.className}VO;
import ${POJO_BASE_PKG}.model${module_name}.${struct.className};
import ${COMMON_PACKAGE}.DTO.PageDTO;
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
	public int removeBy(${struct.className} ${struct.variableName});
	
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
	public int modifyBy(@Param(value="${struct.variableName}") ${struct.className} ${struct.variableName}, @Param(value="condi${struct.className}") ${struct.className} condi${struct.className});
	
	/**
	 * 
	 * @param ${struct.variableName}
	 * @param condi${struct.className}
	 * @return
	 */
	public int modifyWithSelectiveBy(@Param(value="${struct.variableName}") ${struct.className} ${struct.variableName}, @Param(value="condi${struct.className}") ${struct.className} condi${struct.className});
	
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
	public ${struct.className} getBy(${struct.className} ${struct.variableName});
	
	/**
	 * 
	 * @return
	 */
	public ${struct.className} get();
	
	/**
	 * 
	 * @param ${struct.variableName}
	 * @return
	 */
	public List<${struct.className}> getsBy(${struct.className} ${struct.variableName});
	
	/**
	 * 
	 * @return
	 */
	public List<${struct.className}> gets();
	
	/**
	 * 
	 * @param ${struct.variableName}
	 * @return
	 */
	public int getCntBy(@Param(value="${struct.variableName}") ${struct.className} ${struct.variableName});
	
	/**
	 * 
	 * @param ${struct.variableName}
	 * @param page
	 * @param size
	 * @return
	 */
	public List<${struct.className}> getsByPage(@Param(value="${struct.variableName}") ${struct.className} ${struct.variableName}, @Param(value="page") int page, @Param(value="size") int size);
	
	/**
	 * 
	 * @param ${struct.variableName}DTO
	 * @return
	 */
	public ${struct.className}DTO getDTOBy(${struct.className}DTO ${struct.variableName}DTO);
	
	/**
	 * 
	 * @param ${struct.variableName}DTO
	 * @return
	 */
	public List<${struct.className}DTO> getsDTOBy(${struct.className}DTO ${struct.variableName}DTO);
	
	/**
	 * 
	 * @param ${struct.variableName}DTO
	 * @param pageDTO
	 * @return
	 */
	public List<${struct.className}DTO> getsDTOBy(${struct.className}DTO ${struct.variableName}DTO, PageDTO<${struct.className}DTO> pageDTO);
	
}
