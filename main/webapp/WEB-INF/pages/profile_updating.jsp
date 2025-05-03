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
    <title>Update Your Profile | BlogNexus</title>
    <link href="https://fonts.googleapis.com/css2?family=DM+Sans:wght@400;500;700&family=Satisfy&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${contextPath}/css/profile_updating.css">
    <style>
        /* Improved profile picture preview styles */
        .profile-section {
            display: flex;
            justify-content: center;
            margin-bottom: 30px;
        }
        
        .profile-upload {
            position: relative;
            width: 180px;
            height: 180px;
        }
        
        .upload-preview {
            width: 100%;
            height: 100%;
            position: relative;
            border: 2px solid #e0e0e0;
            background-color: #f9f9f9;
            border-radius: 8px;
            overflow: visible; /* Allow the delete button to be visible outside */
        }
        
        .upload-preview img {
            width: 100%;
            height: 100%;
            object-fit: contain;
            border-radius: 6px;
        }
        
        .remove-image {
            position: absolute;
            top: -10px;
            right: -10px;
            width: 30px;
            height: 30px;
            border-radius: 50%;
            background: #ff4d4f;
            border: 2px solid #fff;
            color: white;
            display: flex;
            align-items: center;
            justify-content: center;
            cursor: pointer;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.15);
            z-index: 10;
        }
        
        .remove-image i {
            font-size: 14px;
        }
        
        .upload-placeholder {
            width: 100%;
            height: 100%;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            border: 2px dashed #aaa;
            border-radius: 8px;
            cursor: pointer;
            background: #f9f9f9;
        }
        
        .upload-icon {
            font-size: 40px;
            color: #aaa;
            margin-bottom: 10px;
        }
        
        .upload-placeholder span {
            color: #666;
            font-size: 14px;
        }
        
        /* Disabled email field styling */
        input[disabled] {
            background-color: #f5f5f5;
            cursor: not-allowed;
            opacity: 0.7;
        }
    </style>
