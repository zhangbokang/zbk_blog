<%@ page import="com.zbkblog.utils.Web" %><%--
  Created by IntelliJ IDEA.
  User: zhangbokang
  Date: 2017/6/27
  Time: 10:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta charset="UTF-8">
<link rel="stylesheet" href="<%=Web.staticLoadDomain%>/static/jsTree/themes/default/style.min.css" />

<div class="panel panel-default">
    <div class="panel-heading">分类树列表</div>
    <div class="panel-body">
        <div id="classifyNodeList"></div>
    </div>
</div>
<div id="classifyNodeOption">
    <input type="hidden" id="classifyNodeId"/>
    <label for="classifyNodeText">分类节点名称：</label>
    <input type="text" id="classifyNodeText" class="form-control">
    <br />
    <button class="btn btn-success">增加</button>
    <button class="btn btn-warning">修改</button>
    <button class="btn btn-danger">删除</button>
    <%--添加分类节点到其他分类节点--%>
    <button class="btn btn-warning">添加该节点到</button>
</div>

<script>
    $(function () {
        //初始化目录树
        $('#classifyNodeList').jstree({
            'core' : {
                'data' : {
                    "url" : "/findClassifyByParentId",
                    "dataType" : "json", // needed only if you do not supply JSON headers
                    "data" : function (node) {
//                        return { "id" : node.id };
//                        console.log(node);
                        return node;
                    }
                }
            }
        });
        $('#classifyNodeList').on("changed.jstree", function (e, data) {//changed.jstree表示所有事件
            console.log(data.selected);
//            loadBlogList('/findDocByClassifyId?accessType=classify&classifyId='+data.selected,{});
        });
        //新增目录节点
        function addClassifyNode() {
            var $classifyNodeText = $("#classifyNodeText");
            var classifyNodeText = $classifyNodeText.val();
            if (!$.trim(classifyNodeText)){
                $classifyNodeText.focus();
                alert("请输入分类节点名称");
                return;
            }
            $.ajax({
                url:"/classifyNode/addClassifyNode",
                data:{classifyNodeText:classifyNodeText},
                type:"POST",
                success:function (result) {
                    if (result.code == 0){
                        alert(result.msg);
                        return;
                    }

                }
            })

        }
        //修改目录节点

        //删除目录节点

    });
</script>