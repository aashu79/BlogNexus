<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Contact Us</title>
  <link rel="stylesheet" href="css/contact_us.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
 <jsp:include page="../components/navbar.jsp" />
  <div class="container">
    <h2>Contact Us</h2>
    <p>Whether you have a question, need assistance, or want to share your thoughts, we're here to help. Feel free to reach out to us through any of the following methods.</p>

    <div class="content-wrapper">
      <!-- Left side: Contact Info -->
      <div class="contact-info">
        <div class="contact-item">
          <i class="fa-solid fa-phone"></i>
          <span class="text">+1-234-567-890</span>
        </div>
        <div class="contact-item">
          <i class="fa-solid fa-envelope"></i>
          <span class="text">contact@example.com</span>
        </div>
      </div>

      <!-- Right side: Contact Form -->
      <form class="contact-form">
        <input type="text" placeholder="First" required>
        <input type="text" placeholder="Last" required>
        <input type="email" placeholder="Email" required>
        <textarea placeholder="Message" rows="4" required></textarea>
        <button type="submit">Submit</button>
      </form>
    </div>
  </div>
   <jsp:include page="../components/footer.jsp" />
</body>
</html>



    