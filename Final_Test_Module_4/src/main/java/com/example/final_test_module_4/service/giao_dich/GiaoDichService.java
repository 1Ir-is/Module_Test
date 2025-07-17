package com.example.final_test_module_4.service.giao_dich;

import com.example.final_test_module_4.model.GiaoDich;
import com.example.final_test_module_4.repository.IGiaoDichRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GiaoDichService implements IGiaoDichService {

    @Autowired
    private IGiaoDichRepository giaoDichRepository;

    @Override
    public List<GiaoDich> getAllGiaoDich() {
        return giaoDichRepository.findAll();
    }

    @Override
    public GiaoDich saveGiaoDich(GiaoDich giaoDich) {
        return giaoDichRepository.save(giaoDich);
    }

    @Override
    public List<GiaoDich> searchGiaoDich(String tenKhachHang, String loaiDichVu) {
        return giaoDichRepository.findByKhachHang_TenKhachHangContainingAndLoaiDichVuContaining(tenKhachHang, loaiDichVu);
    }

    @Override
    public GiaoDich getGiaoDichById(Long id) {
        return giaoDichRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteGiaoDichById(Long id) {
        giaoDichRepository.deleteById(id);
    }
}
