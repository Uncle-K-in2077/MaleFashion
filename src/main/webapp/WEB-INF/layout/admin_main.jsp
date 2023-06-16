<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="UTF-8">
<head>
<title>Male Fashion | Admin</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link
	href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700,800,900"
	rel="stylesheet">

<link rel="stylesheet" href="/sidebar/css/style.css">


<jsp:include page="_head.jsp"></jsp:include>
</head>
<jsp:include page="_style.jsp"></jsp:include>
<body>

	<div class="wrapper d-flex align-items-stretch">
		<nav id="sidebar" >
			<div class="p-4 pt-5">
				<a href="#" class="img logo  mb-5"
					style="background-image: url(/sidebar/images/logo2.jpg);"></a>
				<ul class="list-unstyled components mb-5">
					<li class="${currentLine == 'product' ? 'active' : ''}"><a href="#homeSubmenu"
						data-toggle="collapse" aria-expanded="false"
						class="dropdown-toggle">Product</a>
						<ul class="collapse list-unstyled" id="homeSubmenu">
							<li><a href="/admin/product">All product</a></li>
							<li><a href="#">Disable product</a></li>
						</ul></li>
					<li class="${currentLine == 'category' ? 'active' : ''}"><a href="/admin/category">Category</a></li>
					<li><a href="#">Account</a></li>
					<li><a href="#">Order</a></li>
					<li><a href="#">About</a></li>
					<li><a href="#">Mail center</a></li>
					<!-- 
					<li><a href="#pageSubmenu" data-toggle="collapse"
						aria-expanded="false" class="dropdown-toggle">Pages</a>
						<ul class="collapse list-unstyled" id="pageSubmenu">
							<li><a href="#">Page 1</a></li>
							<li><a href="#">Page 2</a></li>
							<li><a href="#">Page 3</a></li>
						</ul></li>
					 -->
				</ul>

				<div class="">
					<p style="color: white;">
						<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
						Developing by Uncle K - &copy;
						<script>
							document.write(new Date().getFullYear());
						</script>
						<i class="icon-heart" aria-hidden="true"></i> <a
							href="https://colorlib.com" target="_blank"></a>
						<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
					</p>
				</div>

			</div>
		</nav>

		<!-- Page Content  -->
		<div id="content" class="p-4 p-md-5" style="">

			<nav class="navbar navbar-expand-lg navbar-light bg-light">
				<div class="container-fluid">

					<button type="button" id="sidebarCollapse" class="btn btn-primary">
						<i class="fa fa-bars"></i> <span class="sr-only">Toggle
							Menu</span>
					</button>
					<button class="btn d-inline-block d-lg-none ml-auto"
						type="button" data-toggle="collapse"
						data-target="#navbarSupportedContent"
						aria-controls="navbarSupportedContent" aria-expanded="false"
						aria-label="Toggle navigation">
						<i class="fa fa-bars"></i>
					</button>

					<div class="collapse navbar-collapse" id="navbarSupportedContent">
						<ul class="nav navbar-nav ml-auto">
							<li class="nav-item active"><a class="nav-link" href="/home">Home</a>
							</li>
							<li class="nav-item"><a class="nav-link" href="/shop">Shop</a>
							</li>
						</ul>
					</div>
				</div>
			</nav>

			<!-- Body here -->
			

			<tiles:insertAttribute name="body"></tiles:insertAttribute>
		</div>
	</div>

	<script src="/sidebar/js/jquery.min.js"></script>
	<script src="/sidebar/js/popper.js"></script>
	<script src="/sidebar/js/bootstrap.min.js"></script>
	<script src="/sidebar/js/main.js"></script>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</body>
</html>