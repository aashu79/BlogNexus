<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BlogNexus - Share Your Stories</title>
    
    <!-- Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=DM+Sans:wght@400;500;700&family=Montserrat:wght@400;500;600;700;800&display=swap" rel="stylesheet">
    
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    
    <!-- Base Styles -->
  
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
 
</head>
<body>
    <!-- Include Navbar -->
    <jsp:include page="../components/navbar.jsp" />

    <!-- Hero Section -->
    <section class="hero">
        <div class="container">
            <div class="hero-content">
                <span class="hero-badge"><i class="fas fa-sparkles"></i> Welcome to BlogNexus</span>
                <h1 class="hero-title">Discover Stories That Matter</h1>
                <p class="hero-subtitle">
                    Dive into thought-provoking articles, expert insights, and captivating stories from writers around the world.
                </p>
                <div class="hero-actions">
                    <a href="#featured" class="btn btn-primary btn-lg">Explore Articles <i class="fas fa-arrow-right"></i></a>
                    <a href="join.jsp" class="btn btn-outline btn-lg">Become a Writer <i class="fas fa-pen"></i></a>
                </div>
                <div class="hero-stats">
                    <div class="stat-item">
                        <span class="stat-value">10K+</span>
                        <span class="stat-label">Articles</span>
                    </div>
                    <div class="stat-item">
                        <span class="stat-value">2.5M+</span>
                        <span class="stat-label">Readers</span>
                    </div>
                    <div class="stat-item">
                        <span class="stat-value">5K+</span>
                        <span class="stat-label">Writers</span>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- Featured Posts Section -->
    <section class="featured-posts section" id="featured">
        <div class="container">
            <div class="section-header">
                <h2 class="section-title">Featured Stories</h2>
                <p class="section-subtitle">Handpicked articles from our editors to inspire and inform.</p>
            </div>

  <div class="trending-grid">
                <div class="trending-main">
                    <div>
                        <jsp:include page="../components/BlogCard.jsp" />
                    </div>
                    <div>
                        <jsp:include page="../components/BlogCard.jsp" />
                    </div>
                      <div>
                        <jsp:include page="../components/BlogCard.jsp" />
                    </div>
                     <div>
                        <jsp:include page="../components/BlogCard.jsp" />
                    </div>
                     <div>
                        <jsp:include page="../components/BlogCard.jsp" />
                    </div>
                     <div>
                        <jsp:include page="../components/BlogCard.jsp" />
                    </div>
                 
                </div>

              
            </div>
            </div>
        </div>
    </section>

    <!-- Categories Section -->
    <section class="categories-section">
        <div class="container">
            <div class="section-header">
                <h2 class="section-title">Explore Categories</h2>
                <p class="section-subtitle">Discover content tailored to your interests or explore new topics.</p>
            </div>

            <div class="categories-grid">
                <a href="category.jsp?cat=technology" class="category-card">
                    <div class="category-icon">
                        <i class="fas fa-microchip"></i>
                    </div>
                    <h3 class="category-title">Technology</h3>
                    <p class="category-count">245 Articles</p>
                </a>

                <a href="category.jsp?cat=environment" class="category-card">
                    <div class="category-icon">
                        <i class="fas fa-leaf"></i>
                    </div>
                    <h3 class="category-title">Environment</h3>
                    <p class="category-count">182 Articles</p>
                </a>

                <a href="category.jsp?cat=food" class="category-card">
                    <div class="category-icon">
                        <i class="fas fa-utensils"></i>
                    </div>
                    <h3 class="category-title">Food</h3>
                    <p class="category-count">157 Articles</p>
                </a>

                <a href="category.jsp?cat=travel" class="category-card">
                    <div class="category-icon">
                        <i class="fas fa-map-marked-alt"></i>
                    </div>
                    <h3 class="category-title">Travel</h3>
                    <p class="category-count">196 Articles</p>
                </a>
            </div>
        </div>
    </section>

    <!-- Trending Section -->
    <section class="trending-section">
        <div class="container">
            <div class="trending-header">
                <h2 class="trending-title">Trending This Week</h2>
                <a href="trending.jsp" class="btn btn-sm btn-outline">View All</a>
            </div>

            <div class="trending-grid">
                <div class="trending-main">
                    <div>
                        <jsp:include page="../components/BlogCard.jsp" />
                    </div>
                    <div>
                        <jsp:include page="../components/BlogCard.jsp" />
                    </div>
                      <div>
                        <jsp:include page="../components/BlogCard.jsp" />
                    </div>
                </div>

              
            </div>
        </div>
    </section>


    <!--  Footer -->
    <jsp:include page="../components/footer.jsp" />
</body>
</html>