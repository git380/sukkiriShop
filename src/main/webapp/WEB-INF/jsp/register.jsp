<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>ユーザ登録</title>
</head>
<body>
<h1>ユーザ登録</h1>
<form action="RegisterServlet" method="post">
  <label for="userId">ユーザID:</label>
  <input type="text" id="userId" name="userId"><br>
  <label for="password">パスワード:</label>
  <input type="password" id="password" name="password"><br>
  <label for="email">メールアドレス:</label>
  <input type="text" id="email" name="email"><br>
  <label for="name">名前:</label>
  <input type="text" id="name" name="name"><br>
  <label for="age">年齢:</label>
  <input type="text" id="age" name="age"><br>
  <input type="submit" value="登録">
</form>
</body>
</html>
