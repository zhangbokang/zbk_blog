<%--
  Created by IntelliJ IDEA.
  User: zhangbokang
  Date: 2017/6/3
  Time: 23:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登陆</title>
    <%--<link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css">--%>
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <style rel="stylesheet">
        #logindiv {
            border: #8F938F 1px solid;
            width: 500px;
            height: 300px;
            background-color: #FFF;
            margin: auto;
            position: fixed;
            top: 0;
            bottom: 0;
            left: 0;
            right: 0;
            padding: 40px;
        }
        #logindiv .row {
            margin-top: 40px;
        }
        #errorMsg {
            margin-top: 10px;
            color: red;
        }
    </style>
</head>
<body>
<div id="logindiv">
    <!--<div>用户登陆</div>-->
    <label for="username">用户名</label>
    <input id="username" type="text" class="form-control">
    <label for="password">密码</label>
    <input id="password" type="password" class="form-control">
    <div class="row">
        <div class="col-sm-6">
            <button id="login" onclick="loginFn()" class="btn btn-success form-control">登陆</button>
        </div>
        <div class="col-sm-6">
            <a id="clean" class="btn btn-warning form-control" href="/index">取消</a>
        </div>
    </div>
    <div id="errorMsg"></div>
</div>
<%--<script src="/static/js/jquery-3.2.0.js"></script>--%>
<script src="//cdn.bootcss.com/jquery/3.2.0/jquery.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<%--<script src="/static/bootstrap/js/bootstrap.min.js"></script>--%>
<script src="/static/js-md5/md5.min.js"></script>
<script src="//cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
<%--<script src="/static/jquery-cookie/jquery.cookie.js"></script>--%>
<script src="/static/js/login.js"></script>
</body>
</html>