package com.wallet.biz.vo;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommonResultDto<T> {
    private Integer code;
    private String msg;
    private T data;

    /**
     * 返回成功的结果
     *
     * @param data 需返回的结果
     * @param <T>
     * @return
     */
    public static <T> CommonResultDto<T> SUCCESS_DATA(T data) {
        return CommonResultDto.<T>builder()
                .code(ErrorConstant.SUCCESS)
                .msg("请求成功")
                .data(data)
                .build();
    }

    /**
     * 返回自定义成功
     *
     * @param <T>
     * @return
     */
    public static <T> CommonResultDto<T> SUCCESS_MSG(String msg) {
        return CommonResultDto.<T>builder()
                .code(ErrorConstant.SUCCESS)
                .msg(msg)
                .build();
    }

    /**
     * 返回成功的结果
     *
     * @param <T>
     * @return
     */
    public static <T> CommonResultDto<T> SUCCESS() {
        return CommonResultDto.<T>builder()
                .code(ErrorConstant.SUCCESS)
                .msg("请求成功")
                .build();
    }

    /**
     * 返回失败的结果 PS：返回"未知异常"
     *
     * @param <T>
     * @return
     */
    public static <T> CommonResultDto<T> FAILURE() {
        return CommonResultDto.<T>builder()
                .code(ErrorConstant.FAILURE)
                .msg("请求失败")
                .build();
    }

    /**
     * 返回失败的结果 PS：返回"未知异常"
     *
     * @param <T>
     * @return
     */
    public static <T> CommonResultDto<T> FAILURE(String msg) {
        return CommonResultDto.<T>builder()
                .code(ErrorConstant.FAILURE)
                .msg(msg)
                .build();
    }

    /**
     * 自定义错误码返回
     *
     * @param <T>
     * @return
     */
    public static <T> CommonResultDto<T> FAILURE(Integer code, String msg) {
        return CommonResultDto.<T>builder()
                .code(code)
                .msg(msg)
                .build();
    }

    /**
     * 自定义
     *
     * @param code
     * @param msg
     * @param data
     * @param <T>
     * @return
     */
    public static <T> CommonResultDto<T> INSTANCE(Integer code, String msg, T data) {
        return CommonResultDto.<T>builder()
                .code(code)
                .msg(msg)
                .data(data)
                .build();
    }

}
