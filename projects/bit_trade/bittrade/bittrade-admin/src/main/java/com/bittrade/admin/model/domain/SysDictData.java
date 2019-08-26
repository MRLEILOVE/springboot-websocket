	package com.bittrade.admin.model.domain;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.bittrade.admin.annotation.Excel;
import com.bittrade.admin.model.domain.base.BaseEntity;

/**
 * <p>
 * 字典数据表
 * </p>
 *
 * @author ourblue
 * @since 2018-11-09
 */
@TableName("sys_dict_data")
public class SysDictData extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 字典编码
     */
    @TableId(value = "dict_code", type = IdType.AUTO)
    @Excel(name = "字典编码")
    private Integer dictCode;
    /**
     * 字典排序
     */
    @TableField("dict_sort")
    private Integer dictSort;
    /**
     * 字典标签
     */
    @TableField("dict_label")
    @Excel(name = "字典标签")
    private String dictLabel;
    /**
     * 字典键值
     */
    @TableField("dict_value")
    @Excel(name = "字典键值")
    private String dictValue;
    /**
     * 字典类型
     */
    @TableField("dict_type")
    private String dictType;
    /**
     * 样式属性（其他样式扩展）
     */
    @TableField("css_class")
    @Excel(name = "字典样式")
    private String cssClass;
    /**
     * 表格回显样式
     */
    @TableField("list_class")
    private String listClass;
    /**
     * 是否默认（Y是 N否）
     */
    @TableField("is_default")
    private String isDefault;
    /**
     * 状态（1正常 停0用）
     */
    private String status;
    /**
     * 创建者
     */
    @TableField("create_by")
    private String createBy;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 更新者
     */
    @TableField("update_by")
    private String updateBy;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;
    /**
     * 备注
     */
    private String remark;


    public Integer getDictCode() {
        return dictCode;
    }

    public void setDictCode(Integer dictCode) {
        this.dictCode = dictCode;
    }

    public Integer getDictSort() {
        return dictSort;
    }

    public void setDictSort(Integer dictSort) {
        this.dictSort = dictSort;
    }

    public String getDictLabel() {
        return dictLabel;
    }

    public void setDictLabel(String dictLabel) {
        this.dictLabel = dictLabel;
    }

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    public String getCssClass() {
        return cssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    public String getListClass() {
        return listClass;
    }

    public void setListClass(String listClass) {
        this.listClass = listClass;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "SysDictData{" +
        "dictCode=" + dictCode +
        ", dictSort=" + dictSort +
        ", dictLabel=" + dictLabel +
        ", dictValue=" + dictValue +
        ", dictType=" + dictType +
        ", cssClass=" + cssClass +
        ", listClass=" + listClass +
        ", isDefault=" + isDefault +
        ", status=" + status +
        ", createBy=" + createBy +
        ", createTime=" + createTime +
        ", updateBy=" + updateBy +
        ", updateTime=" + updateTime +
        ", remark=" + remark +
        "}";
    }
}
