<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<title>登录</title>
</head>
<body>
<span style="color:red">${error}</span>
	<form:form commandName="userInfo" method="post" action="${pageContext.request.contextPath}/login">
		<table>
			<tr>
                <td>姓名：</td>
                <td><form:input path="userName" /></td>
            </tr>
            <tr>
                <td>密码：</td>
                <td><form:password path="password" /></td>
            </tr>
			<tr>
				<td colspan="2"><input type="submit" value="提交" /></td>
			</tr>
		</table>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	</form:form>
</body>
</html>