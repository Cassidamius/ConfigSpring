<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script language="javascript" type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery/jquery-1.10.1.min.js"></script>
<script language="javascript" type="text/javascript"
	src="${pageContext.request.contextPath}/js/common.js"></script>
<h2>编辑资源</h2>
<form:form id="editForm" commandName="resource" method="post"
	action="${pageContext.request.contextPath}/editResource">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<table>
		<tr>
			<td>资源名：</td>
			<td><form:input path="name" /></td>
			<td>资源类型：</td>
			<td><form:select path="rescType">
					<form:options items="${rescTypes}" itemLabel="descn"
						itemValue="code" />
				</form:select></td>
			<td>描述：</td>
			<td><form:input path="descn" /></td>
		</tr>
		<tr>
			<td colspan="6"><input type="button" value="修改"
				onclick="edit();" /></td>
		</tr>
	</table>
</form:form>