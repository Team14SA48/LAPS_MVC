package sg.edu.nus.lapsystem.model;

public class Page {
	private int currentPage=1;
	private int totalPages;
	private int totalLeaves;
	private int pageSize;
	private int nextPage;
	private int prePage;
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalPages() {
		totalPages=totalLeaves%pageSize==0?totalLeaves/pageSize:totalLeaves/pageSize+1;
		return totalPages;
	}
	
	
	public int getTotalLeaves() {
		return totalLeaves;
	}
	public void setTotalLeaves(int totalLeaves) {
		this.totalLeaves = totalLeaves;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getNextPage() {
		if(currentPage<totalPages)
			nextPage=currentPage+1;
		else
			nextPage=currentPage;
		return nextPage;
	}

	public int getPrePage() {
		if(currentPage>1)
			prePage=currentPage-1;
		else
			prePage=currentPage;
		return prePage;
	}


}
