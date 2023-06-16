<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<section class="blog-details spad" style="margin: 0">
	<div class="">
		<!-- Create new category Begin -->
		<div class="blog__details__comment">
			<form action="#">
				<div class="row" style="margin: 0">
					<div class="col-lg-10 col-md-4" style="padding: 0">
						<input style="color: black" type="text" placeholder="New category name">
					</div>
					<div class="col-lg-2 col-md-4" style=" padding: 0">
						<button style="min-width: 100%;" type="submit" class="site-btn">Create</button>
					</div>
				</div>
			</form>
		</div>
		<!-- Create new category End -->
		
		<!-- List Category -->
		<div class="row" style="margin: 0">
			<table class="table col-lg-8 col-md-4">
			  <tbody>
			  	<c:forEach var="item" items="${categoryList }">
				    <tr>
				      <th scope="row">${item.id }</th>
				      <td>${item.name }</td>
				      <td>${item.status == "1" ? '<span style="color: green; font-weight: 700">Online</span>' : '<span style="color: green">OffLine</span>' }</td>
				    </tr>
			  	</c:forEach>
			  	</tbody>
			</table>
			<div class="col-lg-4 col-md-4" style="background-color: pink">
				<!-- Detail of every Category will display here! -->
				<h5 style="color: #333333;font-weight: 600; margin-bottom: 35px;text-align: center;">CATEGORY DETAIL</h5>
			</div>
		</div>
	</div>
</section>