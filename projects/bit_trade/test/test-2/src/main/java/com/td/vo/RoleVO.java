package com.td.vo;

import com.td.domain.Base;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @description 角色返回参数对象
 * @author 
 * @date 
 */
@Setter
@Getter
@ToString
public class RoleVO extends Base {



    /**
     * 角色名(中文)
     */
    private String name;

    /**
     * 角色名
     */
    private String role;
}
