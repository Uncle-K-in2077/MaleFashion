<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- Shop Section Begin -->
<form action="/shop">
<section class="shop spad" >
	<div class="container">
		<div class="row">
			<div class="col-lg-3" >
				<div class="shop__sidebar">
					<div class="shop__sidebar__search">
							<input type="text" name="keyword" placeholder="Search...">
							<button  type="submit">
								<span class="icon_search"></span>
							</button>
						
					</div>
					<div class="shop__sidebar__accordion">
						<div class="accordion" id="accordionExample" style="display: block;">
							<div class="shop__product__option__right " style="float: left; font-size: 16px; text-transform: uppercase;">
								<select name="category_id">
									<option class="card-heading" value="0">All Categories</option>
									<c:forEach var="cate" items="${listCategory}">
										<option class="card-heading" value="${cate.id}" > ${cate.name}</option>
									</c:forEach>
								</select>
							</div>
							
							<div class="shop__product__option__right " style="float: left; font-size: 16px; text-transform: uppercase; margin-top: 30px">
								<select name="range">
									<option value="0">Price Range</option>
									<option value="0-5">0.00 - 5.tr VND</option>
									<option value="5-10">5.tr - 10.tr VND</option>
									<option value="10-15">10.tr - 15.tr VND</option>
									<option value="15-20">15.tr - 20.tr VND</option>
									<option value="20-25">20.tr - 25.tr VND</option>
									<option value="25+">25.tr +</option>
								</select>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-lg-9">
				<div class="shop__product__option">
					<div class="row">
						<div class="col-lg-6 col-md-6 col-sm-6">
							<div class="shop__product__option__left">
								<p>Showing 1–12 of 126 results</p>
							</div>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-6">
							<div class="shop__product__option__right">
								<p>Sort by Price:</p>
								<select name="order_by">
									<option value="ASC">Low To High</option>
									<option value="DESC">High To Low</option>
								</select>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<c:forEach var="item" items="${pageProduct.content }">
						<div class="col-lg-4 col-md-6 col-sm-6">
							<!-- Product item begin -->
							<div class="product__item sale">
								<div class="product__item__pic set-bg"
									data-setbg='<c:url value="${item.image }" />'>
									<span class="${item.issale==1 ? 'label' : '' }">${item.issale==1 ? 'SALE' : '' }</span>
									<ul class="product__hover">
										<li><a href="#"><img src="/img/icon/heart.png" alt=""></a></li>
										<li><a href="#"><img src="/img/icon/compare.png"
												alt=""> <span>Compare</span></a></li>
										<li><a href="#"><img src="/img/icon/search.png"
												alt=""></a></li>
									</ul>
								</div>
								<div class="product__item__text">
									<h6>${item.name }</h6>
									<a href="/cart/add?productId=${item.id }" class="add-cart">+ Add To Cart</a>
									<div class="rating">
										<i class="fa fa-star"></i> 
										<i class="fa fa-star"></i> 
										<i class="fa fa-star"></i> 
										<i class="fa fa-star"></i> 
										<i class="fa fa-star-o"></i>
									</div>
									<h5><fmt:formatNumber value="${item.price}" pattern="###,###" /> VND</h5>
									<div class="product__color__select">
										<label for="pc-7"> <input type="radio" id="pc-7">
										</label> <label class="active black" for="pc-8"> <input
											type="radio" id="pc-8">
										</label> <label class="grey" for="pc-9"> <input type="radio"
											id="pc-9">
										</label>
									</div>
								</div>
							</div>
							<!-- Product session end-->
						</div>
					</c:forEach>
					
					
				</div>
					<!-- Paginnation -->
					<nav aria-label="Page navigation example "
						style="width: max-content; margin: 50px auto; color: black">
						<ul class="pagination">
							<li class="page-item"><a class="page-link" style="color: black" 
								href="/shop?keyword=${param.keyword }&range=${param.range }&category_id=${param.category_id }&sort_by=${param.sort_by }&order_by=${param.order_by }&page=0&limit=${param.limit}">
									<img alt="" src="/img/icon/first.png">
								</a></li>
							<li class="page-item"><a class="page-link" style="color: black"
								href="/shop?keyword=${param.keyword }&range=${param.range }&category_id=${param.category_id }&sort_by=${param.sort_by }&order_by=${param.order_by }&page=${pageProduct.number-1 }&limit=${param.limit}">
									<img alt="" src="/img/icon/previous.png">
								</a></li>
							<li class="page-item d-flex align-items-center justify-content-center" style="color: black; min-height: 100%; align-items: center; justify-content: center; font-size: 18px; font-weight: 700 "><a class="page-link" href="#" style="color: black; max-height: 98%; justify-content: center; line-height: 1.7; ">${pageProduct.number+1 }
									/ ${pageProduct.totalPages }</a></li>
							<c:if test="${(pageProduct.number+1)<pageProduct.totalPages }">
								<li class="page-item"><a class="page-link" style="color: black"
									href="/shop?keyword=${param.keyword }&range=${param.range }&category_id=${param.category_id }&sort_by=${param.sort_by }&order_by=${param.order_by }&page=${pageProduct.number+1 }&limit=${param.limit}">
										<img alt="" src="/img/icon/next.png">
									</a></li>
								<li class="page-item"><a class="page-link" style="color: black"
									href="/shop?keyword=${param.keyword }&range=${param.range }&category_id=${param.category_id }&sort_by=${param.sort_by }&order_by=${param.order_by }&page=${pageProduct.totalPages-1 }&limit=${param.limit}">
										<img alt="" src="/img/icon/last.png">
									</a></li>
							</c:if>
				
						</ul>
					</nav>
			</div>
		</div>
	</div>
</section>
</form>
<!-- Shop Section End -->