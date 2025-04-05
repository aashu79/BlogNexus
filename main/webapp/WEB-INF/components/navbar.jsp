<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login to BlogNexus</title>
    <link href="https://fonts.googleapis.com/css2?family=DM+Sans:wght@400;500;700&family=Satisfy&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/navbar.css">
</head>
<body>
<nav class="navbar">
    <div class="navbar-container">
        <!-- Logo -->
        <div class="navbar-logo">
            <a href="index.jsp" class="logo-link">
                <svg class="logo" viewBox="0 0 60 60" fill="none" xmlns="http://www.w3.org/2000/svg">
                    <path d="M30 55C43.8071 55 55 43.8071 55 30C55 16.1929 43.8071 5 30 5C16.1929 5 5 16.1929 5 30C5 43.8071 16.1929 55 30 55Z" fill="#8CBD9D" fill-opacity="0.2"/>
                    <path d="M22 22H38M22 30H33M22 38H38" stroke="#8CBD9D" stroke-width="3" stroke-linecap="round" stroke-linejoin="round"/>
                </svg>
                <span>BlogNexus</span>
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
                <li class="navbar-item active">
                    <a href="index.jsp" class="navbar-link">Home</a>
                </li>
                <li class="navbar-item has-submenu">
                    <a href="javascript:void(0)" class="navbar-link">
                        Categories
                        <i class="fa-solid fa-chevron-down"></i>
                    </a>
                    <ul class="submenu">
                        <li><a href="category.jsp?cat=technology"><i class="fa-solid fa-microchip"></i> Technology</a></li>
                        <li><a href="category.jsp?cat=travel"><i class="fa-solid fa-compass"></i> Travel</a></li>
                        <li><a href="category.jsp?cat=food"><i class="fa-solid fa-utensils"></i> Food</a></li>
                        <li><a href="category.jsp?cat=lifestyle"><i class="fa-solid fa-heart"></i> Lifestyle</a></li>
                        <li><a href="category.jsp?cat=fashion"><i class="fa-solid fa-shirt"></i> Fashion</a></li>
                        <li><a href="category.jsp?cat=health"><i class="fa-solid fa-heart-pulse"></i> Health</a></li>
                    </ul>
                </li>
                <li class="navbar-item">
                    <a href="featured.jsp" class="navbar-link">Featured</a>
                </li>
                <li class="navbar-item">
                    <a href="about.jsp" class="navbar-link">About</a>
                </li>
                <li class="navbar-item">
                    <a href="contact.jsp" class="navbar-link">Contact</a>
                </li>
            </ul>
            
            <!-- Search Form -->
            <form class="search-form" action="search.jsp" method="get">
                <div class="search-container">
                    <input type="text" name="query" placeholder="Search..." class="search-input">
                    <button type="submit" class="search-btn" aria-label="Search">
                        <i class="fa-solid fa-search"></i>
                    </button>
                </div>
            </form>
            
            <!-- User Actions (Simplified) -->
            <div class="user-actions">
               
                <a href="login.jsp" class="btn btn-outline">Sign In</a>
                <a href="register.jsp" class="btn btn-primary">Sign Up</a>
            </div>
        </div>
    </div>
</nav>
\

<script>
document.addEventListener('DOMContentLoaded', function() {
    // Mobile menu toggle
    const navbarToggle = document.getElementById('navbar-toggle');
    const navbarContent = document.getElementById('navbar-content');
    
    navbarToggle.addEventListener('click', function() {
        this.classList.toggle('active');
        navbarContent.classList.toggle('active');
        document.body.classList.toggle('no-scroll');
    });
    
    // Handle mobile submenu toggles
    const submenuParents = document.querySelectorAll('.has-submenu');
    
    if (window.innerWidth < 992) {
        submenuParents.forEach(function(parent) {
            const link = parent.querySelector('.navbar-link');
            
            link.addEventListener('click', function(e) {
                if (window.innerWidth < 992) {
                    e.preventDefault();
                    parent.classList.toggle('active');
                }
            });
        });
    }
    
    // Handle scroll behavior
    let lastScrollTop = 0;
    const navbar = document.querySelector('.navbar');
    
    window.addEventListener('scroll', function() {
        const scrollTop = window.pageYOffset || document.documentElement.scrollTop;
        
        // Add sticky class when scrolled
        if (scrollTop > 10) {
            navbar.classList.add('sticky');
        } else {
            navbar.classList.remove('sticky');
        }
        
        // Hide/show navbar based on scroll direction
        if (scrollTop > lastScrollTop && scrollTop > 100) {
            // Scrolling down
            navbar.classList.add('scroll-down');
            navbar.classList.remove('scroll-up');
        } else {
            // Scrolling up
            navbar.classList.remove('scroll-down');
            navbar.classList.add('scroll-up');
        }
        
        lastScrollTop = scrollTop;
    });
    
    // Adjust for window resize
    window.addEventListener('resize', function() {
        if (window.innerWidth >= 992) {
            navbarToggle.classList.remove('active');
            navbarContent.classList.remove('active');
            document.body.classList.remove('no-scroll');
            
            // Reset submenu behavior for desktop
            submenuParents.forEach(function(parent) {
                parent.classList.remove('active');
            });
        }
    });

    // Update current time regularly
    function updateDateTime() {
        const timeDisplay = document.getElementById('current-time');
        const now = new Date();
        const formattedDate = now.toISOString().slice(0, 19).replace('T', ' ');
        timeDisplay.textContent = formattedDate;
    }
    
    // Update once on load
    updateDateTime();
    
    // Then update every minute
    setInterval(updateDateTime, 60000);
});
</script>
</body>
</html>

