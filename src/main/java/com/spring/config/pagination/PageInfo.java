package com.spring.config.pagination;

/**
 * 该类描述了分页记录集中的关于页的信息
 */
public class PageInfo {
    
    private static final int MAGIC_NUM = 10;

    /**
     * 总共记录数
     */
    private int totalRow = 0;

    /**
     * 总共页数
     */
    private int totalPage = 0;

    /**
     * 当前页
     */
    private int currentPage = 0;

    /**
     * 每页记录数
     */
    private int pageSize = MAGIC_NUM;
    
    public PageInfo() {
        pageSize = MAGIC_NUM;
        totalRow = 0;
        currentPage = 0;
    }

    /**
     * 构造方法
     * 
     * @param totalRow
     *            总记录数
     * 
     * @param pageSize
     *            页的大小
     * 
     * @param currentPage
     *            页码
     */
    public PageInfo(int totalRow, int pageSize, int currentPage) {
        this.totalRow = totalRow;
        this.pageSize = pageSize;
        countTotalPage();
        countCurrentPage(currentPage);
    }

    private void countCurrentPage(int currentPage) {
        if (currentPage > totalPage) {
            this.currentPage = this.totalPage;
        } else if (currentPage < 1) {
            this.currentPage = 1;
        } else {
            this.currentPage = currentPage;
        }
    }
    
    // 计算总页数
    public void countTotalPage() {
        totalPage = totalRow % pageSize == 0 ? totalRow / pageSize : totalRow / pageSize + 1;
    }
    
    public int countOffset() {
        int offset = pageSize * (currentPage - 1);
        return offset < 1 ? 0 : offset;
    }

    public int getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(int totalRow) {
        this.totalRow = totalRow;
    }
    
    public int getTotalPage() {
        countTotalPage();
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
    
    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    
    public void setPageSize(String pageSize) {
        boolean b = pageSize == null || pageSize.trim().length() == 0 ? true : false;
        this.pageSize = b ? 0 : Integer.parseInt(pageSize);
    }
    
    public void setTotalRow(String totalRow) {
        boolean b = totalRow == null || totalRow.trim().length() == 0 ? true :false;
        this.totalRow = b ? 0 : Integer.parseInt(totalRow);
    }
    
    public void setCurrentPage(String currentPage) {
        boolean b = currentPage == null || currentPage.trim().length() == 0 ? true : false;
        this.currentPage = b ? 0 : Integer.parseInt(currentPage);
    }

}
