<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <!-- FontAwesome CSS -->
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/user_favourite.css">
  
    
    <title>My Favorite Blogs</title>
</head>
<body>
<jsp:include page="../components/fontawesome.jsp"></jsp:include>
    <!-- Header -->
   <jsp:include page="../components/navbar.jsp"></jsp:include>
    
    <!-- Page Content -->
    <main class="main-content">
        <div class="container">
            <div class="page-header">
                <h1>My Favorite Blogs</h1>
              
            </div>
            
            <!-- Favorites Grid -->
            <div class="favorites-grid">
                <!-- Include favorite blog cards -->
                <jsp:include page="../components/favourite_blog_card.jsp">
                    <jsp:param name="blogId" value="1" />
                    <jsp:param name="imageUrl" value="https://images.unsplash.com/photo-1465146344425-f00d5f5c8f07?q=80&w=2676&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D" />
                    <jsp:param name="category" value="Nature" />
                    <jsp:param name="date" value="April 14, 2025" />
                    <jsp:param name="readTime" value="5 min read" />
                    <jsp:param name="title" value="The Beauty of Natural Landscapes and Their Impact on Mental Wellness" />
                    <jsp:param name="excerpt" value="Exploring how spending time in nature can reduce stress, improve mood, and enhance overall mental health. Research shows even brief exposure to natural environments provides significant benefits." />
                    <jsp:param name="authorName" value="Aashu Sharma" />
                    <jsp:param name="authorRole" value="Environmental Writer" />
                </jsp:include>

                <jsp:include page="../components/favourite_blog_card.jsp">
                    <jsp:param name="blogId" value="2" />
                    <jsp:param name="imageUrl" value="https://images.unsplash.com/photo-1488590528505-98d2b5aba04b?q=80&w=2670&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D" />
                    <jsp:param name="category" value="Technology" />
                    <jsp:param name="date" value="April 10, 2025" />
                    <jsp:param name="readTime" value="8 min read" />
                    <jsp:param name="title" value="The Future of Sustainable Technology in Everyday Life" />
                    <jsp:param name="excerpt" value="How eco-friendly innovations are transforming the tech industry, from biodegradable components to energy-efficient designs that reduce environmental impact." />
                    <jsp:param name="authorName" value="Priya Mehta" />
                    <jsp:param name="authorRole" value="Tech Analyst" />
                </jsp:include>

                <jsp:include page="../components/favourite_blog_card.jsp">
                    <jsp:param name="blogId" value="3" />
                    <jsp:param name="imageUrl" value="https://images.unsplash.com/photo-1498837167922-ddd27525d352?q=80&w=2670&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D" />
                    <jsp:param name="category" value="Food" />
                    <jsp:param name="date" value="April 5, 2025" />
                    <jsp:param name="readTime" value="6 min read" />
                    <jsp:param name="title" value="Plant-Based Diets: Benefits for Health and Environment" />
                    <jsp:param name="excerpt" value="Exploring the dual impact of plant-based eating on personal health outcomes and environmental sustainability. Research shows significant benefits for both." />
                    <jsp:param name="authorName" value="Raj Kumar" />
                    <jsp:param name="authorRole" value="Nutrition Expert" />
                </jsp:include>

                <jsp:include page="../components/favourite_blog_card.jsp">
                    <jsp:param name="blogId" value="4" />
                    <jsp:param name="imageUrl" value="https://images.unsplash.com/photo-1542601906990-b4d3fb778b09?q=80&w=2613&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D" />
                    <jsp:param name="category" value="Travel" />
                    <jsp:param name="date" value="March 28, 2025" />
                    <jsp:param name="readTime" value="7 min read" />
                    <jsp:param name="title" value="Eco-Tourism: Exploring the World Responsibly" />
                    <jsp:param name="excerpt" value="How sustainable travel practices are helping preserve natural environments while providing authentic experiences for conscious travelers." />
                    <jsp:param name="authorName" value="Neha Singh" />
                    <jsp:param name="authorRole" value="Travel Blogger" />
                </jsp:include>

                <jsp:include page="../components/favourite_blog_card.jsp">
                    <jsp:param name="blogId" value="5" />
                    <jsp:param name="imageUrl" value="https://images.unsplash.com/photo-1616628188050-7b7d8b970dee?q=80&w=2787&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D" />
                    <jsp:param name="category" value="Wellness" />
                    <jsp:param name="date" value="March 22, 2025" />
                    <jsp:param name="readTime" value="4 min read" />
                    <jsp:param name="title" value="Mindfulness Practices for Busy Professionals" />
                    <jsp:param name="excerpt" value="Simple, effective techniques to incorporate mindfulness into your daily routine, even with a packed schedule. Learn how small moments can make big impacts." />
                    <jsp:param name="authorName" value="Vikram Patel" />
                    <jsp:param name="authorRole" value="Wellness Coach" />
                </jsp:include>

                <jsp:include page="../components/favourite_blog_card.jsp">
                    <jsp:param name="blogId" value="6" />
                    <jsp:param name="imageUrl" value="https://images.unsplash.com/photo-1472214103451-9374bd1c798e?q=80&w=2670&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D" />
                    <jsp:param name="category" value="Environment" />
                    <jsp:param name="date" value="March 15, 2025" />
                    <jsp:param name="readTime" value="9 min read" />
                    <jsp:param name="title" value="Climate Action: Small Steps for Big Change" />
                    <jsp:param name="excerpt" value="How individual actions collectively contribute to environmental preservation. Discover practical ways to reduce your carbon footprint and live more sustainably." />
                    <jsp:param name="authorName" value="Meera Sharma" />
                    <jsp:param name="authorRole" value="Climate Scientist" />
                </jsp:include>
            </div>
            
      
        </div>
    </main>
    
    <!-- Footer -->
    <jsp:include page="../components/footer.jsp"></jsp:include>
   
    
   
</body>
</html>