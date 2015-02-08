<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script language="javascript" type="text/javascript" src="js/jquery/jquery-1.10.1.min.js"></script>
<script>
	function addUser() {
		$("#addForm").submit();
	}
</script>
<h2>新增用户</h2>
<form:form id="addForm" commandName="userInfo" method="post" action="${pageContext.request.contextPath}/addUserInfo">
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
		<tr>
			<td colspan="6"><input type="button" value="添加" onclick="addUser();" /></td>
		</tr>
	</table>
</form:form>