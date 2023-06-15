<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="../../ckeditor/ckeditor/ckeditor.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- Checkout Section Begin -->
<style>
.slider {
	border-radius: 50%;
}

.selectCate{
	height: 50px;
    width: 100%;
    border: 1px solid #e1e1e1;
    font-size: 14px;
    padding-left: 20px;
    margin-bottom: 20px; 
}
</style>
<section class="checkout spad" style="padding: 0">
	<div class="">
		<div class="checkout__form">
			<form action="/admin/product/update" method="post" name="productForm" enctype="multipart/form-data">
				<div class="row" style="margin-left: 0px;">
					<div class="col-lg-8 col-md-6 checkout__order">

						<h6 class="checkout__title">Product Details</h6>
						<c:if test="${not empty errorMsg}">
						    <h6 class="coupon__code" style="color: red">
						        <span class="icon_tag_alt"></span> ${errorMsg}
						    </h6>
						</c:if>
						<div class="row">
							<div class="col-lg-6">
								<div  class="checkout__input">
									<p>
										Name<span>*</span>
									</p>
									<input name="name" style="color: black" type="text"
										value="${findProd.name }">
								</div>
							</div>
							<div class="col-lg-6">
								<div class="checkout__input">
									<p>
										Price<span>*</span>
									</p>
									<input name="price" style="color: black" type="number" value="${findProd.price}" step="any">

								</div>
							</div>
							<div class="col-lg-6">
								<div class="checkout__input">
									<p>
										Available<span>*</span>
									</p>
									<input name="availble" style="color: black" type="number"
										value="${findProd.available }">
								</div>
							</div>
							<div class="col-lg-6">
								<div class="checkout__input">
									<p>
										Created At<span></span>
									</p>
									<input class="form-control" type="text"
										placeholder="Created At" value="${findProd.createdAt }"
										style="background-color: #F5F5F5; color: black" disabled>
								</div>
							</div>
							<input name="idProduct" type="hidden" value="${findProd.id }">
							<input name="oldImage" type="hidden" value="${findProd.image }">
							
							<div class="col-lg-6" >
								<div class="checkout__input">
									<p>
										New category<span></span>
									</p>
									<select style="min-width: 100%;" name="categoryId" id="categorySelect" class="form-select selectCate" aria-label="Default select example">
										<option selected>Chọn Danh Mục</option>
										<c:forEach var="category" items="${categoryList}">
											<option value="${category.id}"
												${category.id == findProd.category.id ? 'selected' : ''}>${category.name}</option>
										</c:forEach>
									</select> 
									<input type="hidden" name="categoryHidden" id="categoryHidden" value="${product.category.id}">
								</div>
							</div>
							<script>
								// Xử lý khi người dùng chọn một category
								document.getElementById('categorySelect').addEventListener(
									'change',function() {
										var categoryId = this.value;
										document.getElementById('categoryHidden').value = categoryId;
								});
							</script>
							
							<div class="col-lg-6">
								<p>
										Customise<span></span>
									</p>
								<div class="checkout__input" style="display: flex;">
							    <div style="width: 49%">
							        <label class="switch">
							            <input style="background-color: black" type="checkbox" name="status" ${findProd.status==1 ? 'checked' : '' }>
							            <span class="slider" style="border-radius: 5px"></span>
							        </label>
							        <p>
							            Status<span>?</span>
							        </p>
							    </div>
								<input type="hidden" id="hiddenStatus" name="hiddenStatus" value="${findProd.status==1 ? '1' : '0' }">
								<script>
									$(document).ready(function() {
									    $('input[name="status"]').change(function() {
									        var hiddenStatus = $(this).prop('checked') ? '1' : '0';
									        document.getElementById('hiddenStatus').value = hiddenStatus;
									    });
									});
								</script>
								
							    <div style="width: 49%">
							        <label class="switch">
							            <input type="checkbox" name="isSale" ${findProd.issale==1 ? 'checked' : '' }>
							            <span class="slider" style="border-radius: 5px"></span>
							        </label>
							        <p>
							            Promotion<span>?</span>
							        </p>
							    </div>
							  	<input type="hidden" id="hiddenIsSale" name="hiddenIsSale" value="${findProd.issale==1 ? '1' : '0' }">
							  	<script>
									$(document).ready(function() {
									    $('input[name="isSale"]').change(function() {
									        var hiddenIsSale = $(this).prop('checked') ? '1' : '0';
									        document.getElementById('hiddenIsSale').value = hiddenIsSale;
									    });
									});
								</script>
							</div>
								
							</div>
						</div>
							<div class="checkout__input">
								<p>
									Description<span>*</span>
								</p>
								<textarea class="form-control mb-3" name="descrip" cols="15">${findProd.descrip }</textarea>
							</div>

					</div>
					<div class="col-lg-4 col-md-6">
						<div class="checkout__order" style="min-height: 100%">
							<h6 class="checkout__title">Product Details</h6>
							<img alt="" src="${findProd.image }"> <br> <input
								type="file" name="imageFile">

							<br>
							<br>
							<button style="width: 49%" type="submit" class="site-btn">SAVE</button>
							<button style="width: 49%" type="submit"
								class="site-btn btn-danger">CANCEL</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</section>
<script>
	CKEDITOR.replace('descrip');
</script>

<!-- Xử lý khi gạt 2 công tắc -->

<!-- Checkout Section End -->