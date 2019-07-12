/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.DAO;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bittrade.pojo.dto.JobTaskDTO;
import com.bittrade.pojo.vo.JobTaskVO;
import com.bittrade.pojo.model.JobTask;
import com.core.framework.base.DAO.IBaseDAO;
//import com.core.framework.DTO.PageDTO;

/**
 * 
 * @author Administrator
 *
 */
public abstract interface IDefaultJobTaskDAO extends IBaseDAO<JobTask, JobTaskDTO, JobTaskVO> {
	
	/**
	 * 
	 * @param jobTask
	 * @return
	 */
	public int add(JobTask jobTask);
	
	/**
	 * 
	 * @param jobTask
	 * @return
	 */
	public int addWithSelective(JobTask jobTask);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public int removeByPK(Serializable PK);
	
	/**
	 * 
	 * @param jobTask
	 * @return
	 */
	public int removeBy(JobTask jobTask);
	
	/**
	 * 
	 * @param jobTask
	 * @return
	 */
	public int modifyByPK(JobTask jobTask);
	
	/**
	 * 
	 * @param jobTask
	 * @return
	 */
	public int modifyWithSelectiveByPK(JobTask jobTask);
	
	/**
	 * 
	 * @param jobTask
	 * @param condiJobTask
	 * @return
	 */
	public int modifyBy(@Param(value="jobTask") JobTask jobTask, @Param(value="condiJobTask") JobTask condiJobTask);
	
	/**
	 * 
	 * @param jobTask
	 * @param condiJobTask
	 * @return
	 */
	public int modifyWithSelectiveBy(@Param(value="jobTask") JobTask jobTask, @Param(value="condiJobTask") JobTask condiJobTask);
	
	/**
	 * 
	 * @param PK
	 * @return
	 */
	public JobTask getByPK(Serializable PK);
	
	/**
	 * 
	 * @param jobTask
	 * @return
	 */
	public JobTask getBy(JobTask jobTask);
	
	/**
	 * 
	 * @return
	 */
	public JobTask get();
	
	/**
	 * 
	 * @param jobTask
	 * @return
	 */
	public List<JobTask> getsBy(JobTask jobTask);
	
	/**
	 * 
	 * @return
	 */
	public List<JobTask> gets();
	
	/**
	 * 
	 * @param jobTask
	 * @return
	 */
	public int getCntBy(@Param(value="jobTask") JobTask jobTask);
	
	/**
	 * 
	 * @param jobTask
	 * @param page
	 * @param size
	 * @return
	 */
	public List<JobTask> getsByPage(@Param(value="jobTask") JobTask jobTask, @Param(value="page") int page, @Param(value="size") int size);
	
}
