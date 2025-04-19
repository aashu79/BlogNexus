<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Profile - BlogNexus</title>
    
    <!-- Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=DM+Sans:wght@400;500;700&family=Montserrat:wght@400;500;600;700;800&display=swap" rel="stylesheet">
    
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    
    <!-- Base Styles -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/profile_page.css">
</head>
<body>
    <!-- Include Navbar -->
    <jsp:include page="../components/navbar.jsp" />

    <section class="profile-section">
        <div class="container">
            <!-- Profile Header -->
            <div class="profile-header">
                <div class="profile-info">
                    <div class="profile-avatar">
                        <img src="https://t4.ftcdn.net/jpg/03/64/21/11/360_F_364211147_1qgLVxv1Tcq0Ohz3FawUfrtONzz8nq3e.jpg" alt="Aashu Kumar">
                    </div>
                    <div class="profile-text">
                        <h1 class="profile-name">Aashu Kumar</h1>
                        <p class="profile-username">@aashu79</p>
                        <p class="profile-joined"><i class="far fa-calendar-alt"></i> Joined April 2023</p>
                    </div>
                </div>
                <div class="profile-actions">
                    <a href="edit-profile.jsp" class="btn btn-primary">
                        <i class="fas fa-pen"></i> Edit Profile
                    </a>
                </div>
            </div>
            
            <!-- My Blogs Section -->
            <div class="my-blogs">
                <div class="blogs-header">
                    <h2 class="section-title">My Blogs</h2>
                    <a href="new-blog.jsp" class="btn btn-primary">
                        <i class="fas fa-plus"></i> New Blog
                    </a>
                </div>
                
                <!-- Blog Posts Grid -->
                <div class="blogs-grid">
                    <!-- Blog Post 1 -->
                    <div class="blog-card">
                        <div class="blog-image">
                            <img src="${pageContext.request.contextPath}/images/post-1.jpg" alt="Blog post">
                            <div class="blog-tag">Technology</div>
                        </div>
                        <div class="blog-content">
                            <h3 class="blog-title">The Future of AI: How Machine Learning is Transforming Industries</h3>
                            <p class="blog-excerpt">Artificial intelligence is revolutionizing how businesses operate across sectors. In this deep dive, we explore the most impactful applications and what's coming next.</p>
                            <div class="blog-footer">
                                <div class="blog-meta">
                                    <span class="blog-date"><i class="far fa-calendar"></i> 2025-04-10 09:30:15</span>
                                    <span class="blog-status published">Published</span>
                                </div>
                                <div class="blog-actions">
                                    <a href="edit-blog.jsp?id=123" class="btn btn-sm btn-outline" title="Edit Blog">
                                        <i class="fas fa-edit"></i> Edit
                                    </a>
                                    <button class="btn btn-sm btn-danger" title="Delete Blog">
                                        <i class="fas fa-trash"></i>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <!-- Blog Post 2 -->
                    <div class="blog-card">
                        <div class="blog-image">
                            <img src="${pageContext.request.contextPath}/images/post-2.jpg" alt="Blog post">
                            <div class="blog-tag">Travel</div>
                        </div>
                        <div class="blog-content">
                            <h3 class="blog-title">Hidden Gems: Exploring Japan's Countryside</h3>
                            <p class="blog-excerpt">Beyond Tokyo and Kyoto lie breathtaking landscapes and charming villages most travelers miss. Discover these off-the-beaten-path destinations that will make your trip unforgettable.</p>
                            <div class="blog-footer">
                                <div class="blog-meta">
                                    <span class="blog-date"><i class="far fa-calendar"></i> 2025-03-28 14:22:45</span>
                                    <span class="blog-status published">Published</span>
                                </div>
                                <div class="blog-actions">
                                    <a href="edit-blog.jsp?id=124" class="btn btn-sm btn-outline" title="Edit Blog">
                                        <i class="fas fa-edit"></i> Edit
                                    </a>
                                    <button class="btn btn-sm btn-danger" title="Delete Blog">
                                        <i class="fas fa-trash"></i>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <!-- Blog Post 3 -->
                    <div class="blog-card">
                        <div class="blog-image">
                            <img src="${pageContext.request.contextPath}/images/post-3.jpg" alt="Blog post">
                            <div class="blog-tag">Productivity</div>
                        </div>
                        <div class="blog-content">
                            <h3 class="blog-title">Mastering the 5-Hour Work Day</h3>
                            <p class="blog-excerpt">How I redefined my work schedule to maximize productivity and reclaim my time. These strategies can help you accomplish more in less time while maintaining work-life balance.</p>
                            <div class="blog-footer">
                                <div class="blog-meta">
                                    <span class="blog-date"><i class="far fa-calendar"></i> 2025-03-15 10:15:33</span>
                                    <span class="blog-status published">Published</span>
                                </div>
                                <div class="blog-actions">
                                    <a href="edit-blog.jsp?id=125" class="btn btn-sm btn-outline" title="Edit Blog">
                                        <i class="fas fa-edit"></i> Edit
                                    </a>
                                    <button class="btn btn-sm btn-danger" title="Delete Blog">
                                        <i class="fas fa-trash"></i>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <!-- Blog Post 4 (Draft) -->
                    <div class="blog-card draft">
                        <div class="blog-image">
                            <img src="${pageContext.request.contextPath}/images/post-4.jpg" alt="Blog post">
                            <div class="blog-tag">Health</div>
                        </div>
                        <div class="blog-content">
                            <h3 class="blog-title">5 Simple Exercises You Can Do At Your Desk</h3>
                            <p class="blog-excerpt">Stay active during your workday with these office-friendly exercises that improve posture and reduce stiffness. Perfect for those with sedentary jobs.</p>
                            <div class="blog-footer">
                                <div class="blog-meta">
                                    <span class="blog-date"><i class="far fa-calendar"></i> 2025-04-14 11:33:27</span>
                                    <span class="blog-status draft">Draft</span>
                                </div>
                                <div class="blog-actions">
                                    <a href="edit-blog.jsp?id=126" class="btn btn-sm btn-outline" title="Edit Blog">
                                        <i class="fas fa-edit"></i> Edit
                                    </a>
                                    <button class="btn btn-sm btn-danger" title="Delete Blog">
                                        <i class="fas fa-trash"></i>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                
              
           
            </div>
        </div>
    </section>

    <!-- Include Footer -->
    <jsp:include page="../components/footer.jsp" />
</body>
</html>