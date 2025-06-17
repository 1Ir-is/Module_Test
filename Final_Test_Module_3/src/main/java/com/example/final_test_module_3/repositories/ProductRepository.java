package com.example.final_test_module_3.repositories;

import com.example.final_test_module_3.models.Product;
import com.example.final_test_module_3.utils.JDBCUtils;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements IProductRepository {

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM Product";

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setDiscount(rs.getDouble("discount"));
                product.setStock(rs.getInt("stock"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public Product findById(int id) {
        String query = "SELECT * FROM Product WHERE id = ?";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Product product = new Product();
                    product.setId(rs.getInt("id"));
                    product.setName(rs.getString("name"));
                    product.setPrice(rs.getDouble("price"));
                    product.setDiscount(rs.getDouble("discount"));
                    product.setStock(rs.getInt("stock"));
                    return product;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(Product product) {
        String query = "INSERT INTO Product (name, price, discount, stock) VALUES (?, ?, ?, ?)";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());
            stmt.setDouble(3, product.getDiscount());
            stmt.setInt(4, product.getStock());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> getTopSellingProducts(int limit) {
        List<Product> topProducts = new ArrayList<>();
        String query = "SELECT p.id, p.name, p.price, p.discount, p.stock, SUM(od.quantity) as total_ordered " +
                "FROM Product p " +
                "JOIN OrderDetail od ON p.id = od.product_id " +
                "GROUP BY p.id " +
                "ORDER BY total_ordered DESC " +
                "LIMIT ?";

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, limit);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product();
                    product.setId(rs.getInt("id"));
                    product.setName(rs.getString("name"));
                    product.setPrice(rs.getDouble("price"));
                    product.setDiscount(rs.getDouble("discount"));
                    product.setStock(rs.getInt("stock"));
                    topProducts.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return topProducts;
    }

    public List<Product> getProductsByDateRange(LocalDate startDate, LocalDate endDate) {
        List<Product> products = new ArrayList<>();

        String query = "SELECT DISTINCT p.* FROM Product p " +
                "JOIN OrderDetail od ON p.id = o.product_id " +
                "JOIN Order o ON od.order_id = o.id " +
                "WHERE o.order_date BETWEEN ? AND ?";

        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setDate(1, Date.valueOf(startDate));
            stmt.setDate(2, Date.valueOf(endDate));

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product();
                    product.setId(rs.getInt("id"));
                    product.setName(rs.getString("name"));
                    product.setPrice(rs.getDouble("price"));
                    product.setDiscount(rs.getDouble("discount"));
                    product.setStock(rs.getInt("stock"));
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }
}