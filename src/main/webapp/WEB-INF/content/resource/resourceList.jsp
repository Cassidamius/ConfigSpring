<%@page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script language="javascript" type="text/javascript" src="js/jquery/jquery-1.10.1.min.js"></script>
<script language="javascript" type="text/javascript" src="js/common.js"></script>
<script>
function deleteItem(url, id) {
	$.ajax({
		url : url,
		data : {id : id, _csrf : $("#_csrf").val()},
		dataType : "json",
		type : "post",
		success : function(data) {
			alert(data.result);
			searchList();
		}
	});
}
</script>
<h2>资源列表</h2>
<form id="searchForm" method="post" action="${pageContext.request.contextPath}/findResourceList">
	<table>
		<tr>
			<td>资源名：</td>
			<td><input type="text" id="name" name="name" value="${resource.name}"/></td>
			<td>资源：</td>
			<td><input type="text" id="rescString" name="rescString" value="${resource.rescString}"/></td>
		</tr>
		<tr>
			<td colspan="4">
				<input type="button" value="查询" name="search" onclick="searchList();" />&nbsp;&nbsp; 
				<input type="button" value="清空" onclick="clean();" /> &nbsp;&nbsp;
				<input type="button" value="添加" onclick="toAddPage('${pageContext.request.contextPath}/toAddResourcePage');" />
			</td>
		</tr>
	</table>
	<input type="hidden" name="${_csrf.parameterName}" id="${_csrf.parameterName}" value="${_csrf.token}" />
	<input type="hidden" name="pageInfo.currentPage" id="currentPage"
		value="${pageResultSet.pageInfo.currentPage}"> <input type="hidden" name="pageInfo.pageSize"
		id="pageSize" value="${pageResultSet.pageInfo.pageSize}"> <input type="hidden"
		name="pageInfo.totalRow" id="totalRow" value="${pageResultSet.pageInfo.totalRow}">
</form>
<div <c:if test="${pageResultSet.list == null}">style="display:none"</c:if>>
<c:if test="${pageResultSet.list != null}">
	<table border="1">
		<tr>
		    <th>操作</th>
			<th>资源名</th>
			<th>资源类型</th>
			<th>资源</th>
		</tr>
		<c:forEach var="item" items="${pageResultSet.list}" varStatus="statu" begin="0">
			<tr>
			    <td><input type="button" name="modify" value="修改" onclick="toEditPage('${pageContext.request.contextPath}/toEditResourcePage/${item.id}');"/>&nbsp;&nbsp;
				<input type="button" name="delete" value="删除" onclick="deleteItem('${pageContext.request.contextPath}/deleteResource','${item.id}');"/></td>
				<td>${item.name}</td>
				<td>${item.rescType}</td>
				<td>${item.rescString}</td>
			</tr>
		</c:forEach>
	</table>
	</c:if>
	<%@ include file="../pagination.jsp"%>
</div>