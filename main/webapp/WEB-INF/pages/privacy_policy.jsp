<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Privacy Policy - BlogNexus</title>
<link rel="stylesheet" href="${contextPath}/css/privacy_policy.css">
</head>
<body>
  <jsp:include page="../components/navbar.jsp" />
  
  <div class="policy-container">
    <h1>Privacy Policy</h1>
    <p class="effective-date">Last Updated: January 1, 2023</p>
    
    <section class="policy-section">
      <h2>1. Introduction</h2>
      <p>Welcome to BlogNexus. We are committed to protecting your personal information and your right to privacy. This Privacy Policy explains how we collect, use, and safeguard your information when you visit our platform.</p>
    </section>

    <section class="policy-section">
      <h2>2. Information We Collect</h2>
      <p>We may collect the following types of information:</p>
      <ul class="policy-list">
        <li><strong>Account Data:</strong> Name, email address, and profile information when you register</li>
        <li><strong>Content Data:</strong> Posts, comments, and other content you submit</li>
        <li><strong>Usage Data:</strong> IP address, browser type, and pages visited</li>
        <li><strong>Cookies:</strong> Session cookies to enhance user experience</li>
      </ul>
    </section>

    <section class="policy-section">
      <h2>3. How We Use Your Information</h2>
      <p>We use the information we collect to:</p>
      <ul class="policy-list">
        <li>Provide and maintain our services</li>
        <li>Improve user experience</li>
        <li>Communicate with users</li>
        <li>Monitor platform security</li>
        <li>Comply with legal obligations</li>
      </ul>
    </section>

    <section class="policy-section">
      <h2>4. Data Sharing</h2>
      <p>We do not sell your personal data. We may share information with:</p>
      <ul class="policy-list">
        <li>Service providers (e.g., hosting, analytics)</li>
        <li>Legal authorities when required</li>
        <li>Other users as part of public profile information</li>
      </ul>
    </section>

    <section class="policy-section">
      <h2>5. Data Security</h2>
      <p>We implement security measures including encryption, access controls, and regular security audits. However, no electronic transmission is completely secure.</p>
    </section>

    <section class="policy-section">
      <h2>6. Your Rights</h2>
      <p>You have the right to:</p>
      <ul class="policy-list">
        <li>Access and update your information</li>
        <li>Request data deletion</li>
        <li>Opt-out of communications</li>
        <li>Withdraw consent for data processing</li>
      </ul>
    </section>



    <section class="policy-section">
      <h2>8. Changes to This Policy</h2>
      <p>We may update this policy periodically. We will notify users of significant changes through platform notifications or email.</p>
    </section>

    <section class="policy-section">
      <h2>9. Contact Us</h2>
      <p>For privacy-related inquiries, please contact us at <a href="${contextPath}/contact">our contact page</a> or email privacy@blognexus.com.</p>
    </section>
  </div>

  <jsp:include page="../components/footer.jsp" />


</body>
</html>