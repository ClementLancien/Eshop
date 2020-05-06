<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><fmt:message key="register.title"/></title>
</head>
<body>
<c:import url="header.jsp"/>
<h2><fmt:message key="register.title"/></h2>
<c:if test="${!empty registerSuccess}">
	<h2 style="color:green;">Votre nouveau compte a été enregistré.</h2>
</c:if>
<c:if test="${!empty registerFail}">
	<h2 style="color:red;">Impossible de créer votre compte.</h2>
</c:if>
<form action="register" method="post">
	<input type="text" name="firstname" placeholder="<fmt:message key="register.firstname"/>">
	<input type="text" name="lastname" placeholder="<fmt:message key="register.lastname"/>">
	<input type="text" name="username" placeholder="<fmt:message key="register.username"/>">
	<input type="password" name="password" placeholder="<fmt:message key="register.password"/>">
	<input type="submit" value="<fmt:message key="register.submit"/>">
</form>

</body>
</html>