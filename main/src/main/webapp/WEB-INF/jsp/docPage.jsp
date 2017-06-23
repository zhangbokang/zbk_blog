<%@ page import="com.zbkblog.utils.Web" %><%--
  Created by IntelliJ IDEA.
  User: zhangbokang
  Date: 2017/5/5
  Time: 16:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>myblog - ${doc.title}</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="http://<%=Web.jsLoadDomain%>/static/editormd/css/editormd.min.css"/>
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <%--<link rel="stylesheet" href="/static/bootstrap/css/bootstrap.css">--%>
    <link rel="stylesheet" href="http://<%=Web.jsLoadDomain%>/static/css/blogpage.css">
    <link rel="stylesheet" href="http://<%=Web.jsLoadDomain%>/static/css/common.css">
    <style>
        #wrapper {
            overflow: hidden;
        }

        #portamento_container {
            float: left;
            position: relative;
        }

        #portamento_container #sidebar {
            float: none;
            position: absolute;
        }

        #portamento_container #sidebar.fixed {
            position: fixed;
        }
    </style>
</head>
<body>
<jsp:include page="header.jsp"/>
<div id="wrapper">
    <div id="docPageBody" class="row">
        <div class="col-md-4" id="col4">
            <div id="sidebar">
                <div id="docToc">
                    <h2>目录</h2>
                    <div id="custom-toc-container"></div>
                </div>
            </div>
        </div>

        <div class="col-md-8">
            <div id="content">
                <div id="docBody">
                    <div class="h1">${doc.title}</div>
                    <div class="row" id="classRow">
                        <div class="col-sm-4">
                            <%--分类：<a--%>
                            分类：${doc.classify.name}
                                <%--href="/findDocByClassifyId?classifyId=${doc.classify.classifyId}">${doc.classify.name}</a>--%>
                        </div>
                        <div class="col-sm-8">
                            <%--标签：<a href="/findDocByTagId?tagId=${doc.tag.tagId}">${doc.tag.name}</a>--%>
                            标签：${doc.tag.name}
                        </div>
                    </div>
                    <div class="row">
                        <jsp:useBean id="dataValue" class="java.util.Date"/>
                        <jsp:setProperty name="dataValue" property="time" value="${doc.updateTime}"/>
                        <div class="col-sm-6"><strong>最后更新&nbsp;<fmt:formatDate value="${dataValue}"
                                                                                type="both"/></strong></div>
                        <div class="col-sm-4 col-sm-offset-2">
                            <span class="badge glyphicon glyphicon-eye-open">&nbsp;${doc.openNumber == null?0:doc.openNumber}</span>
                            <a href="#">
                                <span class="badge glyphicon glyphicon-thumbs-up">&nbsp;${doc.favorNumber == null?0:doc.favorNumber}</span>
                            </a>
                        </div>
                    </div>
                    <hr/>
                    <div id="test-editormd-view">
                        <textarea style="display:none;">${doc.docMd}</textarea>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>

<script src="//cdn.bootcss.com/jquery/3.2.0/jquery.min.js"></script>
<%--<script src="/static/js/jquery-3.2.0.js"></script>--%>
<script src="//cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="http://<%=Web.jsLoadDomain%>/static/editormd/editormd.min.js"></script>
<script src="http://<%=Web.jsLoadDomain%>/static/editormd/lib/marked.min.js"></script>
<script src="http://<%=Web.jsLoadDomain%>/static/editormd/lib/prettify.min.js"></script>
<script src="http://<%=Web.jsLoadDomain%>/static/editormd/lib/raphael.min.js"></script>
<script src="http://<%=Web.jsLoadDomain%>/static/editormd/lib/underscore.min.js"></script>
<script src="http://<%=Web.jsLoadDomain%>/static/editormd/lib/sequence-diagram.min.js"></script>
<script src="http://<%=Web.jsLoadDomain%>/static/editormd/lib/flowchart.min.js"></script>
<script src="http://<%=Web.jsLoadDomain%>/static/editormd/lib/jquery.flowchart.min.js"></script>
<script src="http://<%=Web.jsLoadDomain%>/static/portamento-1.1.1/portamento-min.js"></script>
<script type="text/javascript">

    $('#sidebar').portamento();

    //让目录的宽度跟随他的父元素
    $(window).resize(function () {
        $('#docToc').width($('#col4').width());
        //当宽度小于970时就取消目录的相对定位
        if ($(window).width() < 970) {
            $("#portamento_container #docToc").addClass("relative");
        } else {
            $("#portamento_container #docToc").removeClass("relative");
        }
    });
    $(function () {
        $('#docToc').width($('#col4').width());
    });
    //end

    $(function () {
        $("#test-editormd-view").scrollspy({
            target: "#custom-toc-container"
        });
    })

    var testEditormdView;
    testEditormdView = editormd.markdownToHTML("test-editormd-view", {
        htmlDecode: "style,script,iframe",  // you can filter tags decode
        tocm: true,    // Using [TOCM]
        tocContainer: "#custom-toc-container", // 自定义 ToC 容器层
        emoji: true,
        taskList: true,
        tex: true,  // 默认不解析
        flowChart: true,  // 默认不解析
        sequenceDiagram: true,  // 默认不解析
    });
</script>

</body>
</html>
