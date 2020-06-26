package cn.itcast.utils;

import java.util.List;

public class PageBean<T> {

	//��e��
	private int currentPage = 1;
	//�C����ܪ����
	private int pageCount = 6;
	//�`������
	private int totalCount;
	//�`����
	private int totalPage;
	//�C�����ƾ�
	private List<T> pageData;
	//�ʸ˩Ҧ����d�߱���
	private Condition condition;
	
	public int getTotalPage() {
		//�`���� = �`���� / �C����Ʀ�� +1
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
