package com.liegq.www.bean;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 接收客户端发送消息实体
 * <p>
 * 创建人：leigq <br>
 * 创建时间：2018-11-19 11:07 <br>
 * <p>
 * 修改人： <br>
 * 修改时间： <br>
 * 修改备注： <br>
 * </p>
 */
@Data
@Accessors(chain = true)
@Builder
public class ClientMessage {

    private String message;

}
