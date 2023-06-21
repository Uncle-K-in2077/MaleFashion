<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div id="preloder">
	<div class="loader"></div>
</div>
<!-- Breadcrumb Section Begin -->
<section class="breadcrumb-option">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="breadcrumb__text">
					<h4>Check Out</h4>
					<div class="breadcrumb__links">
						<a href="./index.html">Home</a> <a href="./shop.html">Shop</a> <span>Check
							Out</span>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- Breadcrumb Section End -->

<!-- Checkout Section Begin -->
<section class="checkout spad">
	<div class="container">
		<div class="checkout__form">
			<form:form action="/checkOut" method="post" modelAttribute="order">
				<div class="row">
					<div class="col-lg-8 col-md-6">
						<c:if test="${not empty session.CheckOutMessage}">
						    <h6 class="coupon__code">
						        <span class="icon_tag_alt"></span> ${session.CheckOutMessage}
						    </h6>
						</c:if>


						<h6 class="checkout__title">Billing Details</h6>
						<div class="row">
							<div class="col-lg-12">
								<div class="checkout__input">
									<p>
										Your Full Name<span>*</span>
									</p>
									<input  style="color: black; font-weight: bold;" type="text"
										value="${account.username }" disabled="disabled" >
								</div>
							</div>
						</div>

						<div class="checkout__input">
							<p>
								Address<span>*</span>
							</p>
							<form:errors path="address" cssClass="error"/>
							<form:input path="address" name="address" style="color: black; font-weight: bold;" type="text"
								placeholder="Your Local Address" class="checkout__input__add" />
						</div>

						<div class="row">
							<div class="col-lg-6">
								<div class="checkout__input">
									<p>
										Phone<span>*</span>
									</p>
									<form:errors path="phone" cssClass="error"/>
									<form:input path="phone" value="${phone}" placeholder="Your Phone Number" style="color: black;"
										type="text" />
								</div>
							</div>
							<div class="col-lg-6">
								<div class="checkout__input">
									<p>
										Email<span>*</span>
									</p>
									<input style="color: black; font-weight: bold;" type="text"
										value="${account.email }" disabled="disabled">
								</div>
							</div>
						</div>

						<div class="checkout__input">
							<p>
								Order notes<span>*</span>
							</p>
							<input type="text" name="order_note"
								placeholder="Notes about your order, e.g. special notes for delivery.">
						</div>
					</div>
					<div class="col-lg-4 col-md-6">
						<div class="checkout__order">
							<h4 class="order__title">Your order</h4>
							<div class="checkout__order__products">
								Product <span>Total</span>
							</div>
							<ul class="checkout__total__products">
								<c:forEach var="item" items="${cartItems }">
									<li>${item.quantity }.${item.name } <span><fmt:formatNumber
												value="${item.price * item.quantity }" pattern="###,###" /></span></li>
								</c:forEach>
							</ul>
							<ul class="checkout__total__all">
								<li>Subtotal <span>0.0</span></li>
								<li>Total <span><fmt:formatNumber
											value="${totalAmount }" pattern="###,###" /></span></li>
							</ul>

							<p>Lorem ipsum dolor sit amet, consectetur adip elit, sed do
								eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>

							<button type="submit" class="site-btn">PLACE ORDER</button>
						</div>
					</div>
				</div>
			</form:form>
		</div>
	</div>
</section>
<!-- Checkout Section End -->