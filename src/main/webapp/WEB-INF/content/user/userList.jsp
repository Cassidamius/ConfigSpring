<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script language="javascript" type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery/jquery-1.10.1.min.js"></script>
<script language="javascript" type="text/javascript"
	src="${pageContext.request.contextPath}/js/common.js"></script>
<script>
	function deleteItem(url) {
		$.ajax({
			url : url,
			dataType : "json",
			type : "delete",
			//这里设置heade
			beforeSend : function(xhr) {
				xhr.setRequestHeader('X-CSRF-TOKEN', $("#_csrf").val());
			},
			success : function(data) {
				alert(data.result);
				searchList();
			}
		});
	}
</script>
<h2>用户列表</h2>
<form id="searchForm" method="post"
	action="${pageContext.request.contextPath}/user/list">
	<table>
		<tr>
			<td>用户名：</td>
			<td><input type="text" id="userName" name="userName"
				value="${userInfo.userName}" /></td>
			<td>昵称：</td>
			<td><input type="text" id="nickName" name="nickName"
				value="${userInfo.nickName}" /></td>
		</tr>
		<tr>
			<td colspan="4"><input type="button" value="查询" name="search"
				onclick="searchList();" />&nbsp;&nbsp; <input type="button"
				value="添加"
				onclick="toAddPage('${pageContext.request.contextPath}/user/add');" />
			</td>
		</tr>
	</table>
	<input type="hidden" name="${_csrf.parameterName}"
		id="${_csrf.parameterName}" value="${_csrf.token}" /> <input
		type="hidden" name="pageInfo.currentPage" id="currentPage"
		value="${pageResultSet.pageInfo.currentPage}"> <input
		type="hidden" name="pageInfo.pageSize" id="pageSize"
		value="${pageResultSet.pageInfo.pageSize}"> <input
		type="hidden" name="pageInfo.totalRow" id="totalRow"
		value="${pageResultSet.pageInfo.totalRow}">
</form>
<div
	<c:if test="${pageResultSet.list == null}">style="display:none"</c:if>>
	<c:if test="${pageResultSet.list != null}">
		<table border="1">
			<tr>
				<th>操作</th>
				<th>用户名</th>
				<th>昵称</th>
				<th>手机</th>
				<th>电话</th>
				<th>地址</th>
			</tr>
			<c:forEach var="item" items="${pageResultSet.list}" varStatus="statu"
				begin="0">
				<tr>
					<td><a
						href="${pageContext.request.contextPath}/user/${item.id}/edit"
						class="button gray">修改</a>&nbsp;&nbsp; <a
						href="javascript:void(0);"
						onclick="deleteItem('${pageContext.request.contextPath}/user/${item.id}');"
						class="button gray">删除</a>&nbsp;&nbsp; <a
						href="${pageContext.request.contextPath}/user/${item.id}"
						class="button gray">详情</a></td>
					<td><c:out value="${item.userName}" /></td>
					<td><c:out value="${item.nickName}" /></td>
					<td><c:out value="${item.mobile}" /></td>
					<td><c:out value="${item.telephone}" /></td>
					<td><c:out value="${item.address}" /></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<br />
	<%@ include file="../pagination.jsp"%>
</div>