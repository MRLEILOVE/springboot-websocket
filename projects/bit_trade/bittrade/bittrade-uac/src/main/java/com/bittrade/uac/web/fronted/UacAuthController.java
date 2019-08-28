package com.bittrade.uac.web.fronted;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.bittrade.uac.model.dto.UserFindPwdVo;
import com.bittrade.uac.model.vo.*;
import com.bittrade.uac.model.pojo.User;
import com.bittrade.uac.service.UacUserService;
import com.core.common.DTO.ReturnDTO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author: xzc
 * @create: 2019/8/27 下午2:38
 * @description: 用户管理
 **/
@Slf4j
@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UacAuthController {

    @Autowired
    private UacUserService uacUserService;

    /**
     * 手机是否在系统
     *
     * @param phoneNumber
     * @return
     */
    @PostMapping(value = "/checkPhoneActive/{phoneNumber}")
    @ApiOperation(value = "手机号是否已存在", notes = "手机号是否已存在")
    @ApiImplicitParam(name = "手机号码", value = "phoneNumber", required = true, dataType = "String")
    public ReturnDTO<Boolean> checkPhoneActive(@PathVariable String phoneNumber) {
        log.info("com.jdcloud.provider.web.fronted.UacAuthController.checkPhoneActive:" + phoneNumber);
        User user = new User();
        user.setTelePhone(phoneNumber);
        int count = uacUserService.count(Wrappers.lambdaQuery(user));
        return ReturnDTO.ok(count > 0);
    }

    /**
     * 发送短信
     *
     * @param sendSmsVo
     * @return
     */
    @PostMapping(value = "/send/sms")
    @ApiOperation(value = "发送短信", notes = "发送短信")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "区域号码", value = "areaCode", required = true, dataType = "String"),
            @ApiImplicitParam(name = "手机号码", value = "phoneNumber", required = true, dataType = "String"),
            @ApiImplicitParam(name = "短信发送类型", value = "sendType", required = true, dataType = "Integer"),
    })
    public ReturnDTO<String> sendSms(@RequestBody SendSmsVo sendSmsVo) {
        log.info("com.jdcloud.provider.web.fronted.UacAuthController.sendSms:" + JSON.toJSONString(sendSmsVo));
        uacUserService.sendSms(sendSmsVo);
        return ReturnDTO.ok();
    }

    /**
     * 发送邮箱注册验证码：
     * 1.验证发送邮件的时间间隔
     * 2.缓存验证码
     *
     * @param sendMailVo
     * @return
     */
    @PostMapping(value = "/send/email")
    @ApiOperation(value = "发送邮件", notes = "发送邮件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "邮箱地址", value = "email", required = true, dataType = "String"),
            @ApiImplicitParam(name = "发送类型", value = "sendType", required = true, dataType = "Integer")
    })
    public ReturnDTO<String> sendEmail(@RequestBody SendEmailVo sendMailVo) {
        log.info("com.jdcloud.provider.web.fronted.UacAuthController.sendEmail:" + sendMailVo.toString());
        uacUserService.sendEmail(sendMailVo);
        return ReturnDTO.ok();
    }

    /**
     * phone 注册
     *
     * @param registerVo
     * @return
     */
    @PostMapping(value = "/register")
    @ApiOperation(value = "bit_trade注册", notes = "bit_trade注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "注册类型", value = "registerType", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "手机号区域码", value = "areaCode", required = false, dataType = "String"),
            @ApiImplicitParam(name = "手机号", value = "mobile", required = false, dataType = "String"),
            @ApiImplicitParam(name = "邮箱", value = "email", required = false, dataType = "String"),
            @ApiImplicitParam(name = "密码", value = "password", required = true, dataType = "String"),
            @ApiImplicitParam(name = "验证码", value = "code", required = true, dataType = "String"),
            @ApiImplicitParam(name = "昵称", value = "nickName", required = true, dataType = "String"),
            @ApiImplicitParam(name = "推荐码", value = "recommendCode", required = false, dataType = "String")
    })
    public ReturnDTO<String> register(@RequestBody UserRegisterVo registerVo) {
        log.info("com.jdcloud.provider.web.fronted.UacAuthController.registerPhone:" + registerVo.toString());
        uacUserService.register(registerVo);
        return ReturnDTO.ok();
    }

    /**
     * 找回登入密码
     *
     * @param findPwdVo
     * @return
     */
    @PostMapping(value = "/password/retrieve")
    @ApiOperation(value = "找回密码", notes = "找回密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "登陆名", value = "loginName", required = true, dataType = "String"),
            @ApiImplicitParam(name = "密码", value = "password", required = true, dataType = "String"),
            @ApiImplicitParam(name = "验证码", value = "code", required = true, dataType = "String"),
    })
    public ReturnDTO<String> findPwd(@RequestBody UserFindPwdVo findPwdVo) {
        log.info("com.jdcloud.provider.web.fronted.UacAuthController.findPass:" + findPwdVo.toString());
        uacUserService.findPwd(findPwdVo);
        return ReturnDTO.ok();
    }

    /**
     * 发送找回密码的验证码
     * 发送验证码:如果手机号绑定 优先发送短信验证码 ，如果没有手机号 则发送邮箱验证码
     *
     * @param loginName
     * @return
     */
    @GetMapping(value = "/send/retrieve/{loginName}")
    @ApiOperation(value = "发送找回密码验证码", notes = "发送找回密码验证码")
    @ApiImplicitParam(name = "登入名", value = "loginName", required = true, dataType = "String")
    public ReturnDTO<String> sendRetrieve(@PathVariable String loginName) {
        log.info("com.jdcloud.provider.web.fronted.UacAuthController.sendRetrieve:" + loginName);
        uacUserService.sendRetrieve(loginName);
        return ReturnDTO.ok();
    }

    @PostMapping(value = "/checkCode")
    @ApiOperation(value = "校验验证码", notes = "校验验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "设备码", value = "equipmentCode", required = true, dataType = "String"),
            @ApiImplicitParam(name = "验证码", value = "code", required = true, dataType = "String"),
            @ApiImplicitParam(name = "验证类型", value = "checkType", required = true, dataType = "Integer"),
    })
    public ReturnDTO<String> checkCode(@RequestBody CheckCodeVo checkCodeVo) {
        log.info("com.jdcloud.provider.web.fronted.UacAuthController.checkCode:" + checkCodeVo.toString());
        uacUserService.checkCode(checkCodeVo);
        return ReturnDTO.ok();
    }

}