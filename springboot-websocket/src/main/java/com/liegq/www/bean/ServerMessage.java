package com.liegq.www.bean;

import lombok.Data;

/**
 *  服务端发送消息实体
 * <p>
 * 创建人：leigq <br>
 * 创建时间：2018-11-19 10:44 <br>
 * <p>
 * 修改人： <br>
 * 修改时间： <br>
 * 修改备注： <br>
 * </p>
 */
@Data
public class ServerMessage {

    private String responseMessage;

    public ServerMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
