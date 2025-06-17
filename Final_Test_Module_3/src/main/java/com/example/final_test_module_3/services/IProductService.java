package com.example.final_test_module_3.services;

import com.example.final_test_module_3.models.Product;

import java.time.LocalDate;
import java.util.List;

public interface IProductService {
    List<Product> getAllProducts();
    Product getProductById(int id);
    void addProduct(Product product);
    List<Product> getTopSellingProducts(int limit);
    List<Product> getProductsByDateRange(LocalDate startDate, LocalDate endDate);
}