<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<header>
    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/home">Online Shop</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/home">Home</a>
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
                            <a class="nav-link" href="${pageContext.request.contextPath}/login">Login</a>
                        </li>
                    </c:if>
                </ul>
                <form class="d-flex">
                    <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-success" type="submit">Search</button>
                </form>
            </div>
        </div>
    </nav>
</header>
