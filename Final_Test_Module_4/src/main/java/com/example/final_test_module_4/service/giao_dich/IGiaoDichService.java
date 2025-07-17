package com.example.final_test_module_4.service.giao_dich;

import com.example.final_test_module_4.model.GiaoDich;

import java.util.List;

public interface IGiaoDichService {
    List<GiaoDich> getAllGiaoDich();

    GiaoDich saveGiaoDich(GiaoDich giaoDich);

    List<GiaoDich> searchGiaoDich(String tenKhachHang, String loaiDichVu);

    GiaoDich getGiaoDichById(Long id);

    void deleteGiaoDichById(Long id);
}
