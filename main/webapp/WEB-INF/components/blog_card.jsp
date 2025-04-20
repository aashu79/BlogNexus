<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/BlogCard.css">

<title>Insert title here</title>
</head>
<body>
<div class="blog-card">

    <div class="blog-card-image">
        <a href="blog-detail.jsp?id=${param.blog_id}">
            <img src="${param.thumbnail}" alt="${param.title}" />
        </a>
        <span class="blog-card-category">${param.genre}</span>
    </div>
    <div class="blog-card-content">
        <div class="blog-card-meta">
            <span class="blog-card-date"><i class="far fa-clock"></i> ${param.formattedDate}</span>
            <span class="blog-card-read-time">${param.readTime}</span>
        </div>
        <h3 class="blog-card-title">
            <a href="blog-detail.jsp?id=${param.blog_id}">${param.title}</a>
        </h3>
        <p class="blog-card-excerpt">${param.content}</p>
        <div class="blog-card-author">
            <img src="https://randomuser.me/api/portraits/men/${30 + param.blog_id}.jpg" alt="${param.authorName}" class="author-avatar">
            <div class="author-info">
                <span class="author-name">${param.authorName}</span>
                <span class="author-role">${param.authorRole}</span>
            </div>
        </div>
    </div>
</div>
</body>
</html>