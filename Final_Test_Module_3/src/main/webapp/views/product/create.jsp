<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Thêm sản phẩm</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
  <script>
    function validateForm(event) {
      let isValid = true;

      document.querySelectorAll('.form-text.text-danger').forEach(el => el.style.display = 'none');

      const name = document.forms["productForm"]["name"].value.trim();
      if (name === "") {
        document.getElementById("errorName").style.display = "block";
        isValid = false;
      }

      const price = document.forms["productForm"]["price"].value;
      if (price === "" || price < 101) {
        document.getElementById("errorPrice").style.display = "block";
        isValid = false;
      }

      const stock = document.forms["productForm"]["stock"].value;
      if (stock === "" || stock < 11) {
        document.getElementById("errorStock").style.display = "block";
        isValid = false;
      }

      if (!isValid) {
        event.preventDefault();
      }
    }
  </script>
</head>
<body class="container mt-5">
<h2 class="mb-4">Thêm sản phẩm mới</h2>

<form name="productForm" method="post" action="/products?action=add" class="row g-3" onsubmit="validateForm(event)">
  <div class="col-md-6">
    <label class="form-label">Tên sản phẩm <span class="text-danger">*</span></label>
    <input type="text" name="name" class="form-control" />
    <div id="errorName" class="form-text text-danger" style="display: none;">Tên sản phẩm là bắt buộc.</div>
  </div>
  <div class="col-md-6">
    <label class="form-label">Giá <span class="text-danger">*</span></label>
    <input type="number" name="price" class="form-control" min="101" />
    <div id="errorPrice" class="form-text text-danger" style="display: none;">Giá phải lớn hơn 100.</div>
  </div>
  <div class="col-md-6">
    <label class="form-label">Giảm giá</label>
    <select name="discount" class="form-select">
      <option value="5">5</option>
      <option value="10">10</option>
      <option value="15">15</option>
      <option value="20">20</option>
    </select>
  </div>
  <div class="col-md-6">
    <label class="form-label">Số lượng <span class="text-danger">*</span></label>
    <input type="number" name="stock" class="form-control" min="11" />
    <div id="errorStock" class="form-text text-danger" style="display: none;">Số lượng phải lớn hơn 10.</div>
  </div>
  <div class="col-12">
    <button type="submit" class="btn btn-primary">Lưu</button>
    <a href="/products" class="btn btn-secondary">Hủy</a>
  </div>
</form>
</body>
</html>