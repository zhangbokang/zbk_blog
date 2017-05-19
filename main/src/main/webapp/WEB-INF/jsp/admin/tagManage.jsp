<%--
  Created by IntelliJ IDEA.
  User: zhangbokang
  Date: 2017/5/18
  Time: 22:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta charset="UTF-8">
<style rel="stylesheet">
    #tagMake{
        border: #8F938F 1px solid;
        width: 500px;
        height: 200px;
        background-color:#FFF;
        margin: auto;
        position: fixed;
        z-index:99;
        top: 0;
        bottom: 0;
        left: 0;
        right: 0;
        padding: 20px;
        display:none;
    }
</style>
<div id="tagManage">
    <div><button class="btn btn-default" onclick="isMake();$('#tagMake').show();">新增</button>
        <button onclick="loadTable();" class="btn btn-default">刷新</button></div><br />
    <table id="tagTable"></table>
</div>

<div id="tagMake">
    <label for="theName">名称</label>
    <input type="text" id="theName" class="form-control"><br />
    <button id="ok_btn" onclick="noMake();" class="btn btn-success">确定添加</button>&nbsp;
    <button id="clean_btn" onclick="noMake();" class="btn btn-warning">取消添加</button>
</div>

<script>
    $(function () {
        $("#tagMake #ok_btn").click(function () {
            var tagName = $("#tagMake #theName").val();
            $.ajax({
                url:"/tag/addTag",
                type:"POST",
                data:{"tagName":tagName},
                dataType:"json",
                success:function (data) {
                    if (data.code == 1){
                        loadTable();
                        return;
                    }
                    alert(data.msg);
                },
                error:function () {
                    alert("保存失败，请求发生错误！");
                }
            });
            $("#tagMake").hide();
        });
    });
    $('#tagTable').bootstrapTable({
        //url: 'data.json',
        columns:common.COLUMNS.tag
    });
    //刷新表格
    function loadTable() {
        $.ajax({
            url:"/tag/findAllTag",
            type:"GET",
            dataType:"json",
            success:function (result) {
                if (result.code == 1){
                    var tagData = result.data;
                    $('#tagTable').bootstrapTable("load",tagData);
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