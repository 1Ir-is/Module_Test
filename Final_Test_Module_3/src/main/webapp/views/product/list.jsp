<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Danh sách sản phẩm</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>

<jsp:include page="/views/product/commons/header.jsp" />

<div class="container mt-5">
    <h2 class="mb-4">Danh sách sản phẩm</h2>

    <div class="d-flex justify-content-between mb-3">
        <a href="/products?action=add" class="btn btn-success">+ Thêm sản phẩm</a>
        <div>
            <button type="button" class="btn btn-primary me-2" data-bs-toggle="modal" data-bs-target="#topProductsModal">
                Xem top sản phẩm bán chạy
            </button>
            <button type="button" class="btn btn-info" data-bs-toggle="modal" data-bs-target="#dateRangeModal">
                Xem sản phẩm theo thời gian
            </button>
        </div>
    </div>

    <c:if test="${not empty topSellingTitle}">
        <div class="alert alert-info">
                ${topSellingTitle}
        </div>
    </c:if>

    <table class="table table-bordered table-hover">
        <thead class="table-light">
        <tr>
            <th>STT</th>
            <th>Tên sản phẩm</th>
            <th>Giá</th>
            <th>Giảm giá</th>
            <th>Số lượng</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${empty products}">
            <tr>
                <td colspan="5" class="text-center">Danh sách trống</td>
            </tr>
        </c:if>
        <c:forEach var="p" items="${products}">
            <tr>
                <td>${p.id}</td>
                <td>${p.name}</td>
                <td>${p.price}</td>
                <td>${p.discount}</td>
                <td>${p.stock}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<div class="modal fade" id="topProductsModal" tabindex="-1" aria-labelledby="topProductsModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="topProductsModalLabel">Xem top sản phẩm bán chạy</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="/products" method="get">
                    <input type="hidden" name="action" value="top-selling">
                    <div class="mb-3">
                        <label for="topCount" class="form-label">Chọn số lượng top sản phẩm:</label>
                        <select class="form-select" id="topCount" name="count">
                            <option value="all">Tất cả sản phẩm</option>
                            <option value="3">Top 3</option>
                            <option value="5">Top 5</option>
                            <option value="10">Top 10</option>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary">Xem kết quả</button>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="dateRangeModal" tabindex="-1" aria-labelledby="dateRangeModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="dateRangeModalLabel">Xem sản phẩm theo khoảng thời gian</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="/products" method="get">
                    <input type="hidden" name="action" value="date-range">
                    <div class="mb-3">
                        <label for="startDate" class="form-label">Từ ngày:</label>
                        <input type="date" class="form-control" id="startDate" name="startDate" required>
                    </div>
                    <div class="mb-3">
                        <label for="endDate" class="form-label">Đến ngày:</label>
                        <input type="date" class="form-control" id="endDate" name="endDate" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Xem kết quả</button>
                </form>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/views/product/commons/footer.jsp" />

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>