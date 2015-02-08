package com.spring.config.pagination;

import java.util.List;

/**
 * 该类描述了一个分页数据集 list中是查询的数据集合，pageInfo则描述了附加的页相关的信息
 * @param <T>
 */
public class PageResultSet<T> {

    // 当前页的数据信息
    private List<T> list;

    // 当前页的信息
    private PageInfo pageInfo;
    
    public PageResultSet() {
        pageInfo = new PageInfo();
    }
    
    public PageResultSet(PageInfo pageInfo, List<T> list) {
        this.list = list;
        this.pageInfo = pageInfo;
    }
    
    public PageResultSet(int totalRow, int pageSize, int currentPage, List<T> list) {
        this.list = list;
        this.pageInfo = new PageInfo(totalRow, pageSize, currentPage);
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }
    
}
