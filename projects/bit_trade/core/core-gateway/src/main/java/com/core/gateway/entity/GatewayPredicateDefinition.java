package com.core.gateway.entity;

import java.util.LinkedHashMap;
import java.util.Map;

import lombok.Data;

/**
 * 路由断言定义模型
 */
@Data
public class GatewayPredicateDefinition {

    /**
     * 断言对应的Name
     */
    private String name;

    /**
     * 配置的断言规则
     */
    private Map<String, String> args = new LinkedHashMap<>();
}