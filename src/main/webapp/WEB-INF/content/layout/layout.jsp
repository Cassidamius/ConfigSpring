<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="viewport" content="width=device-width; initial-scale=1.0">
<title>测试系统</title>
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/core.css">
<link rel="stylesheet" href="css/demos.css">
</head>
<body>
	<div id="header">
		<div class="container">
			<tiles:insertAttribute name="top" />
		</div>
	</div>
	<div id="main">
		<div class="container">
			<div id="left">
				<div class="container">
					<tiles:insertAttribute name="left" />
				</div>
			</div>
			<div id="right">
				<div class="container" id="mainCon">
					<tiles:insertAttribute name="body" flush="true" />
				</div>
			</div>
		</div>
	</div>
</body>
</html>