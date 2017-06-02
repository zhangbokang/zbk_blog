<%--
  Created by IntelliJ IDEA.
  User: zhangbokang
  Date: 2017/5/4
  Time: 14:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<meta charset="utf-8">
<div id="doclist">
    <ul class="list-group">

        <c:forEach items="${docPaging.pageList}" var="doc">
            <li class="list-group-item">
                <a class="" href="/doc/docPage?docId=${doc.docId}">
                    <span class="h3">${doc.title}</span>
                    <c:set var="docMdStr1" value="${fn:substring(doc.docMd,0,60)}"/>
                    <c:set var="docMdStr2" value="${fn:replace(docMdStr1, '<', '&lt;')}"/>
                    <c:set var="docMdStrR" value="${fn:replace(docMdStr2, '>', '&gt;')}"/>
                    <p>${docMdStrR} ...</p>
                </a>
                <div class="row">
                    <div class="col-sm-5">分类：${doc.classify.name}</div>
                    <div class="col-sm-7">标签：${doc.tag.name}</div>
                </div>
                <div class="row">
                    <jsp:useBean id="dataValue" class="java.util.Date"/>
                    <jsp:setProperty name="dataValue" property="time" value="${doc.updateTime}"/>
                    <div class="col-sm-6"><strong>最后更新&nbsp;<fmt:formatDate value="${dataValue}" type="both"/></strong>
                    </div>
                    <div class="col-sm-4 col-sm-offset-2">
                        <span class="badge glyphicon glyphicon-eye-open">&nbsp;${doc.openNumber==null?0:doc.openNumber}</span>
                        <span class="badge glyphicon glyphicon-thumbs-up">&nbsp;${doc.favorNumber==null?0:doc.favorNumber}</span>
                    </div>
                </div>
            </li>
        </c:forEach>

    </ul>
    <div>
        <div id="xxx"></div>
        <ul class="pagination" id="paging">
        </ul>
    </div>
</div>
<script>
    //    分页
    $("#paging").jqPaginator({
        //参考：http://jqpaginator.keenwon.com/
//        totalPages:20, //总页数
//        totalCounts:200, //总记录数
        totalCounts:${docPaging.totalCounts},
//        pageSize:10, //设置每一页的条目数
        pageSize:${docPaging.pageSize},
        //注意：要么设置totalPages，要么设置totalCounts + pageSize，否则报错；设置了totalCounts和pageSize后，会自动计算出totalPages。
//        currentPage:1, //设置当前的页码
        currentPage:${docPaging.currentPage},
        visiblePages: 13, //设置最多显示的页码数（例如有100也，当前第1页，则显示1 - 13页）
//        disableClass:'disabled', //设置首页，上一页，下一页，末页的“禁用状态”样式
        first: '<li class="first"><a href="#">首页</a></li>',
        prev: '<li class="prev"><a href="#">上一页</a></li>',
        next: '<li class="next"><a href="#">下一页</a></li>',
        last: '<li class="last"><a href="#">末页</a></li>',
        page: '<li class="page"><a href="#">{{page}}</a></li>',
        onPageChange: function (num, type) {
//          回调函数，当换页时触发（包括初始化第一页的时候），会传入两个参数：
//          1、“目标页"的页码，Number类型
//          2、触发类型，可能的值：“init”（初始化），“change”（点击分页）
            $("#xxx").text("共" + this.totalCounts + "条，每页" + this.pageSize + "条，当前第" + num + "页");
            if (type == "change") {
                loadBlogList(
                        <c:choose>
                        <c:when test="${accessType=='doc'}">
                        "/findAllDoc?accessType=doc"
                </c:when>
                <c:when test="${accessType=='tag'}">
                '/findDocByTagId?accessType=tag&tagId=${tagId}'
                </c:when>
                <c:when test="${accessType=='classify'}">
                '/findDocByClassifyId?accessType=classify&classifyId=${classifyId}'
                        </c:when>
                        </c:choose>
                        , {
//                        totalCounts:this.totalCounts,
                    pageSize: this.pageSize,
                    currentPage: num
                }
            )
            }
        }
    });
</script>
