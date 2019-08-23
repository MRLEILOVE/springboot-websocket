package com.bittrade.admin.model.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author xzc
 * @date 2019-08-22 16:24
 * @description 广告页面查询结果集
 */
@Getter
@Setter
@ToString
public class AdvertInfoPageDo {


    private String loginName;

    private String nick_name;

    private String message;
}
