<%@page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script language="javascript" type="text/javascript" src="js/jquery/jquery-1.10.1.min.js"></script>
<script language="javascript" type="text/javascript" src="js/common.js"></script>

<h2>编辑用户</h2>
<form:form id="editForm" commandName="userInfo" method="post" action="${pageContext.request.contextPath}/editUserInfo">
    <form:hidden path="id"/>
    <form:hidden path="createTime"/>
    <form:hidden path="deleteFlag"/>
    <form:hidden path="version"/>
    <form:hidden path="password"/>
    <form:hidden path="status"/>
	<table>
		<tr>
			<td>用户名：</td>
			<td><form:input path="userName" /></td>
			<td>昵称：</td>
			<td><form:input path="nickName" /></td>
			<td>姓名拼音：</td>
			<td id="pinyin">${namePinyin}</td>
		</tr>
		<tr>
			<td>地址：</td>
			<td><form:input path="address" /></td>
			<td>手机：</td>
			<td><form:input path="mobile" /></td>
			<td>电话：</td>
			<td><form:input path="telephone" /></td>
		</tr>
		<tr>
			<td colspan="6"><input type="button" value="修改" onclick="edit();" /></td>
		</tr>
	</table>
</form:form>