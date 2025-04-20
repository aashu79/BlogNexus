<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    

    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/favourite_blog_card.css">

    
    <title>Favorite Blog Card</title>
</head>
<body>
<jsp:include page="./fontawesome.jsp"></jsp:include>
    <div class="blog-card">
    <div class="blog-card-image">
        <a href="blog-detail.jsp?id=${param.blogId}">
            <img src="${param.imageUrl}" alt="${param.title}" />
        </a>
        <span class="blog-card-category">${param.category}</span>
        <button class="favorite-btn active">
            <i class="fas fa-heart"></i>
        </button>
    </div>
    <div class="blog-card-content">
        <div class="blog-card-meta">
            <span class="blog-card-date"><i class="far fa-clock"></i> ${param.date}</span>
            <span class="blog-card-read-time">${param.readTime}</span>
        </div>
        <h3 class="blog-card-title">
            <a href="blog-detail.jsp?id=${param.blogId}">${param.title}</a>
        </h3>
        <p class="blog-card-excerpt">${param.excerpt}</p>
        <div class="blog-card-author">
            <img src="https://randomuser.me/api/portraits/men/${30 + param.blogId}.jpg" alt="${param.authorName}" class="author-avatar">
            <div class="author-info">
                <span class="author-name">${param.authorName}</span>
                <span class="author-role">${param.authorRole}</span>
            </div>
            <div class="blog-card-actions">
                <button class="action-btn bookmark-btn">
                    <i class="far fa-bookmark"></i>
                </button>
                <button class="action-btn share-btn">
                    <i class="fas fa-share-alt"></i>
                </button>
            </div>
        </div>
    </div>
</div>
</body>
</html>