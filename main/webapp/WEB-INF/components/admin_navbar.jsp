<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <c:set var="contextPath" value="${pageContext.request.contextPath}" />

    <link rel="stylesheet" href="${contextPath}/css/admin_navbar.css">

<!-- Navbar Component with URL-based Active Link -->
<nav class="admin-navbar">
<jsp:include page="./message_handler.jsp"></jsp:include>
<jsp:include page="./fontawesome.jsp"></jsp:include>
    <div class="logo">
        <h2>Admin Panel</h2>
    </div>
    
    <!-- Set current request URI for active link detection -->
    <c:set var="currentUri" value="${requestScope['javax.servlet.forward.request_uri']}" />
    <c:if test="${empty currentUri}">
        <c:set var="currentUri" value="${pageContext.request.requestURI}" />
    </c:if>
    
    <div class="nav-links">
        <a href="${contextPath}/admin/dashboard" class="${currentUri.contains('/admin/dashboard') || currentUri.endsWith('/admin/') || currentUri.endsWith('/admin') ? 'active' : ''}">
            <i class="fas fa-tachometer-alt"></i> Dashboard
        </a>
        <a href="${contextPath}/admin/users" class="${currentUri.contains('/admin/users') ? 'active' : ''}">
            <i class="fas fa-users"></i> Users
        </a>
        <a href="${contextPath}/admin/blogs" class="${currentUri.contains('/admin/blogs') ? 'active' : ''}">
            <i class="fas fa-blog"></i> Blogs
        </a>
    </div>
    <div class="user-profile">
        <div class="avatar-container">
            <img src="${not empty sessionScope.user.profilePicture ? contextPath.concat('/resources/imagesprofileImages/').concat(sessionScope.user.profilePicture) : 'https://via.placeholder.com/40'}" alt="User Avatar" class="avatar">
            <div class="dropdown-content">
                <!-- Using inline style with display:none based on condition -->
                <a href="${contextPath}/login" id="loginBtn" style="display: ${sessionScope.isLoggedIn eq true ? 'none' : 'block'}">Login</a>
                <a href="${contextPath}/logout" id="logoutBtn" style="display: ${sessionScope.isLoggedIn eq true ? 'block' : 'none'}">Logout</a>
                <a href="${contextPath}/admin/profile">Profile</a>
                <a href="${contextPath}/admin/settings">Settings</a>
            </div>
        </div>
    </div>
</nav>