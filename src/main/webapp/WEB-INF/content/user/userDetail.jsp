<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script language="javascript" type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery/jquery-1.10.1.min.js"></script>
<script language="javascript" type="text/javascript"
	src="${pageContext.request.contextPath}/js/common.js"></script>
<h2>用户详情</h2>
<table>
	<tr>
		<td>用户名：</td>
		<td><form:input path="userInfo.userName" /></td>
		<td>昵称：</td>
		<td><form:input path="userInfo.nickName" /></td>
		<td>地址：</td>
		<td><form:input path="userInfo.address" /></td>
	</tr>
	<tr>
		<td>电话：</td>
		<td><form:input path="userInfo.telephone" /></td>
		<td>手机：</td>
		<td colspan="3"><form:input path="userInfo.mobile" /></td>
	</tr>
	<tr colspan="6">
		<table border="1">
			<tr>
				<th>角色</th>
				<th>角色说明</th>
			</tr>
			<c:forEach var="item" items="${userInfo.roles}">
				<tr>
					<td>${item.name}</td>
					<td>${item.roleDesc}</td>
				</tr>
			</c:forEach>
		</table>
	</tr>
</table>