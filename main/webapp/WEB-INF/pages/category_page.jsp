<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Category: Nature</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/category_page.css">
</head>
<body>
<jsp:include page="../components/navbar.jsp"></jsp:include>
    <div class="category-container">
        <header class="category-header">
            <h1 class="category-title"><i class="fas fa-leaf category-icon"></i>Nature</h1>
            <p class="category-description">Explore the beauty and wonders of our natural world through these inspiring articles.</p>
        </header>
        
        <div class="category-stats">
            <div class="stat-item">
                <span class="stat-number">12</span>
                <span class="stat-label">Articles</span>
            </div>
            <div class="stat-item">
                <span class="stat-number">4</span>
                <span class="stat-label">Authors</span>
            </div>
            <div class="stat-item">
                <span class="stat-number">586</span>
                <span class="stat-label">Views</span>
            </div>
        </div>
        
        <div class="blog-grid">
            <!-- Blog Card 1 -->
            <jsp:include page="../components/blog_card.jsp">
                <jsp:param name="blog_id" value="1" />
                <jsp:param name="thumbnail" value="https://images.unsplash.com/photo-1465146344425-f00d5f5c8f07?w=800" />
                <jsp:param name="title" value="The Beauty of Natural Landscapes" />
                <jsp:param name="content" value="Exploring how spending time in nature can reduce stress, improve mood, and enhance overall mental health. Research shows even brief exposure to natural environments provides significant benefits." />
                <jsp:param name="genre" value="Nature" />
                <jsp:param name="created_by" value="aashu79" />
                <jsp:param name="formattedDate" value="April 20, 2025" />
                <jsp:param name="readTime" value="5 min read" />
                <jsp:param name="authorName" value="Aashu Sharma" />
                <jsp:param name="authorRole" value="Environmental Writer" />
            </jsp:include>
            
            <!-- Blog Card 2 -->
            <jsp:include page="../components/blog_card.jsp">
                <jsp:param name="blog_id" value="2" />
                <jsp:param name="thumbnail" value="https://images.unsplash.com/photo-1470071459604-3b5ec3a7fe05?w=800" />
                <jsp:param name="title" value="Forests: Earth's Green Lungs" />
                <jsp:param name="content" value="Dive into the critical role forests play in maintaining our planet's ecological balance, providing habitat for countless species, and combating climate change through carbon sequestration." />
                <jsp:param name="genre" value="Nature" />
                <jsp:param name="created_by" value="ecopriya" />
                <jsp:param name="formattedDate" value="April 25, 2025" />
                <jsp:param name="readTime" value="7 min read" />
                <jsp:param name="authorName" value="Priya Nair" />
                <jsp:param name="authorRole" value="Conservation Specialist" />
            </jsp:include>
            
            <!-- Blog Card 3 -->
            <jsp:include page="../components/blog_card.jsp">
                <jsp:param name="blog_id" value="3" />
                <jsp:param name="thumbnail" value="https://images.unsplash.com/photo-1505245208761-ba872912fac0?w=800" />
                <jsp:param name="title" value="The Healing Power of Water" />
                <jsp:param name="content" value="From cascading waterfalls to tranquil lakes, water bodies have captivated human imagination for centuries. Discover how water features in nature promote mindfulness and psychological restoration." />
                <jsp:param name="genre" value="Nature" />
                <jsp:param name="created_by" value="annykamakshigupta" />
                <jsp:param name="formattedDate" value="May 2, 2025" />
                <jsp:param name="readTime" value="6 min read" />
                <jsp:param name="authorName" value="Anny Kamakshi Gupta" />
                <jsp:param name="authorRole" value="Nature Therapist" />
            </jsp:include>
            
            <!-- Blog Card 4 -->
            <jsp:include page="../components/blog_card.jsp">
                <jsp:param name="blog_id" value="4" />
                <jsp:param name="thumbnail" value="https://images.unsplash.com/photo-1500829243541-74b677fecc30?w=800" />
                <jsp:param name="title" value="Seasonal Changes in Wildlife" />
                <jsp:param name="content" value="Animals and plants have developed remarkable adaptations to thrive through changing seasons. This article explores the fascinating transformations that occur in nature throughout the year." />
                <jsp:param name="genre" value="Nature" />
                <jsp:param name="created_by" value="rajdeep22" />
                <jsp:param name="formattedDate" value="May 3, 2025" />
                <jsp:param name="readTime" value="8 min read" />
                <jsp:param name="authorName" value="Raj Kapoor" />
                <jsp:param name="authorRole" value="Wildlife Photographer" />
            </jsp:include>
            
            <!-- Blog Card 5 -->
            <jsp:include page="../components/blog_card.jsp">
                <jsp:param name="blog_id" value="5" />
                <jsp:param name="thumbnail" value="https://images.unsplash.com/photo-1446329813274-7c9036bd9a1f?w=800" />
                <jsp:param name="title" value="Birds: Masters of the Sky" />
                <jsp:param name="content" value="The extraordinary world of birds offers endless discoveries, from migration patterns spanning continents to intricate nesting behaviors. Learn why birdwatching has become a beloved global pastime." />
                <jsp:param name="genre" value="Nature" />
                <jsp:param name="created_by" value="aashu79" />
                <jsp:param name="formattedDate" value="May 4, 2025" />
                <jsp:param name="readTime" value="5 min read" />
                <jsp:param name="authorName" value="Aashu Sharma" />
                <jsp:param name="authorRole" value="Environmental Writer" />
            </jsp:include>
            
            <!-- Blog Card 6 -->
            <jsp:include page="../components/blog_card.jsp">
                <jsp:param name="blog_id" value="6" />
                <jsp:param name="thumbnail" value="https://images.unsplash.com/photo-1507525428034-b723cf961d3e?w=800" />
                <jsp:param name="title" value="Ocean Ecosystems: Hidden Wonders" />
                <jsp:param name="content" value="Covering more than 70% of our planet, oceans harbor incredibly diverse ecosystems from coral reefs to abyssal plains. Explore the underwater world and why marine conservation matters for humanity." />
                <jsp:param name="genre" value="Nature" />
                <jsp:param name="created_by" value="annykamakshigupta" />
                <jsp:param name="formattedDate" value="May 4, 2025" />
                <jsp:param name="readTime" value="9 min read" />
                <jsp:param name="authorName" value="Anny Kamakshi Gupta" />
                <jsp:param name="authorRole" value="Nature Therapist" />
            </jsp:include>
        </div>
        
        <div class="pagination">
            <a href="#" class="page-item active">1</a>
            <a href="#" class="page-item">2</a>
            <a href="#" class="page-next"><i class="fas fa-angle-right"></i></a>
        </div>
    </div>
    <jsp:include page="../components/footer.jsp"></jsp:include>
    
</body>
</html>