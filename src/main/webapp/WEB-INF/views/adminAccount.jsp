<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<section class="blog-details spad" style="margin: 0">
	<div class="">
		<!-- search -->
		<div class="blog__details__comment">
			<form action="/admin/account" method="get">
				<div class="row" style="margin: 0">
					<div class="col-lg-10 col-md-4" style="padding: 0">
						<input value="${keyword }" name="keyword" style="color: black" type="text" placeholder="Finding someone?">
					</div>
					<div class="col-lg-2 col-md-4" style=" padding: 0">
						<button style="min-width: 100%;" type="submit" class="site-btn">Search</button>
					</div>
				</div>
			</form>
		</div>
		<!-- search end -->
		
		<!-- List Account -->
		<div class="row" style="margin: 0">
			<table class="table col-lg-12 col-md-4">
			  <tbody>
			  	<c:forEach var="item" items="${accountList }">
				    <tr>
				      <th scope="row">${item.username }</th>
				      <td>${item.email }</td>
				      <td>${item.password }</td>
				      <td>${item.activated == "1" ? '<span style="color: green; font-weight: 700">Online</span>' : '<span style="color: red; font-weight: 700"; >OffLine</span>' }</td>
				      <td>${item.admin == "1" ? '<span style="color: green; font-weight: 700">Admin</span>' : '<span style="color: orange; font-weight: 700">Guest</span>' }</td>
				    </tr>
			  	</c:forEach>
			  	</tbody>
			</table>
			<!-- 
			<div class="col-lg-4 col-md-4" style="background-color: pink">
				
				<h5 style="color: #333333;font-weight: 600; margin-bottom: 35px;text-align: center;">CATEGORY DETAIL</h5>
			</div>
			 -->
		</div>
	</div>
</section>