package com.core.framework.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageDTO<T> {

	private int page;
	private int size;
	private int totalPage;
	private int totalSize;
	private List<T> data;

}
