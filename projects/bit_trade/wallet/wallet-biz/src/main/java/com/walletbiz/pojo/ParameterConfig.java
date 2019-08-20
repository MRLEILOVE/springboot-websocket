package com.walletbiz.pojo;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 参数配置表
 * </p>
 *
 * @author yongheng
 * @since 2018-10-25
 */
@TableName("t_parameter_config")
@NoArgsConstructor
@AllArgsConstructor
public class ParameterConfig implements Serializable {

	private static final long serialVersionUID = 1L;

	public ParameterConfig(String key, String value, String updater) {
		this.dictionaryKey = key;
		this.dictionaryValue = value;
		this.updater = updater;
	}

	/**
	 * 主键id
	 */
	@TableId(value = "dictionary_id", type = IdType.AUTO)
	private Long	dictionaryId;
	/**
	 * 字典key
	 */
	@TableField("dictionary_key")
	private String	dictionaryKey;
	/**
	 * 字典值
	 */
	@TableField("dictionary_value")
	private String	dictionaryValue;
	/**
	 * 备注
	 */
	@TableField("dictionary_remark")
	private String	dictionaryRemark;
	/**
	 * creater
	 */
	private String	creater;
	/**
	 * 创建时间
	 */
	@TableField("create_time")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date	createTime;
	/**
	 * 修改人
	 */
	private String	updater;
	/**
	 * 修改时间
	 */
	@TableField("update_time")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date	updateTime;

	public Long getDictionaryId() {
		return dictionaryId;
	}

	public void setDictionaryId(Long dictionaryId) {
		this.dictionaryId = dictionaryId;
	}

	public String getDictionaryKey() {
		return dictionaryKey;
	}

	public void setDictionaryKey(String dictionaryKey) {
		this.dictionaryKey = dictionaryKey;
	}

	public String getDictionaryValue() {
		return dictionaryValue;
	}

	public void setDictionaryValue(String dictionaryValue) {
		this.dictionaryValue = dictionaryValue;
	}

	public String getDictionaryRemark() {
		return dictionaryRemark;
	}

	public void setDictionaryRemark(String dictionaryRemark) {
		this.dictionaryRemark = dictionaryRemark;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdater() {
		return updater;
	}

	public void setUpdater(String updater) {
		this.updater = updater;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "ParameterConfig{" + "dictionaryId=" + dictionaryId + ", dictionaryKey=" + dictionaryKey + ", dictionaryValue=" + dictionaryValue
				+ ", dictionaryRemark=" + dictionaryRemark + ", creater=" + creater + ", createTime=" + createTime + ", updater=" + updater
				+ ", updateTime=" + updateTime + "}";
	}
}
