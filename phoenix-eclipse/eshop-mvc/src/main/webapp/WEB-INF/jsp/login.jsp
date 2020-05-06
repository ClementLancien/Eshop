<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="login.title"/></title>
</head>
<body>
<c:import url="header.jsp"/>

<h1><spring:message code="login.title"/></h1>

<spring:message code="login.submit" var="submitLabel"/>

<form:form action="login" method="post" modelAttribute="loginForm">
	<label for="usernameInput"><spring:message code="login.username"/></label>
	<form:input id="usernameInput" path="username"/>
	<form:errors path="username"/>
	
	<label for="passwordInput"><spring:message code="login.password"/></label>
	<form:password id="passwordInput" path="password"/>
	<form:errors path="password"/>
	
	<input type="submit" value="${submitLabel}"/>
</form:form>

<c:if test="${!empty authFailed}">
	<span style="color:red;"><spring:message code="login.errorMessage"/></span>
</c:if>

</body>
</html>