<%--
  Created by IntelliJ IDEA.
  User: FLY陈威
  Date: 2021/5/8
  Time: 15:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="page_nav">
    <c:if test="${requestScope.page.pageNo>1}">
        <a href="${requestScope.page.url}">首页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">上一页</a>
        <%-- <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">${requestScope.page.pageNo-1}</a>--%>
    </c:if>
    <%--上一页--%>
    <c:choose >
        <%--情况1:总页码小于等于五页--%>
        <c:when test="${requestScope.page.pageTotal<=5}">
            <%--优化--%>
            <c:set var="begin" value="1"></c:set>
            <c:set var="end" value="${requestScope.page.pageTotal}"></c:set>
            <%--<c:forEach begin="1" end="${requestScope.page.pageTotal}" var="i">
                <c:if test="${i==requestScope.page.pageNo}">
                    <span style="color: #FF9500">[ ${i} ]</span>
                </c:if>
                <c:if test="${i!=requestScope.page.pageNo}">
                    <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
                </c:if>
            </c:forEach>--%>
        </c:when>
        <%--情况2:总页码大于5的情况--%>
        <c:otherwise >
            <%--小情况1:当前页码为前面三个--%>
            <c:choose>
                <c:when test="${requestScope.page.pageNo<=3}">
                    <c:set var="begin" value="1"></c:set>
                    <c:set var="end" value="${requestScope.page.pageNo}"></c:set>
                    <%-- <c:forEach begin="1" end="${requestScope.page.pageNo}" var="i">
                <c:if test="${i==requestScope.page.pageNo}">
                    <span style="color: #FF9500">[ ${i} ]</span>
                </c:if>
                <c:if test="${i!=requestScope.page.pageNo}">
                    <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
                </c:if>
            </c:forEach>--%>
                </c:when>
                <%--当前页码为最后三个--%>
                <c:when test="${requestScope.page.pageNo>pageScope.page.pageTotal-3}">
                    <c:set var="begin" value="${requestScope.page.pageTotal-4}"></c:set>
                    <c:set var="end" value="${requestScope.page.pageTotal}"></c:set>
                    <%--<c:forEach begin="${requestScope.page.pageTotal-4}" end="${requestScope.page.pageTotal}" var="i">
                        <c:if test="${i==requestScope.page.pageNo}">
                            <span style="color: #FF9500">[ ${i} ]</span>
                        </c:if>
                        <c:if test="${i!=requestScope.page.pageNo}">
                            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
                        </c:if>
                    </c:forEach>--%>
                </c:when>
                <c:otherwise>
                    <c:set var="begin" value="${requestScope.page.pageNo-2}"></c:set>
                    <c:set var="end" value="${requestScope.page.pageNo+2}"></c:set>
                    <%--<c:forEach begin="${requestScope.page.pageNo-2}" end="${requestScope.page.pageNo+2}" var="i">
                        <c:if test="${i==requestScope.page.pageNo}">
                            <span style="color: #FF9500">[ ${i} ]</span>
                        </c:if>
                        <c:if test="${i!=requestScope.page.pageNo}">
                            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
                        </c:if>
                    </c:forEach>--%>
                </c:otherwise>
            </c:choose>
        </c:otherwise>
    </c:choose>

    <c:forEach begin="${begin}" end="${end}" var="i">
        <c:if test="${i==requestScope.page.pageNo}">
            <span style="color: #FF9500">[ ${i} ]</span>
        </c:if>
        <c:if test="${i!=requestScope.page.pageNo}">
            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
        </c:if>
    </c:forEach>
    <%---下一页--%>
    <%--        <span style="color: #FF9500">[ ${requestScope.page.pageNo} ]</span>--%>
    <c:if test="${requestScope.page.pageNo<requestScope.page.pageTotal}">
        <%--<a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">${requestScope.page.pageNo+1}</a>--%>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">下一页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
    </c:if>

    共 ${requestScope.page.pageTotal} 页，共 ${requestScope.page.pageTotalCount} 条记录 到第<input
        value="${requestScope.page.pageNo} " name="pn" id="pn_input"/>页
    <input id="searchPageBtn"type="button" value="确定">
    <script type="text/javascript">
        $(function(){
            $("#searchPageBtn").click(
                function () {
                    var pnVal = $("#pn_input").val();
                    var pageTotal=${requestScope.page.pageTotal};
                    if (pnVal<1||pnVal>pageTotal){
                        return false;}
                    location.href="${pageScope.basePath}${requestScope.page.url}&pageNo="+pnVal;
                }
            )
        })
    </script>
</div>
