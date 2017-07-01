<%@ page import="com.zbkblog.utils.Web" %><%--
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
    <link rel="stylesheet" href="<%=Web.staticLoadDomain%>/static/bootstrap/css/bootstrap.min.css">
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
    <input id="username" type="text" onkeyup="EnteyKey(event)" class="form-control">
    <label for="password">密码</label>
    <input id="password" type="password" onkeyup="EnteyKey(event)" class="form-control">
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
<script src="<%=Web.staticLoadDomain%>/static/jquery/jquery-3.2.1.min.js"></script>
<script src="<%=Web.staticLoadDomain%>/static/bootstrap/js/bootstrap.min.js"></script>
<script src="<%=Web.staticLoadDomain%>/static/js-md5/md5.min.js"></script>
<script src="<%=Web.staticLoadDomain%>/static/jquery-cookie/jquery.cookie.js"></script>
<script src="<%=Web.staticLoadDomain%>/static/js/login.js"></script>
<script>
    //输入框的回车事件
    function EnteyKey(e) {
        if (e.keyCode==13){loginFn();}
    }
</script>
</body>
</html>