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
    .tagMake{
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
        <button onclick="loadTagTable();" class="btn btn-default">刷新</button></div><br />
    <table id="tagTable"></table>
</div>

<div id="tagMake" class="tagMake">
    <label for="theName">名称</label>
    <input type="text" id="theName" class="form-control"><br />
    <button id="ok_btn" onclick="noMake();" class="btn btn-success">确定添加</button>&nbsp;
    <button id="clean_btn" onclick="noMake();$('#tagMake').hide();" class="btn btn-warning">取消添加</button>
</div>
<div id="tagDelete" class="tagMake">
    <input id="tagDeleteId" type="hidden">
    <label>确认删除"<span></span>"吗？</label>
    <button onclick="deleteTagManage($('#tagDeleteId').val());noMake();$('#tagDelete').hide()" class="btn btn-default">确定</button>
    <button onclick="noMake();$('#tagDelete').hide()" class="btn btn-default">取消</button>
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
                success:function (result) {
                    if (result.code == 1){
                        var rows = [];
                        rows.push(result.data);
                        $('#tagTable').bootstrapTable("append",rows);
                        $("#tagMake input[type='text']").val("");
                        return;
                    }
                    alert(result.msg);
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
    function loadTagTable() {
        $.ajax({
            url:common.URL.tag.findAllTag,
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
    loadTagTable();

    //删除分类确认框
    function deleteTagMake(id,name) {
        isMake();
        $("#tagDelete").show();
        $("#tagDeleteId").val(id);
        $("#tagDelete label span").text(name);
    }
    //删除分类
    function deleteTagManage(tagId) {
        $.ajax({
            url:common.URL.tag.deleteTag + "?tagId="+tagId,
            type:"GET",
            dataType:"json",
            success:function (result) {
                if (result.code == 1){
                    var tagIdNum = parseInt(tagId);
                    $("#tagTable").bootstrapTable("remove",{
                        field: 'tagId',
                        values: [tagIdNum]
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