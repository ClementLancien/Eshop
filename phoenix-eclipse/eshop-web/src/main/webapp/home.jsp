<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><fmt:message key="home.title"/></title>
</head>
<body>
<c:import url="header.jsp"/>
<h1><fmt:message key="home.title"/></h1>
<%-- <ul>
	<li>
		<c:url value="login.jsp" var="urlLogin">
		</c:url>
		<a href="${urlLogin}"><fmt:message key="home.menu.login"/></a>
	</li>
	<li>
		<c:url value="search" var="urlSearch">
		</c:url>
		<a href="${urlSearch}"><fmt:message key="home.menu.search"/></a>
	</li>
</ul> --%>

<!-- <div>
	<a href="login.jsp">se connecter</a>
	<a href="search">recherche</a>
</div>
 -->
<c:if test="${!empty customer  && param.customer != null}">
	<h1>
		<fmt:message key="home.welcome">
			<fmt:param value="${cust.firstName}"/>
			<fmt:param value="${cust.lastName}"/>
		</fmt:message>
	</h1>
</c:if>

</body>
</html>