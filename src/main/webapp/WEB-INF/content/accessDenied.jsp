<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Access Denied</title>
    <style type="text/css">
div.error {
    width: 260px;
    border: 2px solid red;
    background-color: yellow;
    text-align: center;
}
    </style>
  </head>
  <body>
    <h1>Access Denied</h1>
    <hr>
    <div class="error">
      ${requestScope['SPRING_SECURITY_403_EXCEPTION'].message}
    </div>
    <br/>
    <a id="logout" href="${pageContext.request.contextPath}/logout">安全退出</a>
    <hr>
  </body>
</html>