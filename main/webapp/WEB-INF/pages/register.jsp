<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- Define a reusable variable for the context path --%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Join BlogNexus | Start Your Journey</title>
    <link href="https://fonts.googleapis.com/css2?family=DM+Sans:wght@400;500;700&family=Satisfy&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="${contextPath}/css/register.css">
    <style>
        .form-message {
            color: #b94a48;
            background: #fbe3e4;
            border: 1px solid #fbc2c4;
            padding: 12px;
            border-radius: 5px;
            margin-bottom: 18px;
            text-align: center;
            font-size: 1.05em;
        }
    </style>
</head>
<body>
    <div class="page-container">
        <!-- Left section with a background -->
        <div class="visual-section">
            <div class="brand">
                <svg class="logo" viewBox="0 0 60 60" fill="none" xmlns="http://www.w3.org/2000/svg">
                    <path d="M30 55C43.8071 55 55 43.8071 55 30C55 16.1929 43.8071 5 30 5C16.1929 5 5 16.1929 5 30C5 43.8071 16.1929 55 30 55Z" fill="#FFF3C0" fill-opacity="0.7"/>
                    <path d="M22 22H38M22 30H33M22 38H38" stroke="#8CBD9D" stroke-width="3" stroke-linecap="round" stroke-linejoin="round"/>
                </svg>
                <span>BlogNexus</span>
            </div>
            <div class="illustration">
                <div class="blob blob-1"></div>
                <div class="blob blob-2"></div>
                <div class="blob blob-3"></div>
                <div class="deco-element tech">
                    <svg viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path d="M20 7H4C2.89543 7 2 7.89543 2 9V19C2 20.1046 2.89543 21 4 21H20C21.1046 21 22 20.1046 22 19V9C22 7.89543 21.1046 7 20 7Z" stroke="#83D0CB" stroke-width="2" stroke-linecap="round"/>
                        <path d="M16 21V5C16 3.89543 15.1046 3 14 3H10C8.89543 3 8 3.89543 8 5V21" stroke="#83D0CB" stroke-width="2" stroke-linecap="round"/>
                    </svg>
                </div>
                <div class="deco-element travel">
                    <svg viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path d="M17.5 19H9.5L8.5 22H16.5L17.5 19Z" stroke="#E8C07D" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
                        <path d="M5 11.5C5 11.5 7.5 9 12.5 9C17.5 9 20 11.5 20 11.5" stroke="#E8C07D" stroke-width="2" stroke-linecap="round"/>
                        <path d="M5 14.5C5 14.5 7.5 17 12.5 17C17.5 17 20 14.5 20 14.5" stroke="#E8C07D" stroke-width="2" stroke-linecap="round"/>
                        <path d="M12.5 17V22" stroke="#E8C07D" stroke-width="2" stroke-linecap="round"/>
                        <path d="M12.5 9V2" stroke="#E8C07D" stroke-width="2" stroke-linecap="round"/>
                    </svg>
                </div>
                <div class="deco-element food">
                    <svg viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path d="M6 15V19C6 20.1046 6.89543 21 8 21H16C17.1046 21 18 20.1046 18 19V15" stroke="#8CBD9D" stroke-width="2" stroke-linecap="round"/>
                        <path d="M5 10H19C20.1046 10 21 10.8954 21 12V13C21 14.1046 20.1046 15 19 15H5C3.89543 15 3 14.1046 3 13V12C3 10.8954 3.89543 10 5 10Z" stroke="#8CBD9D" stroke-width="2" stroke-linecap="round"/>
                        <path d="M16 10V3M8 10V3" stroke="#8CBD9D" stroke-width="2" stroke-linecap="round"/>
                    </svg>
                </div>
                <div class="deco-element lifestyle">
                    <svg viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path d="M12 21C16.9706 21 21 16.9706 21 12C21 7.02944 16.9706 3 12 3C7.02944 3 3 7.02944 3 12C3 16.9706 7.02944 21 12 21Z" stroke="#B8E0D2" stroke-width="2"/>
                        <path d="M12 7V12L15 15" stroke="#B8E0D2" stroke-width="2" stroke-linecap="round"/>
                    </svg>
                </div>
                <div class="dot dot-1"></div>
                <div class="dot dot-2"></div>
                <div class="dot dot-3"></div>
                <div class="dot dot-4"></div>
                <div class="tagline">
                    <h2>Tell your story to the world</h2>
                    <p>Join thousands of writers and creators who share their passion through words</p>
                </div>
            </div>
            <div class="quote">
                <span class="quote-mark">"</span>
                <p>The beautiful thing about writing is that you can use words to create worlds.</p>
            </div>
        </div>

        <!-- Right section with form -->
        <div class="form-section">
            <div class="mobile-logo">
                <svg class="logo" viewBox="0 0 60 60" fill="none" xmlns="http://www.w3.org/2000/svg">
                    <path d="M30 55C43.8071 55 55 43.8071 55 30C55 16.1929 43.8071 5 30 5C16.1929 5 5 16.1929 5 30C5 43.8071 16.1929 55 30 55Z" fill="#E8F4EA" fill-opacity="0.7"/>
                    <path d="M22 22H38M22 30H33M22 38H38" stroke="#8CBD9D" stroke-width="3" stroke-linecap="round" stroke-linejoin="round"/>
                </svg>
                <span>BlogNexus</span>
            </div>
            <div class="form-header">
                <h1>Create your account</h1>
                <p>Start your blogging journey today</p>
            </div>
        <jsp:include page="../components/message_handler.jsp"></jsp:include>
            <form action="${contextPath}/register" method="post" enctype="multipart/form-data">                
                <div class="compact-form">
                    <div class="form-columns">
                        <div class="form-column">
                            <div class="form-field">
                                <div class="input-with-icon">
                                    <input type="text" id="firstName" name="firstName" placeholder="First Name" required>
                                    <i class="fa-regular fa-user"></i>
                                </div>
                            </div>
                            <div class="form-field">
                                <div class="input-with-icon">
                                    <input type="email" id="email" name="email" placeholder="Email Address" required>
                                    <i class="fa-regular fa-envelope"></i>
                                </div>
                            </div>
                            <div class="form-field password-field">
                                <div class="input-with-icon">
                                    <input type="password" id="password" name="password" placeholder="Create Password" required>
                                    <i class="fa-solid fa-lock"></i>
                                    <span class="toggle-password" id="passwordToggle" tabindex="0">
                                        <i class="fa-regular fa-eye"></i>
                                    </span>
                                </div>
                            </div>
                        </div>
                        <div class="form-column">
                            <div class="form-field">
                                <div class="input-with-icon">
                                    <input type="text" id="lastName" name="lastName" placeholder="Last Name" required>
                                    <i class="fa-regular fa-user"></i>
                                </div>
                            </div>
                            <div class="form-field">
                                <div class="input-with-icon">
                                    <input type="tel" id="phone" name="phone" placeholder="Phone Number (Optional)">
                                    <i class="fa-solid fa-phone"></i>
                                </div>
                            </div>
                            <div class="form-field password-field">
                                <div class="input-with-icon">
                                    <input type="password" id="confirmPassword" name="confirmPassword" placeholder="Confirm Password" required>
                                    <i class="fa-solid fa-lock"></i>
                                    <span class="toggle-password" id="confirmPasswordToggle" tabindex="0">
                                        <i class="fa-regular fa-eye"></i>
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-field select-field">
                        <div class="custom-select">
                            <select id="country" name="country" required>
                                <option value="" disabled selected>Select your country</option>
                                <option value="nepal">Nepal</option>
                                <option value="america">America</option>
                                <option value="canada">Canada</option>
                                <option value="united_kingdom">United Kingdom</option>
                                <option value="australia">Australia</option>
                                <option value="india">India</option>
                                <option value="japan">Japan</option>
                            </select>
                        </div>
                    </div>
                    <div class="profile-section">
                        <div class="profile-upload">
                            <div class="upload-placeholder" id="uploadPlaceholder">
                                <div class="upload-icon">
                                    <i class="fa-solid fa-cloud-arrow-up"></i>
                                </div>
                                <span>Upload photo</span>
                            </div>
                            <div class="upload-preview" id="uploadPreview" style="display:none;">
                                <img id="previewImage" alt="Profile preview">
                                <button type="button" class="remove-image" id="removeImage">
                                    <i class="fa-solid fa-xmark"></i>
                                </button>
                            </div>
                            <input type="file" id="profilePicture" name="profilePicture" accept="image/*" class="visually-hidden">
                        </div>
                    </div>
                    <div class="form-action">
                        <button type="submit" class="btn submit-btn">Create Account</button>
                    </div>
                </div>
            </form>
            <div class="form-footer">
                <p>Already have an account? <a href="${contextPath}/login.jsp">Sign in</a></p>
            </div>
        </div>
    </div>
    <script>
        // Password visibility toggle 
        document.getElementById('passwordToggle').addEventListener('click', function() {
            const passwordInput = document.getElementById('password');
            const icon = this.querySelector('i');
            if (passwordInput.type === 'password') {
                passwordInput.type = 'text';
                icon.classList.remove('fa-eye');
                icon.classList.add('fa-eye-slash');
            } else {
                passwordInput.type = 'password';
                icon.classList.remove('fa-eye-slash');
                icon.classList.add('fa-eye');
            }
        });
        document.getElementById('confirmPasswordToggle').addEventListener('click', function() {
            const confirmPasswordInput = document.getElementById('confirmPassword');
            const icon = this.querySelector('i');
            if (confirmPasswordInput.type === 'password') {
                confirmPasswordInput.type = 'text';
                icon.classList.remove('fa-eye');
                icon.classList.add('fa-eye-slash');
            } else {
                confirmPasswordInput.type = 'password';
                icon.classList.remove('fa-eye-slash');
                icon.classList.add('fa-eye');
            }
        });
        // Profile picture upload
        const uploadPlaceholder = document.getElementById('uploadPlaceholder');
        const uploadPreview = document.getElementById('uploadPreview');
        const profileInput = document.getElementById('profilePicture');
        const previewImage = document.getElementById('previewImage');
        const removeButton = document.getElementById('removeImage');
        uploadPlaceholder.addEventListener('click', function() {
            profileInput.click();
        });
        profileInput.addEventListener('change', function() {
            if (this.files && this.files[0]) {
                const reader = new FileReader();
                reader.onload = function(e) {
                    previewImage.src = e.target.result;
                    uploadPreview.style.display = 'block';
                    uploadPlaceholder.style.display = 'none';
                };
                reader.readAsDataURL(this.files[0]);
            }
        });
        removeButton.addEventListener('click', function(e) {
            e.stopPropagation();
            profileInput.value = '';
            uploadPreview.style.display = 'none';
            uploadPlaceholder.style.display = 'flex';
        });
    </script>
</body>
</html>