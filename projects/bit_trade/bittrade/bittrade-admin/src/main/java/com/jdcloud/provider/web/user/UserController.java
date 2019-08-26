package com.jdcloud.provider.web.user;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.dto.UserAccountDto;
import com.jdcloud.provider.dto.UserUpdatePasswordDto;
import com.jdcloud.provider.model.dto.UserAuthenticationDto;
import com.jdcloud.provider.model.service.UacUserFeignApi;
import com.jdcloud.provider.pojo.UserAccount;
import com.jdcloud.provider.pojo.UserVo;
import com.jdcloud.provider.service.UserAccountService;
import com.jdcloud.provider.service.UserStatisticService;
import com.jdcloud.provider.utils.ExcelUtil;
import com.jdcloud.provider.web.base.BaseController;
import com.jdcloud.util.wrapper.TableDataInfo;
import com.jdcloud.util.wrapper.WrapMapper;
import com.jdcloud.util.wrapper.Wrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 微合约持仓表 前端控制器
 * </p>
 *
 * @author ourblue
 * @since 2018-11-03
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	private String			prefix	= "user";

	@Autowired
	private UserAccountService userAccountService;
	@Autowired
	private UserStatisticService userStatisticService;
	@Resource
	private UacUserFeignApi uacUserFeignApi;

	@RequiresPermissions("user:view")
	@GetMapping()
	public String user() {
		return prefix + "/user";
	}

	@RequiresPermissions("user:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(UserAccountDto userDto) {
		Page<UserVo> users = userAccountService.selectUserList( getPage(), userDto );
		return new TableDataInfo( users.getRecords(), users.getTotal() );
	}
	@RequiresPermissions("user:export")
	@PostMapping("/export")
	@ResponseBody
	public Wrapper<String> export(UserAccountDto userDto) {
		List<UserVo> users = userAccountService.selectContractMicroExportList(userDto);
		ExcelUtil<UserVo> util = new ExcelUtil<UserVo>( UserVo.class );
		return util.exportExcel( users, "users" );
	}

	@RequiresPermissions("user:detail")
	@GetMapping("/detail/{userId}")
	public String detail(@PathVariable("userId") Long userId, ModelMap mmap) {
		mmap.put( "user", userAccountService.selectUserById( userId ) );
		Wrapper<UserAuthenticationDto> wrapper = uacUserFeignApi.queryUserAuthentication( userId );
		UserAuthenticationDto userAuth = wrapper.getResult();
		if (userAuth == null) {
			userAuth = new UserAuthenticationDto();
		}
		mmap.put( "userAuth", userAuth);
		return prefix + "/detail";
	}

	@RequiresPermissions("user:edit")
	@PostMapping("/edit")
	@ResponseBody
	public Wrapper edit(UserAccountDto userDto) {
		List<UserVo> users = userAccountService.selectContractMicroExportList(userDto);
		ExcelUtil<UserVo> util = new ExcelUtil<UserVo>( UserVo.class );
		return util.exportExcel( users, "users" );
	}


	@PostMapping("/internalAccount")
	@ResponseBody
	public Wrapper internalAccount(Long id,Integer caution) {
		try {
			UserAccount userAccount = userAccountService.selectById(id);
			if (userAccount == null) return WrapMapper.error("账户不存在！");
			userAccount.setInternalAccount(caution);
			userAccountService.updateById(userAccount);
			return WrapMapper.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return WrapMapper.error("账户设置失败！");
		}
	}

	@PostMapping("/updatePassword")
	@ResponseBody
	public Wrapper updatePassword(UserUpdatePasswordDto updatePasswordDto ) {
		return uacUserFeignApi.updatePassword(JSON.toJSONString(updatePasswordDto));
	}



	/**
	 * @Description: 手动更新团队人数
	 * @Author: senghor
	 * @Date: 2019/5/24 0024 16:41
	 */
	@RequiresPermissions("user:refreshTeamCount")
	@PostMapping("/refreshTeamCount")
	@ResponseBody
	public synchronized Wrapper refreshTeamCount() {
		return userStatisticService.refreshTeamCount();
	}


}