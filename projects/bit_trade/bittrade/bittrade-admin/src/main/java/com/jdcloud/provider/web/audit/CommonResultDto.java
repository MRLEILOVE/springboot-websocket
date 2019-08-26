package com.jdcloud.provider.web.audit;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommonResultDto<T> {
    private Integer code;
    private String msg;
    private T data;



}
