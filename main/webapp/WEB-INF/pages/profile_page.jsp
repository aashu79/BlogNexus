<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Profile - BlogNexus</title>
    
    <!-- Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=DM+Sans:wght@400;500;700&family=Montserrat:wght@400;500;600;700;800&display=swap" rel="stylesheet">
    
    <!-- Base Styles -->
    <link rel="stylesheet" href="${contextPath}/css/index.css">
    <link rel="stylesheet" href="${contextPath}/css/profile_page.css">
</head>
<body>
<jsp:include page="../components/message_handler.jsp"/>
<jsp:include page="../components/fontawesome.jsp"/>

    <!-- Include Navbar -->
    <jsp:include page="../components/navbar.jsp" />

    <section class="profile-section">
        <div class="container">
            <!-- Profile Header -->
            <div class="profile-header">
                <div class="profile-info">
                    <div class="profile-avatar">
                        <!-- Use profilePictureUrl which is already formatted correctly -->
                        <c:choose>
                            <c:when test="${not empty sessionScope.user.profilePictureUrl}">
                                <img src="${contextPath}${sessionScope.user.profilePictureUrl}" 
                                     alt="${sessionScope.user.firstName} ${sessionScope.user.lastName}">
                            </c:when>
                            <c:otherwise>
                                <img src="https://t4.ftcdn.net/jpg/03/64/21/11/360_F_364211147_1qgLVxv1Tcq0Ohz3FawUfrtONzz8nq3e.jpg" 
                                     alt="${sessionScope.user.firstName} ${sessionScope.user.lastName}">
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="profile-text">
                        <h1 class="profile-name">${sessionScope.user.firstName} ${sessionScope.user.lastName}</h1>
                    </div>
                </div>
                <div class="profile-actions">
                    <a href="${contextPath}/user/profile/update" class="btn btn-primary">
                        <i class="fas fa-pen"></i> Edit Profile
                    </a>
                </div>
            </div>
            
            <!-- My Blogs Section -->
            <div class="my-blogs">
                <div class="blogs-header">
                    <h2 class="section-title">My Blogs</h2>
                    <a href="${contextPath}/user/blog/create" class="btn btn-primary">
                        <i class="fas fa-plus"></i> New Blog
                    </a>
                </div>
                
                <!-- Blog Posts Grid -->
                <div class="blogs-grid">
                    <c:choose>
                        <c:when test="${empty userBlogs}">
                            <div class="no-blogs-message" style="text-align:center; padding:2rem; background:#f8f8f8; border-radius:8px;">
                                <p>You haven't published any blogs yet. Click "New Blog" to create your first post!</p>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${userBlogs}" var="blog">
                                <!-- Blog Card -->
                                <div class="blog-card">
                                    <div class="blog-image">
                                        <!-- Use blog.thumbnailUrl which is already formatted correctly -->
                                        <img src="${contextPath}${blog.thumbnailUrl}" alt="${blog.title}">
                                        <div class="blog-tag">${blog.genre}</div>
                                    </div>
                                    <div class="blog-content">
                                        <h3 class="blog-title">
                                            <a href="${contextPath}/blog/${blog.blogId}">${blog.title}</a>
                                        </h3>
                                        <p class="blog-excerpt">
                                            ${fn:substring(blog.content, 0, 150)}${fn:length(blog.content) > 150 ? '...' : ''}
                                        </p>
                                        <div class="blog-footer">
                                            <div class="blog-meta">
                                                <span class="blog-date"><i class="far fa-calendar"></i> 2025-05-01 17:35:28</span>
                                                <span class="blog-status published">Published</span>
                                            </div>
                                            <div class="blog-actions">
                                                <a href="${contextPath}/user/blog/edit/${blog.blogId}" class="btn btn-sm btn-outline" title="Edit Blog">
                                                    <i class="fas fa-edit"></i> Edit
                                                </a>
                                                <form method="post" action="${contextPath}/user/blog/delete" style="display:inline;">
                                                    <input type="hidden" name="id" value="${blog.blogId}">
                                                    <button type="submit" class="btn btn-sm btn-danger" title="Delete Blog">
                                                        <i class="fas fa-trash"></i> Delete
                                                    </button>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </section>

    <!-- Include Footer -->
    <jsp:include page="../components/footer.jsp" />
</body>
</html>