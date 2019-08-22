package com.admin.bittrade.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author xzc
 * @date 2019-08-22 11:28
 * @description
 */
@Getter
@Setter
@ToString
public class PageParam {

    /**
     * 行数
     */
    private Integer pageSize;

    /**
     * 页数
     */
    private Integer pageNum;
}
