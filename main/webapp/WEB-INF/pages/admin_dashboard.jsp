<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Auto refresh the page every 30 seconds -->
    <meta http-equiv="refresh" content="30">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="${contextPath}/css/admin_dashboard.css">
    <link href="https://fonts.googleapis.com/css2?family=DM+Sans:wght@400;500;700&family=Montserrat:wght@400;500;600;700;800&display=swap" rel="stylesheet">
</head>
<body>
    <jsp:include page="../components/fontawesome.jsp"></jsp:include>
    <jsp:include page="../components/message_handler.jsp"></jsp:include>
    <!-- Include the navbar component -->
    <jsp:include page="../components/admin_navbar.jsp" />

    <!-- Main Content -->
    <div class="content">
        <div class="dashboard-header">
            <h1>Dashboard</h1>
            <p class="current-time">Current Date and Time (UTC): ${currentDateTime}</p>
        </div>
        
        <!-- Welcome Card -->
        <div class="card welcome-card">
            <h2>Welcome to Admin Dashboard</h2>
            <p>Hello, ${username}! Manage your application with ease.</p>
        </div>
        
        <!-- Stats Cards -->
        <div class="stats-container">
            <div class="card stat-card">
                <div class="stat-icon">
                    <i class="fas fa-users"></i>
                </div>
                <div class="stat-details">
                    <h3>Total Users</h3>
                    <p class="stat-number">${stats.userCount}</p>
                </div>
            </div>
            
            <div class="card stat-card">
                <div class="stat-icon">
                    <i class="fas fa-blog"></i>
                </div>
                <div class="stat-details">
                    <h3>Total Blogs</h3>
                    <p class="stat-number">${stats.blogCount}</p>
                </div>
            </div>
            
            <div class="card stat-card">
                <div class="stat-icon">
                    <i class="fas fa-comments"></i>
                </div>
                <div class="stat-details">
                    <h3>Comments</h3>
                    <p class="stat-number">${stats.commentCount}</p>
                </div>
            </div>
        </div>
        
        <!-- Recent Blogs Section -->
        <div class="card section-card">
            <div class="section-header">
                <h2>Recent Blogs</h2>
                <a href="${contextPath}/admin/blogs" class="btn btn-sm">View All</a>
            </div>
            <div class="table-container">
                <table>
                    <thead>
                        <tr>
                            <th>Title</th>
                            <th>Author</th>
                            <th>Category</th>
                            <th>Date</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:choose>
                            <c:when test="${empty recentBlogs}">
                                <tr>
                                    <td colspan="4" class="text-center">No blogs found</td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <c:forEach items="${recentBlogs}" var="blog">
                                    <tr>
                                        <td>${blog.title}</td>
                                        <td>${blog.authorName}</td>
                                        <td>${blog.genre}</td>
                                        <td>
                                            <fmt:parseDate value="${blog.createdAt}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="parsedDate" type="both" />
                                            <fmt:formatDate value="${parsedDate}" pattern="yyyy-MM-dd" />
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </tbody>
                </table>
            </div>
        </div>
        
        <!-- Recent Users Section -->
        <div class="card section-card">
            <div class="section-header">
                <h2>Recent Users</h2>
                <a href="${contextPath}/admin/users" class="btn btn-sm">View All</a>
            </div>
            <div class="table-container">
                <table>
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Country</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:choose>
                            <c:when test="${empty recentUsers}">
                                <tr>
                                    <td colspan="3" class="text-center">No users found</td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <c:forEach items="${recentUsers}" var="user">
                                    <tr>
                                        <td>${user.firstName} ${user.lastName}</td>
                                        <td>${user.email}</td>
                                        <td>${not empty user.country ? user.country : 'Not specified'}</td>
                                    </tr>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>