<%@page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script language="javascript" type="text/javascript" src="js/jquery/jquery-1.10.1.min.js"></script>
<script language="javascript" type="text/javascript" src="js/common.js"></script>

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
				<input type="button" value="添加" onclick="toAddPage('/toAddUserPage');" />
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
			<th>操作</th>
			<th>用户名</th>
			<th>昵称</th>
			<th>手机</th>
			<th>电话</th>
			<th>地址</th>
		</tr>
		<c:forEach var="item" items="${pageResultSet.list}" varStatus="statu" begin="0">
			<tr>
				<td><input type="button" name="modify" value="修改" onclick="toEditPage('${pageContext.request.contextPath}/toEditUserPage', '${item.id}');"/>&nbsp;&nbsp;
				<input type="button" name="delete" value="删除" onclick="deleteItem('${pageContext.request.contextPath}/deleteUser', '${item.id}');"/></td>
				<td>${item.userName}</td>
				<td>${item.nickName}</td>
				<td>${item.mobile}</td>
				<td>${item.telephone}</td>
				<td>${item.address}</td>
			</tr>
		</c:forEach>
	</table>
	</c:if>
	<%@ include file="../pagination.jsp"%>
</div>