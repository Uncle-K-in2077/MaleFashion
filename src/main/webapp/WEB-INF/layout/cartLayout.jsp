<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MaleFashion | Sgopping Cart</title>
<jsp:include page="_head.jsp"></jsp:include>
</head>
<style type="text/css">
	.error {
  		color: red;
  		font-size: 16px;
  		font-stretch:inherit; 
	}
</style>
<body>
	<!-- Page Preloder -->
    
	<tiles:insertAttribute name="header"></tiles:insertAttribute>
	<tiles:insertAttribute name="body"></tiles:insertAttribute>
	<tiles:insertAttribute name="count"></tiles:insertAttribute>
	<tiles:insertAttribute name="blogs"></tiles:insertAttribute>
	<tiles:insertAttribute name="footer"></tiles:insertAttribute>
</body>
</html>