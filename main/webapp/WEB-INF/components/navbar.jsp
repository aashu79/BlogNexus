<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BlogNexus</title>
    <link href="https://fonts.googleapis.com/css2?family=DM+Sans:wght@400;500;700&family=Satisfy&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${contextPath}/css/navbar.css">
    
<style>

</style>
</head>
<body>
<jsp:include page="./fontawesome.jsp"/>

    <%-- Set common variables --%>
    <c:set var="appName" value="BlogNexus" />
    <c:set var="currentPath" value="${pageContext.request.servletPath}" />
    <c:set var="isLoggedIn" value="${sessionScope.isLoggedIn eq true}" />
    <c:set var="isAdmin" value="${not empty sessionScope.user && sessionScope.user.userRole eq 'admin'}" />
    
    <nav class="navbar">
        <div class="navbar-container">
            <!-- Logo -->
            <div class="navbar-logo">
                <a href="${contextPath}/" class="logo-link">
                    <span class="logo-text">${appName}</span>
                </a>
            </div>
            
            <!-- Mobile Toggle Button -->
            <button class="navbar-toggle" id="navbar-toggle" aria-label="Toggle navigation menu">
                <span class="toggle-icon"></span>
            </button>
            
            <!-- Navigation Container -->
            <div class="navbar-content" id="navbar-content">
                <!-- Main Navigation -->
                <ul class="navbar-menu">
                    <li class="navbar-item ${currentPath eq '/' ? 'active' : ''}">
                        <a href="${contextPath}/" class="navbar-link">Home</a>
                    </li>
                    <li class="navbar-item has-submenu">
                        <a href="javascript:void(0)" class="navbar-link">
                            Categories
                            <i class="fa-solid fa-chevron-down"></i>
                        </a>
                        <ul class="submenu">
                            <li><a href="${contextPath}/category?cat=technology"><i class="fa-solid fa-microchip"></i> Technology</a></li>
                            <li><a href="${contextPath}/category?cat=travel"><i class="fa-solid fa-compass"></i> Travel</a></li>
                            <li><a href="${contextPath}/category?cat=food"><i class="fa-solid fa-utensils"></i> Food</a></li>
                            <li><a href="${contextPath}/category?cat=lifestyle"><i class="fa-solid fa-heart"></i> Lifestyle</a></li>
                            <li><a href="${contextPath}/category?cat=fashion"><i class="fa-solid fa-shirt"></i> Fashion</a></li>
                            <li><a href="${contextPath}/category?cat=health"><i class="fa-solid fa-heart-pulse"></i> Health</a></li>
                        </ul>
                    </li>
                    <li class="navbar-item ${currentPath eq '/featured' ? 'active' : ''}">
                        <a href="#featured" class="navbar-link">Featured</a>
                    </li>
                    <li class="navbar-item ${currentPath eq '/about' ? 'active' : ''}">
                        <a href="${contextPath}/about" class="navbar-link">About</a>
                    </li>
                    <li class="navbar-item ${currentPath eq '/contact' ? 'active' : ''}">
                        <a href="${contextPath}/contact" class="navbar-link">Contact</a>
                    </li>
                </ul>
                
                <!-- Simple Search Form -->
                <form class="search-form" action="${contextPath}/search" method="get">
                    <div class="search-container">
                        <input type="text" name="query" placeholder="Search blogs..." class="search-input">
                        <button type="submit" class="search-btn" aria-label="Search">
                            <i class="fa-solid fa-search"></i>
                        </button>
                    </div>
                </form>
                
                <!-- User Actions -->
                <div class="user-actions">
                    <!-- Login/Register buttons (shown when not logged in) -->
                    <div class="guest-actions" style="display: ${!isLoggedIn ? 'flex' : 'none'}">
                        <a href="${contextPath}/login" class="btn btn-outline">Sign In</a>
                        <a href="${contextPath}/register" class="btn btn-primary">Sign Up</a>
                    </div>
                    
                    <!-- User dropdown menu (shown when logged in) -->
                    <div class="user-dropdown" style="display: ${isLoggedIn ? 'block' : 'none'}">
                        <div class="user-menu-toggle" id="user-menu-toggle">
                            <!-- Profile picture or default icon -->
                            <c:choose>
                                <c:when test="${not empty sessionScope.user.profilePictureUrl}">
                                    <img src="${contextPath}${sessionScope.user.profilePictureUrl}" alt="Profile">
                                </c:when>
                                <c:otherwise>
                                    <i class="fa-solid fa-user-circle"></i>
                                </c:otherwise>
                            </c:choose>
                            
                            <span>${sessionScope.user.firstName}</span>
                            <i class="fa-solid fa-chevron-down"></i>
                        </div>
                        <ul class="user-dropdown-menu">
                    
                            <li><a href="${contextPath}/user/profile"><i class="fa-solid fa-user"></i> My Profile</a></li>
                            <li><a href="${contextPath}/user/favourite"><i class="fa fa-heart"></i> Favourites</a></li>
                            <li><a href="${contextPath}/user/blog/create"><i class="fa-solid fa-pen-to-square"></i> Create Blog</a></li>
                            
                            <!-- Admin section (only visible for admin users) -->
                            <li class="divider" style="display: ${isAdmin ? 'block' : 'none'}"></li>
                            <li style="display: ${isAdmin ? 'block' : 'none'}">
                                <a href="${contextPath}/admin/dashboard"><i class="fa-solid fa-shield-halved"></i> Admin Panel</a>
                            </li>
                            
                            <li class="divider"></li>
                            <li><a href="${contextPath}/logout"><i class="fa-solid fa-right-from-bracket"></i> Logout</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        
        <!-- Mobile menu backdrop -->
        <div class="backdrop" id="menu-backdrop"></div>
    </nav>

    <script>
    document.addEventListener('DOMContentLoaded', function() {
        // Elements
        const navbar = document.querySelector('.navbar');
        const navbarToggle = document.getElementById('navbar-toggle');
        const navbarContent = document.getElementById('navbar-content');
        const backdrop = document.getElementById('menu-backdrop');
        const submenuParents = document.querySelectorAll('.has-submenu');
        const userMenuToggle = document.getElementById('user-menu-toggle');
        
        // Mobile menu toggle
        navbarToggle.addEventListener('click', function() {
            this.classList.toggle('active');
            navbarContent.classList.toggle('active');
            backdrop.classList.toggle('active');
            document.body.classList.toggle('no-scroll');
        });
        
        // Close mobile menu when clicking backdrop
        backdrop && backdrop.addEventListener('click', function() {
            navbarToggle.classList.remove('active');
            navbarContent.classList.remove('active');
            backdrop.classList.remove('active');
            document.body.classList.remove('no-scroll');
        });
        
        // Handle submenu on mobile
        submenuParents.forEach(function(parent) {
            const link = parent.querySelector('.navbar-link');
            
            link.addEventListener('click', function(e) {
                if (window.innerWidth < 992) {
                    e.preventDefault();
                    
                    // Close all other open submenus
                    submenuParents.forEach(function(item) {
                        if (item !== parent && item.classList.contains('active')) {
                            item.classList.remove('active');
                        }
                    });
                    
                    // Toggle current submenu
                    parent.classList.toggle('active');
                }
            });
        });

        // User dropdown menu toggle (if logged in)
        if (userMenuToggle) {
            userMenuToggle.addEventListener('click', function(e) {
                e.stopPropagation();
                const userDropdown = this.closest('.user-dropdown');
                userDropdown.classList.toggle('active');
            });
            
            // Close user menu when clicking outside
            document.addEventListener('click', function(e) {
                const userDropdown = document.querySelector('.user-dropdown');
                if (userDropdown && userDropdown.classList.contains('active') && 
                    !userDropdown.contains(e.target)) {
                    userDropdown.classList.remove('active');
                }
            });
        }
        
        // Sticky navbar on scroll
        window.addEventListener('scroll', function() {
            const scrollTop = window.pageYOffset || document.documentElement.scrollTop;
            
            // Add sticky class when scrolled
            if (scrollTop > 10) {
                navbar.classList.add('sticky');
            } else {
                navbar.classList.remove('sticky');
            }
        }, { passive: true });
        
        // Handle window resize
        window.addEventListener('resize', function() {
            if (window.innerWidth >= 992) {
                navbarToggle.classList.remove('active');
                navbarContent.classList.remove('active');
                backdrop.classList.remove('active');
                document.body.classList.remove('no-scroll');
                
                // Reset all active submenus
                submenuParents.forEach(function(item) {
                    item.classList.remove('active');
                });
            }
        });
    });
    </script>
</body>
</html>