<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta charset="UTF-8">
<title>Footer</title>
<link rel="stylesheet" href="${contextPath}/css/footer.css">
</head>
<body>
<footer class="site-footer">
  <div class="footer-content">
    <!-- Main Footer Content -->
    <div class="footer-primary">
      <div class="footer-column brand-column">
        <!-- Logo Section -->
        <div class="footer-logo">
          <a href="${contextPath}/" class="logo-link">
            <span class="logo-text">BlogNexus</span>
          </a>
        </div>
        <p class="footer-tagline">Share your stories with the world</p>
        <p class="footer-description">
          BlogNexus is a modern platform for writers, thinkers, and creators to share ideas and connect with readers around the globe.
        </p>
        <div class="social-links">
          <a href="#" aria-label="Facebook"><i class="fab fa-facebook-f"></i></a>
          <a href="#" aria-label="Twitter"><i class="fab fa-twitter"></i></a>
          <a href="#" aria-label="Instagram"><i class="fab fa-instagram"></i></a>
          <a href="#" aria-label="Pinterest"><i class="fab fa-pinterest-p"></i></a>
          <a href="#" aria-label="LinkedIn"><i class="fab fa-linkedin-in"></i></a>
        </div>
      </div>
      
      <div class="footer-column">
        <h3>Explore</h3>
        <ul class="footer-links">
          <li><a href="${contextPath}/">Home</a></li>
          <li><a href="${contextPath}/featured">Featured Articles</a></li>
          <li><a href="${contextPath}/recent">Recent Posts</a></li>
          <li><a href="${contextPath}/popular">Popular Topics</a></li>
          <li><a href="${contextPath}/writers-guidelines">Writer's Guidelines</a></li>
        </ul>
      </div>
      
      <div class="footer-column">
        <h3>Categories</h3>
        <ul class="footer-links">
          <li><a href="${contextPath}/category?cat=technology">Technology</a></li>
          <li><a href="${contextPath}/category?cat=travel">Travel</a></li>
          <li><a href="${contextPath}/category?cat=food">Food</a></li>
          <li><a href="${contextPath}/category?cat=lifestyle">Lifestyle</a></li>
          <li><a href="${contextPath}/category?cat=fashion">Fashion</a></li>
          <li><a href="${contextPath}/category?cat=health">Health</a></li>
        </ul>
      </div>
      
      <div class="footer-column">
        <h3>Company</h3>
        <ul class="footer-links">
          <li><a href="${contextPath}/about">About Us</a></li>
          <li><a href="${contextPath}/contact">Contact</a></li>
   
          <li><a href="${contextPath}/privacy-policy">Privacy Policy</a></li>
          <li><a href="${contextPath}/terms-and-condition">Terms of Service</a></li>
        </ul>
      </div>
    </div>

    <!-- Footer Bottom -->
    <div class="footer-bottom">
      <div class="copyright">
        <p>&copy; 2025 BlogNexus. All rights reserved.</p>
      </div>
      <div class="footer-legal">
        <a href="${contextPath}/privacy-policy">Privacy Policy</a>
        <a href="${contextPath}/terms-and-condition">Terms of Use</a>
      
      </div>
      <div class="footer-credit">
        <p>Made with <i class="fas fa-heart"></i> by <a href="${contextPath}/about">BlogNexus Team</a></p>
      </div>
    </div>
  </div>
</footer>
</body>
</html>