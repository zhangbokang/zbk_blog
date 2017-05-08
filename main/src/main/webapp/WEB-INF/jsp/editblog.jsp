<%--
  Created by IntelliJ IDEA.
  User: zhangbokang
  Date: 2017/5/4
  Time: 15:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文章编辑</title>
    <link rel="stylesheet" href="/static/editormd/css/editormd.min.css" />
    <%--<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">--%>
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="/static/css/editblog.css">
    <meta charset="utf-8">
</head>
<body>
<div class="row">
    <div class="col-sm-4">
        <a href="/index"><b>返回主页</b></a>
    </div>
    <div class="col-sm-8" id="msg"></div>
</div>
    <form action="/save" method="post" id="blogForm">
        <input type="hidden" name="blogId" id="blogId" value="${blogDoc.blogId}">
        <div class="row" id="blogTitleArea">
            <div class="col-sm-4">
                <div class="form-group">
                    <label for="blogTitle" class="control-label">标题：</label>
                    <input type="text" name="blogTitle" value="${blogDoc.blogTitle}" id="blogTitle" class="form-control" placeholder="请输入标题">
                </div>
            </div>
            <div class="col-sm-3">
                <div class="form-group">
                    <label for="blogClass" class="control-label">分类：</label>
                    <select id="blogClass" name="blogClass" class="form-control">
                        <option checked>请选择分类</option>
                        <option value="aa">AA</option>
                        <option value="bb">BB</option>
                    </select>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="form-group">
                    <label for="blogTag" class="control-label">标签：</label>
                    <input type="text" name="blogTag" value="${blogDoc.blogTag}" id="blogTag" class="form-control" placeholder="请输入标签 多个使用逗号分隔">
                </div>
            </div>
            <div class="col-sm-1 center-block">
                <%--<button class="btn btn-default" type="button" onclick="save(this)">保存</button>--%>
                    <button class="btn btn-default" type="button" onclick="submitForm('#blogForm')">保存</button>
            </div>
        </div>
        <div class="row" id="editArea">
            <div class="col-sm-12">
                <div id="editormd">
                    <textarea style="display:none;" name="blogMd">${blogDoc.blogMd}</textarea>
                </div>
            </div>
        </div>

    </form>



    <script src="//cdn.bootcss.com/jquery/3.2.0/jquery.min.js"></script>
    <%--<script src="/static/js/jquery-3.2.0.js"></script>--%>
    <script src="//cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="/static/editormd/editormd.min.js"></script>
    <script src="//cdn.bootcss.com/jquery.serializeJSON/2.8.1/jquery.serializejson.min.js"></script>
    <%--<script src="/static/js/jquery.serializejson.js"></script>--%>
    <script type="text/javascript">
        //点击保存按钮提交表单
        function submitForm(formId) {
            var formData = $(formId).serializeJSON();
            if (formData.blogTitle == null || formData.blogTitle == ""){
                var blogTitle = $(formId).find("#blogTitle");
                $(blogTitle).focus();
                alert("请填写标题");
                return ;
            }
            if (formData.blogClass == "请选择分类"){
                $(formId).find("#blogClass").focus();
                alert("请选择分类");
                return ;
            }
            if (formData.blogTag == null || formData.blogTag == ""){
                $(formId).find("#blogTag").focus();
                alert("请填写标签");
                return ;
            }
            if (formData.blogMd == null || formData.blogMd == ""){
                $("#msg").show();
                $("#msg").text("文章内容为空");
                $("#msg").css("color","red");
                setTimeout(function () {
                    $("#msg").hide();
                },4500);
                return ;
            }
            $.ajax({
                url:"/save",
                type:"POST",
                data:formData,
                dataType:"json",
                error:function () {
                    $("#msg").show();
                    $("#msg").text("保存请求失败，请稍后再试");
                    $("#msg").css("color","red");
                    setTimeout(function () {
                        $("#msg").hide();
                    },4500);
                },
                success:function (data) {
                    $("#msg").show();
                    $("#msg").text(data.msg);
                    if (data.success == 1){
                        $("#blogId").val(data.blogId);
                        $("#msg").css("color","#0F0");
                    }else{
                        $("#msg").css("color","red");
                    }
                    setTimeout(function () {
                        $("#msg").hide();
                        $("#msg").removeClass("success");
                    },4500);
                }

            });
        }

        //初始化编辑器
        var testEditor;
        $(function() {
            //$.get('/test.md', function(md){
                testEditor = editormd("editormd", {
                    //width: "90%",
                    //height: 550,
                    path : '/static/editormd/lib/',
                    theme : "dark",
                    previewTheme : "dark",
                    editorTheme : "pastel-on-dark",
                    //markdown : md,  //要加载的md信息/文件，通常和ajax请求配合获取数据
                    codeFold : true,
                    //syncScrolling : false,
                    //saveHTMLToTextarea : true,    // 保存 HTML 到 Textarea
                    searchReplace : true,
                    //watch : false,                // 关闭实时预览
                    //htmlDecode : "style,script,iframe|on*",            // 开启 HTML 标签解析，为了安全性，默认不开启
                    //toolbar  : false,             //关闭工具栏
                    //previewCodeHighlight : false, // 关闭预览 HTML 的代码块高亮，默认开启
                    emoji : true,
                    taskList : true,
                    //tocm            : true,         // Using [TOCM] 下拉菜单目录
                    //tocDropdown   : false,
                    //tocContainer : "",             //自定义ToC容器，和CSS选择器一样选择容器
                    tex : true,                   // 开启科学公式TeX语言支持，默认关闭
                    flowChart : true,             // 开启流程图支持，默认关闭
                    sequenceDiagram : true,       // 开启时序/序列图支持，默认关闭,
                    //dialogLockScreen : false,   // 设置弹出层对话框不锁屏，全局通用，默认为true
                    //dialogShowMask : false,     // 设置弹出层对话框显示透明遮罩层，全局通用，默认为true
                    //dialogDraggable : false,    // 设置弹出层对话框不可拖动，全局通用，默认为true
                    //dialogMaskOpacity : 0.4,    // 设置透明遮罩层的透明度，全局通用，默认值为0.1
                    //dialogMaskBgColor : "#000", // 设置透明遮罩层的背景颜色，全局通用，默认为#fff
                    imageUpload : true,
                    imageFormats : ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
//                    imageUploadURL : "./php/upload.php",
                    imageUploadURL : "/upImage",
                    /*onload : function() {  //onload后执行的函数
                        //console.log('onload', this);
                        //this.fullscreen();
                        //this.unwatch();
                        //this.watch().fullscreen();

                        //this.setMarkdown("#PHP");
                        //this.width("100%");
                        //this.height(480);
                        //this.resize("100%", 640);
                    }*/
                //});
            });

//            $("#goto-line-btn").bind("click", function(){
//                testEditor.gotoLine(90);
//            });
//
//            $("#show-btn").bind('click', function(){
//                testEditor.show();
//            });
//
//            $("#hide-btn").bind('click', function(){
//                testEditor.hide();
//            });
//
//            $("#get-md-btn").bind('click', function(){
//                alert(testEditor.getMarkdown());
//            });
//
//            $("#get-html-btn").bind('click', function() {
//                alert(testEditor.getHTML());
//            });
//
//            $("#watch-btn").bind('click', function() {
//                testEditor.watch();
//            });
//
//            $("#unwatch-btn").bind('click', function() {
//                testEditor.unwatch();
//            });
//
//            $("#preview-btn").bind('click', function() {
//                testEditor.previewing();
//            });
//
//            $("#fullscreen-btn").bind('click', function() {
//                testEditor.fullscreen();
//            });
//
//            $("#show-toolbar-btn").bind('click', function() {
//                testEditor.showToolbar();
//            });
//
//            $("#close-toolbar-btn").bind('click', function() {
//                testEditor.hideToolbar();
//            });
//
//            $("#toc-menu-btn").click(function(){
//                testEditor.config({
//                    tocDropdown   : true,
//                    tocTitle      : "目录 Table of Contents",
//                });
//            });
//
//            $("#toc-default-btn").click(function() {
//                testEditor.config("tocDropdown", false);
//            });
        });


    </script>
</body>
</html>
