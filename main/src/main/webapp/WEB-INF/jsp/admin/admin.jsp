<%--
  Created by IntelliJ IDEA.
  User: zhangbokang
  Date: 2017/5/18
  Time: 22:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>管理页面</title>
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/bootstraptable/bootstrap-table.min.css">
    <link rel="stylesheet" href="/static/css/common.css">

    <style rel="stylesheet">
        #loadDiv{
            padding: 20px 30px;
            min-height: 600px;
        }
        #navDiv{
            padding: 5px 5px 0px 5px;
        }
    </style>
</head>
<body>

<div style="background-color: #FFF">
    <div class="h1" style="padding: 8px 15px;">
        管理界面
    </div>
    <div id="navDiv">
        <ul class="nav nav-tabs nav-justified">
            <li class="active"><a href="javascrpt:void(0)" onclick="$('#loadDiv').load('/admin/docManage');">文章管理</a></li>
            <li><a href="javascrpt:void(0)" onclick="$('#loadDiv').load('/admin/classifyManage');">分类管理</a></li>
            <li><a href="javascrpt:void(0)" onclick="$('#loadDiv').load('/admin/tagManage');">标签管理</a></li>
            <li><a href="javascrpt:void(0)" onclick="$('#loadDiv').load('/admin/commentManage');">评论管理</a></li>
        </ul>
    </div>
    <div id="loadDiv"></div>
</div>

<script src="/static/js/jquery-3.2.0.js"></script>
<script src="/static/bootstrap/js/bootstrap.min.js"></script>
<script src="/static/bootstraptable/bootstrap-table.min.js"></script>
<script src="/static/bootstraptable/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="/static/common/common.js"></script>
<script>
    $(function () {
        //默认加载文章管理页面
        $('#loadDiv').load('/admin/docManage');
        //点击导航栏效果
        $("#navDiv li").click(function () {
            $("#navDiv li").removeClass('active');
            $(this).addClass('active');
        });
    });
</script>
</body>
</html>