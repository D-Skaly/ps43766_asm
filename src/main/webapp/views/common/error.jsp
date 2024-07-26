<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
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
    <title>Error</title>
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
            <div class="text-center">
                <h1 class="display-4">Error</h1>
                <h2>An error occurred</h2>
                <p class="lead">Sorry, something went wrong. Please try again later.</p>
                <a href="${pageContext.request.contextPath}/home.jsp" class="btn btn-primary">Go to Home</a>
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


