<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>商品情報一覧</title>
</head>
<body>

<h1>スッキリ商店：商品検索</h1>

<form action="ShohinQueryServlet" method="GET">
    <p>検索方法：<br>
        <input type="radio" name="cmd" value="query" checked>商品名
        <input type="radio" name="cmd" value="price">価格
    </p>
    <p>
        キーワード:(商品名の一部、または価格を入力してください)<br>
        <input type="text" name="keyword">
    </p>
    <p>
        <input type="submit" value="送信">
    </p>
</form>

<table border="1">
    <tr>
        <th rowspan="2">商品画像</th>
        <th>商品名</th>
        <th>容量</th>
        <th>販売価格</th>
        <th rowspan="2"></th>
    </tr>
    <tr>
        <th colspan="3">商品説明</th>
    </tr>

    <c:forEach var="shohin" items="${shohinList}">
        <tr>
            <td rowspan="2"><img src="/sukkiriShop/img/${shohin.image}"></td>
            <td>${shohin.name}</td>
            <td>${shohin.vol}</td>
            <td>${shohin.price}</td>
            <td rowspan="2">
                <form action="/sukkiriShop/ShohinDetailServlet" method="get">
                    <input type="hidden" name="code" value="${shohin.code}">
                    <input type="submit" value="詳細">
                </form>
            </td>
        </tr>
        <tr>
            <td colspan="3">${shohin.comment}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
