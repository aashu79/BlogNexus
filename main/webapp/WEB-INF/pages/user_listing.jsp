<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Management - Admin Dashboard</title>
    <link rel="stylesheet" href="${contextPath}/css/user_listing.css">
</head>
<body>
    <jsp:include page="../components/fontawesome.jsp"></jsp:include>
    <jsp:include page="../components/message_handler.jsp"></jsp:include>
 
    <jsp:include page="../components/admin_navbar.jsp" />

    <!-- Main Content -->
    <div class="content">
        <div class="dashboard-header">
            <h1>User Management</h1>
          
          
        </div>
        
        <!-- Action Bar -->
        <div class="action-bar">
            <a href="${contextPath}/admin/users/add" class="add-user-link">
                <i class="fas fa-plus"></i> Add New User
            </a>
           
        </div>
        
        <!-- Users Table Card -->
        <div class="card section-card">
            <div class="section-header">
                <h2>Users List</h2>
            </div>
            <div class="table-container">
                <table id="usersTable">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Profile</th>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Phone</th>
                            <th>Country</th>
                            <th>Role</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <!-- Sample user data rows -->
                        <tr>
                            <td>1</td>
                            <td>
                                <div class="user-avatar">
                                    <img src="https://via.placeholder.com/40" alt="User Avatar">
                                </div>
                            </td>
                            <td>John Doe</td>
                            <td>john.doe@example.com</td>
                            <td>+1234567890</td>
                            <td>United States</td>
                            <td><span class="role-badge admin">Admin</span></td>
                            <td>
                                <div class="action-buttons">
                                 
                                    <a href="${contextPath}/admin/users/delete/1" class="action-link delete" title="Delete User">
                                        <i class="fas fa-trash-alt"></i> Delete
                                    </a>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td>
                                <div class="user-avatar">
                                    <img src="https://via.placeholder.com/40" alt="User Avatar">
                                </div>
                            </td>
                            <td>Jane Smith</td>
                            <td>jane.smith@example.com</td>
                            <td>+9876543210</td>
                            <td>Canada</td>
                            <td><span class="role-badge user">User</span></td>
                            <td>
                                <div class="action-buttons">
                                 
                                    <a href="${contextPath}/admin/users/delete/2" class="action-link delete" title="Delete User">
                                        <i class="fas fa-trash-alt"></i> Delete
                                    </a>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>3</td>
                            <td>
                                <div class="user-avatar">
                                    <i class="fas fa-user"></i>
                                </div>
                            </td>
                            <td>Robert Johnson</td>
                            <td>robert@example.com</td>
                            <td>+1122334455</td>
                            <td>Australia</td>
                            <td><span class="role-badge user">User</span></td>
                            <td>
                                <div class="action-buttons">
                                 
                                    <a href="${contextPath}/admin/users/delete/3" class="action-link delete" title="Delete User">
                                        <i class="fas fa-trash-alt"></i> Delete
                                    </a>
                                </div>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>