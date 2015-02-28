<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script language="javascript" type="text/javascript" src="js/jquery/jquery-1.10.1.min.js"></script>
<script>
	function add() {
		if ($("#name").val() == "") {
			alert("请输入角色名称");
			return false;
		}
		$("#addForm").submit();
	}
</script>
<h2>新增角色</h2>
<form:form id="addForm" modelAttribute="role" method="post" action="${pageContext.request.contextPath}/addRole">
	<table>
		<tr>
			<th>角色名：</th>
			<td><form:input path="name"/></td>
			<th>描述：</th>
			<td><form:input path="roleDesc"/></td>
		</tr>
		<tr colspan="4">
		<table>
		<tr>
		<th>选择</th>
		<th>资源名称</th>
		<th>资源类型</th>
		<th>资源</th>
		</tr>
		<c:forEach var="item" items="${resources}" varStatus="statu" begin="0">
			<tr>
				<td><input type="checkbox" name="resourceids" id="resourceids[${statu.index}]" value="${item.id}"/></td>
				<td>${item.name}</td>
				<td>${item.rescType}</td>
				<td>${item.rescString}</td>
			</tr>
		</c:forEach>
		</table>
		</tr>
		<tr>
			<td colspan="4"><input type="button" value="添加" onclick="add();" /></td>
		</tr>
	</table>
</form:form>