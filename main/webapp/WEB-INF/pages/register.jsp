<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Join Our Blog</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/register.css">
</head>
<body>
    <div class="container">
        <div class="form-header">
            <h1>Join Our Blog</h1>
            <p>Create your account and start sharing stories</p>
        </div>
        
        <form action="registerProcess.jsp" method="post" enctype="multipart/form-data">
            <!-- First name -->
            <div class="form-group floating-input">
                <input type="text" id="firstName" name="firstName" placeholder=" " required>
                <label for="firstName">First Name</label>
            </div>
            
            <!-- Last name -->
            <div class="form-group floating-input">
                <input type="text" id="lastName" name="lastName" placeholder=" " required>
                <label for="lastName">Last Name</label>
            </div>
            
            <!-- Email -->
            <div class="form-group floating-input">
                <input type="email" id="email" name="email" placeholder=" " required>
                <label for="email">Email Address</label>
            </div>
            
            <!-- Phone -->
            <div class="form-group floating-input">
                <input type="tel" id="phone" name="phone" placeholder=" ">
                <label for="phone">Phone Number</label>
            </div>
            
            <!-- Country - Now full width -->
            <div class="form-group floating-input full-width">
                <select id="country" name="country" required>
                    <option value="" disabled selected></option>
                    <option value="US">United States</option>
                    <option value="CA">Canada</option>
                    <option value="UK">United Kingdom</option>
                    <option value="AU">Australia</option>
                    <option value="IN">India</option>
                    <option value="JP">Japan</option>
                    <option value="other">Other</option>
                </select>
                <label for="country">Country</label>
            </div>
            
            <!-- Password - Now full width -->
            <div class="form-group full-width">
                <div class="password-group">
                    <input type="password" id="password" name="password" placeholder="Create a password" required>
                    <span class="password-toggle" id="passwordToggle"><i class="far fa-eye"></i></span>
                </div>
            </div>
            
            <!-- Confirm Password - Now full width -->
            <div class="form-group full-width">
                <div class="password-group">
                    <input type="password" id="confirmPassword" name="confirmPassword" placeholder="Confirm your password" required>
                    <span class="password-toggle" id="confirmPasswordToggle"><i class="far fa-eye"></i></span>
                </div>
            </div>
            
            <!-- Profile Picture - Last in form -->
            <div class="form-group file-upload full-width">
                <label for="profilePicture">Profile Picture</label>
                <div class="upload-container">
                    <div class="upload-box">
                        <i class="fas fa-cloud-upload-alt"></i>
                        <p>Drag and drop or click to browse</p>
                    </div>
                    <input type="file" id="profilePicture" name="profilePicture" accept="image/*">
                </div>
                <div class="upload-preview" id="previewContainer">
                    <img id="imagePreview" src="#" alt="Preview" style="display: none;">
                </div>
            </div>
            
            <!-- Submit Button -->
            <div class="form-group full-width">
                <button type="submit" class="submit-btn">Create Account</button>
            </div>
            
            <!-- Form Footer -->
            <div class="form-footer">
                <p>Already have an account? <a href="login.jsp">Sign in</a></p>
            </div>
        </form>
    </div>

    <script>
        // Profile picture preview
        document.getElementById("profilePicture").addEventListener("change", function() {
            const file = this.files[0];
            if (file) {
                const reader = new FileReader();
                reader.onload = function(e) {
                    const imagePreview = document.getElementById("imagePreview");
                    const previewContainer = document.getElementById("previewContainer");
                    imagePreview.src = e.target.result;
                    imagePreview.style.display = "block";
                    previewContainer.classList.add("has-image");
                }
                reader.readAsDataURL(file);
            }
        });
        
        // Password toggle visibility
        document.getElementById("passwordToggle").addEventListener("click", function() {
            const passwordInput = document.getElementById("password");
            const icon = this.querySelector("i");
            
            if (passwordInput.type === "password") {
                passwordInput.type = "text";
                icon.classList.remove("fa-eye");
                icon.classList.add("fa-eye-slash");
            } else {
                passwordInput.type = "password";
                icon.classList.remove("fa-eye-slash");
                icon.classList.add("fa-eye");
            }
        });
        
        document.getElementById("confirmPasswordToggle").addEventListener("click", function() {
            const passwordInput = document.getElementById("confirmPassword");
            const icon = this.querySelector("i");
            
            if (passwordInput.type === "password") {
                passwordInput.type = "text";
                icon.classList.remove("fa-eye");
                icon.classList.add("fa-eye-slash");
            } else {
                passwordInput.type = "password";
                icon.classList.remove("fa-eye-slash");
                icon.classList.add("fa-eye");
            }
        });
    </script>
</body>
</html>