package cn.itcast.utils;

import java.util.List;

public class PageBean<T> {

	//讽玡
	private int currentPage = 1;
	//–陪ボ︽计
	private int pageCount = 6;
	//羆魁计
	private int totalCount;
	//羆计
	private int totalPage;
	//–计沮
	private List<T> pageData;
	//杆┮Τ琩高兵ン
	private Condition condition;
	
	public int getTotalPage() {
		//羆计 = 羆魁 / –陪计︽计 +1
		if(totalCount % pageCount == 0){
			totalPage = totalCount / pageCount;
		}else{
			totalPage = totalCount / pageCount + 1;
		}
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	public List<T> getPageData() {
		return pageData;
	}
	public void setPageData(List<T> pageData) {
		this.pageData = pageData;
	}
	public Condition getCondition() {
		return condition;
	}
	public void setCondition(Condition condition) {
		this.condition = condition;
	}
	
	
}
