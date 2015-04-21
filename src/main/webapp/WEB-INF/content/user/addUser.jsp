<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.10.1.min.js"></script>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<h2>新增用户</h2>
<form:form id="addForm" commandName="userInfo" ENCTYPE="application/x-www-form-urlencoded" method="post" action="${pageContext.request.contextPath}/addUserInfo">
	<table>
		<tr>
			<td>用户名：</td>
			<td><form:input path="userName" /></td>
			<td>昵称：</td>
			<td><form:input path="nickName" /></td>
			<td>地址：</td>
			<td><form:input path="address" /></td>
		</tr>
		<tr>
			<td>电话：</td>
			<td><form:input path="telephone" /></td>
			<td>手机：</td>
			<td colspan="3"><form:input path="mobile" /></td>
		</tr>
		<tr colspan="6">
		<table>
		<tr>
		<th>选择</th>
		<th>角色</th>
		<th>角色说明</th>
		</tr>
		<c:forEach var="item" items="${roles}" varStatus="statu" begin="0">
			<tr>
				<td><form:checkbox path="roleIds" value="${item.id}"/></td>
				<td>${item.name}</td>
				<td>${item.roleDesc}</td>
			</tr>
		</c:forEach>
		</table>
		</tr>
		<tr>
			<td colspan="6"><input type="button" value="添加" onclick="add();" /></td>
		</tr>
	</table>
</form:form>