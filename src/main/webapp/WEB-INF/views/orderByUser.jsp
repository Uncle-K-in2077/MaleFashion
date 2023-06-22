<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
			<div class="col-lg-8">
				<div class="row">
					<div class="col-md-12">
						<div class="card mb-4 mb-md-0">
							<div class="card-body shopping__cart__table">
								<h3 style="text-align: center; color: #E53637; font-weight: bolder;">Your Orders</h3>
								<hr>
								<table>
									<thead>
										<tr>
											<th>Order Information</th>
											<th>Address</th>
											<th>Amount</th>
											<th></th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="item" items="${orders }">
											<tr>
												<td class="product__cart__item">
													<div class="product__cart__item__pic">
														<a href="/checkOut/detail/${item.id }"> <img
															style="width: 90px; height: 90px"
															src="../sidebar/images/logo2.jpg" alt="">
														</a>
													</div>
													<div class="product__cart__item__text">
														<h6>${item.createdAt }</h6>
														<h6>
															${item.note }
														</h6>
													</div>
												</td>
												<td class="product__cart__item">
													<div class="">
														<h6>${item.address }</h6>
														<br>
														<h6>Phone: ${item.phone }</h6>
													</div>
												</td>
												<td class="cart__price"><fmt:formatNumber
														value="${item.amount}" pattern="###,###" /></td>
												
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		