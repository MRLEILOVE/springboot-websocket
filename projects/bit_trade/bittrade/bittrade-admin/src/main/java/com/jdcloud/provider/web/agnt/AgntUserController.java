package com.jdcloud.provider.web.agnt;

import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.pojo.AgntUser;
import com.jdcloud.provider.service.AgntUserService;
import com.jdcloud.provider.web.base.BaseController;
import com.jdcloud.util.wrapper.TableDataInfo;
import com.jdcloud.util.wrapper.Wrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 代理商账号管理
 * </p>
 *
 * @author ourblue
 * @since 2018-11-03
 */
@Controller
@RequestMapping("/agnt/user")
public class AgntUserController extends BaseController {

	@Autowired
	private AgntUserService	agntUserService;

	private String			prefix	= "agnt/user";

	@RequiresPermissions("agnt:user:view")
	@GetMapping()
	public String user() {
		return prefix + "/user";
	}

	@RequiresPermissions("agnt:user:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(AgntUser user) {
		Page<AgntUser> list = agntUserService.selectAgntUserList( getPage(), user );
		return new TableDataInfo( list.getRecords(), list.getTotal() );
	}

	@GetMapping("/add")
	public String add(ModelMap mmap) {
		return prefix + "/add";
	}

	/**
	 * 添加代理商账号<br>
	 * 
	 * @return:
	 * @Author: yongheng
	 * @Date: 2019/1/10 18:01
	 */
	@RequiresPermissions("agnt:user:add")
	@PostMapping("/add")
	@ResponseBody
	public Wrapper<String> addSave(AgntUser user) {
		return agntUserService.insertAgntUser( user );
	}

	@GetMapping("/edit/{agntId}")
	public String edit(@PathVariable("agntId") Integer agntId, ModelMap mmap) {
		mmap.put( "agnt", agntUserService.selectByAgntId( agntId ) );
		return prefix + "/edit";
	}

	/**
	 * 编辑代理商<br>
	 * 
	 * @return:
	 * @Author: yongheng
	 * @Date: 2019/1/10 18:01
	 */
	@PostMapping("/edit")
	@RequiresPermissions("agnt:user:edit")
	@ResponseBody
	public Wrapper<String> editSave(AgntUser user) {
		return toAjax( agntUserService.editAgntUser( user ) );
	}

	/**
	 * 代理商删除<br>
	 * 
	 * @return:
	 * @Author: yongheng
	 * @Date: 2019/1/10 18:01
	 */
	@PostMapping("/del")
	@RequiresPermissions("agnt:user:del")
	@ResponseBody
	public Wrapper<String> del(AgntUser agntUser) {
		return agntUserService.delAgntUser( agntUser );
	}

	/**
	 * 跳转修改代理商级别页面<br>
	 * 
	 * @return:
	 * @Author: yongheng
	 * @Date: 2019/1/10 18:01
	 */
	@GetMapping("/editLevel/{agntId}/{agntLevel}")
	public String editLevel(@PathVariable("agntId") Integer agntId, @PathVariable("agntLevel") Integer agntLevel, ModelMap mmap) {
		AgntUser agntUser = agntUserService.selectById( agntId );
		AgntUser parentAgnt = agntUserService.selectById( agntUser.getParentId() );
		mmap.put( "agntId", agntId );
		mmap.put( "agntLevel", agntLevel );
		mmap.put( "parentAgntId", parentAgnt == null ? "" : parentAgnt.getAgntId() );
		mmap.put( "parentName", parentAgnt == null ? "" : parentAgnt.getAgntName() );
		mmap.put( "parentLoginName", parentAgnt == null ? "" : parentAgnt.getLoginName() );
		return prefix + "/editLevel";
	}

	/**
	 * 修改代理商级别<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2019/1/10 18:01
	 */
	@PostMapping("/editAgntLevel")
	@RequiresPermissions("agnt:user:edit")
	@ResponseBody
	public Wrapper<String> editAgntLevel(AgntUser agntUser) {
		return agntUserService.editAgntLevel( agntUser );
	}
}