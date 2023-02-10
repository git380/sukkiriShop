<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ユーザ削除</title>
</head>
<body>
<h1>ユーザ削除</h1>
<form action="DeleteUserServlet" method="post">
    <label>確認のため、ユーザIDを入力してください</label><br>
    ユーザID:<input type="text" name="userId"><br>
    <input type="submit" value="削除">
</form>
</body>
</html>
