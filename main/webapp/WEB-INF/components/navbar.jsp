<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BlogNexus</title>
    <link href="https://fonts.googleapis.com/css2?family=DM+Sans:wght@400;500;700&family=Satisfy&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <!-- Link to your external CSS file -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/navbar.css">
    <!-- You can embed your CSS here if you prefer everything in one file -->
</head>
<body>
    <nav class="navbar">
        <div class="navbar-container">
            <!-- Logo -->
            <div class="navbar-logo">
                <a href="/" class="logo-link">

                    <span class="logo-text">BlogNexus</span>
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
                        <a href="index.html" class="navbar-link">Home</a>
                    </li>
                    <li class="navbar-item has-submenu">
                        <a href="javascript:void(0)" class="navbar-link">
                            Categories
                            <i class="fa-solid fa-chevron-down"></i>
                        </a>
                        <ul class="submenu">
                            <li><a href="category.html?cat=technology"><i class="fa-solid fa-microchip"></i> Technology</a></li>
                            <li><a href="category.html?cat=travel"><i class="fa-solid fa-compass"></i> Travel</a></li>
                            <li><a href="category.html?cat=food"><i class="fa-solid fa-utensils"></i> Food</a></li>
                            <li><a href="category.html?cat=lifestyle"><i class="fa-solid fa-heart"></i> Lifestyle</a></li>
                            <li><a href="category.html?cat=fashion"><i class="fa-solid fa-shirt"></i> Fashion</a></li>
                            <li><a href="category.html?cat=health"><i class="fa-solid fa-heart-pulse"></i> Health</a></li>
                        </ul>
                    </li>
                    <li class="navbar-item">
                        <a href="featured.html" class="navbar-link">Featured</a>
                    </li>
                    <li class="navbar-item">
                        <a href="about.html" class="navbar-link">About</a>
                    </li>
                    <li class="navbar-item">
                        <a href="contact.html" class="navbar-link">Contact</a>
                    </li>
                </ul>
                
                <!-- Search Form -->
                <form class="search-form" action="search.html" method="get">
                    <div class="search-container">
                        <input type="text" name="query" placeholder="Search..." class="search-input">
                        <button type="submit" class="search-btn" aria-label="Search">
                            <i class="fa-solid fa-search"></i>
                        </button>
                    </div>
                </form>
                
                <!-- User Actions -->
                <div class="user-actions">
                    <a href="login.html" class="btn btn-outline">Sign In</a>
                    <a href="register.html" class="btn btn-primary">Sign Up</a>
                </div>
            </div>
        </div>
        
        <!-- Mobile menu backdrop -->
        <div class="backdrop" id="menu-backdrop"></div>
    </nav>


    <!-- JavaScript embedded at the end of the file -->
    <script>
    document.addEventListener('DOMContentLoaded', function() {
        // Elements
        const navbar = document.querySelector('.navbar');
        const navbarToggle = document.getElementById('navbar-toggle');
        const navbarContent = document.getElementById('navbar-content');
        const backdrop = document.getElementById('menu-backdrop');
        const submenuParents = document.querySelectorAll('.has-submenu');
        
        // Variables
        let lastScrollTop = 0;
        const scrollThreshold = 100; // Minimum scroll before navbar hides
        
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
        
        // Sticky navbar on scroll
        window.addEventListener('scroll', function() {
            const scrollTop = window.pageYOffset || document.documentElement.scrollTop;
            
            // Add sticky class when scrolled
            if (scrollTop > 10) {
                navbar.classList.add('sticky');
            } else {
                navbar.classList.remove('sticky');
            }
            
            // Hide/show navbar based on scroll direction
            if (scrollTop > lastScrollTop && scrollTop > scrollThreshold) {
                // Scrolling down
                navbar.classList.add('scroll-down');
                navbar.classList.remove('scroll-up');
            } else if (scrollTop < lastScrollTop) {
                // Scrolling up
                navbar.classList.remove('scroll-down');
                navbar.classList.add('scroll-up');
            }
            
            lastScrollTop = scrollTop <= 0 ? 0 : scrollTop;
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