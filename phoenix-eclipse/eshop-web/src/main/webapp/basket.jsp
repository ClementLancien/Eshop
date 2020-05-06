<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mon panier</title>
</head>
<body>
<c:import url="header.jsp"/>
<c:if test="${!empty basket}">
	<h2>Votre Panier</h2>
	<ul>
		<c:forEach items="${basket}" var="b">
			<li>Titre : ${b.product.name}  Prix : ${b.product.price} Quantité : ${b.quantity}</li>
		</c:forEach>
	</ul>
	
	<form action="basket" method="post">
		<input type="submit" value="Passez commande"/>
	</form>
</c:if>
<c:if test="${empty basket}">
	<h2>Votre panier est vide.</h2>
</c:if>
<c:if test="${!empty orderCommandSuccess}">
	<h3 style="color:green;">Votre commande a bien été enregistrée.</h3>
</c:if> 
</body>
</html>