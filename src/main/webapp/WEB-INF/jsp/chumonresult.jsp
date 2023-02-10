<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>注文結果画面</title>
</head>
<body>
<h1>ご注文を承りました</h1>
<c:if test="${not empty resultList}">
    <table border="1">
        <tr>
            <th>商品名</th>
            <th>数量</th>
            <th>単価</th>
            <th>金額</th>
        </tr>
        <c:set var="total" value="${0}"/> <!-- 合計用変数定義 -->
        <c:forEach items="${resultList}" var="result">
            <tr>
                <td>${result.name}</td>
                <td>${result.quantity}個</td>
                <td>${result.price}円</td>
                <td>${result.quantity * result.price}円</td>
            </tr>
            <c:set var="total" value="${total + (result.quantity * result.price)}"/><!-- 合計計算 -->
        </c:forEach>
    </table>
    合計金額: ${total}円<br>
</c:if>
<br>
<a href="/sukkiriShop/WelcomeServlet">トップページへ戻る</a>
</body>
</html>