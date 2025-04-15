<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Contact Us</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/contact_us.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
  <div class="contact-container">
    <h1>Contact Us</h1>
    <p class="subtitle">
      Whether you have a question, need assistance, or want to share your thoughts, we're here to help.
      Feel free to reach out to us through any of the following methods.
    </p>

    <div class="contact-content">
      <div class="contact-left">
        <div class="contact-item">
          <i class="fa-solid fa-phone"></i>
          <span>+1-234-567-890</span>
        </div>
        <div class="contact-item">
        <i class="fa-solid fa-envelope"></i>
          <span>contact@example.com</span>
        </div>
      </div>

      <div class="contact-right">
        <form>
          <input type="text" placeholder="First" />
          <input type="text" placeholder="Last" />
          <input type="email" placeholder="Email" />
          <textarea placeholder="Message"></textarea>
          <button type="submit">Submit</button>
        </form>
      </div>
    </div>
  </div>
</body>
</html>



