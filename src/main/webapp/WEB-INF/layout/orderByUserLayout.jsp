<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MaleFashion | Order by User</title>
<jsp:include page="_head.jsp"></jsp:include>
</head>
<body>
	<!-- Page Preloder -->
    <div id="preloder">
        <div class="loader"></div>
    </div>
    
	<tiles:insertAttribute name="header"></tiles:insertAttribute>
	<section style="background-color: #eee;">
	<div class="container py-5">

		<div class="row">
			<div class="col-lg-4">
				<div class="card mb-4">
					<div class="card-body text-center">
						<img src="/sidebar/images/logo2.jpg" alt="avatar"
							class="rounded-circle img-fluid" style="width: 150px;">
						<h5 class="my-3">${account.username }</h5>
						<p class="text-muted mb-1">Full Stack Developer</p>
						<p class="text-muted mb-4">Living in 2077</p>
						<div class="d-flex justify-content-center mb-2">
							<button style="background-color: #FF6F52; color: white; font-weight: bold;" type="button" class="btn">View Profile</button>
						</div>
					</div>
				</div>

				<div class="card mb-4">
					<div class="card-body">
						<div class="row">
							<div class="col-sm-3">
								<p class="mb-0">Full Name</p>
							</div>
							<div class="col-sm-9">
								<p class="text-muted mb-0">${account.username }</p>
							</div>
						</div>
						<hr>
						<div class="row">
							<div class="col-sm-3">
								<p class="mb-0">Email</p>
							</div>
							<div class="col-sm-9">
								<p class="text-muted mb-0">${account.email }</p>
							</div>
						</div>
						<hr>
						<div class="row">
							<div class="col-sm-3">
								<p class="mb-0">Phone</p>
							</div>
							<div class="col-sm-9">
								<p class="text-muted mb-0">0999 999 999</p>
							</div>
						</div>

					</div>
				</div>
			</div>
			<tiles:insertAttribute name="body"></tiles:insertAttribute>
			
		</div>
	</div>
</section>
	
	<tiles:insertAttribute name="footer"></tiles:insertAttribute>
</body>
</html>