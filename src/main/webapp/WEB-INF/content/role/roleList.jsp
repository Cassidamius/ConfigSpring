<%@page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script language="javascript" type="text/javascript" src="js/jquery/jquery-1.10.1.min.js"></script>
<script>

function searchList() {
	$("#searchForm").submit();
}

function changePage(currentPage,totalRow) {
	//alert("currentPage:" + $("#currentPage").val());
	$("#currentPage").val(currentPage);
	$("#pageSize").val($("#pageSizeSelect option:selected").val());
	$("#totalRow").val(totalRow);
	//alert("currentPage:" + $("#currentPage").val());
	$("#searchForm").attr("method", "post");
	$("#searchForm").submit();
}

function toAddRolePage() {
	$("#searchForm").attr("action", "${pageContext.request.contextPath}/toAddRolePage");
	$("#searchForm").submit();
}

</script>
<h2>角色列表</h2>
<form id="searchForm" method="post" action="${pageContext.request.contextPath}/findRoleList">
	<table>
		<tr>
			<td>角色名：</td>
			<td><input type="text" id="name" name="name" value="${role.name}"/></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="button" value="查询" name="search" onclick="searchList();" />&nbsp;&nbsp; 
				<input type="button" value="清空" onclick="clean();" /> &nbsp;&nbsp;
				<input type="button" value="添加" onclick="toAddRolePage();" />
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
			<th>角色名</th>
			<th>描述</th>
		</tr>
		<c:forEach var="item" items="${pageResultSet.list}" varStatus="statu" begin="0">
			<tr>
				<td>${item.name}</td>
				<td>${item.roleDesc}</td>
			</tr>
		</c:forEach>
	</table>
	</c:if>
	<%@ include file="../pagination.jsp"%>
</div>