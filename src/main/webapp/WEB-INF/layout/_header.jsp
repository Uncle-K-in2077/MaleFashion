<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>


<div class="offcanvas-menu-overlay"></div>
<div class="offcanvas-menu-wrapper">
	<div class="offcanvas__option">
		<div class="offcanvas__links">
			<a href="#">Sign in</a> <a href="#">FAQs</a>
		</div>
		<div class="offcanvas__top__hover">
			<span>Usd <i class="arrow_carrot-down"></i></span>
			<ul>
				<li>USD</li>
				<li>EUR</li>
				<li>USD</li>
			</ul>
		</div>
	</div>
	<div class="offcanvas__nav__option">
		<a href="#" class="search-switch"><img src="/img/icon/search.png"
			alt=""></a> <a href="#"><img src="/img/icon/heart.png" alt=""></a>
		<a href="#"><img src="/img/icon/cart.png" alt=""> <span>0</span></a>
		<div class="price">$0.00</div>
	</div>
	<div id="mobile-menu-wrap"></div>
	<div class="offcanvas__text">
		<p>Free shipping, 30-day return or refund guarantee.</p>
	</div>
</div>
<!-- Offcanvas Menu End -->

<!-- Header Section Begin -->
<header class="header">
	<div class="header__top">
		<div class="container">
			<div class="row">
				<div class="col-lg-4 col-md-7">
					<div class="header__top__left">
						<p><s:message code="header.ship.title"/></p>
					</div>
				</div>
				<div class="col-lg-8 col-md-5">
					<div class="header__top__right">
						<div class="header__top__links">
							<c:choose>
								<c:when test="${empty account}">
									<a href="/login"><s:message code="hd.l1.sign"/></a>
									<a href="#">FAQs</a>
								</c:when>
								<c:otherwise>
									<a style="color: #E53642; font-weight: 600;" href="#"> <span
										style="color: white"><s:message code="hd.l1.hi"/>  </span> ${account.username}
									</a>
									<a href="/login/logout"><s:message code="hd.l1.logout"/></a>
								</c:otherwise>
							</c:choose>
							<a class="" aria-current="page" href="?lang=vi">Tiếng Việt</a>
							<a class="" aria-current="page" href="?lang=en">English</a>
						</div>
						
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-lg-3 col-md-3">
				<div class="header__logo">
					<a href="/home"><img src="/img/logo.png" alt=""></a>
				</div>
			</div>
			<div class="col-lg-6 col-md-6">
				<nav class="header__menu mobile-menu">
					<ul>
						<li class="${currentPage == 'home' ? 'active' : ''}"><a
							href="/home"><s:message code="hd.l2.home"/></a></li>
						<li class="${currentPage == 'shop' ? 'active' : ''}"><a
							href="/shop"><s:message code="hd.l2.shop"/></a></li>
						<li class="${currentPage == 'pages' ? 'active' : ''}"><a
							href="#"><s:message code="hd.l2.pages"/></a>
							<ul class="dropdown">
								<li><a href="./about.html">About Us</a></li>
								<li><a href="/checkOut/${account.id }">Your Order</a></li>
								<li><a href="/cart">Shopping Cart</a></li>
								<li><a href="/checkOut">Check Out</a></li>
								<li><a href="./blog-details.html">Blog Details</a></li>
							</ul></li>
						<li class="${currentPage == 'blog' ? 'active' : ''}"><a
							href="./blog.html"><s:message code="hd.l2.blog"/></a></li>
						<li class="${currentPage == 'contact' ? 'active' : ''}"><a
							href="./contact.html"><s:message code="hd.l2.contact"/></a></li>
					</ul>
				</nav>
			</div>
			<div class="col-lg-3 col-md-3">
				<div class="header__nav__option">
					<a href="#" class="search-switch"><img
						src="/img/icon/search.png" alt=""></a> <a href="#"><img
						src="/img/icon/heart.png" alt=""></a> <a href="/cart"><img
						src="/img/icon/cart.png" alt=""> <span>0</span></a>
					<div class="price">$0.00</div>
				</div>
			</div>
		</div>
		<div class="canvas__open">
			<i class="fa fa-bars"></i>
		</div>
	</div>
</header>
<!-- Header Section End -->
<script>
	$(document).ready(function() {
		$("a[href*=lang]").on("click", function() {
			var param = $(this).attr("href");
			$.ajax({
				url : "/home/index" + param,
				success : function() {
					location.reload();
				}
			});
			return false;
		})
	})
</script>