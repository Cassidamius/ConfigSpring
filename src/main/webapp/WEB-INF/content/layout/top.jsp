<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<h1 class="logo">测试系统</h1>
<ul class="do">
  <li>欢迎<span id="top-nickName"><a href="${pageContext.request.contextPath}/getUserInfoById/${sessionUserInfo.id}">${sessionUserInfo.userName}</a></span></li>
  <li><a id="logout" href="logout">安全退出</a></li>
</ul>