<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login to Our Blog</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body>
    <div class="container">
        <div class="form-header">s
            <h1>Welcome Back</h1>
            <p>Sign in to continue to your account</p>
        </div>
        
        <form action="loginProcess.jsp" method="post">
            <!-- Email/Username -->
            <div class="form-group floating-input">
                <input type="text" id="email" name="email" placeholder=" " required>
                <label for="email">Email Address</label>
            </div>
            
            <!-- Password -->
            <div class="form-group">
                <div class="password-group">
                    <input type="password" id="password" name="password" placeholder="Enter your password" required>
                    <span class="password-toggle" id="passwordToggle"><i class="far fa-eye"></i></span>
                </div>
            </div>
   
            
            <!-- Login Button -->
            <button type="submit" class="submit-btn">Sign In</button>
            
    
            
     
        </form>
    </div>

    <script>
        // Add current timestamp
        document.addEventListener('DOMContentLoaded', function() {
            const timestampElement = document.querySelector('.timestamp');
            if (timestampElement) {
                timestampElement.textContent = 'Current Time: 2025-04-03 17:49:50';
            }
            
            const userElement = document.querySelector('.user-info');
            if (userElement) {
                userElement.textContent = 'User: aashu79';
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
    </script>
</body>
</html>