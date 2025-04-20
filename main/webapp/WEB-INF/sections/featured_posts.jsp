<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/featured_posts.css">

<section class="featured-posts section" id="featured">
    <div class="container">
        <div class="section-header">
            <h2 class="section-title">Featured Stories</h2>
            <p class="section-subtitle">Handpicked articles from our editors to inspire and inform.</p>
        </div>

        <div class="featured-posts-grid">
            <div class="post-card">
                <jsp:include page="../components/blog_card.jsp">
                    <jsp:param name="blog_id" value="1" />
                    <jsp:param name="thumbnail" value="https://images.unsplash.com/photo-1465146344425-f00d5f5c8f07?w=800" />
                    <jsp:param name="title" value="The Beauty of Natural Landscapes" />
                    <jsp:param name="content" value="Exploring how spending time in nature can reduce stress, improve mood, and enhance overall mental health. Research shows even brief exposure to natural environments provides significant benefits for cognitive function and emotional wellbeing." />
                    <jsp:param name="genre" value="Nature" />
                    <jsp:param name="created_by" value="aashu79" />
                    <jsp:param name="formattedDate" value="April 20, 2025" />
                    <jsp:param name="readTime" value="5 min read" />
                    <jsp:param name="authorName" value="Aashu Sharma" />
                    <jsp:param name="authorRole" value="Environmental Writer" />
                </jsp:include>
            </div>
            <div class="post-card">
                <jsp:include page="../components/blog_card.jsp">
                    <jsp:param name="blog_id" value="2" />
                    <jsp:param name="thumbnail" value="https://images.unsplash.com/photo-1488590528505-98d2b5aba04b?w=800" />
                    <jsp:param name="title" value="The Future of Sustainable Technology" />
                    <jsp:param name="content" value="How eco-friendly innovations are transforming the tech industry, from biodegradable components to energy-efficient designs that reduce environmental impact without sacrificing performance or user experience." />
                    <jsp:param name="genre" value="Technology" />
                    <jsp:param name="created_by" value="priya_tech" />
                    <jsp:param name="formattedDate" value="April 18, 2025" />
                    <jsp:param name="readTime" value="8 min read" />
                    <jsp:param name="authorName" value="Priya Mehta" />
                    <jsp:param name="authorRole" value="Tech Analyst" />
                </jsp:include>
            </div>
            <div class="post-card">
                <jsp:include page="../components/blog_card.jsp">
                    <jsp:param name="blog_id" value="3" />
                    <jsp:param name="thumbnail" value="https://images.unsplash.com/photo-1498837167922-ddd27525d352?w=800" />
                    <jsp:param name="title" value="Plant-Based Diets: Benefits for Health and Environment" />
                    <jsp:param name="content" value="Exploring the dual impact of plant-based eating on personal health outcomes and environmental sustainability. Research shows significant benefits for cardiovascular health, weight management, and reducing carbon footprint." />
                    <jsp:param name="genre" value="Food" />
                    <jsp:param name="created_by" value="raj_kumar" />
                    <jsp:param name="formattedDate" value="April 15, 2025" />
                    <jsp:param name="readTime" value="6 min read" />
                    <jsp:param name="authorName" value="Raj Kumar" />
                    <jsp:param name="authorRole" value="Nutrition Expert" />
                </jsp:include>
            </div>
            <div class="post-card">
                <jsp:include page="../components/blog_card.jsp">
                    <jsp:param name="blog_id" value="4" />
                    <jsp:param name="thumbnail" value="https://images.unsplash.com/photo-1542601906990-b4d3fb778b09?w=800" />
                    <jsp:param name="title" value="Eco-Tourism: Exploring the World Responsibly" />
                    <jsp:param name="content" value="How sustainable travel practices are helping preserve natural environments while providing authentic experiences for conscious travelers. Discover destinations pioneering eco-friendly tourism and how to minimize your environmental impact." />
                    <jsp:param name="genre" value="Travel" />
                    <jsp:param name="created_by" value="neha_singh" />
                    <jsp:param name="formattedDate" value="April 12, 2025" />
                    <jsp:param name="readTime" value="7 min read" />
                    <jsp:param name="authorName" value="Neha Singh" />
                    <jsp:param name="authorRole" value="Travel Blogger" />
                </jsp:include>
            </div>
            <div class="post-card">
                <jsp:include page="../components/blog_card.jsp">
                    <jsp:param name="blog_id" value="5" />
                    <jsp:param name="thumbnail" value="https://images.unsplash.com/photo-1616628188050-7b7d8b970dee?w=800" />
                    <jsp:param name="title" value="Mindfulness Practices for Busy Professionals" />
                    <jsp:param name="content" value="Simple, effective techniques to incorporate mindfulness into your daily routine, even with a packed schedule. Learn how small moments of presence can lead to improved focus, reduced stress, and better work-life balance." />
                    <jsp:param name="genre" value="Wellness" />
                    <jsp:param name="created_by" value="vikram_patel" />
                    <jsp:param name="formattedDate" value="April 10, 2025" />
                    <jsp:param name="readTime" value="4 min read" />
                    <jsp:param name="authorName" value="Vikram Patel" />
                    <jsp:param name="authorRole" value="Wellness Coach" />
                </jsp:include>
            </div>
            <div class="post-card">
                <jsp:include page="../components/blog_card.jsp">
                    <jsp:param name="blog_id" value="6" />
                    <jsp:param name="thumbnail" value="https://images.unsplash.com/photo-1472214103451-9374bd1c798e?w=800" />
                    <jsp:param name="title" value="Climate Action: Small Steps for Big Change" />
                    <jsp:param name="content" value="How individual actions collectively contribute to environmental preservation. Discover practical ways to reduce your carbon footprint through everyday choices in transportation, food consumption, and energy usage at home and work." />
                    <jsp:param name="genre" value="Environment" />
                    <jsp:param name="created_by" value="meera_sharma" />
                    <jsp:param name="formattedDate" value="April 5, 2025" />
                    <jsp:param name="readTime" value="9 min read" />
                    <jsp:param name="authorName" value="Meera Sharma" />
                    <jsp:param name="authorRole" value="Climate Scientist" />
                </jsp:include>
            </div>
        </div>
    </div>
</section>