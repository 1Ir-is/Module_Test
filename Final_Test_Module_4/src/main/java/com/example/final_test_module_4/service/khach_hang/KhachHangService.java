package com.example.final_test_module_4.service.khach_hang;

import com.example.final_test_module_4.model.KhachHang;
import com.example.final_test_module_4.repository.IKhachHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KhachHangService implements IKhachHangService {

    @Autowired
    private IKhachHangRepository khachHangRepository;

    @Override
    public List<KhachHang> getAllKhachHang() {
        return khachHangRepository.findAll();
    }

    @Override
    public KhachHang getKhachHangById(Long id) {
        return khachHangRepository.findById(id).orElse(null);
    }
}
