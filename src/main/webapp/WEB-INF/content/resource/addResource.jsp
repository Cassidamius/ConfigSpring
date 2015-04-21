<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script language="javascript" type="text/javascript"
	src="js/jquery/jquery-1.10.1.min.js"></script>
<script language="javascript" type="text/javascript" src="js/common.js"></script>
<script>
	function add() {
		$("#addForm").submit();
	}
</script>
<h2>新增用户</h2>
<form:form id="addForm" commandName="resource" method="post"
	action="${pageContext.request.contextPath}/addResource">
	<table>
		<tr>
			<td>资源名：</td>
			<td><form:input path="name" /></td>
			<td>资源类型：</td>
			<td><form:select path="rescType">
					<form:options items="${rescTypes}" itemLabel="descn"
						itemValue="code" />
				</form:select></td>
			<td>资源：</td>
			<td><form:input path="rescString" /></td>
		</tr>
		<tr>
			<td colspan="6"><input type="button" value="添加" onclick="add();" /></td>
		</tr>
	</table>
</form:form>