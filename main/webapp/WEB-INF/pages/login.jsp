<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<c:set var="contextPath" value="${pageContext.request.contextPath}"
	scope="application" />
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login to BlogNexus</title>
<link
	href="https://fonts.googleapis.com/css2?family=DM+Sans:wght@400;500;700&family=Satisfy&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="${contextPath}/css/login.css">
</head>
<body>
	<jsp:include page="../components/fontawesome.jsp" />
	<jsp:include page="../components/message_handler.jsp" />

	<div class="page-container">
		<!-- Left section with illustrations -->
		<div class="visual-section">
			<div class="brand">
				<svg class="logo" viewBox="0 0 60 60" fill="none"
					xmlns="http://www.w3.org/2000/svg">
                    <path
						d="M30 55C43.8071 55 55 43.8071 55 30C55 16.1929 43.8071 5 30 5C16.1929 5 5 16.1929 5 30C5 43.8071 16.1929 55 30 55Z"
						fill="#FFF3C0" fill-opacity="0.7" />
                    <path d="M22 22H38M22 30H33M22 38H38"
						stroke="#8CBD9D" stroke-width="3" stroke-linecap="round"
						stroke-linejoin="round" />
                </svg>
				<span>BlogNexus</span>
			</div>

			<div class="illustration">
				<div class="blob blob-1"></div>
				<div class="blob blob-2"></div>
				<div class="blob blob-3"></div>

				<!-- Decorative elements -->
				<div class="deco-element tech">
					<svg viewBox="0 0 24 24" fill="none"
						xmlns="http://www.w3.org/2000/svg">
                        <path
							d="M20 7H4C2.89543 7 2 7.89543 2 9V19C2 20.1046 2.89543 21 4 21H20C21.1046 21 22 20.1046 22 19V9C22 7.89543 21.1046 7 20 7Z"
							stroke="#83D0CB" stroke-width="2" stroke-linecap="round" />
                        <path
							d="M16 21V5C16 3.89543 15.1046 3 14 3H10C8.89543 3 8 3.89543 8 5V21"
							stroke="#83D0CB" stroke-width="2" stroke-linecap="round" />
                    </svg>
				</div>
				<div class="deco-element travel">
					<svg viewBox="0 0 24 24" fill="none"
						xmlns="http://www.w3.org/2000/svg">
                        <path d="M17.5 19H9.5L8.5 22H16.5L17.5 19Z"
							stroke="#E8C07D" stroke-width="2" stroke-linecap="round"
							stroke-linejoin="round" />
                        <path
							d="M5 11.5C5 11.5 7.5 9 12.5 9C17.5 9 20 11.5 20 11.5"
							stroke="#E8C07D" stroke-width="2" stroke-linecap="round" />
                        <path
							d="M5 14.5C5 14.5 7.5 17 12.5 17C17.5 17 20 14.5 20 14.5"
							stroke="#E8C07D" stroke-width="2" stroke-linecap="round" />
                        <path d="M12.5 17V22" stroke="#E8C07D"
							stroke-width="2" stroke-linecap="round" />
                        <path d="M12.5 9V2" stroke="#E8C07D"
							stroke-width="2" stroke-linecap="round" />
                    </svg>
				</div>
				<div class="deco-element lifestyle">
					<svg viewBox="0 0 24 24" fill="none"
						xmlns="http://www.w3.org/2000/svg">
                        <path
							d="M12 21C16.9706 21 21 16.9706 21 12C21 7.02944 16.9706 3 12 3C7.02944 3 3 7.02944 3 12C3 16.9706 7.02944 21 12 21Z"
							stroke="#B8E0D2" stroke-width="2" />
                        <path d="M12 7V12L15 15" stroke="#B8E0D2"
							stroke-width="2" stroke-linecap="round" />
                    </svg>
				</div>

				<!-- Dots -->
				<div class="dot dot-1"></div>
				<div class="dot dot-2"></div>
				<div class="dot dot-3"></div>
				<div class="dot dot-4"></div>

				<div class="tagline">
					<h2>Welcome back!</h2>
					<p>Sign in to continue your blogging journey</p>
				</div>
			</div>

			<div class="quote">
				<span class="quote-mark">"</span>
				<p>The beautiful thing about writing is that you can use words
					to create worlds.</p>
			</div>
		</div>

		<!-- Right section with form -->
		<div class="form-section">
			<div class="mobile-logo">
				<svg class="logo" viewBox="0 0 60 60" fill="none"
					xmlns="http://www.w3.org/2000/svg">
                    <path
						d="M30 55C43.8071 55 55 43.8071 55 30C55 16.1929 43.8071 5 30 5C16.1929 5 5 16.1929 5 30C5 43.8071 16.1929 55 30 55Z"
						fill="#E8F4EA" fill-opacity="0.7" />
                    <path d="M22 22H38M22 30H33M22 38H38"
						stroke="#8CBD9D" stroke-width="3" stroke-linecap="round"
						stroke-linejoin="round" />
                </svg>
				<span>BlogNexus</span>
			</div>

			<div class="form-header">
				<h1>Welcome Back</h1>
				<p>Sign in to continue to your account</p>
			</div>

			<form action="${contextPath}/login" method="post">
				<div class="form-field">
					<div class="input-with-icon">
						<input type="text" id="email" name="email"
							placeholder="Email Address" required> <i
							class="fa-regular fa-envelope"></i>
					</div>
				</div>

				<div class="form-field password-field">
					<div class="input-with-icon">
						<input type="password" id="password" name="password"
							placeholder="Enter your password" required> <i
							class="fa-solid fa-lock"></i> <span class="toggle-password"
							id="passwordToggle" tabindex="0"> <i
							class="fa-regular fa-eye"></i>
						</span>
					</div>
				</div>



				<button type="submit" class="btn submit-btn">Sign In</button>

				<div class="form-footer">
					<p>
						Don't have an account? <a href="${contextPath}/register">Sign
							up</a>
					</p>
				</div>
			</form>
		</div>
	</div>

	<script>
		// Password visibility toggle
		document.getElementById("passwordToggle").addEventListener("click",
				function() {
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

		// Add keyboard accessibility to toggle
		document.getElementById("passwordToggle").addEventListener("keydown",
				function(e) {
					if (e.key === 'Enter' || e.key === ' ') {
						e.preventDefault();
						this.click();
					}
				});
	</script>
</body>
</html>