package com.spring.config.controller;

import com.spring.config.pagination.PageResultSet;

public class PageController<T> {

	protected PageResultSet<T> pageResultSet;

    public PageResultSet<T> getPageResultSet() {
        return pageResultSet;
    }

    public void setPageResultSet(PageResultSet<T> pageResultSet) {
        this.pageResultSet = pageResultSet;
    }
    
}
