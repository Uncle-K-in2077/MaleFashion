<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="javax.validation.constraints.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MaleFashion | Sing Up</title>
<jsp:include page="_head.jsp"></jsp:include>
<!-- Font Awesome -->
<link
  href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
  rel="stylesheet"
/>
<!-- Google Fonts -->
<link
  href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"
  rel="stylesheet"
/>
<!-- MDB -->
<link
  href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.4.0/mdb.min.css"
  rel="stylesheet"
/>
</head>
<style type="text/css">
	.error {
  		color: red;
  		font-size: 16px;
  		font-stretch:inherit; 
  		margin-left: 20px;
	}
	
	.overlay {
  top: 0;
  left: 0;
  background-color: rgba(255, 255, 255, 0.8); /* Màu nền trắng với độ mờ (opacity) là 0.8 */
  backdrop-filter: blur(8px); /* Hiệu ứng làm mờ */
}
</style>
<body>
<!-- Page Preloder -->
    <div id="preloder">
        <div class="loader"></div>
    </div>
	<section class="vh-100" style="background-color: #B07F5E;">
  <div class="container h-100">
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col-lg-12 col-xl-11">
        <div class="card text-black" style="border-radius: 25px;">
          <div class="card-body p-md-5" style="background-image: url('../img/hero/hero-1.jpg'); background-position: center center; background-size: cover;">
            <div class="row justify-content-center" >
            
              <div style="border: 2px solid #F2DFDB; border-style: double; " class="overlay col-md-10 col-lg-9 col-xl-5 order-2 order-lg-1">

                <p class="text-center h1 fw-bold mb-3 mx-1 mx-md-4 mt-4">OTP Verification </p>
				
                <form class="mx-1 mx-md-4" action="/signup/confirm-otp" method="post">

                  <div class="d-flex flex-row align-items-center mb-4">
                    <i class="fas fa-envelope fa-lg me-3 fa-fw"></i>
                    <div class="form-outline flex-fill mb-0">
                      <p style=" color: black;" id="form3Example3c" class="form-control">We have sent a verification OTP code to your Email: <span style="color: #B07F5E; font-weight: bold;">${account.email }</span></p>
                    </div>
                  </div>
                  
                  
                  <p style="color: red; font-weight: 600; margin-left: 40px">${errorMessage }</p>
                  
                  <div class="d-flex flex-row align-items-center mb-4">
                    <i class="fas fa-shield fa-lg me-3 fa-fw"></i>
                    <div class="form-outline flex-fill mb-0">
                      <input placeholder="OTP" required="required" name="otp" style="background-color: #D3D3D3; color: black; " type="text" id="form3Example3c" class="form-control" />
                    </div>
                  </div>

                  <div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4">
                    <button class="btn btn-primary btn-lg" type="submit">Verify</button>
                  </div>
                  
                  <div class="form-check d-flex justify-content-center mb-5">
                    <label class="form-check-label" for="form2Example3">
                      Didn't receive the code? <a href="#!">ReSend OTP</a>
                    </label>
                  </div>

                </form>
              </div>
             
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>
</body>
</html>
<jsp:include page="_footer.jsp"></jsp:include>
<!-- 
	<form class="otp-Form" action="/signup/confirm-otp" method="post">

		<span class="mainHeading">Enter OTP</span>
		<p class="otpSubheading">We have sent a verification code to your
			mobile number</p>
		<input type="text" value="${guestEmail }" disabled="disabled"
			style="color: Orange">
		<div class="inputContainer">
			<input required="required" name="otp" type="text" class="otp-input"
				id="otp-input1">
		</div>
		<button class="verifyButton" type="submit">Verify</button>
		<p class="resendNote">Didn't receive the code?</p>

		<h1>${errorMessage }</h1>

	</form>
 -->