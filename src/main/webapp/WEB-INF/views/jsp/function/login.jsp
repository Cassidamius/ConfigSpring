<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<title>登录</title>
</head>
<body>
	<form:form commandName="userInfo" method="post" action="${pageContext.request.contextPath}/j_spring_security_check">
		<table>
			<tr>
                <td>姓名jsp：</td>
                <td><form:input path="userName" />
            </tr>
            <tr>
                <td>密码：</td>
                <td><form:password path="password" />
            </tr>
			<tr>
				<td colspan="2"><input type="submit" value="提交" /></td>
			</tr>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		</table>
	</form:form>
</body>
</html>