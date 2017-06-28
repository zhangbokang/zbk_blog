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
        <button class="btn btn-success" id="addRoot_classifyNode">创建根节点</button>
        <button class="btn btn-warning" id="toParent_classifyNode">添加到选择节点</button>
    </div>
    <div class="row">
        <button class="btn btn-success" id="refresh_classifyNode">
            <i class="glyphicon glyphicon-refresh"></i>刷新</button>
        <button class="btn btn-success" id="add_classifyNode">
            <i class="glyphicon glyphicon-asterisk"></i>创建</button>
        <button class="btn btn-warning" id="edit_classifyNode">
            <i class="glyphicon glyphicon-pencil"></i>修改</button>
        <%--暂存移动节点的ID--%>
        <input type="hidden" id="move_classifyNode_input">
        <button class="btn btn-warning" id="move_classifyNode">
            <i class="glyphicon glyphicon-move"></i>移动</button>
        <button class="btn btn-danger" id="delete_classifyNode">
            <i class="glyphicon glyphicon-remove"></i>删除</button>
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
//                "animation": 0,
//                'force_text': true,
                "check_callback": true, //启用所有修改，可以增删改
//                "themes": {"stripes": true},
                'data' : {
                    "url" : "/findClassifyByParentId",
                    "dataType" : "json", // needed only if you do not supply JSON headers
                    "data" : function (node) {
//                        return { "id" : node.id };
//                        console.log(node);
                        return node;
                    }
                }
            },
            "plugins" : [
//                "checkbox",
//                "contextmenu",
//                "dnd",
//                "massload",
//                "search",
//                "sort",
//                "state",
//                "types", //用来支持自定义节点类型，配合jstree({"type":{"deftule":{}}})中的types使用
                "unique", //防止重名
//                "wholerow", //去除fie点前面的虚线
//                "changed",
//                "conditionalselect"
            ]
        });
        $('#classifyNodeList').on("changed.jstree", function (e, data) {//changed.jstree表示所有事件
            console.log(data.selected);
//            loadBlogList('/findDocByClassifyId?accessType=classify&classifyId='+data.selected,{});
        });
        //刷新目录树
        $("#refresh_classifyNode").on("click", function () {
            $('#classifyNodeList').jstree(true).refresh();
        });
        //新增根节点
        $("#addRoot_classifyNode").on("click",function () {
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
        //新增节点
        $("#add_classifyNode").on("click", function () {
            var ref = $('#classifyNodeList').jstree(true),
                sel = ref.get_selected();
            var defaultNode = {};
            //先发请求创建一个默认节点，拿到服务器返回的节点ID
            $.ajax({
                url: "/classifyNode/addClassifyNode",
                data: {classifyNodeText: "新建节点"},
                type: "POST",
                dataType: "json",
                success: function (result) {
                    if (result.code == 1) {
//                        $('#classifyNodeList').jstree(true).refresh();
//                        $classifyNodeText.val("");
                        defaultNode = result.data;
                        return;
                    }
                    if (result.code == 0) {
                        alert(result.msg);
                    }
                },
                error:function () {
                    alert("创建失败！");
                }
            });
            //创建默认节点end
            if (!sel.length) {
                sel = ref.create_node("#",defaultNode);
                if (sel) {
                    ref.edit(sel)
                }
                return false;
            }
            sel = sel[0];
            sel = ref.create_node(sel, defaultNode);
            var falg;
            if (sel) {
                ref.edit(sel,sel.text,function (node) {
                    edit_node(ref,node,function () {
                        
                    });
                });
            }
        });
        //修改目录节点
        $("#edit_classifyNode").on("click", function () {
            var ref = $('#classifyNodeList').jstree(true),
                sel = ref.get_selected();
            if(!sel.length) {
                alert("请选择分类节点！");
                return false;
            }
            sel = sel[0];
            ref.edit(sel,sel.text,function (node) {
                edit_node(ref,node)
            });
        });
        /**
         * 修改节点的回调函数
         * @param ref jsTree对象
         * @param node 回调传的节点
         * @param call 失败时的回调，默认刷新jsTree
         */
        function edit_node(ref,node,call) {
            var data = {};
            data.classifyNodeId = node.id;
            data.classifyNodeText = node.text;
            $.ajax({
                url:"/classifyNode/updateClassifyNode",
                data:data,
                type:"POST",
                dataType:"json",
                success:function (result) {
                    if (result.code == 1){
                        return;
                    }
                    alert("修改失败，"+result.msg);
                    if (typeof call == "function"){
                        call();
                    }else{
                        ref.refresh();
                    }
                },
                error:function () {
                    alert("修改失败！");
                    if (typeof call == "function"){
                        call();
                    }else{
                        ref.refresh();
                    }
                }
            });
        }
        //删除目录节点
        $("#delete_classifyNode").on("click", function () {
            var ref = $('#classifyNodeList').jstree(true),
                    sel = ref.get_selected();
            if (!sel.length) {
                alert("请选择分类节点！");
                return false;
            }

            for (var i=0;i<sel.length;i++){

            }

        });
        function del_node(nodeId) {
            var tmpSel = [];
            $.ajax({
                url: "/classifyNode/deleteClassifyNode",
                data: {classifyNodeId: nodeId,deleteOk:""},
                type: "POST",
                async:false,
                dataType: "json",
                success: function (result) {
                    if (result.code == 1) {
                        if (typeof result.data == "string"){
                            alert(nodeId+"删除失败，该节点还有子节点！");
                            return;
                        }
                        tmpSel.push(nodeId);
                        return;
                    }
                    if (result.code == 0) {
                        alert(nodeId+"删除失败，"+result.msg);
                        return;
                    }
                },
                error:function () {
                    alert(nodeId+"删除失败！");
                }
            });
            ref.delete_node(tmpSel);
        }
    });

</script>