<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
<c:forEach var="role" begin="0" items="${sessionUserInfo.roles}">
<c:forEach var="resource" begin="0" items="${role.resources}">
<c:if test="${resource.rescType == 'MENU'}">
<a href="${pageContext.request.contextPath}${resource.rescString}">${resource.name}</a><br/>
</c:if>
</c:forEach>
</c:forEach>
</div>