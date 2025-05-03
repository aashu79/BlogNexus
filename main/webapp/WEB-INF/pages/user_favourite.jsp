<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/user_favourite.css">
    
    
    <title>My Favorite Blogs</title>
</head>
<body>
    <jsp:include page="../components/fontawesome.jsp"></jsp:include>
    <jsp:include page="../components/navbar.jsp"></jsp:include>
    <jsp:include page="../components/message_handler.jsp"></jsp:include>
    
    <!-- Page Content -->
    <main class="main-content">
        <div class="container">
     
            <!-- Favorites Grid -->
            <div class="favorites-grid">
                <c:choose>
                    <c:when test="${empty blogs}">
                        <div class="no-favourites">
                            <i class="far fa-heart"></i>
                            <h3>You haven't added any blogs to your favorites yet</h3>
                            <p>Browse our blogs and click the "Add to Favorites" button on blogs you love!</p>
                            <a href="${pageContext.request.contextPath}/blogs" class="browse-btn">Browse Blogs</a>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${blogs}" var="blog">
                            <!-- Start of favorite blog card -->
                            <div class="favourite-card">
                                <div class="card-image">
                                    <img src="${pageContext.request.contextPath}${blog.imageUrl}" 
                                         alt="${blog.title}" loading="lazy">
                                    <div class="card-badge">${blog.genre}</div>
                                </div>
                                
                                <div class="card-content">
                                    <div class="card-meta">
                                        <span class="card-date">${formattedDates[blog.blogId]}</span>
                                        <span class="card-readtime">${readTimes[blog.blogId]}</span>
                                    </div>
                                    
                                    <h3 class="card-title">
                                        <a href="${pageContext.request.contextPath}/blog/${blog.blogId}">${blog.title}</a>
                                    </h3>
                                    
                                    <p class="card-excerpt">${excerpts[blog.blogId]}</p>
                                    
                                    <div class="card-author">
                                        <div class="author-info">
                                            <span class="author-name">${blog.authorName}</span>
                                            <span class="author-role">${authorRoles[blog.blogId]}</span>
                                        </div>
                                    </div>
                                    
                                    <div class="card-actions">
                                        <a href="${pageContext.request.contextPath}/blog/${blog.blogId}" class="read-btn">
                                            <i class="fas fa-book-open"></i> Read More
                                        </a>
                                        
                                        <form action="${pageContext.request.contextPath}/user/favourite/remove" method="post" class="remove-form">
                                            <input type="hidden" name="blogId" value="${blog.blogId}">
                                            <button type="submit" class="remove-btn" onclick="return confirm('Remove this blog from your favorites?')">
                                                <i class="fas fa-heart-broken"></i> Remove
                                            </button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <!-- End of favorite blog card -->
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </main>
    
    <jsp:include page="../components/footer.jsp"></jsp:include>
    
    <script>
       
        document.addEventListener('DOMContentLoaded', function() {
            
            const removeForms = document.querySelectorAll('.remove-form');
            removeForms.forEach(form => {
                form.addEventListener('submit', function(e) {
                    if (confirm('Are you sure you want to remove this blog from your favorites?')) {
                        const card = this.closest('.favourite-card');
                        card.style.transition = 'opacity 0.5s, transform 0.5s';
                        card.style.opacity = '0';
                        card.style.transform = 'scale(0.9)';
                       
                            form.submit();
                     
                        e.preventDefault();
                    } else {
                        e.preventDefault();
                    }
                });
            });
        });
    </script>
</body>
</html>