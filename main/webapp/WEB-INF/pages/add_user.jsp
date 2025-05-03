<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add New User - Admin Dashboard</title>
    <link rel="stylesheet" href="${contextPath}/css/add_user.css?v=<%=System.currentTimeMillis()%>">
</head>
<body>
    <jsp:include page="../components/fontawesome.jsp"></jsp:include>
    <jsp:include page="../components/message_handler.jsp"></jsp:include>
    <jsp:include page="../components/admin_navbar.jsp" />

    <!-- Main Content -->
    <div class="content">
        <div class="dashboard-header">
            <h1 class="page-title">Add New User</h1>
            <div>
                <p>Current Date and Time (UTC - YYYY-MM-DD HH:MM:SS formatted): ${currentDateTime}</p>
                <p>Current User's Login: ${currentUser.firstName}</p>
            </div>
        </div>
        
        <!-- Simple Form Card -->
        <div class="simple-card">
            <form action="${contextPath}/admin/users/add" method="post" enctype="multipart/form-data" id="createUserForm">                
                <div class="form-grid">
                    <!-- Left Column -->
                    <div class="form-column">
                        <div class="form-group">
                            <label for="firstName">First Name</label>
                            <div class="input-field">
                                <i class="fa-regular fa-user"></i>
                                <input type="text" id="firstName" name="firstName" placeholder="First name" required>
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label for="email">Email</label>
                            <div class="input-field">
                                <i class="fa-regular fa-envelope"></i>
                                <input type="email" id="email" name="email" placeholder="Email address" required>
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label for="password">Password</label>
                            <div class="input-field">
                                <i class="fa-solid fa-lock"></i>
                                <input type="password" id="password" name="password" placeholder="Password" required>
                                <button type="button" class="toggle-btn" tabindex="-1">
                                    <i class="fa-regular fa-eye"></i>
                                </button>
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label for="role">Role</label>
                            <div class="select-field">
                                <select id="role" name="role" required>
                                    <option value="" disabled selected>Select role</option>
                                    <option value="admin">Administrator</option>
                                    <option value="user">Regular User</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    
                    <!-- Right Column -->
                    <div class="form-column">
                        <div class="form-group">
                            <label for="lastName">Last Name</label>
                            <div class="input-field">
                                <i class="fa-regular fa-user"></i>
                                <input type="text" id="lastName" name="lastName" placeholder="Last name" required>
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label for="phone">Phone</label>
                            <div class="input-field">
                                <i class="fa-solid fa-phone"></i>
                                <input type="tel" id="phone" name="phone" placeholder="Phone (optional)">
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label for="confirmPassword">Confirm Password</label>
                            <div class="input-field">
                                <i class="fa-solid fa-lock"></i>
                                <input type="password" id="confirmPassword" name="confirmPassword" placeholder="Confirm password" required>
                                <button type="button" class="toggle-btn" tabindex="-1">
                                    <i class="fa-regular fa-eye"></i>
                                </button>
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label for="country">Country</label>
                            <div class="select-field">
                                <select id="country" name="country" required>
                                    <option value="" disabled selected>Select country</option>
                                    <option value="nepal">Nepal</option>
                                    <option value="united_states">United States</option>
                                    <option value="canada">Canada</option>
                                    <option value="united_kingdom">United Kingdom</option>
                                    <option value="australia">Australia</option>
                                    <option value="india">India</option>
                                    <option value="japan">Japan</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
                
                <!-- Profile Picture Upload -->
                <div class="upload-section">
                    <label>Profile Picture</label>
                    <div class="upload-container">
                        <div class="upload-box" id="uploadBox">
                            <i class="fa-solid fa-cloud-arrow-up"></i>
                            <span>Click to upload</span>
                        </div>
                        <div class="preview-box" id="previewBox" style="display:none;">
                            <img id="previewImg" alt="Preview">
                            <button type="button" class="remove-btn" id="removeBtn">×</button>
                        </div>
                        <input type="file" id="profilePicture" name="profilePicture" accept="image/*" hidden>
                    </div>
                </div>
                
                <!-- Form Actions -->
                <div class="form-actions">
                    <button type="button" class="cancel-btn" onclick="window.location.href='${contextPath}/admin/users'">
                        Cancel
                    </button>
                    <button type="submit" class="submit-btn">
                        Create User
                    </button>
                </div>
            </form>
        </div>
    </div>
    
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            // Password toggle functionality
            document.querySelectorAll('.toggle-btn').forEach(function(btn) {
                btn.addEventListener('click', function() {
                    const input = this.parentElement.querySelector('input');
                    const icon = this.querySelector('i');
                    
                    if (input.type === 'password') {
                        input.type = 'text';
                        icon.classList.remove('fa-eye');
                        icon.classList.add('fa-eye-slash');
                    } else {
                        input.type = 'password';
                        icon.classList.remove('fa-eye-slash');
                        icon.classList.add('fa-eye');
                    }
                });
            });
            
            // Profile picture upload
            const uploadBox = document.getElementById('uploadBox');
            const previewBox = document.getElementById('previewBox');
            const previewImg = document.getElementById('previewImg');
            const profilePicture = document.getElementById('profilePicture');
            const removeBtn = document.getElementById('removeBtn');
            
            uploadBox.addEventListener('click', function() {
                profilePicture.click();
            });
            
            profilePicture.addEventListener('change', function() {
                if (this.files && this.files[0]) {
                    const reader = new FileReader();
                    
                    reader.onload = function(e) {
                        previewImg.src = e.target.result;
                        uploadBox.style.display = 'none';
                        previewBox.style.display = 'block';
                    }
                    
                    reader.readAsDataURL(this.files[0]);
                }
            });
            
            removeBtn.addEventListener('click', function() {
                profilePicture.value = '';
                uploadBox.style.display = 'flex';
                previewBox.style.display = 'none';
            });
            
            // Form validation
            const form = document.getElementById('createUserForm');
            form.addEventListener('submit', function(e) {
                const password = document.getElementById('password').value;
                const confirmPassword = document.getElementById('confirmPassword').value;
                
                if (password !== confirmPassword) {
                    e.preventDefault();
                    alert('Passwords do not match!');
                }
                
                // Check password strength
                if (password.length < 8) {
                    e.preventDefault();
                    alert('Password must be at least 8 characters long');
                    return;
                }
                
                let hasLetter = false;
                let hasDigit = false;
                
                for (let i = 0; i < password.length; i++) {
                    const char = password.charAt(i);
                    if (/[a-zA-Z]/.test(char)) hasLetter = true;
                    if (/[0-9]/.test(char)) hasDigit = true;
                }
                
                if (!hasLetter || !hasDigit) {
                    e.preventDefault();
                    alert('Password must contain at least one letter and one number');
                }
            });
        });
    </script>
</body>
</html>