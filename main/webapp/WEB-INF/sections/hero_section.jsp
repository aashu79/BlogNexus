<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/hero.css">

<section class="hero">
    <div class="container">
        <div class="hero-content">
            <span class="hero-badge"><i class="fas fa-sparkles"></i> Welcome to BlogNexus</span>
            <h1 class="hero-title">Discover Stories That Matter</h1>
            <p class="hero-subtitle">
                Dive into thought-provoking articles, expert insights, and captivating stories from writers around the world.
            </p>
            <div class="hero-actions">
                <a href="#featured" class="btn btn-primary">Explore Articles <i class="fas fa-arrow-right"></i></a>
                <a href="${pageContext.request.contextPath}/register" class="btn btn-outline">Become a Writer <i class="fas fa-pen"></i></a>
            </div>
            <div class="hero-stats">
                <div class="stat-item">
                    <span class="stat-value">10K+</span>
                    <span class="stat-label">Articles</span>
                </div>
                <div class="stat-item">
                    <span class="stat-value">2.5M+</span>
                    <span class="stat-label">Readers</span>
                </div>
                <div class="stat-item">
                    <span class="stat-value">5K+</span>
                    <span class="stat-label">Writers</span>
                </div>
            </div>
        </div>
    </div>

</section>