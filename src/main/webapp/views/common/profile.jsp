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
    <title>Profile</title>
    <!-- Bootstrap CSS from CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<%@ include file="header.jsp" %>

<main class="container mt-4">
    <div class="row">
        <div class="col-md-8 offset-md-2">
            <div class="card">
                <div class="card-header">
                    <h3 class="text-center">Profile</h3>
                </div>
                <div class="card-body">
                    <!-- Display error messages -->
                    <c:if test="${not empty errorMessage}">
                        <div class="alert alert-danger">
                            <c:out value="${errorMessage}"/>
                        </div>
                    </c:if>

                    <!-- Display success messages -->
                    <c:if test="${not empty successMessage}">
                        <div class="alert alert-success">
                            <c:out value="${successMessage}"/>
                        </div>
                    </c:if>

                    <form action="${pageContext.request.contextPath}/updateProfile" method="post">
                        <div class="mb-3">
                            <label for="username" class="form-label">Username</label>
                            <input type="text" id="username" name="username" class="form-control" value="${user.username}" readonly>
                        </div>
                        <div class="mb-3">
                            <label for="email" class="form-label">Email</label>
                            <input type="email" id="email" name="email" class="form-control" value="${user.email}" required>
                        </div>
                        <div class="mb-3">
                            <label for="password" class="form-label">New Password</label>
                            <input type="password" id="password" name="password" class="form-control">
                            <small class="form-text text-muted">Leave empty if you do not want to change the password.</small>
                        </div>
                        <div class="mb-3">
                            <label for="confirmPassword" class="form-label">Confirm New Password</label>
                            <input type="password" id="confirmPassword" name="confirmPassword" class="form-control">
                        </div>
                        <div class="mb-3">
                            <button type="submit" class="btn btn-primary">Update Profile</button>
                        </div>
                    </form>
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

