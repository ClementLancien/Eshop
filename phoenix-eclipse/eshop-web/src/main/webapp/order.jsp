<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<style type="text/css">
@import url("style.css");
</style>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mes commandes passées</title>
</head>
<body>
<c:import url="header.jsp"/>
<h2>Mes commandes passées</h2>
<c:if test="${!empty orders}">
	<ul>
		<c:forEach items="${orders}" var="o">
			<!-- <th COLSPAN="3" style="text-align:center;"> -->
         		<h3>Numéro de commande : ${o.orderNumber} - Etat : ${o.status}</h3>
      		<!-- </th> -->
			<table>
				<tr>
					<th>Titre</th>
					<th>Prix unitaire</th>
					<th>Quantité</th>
				</tr>
				<c:forEach items="${o.lines}" var="line">
					<tr>
						<td>${line.product.name}</td>
						<td>${line.product.price}</td>
						<td>${line.quantity}</td>
					</tr>
				</c:forEach>
			</table>
		</c:forEach>
	</ul>
</c:if>
<c:if test="${empty orders}">
	<h2>Vous n'avez aucune commande chez nous. :(</h2>
</c:if>
</body>
</html>
