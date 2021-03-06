<%@ page import="com.zbkblog.utils.Web" %><%--
  Created by IntelliJ IDEA.
  User: zhangbokang
  Date: 2017/5/2
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 头部 -->
<meta charset="utf-8">
<link rel="stylesheet" href="<%=Web.staticLoadDomain%>/static/css/header.css" />
<header>
    <div class="masthead">
        <a href="index" id="logo">
            <img src="<%=Web.staticLoadDomain%>/static/img/logo1.jpg" alt="MyNote">
        </a>
        <ul class="nav nav-pills pull-right">
            <li><a href="/index">主页</a></li>
            <li><a href="/admin">管理页面</a></li>
            <%--<li><a href="#">关于</a></li>--%>
            <%--<li><a href="#">联系方式</a></li>--%>
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
            <a class="navbar-brand" href="/index">我的笔记系统</a>
        </div>
        <div class="collapse navbar-collapse navbar-responsive-collapse">
            <ul class="nav nav-tabs">
                <li><a href="/index">文章列表</a></li>
                <%--<li><a href="#">链接</a></li>--%>

                <div class="navbar-form navbar-right">
                    <div class="form-group">
                        <input id="keyword" type="text" class="form-control" placeholder="请输入关键词" />
                    </div>
                    <button class="btn btn-default" onclick="searchKeyword()">搜索</button>
                </div>
            </ul>
        </div>
    </div>
</header>
<script>
    function searchKeyword() {
        if (typeof(loadBlogList) == "function"){
            loadBlogList('/searchDocByKeywork?accessType=keyword&keyword='+$('#keyword').val(),{});
            return;
        }
        alert("请前往主页后搜索！");
    }
</script>
