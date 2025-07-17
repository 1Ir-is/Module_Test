package com.example.final_test_module_4.repository;

import com.example.final_test_module_4.model.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IKhachHangRepository extends JpaRepository<KhachHang, Long> {
}
