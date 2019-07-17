package com.core.framework.DTO;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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

}
