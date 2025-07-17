package com.example.final_test_module_4.service.khach_hang;

import com.example.final_test_module_4.model.KhachHang;

import java.util.List;

public interface IKhachHangService {
    List<KhachHang> getAllKhachHang();

    KhachHang getKhachHangById(Long id);
}
