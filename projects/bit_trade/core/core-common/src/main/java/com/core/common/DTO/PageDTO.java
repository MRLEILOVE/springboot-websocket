package com.core.common.DTO;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
//@lombok.NoArgsConstructor
@AllArgsConstructor
public class PageDTO<T> implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private int page;
	private int size;
	private int totalPage;
	private int totalSize;
	private List<T> data;

	public PageDTO() {
		super();
	}
	
	public PageDTO(int page, int size) {
		super();
		this.page = page;
		this.size = size;
	}

}
