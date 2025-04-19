<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="${contextPath}/css/admin_dashboard.css">
    <link href="https://fonts.googleapis.com/css2?family=DM+Sans:wght@400;500;700&family=Montserrat:wght@400;500;600;700;800&display=swap" rel="stylesheet">
    <style>
        /* Additional styles for the graph */
        .graph-container {
            padding: 20px 10px;
            height: 300px;
            position: relative;
        }
        .bar-chart {
            height: 100%;
            width: 100%;
            display: flex;
            align-items: flex-end;
            justify-content: space-around;
        }
        .bar-container {
            display: flex;
            flex-direction: column;
            align-items: center;
            height: 100%;
            width: 100%;
        }
        .bar {
            width: 60px;
            max-width: 60px;
            background: linear-gradient(to top, #2a9d8f, #40c9b9);
            border-radius: 5px 5px 0 0;
            position: relative;
            transition: height 0.5s ease;
        }
        .bar-label {
            margin-top: 8px;
            font-size: 12px;
            text-align: center;
            color: #4f6380;
        }
        .value-label {
            position: absolute;
            top: -25px;
            font-size: 12px;
            font-weight: bold;
        }
        .legend {
            display: flex;
            justify-content: center;
            margin-top: 20px;
        }
        .legend-item {
            display: flex;
            align-items: center;
            margin: 0 15px;
        }
        .legend-color {
            width: 15px;
            height: 15px;
            margin-right: 5px;
            border-radius: 3px;
        }
        .grid-line {
            position: absolute;
            width: 100%;
            height: 1px;
            background-color: #e2e8f0;
        }
        .grid-label {
            position: absolute;
            left: 5px;
            font-size: 10px;
            color: #8794a8;
        }
    </style>
</head>
<body>
    <jsp:include page="../components/fontawesome.jsp"></jsp:include>
    <jsp:include page="../components/message_handler.jsp"></jsp:include>
    <!-- Include the navbar component -->
    <jsp:include page="../components/admin_navbar.jsp" />

    <!-- Main Content -->
    <div class="content">
        <div class="dashboard-header">
            <h1>Dashboard</h1>
            <p id="currentDateTime" class="current-time"></p>
        </div>
        
        <!-- Welcome Card -->
        <div class="card welcome-card">
            <h2>Welcome to Admin Dashboard</h2>
            <p>Hello, aashu79! Manage your application with ease.</p>
        </div>
        
        <!-- Stats Cards -->
        <div class="stats-container">
            <div class="card stat-card">
                <div class="stat-icon">
                    <i class="fas fa-users"></i>
                </div>
                <div class="stat-details">
                    <h3>Total Users</h3>
                    <p class="stat-number">256</p>
                </div>
            </div>
            
            <div class="card stat-card">
                <div class="stat-icon">
                    <i class="fas fa-blog"></i>
                </div>
                <div class="stat-details">
                    <h3>Total Blogs</h3>
                    <p class="stat-number">124</p>
                </div>
            </div>
            
            <div class="card stat-card">
                <div class="stat-icon">
                    <i class="fas fa-comments"></i>
                </div>
                <div class="stat-details">
                    <h3>Comments</h3>
                    <p class="stat-number">842</p>
                </div>
            </div>
        </div>
        

        <!-- Recent Blogs Section -->
        <div class="card section-card">
            <div class="section-header">
                <h2>Recent Blogs</h2>
            </div>
            <div class="table-container">
                <table>
                    <thead>
                        <tr>
                            <th>Title</th>
                            <th>Author</th>
                            <th>Category</th>
                            <th>Date</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Getting Started with JavaScript</td>
                            <td>John Doe</td>
                            <td>Technology</td>
                            <td>2025-04-18</td>
                        </tr>
                        <tr>
                            <td>10 Healthy Breakfast Ideas</td>
                            <td>Mary Smith</td>
                            <td>Food</td>
                            <td>2025-04-17</td>
                        </tr>
                        <tr>
                            <td>Top Travel Destinations 2025</td>
                            <td>Alex Johnson</td>
                            <td>Travel</td>
                            <td>2025-04-15</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        
        <!-- Recent Users Section -->
        <div class="card section-card">
            <div class="section-header">
                <h2>Recent Users</h2>
            </div>
            <div class="table-container">
                <table>
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Join Date</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Sarah Johnson</td>
                            <td>sarah.j@example.com</td>
                            <td>2025-04-19</td>
                        </tr>
                        <tr>
                            <td>Michael Brown</td>
                            <td>m.brown@example.com</td>
                            <td>2025-04-18</td>
                        </tr>
                        <tr>
                            <td>Emily Wilson</td>
                            <td>e.wilson@example.com</td>
                            <td>2025-04-17</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

   
</body>
</html>