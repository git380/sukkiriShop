<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>在庫数更新</title>
</head>
<body>
<h1>在庫数更新</h1>
<form action="UpdateZaikoServlet" method="post">
<%--    <label for="itemId">商品ID:</label>--%>
<%--    <input type="text" id="itemId" name="itemId"><br>--%>
    全ての在庫を入力された値に変更します。<br>
    <label for="stock">在庫数:</label>
    <input type="text" id="stock" name="stock"><br>
    <input type="submit" value="更新">
</form>
</body>
</html>
