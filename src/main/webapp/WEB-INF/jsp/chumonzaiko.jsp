<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>在庫メッセージ画面</title>
</head>
<body>
<h1>在庫メッセージ画面</h1>
<p>${e.message}</p>
<c:if test="${not empty e.msgList}">
    <ul>
        <c:forEach items="${e.msgList}" var="msg">
            <li>${msg}</li>
        </c:forEach>
    </ul>
</c:if>
<br>
<a href="/chumon">カート処理へ戻る</a>
</body>
</html>