<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title><fmt:message key="search.title"/></title>
</head>
<c:import url="header.jsp"/>
<h1><fmt:message key="search.title"/></h1>
<body>


<!-- <div>
	<a href="home.jsp">Accueil</a>
	<a href="login.jsp">se connecter</a>
	<a href="search">recherche</a>
</div> -->

<form action="search" method="get">
	<input type="text" name="keyword">
	<select name="familyId">
		<option value="-1">-- Pas de famille -- </option>
		<c:forEach items="${families}" var="f">
			<option value="${f.id}">${f.name}</option>
		</c:forEach>
		</select>
	<input type="submit" value="<fmt:message key="search.submit"/>">
</form>

<%-- <c:if test="${!empty products}">
	<h2><fmt:message key="search.result.title"/></h2>
	<ul>
		<c:forEach items="${products}" var="p">
			<li><c:url value="detail" var="urlDetail">
				<c:param name="productId" value="${p.id}"/>
				</c:url>
				<a href="${urlDetail}">${p.name}</a>
			</li>
		</c:forEach>
	</ul>
</c:if> --%>

<c:if test="${!empty products}">
	<h2><fmt:message key="search.result.title"/></h2>
	<ul>
		<c:forEach items="${products}" var="p">
			<li>
				<div>
					<img src="images?id=${p.image.id}" width="100"/>					
					<a href="detail?id=${p.id}">${p.name}</a>
				</div>
			</li>
			<%-- <li><c:url value="detail" var="urlDetail">
				<c:param name="productId" value="${p.id}"/>
				</c:url>
				<a href="${urlDetail}">${p.name}</a>
			</li> --%>
		</c:forEach>
	</ul>
</c:if>
<c:if test="${empty products && param.keyword != null}">
	<h2><fmt:message key="search.result.warningMessage"/></h2>
</c:if>

</body>
</html>