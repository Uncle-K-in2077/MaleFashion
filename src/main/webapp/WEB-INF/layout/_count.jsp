<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!-- Categories Section Begin -->
<section class="categories spad">
	<div class="container">
		<div class="row">
			<div class="col-lg-3">
				<div class="categories__text">
					<h2>
						<s:message code="count.title.1"/> <br /> <span><s:message code="count.title.2"/> </span> <br />
						<s:message code="count.title.3"/> 
					</h2>
				</div>
			</div>
			<div class="col-lg-4">
				<div class="categories__hot__deal">
					<img src="img/product-sale.png" alt="">
					<div class="hot__deal__sticker">
						<span><s:message code="count.saleof"/> </span>
						<h5>$29.99</h5>
					</div>
				</div>
			</div>
			<div class="col-lg-4 offset-lg-1">
				<div class="categories__deal__countdown">
					<span><s:message code="count.deal"/> </span>
					<h2><s:message code="count.title.4"/></h2>
					<div class="categories__deal__countdown__timer" id="countdown">
						<div class="cd-item">
							<span>3</span>
							<p><s:message code="count.day"/></p>
						</div>
						<div class="cd-item">
							<span>1</span>
							<p><s:message code="count.hours"/></p>
						</div>
						<div class="cd-item">
							<span>50</span>
							<p><s:message code="count.minutes"/></p>
						</div>
						<div class="cd-item">
							<span>18</span>
							<p><s:message code="count.second"/></p>
						</div>
					</div>
					<a href="#" class="primary-btn"><s:message code="count.button"/></a>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- Categories Section End -->