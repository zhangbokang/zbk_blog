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
    #allMake{
        /*position: fixed;*/
        /*display: none*/
        /*width: 100%;*/
        /*height: 100%;*/
        /*z-index: 999;*/
        width:100%;
        height:100%;
        background-color:#000;
        position:fixed;
        top:0;
        left:0;
        z-index:98;
        opacity:0.3;
        /*兼容IE8及以下版本浏览器*/
        filter: alpha(opacity=30);
        /*display:none;*/
    }
    #makeBody{
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
        opacity:1;
        /*display:none;*/
    }
</style>
<div id="tagManage">
    <div><button class="btn btn-default">新增</button>
        <button onclick="loadTable();" class="btn btn-default">刷新</button></div><br />
    <table id="tagTable"></table>
</div>

<%--遮罩--%>
<div id="allMake"></div>
    <div id="makeBody">
        <div>
            <label for="tagName">标签名称</label>
            <input id="tagName" class="form-control">
        </div>
    </div>

<script>
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