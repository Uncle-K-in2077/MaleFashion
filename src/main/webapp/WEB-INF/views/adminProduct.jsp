<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="../../ckeditor/ckeditor/ckeditor.js"></script>
<button class="btn btn-dark collapse-button" style="margin-bottom: 5px" type="button"
				data-bs-toggle="collapse" data-bs-target="#collapseExample"
				aria-expanded="false" aria-controls="collapseExample">Create
				new Product +</button>

			<div class="collapse collapse-element" id="collapseExample">
				<div class="card card-body">
					<form action="/admin/product/add" name="ProductForm" method="post" enctype="multipart/form-data">
						<!-- Các trường dữ liệu của form -->
						<div class="mb-3">
							<label for="exampleInputEmail1" class="form-label">Product
								Name</label> <input type="text" name="name" class="form-control"
								id="exampleInputEmail1" aria-describedby="emailHelp">
						</div>

						<div class="mb-3">
							<label for="exampleInputEmail1" class="form-label">Image</label>
							<input type="file" name="imageFile" class="form-control"
								id="exampleInputEmail1" aria-describedby="emailHelp">
						</div>

						<div class="mb-3">
							<label for="exampleInputEmail1" class="form-label">Price</label>
							<input type="number" name="price" class="form-control"
								id="exampleInputEmail1" aria-describedby="emailHelp">
						</div>
						
						<div class="mb-3">
							<label for="exampleInputEmail1" class="form-label">Availble</label>
							<input type="number" name="availble" class="form-control"
								id="exampleInputEmail1" aria-describedby="emailHelp">
						</div>

						<div class="mb-3">
						<label for="exampleInputEmail1" class="form-label">Category</label>
						<select name="categoryId" id="categorySelect" class="form-select"
							aria-label="Default select example">
							<option selected>Choose Category</option>
							<c:forEach var="category" items="${categoryList}">
								<option value="${category.id}">${category.name}</option>
							</c:forEach>
						</select> <input type="hidden" name="categoryHidden" id="categoryHidden"
							value="">
						<script>
						    // Xử lý khi người dùng chọn một category
						    document.getElementById('categorySelect').addEventListener('change', function() {
						        var categoryId = this.value;
						        document.getElementById('categoryHidden').value = categoryId;
						    });
						</script>
						
						<div class="mb-3">
							<label for="exampleInputEmail1" class="form-label">Description</label>
							<textarea class="form-control mb-3" name="descrip2" cols="15">${findProd.descrip }</textarea>
						</div>

					</div>

						<button type="submit" class="btn btn-dark">Submit</button>
					</form>
				</div>
			</div>
			
			<!-- Script đóng mở form Collapse tự tui viết trời ơi  -->
			<script type="text/javascript">
				document.addEventListener('DOMContentLoaded', function() {
					var collapseButton = document
							.querySelector('.collapse-button');
					var collapseElement = document
							.querySelector('.collapse-element');

					collapseButton.addEventListener('click', function() {
						var collapse = new bootstrap.Collapse(collapseElement);
						collapse.toggle();
					});
				});
			</script>

<table class="table table-striped table-hover table-dark">
	<thead>
		<tr>
			<th scope="col">Name</th>
			<th scope="col">Img</th>
			<th scope="col">Price</th>
			<th scope="col">Created date</th>
			<th scope="col">Quantity & Category</th>
			<th scope="col">Status</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="item" items="${productList }">
			<tr>
				<td><a style="color: white" href="/admin/product/getByID?id=${item.id }">${item.name}</a></td>
				<td><img style="width: 150px" class="img-thumbnail" alt=""
					src="${item.image}"></td>
				<td><fmt:formatNumber value="${item.price}" pattern="###,###" /> đ</td>
				<td>${item.createdAt}</td>
				<td>${item.category.getName()}<br> ${item.available} </td>
				<td>
					<label class="switch">
					    <input type="checkbox" id="toggleSwitch" ${item.status==1 ? 'checked' : '' }>
					    <span class="slider" style="border-radius: 5px"></span>
					  </label>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
 <script>
    var toggleSwitch = document.getElementById('toggleSwitch');
    toggleSwitch.addEventListener('change', function() {
        var productId = <c:out value="${product.id}"/>; // ID của sản phẩm
        var newStatus = toggleSwitch.checked ? 1 : 0; // Trạng thái mới (1: đang kinh doanh, 0: đã ngừng kinh doanh)

        // Gửi yêu cầu AJAX đến máy chủ để cập nhật trạng thái của sản phẩm
        var xhr = new XMLHttpRequest();
        //xhr.open('POST', '/admin/product/updateStatus?productId=' + productId + '&status=' + newStatus, true);
        xhr.send();
    });
</script>
<script>
	CKEDITOR.replace('descrip2');
</script>
