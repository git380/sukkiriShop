<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>スッキリ商店</title>
</head>
<body>
<c:forEach var="i" begin="1" end="5">
    <p>ようこそ<c:out value="${userId}"/>さん</p>
</c:forEach>
<a href="/sukkiriShop/WelcomeServlet">トップへ</a>
</body>
</html>