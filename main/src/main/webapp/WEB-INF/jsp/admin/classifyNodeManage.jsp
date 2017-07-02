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
    <%--<div class="row">--%>
        <%--<input type="hidden" id="classifyNodeId"/>--%>
        <%--<label for="classifyNodeText">分类节点名称：</label>--%>
        <%--<input type="text" id="classifyNodeText" class="form-control">--%>
    <%--</div>--%>
    <%--<div class="row">--%>
        <%--<button class="btn btn-success" id="addRoot_classifyNode">创建根节点</button>--%>
    <%--</div>--%>
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
                    "url" : "/findClassifyNodeByParentId",
                    "dataType" : "json", // needed only if you do not supply JSON headers
                    "type":"POST",
                    "data" : function (node) {
//                        return { "id" : node.id };
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
//        $('#classifyNodeList').on("changed.jstree", function (e, data) {//changed.jstree表示所有事件
//        $('#classifyNodeList').on("select_node.jstree", function (e, data) {
//            console.log(data.selected);
//            loadBlogList('/findDocByClassifyId?accessType=classify&classifyId='+data.selected,{});
//        });
        //刷新目录树
        $("#refresh_classifyNode").on("click", function () {
            $('#classifyNodeList').jstree(true).refresh();
        });

        //移动节点
        $("#move_classifyNode").on("click", function () {
            var $mcinput = $("#move_classifyNode_input");
            var ref = $('#classifyNodeList').jstree(true),
                    sel = ref.get_selected();
            if (!sel.length) {
                alert("请选择分类节点！");
                return;
            }
            var nodeId = $mcinput.val();
            if (!$.trim(nodeId)) {
                $mcinput.val(sel[0]);
//                alert("请选择要添加到那个节点后再点击移动。");
                return;
            }
            if (nodeId == sel[0]) {
                var falg = confirm("要移动该节点到根节点吗？");
                if (falg){
                    appendNodeTo(ref, "#", nodeId, function () {});
                    ref.refresh();
                    $mcinput.val("");
                }
            }
        });
        //配合移动按钮移动节点
        $('#classifyNodeList').on("select_node.jstree", function (e, data) {
            var parentId = data.selected[0];
            var $mcinput = $("#move_classifyNode_input");
            var nodeId = $mcinput.val();
            if (!$.trim(nodeId) || nodeId == parentId){
                return;
            }
            var ref = $('#classifyNodeList').jstree(true);
            appendNodeTo(ref, parentId, nodeId, function () {});
            ref.refresh();
            $mcinput.val("");
        });

        /**
         * 添加节点到其他节点
         *
         * @param ref
         * @param parentId
         * @param cId
         * @param call 失败后的回调函数
         */
        function appendNodeTo(ref,parentId,cId,call) {
            var data = {};
            data.parentClassifyNodeId = parentId;
            data.classifyNodeId = cId;
            $.ajax({
                url: "/classifyNode/appendToParentClassifyNode",
                type: "POST",
                async:false,
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.code == 1) {
                        return;
                    }
                    if (typeof call == "function") {
                        call();
                    }else {
                        del_node(ref, cId);
                    }
                    alert(result.msg);
                    return ;
                },
                error: function () {
                    if (typeof call == "function") {
                        call();
                    }else {
                        del_node(ref, cId);
                    }
                    alert("添加子节点失败");
                    return ;
                }
            });
        }
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
                async:false,
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
            if (typeof defaultNode.id =="undefined"){
                alert("创建节点失败");
                return ;
            }
            //创建默认节点end
            if (!sel.length) {
                sel = ref.create_node("#",defaultNode,"first");
                if (sel) {
                    ref.edit(sel, sel.text, function (node) {
                        edit_node(ref, node, function () {//重写失败时的回调
                            del_node(ref, node.id);
                        });
                    });
                }
                return ;
            }
            sel = sel[0];
            sel = ref.create_node(sel, defaultNode,"first",function () {
                appendNodeTo(ref,sel, defaultNode.id);
            });
            if (sel) {
                ref.edit(sel,sel.text,function (node) {
                    edit_node(ref,node,function () {
                        del_node(ref, sel);
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
         * 修改节点的函数
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
                del_node(ref,sel[i]);
            }

        });
        /**
         * 删除节点的函数
         * @param ref jsTree对象
         * @param nodeId 要删除的节点的Id
         */
        function del_node(ref,nodeId) {
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
                        ref.delete_node(tmpSel);
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
        }
    });

</script>