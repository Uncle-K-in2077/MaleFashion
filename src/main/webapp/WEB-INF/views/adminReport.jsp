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
						<input value="${keyword }" name="keyword" style="color: black"
							type="text"
							placeholder="Find an order by Customer name or phone number">
					</div>
					<div class="col-lg-2 col-md-4" style="padding: 0">
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
						<th scope="col">Product ID</th>
						<th scope="col">Image</th>
						<th scope="col">Product name</th>
						<th scope="col">Price</th>
						<th scope="col">Count</th>
						<th scope="col">Amount</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${report}">
						<tr>
							<td>${item.group }</td>
							<td><img width="200px" src="${ item.image}" class="img-thumbnail"></td>
							<td>${item.name}</td>
							<td><fmt:formatNumber value="${item.price}"
									pattern="###,###" /></td>
							<td>${item.count }</td>
							<td><fmt:formatNumber value="${item.amount}"
									pattern="###,###" /></td>
						</tr>
					</c:forEach>


				</tbody>
			</table>

		</div>
	</div>
</section>