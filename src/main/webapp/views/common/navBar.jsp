<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: anhnh
  Date: 22/07/2024
  Time: 10:00 CH
  To change this template use File | Settings | File Templates.
--%>
<ul class="navbar-nav mr-auto">
    <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/home">Home</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/product/productList.jsp">Products</a>
    </li>
    <c:choose>
        <c:when test="${userRole == 'CUSTOMER' || userRole == 'ADMIN'}">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/cart/cart.jsp">Cart</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/order/orderList.jsp">Orders</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/user/profile.jsp">Profile</a>
            </li>
        </c:when>
        <c:when test="${userRole == 'ADMIN'}">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/admin/adminDashboard.jsp">Admin</a>
            </li>
        </c:when>
    </c:choose>
    <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/contactUs.jsp">Contact</a>
    </li>
    <c:if test="${userRole == null}">
        <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/login.jsp">Login</a>
        </li>
    </c:if>
</ul>


