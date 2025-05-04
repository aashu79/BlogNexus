<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/categories.css">

<section class="categories-section">
    <div class="container" >
        <div class="section-header">
            <h2 class="section-title">Explore Categories</h2>
            <p class="section-subtitle">Discover content tailored to your interests or explore new topics.</p>
        </div>

        <div class="categories-grid">
            <a href="${pageContext.request.contextPath}/category?cat=technology" class="category-card">
                <div class="category-icon">
                    <i class="fas fa-microchip"></i>
                </div>
                <h3 class="category-title">Technology</h3>
                <p class="category-count">245 Articles</p>
            </a>

            <a href="${pageContext.request.contextPath}/category?cat=environment" class="category-card">
                <div class="category-icon">
                    <i class="fas fa-leaf"></i>
                </div>
                <h3 class="category-title">Environment</h3>
                <p class="category-count">182 Articles</p>
            </a>

            <a href="${pageContext.request.contextPath}/category?cat=food" class="category-card">
                <div class="category-icon">
                    <i class="fas fa-utensils"></i>
                </div>
                <h3 class="category-title">Food</h3>
                <p class="category-count">157 Articles</p>
            </a>

            <a href="${pageContext.request.contextPath}/category?cat=travel" class="category-card">
                <div class="category-icon">
                    <i class="fas fa-map-marked-alt"></i>
                </div>
                <h3 class="category-title">Travel</h3>
                <p class="category-count">196 Articles</p>
            </a>
        </div>
    </div>
</section>