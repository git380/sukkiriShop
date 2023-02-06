<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>商品詳細</title>
</head>
<body>
<h1>商品詳細</h1>
<table>
    <tr>
        <td>商品コード</td>
        <td>${shohin.code}</td>
    </tr>
    <tr>
        <td>商品名</td>
        <td>${shohin.name}</td>
    </tr>
    <tr>
        <td>価格</td>
        <td>${shohin.price}</td>
    </tr>
</table>
<form action="<%=request.getContextPath()%>/CartServlet" method="post">
    <input type="hidden" name="cmd" value="add">
    <input type="hidden" name="code" value="${shohin.code}">
    数量:
    <select name="quantity">
        <option value="1">1</option>
        <option value="2">2</option>
        <option value="3">3</option>
        <option value="4">4</option>
        <option value="5">5</option>
        <option value="6">6</option>
        <option value="7">7</option>
        <option value="8">8</option>
        <option value="9">9</option>
    </select>
    <input type="submit" value="カートに追加する">
</form>
<input type="button" value="戻る" onClick='history.back();' />
</body>
</html>