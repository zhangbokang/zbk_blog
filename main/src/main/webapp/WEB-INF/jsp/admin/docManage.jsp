<%--
  Created by IntelliJ IDEA.
  User: zhangbokang
  Date: 2017/5/18
  Time: 22:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta charset="UTF-8">
<div id="docManage">
    <div><a href="/doc/editDoc" class="btn btn-default">新增</a>&nbsp;
        <button onclick="$('#docTable').bootstrapTable('refresh',{silent: true})" class="btn btn-default">刷新</button></div><br />
    <table id="docTable"></table>
</div>
<script>
    $('#docTable').bootstrapTable({
        url:common.URL.doc.findAllDoc,
        striped:true,
        sidePagination:"server", //分页方式为server
        pageList:"[15, 30, 50, 100, All]", //分页可选的每页数据条数
        pageSize:15,//如果设置了分页，页面数据条数
        pagination:"true", //在表格底部显示分页条
        responseHandler:function (res) {//加载服务器数据之前的处理程序，可以用来格式化数据
            var rows = res.rows;
            for (var i in rows){
                rows[i].classifyNodeName = "";
                for(var x in rows[i].classifyNodes){
                    rows[i].classifyNodeName = rows[i].classifyNodeName + x["text"] + "  ";
                }
                rows[i].tagName = rows[i]["tag"]["name"];
            }
            res.rows = rows;
            return res;
        },
        columns:common.COLUMNS.doc
    });
    //刷新表格
//    function loadDocTable() {
//        $.ajax({
//            url:"/doc/findAllDocOutJson",
//            type:"GET",
//            dataType:"json",
//            success:function (result) {
//                if (result.code == 1){
//                    var docData = result.data;
//                    for (var i in docData){
//                        docData[i].classifyName = docData[i]["classify"]["name"];
//                        docData[i].tagName = docData[i]["tag"]["name"];
//                    }
//                    $('#docTable').bootstrapTable("load",docData);
//                    return;
//                }
//                alert(result.msg);
//            },
//            error: function () {
//                alert("请求出现问题！");
//                return;
//            }
//        });
//
//    }
//    loadDocTable();

</script>