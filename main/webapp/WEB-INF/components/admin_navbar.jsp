<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!-- Add a version parameter to prevent CSS caching -->
<link rel="stylesheet" href="${contextPath}/css/admin_navbar.css?v=<%=System.currentTimeMillis()%>">

<!-- Additional inline styles for profile picture display -->
<style>
    /* Profile picture styling in admin navbar */
    .user-profile .user-menu-toggle {
        display: flex;
        align-items: center;
        gap: 8px;
        cursor: pointer;
        position: relative;
    }
    
    .user-profile .user-menu-toggle img {
        width: 36px;
        height: 36px;
        border-radius: 50%;
        border: 2px solid #4CAF50;
        object-fit: cover;
        box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
    }
    
    .user-profile .user-menu-toggle i.fa-user-circle {
        font-size: 32px;
        color: #4CAF50;
    }
    
    .user-profile .user-menu-toggle .dropdown-content {
        display: none;
        position: absolute;
        top: 100%;
        right: 0;
        background-color: #fff;
        min-width: 160px;
        box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);
        z-index: 1000;
        border-radius: 8px;
        margin-top: 8px;
        padding: 8px 0;
    }
    
    .user-profile .user-menu-toggle:hover .dropdown-content {
        display: block;
    }
    
    .user-profile .user-menu-toggle .dropdown-content a {
        padding: 10px 16px;
        text-decoration: none;
        display: flex;
        align-items: center;
        gap: 8px;
        color: #333;
    }
    
    .user-profile .user-menu-toggle .dropdown-content a:hover {
        background-color: #f5f5f5;
    }
</style>

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
        <div class="user-menu-toggle" id="admin-user-menu-toggle">
            <!-- Profile picture or default icon -->
            <c:choose>
                <c:when test="${not empty sessionScope.user.profilePictureUrl}">
                    <img src="${contextPath}${sessionScope.user.profilePictureUrl}" alt="Profile" onerror="this.onerror=null; this.src='${contextPath}/resources/images/default-profile.png';">
                </c:when>
                <c:otherwise>
                    <i class="fa-solid fa-user-circle"></i>
                </c:otherwise>
            </c:choose>
            
            <span>${sessionScope.user.firstName}</span>
            <i class="fa-solid fa-chevron-down"></i>
            
            <div class="dropdown-content">
                <a href="${contextPath}/user/profile/update">
                    <i class="fas fa-user-edit"></i> Edit Profile
                </a>
                <a href="${contextPath}/logout">
                    <i class="fas fa-sign-out-alt"></i> Logout
                </a>
            </div>
        </div>
    </div>
</nav>

<script>
    // Optional JavaScript to toggle dropdown on click rather than hover
    document.addEventListener('DOMContentLoaded', function() {
        const userMenuToggle = document.getElementById('admin-user-menu-toggle');
        if (userMenuToggle) {
            userMenuToggle.addEventListener('click', function(e) {
                const dropdown = this.querySelector('.dropdown-content');
                if (dropdown) {
                    dropdown.style.display = dropdown.style.display === 'block' ? 'none' : 'block';
                }
                e.stopPropagation();
            });
            
            // Close dropdown when clicking elsewhere
            document.addEventListener('click', function() {
                const dropdown = userMenuToggle.querySelector('.dropdown-content');
                if (dropdown) {
                    dropdown.style.display = 'none';
                }
            });
            
            // Debug log for profile picture
            const profileImg = userMenuToggle.querySelector('img');
            if (profileImg) {
                console.log('Profile image src:', profileImg.src);
            } else {
                console.log('No profile image found, using default icon');
            }
        }
    });
</script>