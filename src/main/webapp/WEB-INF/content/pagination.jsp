<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
共${pageResultSet.pageInfo.totalRow}条，共${pageResultSet.pageInfo.totalPage}页；当前第${pageResultSet.pageInfo.currentPage}页；每页显示：
	<select id="pageSizeSelect">
	<option value="10" <c:if test="${pageResultSet.pageInfo.pageSize == 10}">selected</c:if>>10</option>
	<option value="20" <c:if test="${pageResultSet.pageInfo.pageSize == 20}">selected</c:if>>20</option>
	<option value="30" <c:if test="${pageResultSet.pageInfo.pageSize == 30}">selected</c:if>>30</option>
	<option value="40" <c:if test="${pageResultSet.pageInfo.pageSize == 40}">selected</c:if>>40</option>
	<option value="50" <c:if test="${pageResultSet.pageInfo.pageSize == 50}">selected</c:if>>50</option>
	</select>条；跳转到第<input type="text" id="thePage" name="thePage" size="3"/>页；
	<c:if test="${pageResultSet.pageInfo.currentPage == 1 && pageResultSet.pageInfo.totalPage == 1}">首页上一页下一页末页</c:if>
	<c:if test="${pageResultSet.pageInfo.currentPage == 1 && pageResultSet.pageInfo.totalPage > 1}">首页上一页<a href="javascript:changePage(${pageResultSet.pageInfo.currentPage + 1},${pageResultSet.pageInfo.totalRow});">下一页</a><a href="javascript:changePage(${pageResultSet.pageInfo.totalPage},${pageResultSet.pageInfo.totalRow});">末页</a></c:if>
	<c:if test="${pageResultSet.pageInfo.currentPage > 1 && pageResultSet.pageInfo.totalPage > pageResultSet.pageInfo.currentPage}"><a href="javascript:changePage(0,${pageResultSet.pageInfo.totalRow});">首页</a><a href="javascript:changePage(${pageResultSet.pageInfo.currentPage - 1},${pageResultSet.pageInfo.totalRow});">上一页</a><a href="javascript:changePage(${pageResultSet.pageInfo.currentPage + 1},${pageResultSet.pageInfo.totalRow});">下一页</a><a href="javascript:changePage(${pageResultSet.pageInfo.totalPage},${pageResultSet.pageInfo.totalRow});">末页</a></c:if>
	<c:if test="${pageResultSet.pageInfo.currentPage > 1 && pageResultSet.pageInfo.totalPage == pageResultSet.pageInfo.currentPage}"><a href="javascript:changePage(0,${pageResultSet.pageInfo.totalRow});">首页</a><a href="javascript:changePage(${pageResultSet.pageInfo.currentPage - 1},${pageResultSet.pageInfo.totalRow});">上一页</a>下一页末页</c:if>