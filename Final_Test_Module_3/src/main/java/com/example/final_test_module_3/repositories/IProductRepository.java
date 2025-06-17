package com.example.final_test_module_3.repositories;

import com.example.final_test_module_3.models.Product;

import java.time.LocalDate;
import java.util.List;

public interface IProductRepository {
    List<Product> findAll();
    Product findById(int id);
    void save(Product product);
    List<Product> getTopSellingProducts(int limit);
    List<Product> getProductsByDateRange(LocalDate startDate, LocalDate endDate);
}