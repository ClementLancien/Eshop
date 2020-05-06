<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><fmt:message key="login.title"/></title>
</head>
<body>
<c:import url="header.jsp"/>
<h1><fmt:message key="login.title"/></h1>

<form action="login" method="post">
	<input type="text" name="username" placeholder="<fmt:message key="login.username"/>">
	<input type="password" name="password" placeholder="<fmt:message key="login.password"/>">
	<input type="submit" value="<fmt:message key="login.submit"/>">
</form>

<c:if test="${!empty authFailed}">
	<span style="color:red;"> <fmt:message key="login.errorMessage"/></span>
</c:if>

<!-- On a deux scripplets
	une scipplet ==> <% %>
 -->
<%-- <% 
if (request.getAttribute("authFailed") != null) {%>
	<span style="color:red;"> Echec de l'authentification </span>
<%
}
%> --%>
</body>
</html>