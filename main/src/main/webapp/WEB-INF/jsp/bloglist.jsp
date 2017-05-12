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
<meta charset="utf-8">
<div id="bloglist">
    <ul class="list-group">

        <c:forEach items="${pageInfo.list}" var="blogDoc">
            <li class="list-group-item">
                <a class="" href="blogpage?blogId=${blogDoc.blogId}">
                    <span class="h3">${blogDoc.blogTitle}</span>
                    <c:set var="blogMdStr" value="${fn:substring(blogDoc.blogMd,0,60)}" />
                    <p> ...</p>
                </a>
                <div class="row">
                    <div class="col-sm-5">分类：${blogDoc.blogClass}</div>
                    <div class="col-sm-7">标签：${blogDoc.blogTag}</div>
                </div>
                <div class="row">
                    <div class="col-sm-6"><strong>最后更新&nbsp;${blogDoc.updataTimeStr}</strong></div>
                    <div class="col-sm-4 col-sm-offset-2">
                        <span class="badge glyphicon glyphicon-eye-open">&nbsp;${blogDoc.openNumber}</span>
                        <%--<a href="#">--%>
                            <span class="badge glyphicon glyphicon-thumbs-up">&nbsp;${blogDoc.supportNumber}</span>
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
