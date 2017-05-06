<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>myblog主页</title>
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap-table/1.11.1/bootstrap-table.min.css">
    <link rel="stylesheet" href="/static/css/style.css">
    <style rel="stylesheet">

        div{
            /*border: 1px red solid;*/
        }

    </style>
</head>
<body>
<!-- 头部 -->
<jsp:include page="header.jsp" />
<!-- 主体部分 -->
<div id="main">
    <div class="row">
        <!-- 分类列表、标签列表等列表 -->
        <div class="col-sm-2">
            <jsp:include page="classAndTagPanel.jsp"/>
        </div>
        <!-- 主体显示部分 -->
        <div class="col-sm-7">
            <jsp:include page="bloglist.jsp"/>
        </div>
        <!-- 标签地图和排行列表 -->
        <div class="col-sm-3">
            <jsp:include page="tagcloudAndOrderPanel.jsp"/>
        </div>
    </div>
</div>
<!-- 底部 -->
<jsp:include page="footer.jsp" />


<script src="//cdn.bootcss.com/jquery/3.2.0/jquery.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap-table/1.11.1/bootstrap-table.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap-table/1.11.1/bootstrap-table-locale-all.min.js"></script>
</body>
</html>