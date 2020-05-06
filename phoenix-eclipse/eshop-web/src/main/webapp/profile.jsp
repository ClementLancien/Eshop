<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><fmt:message key="profile.title"/></title>
</head>
<body>
<c:import url="header.jsp"/>

<h1><fmt:message key="profile.title"/></h1>

<ul>
	<li>
		<fmt:message key="profile.customer.firstname">
			<fmt:param value="${customer.firstName}"/>
		</fmt:message>
	</li>
	<li>
		<fmt:message key="profile.customer.lastname">
			<fmt:param value="${customer.lastName}"/>
		</fmt:message>
	</li>
	<li>
		<fmt:message key="profile.customer.username">
			<fmt:param value="${customer.username}"/>
		</fmt:message>
	</li>
	<li>
		<fmt:message key="profile.customer.password">
			<fmt:param value="${customer.password}"/>
		</fmt:message>
	</li>
</ul>
</body>
</html>