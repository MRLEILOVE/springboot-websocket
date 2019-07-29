package com.td.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import lombok.Data;

/**
 * @description:
 * @date 2019/02/19 18:01:22
 * @author Rong.Jia
 */
@Data
//@MappedSuperclass
public class Base implements Serializable {

    private static final long serialVersionUID = -7519418012137093264L;

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    /**
     * 添加时间
     */
    protected Long createdTime;


    /**
     * 描述
     */
    protected String description;

}
