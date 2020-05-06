<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="search.title"/></title>
</head>
<body>
<c:import url="header.jsp"/>
<h1><spring:message code="search.title"/></h1>

<form:form action="search" method="get" modelAttribute="searchForm">
	<label for="keywordInput"><spring:message code="search.title"/></label> 
	<form:input id ="keywordInput" path="keyword"/>
	<form:select path="familyId">
	   <form:option value="-1" label="Toutes catégories"/>
	   <form:options items="${families}" itemLabel="name" itemValue="id"/>
	</form:select>
	<input type="submit" value="Rechercher" />
</form:form>

<c:if test="${!empty products}">
	<h2><spring:message code="search.result.title"/></h2>
	<ul>
		<c:forEach items="${products}" var="p">
			<li>
				<div>
					<img src="images?id=${p.image.id}" width="100"/>		
					<a href="detail?id=${p.id}">${p.name}</a>
				</div>
			</li>
		</c:forEach>
	</ul>
</c:if>
<c:if test="${empty products && param.keyword != null}">
	<h2><spring:message code="search.result.warningMessage"/></h2>
</c:if>
</body>
</html>