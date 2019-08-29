package com.bittrade.uac.web.fronted;

import javax.validation.Valid;

import com.bittrade.uac.model.dto.ChangePasswordDto;
import com.bittrade.uac.model.dto.CurrentUserDto;
import com.bittrade.uac.model.dto.ReturnDTO;
import com.bittrade.uac.model.dto.UserAuthenticationDto;
import com.bittrade.uac.model.enums.ConstantEnum;
import com.bittrade.uac.model.pojo.AuditRecord;
import com.bittrade.uac.model.pojo.User;
import com.bittrade.uac.model.pojo.UserAuthentication;
import com.bittrade.uac.model.vo.UserAuthenticationVo;
import com.bittrade.uac.service.AuditRecordService;
import com.bittrade.uac.service.UacAuthenticationService;
import com.bittrade.uac.service.UacUserService;
import com.bittrade.uac.utils.WebUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.bittrade.uac.model.vo.UserVo;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: xzc
 * @create: 2019/8/27 下午2:38
 * @description: 用户接口
 **/
@Slf4j
@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UacUserController {

    @Autowired
    private UacUserService uacUserService;

    @Autowired
    private UacAuthenticationService authenticationService;

    @Autowired
    private AuditRecordService auditRecordService;

    /**
     * 当前用户登陆信息
     *
     * @return
     */
    @PostMapping(value = "/information")
    @ApiOperation(value = "当前登入用户信息", notes = "当前登入用户信息")
    public ReturnDTO<UserVo> queryUserInfo() {
        CurrentUserDto currentUser = WebUtil.getCurrentUser();
        User user = uacUserService.getById(currentUser.getUserId());
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        return ReturnDTO.ok(userVo);
    }

    /**
     * 修改用户密码
     *
     * @param changePasswordDto
     * @return
     */
    @PostMapping(value = "/updatePassword")
    @ApiOperation(value = "修改用户密码", notes = "修改用户密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "新密码", value = "newPass", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "旧密码", value = "oldPass", required = true, dataType = "String", paramType = "query")
    })
    public ReturnDTO<String> updatePassword(@RequestBody ChangePasswordDto changePasswordDto) {
        log.info("修改用户密码请求参数" + changePasswordDto.toString());
        uacUserService.updatePassword(changePasswordDto);
        return ReturnDTO.ok();
    }

    /**
     * 用户认证
     *
     * @param userAuthenticationDto
     * @return
     */
    @PostMapping(value = "/userAuthentication")
    @ApiOperation(value = "用户认证", notes = "用户认证")
    public ReturnDTO<String> userAuthentication(@RequestBody @Valid UserAuthenticationDto userAuthenticationDto) {
        return authenticationService.saveInfo(userAuthenticationDto);
    }

    /**
     * 查询认证信息
     *
     * @return
     */
    @GetMapping(value = "/authenticationInformation ")
    @ApiOperation(value = "查询认证信息 ", notes = "查询认证信息 ")
    public ReturnDTO<UserAuthenticationVo> authenticationInformation() {
        CurrentUserDto currentUser = WebUtil.getCurrentUser();
        Long userId = currentUser.getUserId();
        UserAuthentication authentication = authenticationService.getById(userId);
        UserAuthenticationVo authenticationVo = new UserAuthenticationVo();
        if (authentication == null) {
            return ReturnDTO.ok();
        }
        BeanUtils.copyProperties(authentication, authenticationVo);
        //查询认证被拒原因
        if (authenticationVo.getFhasRealValidate().toString().equals(ConstantEnum.AuthenticationConstant.NOT_AUDIT.getCode())) {
            AuditRecord auditRecord = auditRecordService.getByUserId(userId);
            if (auditRecord != null) {
                authenticationVo.setRemark(auditRecord.getAuditRemark());
            }
        }
        return ReturnDTO.ok(authenticationVo);
    }
}