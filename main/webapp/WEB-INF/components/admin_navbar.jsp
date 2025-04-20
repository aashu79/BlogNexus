<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!-- Add a version parameter to prevent CSS caching -->
<link rel="stylesheet" href="${contextPath}/css/admin_navbar.css?v=<%=System.currentTimeMillis()%>">

<!-- Navbar Component with URL-based Active Link -->
<nav class="admin-navbar">
<jsp:include page="./message_handler.jsp"></jsp:include>
<jsp:include page="./fontawesome.jsp"></jsp:include>
    <div class="logo">
        <h2>Admin Panel</h2>
    </div>
    
    <!-- Improved URL matching for active link detection -->
    <c:set var="requestURI" value="${requestScope['javax.servlet.forward.request_uri']}" />
    <c:if test="${empty requestURI}">
        <c:set var="requestURI" value="${pageContext.request.requestURI}" />
    </c:if>
    <c:set var="currentPath" value="${requestURI.substring(contextPath.length())}" />
    
    <div class="nav-links">
        <a href="${contextPath}/admin/dashboard" class="${currentPath.startsWith('/admin/dashboard') || currentPath.equals('/admin/') || currentPath.equals('/admin') ? 'active' : ''}">
            <i class="fas fa-tachometer-alt"></i> Dashboard
        </a>
        <a href="${contextPath}/admin/users" class="${currentPath.startsWith('/admin/users') ? 'active' : ''}">
            <i class="fas fa-users"></i> Users
        </a>
        <a href="${contextPath}/admin/blogs" class="${currentPath.startsWith('/admin/blogs') ? 'active' : ''}">
            <i class="fas fa-blog"></i> Blogs
        </a>
    </div>
    <div class="user-profile">
        <div class="user-menu-toggle" id="user-menu-toggle">
            <img src="${not empty sessionScope.user.profilePicture ? contextPath.concat('/resources/imagesprofileImages/').concat(sessionScope.user.profilePicture) : '#'}" 
                 style="display: ${not empty sessionScope.user.profilePicture ? 'inline-block' : 'none'}" 
                 alt="Profile">
            <i class="fa-solid fa-user-circle" style="display: ${empty sessionScope.user.profilePicture ? 'inline-block' : 'none'}"></i>
            
            <span>${sessionScope.user.firstName}</span>
            <i class="fa-solid fa-chevron-down"></i>
            
            <div class="dropdown-content">
                <a href="${contextPath}/logout">
                    <i class="fas fa-sign-out-alt"></i> Logout
                </a>
            </div>
        </div>
    </div>
</nav>