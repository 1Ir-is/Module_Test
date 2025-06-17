package com.example.final_test_module_3.controllers;

import com.example.final_test_module_3.models.Product;
import com.example.final_test_module_3.services.IProductService;
import com.example.final_test_module_3.services.ProductService;
import com.example.final_test_module_3.repositories.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {
    private IProductService productService;

    @Override
    public void init() throws ServletException {
        productService = new ProductService(new ProductRepository());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "/";

        switch (action) {
            case "/":
                listProducts(req, resp);
                break;
            case "add":
                showAddForm(req, resp);
                break;
            case "top-selling":
                showTopSellingProducts(req, resp);
                break;
            case "date-range":
                showProductsByDateRange(req, resp);
                break;
            default:
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    private void showProductsByDateRange(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String startDateStr = req.getParameter("startDate");
            String endDateStr = req.getParameter("endDate");

            if (startDateStr == null || endDateStr == null || startDateStr.isEmpty() || endDateStr.isEmpty()) {
                req.setAttribute("error", "Vui lòng chọn khoảng thời gian");
                listProducts(req, resp);
                return;
            }

            LocalDate startDate = LocalDate.parse(startDateStr);
            LocalDate endDate = LocalDate.parse(endDateStr);

            List<Product> products = productService.getProductsByDateRange(startDate, endDate);

            req.setAttribute("products", products);
            req.setAttribute("topSellingTitle", "Sản phẩm được đặt từ " + startDateStr + " đến " + endDateStr);
            req.getRequestDispatcher("/views/product/list.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("error", "Lỗi khi tìm kiếm: " + e.getMessage());
            listProducts(req, resp);
        }
    }

    private void showTopSellingProducts(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String countParam = req.getParameter("count");

        if (countParam == null || countParam.isEmpty() || "all".equals(countParam)) {
            listProducts(req, resp);
            return;
        }

        try {
            int count = Integer.parseInt(countParam);
            List<Product> topProducts = productService.getTopSellingProducts(count);
            req.setAttribute("products", topProducts);
            req.setAttribute("topSellingTitle", "Top " + count + " sản phẩm bán chạy nhất");
            req.getRequestDispatcher("/views/product/list.jsp").forward(req, resp);
        } catch (NumberFormatException e) {
            listProducts(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null || action.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action is required.");
            return;
        }
        switch (action) {
            case "add":
                addProduct(req, resp);
                break;
            default:
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    private void listProducts(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = productService.getAllProducts();
        req.setAttribute("products", products);
        req.getRequestDispatcher("/views/product/list.jsp").forward(req, resp);
    }


    private void showAddForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/product/create.jsp").forward(req, resp);
    }

    private void addProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String name = req.getParameter("name");
            String priceStr = req.getParameter("price");
            String discountStr = req.getParameter("discount");
            String stockStr = req.getParameter("stock");

            if (name == null || name.trim().isEmpty() || priceStr == null || discountStr == null || stockStr == null) {
                req.setAttribute("error", "All fields are required.");
                req.getRequestDispatcher("/views/product/create.jsp").forward(req, resp);
                return;
            }

            double price = Double.parseDouble(priceStr);
            double discount = Double.parseDouble(discountStr);
            int stock = Integer.parseInt(stockStr);

            Product product = new Product();
            product.setName(name);
            product.setPrice(price);
            product.setDiscount(discount);
            product.setStock(stock);

            productService.addProduct(product);
            resp.sendRedirect(req.getContextPath() + "/products");
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Invalid number format for price, discount, or stock.");
            req.getRequestDispatcher("/views/product/create.jsp").forward(req, resp);
        }
    }
}