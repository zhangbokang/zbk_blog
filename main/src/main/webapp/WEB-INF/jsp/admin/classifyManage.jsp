<%--
  Created by IntelliJ IDEA.
  User: zhangbokang
  Date: 2017/5/18
  Time: 22:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta charset="UTF-8">
<style rel="stylesheet">
    .classifyMake{
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
<div id="classifyManage">
    <div><button class="btn btn-default" onclick="isMake();$('#classifyMake').show();">新增</button>
        <button onclick="loadClassifyTable();" class="btn btn-default">刷新</button></div><br />
    <table id="classifyTable"></table>
</div>
<div id="classifyMake" class="classifyMake">
    <label for="theName">名称</label>
    <input type="text" id="theName" class="form-control"><br />
    <button id="ok_btn" onclick="noMake();" class="btn btn-success">确定添加</button>&nbsp;
    <button id="clean_btn" onclick="noMake();$('#classifyMake').hide();" class="btn btn-warning">取消添加</button>
</div>
<div id="classifyDelete" class="classifyMake">
    <input id="classifyDeleteId" type="hidden">
    <label>确认删除"<span></span>"吗？</label>
    <button onclick="deleteClassifyManage($('#classifyDeleteId').val());noMake();$('#classifyDelete').hide()" class="btn btn-default">确定</button>
    <button onclick="noMake();$('#classifyDelete').hide()" class="btn btn-default">取消</button>
</div>
<script>
    $(function () {
        $("#classifyMake #ok_btn").click(function () {
            var classifyName = $("#classifyMake #theName").val();
            $.ajax({
                url:"/classify/addClassify",
                type:"POST",
                data:{"classifyName":classifyName},
                dataType:"json",
                success:function (result) {
                    if (result.code == 1){
                        var rows = [];
                        rows.push(result.data);
                        $('#classifyTable').bootstrapTable("append",rows);
//                        loadClassifyTable();
                        return;
                    }
                    alert(result.msg);
                },
                error:function () {
                    alert("保存失败，请求发生错误！");
                }
            });
            $("#classifyMake").hide();
        });
    });
    $('#classifyTable').bootstrapTable({
        //url: 'data.json',
        columns:common.COLUMNS.classify,
    });
    //刷新表格
    function loadClassifyTable() {
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
    loadClassifyTable();

    //删除分类确认框
    function deleteClassifyMake(id,name) {
        isMake();
        $("#classifyDelete").show();
        $("#classifyDeleteId").val(id);
        $("#classifyDelete label span").text(name);
    }
    //删除分类
    function deleteClassifyManage(classifyId) {
        $.ajax({
            url:common.URL.classify.deleteClassify + "?classifyId="+classifyId,
            type:"GET",
            dataType:"json",
            success:function (result) {
                if (result.code == 1){
                    var classifyIdNum = parseInt(classifyId);
                    $("#classifyTable").bootstrapTable("remove",{
                        field: 'classifyId',
                        values: [classifyIdNum]
                    });
                    return;
                }
                alert(result.msg);
            },
            error:function () {
                alert("请求出现问题！");
                return;
            }
        });
    }
</script>