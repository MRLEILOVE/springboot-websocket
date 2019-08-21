package com.bittrade.pojo.vo;

import com.core.framework.base.VO.BaseVO;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @author Administrator
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TChatRecordLogVO extends BaseVO<TChatRecordLogVO> {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Long senderId;
	private Long receiverId;
	private Byte[] content;
	private Byte sendType;
	private Byte readed;
	private java.time.LocalDateTime createTime;
	private java.time.LocalDateTime updateTime;

}
