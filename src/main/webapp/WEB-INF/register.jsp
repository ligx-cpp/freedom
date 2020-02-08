<%--
  Created by IntelliJ IDEA.
  User: 李光新
  Date: 2020/2/4
  Time: 15:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/user/add" method="post">
    用户名:<input type="text" name="username" /></br>
    密码:<input type="password" name="password" /></br>
    确认密码:<input type="password" name="password2" /></br>
    性别:<input type="text" name="gender" /></br>
    邮箱:<input type="text" name="email" /></br>
    <input type="submit" value="注册">
</form>
</body>
</html>
