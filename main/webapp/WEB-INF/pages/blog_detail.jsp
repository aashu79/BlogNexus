<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>The Beauty of Natural Landscapes | GreenBlog</title>
    


    
    <!-- Custom CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/blog_detail.css">
</head>
<body>
 <jsp:include page="../components/fontawesome.jsp"></jsp:include>
 <jsp:include page="../components/navbar.jsp"></jsp:include>

    <!-- Blog Content -->
    <main class="blog-main">
        <div class="container">
            <!-- Blog Header -->
            <header class="blog-header">
                <
                
                <h1 class="blog-title">The Beauty of Natural Landscapes</h1>
                
                <div class="blog-meta">
                    <div class="author">
                        <img src="https://randomuser.me/api/portraits/men/31.jpg" alt="Aashu Sharma" class="author-avatar">
                        <div class="author-info">
                            <span class="author-name">Rameshwor Sharma</span>
                            <span class="author-role">Environmental Writer</span>
                        </div>
                    </div>
                    
                    <div class="blog-date">
                        <i class="far fa-calendar-alt"></i>
                        <span>April 20, 2025</span>
                    </div>
                    
                    <div class="blog-readtime">
                        <i class="far fa-clock"></i>
                        <span>5 min read</span>
                    </div>
                    
                    <div class="blog-category">
                        <span>Nature</span>
                    </div>
                </div>
            </header>
            
    
            
            <!-- Featured Image -->
            <div class="featured-image-wrapper">
                <div class="featured-image">
                    <img src="https://images.unsplash.com/photo-1465146344425-f00d5f5c8f07?w=1200" alt="Natural Landscape">
                </div>
            </div>
            
            <!-- Blog Actions -->
            <div class="blog-actions">
                <div class="blog-actions-left">
                    <% if (session.getAttribute("isLoggedIn") != null && (Boolean)session.getAttribute("isLoggedIn")) { %>
                    <button class="action-btn favorite-btn" id="favoriteBtn">
                        <i class="far fa-heart"></i>
                        <span>Add to Favorites</span>
                    </button>
                    <% } %>
                    
                    <button class="action-btn share-btn">
                        <i class="fas fa-share-alt"></i>
                        <span>Share</span>
                    </button>
                </div>
         
            </div>
            
            <!-- Blog Content -->
            <article class="blog-content">
                <p class="article-content">
                   	Exploring how spending time in nature can reduce stress, improve mood, and enhance overall mental health. Research shows even brief exposure to natural environments provides significant benefits for cognitive function and emotional wellbeing.
                    Exploring how spending time in nature can reduce stress, improve mood, and enhance overall mental health. Research shows even brief exposure to natural environments provides significant benefits for cognitive function and emotional wellbeing.
                    
                    Exploring how spending time in nature can reduce stress, improve mood, and enhance overall mental health. Research shows even brief exposure to natural environments provides significant benefits for cognitive function and emotional wellbeing.
                    	Exploring how spending time in nature can reduce stress, improve mood, and enhance overall mental health. Research shows even brief exposure to natural environments provides significant benefits for cognitive function and emotional wellbeing.
                    Exploring how spending time in nature can reduce stress, improve mood, and enhance overall mental health. Research shows even brief exposure to natural environments provides significant benefits for cognitive function and emotional wellbeing.
                    
                    Exploring how spending time in nature can reduce stress, improve mood, and enhance overall mental health. Research shows even brief exposure to natural environments provides significant benefits for cognitive function and emotional wellbeing.
                    	Exploring how spending time in nature can reduce stress, improve mood, and enhance overall mental health. Research shows even brief exposure to natural environments provides significant benefits for cognitive function and emotional wellbeing.
                    Exploring how spending time in nature can reduce stress, improve mood, and enhance overall mental health. Research shows even brief exposure to natural environments provides significant benefits for cognitive function and emotional wellbeing.
                    
                    Exploring how spending time in nature can reduce stress, improve mood, and enhance overall mental health. Research shows even brief exposure to natural environments provides significant benefits for cognitive function and emotional wellbeing.
                    	Exploring how spending time in nature can reduce stress, improve mood, and enhance overall mental health. Research shows even brief exposure to natural environments provides significant benefits for cognitive function and emotional wellbeing.
                    Exploring how spending time in nature can reduce stress, improve mood, and enhance overall mental health. Research shows even brief exposure to natural environments provides significant benefits for cognitive function and emotional wellbeing.
                    
                    Exploring how spending time in nature can reduce stress, improve mood, and enhance overall mental health. Research shows even brief exposure to natural environments provides significant benefits for cognitive function and emotional wellbeing.
                </p>
    
            </article>
            
     
            
            <!-- Comments Section -->
            <section class="comments-section">
                <h2 class="section-title">Comments <span class="comment-count">(2)</span></h2>
                
                <% if (session.getAttribute("isLoggedIn") != null && (Boolean)session.getAttribute("isLoggedIn")) { %>
                <div class="add-comment">
                    <div class="comment-user">
                        <img src="https://randomuser.me/api/portraits/men/42.jpg" alt="Your Avatar" class="comment-avatar">
                        <span>aashu79</span>
                    </div>
                    <form class="comment-form">
                        <textarea placeholder="Share your thoughts..." rows="3"></textarea>
                        <button type="submit" class="btn-primary">Post Comment</button>
                    </form>
                </div>
                <% } %>
                
                <div class="comments-list">
                    <div class="comment">
                        <div class="comment-header">
                            <div class="comment-user">
                                <img src="https://randomuser.me/api/portraits/women/33.jpg" alt="Sarah Johnson" class="comment-avatar">
                                <div>
                                    <span class="comment-author">Sarah Johnson</span>
                                    <span class="comment-date">April 20, 2025 • 15:45</span>
                                </div>
                            </div>
                        </div>
                        <div class="comment-body">
                            <p>This article resonated with me so deeply. I've been incorporating forest walks into my routine for the past year, and the difference in my anxiety levels is remarkable. Would love to see more content about creating natural spaces in urban apartments!</p>
                        </div>
                    
                    </div>
                    
                    <div class="comment">
                        <div class="comment-header">
                            <div class="comment-user">
                                <img src="https://randomuser.me/api/portraits/men/85.jpg" alt="Michael Chen" class="comment-avatar">
                                <div>
                                    <span class="comment-author">Michael Chen</span>
                                    <span class="comment-date">April 19, 2025 • 21:30</span>
                                </div>
                            </div>
                        </div>
                        <div class="comment-body">
                            <p>I'm curious about the long-term studies on this topic. While short-term benefits are clear, has anyone conducted longitudinal research on how regular nature exposure affects chronic conditions like depression over several years?</p>
                        </div>
                      
                    </div>
                </div>
            </section>
            
            <!-- Related Articles -->
            <section class="related-articles">
                <h2 class="section-title">Related Articles</h2>
                
                <div class="related-grid">
                    <a href="#" class="related-card">
                        <div class="related-image">
                            <img src="https://images.unsplash.com/photo-1501854140801-50d01698950b?w=600" alt="Mountain lake">
                        </div>
                        <div class="related-content">
                            <span class="related-category">Nature</span>
                            <h3 class="related-title">How Blue Spaces Impact Mental Health</h3>
                            <div class="related-meta">
                                <span class="related-date">April 15, 2025</span>
                                <span class="related-dot">•</span>
                                <span class="related-time">6 min read</span>
                            </div>
                        </div>
                    </a>
                    
                    <a href="#" class="related-card">
                        <div class="related-image">
                            <img src="https://images.unsplash.com/photo-1520262389826-979a143c95d6?w=600" alt="Urban garden">
                        </div>
                        <div class="related-content">
                            <span class="related-category">Urban Living</span>
                            <h3 class="related-title">Creating Green Spaces in Urban Environments</h3>
                            <div class="related-meta">
                                <span class="related-date">April 10, 2025</span>
                                <span class="related-dot">•</span>
                                <span class="related-time">8 min read</span>
                            </div>
                        </div>
                    </a>
                    
                    <a href="#" class="related-card">
                        <div class="related-image">
                            <img src="https://images.unsplash.com/photo-1473773508845-188df298d2d1?w=600" alt="Mountain view">
                        </div>
                        <div class="related-content">
                            <span class="related-category">Travel</span>
                            <h3 class="related-title">Top Eco-Tourism Destinations for 2025</h3>
                            <div class="related-meta">
                                <span class="related-date">April 5, 2025</span>
                                <span class="related-dot">•</span>
                                <span class="related-time">10 min read</span>
                            </div>
                        </div>
                    </a>
                </div>
            </section>
        </div>
    </main>
     <jsp:include page="../components/footer.jsp"></jsp:include>

</body>
</html>