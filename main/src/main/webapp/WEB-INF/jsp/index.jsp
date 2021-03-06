<%@ page import="com.zbkblog.utils.Web" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>myblog主页</title>
    <link rel="stylesheet" href="<%=Web.staticLoadDomain%>/static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=Web.staticLoadDomain%>/static/bootstraptable/bootstrap-table.min.css">
    <link rel="stylesheet" href="<%=Web.staticLoadDomain%>/static/css/style.css">
    <link rel="stylesheet" href="<%=Web.staticLoadDomain%>/static/css/common.css">
    <script src="<%=Web.staticLoadDomain%>/static/jquery/jquery-3.2.1.min.js"></script>
    <script src="<%=Web.staticLoadDomain%>/static/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<!-- 头部 -->
<jsp:include page="header.jsp" />
<!-- 主体部分 -->
<div id="main">
    <div class="row">
        <!-- 分类列表、标签列表、标签地图和排行列表等列表 -->
        <div class="col-sm-4">
            <jsp:include page="allPanel.jsp"/>
        </div>
        <!-- 主体显示部分 -->
        <div class="col-sm-8" id="bloglist">
            <%--<jsp:include page="bloglist.jsp"/>--%>
        </div>
    </div>
</div>
<!-- 底部 -->
<jsp:include page="footer.jsp" />



<script src="<%=Web.staticLoadDomain%>/static/jqPaginator/dist/jqPaginator.min.js"></script>
<script>
    $("#bloglist").load("/findAllDoc?accessType=doc");
    function loadBlogList(url,par) {
        $("#bloglist").load(url,par);
    }
</script>
</body>
</html>