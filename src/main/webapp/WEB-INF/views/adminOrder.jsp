<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<section class="blog-details spad" style="margin: 0">
	<div class="">
		<!-- search -->
		<div class="blog__details__comment">
			<form action="/admin/account/2077" method="get">
				<div class="row" style="margin: 0">
					<div class="col-lg-10 col-md-4" style="padding: 0">
						<input value="${keyword }" name="keyword" style="color: black" type="text" placeholder="Find an order by Customer name or phone number">
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
				<thead>
				    <tr>
				      <th scope="col">Customer</th>
				      <th scope="col">Address</th>
				      <th scope="col">Phone</th>
				      <th scope="col">Created At</th>
				      <th scope="col">Amount</th>
				      <th scope="col">Status</th>
				    </tr>
				  </thead>
			  <tbody>
			  <c:forEach var="item" items="${allOrderList}">
			    <c:set var="username" value="${item.account.username}" />
			    <tr>
			        <td><a href="/admin/order/detail/${item.id }" >${username}</a></td>
			        <td>${item.address}</td>
			        <td>${item.phone}</td>
			        <td>${item.createdAt}</td>
			        <td><fmt:formatNumber value="${item.amount}" pattern="###,###" /></td>
				    <td>${item.status == 1 ? '<span style="color: green; font-weight: 700">Done</span>' : '<span style="color: red; font-weight: 700">On Delivery</span>'}</td>
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