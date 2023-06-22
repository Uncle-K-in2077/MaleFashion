<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- Shop Details Section Begin -->
<section class="shop-details">
	<div class="product__details__pic">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="product__details__breadcrumb">
						<a href="./index.html">Home</a> <a href="./shop.html">Shop</a> <span>Product
							Details</span>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-3 col-md-3">
					<ul class="nav nav-tabs" role="tablist">
						<li class="nav-item"><a class="nav-link active"
							data-toggle="tab" href="#tabs-1" role="tab">
								<div class="product__thumb__pic set-bg"
									data-setbg="../${product.image }"></div>
						</a></li>
						<li class="nav-item"><a class="nav-link" data-toggle="tab"
							href="#tabs-2" role="tab">
								<div class="product__thumb__pic set-bg"
									data-setbg="../img/shop-details/thumb-2.png"></div>
						</a></li>
						<li class="nav-item"><a class="nav-link" data-toggle="tab"
							href="#tabs-3" role="tab">
								<div class="product__thumb__pic set-bg"
									data-setbg="../img/shop-details/thumb-3.png"></div>
						</a></li>
						<li class="nav-item"><a class="nav-link" data-toggle="tab"
							href="#tabs-4" role="tab">
								<div class="product__thumb__pic set-bg"
									data-setbg="../img/shop-details/thumb-4.png">
									<i class="fa fa-play"></i>
								</div>
						</a></li>
					</ul>
				</div>
				<div class="col-lg-6 col-md-9">
					<div class="tab-content">
						<div class="tab-pane active" id="tabs-1" role="tabpanel">
							<div class="product__details__pic__item">
								<img
									style="width: 80%; /* Kích thước ảnh mặc định */ height: 80%; object-fit: cover; /* Hiển thị ảnh theo tỷ lệ */ transition: transform 0.3s ease;"
									src="../${product.image }" alt="">
							</div>
						</div>
						<div class="tab-pane" id="tabs-2" role="tabpanel">
							<div class="product__details__pic__item">
								<img src="../img/shop-details/product-big-3.png" alt="">
							</div>
						</div>
						<div class="tab-pane" id="tabs-3" role="tabpanel">
							<div class="product__details__pic__item">
								<img src="../img/shop-details/product-big.png" alt="">
							</div>
						</div>
						<div class="tab-pane" id="tabs-4" role="tabpanel">
							<div class="product__details__pic__item">
								<img src="../img/shop-details/product-big-4.png" alt=""> <a
									href="https://www.youtube.com/watch?v=8PJ3_p7VqHw&list=RD8PJ3_p7VqHw&start_radio=1"
									class="video-popup"><i class="fa fa-play"></i></a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="product__details__content">
		<div class="container">
			<div class="row d-flex justify-content-center">
				<div class="col-lg-8">
					<div class="product__details__text">
						<h3>${product.name }</h3>
						<div class="rating">
							<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
								class="fa fa-star"></i> <i class="fa fa-star"></i> <i
								class="fa fa-star-o"></i> <span> - ${product.available } product available</span>
						</div>
						<h3>
							<fmt:formatNumber value="${product.price}" pattern="###,###" />
							VND<span></span>
						</h3>
						<p>Coat with quilted lining and an adjustable hood. Featuring
							long sleeves with adjustable cuff tabs, adjustable asymmetric hem
							with elastic side tabs and a front zip fastening with placket.</p>

						<div class="product__details__cart__option">
							<div class="quantity">
								<div class="pro-qty">
									<input type="text" value="1">
								</div>
							</div>
							<a href="/cart/add?productId=${product.id }" class="primary-btn">add to cart</a>
						</div>
						<div class="product__details__btns__option">
							<a href="#"><i class="fa fa-heart"></i> add to wishlist</a> <a
								href="#"><i class="fa fa-exchange"></i> Add To Compare</a>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12">
					<div class="product__details__tab">
						<ul class="nav nav-tabs" role="tablist">
							<li class="nav-item"><a class="nav-link active"
								data-toggle="tab" href="#tabs-5" role="tab">Description</a></li>
							<li class="nav-item"><a class="nav-link" data-toggle="tab"
								href="#tabs-6" role="tab">Customer Previews(5)</a></li>
							<li class="nav-item"><a class="nav-link" data-toggle="tab"
								href="#tabs-7" role="tab">Additional information</a></li>
						</ul>
						<div class="tab-content">
							<div class="tab-pane active" id="tabs-5" role="tabpanel">
								<div class="product__details__tab__content">
									<p class="note">Male-Fashion - Embrace the Perfection of Premium-Quality Apparel</p>
									<div class="product__details__tab__content__item">
										<h5>Products Infomation</h5>
										<p>${product.descrip }</p>
									</div>

								</div>
							</div>
							<div class="tab-pane" id="tabs-6" role="tabpanel">
								<div class="product__details__tab__content">
									<div class="product__details__tab__content__item">
										<h5>Products Infomation</h5>
										<p>A Pocket PC is a handheld computer, which features many
											of the same capabilities as a modern PC. These handy little
											devices allow individuals to retrieve and store e-mail
											messages, create a contact file, coordinate appointments,
											surf the internet, exchange text messages and more. Every
											product that is labeled as a Pocket PC must be accompanied
											with specific software to operate the unit and must feature a
											touchscreen and touchpad.</p>
										<p>As is the case with any new technology product, the
											cost of a Pocket PC was substantial during it’s early
											release. For approximately $700.00, consumers could purchase
											one of top-of-the-line Pocket PCs in 2003. These days,
											customers are finding that prices have become much more
											reasonable now that the newness is wearing off. For
											approximately $350.00, a new Pocket PC can now be purchased.</p>
									</div>
									<div class="product__details__tab__content__item">
										<h5>Material used</h5>
										<p>Polyester is deemed lower quality due to its none
											natural quality’s. Made from synthetic materials, not natural
											like wool. Polyester suits become creased easily and are
											known for not being breathable. Polyester suits tend to have
											a shine to them compared to wool and cotton suits, this can
											make the suit look cheap. The texture of velvet is luxurious
											and breathable. Velvet is a great choice for dinner party
											jacket and can be worn all year round.</p>
									</div>
								</div>
							</div>
							<div class="tab-pane" id="tabs-7" role="tabpanel">
								<div class="product__details__tab__content">
									<p class="note">Nam tempus turpis at metus scelerisque
										placerat nulla deumantos solicitud felis. Pellentesque diam
										dolor, elementum etos lobortis des mollis ut risus. Sedcus
										faucibus an sullamcorper mattis drostique des commodo
										pharetras loremos.</p>
									<div class="product__details__tab__content__item">
										<h5>Products Infomation</h5>
										<p>A Pocket PC is a handheld computer, which features many
											of the same capabilities as a modern PC. These handy little
											devices allow individuals to retrieve and store e-mail
											messages, create a contact file, coordinate appointments,
											surf the internet, exchange text messages and more. Every
											product that is labeled as a Pocket PC must be accompanied
											with specific software to operate the unit and must feature a
											touchscreen and touchpad.</p>
										<p>As is the case with any new technology product, the
											cost of a Pocket PC was substantial during it’s early
											release. For approximately $700.00, consumers could purchase
											one of top-of-the-line Pocket PCs in 2003. These days,
											customers are finding that prices have become much more
											reasonable now that the newness is wearing off. For
											approximately $350.00, a new Pocket PC can now be purchased.</p>
									</div>
									<div class="product__details__tab__content__item">
										<h5>Material used</h5>
										<p>Polyester is deemed lower quality due to its none
											natural quality’s. Made from synthetic materials, not natural
											like wool. Polyester suits become creased easily and are
											known for not being breathable. Polyester suits tend to have
											a shine to them compared to wool and cotton suits, this can
											make the suit look cheap. The texture of velvet is luxurious
											and breathable. Velvet is a great choice for dinner party
											jacket and can be worn all year round.</p>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<hr>
<!-- Shop Details Section End -->