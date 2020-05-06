<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Accueil</title>
</head>
<body>
<c:import url="header.jsp"/>
<h1>Accueil</h1>
Bonjour ${customer.firstName}  ${customer.lastName} 
</body>
</html>