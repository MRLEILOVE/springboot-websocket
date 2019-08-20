package com.walletbiz.vo;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * <p>
 * 参数配置表
 * </p>
 *
 * @author yongheng
 * @since 2018-10-25
 */
@TableName("t_parameter_config")
@Data
public class ParameterConfigVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 字典key
     */
    @TableField("dictionary_key")
    private String dictionaryKey;
    /**
     * 字典值
     */
    @TableField("dictionary_value")
    private String dictionaryValue;
    /**
     * 备注
     */
    @TableField("dictionary_remark")
    private String dictionaryRemark;
}
