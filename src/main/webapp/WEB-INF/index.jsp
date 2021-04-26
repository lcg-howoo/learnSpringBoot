<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<spring:eval expression="@environment.getProperty('spring.profiles.active')" var="runMode" />


<!DOCTYPE html>
<html>
	<head>
		<title>
			demo project
		</title>
		<script src="${runMode == 'local'? 'http://localhost:3000': ''}/built/bundle.view.js" defer></script>
	</head>
	<body>
		<%
			for(int i = 0 ; i < 10; i++) {
			%> <h2> <%=i %></h2> <%
			}
		%>
		<c:forEach var="i" begin="10" end="20">
			<h3>${i}</h3>
		</c:forEach>

		<div id="react-root"></div>
	</body>
</html>