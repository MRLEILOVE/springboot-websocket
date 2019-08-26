package com.jdcloud.provider.web.audit;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.base.enums.ConstantEnum;
import com.jdcloud.provider.dto.Authentication;
import com.jdcloud.provider.model.dto.AuthenticationDto;
import com.jdcloud.provider.model.dto.UserAuthenticationDto;
import com.jdcloud.provider.model.service.UacUserFeignApi;
import com.jdcloud.provider.pojo.AgntUser;
import com.jdcloud.provider.pojo.AuditRecord;
import com.jdcloud.provider.service.AgntUserService;
import com.jdcloud.provider.service.AuditRecordService;
import com.jdcloud.provider.service.UserAccountService;
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

/**
 * <p>
 * 身份认证审核
 * <p>
 *
 * @author yongheng
 * @since 2018/11/22
 */
@Controller
@RequestMapping("/audit/authentication")
public class AuthenticationController extends BaseController {

	@Resource
	private UacUserFeignApi		uacUserFeignApi;
	@Autowired
	private UserAccountService	userAccountService;
	@Autowired
	private AuditRecordService	auditRecordService;
	@Autowired
	private AgntUserService		agntUserService;
	private String				prefix	= "audit/authentication";

	@RequiresPermissions("audit:authentication:view")
	@GetMapping()
	public String authentication() {
		return prefix + "/authentication";
	}

	@RequiresPermissions("audit:authentication:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(AuthenticationDto authentication) {
		this.paramsHandle( authentication );
		Wrapper<Page<AuthenticationDto>> pageWrapper = uacUserFeignApi.queryAuthenticationList( JSON.toJSONString( authentication ) );
		Page<AuthenticationDto> result = pageWrapper.getResult();
		for (AuthenticationDto record : result.getRecords()) {
			AgntUser agntUser = agntUserService.selectAgntById( record.getUserId() );
			if (agntUser != null) {
				record.setAgntName( agntUser.getAgntName() );
			}

			if (record.getFhasRealValidate().toString().equals(ConstantEnum.AuthenticationConstant.NOT_AUDIT.getCode())) {
				AuditRecord auditRecord = auditRecordService.selectByUserId( record.getUserId() );
				if (auditRecord != null) {
					record.setRemark( auditRecord.getAuditRemark() );
				}
			}
		}
		return new TableDataInfo( result.getRecords(), result.getTotal() );
	}

	@RequiresPermissions("audit:authentication:edit")
	@GetMapping("/edit/{userId}")
	public String authenticationEdit(@PathVariable("userId") Long userId, ModelMap mmap) {
		Wrapper<UserAuthenticationDto> wrapper = uacUserFeignApi.queryUserAuthentication( userId );
		// 根据userId查询邀请人
		AgntUser agntUser = agntUserService.selectAgntById( userId );
		if (wrapper.getResult() != null && agntUser != null) {
			wrapper.getResult().setAgntName( agntUser.getAgntName() );
		}
		mmap.put( "authentication", wrapper.getResult() );
		return prefix + "/edit";
	}

	/**
	 * 认证审核<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/26 19:56
	 */
	@RequiresPermissions("audit:authentication:audit")
	@PostMapping("/audit")
	@ResponseBody
	public Wrapper<UserAuthenticationDto> audit(Authentication authentication) {
		Wrapper<UserAuthenticationDto> wrapper = uacUserFeignApi.authenticationAudit( authentication.getUserId(),
				authentication.getFhasRealValidate(), this.getUser().getUserName() );
		if (wrapper.getResult() == null) {
			return WrapMapper.error( "审核失败" );
		}

		// 保存审核记录
		auditRecordService.saveRecord( wrapper.getResult(), authentication.getRemark() );
		return WrapMapper.ok();
	}

	@RequiresPermissions("audit:authentication:auditRefuse")
	@GetMapping("/auditRefuse/{userId}")
	public String auditRefuse(@PathVariable("userId") Long userId, ModelMap mmap) {
		mmap.put( "userId", userId );
		return prefix + "/auditRemark";
	}
}