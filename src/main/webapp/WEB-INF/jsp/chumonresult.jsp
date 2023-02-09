<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>注文結果画面</title>
</head>
<body>
<h1>注文結果画面</h1>
<c:if test="${not empty resultList}">
    <table>
        <tr>
            <th>商品名</th>
            <th>数量</th>
            <th>単価</th>
            <th>金額</th>
        </tr>
        <c:forEach items="${resultList}" var="result">
            <tr>
                <td>${result.itemName}</td>
                <td>${result.quantity}</td>
                <td>${result.price}</td>
                <td>${result.quantity * result.price}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<br>
<a href="/">Welcome画面へ戻る</a>
</body>
</html>