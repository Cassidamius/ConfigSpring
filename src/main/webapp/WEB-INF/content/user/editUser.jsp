<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.10.1.min.js"></script>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<h2>编辑用户</h2>
<form:form id="editForm" commandName="userInfo" method="post" action="${pageContext.request.contextPath}/editUserInfo">
    <form:hidden path="id"/>
    <form:hidden path="version"/>
	<table>
		<tr>
			<td>用户名：</td>
			<td><form:input path="userName" /></td>
			<td>昵称：</td>
			<td><form:input path="nickName" /></td>
			<td>描述：</td>
			<td><form:input path="descn" /></td>
		</tr>
		<tr>
			<td>地址：</td>
			<td><form:input path="address" /></td>
			<td>手机：</td>
			<td><form:input path="mobile" /></td>
			<td>电话：</td>
			<td><form:input path="telephone" /></td>
		</tr>
		<tr colspan="6">
		<table>
		<tr>
		<th>选择</th>
		<th>角色</th>
		<th>角色说明</th>
		</tr>
		<c:forEach var="item" items="${rolesMap}" varStatus="statu" begin="0">
			<tr>
				<td>
				<input type="checkbox" name="roleIds" value="${item.key.id}" <c:if test="${item.value == 1}">checked="checked"</c:if>/>
				</td>
				<td>${item.key.name}</td>
				<td>${item.key.roleDesc}</td>
			</tr>
		</c:forEach>
		</table>
		</tr>
		<tr>
			<td colspan="6"><input type="button" value="修改" onclick="edit();" /></td>
		</tr>
	</table>
</form:form>