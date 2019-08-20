package com.wallet.biz.vo;

import lombok.Data;

import java.util.List;
@Data
public class PageVo<T> {
    private int current;
    private int size;
    private  Long pages;
    private Long total;
    private List<T> records;

}
