<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/about_us.css">
</head>
<body>
 <!-- Include Navbar -->
    <jsp:include page="../components/navbar.jsp" />
  <main>
        <section class="about">
            <h2>Who Are We</h2>
            <p>Welcome to Blog Nexus, your ultimate destination for bloggers seeking to connect, grow, and thrive in the digital landscape.
Our Mission
At Blog Nexus, we believe in the power of words to inspire, educate, and transform. Our platform was built with one vision in mind: to create a vibrant ecosystem where bloggers of all experience levels can showcase their content, build meaningful connections, and accelerate their growth online.</p>
        </section>

        <section class="team-section">
            <h2>OUR TEAM</h2>
            <div class="team-members">
                <div class="team-member">
                    <img src="resources/images/image1.jpg" alt="Team Member 1">
                    <h3>Ashu Kumar Shah</h3>
                    <p>CEO</p>
                </div>
                <div class="team-member">
                    <img src="resources/images/image1.jpg" alt="Team Member 2">
                    <h3>Ayush Bhattarai</h3>
                    <p>Developer</p>
                </div>
                <div class="team-member">
                    <img src="resources/images/image1.jpg" alt="Team Member 3">
                    <h3>Sanjok Shrestha</h3>
                    <p>Marketing Manager</p>
                </div>
                <div class="team-member">
                    <img src="resources/images/image1.jpg" alt="Team Member 4">
                    <h3>Bishal Kumar Rajak</h3>
                    <p>Customer Support</p>
                </div>
                <div class="team-member">
                    <img src="resources/images/image1.jpg" alt="Team Member 5">
                    <h3>Kamakshi Gupta</h3>
                    <p>Software engineer</p>
                </div>
                

            </div>
        </section>

        <section class="company-section">
            <h2>COMPANY HISTORY</h2>
            <ul>
                <div class="milestone-tracker">
  <h3>Our Growth Journey</h3>
  <div class="milestone-container">
    <div class="milestone-item">
      <div class="milestone-date">2023</div>
      <div class="milestone-dot active"></div>
      <div class="milestone-label">Founded</div>
    </div>
    <div class="milestone-item">
      <div class="milestone-date">2024</div>
      <div class="milestone-dot active"></div>
      <div class="milestone-label">Expansion</div>
    </div>
    <div class="milestone-item">
      <div class="milestone-date">2025</div>
      <div class="milestone-dot current"></div>
      <div class="milestone-label">Now</div>
    </div>
    <div class="milestone-item">
      <div class="milestone-date">2026</div>
      <div class="milestone-dot"></div>
      <div class="milestone-label">Future</div>
    </div>
    <div class="milestone-line"></div>
  </div>
  <div class="join-story">
  <h3>OUR STORY</h3>
  <div class="story-paragraph">
  <p>BlogNexus emerged in early 2023 from a series of virtual brainstorming sessions between three digital professionals who found themselves frustrated by the fragmented nature of online content. What began as a shared document of ideas quickly transformed into a small, collaborative platform launched in Kathmandu, with the mission to create a central hub where diverse perspectives could intersect and flourish.</p>
   <p>The founding team's varied backgrounds in technology, content strategy, and data analysis shaped BlogNexus's unique approach to digital publishing. Within months of launch, the site gained attention for its cross-disciplinary insights and thoughtful analysis, attracting readers looking for deeper connections between emerging trends. Today, BlogNexus continues to grow its community of contributors and readers while staying true to its original vision: creating meaningful connections at the intersection of ideas.</p>
    <p>Be part of our journey</p>
  
  </div> 
    <div class="circle-mini"></div>
  </div>
</div>                
            </ul>
        </section>
    </main>
  	<jsp:include page="../components/footer.jsp" /> 
</body>
</html>