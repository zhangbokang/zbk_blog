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
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap-table/1.11.1/bootstrap-table.min.css">
    <link rel="stylesheet" href="http://zhishi01-1253216462.costj.myqcloud.com/static/css/common.css">

    <style rel="stylesheet">
        #allMake{
            width:100%;
            height:100%;
            background-color:#000;
            position:fixed;
            top:0;
            left:0;
            z-index:98;
            opacity:0.3;
            /*兼容IE8及以下版本浏览器*/
            filter: alpha(opacity=30);
            display:none;
        }

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
<jsp:include page="../header.jsp" />
<div style="background-color: #FFF">
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
<jsp:include page="../footer.jsp" />

<%--遮罩--%>
<div id="allMake"></div>


<script src="//cdn.bootcss.com/jquery/3.2.0/jquery.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap-table/1.11.1/bootstrap-table.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap-table/1.11.1/bootstrap-table-locale-all.min.js"></script>
<script src="http://zhishi01-1253216462.costj.myqcloud.com/static/common/common.js"></script>
<script>
    //遮罩
    function isMake() {
        $("#allMake").show();
    }
    function noMake() {
        $("#allMake").hide();
    }
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