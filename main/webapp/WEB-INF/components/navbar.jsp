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
    
    /* Main dropdown container */
    .user-dropdown {
        position: relative;
    }
    
    /* Profile button styling - enhanced */
    .user-menu-toggle {
        display: flex;
        align-items: center;
        gap: 10px;
        padding: 8px 14px;
        background-color: #f0f9f0;
        border-radius: 10px;
        cursor: pointer;
        border: 1px solid #c8e6c9;
        box-shadow: 0 2px 5px rgba(76, 175, 80, 0.08);
        transition: all 0.2s ease;
    }
    
    /* Hover effect */
    .user-menu-toggle:hover {
        background-color: #e8f5e9;
        box-shadow: 0 3px 8px rgba(76, 175, 80, 0.12);
    }
    
    /* Profile icon when no image - bigger */
    .user-menu-toggle i.fa-user-circle {
        font-size: 32px; /* Increased from 24px */
        color: #43a047;
        filter: drop-shadow(0 1px 2px rgba(0, 0, 0, 0.1));
    }
    
    /* Down arrow icon */
    .user-menu-toggle i.fa-chevron-down {
        font-size: 12px;
        color: #66bb6a;
        margin-left: 4px;
        transition: transform 0.3s ease;
    }
    
    /* Rotate arrow when menu is open */
    .user-dropdown.active .user-menu-toggle i.fa-chevron-down {
        transform: rotate(180deg);
    }
    
    /* Profile picture styling - bigger and more visible */
    .user-menu-toggle img {
        width: 36px; /* Increased from 28px */
        height: 36px; /* Increased from 28px */
        border-radius: 50%;
        border: 2.5px solid #4caf50;
        object-fit: cover;
        box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
        transition: all 0.2s ease;
    }
    
    /* Profile picture hover effect */
    .user-menu-toggle:hover img {
        border-color: #388e3c;
        transform: scale(1.05);
    }
    
    /* Username text - improved visibility */
    .user-menu-toggle span {
        font-weight: 600;
        color: #2e7d32;
        max-width: 120px; /* Slightly increased */
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
        letter-spacing: 0.2px;
    }
    
    /* Dropdown menu container - improved styling */
    .user-dropdown-menu {
        position: absolute;
        top: 100%;
        right: 0;
        margin-top: 10px;
        width: 220px; /* Slightly increased */
        background-color: #ffffff;
        border-radius: 12px;
        padding: 10px;
        box-shadow: 0 8px 20px rgba(0, 0, 0, 0.12);
        border: 1px solid #d7f0d7;
        z-index: 1000;
        
        /* Hidden by default */
        display: none;
    }
    
    /* Show menu when active */
    .user-dropdown.active .user-dropdown-menu {
        display: block;
        animation: dropdownFade 0.2s ease;
    }
    
    /* Menu items */
    .user-dropdown-menu li {
        list-style: none;
        margin: 2px 0;
    }
    
    /* Divider line */
    .user-dropdown-menu li.divider {
        height: 1px;
        background-color: #e8f5e9;
        margin: 8px 0;
    }
    
    /* Menu links */
    .user-dropdown-menu a {
        display: flex;
        align-items: center;
        gap: 12px;
        padding: 12px 14px;
        text-decoration: none;
        color: #388e3c;
        font-size: 14px;
        font-weight: 500;
        border-radius: 8px;
        transition: all 0.2s ease;
    }
    
    /* Menu link hover */
    .user-dropdown-menu a:hover {
        color: #2e7d32;
        background-color: #f1f8e9;
        transform: translateX(2px);
    }
    
    /* Menu icons */
    .user-dropdown-menu i {
        width: 18px;
        text-align: center;
        color: #66bb6a;
        font-size: 15px;
    }
    
    /* Menu icon hover */
    .user-dropdown-menu a:hover i {
        color: #43a047;
    }
    
    /* Logout link - special red styling */
    .user-dropdown-menu a[href*="logout"] {
        color: #e53e3e;
    }
    
    /* Logout link hover */
    .user-dropdown-menu a[href*="logout"]:hover {
        background-color: #ffebee;
    }
    
    /* Logout icon */
    .user-dropdown-menu a[href*="logout"] i {
        color: #e53e3e;
    }
    
    /* Simple animation for dropdown */
    @keyframes dropdownFade {
        from {
            opacity: 0;
            transform: translateY(-5px);
        }
        to {
            opacity: 1;
            transform: translateY(0);
        }
    }
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
                        <a href="${contextPath}/featured" class="navbar-link">Featured</a>
                    </li>
                    <li class="navbar-item ${currentPath eq '/about' ? 'active' : ''}">
                        <a href="${contextPath}/about" class="navbar-link">About</a>
                    </li>
                    <li class="navbar-item ${currentPath eq '/contact' ? 'active' : ''}">
                        <a href="${contextPath}/contact" class="navbar-link">Contact</a>
                    </li>
                </ul>
                
                <!-- Search Form -->
                <form class="search-form" action="${contextPath}/search" method="get">
                    <div class="search-container">
                        <input type="text" name="query" placeholder="Search..." class="search-input">
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
				          <img src="${not empty sessionScope.user.profilePicture ? contextPath.concat('/resources/imagesprofileImages/').concat(sessionScope.user.profilePicture) : '#'}" 
     				style="display: ${not empty sessionScope.user.profilePicture ? 'inline-block' : 'none'}" 
     					alt="Profile">
				            <i class="fa-solid fa-user-circle" style="display: ${empty sessionScope.user.profilePicture ? 'inline-block' : 'none'}"></i>
				            
				            <span>${sessionScope.user.firstName}</span>
				            <i class="fa-solid fa-chevron-down"></i>
				        </div>
				        <ul class="user-dropdown-menu">
				            <li><a href="${contextPath}/dashboard"><i class="fa-solid fa-gauge"></i> Dashboard</a></li>
				            <li><a href="${contextPath}/profile"><i class="fa-solid fa-user"></i> My Profile</a></li>
				            <li><a href="${contextPath}/create-post"><i class="fa-solid fa-pen-to-square"></i> Create Blog</a></li>
				            
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