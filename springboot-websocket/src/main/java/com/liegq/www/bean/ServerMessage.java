package com.liegq.www.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServerMessage {

    private String responseMessage;

}
