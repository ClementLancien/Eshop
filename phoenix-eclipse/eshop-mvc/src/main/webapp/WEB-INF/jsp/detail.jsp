<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="detail.title"/></title>
</head>
<body>
<c:import url="header.jsp"/>

<h1><spring:message code="detail.title"/></h1>

<c:if test="${!empty addToBasketSuccess}">
	<p style="font-weight:bold;color:green;">Produit ajouté au panier.</p>
</c:if>

<c:if test="${!empty addToBasketfail}">
	<p style="font-weight:bold;color:red;">Une erreur est survenue. Impossible d'ajouter ce produit a votre panier.</p>
</c:if>

<c:if test="${!empty product}">
	<div>
		<img src="images?id=${product.image.id}" width="100"/>
	</div>
	<p style="font-weight:bold;">Titre : ${product.name}</p>
	<p style="font-weight:bold;">Prix : ${product.price}</p>
	<p style="font-weight:bold;">Date : ${product.releaseDate}</p>
</c:if>

<form:form action="detail" method="post" modelAttribute="basketForm">
	<input name="id" type="hidden" value="${product.id}">
	<input name="quantity" type="number" value="1" min="1">
	<input type="submit" value="Ajouter au panier">
</form:form>
</body>
</html>