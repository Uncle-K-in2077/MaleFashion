<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MaleFashion | Login</title>
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
</style>
<body>
<!-- Page Preloder -->
    <div id="preloder">
        <div class="loader"></div>
    </div>
	<section class="vh-100" style="background-color: #B07F5E;">
  <div class="container h-100" >
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col-lg-12 col-xl-11">
        <div class="card text-black" style="border-radius: 25px; ">
          <div class="card-body p-md-5" style="background-image: url('./img/hero/hero-2.jpg'); background-position: center center; background-size: cover;">
            <div class="row justify-content-center">
              <div style="border: 2px solid #F2DFDB; border-style: double;" class="col-md-10 col-lg-6 col-xl-5 order-2 order-lg-1">

                <p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4">Login</p>

				<b Class="error" style="margin-left: 60px">${LoginMessage}${param.error}</b>
                <form action="/login" method="post" class="mx-1 mx-md-4">

                  <div class="d-flex flex-row align-items-center mb-4">
                    <i class="fas fa-envelope fa-lg me-3 fa-fw"></i>
                    <div class="form-outline flex-fill mb-0">
                      <input name="email" required="required" placeholder="Email" style="background-color: #D3D3D3; color: black; " type="email" id="form3Example3c" class="form-control" />
                    </div>
                  </div>

                  <div class="d-flex flex-row align-items-center mb-4">
                    <i class="fas fa-lock fa-lg me-3 fa-fw"></i>
                    <div class="form-outline flex-fill mb-0">
                      <input name="password" required="required" placeholder="Password" style="background-color: #D3D3D3; color: black; " type="password" id="form3Example4c" class="form-control" />
                    </div>
                  </div>

                  <div class="form-check d-flex justify-content-center mb-5">
                    <input class="form-check-input me-2" type="checkbox" value="" id="form2Example3c" />
                    <label class="form-check-label" for="form2Example3">
                      Remember me  | <a href="./signup">Register</a> | <a href="./login/forgotPassword">Forgot password?</a>
                    </label>
                  </div>
                  

                  <div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4">
                    <button type="submit" class="btn btn-primary btn-lg">Register</button>
                  </div>

                </form>

              </div>
              <div class="col-md-10 col-lg-6 d-flex align-items-center order-1 order-lg-2">
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