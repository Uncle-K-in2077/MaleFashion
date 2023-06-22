<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="col-lg-12">
	<div class="row">
		<div class="col-md-12">
			<div class="card mb-12 mb-md-0">
				<div class="card-body shopping__cart__table">
					<div class="col-lg-12 col-md-12">
						<div class="checkout__order" style="min-width: 100%"> 
							<h5 class="order__title">Your order number: ${order.id }</h5>
							<div class="checkout__order__products">
								Product  <span>Total</span>
							</div>
							<ul class="checkout__total__products">
								<c:forEach var="item" items="${order.orderDetails}">
									<c:set var="productName" value="${item.product.name}" />
									<li>${productName } - ${item.quantity}
									<span><fmt:formatNumber value="${item.price * item.quantity}" pattern="###,###" /></span></li>
								</c:forEach>
							</ul>
							<ul class="checkout__total__all">
								<li>Subtotal <span>0.0</span></li>
								<li>Total <span><fmt:formatNumber value="${order.amount}" pattern="###,###" /></span></li>
							</ul>
							<ul class="checkout__total__products">
								<li>Phone <span>${order.phone}</span></li>
								<li>Address <span>${order.address}</span></li>
								<li>Note <span>${order.note}</span></li>
							</ul>
							<a href="/checkOut/${account.id }">
								<button type="button" class="site-btn">Your order is on process - Back to order list</button>
							</a>
						</div>
					</div>	
				</div>
			</div>
		</div>
	</div>
</div>