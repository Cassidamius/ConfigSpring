<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script language="javascript" type="text/javascript" src="js/jquery/jquery-1.10.1.min.js"></script>
<script>
	function add() {
		$("#addForm").submit();
	}
</script>
<h2>新增用户</h2>
<form id="inputForm">
	<table>
		<tr>
			<td>用户名：</td>
			<td><input type="text" name="userInfo.userName" /></td>
			<td>昵称：</td>
			<td><input type="text" name="userInfo.nickName" /></td>
			<td>地址：</td>
			<td><input type="text" name="userInfo.address" /></td>
		</tr>
		<tr>
			<td>电话：</td>
			<td><input type="text" name="userInfo.telephone" /></td>
			<td>手机：</td>
			<td colspan="3"><input type="text" name="userInfo.mobile" /></td>
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
				<td><input type="checkbox" name="userInfo.roles.id" id="roles[${statu.index}]" value="${item.id}"/></td>
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
</form>
<form id="addForm" method="post" action="${pageContext.request.contextPath}/addUserInfo">
	<input type="hidden" name="userInfo.userName" />
	<input type="hidden" name="userInfo.nickName" />
	<input type="hidden" name="userInfo.address" />
	<input type="hidden" name="userInfo.telephone" />
	<input type="hidden" name="userInfo.mobile" />
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>