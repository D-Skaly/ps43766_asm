<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: anhnh
  Date: 22/07/2024
  Time: 9:42 CH
  To change this template use File | Settings | File Templates.
--%>
<footer class="bg-light text-center text-lg-start mt-4">
    <div class="container p-4">
        <!-- Footer content -->
        <c:if test="${userRole != null}">
            <p class="text-muted">Logged in as: <c:out value="${userRole}"/></p>
        </c:if>
        <p class="text-muted">&copy; 2024 Online Shop. All rights reserved.</p>
    </div>
</footer>

