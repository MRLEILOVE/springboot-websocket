package com.bittrade.c2c.controller;

import com.bittrade.c2c.service.ChatRecordLogService;
import com.bittrade.c2c.vo.SendOrderVo;
import com.bittrade.c2c.vo.SendVo;
import com.core.common.DTO.ReturnDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author xzc
 * @date 2019-08-19 17:17
 * @description 聊天
 */
@RestController
public class ChatController {

    @Autowired
    private ChatRecordLogService chatRecordLogService;

    /**
     * 推送订单详情
     *
     * @param sendOrderVo
     * @return
     */
    @PostMapping("/sendOrder")
    @ApiOperation(value = "拉取订单详情", notes = "拉取订单详情")
    public ReturnDTO pullOrderDetail(SendOrderVo sendOrderVo) {
        chatRecordLogService.pullOrderDetail(sendOrderVo);
        return ReturnDTO.ok("发送成功");
    }

    /**
     * 发送聊天信息
     *
     * @param sendVo
     * @return
     */
    @PostMapping("/sendMessage")
    @ApiOperation(value = "发送聊天信息", notes = "发送聊天信息")
    public ReturnDTO sendMessage(SendVo sendVo) {
        chatRecordLogService.sendMessage(sendVo);
        return ReturnDTO.ok("发送成功");
    }

    /**
     * 拉取指定用户的消息
     *
     * @param receiverId 接收者
     */
    @PostMapping("/pullUnreadMessage")
    @ApiOperation(value = "拉取指定用户的聊天记录", notes = "拉取指定用户的聊天记录")
    public ReturnDTO pullMessage(Long receiverId) {
        long unreadCount = chatRecordLogService.pullUnreadMessage(receiverId);
        return ReturnDTO.ok(unreadCount);
    }

}
