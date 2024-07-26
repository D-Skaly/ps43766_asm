<%--
  Created by IntelliJ IDEA.
  User: anhnh
  Date: 22/07/2024
  Time: 9:48 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <!-- Bootstrap CSS from CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<%@ include file="header.jsp" %>

<main class="container mt-4">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-header">
                    <h3 class="text-center">Login</h3>
                </div>
                <div class="card-body">
                    <!-- Display error messages -->
                    <%--@elvariable id="errorMessage" type="java.lang.String"--%>
                    <c:if test="${not empty errorMessage}">
                        <div class="alert alert-danger">
                            <c:out value="${errorMessage}"/>
                        </div>
                    </c:if>

                    <form action="${pageContext.request.contextPath}/login" method="post">
                        <div class="mb-3">
                            <label for="username" class="form-label">Username</label>
                            <input type="text" id="username" name="username" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label for="password" class="form-label">Password</label>
                            <input type="password" id="password" name="password" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <button type="submit" class="btn btn-primary">Login</button>
                        </div>
                    </form>
                    <div class="text-center">
                        <p>Don't have an account? <a href="${pageContext.request.contextPath}/register">Register here</a></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>

<%@ include file="footer.jsp" %>

<!-- Bootstrap JS and dependencies from CDN -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"></script>
</body>
</html>

