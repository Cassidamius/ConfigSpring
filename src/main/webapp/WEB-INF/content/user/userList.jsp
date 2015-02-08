<%@page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script language="javascript" type="text/javascript" src="js/jquery/jquery-1.10.1.min.js"></script>
<script>

function searchList() {
	$("#searchForm").submit();
}

function changePage(currentPage,totalRow) {
	alert("currentPage:" + $("#currentPage").val());
	$("#currentPage").val(currentPage);
	$("#pageSize").val($("#pageSizeSelect option:selected").val());
	$("#totalRow").val(totalRow);
	alert("currentPage:" + $("#currentPage").val());
	$("#searchForm").submit();
}

function toAddUserPage() {
	$("#searchForm").attr("action", "${pageContext.request.contextPath}/toAddUserPage");
	$("#searchForm").submit();
}

</script>
<h2>用户列表</h2>
<form id="searchForm" method="post" action="${pageContext.request.contextPath}/findUserList">
	<table>
		<tr>
			<td>用户名：</td>
			<td><input type="text" id="userName" name="userName" value="${userInfo.userName}"/></td>
			<td>昵称：</td>
			<td><input type="text" id="nickName" name="nickName" value="${userInfo.nickName}"/></td>
		</tr>
		<tr>
			<td colspan="4">
				<input type="button" value="查询" name="search" onclick="searchList();" />&nbsp;&nbsp; 
				<input type="button" value="清空" onclick="clean();" /> &nbsp;&nbsp;
				<input type="button" value="添加" onclick="toAddUserPage();" />
			</td>
		</tr>
	</table>
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	<input type="hidden" name="pageInfo.currentPage" id="currentPage"
		value="${pageResultSet.pageInfo.currentPage}"> <input type="hidden" name="pageInfo.pageSize"
		id="pageSize" value="${pageResultSet.pageInfo.pageSize}"> <input type="hidden"
		name="pageInfo.totalRow" id="totalRow" value="${pageResultSet.pageInfo.totalRow}">
</form>
<div <c:if test="${pageResultSet.list == null}">style="display:none"</c:if>>
<c:if test="${pageResultSet.list != null}">
	<table border="1">
		<tr>
			<th>用户名</th>
			<th>昵称</th>
		</tr>
		<c:forEach var="item" items="${pageResultSet.list}" varStatus="statu" begin="0">
			<tr>
				<td>${item.userName}</td>
				<td>${item.nickName}</td>
			</tr>
		</c:forEach>
	</table>
	</c:if>
	<%@ include file="../pagination.jsp"%>
</div>