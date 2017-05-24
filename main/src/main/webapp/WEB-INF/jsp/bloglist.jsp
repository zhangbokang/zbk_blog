<%--
  Created by IntelliJ IDEA.
  User: zhangbokang
  Date: 2017/5/4
  Time: 14:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<meta charset="utf-8">
<div id="doclist">
    <ul class="list-group">

        <c:forEach items="${docList}" var="doc">
            <li class="list-group-item">
                <a class="" href="/doc/docPage?docId=${doc.docId}">
                    <span class="h3">${doc.title}</span>
                    <c:set var="docMdStr1" value="${fn:substring(doc.docMd,0,60)}" />
                    <c:set var="docMdStr2" value="${fn:replace(docMdStr1, '<', '&lt;')}" />
                    <c:set var="docMdStrR" value="${fn:replace(docMdStr2, '>', '&gt;')}" />
                    <p>${docMdStrR} ...</p>
                </a>
                <div class="row">
                    <div class="col-sm-5">分类：${doc.classify.name}</div>
                    <div class="col-sm-7">标签：${doc.tag.name}</div>
                </div>
                <div class="row">
                    <jsp:useBean id="dataValue" class="java.util.Date" />
                    <jsp:setProperty name="dataValue" property="time" value="${doc.updateTime}" />
                    <div class="col-sm-6"><strong>最后更新&nbsp;<fmt:formatDate value="${dataValue}" type="both" /></strong></div>
                    <div class="col-sm-4 col-sm-offset-2">
                        <span class="badge glyphicon glyphicon-eye-open">&nbsp;${doc.openNumber==null?0:doc.openNumber}</span>
                        <%--<a href="#">--%>
                            <span class="badge glyphicon glyphicon-thumbs-up">&nbsp;${doc.favorNumber==null?0:doc.favorNumber}</span>
                        <%--</a>--%>
                    </div>
                </div>
            </li>
        </c:forEach>

    </ul>
    <div id="paging">
        <ul class="pagination">
            <li><a href="#">&laquo;</a></li>
            <li><a href="#">1</a></li>
            <li><a href="#">2</a></li>
            <li><a href="#">3</a></li>
            <li><a href="#">4</a></li>
            <li><a href="#">5</a></li>
            <li><a href="#">5</a></li>
            <li><a href="#">5</a></li>
            <li><a href="#">5</a></li>
            <li><a href="#">5</a></li>
            <li><a href="#">5</a></li>
            <li><a href="#">5</a></li>
            <li><a href="#">5</a></li>
            <li><a href="#">5</a></li>
            <li><a href="#">&raquo;</a></li>
        </ul>
    </div>
</div>
