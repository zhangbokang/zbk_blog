<%--
  Created by IntelliJ IDEA.
  User: zhangbokang
  Date: 2017/5/4
  Time: 14:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<meta charset="utf-8">
<style rel="stylesheet">
    #tagsList {
        position: relative;
        height: 300px;
        margin: -15px auto;
    }

    #tagsList a {
        position: absolute;
        top: 0px;
        left: 0px;
        font-family: Microsoft YaHei;
        font-weight: bold;
        text-decoration: none;
        padding: 3px 6px;
    }

    #tagsList a:hover {
        color: #FF0000;
        letter-spacing: 2px;
    }
    .panel-body a:hover{
        text-decoration: none;
    }
    .panel-body li>div:hover{
        background-color: #f7f7f7;
    }
</style>
<!-- 分类列表、标签列表等列表 -->
<div class="panel panel-default">
    <div class="panel-heading">分类列表</div>
    <div class="panel-body">
        <ul class="list-unstyled">
            <c:forEach var="classify" varStatus="status" items="${classifyList}">
                <li>
                    <a href="#" onclick="loadBlogList('/findDocByClassifyId?accessType=classify&classifyId=${classify.classifyId}');">${classify.name}</a>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>
<div class="panel panel-default">
    <div class="panel-heading">标签地图</div>
    <div class="panel-body">
        <div id="tagsList">
            <c:forEach var="tag" items="${tagList}">
                <span>
                    <a href="#" onclick="loadBlogList('/findDocByTagId?accessType=tag&tagId=${tag.tagId}');">${tag.name}</a>
                </span>
            </c:forEach>
        </div>
    </div>
</div>
<div class="panel panel-default">
    <div class="panel-heading">最新文章</div>
    <div class="panel-body">
        <ul class="list-unstyled">
            <c:forEach var="doc" varStatus="status" items="${zxwz}">
                <li>
                    <div><a href="/doc/docPage?docId=${doc.docId}">${status.count}.${doc.title}</a>
                        <div style="text-align: right">
                            <jsp:useBean id="dataValue" class="java.util.Date"/>
                            <jsp:setProperty name="dataValue" property="time" value="${doc.updateTime}"/>
                            <fmt:formatDate value="${dataValue}" type="both"/>
                        </div>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>
<div class="panel panel-default">
    <div class="panel-heading">阅读排行</div>
    <div class="panel-body">
        <ul class="list-unstyled">
            <c:forEach var="doc" varStatus="status" items="${ydph}">
                <li>
                    <div class="row">
                        <div class="col-sm-9"><a href="/doc/docPage?docId=${doc.docId}">${status.count}.${doc.title}</a></div>
                        <div class="col-sm-3" style="text-align: right"><span class="badge glyphicon glyphicon-eye-open">&nbsp;${doc.openNumber==null?0:doc.openNumber}</span></div>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>
<div class="panel panel-default">
    <div class="panel-heading">点赞排行</div>
    <div class="panel-body">
        <ul class="list-unstyled">
            <c:forEach var="doc" varStatus="status" items="${dzph}">
                <li>
                    <div class="row">
                        <div class="col-sm-9"><a href="/doc/docPage?docId=${doc.docId}">${status.count}.${doc.title}</a></div>
                        <div class="col-sm-3" style="text-align: right"><span class="badge glyphicon glyphicon-thumbs-up pull-right">&nbsp;${doc.favorNumber==null?0:doc.favorNumber}</span></div>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>
<script src="/static/js/tagcloud.js"></script>