<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><fmt:message key="detail.title"/></title>
</head>
<body>
<c:import url="header.jsp"/>

<h1><fmt:message key="detail.title"/></h1>

<c:if test="${!empty addToBasketSuccess}">
	<p style="font-weight:bold;color:green;">Produit ajouté au panier.</p>
</c:if>

<c:if test="${!empty addToBasketfail}">
	<p style="font-weight:bold;color:red;">Une erreur est survenue. Impossible d'ajouter ce produit a votre panier.</p>
</c:if>

<c:if test="${!empty product && !empty date}">
	<p style="font-weight:bold;">
		<fmt:message key="detail.description.title">
			<fmt:param value="${product.name}"/>
		</fmt:message>
	</p>
	<p style="font-weight:bold;">Catégorie : ${param.category}<p>
	<p style="font-weight:bold;">
		<fmt:message key="detail.description.price">
			<fmt:param value="${product.price}"/>
		</fmt:message>
	</p>
	<p>
		<fmt:message key="detail.description.date">
			<fmt:param value="${date}"/>
		</fmt:message>
	</p>

</c:if>

<form action="detail" method="post">
	<input name="id" type="hidden" value="${product.id}">
	<input name="quantity" type="number" value="1" min="1">
	<input type="submit" value="<fmt:message key="detail.menu.addToBasket"/> ">
</form>


</body>
</html>
