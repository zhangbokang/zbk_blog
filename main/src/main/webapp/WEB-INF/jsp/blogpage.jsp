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
    <title>Title</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="/static/editormd/css/editormd.min.css" />
    <%--<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">--%>
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.css">
    <style rel="stylesheet">
        body{
            padding:50px 100px;
        }
        #custom-toc-container{
            border:1px solid #000;
            width: 100%;
            height: 600px;
            position: fixed;
            overflow: auto;
        }
    </style>
</head>
<body>
<div class="row">
    <div class="col-lg-12">
        <div class="h2">${blogDoc.blogTitle}</div>
        <div class="row">
            <div class="col-sm-6"><strong>最后更新&nbsp;${blogDoc.updataTimeStr}</strong></div>
            <div class="col-sm-4 col-sm-offset-2">
                <span class="badge glyphicon glyphicon-eye-open">&nbsp;2500</span>
                <a href="#">
                    <span class="badge glyphicon glyphicon-thumbs-up">&nbsp;2230</span>
                </a>
            </div>
        </div>
    </div>
</div>
<hr />
<div class="row">
    <div class="col-lg-4"  id="sidebar">
        <h2>目录</h2>
        <div id="custom-toc-container"></div>
    </div>
    <div class="col-lg-8">
        <div id="test-editormd-view">
            <textarea style="display:none;">${blogDoc.blogMd}</textarea>
        </div>
    </div>
</div>




<%--<script src="//cdn.bootcss.com/jquery/3.2.0/jquery.min.js"></script>--%>
<script src="/static/js/jquery-3.2.0.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="/static/editormd/editormd.min.js"></script>
<%--<script src="/static/js/jquery.serializejson.js"></script>--%>
<script src="/static/editormd/lib/marked.min.js"></script>
<script src="/static/editormd/lib/prettify.min.js"></script>

<script src="/static/editormd/lib/raphael.min.js"></script>
<script src="/static/editormd/lib/underscore.min.js"></script>
<script src="/static/editormd/lib/sequence-diagram.min.js"></script>
<script src="/static/editormd/lib/flowchart.min.js"></script>
<script src="/static/editormd/lib/jquery.flowchart.min.js"></script>

<script type="text/javascript">
    $(function(){
        $("#test-editormd-view").scrollspy({
            target: "#custom-toc-container"
        });
    })

    //    $(function() {
//        var testEditormdView;//, testEditormdView2;
//
////        $.get("test.md", function(markdown) {
//
//            testEditormdView = editormd.markdownToHTML("test-editormd-view", {
//                //markdown        : markdown ,//+ "\r\n" + $("#append-test").text(),
//                //htmlDecode      : true,       // 开启 HTML 标签解析，为了安全性，默认不开启
//                htmlDecode      : "style,script,iframe",  // you can filter tags decode
//                //toc             : false,
//                tocm            : true,    // Using [TOCM]
//                tocContainer    : "#custom-toc-container", // 自定义 ToC 容器层
//                //gfm             : false,
//                //tocDropdown     : true,
//                // markdownSourceCode : true, // 是否保留 Markdown 源码，即是否删除保存源码的 Textarea 标签
//                emoji           : true,
//                taskList        : true,
//                tex             : true,  // 默认不解析
//                flowChart       : true,  // 默认不解析
//                sequenceDiagram : true,  // 默认不解析
//            });

            //console.log("返回一个 jQuery 实例 =>", testEditormdView);

            // 获取Markdown源码
            //console.log(testEditormdView.getMarkdown());

            //alert(testEditormdView.getMarkdown());
//        });

        var testEditormdView;

        testEditormdView = editormd.markdownToHTML("test-editormd-view", {
            //path : '/static/editormd/lib/',
            htmlDecode      : "style,script,iframe",  // you can filter tags decode
            tocm            : true,    // Using [TOCM]
            tocContainer    : "#custom-toc-container", // 自定义 ToC 容器层
            emoji           : true,
            taskList        : true,
            tex             : true,  // 默认不解析
            flowChart       : true,  // 默认不解析
            sequenceDiagram : true,  // 默认不解析
        });
//    });
</script>

</body>
</html>
