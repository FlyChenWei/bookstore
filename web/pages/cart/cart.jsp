<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>购物车</title>
    <base href="http://localhost:8080/bookStore/">
    <%@include file="/pages/common/head.jsp" %>
    <script type="text/javascript">
        $(function () {
            // 给输入框绑定 onchange内容发生改变事件
            $(".updateCount").change(function () {
                // 获取商品名称
                var name = $(this).parent().parent().find("td:first").text();
                var id = $(this).attr('bookId');
                // 获取商品数量
                var count = this.value;
                if ( confirm("你确定要将【" + name + "】商品修改数量为：" + count + " 吗?") ) {
                    //发起请求。给服务器保存修改
                    location.href = "http://localhost:8080/bookStore/cartServlet?action=updateCount&count="+count+"&id="+id;
                } else {
                    // defaultValue属性是表单项Dom对象的属性。它表示默认的value属性值。
                    this.value = this.defaultValue;
                }
            });

        });
    </script>
</head>
<body>
<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <%--			<span class="wel_word">购物车</span>--%>
    <%@include file="/pages/common/login_success_menu.jsp" %>
</div>
<div id="main">

    <c:if test="${ empty sessionScope.cart.items}">
        <table>
            <tr>
                <td colspan="5" style="color: #FF9500"><a href="index.jsp">亲,当前购物车为空! 快去浏览信息吧</a></td>
            </tr>
        </table>
    </c:if>

    <c:if test="${not empty sessionScope.cart.items}">
    <table>
        <tr>
            <td>商品名称</td>
            <td>数量</td>
            <td>单价</td>
            <td>金额</td>
            <td>操作</td>
        </tr>
        <c:forEach items="${sessionScope.cart.items}" var="entry">
            <tr>
                <td>${entry.value.name}</td>
                <td>
                        <%--自定义属性记录book的id值,方便传入servlet--%>
                    <input  bookId="${entry.value.id}"style=" width: 80px" type="text" class="updateCount" value="${entry.value.count}">
                </td>
                <td>${entry.value.price}</td>
                <td>${entry.value.totalPrice}</td>
                <td><a href="cartServlet?action=delete&bookId=${entry.value.id}">删除</a></td>
            </tr>
        </c:forEach>
        </c:if>
    </table>
    <br>
    <c:if test="${not empty sessionScope.cart.items}">
        <div class="cart_info" style="text-align: center">
                        <span class="cart_span">购物车中共有<span
                                class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
            <span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
            <span class="cart_span"><a href="cartServlet?action=clear">清空购物车</a></span>
            <span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
        </div>
    </c:if>


</div>

<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
</div>
</body>
</html>