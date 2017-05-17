<%--
  Created by IntelliJ IDEA.
  User: zhangbokang
  Date: 2017/5/2
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 头部 -->
<meta charset="utf-8">
<link rel="stylesheet" href="/static/css/header.css" />
<header>
    <div class="masthead">
        <a href="index.html" id="logo">
            <img src="http://zhishi01.nos-eastchina1.126.net/logo.jpg" alt="MyBlog">
        </a>
        <ul class="nav nav-pills pull-right">
            <li><a href="/index">主页</a></li>
            <li><a href="/doc/editDoc">发布文章</a></li>
            <%--<li><a href="#">关于</a></li>--%>
            <li><a href="#">联系方式</a></li>
        </ul>
    </div>
    <!-- 导航条 -->
    <div class="navbar navbar-default">
        <div class="navbar-header">
            <button class="navbar-toggle" type="button" data-toggle="collapse" data-target=".navbar-responsive-collapse">
                <span class="sr-only">Toggle Navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/index">MyBlog博客</a>
        </div>
        <div class="collapse navbar-collapse navbar-responsive-collapse">
            <ul class="nav nav-tabs">
                <li><a href="/index">文章列表</a></li>
                <%--<li><a href="#">链接</a></li>--%>

                <form action="#" method="post" class="navbar-form navbar-right">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="请输入关键词" />
                    </div>
                    <button type="submit" class="btn btn-default">搜索</button>
                </form>
            </ul>
        </div>
    </div>
</header>
