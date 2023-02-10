<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>スッキリ商店</title>
</head>
<body>
<h1>ようこそ!スッキリ商店です。</h1>
<ul>
    <c:if test="${empty sessionScope.userId}">
        <li><a href="/sukkiriShop/LoginServlet">ログイン</a></li>
        <li><a href="/sukkiriShop/RegisterServlet">ユーザ登録</a></li>
    </c:if>
    <c:if test="${not empty sessionScope.userId}">
        <li><a href="/sukkiriShop/LogoutServlet">ログアウト</a></li>
        <li><a href="/sukkiriShop/DeleteUserServlet">ユーザ削除</a></li>
    </c:if>
    <li><a href="/sukkiriShop/ShohinQueryServlet">ショップ</a></li>
    <li><a href="/sukkiriShop/CartServlet">カート</a></li>
    <li><a href="/sukkiriShop/UpdateZaikoServlet">在庫追加</a></li>
</ul>
</body>
</html>