package com.sise.cwh.estate.util;

import java.util.List;

public class Pagination {
	private int pageNo;//当前页数
	private int pageSize;//每页数量
	private int count;//总数量
	private String sec;//排序
	private String sort;//排序名
	@SuppressWarnings("rawtypes")
	private List list;
	
	public Pagination(int pageNo, int pageSize, String sec, String sort) {
		super();
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		//this.count = count;
		this.sec = sec;
		this.sort = sort;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@SuppressWarnings("rawtypes")
	public List getList() {
		return list;
	}
	@SuppressWarnings("rawtypes")
	public void setList(List list) {
		this.list = list;
	}
	public String getSec() {
		return sec;
	}
	public void setSec(String sec) {
		this.sec = sec;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public int getFirstResult(){//数据开始下标
		return getPageSize()*(getPageNo()-1);
	}
	public int getMaxResults(){//显示几条数据
		return getPageSize();
	}
	/**
	 * 总共页数
	 * @return
	 */
	public int getPageCount(){
		return (int)Math.ceil((double)getCount()/(double)getPageSize());
	}
}
