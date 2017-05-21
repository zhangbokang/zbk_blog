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
    <div><a href="/doc/editDoc" class="btn btn-default">新增</a>&nbsp;<button onclick="loadDocTable();" class="btn btn-default">刷新</button></div><br />
    <table id="docTable"></table>
</div>
<script>
    $('#docTable').bootstrapTable({
        //url: 'data.json',
        columns:common.COLUMNS.doc
    });
    //刷新表格
    function loadDocTable() {
        $.ajax({
            url:"/doc/findAllDoc",
            type:"GET",
            dataType:"json",
            success:function (result) {
                if (result.code == 1){
                    var docData = result.data;
                    for (var i in docData){
                        docData[i].classifyName = docData[i]["classify"]["name"];
                        docData[i].tagName = docData[i]["tag"]["name"];
                    }
                    $('#docTable').bootstrapTable("load",docData);
                    return;
                }
                alert(result.msg);
            },
            error: function () {
                alert("请求出现问题！");
                return;
            }
        });

    }
    loadDocTable();

</script>