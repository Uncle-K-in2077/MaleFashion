<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- Breadcrumb Section Begin -->
<section class="breadcrumb-option">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="breadcrumb__text">
					<h4>Shopping Cart</h4>
					<div class="breadcrumb__links">
						<a href="./index.html">Home</a> <a href="./shop.html">Shop</a> <span>Your
							Shopping Cart</span>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- Breadcrumb Section End -->
<!-- Shopping Cart Section Begin -->
<section class="shopping-cart spad">
	<div class="container">
		<div class="row">
			<div class="col-lg-8">
				<div class="shopping__cart__table">
					<table>
						<thead>
							<tr>
								<th>Product</th>
								<th>Quantity</th>
								<th>Total</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<!-- List Product in your Cart Begin-->
							<c:forEach var="item" items="${ cartItems}">
								<form action="/cart/update/${item.id }" method="get">
								<tr>
									<td class="product__cart__item">
										<div class="product__cart__item__pic">
											<img style="width: 90px; height: 90px" src="${item.image }"
												alt="">
										</div>
										<div class="product__cart__item__text">
											<h6>${item.name }</h6>
											<h5>
												<fmt:formatNumber value="${item.price}" pattern="###,###" />
											</h5>
										</div>
									</td>
									<td class="quantity__item">
										<div class="quantity">
											<div class="pro-qty-2">
												<input onchange="this.form.submit()" name="quantity"
													type="text" value="${item.quantity }">
											</div>
										</div>
									</td>
									<td class="cart__price"><fmt:formatNumber
											value="${item.price * item.quantity }" pattern="###,###" /></td>
									<td class="cart__close">
										<a href="/cart/remove/${item.id }">
											<i class="fa fa-close"></i>
										</a>
									</td>
								</tr>
								</form>
							</c:forEach>
							<!-- List Product in your Cart End-->

						</tbody>
					</table>
				</div>
				<div class="row">
					<div class="col-lg-6 col-md-6 col-sm-6">
						<div class="continue__btn">
							<a href="/shop">Continue Shopping</a>
						</div>
					</div>
					<div class="col-lg-6 col-md-6 col-sm-6">
						<div class="continue__btn update__btn">
							<a href="/cart"><i class="fa fa-spinner"></i> Update cart</a>
						</div>
					</div>
				</div>
			</div>
			<div class="col-lg-4">
				<div class="cart__discount">
					<h6>Discount codes</h6>
					<form action="#">
						<input type="text" placeholder="Coupon code">
						<button type="submit">Apply</button>
					</form>
				</div>
				<div class="cart__total">
					<h6>Cart total</h6>
					<ul>
						<li>Subtotal <span>0.0</span></li>
						<li>Total <span><fmt:formatNumber
									value="${totalAmount }" pattern="###,###" /></span></li>
					</ul>
					<a href="/checkOut" class="primary-btn">Proceed to checkout</a>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- Shopping Cart Section End -->