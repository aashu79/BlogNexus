<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/BlogCard.css">

<title>Insert title here</title>
</head>
<body>
<div class="blog-card">
    <div class="blog-card-image">
        <a href="blog-detail.jsp">
            <img src="https://images.unsplash.com/photo-1465146344425-f00d5f5c8f07?q=80&w=2676&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D" alt="Nature landscape" />
        </a>
        <span class="blog-card-category">Nature</span>
    </div>
    <div class="blog-card-content">
        <div class="blog-card-meta">
            <span class="blog-card-date"><i class="far fa-clock"></i> April 14, 2025</span>
            <span class="blog-card-read-time">5 min read</span>
        </div>
        <h3 class="blog-card-title">
            <a href="blog-detail.jsp">The Beauty of Natural Landscapes and Their Impact on Mental Wellness</a>
        </h3>
        <p class="blog-card-excerpt">Exploring how spending time in nature can reduce stress, improve mood, and enhance overall mental health. Research shows even brief exposure to natural environments provides significant benefits.</p>
        <div class="blog-card-author">
            <img src="https://randomuser.me/api/portraits/men/42.jpg" alt="Aashu Sharma" class="author-avatar">
            <div class="author-info">
                <span class="author-name">Aashu Sharma</span>
                <span class="author-role">Environmental Writer</span>
            </div>
        </div>
    </div>
</div>
</body>
</html>