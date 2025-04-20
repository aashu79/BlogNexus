<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Terms and Conditions</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/terms_and_condition.css">
</head>
<body>
  <jsp:include page="../components/navbar.jsp" />
  
  <div class="terms-container">
    <h1>Terms and Conditions</h1>
    <p class="effective-date">Effective: January 1, 2023</p>
    
    <section class="terms-section">
      <h2>1. Introduction</h2>
      <p>Welcome to our platform. These terms and conditions outline the rules and regulations for the use of our services.</p>
    </section>

    <section class="terms-section">
      <h2>2. Acceptance of Terms</h2>
      <p>By accessing this platform, we assume you accept these terms and conditions in full. Do not continue to use our services if you do not agree.</p>
    </section>

    <section class="terms-section">
      <h2>3. User Responsibilities</h2>
      <p>Users must not misuse our services. You agree to use our platform only for lawful purposes and in ways that do not infringe the rights of others.</p>
    </section>

    <section class="terms-section">
      <h2>4. Intellectual Property</h2>
      <p>All content on this platform, including text, graphics, and logos, is our property and protected by copyright and intellectual property laws.</p>
    </section>

    <section class="terms-section">
      <h2>5. Limitation of Liability</h2>
      <p>We shall not be liable for any indirect, incidental, special, or consequential damages resulting from the use of our services.</p>
    </section>

    <section class="terms-section">
      <h2>6. Changes to Terms</h2>
      <p>We reserve the right to modify these terms at any time. Continued use after changes constitutes acceptance of the modified terms.</p>
    </section>
  </div>

  <jsp:include page="../components/footer.jsp" />

  <script>
    // Add smooth scrolling to anchor links
    document.querySelectorAll('a[href^="#"]').forEach(anchor => {
      anchor.addEventListener('click', function (e) {
        e.preventDefault();
        document.querySelector(this.getAttribute('href')).scrollIntoView({
          behavior: 'smooth'
        });
      });
    });
  </script>
</body>
</html>