</head>
<body>
<jsp:include page="../components/fontawesome.jsp"/>
<jsp:include page="../components/navbar.jsp"></jsp:include>
    <div class="container">
        <div class="form-header">
            <h1>Update Your Profile</h1>
            <p>Keep your account information up-to-date</p>
        </div>
        
        <div class="form-card">
            <jsp:include page="../components/message_handler.jsp"></jsp:include>
            
            <form action="${contextPath}/user/profile/update" method="post" enctype="multipart/form-data">                
                <div class="profile-section">
                    <div class="profile-upload">
                        <div class="upload-preview" id="uploadPreview" ${empty user.profilePicture ? 'style="display:none;"' : ''}>
                            <img id="previewImage" src="${empty user.profilePictureUrl ? '' : contextPath.concat(user.profilePictureUrl)}" alt="Profile preview">
                            <button type="button" class="remove-image" id="removeImage">
                                <i class="fa-solid fa-xmark"></i>
                            </button>
                        </div>
                        <div class="upload-placeholder" id="uploadPlaceholder" ${empty user.profilePicture ? '' : 'style="display:none;"'}>
                            <div class="upload-icon">
                                <i class="fa-solid fa-cloud-arrow-up"></i>
                            </div>
                            <span>Upload photo</span>
                        </div>
                        <input type="file" id="profilePicture" name="profilePicture" accept="image/*" class="visually-hidden">
                        <input type="hidden" name="currentProfilePicture" value="${user.profilePicture}" id="currentProfilePicture">
                    </div>
                </div>
                
                <div class="form-grid">
                    <div class="form-group">
                        <label for="firstName">First Name</label>
                        <div class="input-with-icon">
                            <i class="fa-regular fa-user"></i>
                            <input type="text" id="firstName" name="firstName" value="${user.firstName}" required>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label for="lastName">Last Name</label>
                        <div class="input-with-icon">
                            <i class="fa-regular fa-user"></i>
                            <input type="text" id="lastName" name="lastName" value="${user.lastName}" required>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label for="email">Email Address</label>
                        <div class="input-with-icon">
                            <i class="fa-regular fa-envelope"></i>
                            <input type="email" id="email" name="email" value="${user.email}" readonly disabled required>
                            <!-- Add a hidden input to ensure email is submitted with the form -->
                            <input type="hidden" name="email" value="${user.email}">
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label for="phone">Phone Number</label>
                        <div class="input-with-icon">
                            <i class="fa-solid fa-phone"></i>
                            <input type="tel" id="phone" name="phone" value="${user.phone}" placeholder="(Optional)">
                        </div>
                    </div>
                    
                    <div class="form-group full-width">
                        <label for="country">Country</label>
                        <div class="select-wrapper">
                            <i class="fa-solid fa-globe"></i>
                            <select id="country" name="country" required>
                                <option value="" disabled ${empty user.country ? 'selected' : ''}>Select your country</option>
                                <option value="nepal" ${user.country == 'nepal' ? 'selected' : ''}>Nepal</option>
                                <option value="america" ${user.country == 'america' ? 'selected' : ''}>America</option>
                                <option value="canada" ${user.country == 'canada' ? 'selected' : ''}>Canada</option>
                                <option value="united_kingdom" ${user.country == 'united_kingdom' ? 'selected' : ''}>United Kingdom</option>
                                <option value="australia" ${user.country == 'australia' ? 'selected' : ''}>Australia</option>
                                <option value="india" ${user.country == 'india' ? 'selected' : ''}>India</option>
                                <option value="japan" ${user.country == 'japan' ? 'selected' : ''}>Japan</option>
                            </select>
                            <i class="fa-solid fa-chevron-down select-arrow"></i>
                        </div>
                    </div>
                </div>
                
                <div class="section-divider">
                    <span>Change Password (Optional)</span>
                </div>
                
                <div class="form-grid">
                    <div class="form-group full-width">
                        <label for="currentPassword">Current Password</label>
                        <div class="input-with-icon">
                            <i class="fa-solid fa-lock"></i>
                            <input type="password" id="currentPassword" name="currentPassword">
                            <span class="toggle-password" id="currentPasswordToggle" tabindex="0">
                                <i class="fa-regular fa-eye"></i>
                            </span>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label for="newPassword">New Password</label>
                        <div class="input-with-icon">
                            <i class="fa-solid fa-lock"></i>
                            <input type="password" id="newPassword" name="newPassword">
                            <span class="toggle-password" id="newPasswordToggle" tabindex="0">
                                <i class="fa-regular fa-eye"></i>
                            </span>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label for="confirmPassword">Confirm New Password</label>
                        <div class="input-with-icon">
                            <i class="fa-solid fa-lock"></i>
                            <input type="password" id="confirmPassword" name="confirmPassword">
                            <span class="toggle-password" id="confirmPasswordToggle" tabindex="0">
                                <i class="fa-regular fa-eye"></i>
                            </span>
                        </div>
                    </div>
                </div>

                <div class="form-actions">
                    <a href="${contextPath}/dashboard" class="btn cancel-btn">Cancel</a>
                    <button type="submit" class="btn save-btn">Save Changes</button>
                </div>
            </form>
        </div>
    </div>
<jsp:include page="../components/footer.jsp"></jsp:include>
    <script>
        // Password visibility toggle functions
        function setupPasswordToggle(toggleId, inputId) {
            document.getElementById(toggleId).addEventListener('click', function() {
                const passwordInput = document.getElementById(inputId);
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
        }
        
        // Setup all password toggles
        setupPasswordToggle('currentPasswordToggle', 'currentPassword');
        setupPasswordToggle('newPasswordToggle', 'newPassword');
        setupPasswordToggle('confirmPasswordToggle', 'confirmPassword');
        
        // Profile picture upload
        const uploadPlaceholder = document.getElementById('uploadPlaceholder');
        const uploadPreview = document.getElementById('uploadPreview');
        const profileInput = document.getElementById('profilePicture');
        const previewImage = document.getElementById('previewImage');
        const removeButton = document.getElementById('removeImage');
        const currentProfilePictureInput = document.getElementById('currentProfilePicture');
        
        // Debug image source
        console.log("Current image source:", previewImage.src);
        
        // Check if we need to show the preview initially
        if (previewImage && previewImage.src && previewImage.src !== window.location.href) {
            uploadPreview.style.display = 'block';
            uploadPlaceholder.style.display = 'none';
        } else {
            uploadPreview.style.display = 'none';
            uploadPlaceholder.style.display = 'flex';
        }
        
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
            e.preventDefault(); // Prevent any form submission
            e.stopPropagation();
            profileInput.value = '';
            previewImage.src = '';
            uploadPreview.style.display = 'none';
            uploadPlaceholder.style.display = 'flex';
            currentProfilePictureInput.value = '';
            console.log("Profile picture removed");
        });
    </script>
</body>
</html>