<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>カート</title>
</head>
<body>

<h1>スッキリ商店：カート</h1>

<table border="1">
    <tr>
        <th>商品名</th>
        <th>単価</th>
        <th>数量</th>
        <th>合計金額</th>
        <th>削除</th>
    </tr>
    <c:set var="total" value="${0}" /> <!-- 合計用変数定義 -->
    <c:forEach var="cart" items="${cartList}" varStatus="status">
        <tr>
            <td>${cart.name}</td>
            <td>${cart.price}円</td>
            <td>
                <form action="/14webap/CartServlet" method="post">
                    <input type="hidden" name="cmd" value="mod">
                    <input type="hidden" name="idx" value="${status.index}">
                    <input type="hidden" name="code" value="${cart.code}">
                    <input type="number" name="quantity" value="${cart.quantity}">
                    <input type="submit" value="変更">
                </form>
            </td>
            <td>${cart.price * cart.quantity}円</td>
            <c:set var="total" value="${total + (cart.price * cart.quantity)}" /><!-- 合計計算 -->
            <td>
                <form action="/14webap/CartServlet" method="post">
                    <input type="hidden" name="cmd" value="del">
                    <input type="hidden" name="idx" value="${status.index}">
                    <input type="hidden" name="code" value="${cart.code}">
                    <input type="submit" value="削除">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
合計金額:<c:out value="${total}" /> 円<br>
<input type="button" value="戻る" onClick="history.back()">

</body>
</html>
