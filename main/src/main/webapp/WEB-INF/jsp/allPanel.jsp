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
<!-- 分类列表、标签列表等列表 -->
<div class="panel panel-default">
    <div class="panel-heading">分类列表</div>
    <div class="panel-body">
        <ul class="list-unstyled">
            <c:forEach var="classify" varStatus="status" items="${classifyList}">
                <li>
                    <a href="/findDocByClassifyId?classifyId=${classify.classifyId}">${classify.name}</a>
                </li>
            </c:forEach>
            <%--<li><a href="#">Hadeep</a> </li>--%>
            <%--<li><a href="#">Java</a> </li>--%>
        </ul>
    </div>
</div>
<div class="panel panel-default">
    <div class="panel-heading">标签地图</div>
    <div class="panel-body"></div>
</div>
<div class="panel panel-default">
    <div class="panel-heading">最新文章</div>
    <div class="panel-body">
        <ul class="list-unstyled">
            <c:forEach var="doc" varStatus="status" items="${zxwz}">
            <li>
                <a href="/doc/docPage?docId=${doc.docId}">${status.count}.${doc.title}</a>
                <jsp:useBean id="dataValue" class="java.util.Date" />
                <jsp:setProperty name="dataValue" property="time" value="${doc.updateTime}" />
                <span class="pull-right"><fmt:formatDate value="${dataValue}" type="both" /></span>
            </li>
            </c:forEach>
        </ul>
    </div>
</div>
<%--<div class="panel panel-default">--%>
    <%--<div class="panel-heading">推荐阅读</div>--%>
    <%--<div class="panel-body"></div>--%>
<%--</div>--%>
<div class="panel panel-default">
    <div class="panel-heading">阅读排行</div>
    <div class="panel-body">
        <ul class="list-unstyled">
            <c:forEach var="doc" varStatus="status" items="${ydph}">
                <li>
                    <a href="/doc/docPage?docId=${doc.docId}">${status.count}.${doc.title}</a>
                    <span class="badge glyphicon glyphicon-eye-open pull-right">&nbsp;${doc.openNumber==null?0:doc.openNumber}</span>
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
                    <a href="/doc/docPage?docId=${doc.docId}">${status.count}.${doc.title}</a>
                    <span class="badge glyphicon glyphicon-thumbs-up pull-right">&nbsp;${doc.favorNumber==null?0:doc.favorNumber}</span>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>