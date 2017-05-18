<%--
  Created by IntelliJ IDEA.
  User: zhangbokang
  Date: 2017/5/18
  Time: 22:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta charset="UTF-8">
<div id="classifyManage">
    <div><button class="btn btn-default">新增</button>
        <button onclick="loadTable();" class="btn btn-default">刷新</button></div><br />
    <table id="classifyTable"></table>
</div>
<script>
    $('#classifyTable').bootstrapTable({
        //url: 'data.json',
        columns:common.COLUMNS.classify
    });
    //刷新表格
    function loadTable() {
        $.ajax({
            url:"/classify/findAllClassify",
            type:"GET",
            dataType:"json",
            success:function (result) {
                if (result.code == 1){
                    var classifyData = result.data;
                    $('#classifyTable').bootstrapTable("load",classifyData);
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
    loadTable();

</script>