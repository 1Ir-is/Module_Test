package com.example.final_test_module_4.repository;

import com.example.final_test_module_4.model.GiaoDich;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IGiaoDichRepository extends JpaRepository<GiaoDich, Long> {
    List<GiaoDich> findByKhachHang_TenKhachHangContainingAndLoaiDichVuContaining(String tenKhachHang, String loaiDichVu);
}
