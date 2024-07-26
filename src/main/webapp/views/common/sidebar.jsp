<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="sidebar bg-light p-3">
    <!-- Search Bar -->
    <div class="search-bar mb-4">
        <form action="${pageContext.request.contextPath}/search" method="get" class="d-flex">
            <input type="text" name="query" class="form-control me-2" placeholder="Search..."/>
            <button type="submit" class="btn btn-primary">Search</button>
        </form>
    </div>

    <!-- Filters -->
    <div class="filters">
        <h5 class="mb-3">Filters</h5>
        <form action="${pageContext.request.contextPath}/filter" method="get">
            <!-- Example filter options -->
            <div class="form-group mb-3">
                <label for="category">Category</label>
                <select id="category" name="category" class="form-control">
                    <option value="">All Categories</option>
                    <!-- Populate categories here -->
                </select>
            </div>
            <div class="form-group mb-3">
                <label for="price">Price Range</label>
                <input type="range" id="price" name="price" min="0" max="1000" class="form-control"/>
            </div>
            <button type="submit" class="btn btn-primary">Apply Filters</button>
        </form>
    </div>
</div>
