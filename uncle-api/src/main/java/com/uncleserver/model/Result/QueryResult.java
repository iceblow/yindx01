package com.uncleserver.model.Result;

import java.io.Serializable;
import java.util.List;

/**
 * 分页查询封装类
 * 
 * @author wangcl
 *
 * @param <T>
 * 
 * @version 2017-01-23
 */
public class QueryResult<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<T> rows;
	private int records;
	private long total;
	private int page;
	
	public QueryResult(){
	}
	
	public QueryResult(List<T> dataList, Long total, int pageIndex, int pageSize) {
		if (total == null) {
			total = 0L;
		}
		this.rows = dataList;
		this.records = dataList==null?0:dataList.size();
		this.total = (total + pageSize - 1) / pageSize;
		this.page = pageIndex;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public int getRecords() {
		return records;
	}

	public void setRecords(int records) {
		this.records = records;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

}
