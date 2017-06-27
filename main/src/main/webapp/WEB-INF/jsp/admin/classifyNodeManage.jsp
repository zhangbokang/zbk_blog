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
<style>
    #classifyNodeOption .row{
        padding-bottom: 10px;
    }
</style>
<div id="classifyNodeOption">
    <div class="row">
        <input type="hidden" id="classifyNodeId"/>
        <label for="classifyNodeText">分类节点名称：</label>
        <input type="text" id="classifyNodeText" class="form-control">
    </div>
    <div class="row">
        <button class="btn btn-success" id="add_classifyNode">增加</button>
        <button class="btn btn-warning" id="toParent_classifyNode">添加到选择节点</button>
    </div>
    <div class="row">
        <button class="btn btn-warning" id="edit_classifyNode">修改</button>
        <button class="btn btn-danger" id="delete_classifyNode">删除</button>
    </div>
</div>

<div class="panel panel-default">
    <div class="panel-heading">分类树列表</div>
    <div class="panel-body">
        <div id="classifyNodeList"></div>
    </div>
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
        $("#add_classifyNode").on("click",function () {
            var $classifyNodeText = $("#classifyNodeText");
            var classifyNodeText = $classifyNodeText.val();
            if (!$.trim(classifyNodeText)){
                $classifyNodeText.focus();
                alert("请输入分类节点名称");
                return;
            }
            $.ajax({
                url: "/classifyNode/addClassifyNode",
                data: {classifyNodeText: classifyNodeText},
                type: "POST",
                dataType: "json",
                success: function (result) {
                    if (result.code == 1) {
                        $('#classifyNodeList').jstree(true).refresh();
                        $classifyNodeText.val("");
                        return;
                    }
                    if (result.code == 0) {
                        alert(result.msg);
                        return;
                    }

                }
            });
        });
        //修改目录节点

        //删除目录节点
        $("#delete_classifyNode").on("click", function () {
            var ref = $('#classifyNodeList').jstree(true),
                    sel = ref.get_selected();
            if (!sel.length) {
                alert("请选择分类节点！");
                return false;
            }
//            ref.delete_node(sel);
            deleteAjax(sel[0], "");
        });
        function deleteAjax(classifyNodeId,deleteOk) {
            $.ajax({
                url: "/classifyNode/deleteClassifyNode",
                data: {classifyNodeId: classifyNodeId,deleteOk:deleteOk},
                type: "POST",
                dataType: "json",
                success: function (result) {
                    if (result.code == 1) {
                        if (typeof result.data == "string"){
                            alert(result.data);
                            return;
                        }
                        $('#classifyNodeList').jstree(true).refresh();
                        return;
                    }
                    if (result.code == 0) {
                        alert(result.msg);
                        return;
                    }

                }
            });
        }
    });

</script>