package com.uncleserver.model.Result;

import java.io.Serializable;

/**
 * 分页查询封装类
 * 
 * @author wangcl
 * @version 2017-01-23
 */
public class PQuery implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int page;

	private int rows;

	public int getPage() {
		if (page == 0)
			return 1;
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		if (rows == 0)
			return 10;
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getStartPage() {
		return (getPage() - 1) * getRows();
	}
}
