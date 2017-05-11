<%--
  Created by IntelliJ IDEA.
  User: zhangbokang
  Date: 2017/5/5
  Time: 16:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${blogDoc.blogTitle}</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="/static/editormd/css/editormd.min.css" />
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <%--<link rel="stylesheet" href="/static/bootstrap/css/bootstrap.css">--%>
    <link rel="stylesheet" href="/static/css/blogpage.css">
    <link rel="stylesheet" href="/static/css/common.css">
</head>
<body>
<jsp:include page="header.jsp" />
<div class="row">
    <div class="col-md-4" id="col4">
        <div id="portamento_container">
            <div id="blogToc">
                <h2>目录</h2>
                <div id="custom-toc-container"></div>
            </div>
        </div>
    </div>
    <div class="col-md-8">
        <div class="row">
            <div class="col-lg-12">
                <div class="h1">${blogDoc.blogTitle}</div>
                <div class="row" id="classRow">
                    <div class="col-sm-4">
                        分类：<a href="#">${blogDoc.blogClass}</a>
                    </div>
                    <div class="col-sm-8">
                        标签：<a href="#">${blogDoc.blogTag}</a>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-6"><strong>最后更新&nbsp;${blogDoc.updataTimeStr}</strong></div>
                    <div class="col-sm-4 col-sm-offset-2">
                        <span class="badge glyphicon glyphicon-eye-open">&nbsp;${blogDoc.openNumber == null?0:blogDoc.openNumber}</span>
                        <a href="#">
                            <span class="badge glyphicon glyphicon-thumbs-up">&nbsp;${blogDoc.supportNumber == null?0:blogDoc.supportNumber}</span>
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <hr />
        <div id="test-editormd-view">
            <textarea style="display:none;">${blogDoc.blogMd}</textarea>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp" />

<script src="//cdn.bootcss.com/jquery/3.2.0/jquery.min.js"></script>
<%--<script src="/static/js/jquery-3.2.0.js"></script>--%>
<script src="//cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="/static/editormd/editormd.min.js"></script>
<script src="/static/editormd/lib/marked.min.js"></script>
<script src="/static/editormd/lib/prettify.min.js"></script>
<script src="/static/editormd/lib/raphael.min.js"></script>
<script src="/static/editormd/lib/underscore.min.js"></script>
<script src="/static/editormd/lib/sequence-diagram.min.js"></script>
<script src="/static/editormd/lib/flowchart.min.js"></script>
<script src="/static/editormd/lib/jquery.flowchart.min.js"></script>
<script src="/static/js/portamento.js"></script><!--这个js我定制了一下，暂时还没有压缩，必须使用非压缩的 -->
<script type="text/javascript">

    $('#blogToc').portamento({disableWorkaround: true});

    //让目录的宽度跟随他的父元素
    $(window).resize(function(){
        $('#blogToc').width($('#col4').width());
        //当宽度小于970时就取消目录的相对定位
        if ($(window).width()<970){
            $("#portamento_container #blogToc").addClass("relative");
        }else {
            $("#portamento_container #blogToc").removeClass("relative");
        }
    });
    $(function () {
        $('#blogToc').width($('#col4').width());
    });
    //end

    $(function(){
        $("#test-editormd-view").scrollspy({
            target: "#custom-toc-container"
        });
    })

        var testEditormdView;
        testEditormdView = editormd.markdownToHTML("test-editormd-view", {
            htmlDecode      : "style,script,iframe",  // you can filter tags decode
            tocm            : true,    // Using [TOCM]
            tocContainer    : "#custom-toc-container", // 自定义 ToC 容器层
            emoji           : true,
            taskList        : true,
            tex             : true,  // 默认不解析
            flowChart       : true,  // 默认不解析
            sequenceDiagram : true,  // 默认不解析
        });
</script>

</body>
</html>
