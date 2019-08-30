package com.bittrade.uac.web;

import com.bittrade.uac.model.dto.ReturnDTO;
import com.bittrade.uac.model.pojo.UserGesture;
import com.bittrade.uac.model.vo.CheckGestureVo;
import com.bittrade.uac.model.vo.GestureVo;
import com.bittrade.uac.service.UserGestureService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: xzc
 * @create: 2019-08-13 10:19
 * @description: 手势控制接口
 **/
@Slf4j
@RestController
@RequestMapping(value = "/gestures", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserGesturesController {
    @Autowired
    private UserGestureService gestureService;

    /**
     * 创建或更新手势密码
     *
     * @param gestureVo
     * @return
     */
    @PostMapping(value = "/create")
    @ApiOperation(value = "创建或更新手势密码", notes = "创建或更新手势密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, paramType = "long"),
            @ApiImplicitParam(name = "gestures", value = "手势密码", required = true, paramType = "string"),
            @ApiImplicitParam(name = "period", value = "验证周期", required = true, paramType = "integer"),
            @ApiImplicitParam(name = "periodUnit", value = "验证周期单位", required = true, paramType = "integer"),
            @ApiImplicitParam(name = "enabled", value = "是否启用", required = true, paramType = "boolean")
    })
    public ReturnDTO<UserGesture> create(GestureVo gestureVo) {
        boolean success = gestureService.create(gestureVo);
        if (success) {
            return ReturnDTO.ok();
        }
        return ReturnDTO.error("创建失败");
    }

    /**
     * 验证手势密码
     *
     * @param gestureVo
     * @return
     */
    @PostMapping(value = "/check")
    @ApiOperation(value = "验证手势密码", notes = "验证手势密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, paramType = "long"),
            @ApiImplicitParam(name = "gestures", value = "手势密码", required = true, paramType = "string")
    })
    public ReturnDTO<UserGesture> checkGesture(CheckGestureVo gestureVo) {
        boolean success = gestureService.checkGesture(gestureVo);
        if (success) {
            return ReturnDTO.ok();
        }
        return ReturnDTO.error("验证失败");
    }
}
