package com.example.final_test_module_4.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class GiaoDich {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String maGiaoDich;

    @ManyToOne
    @JoinColumn(name = "khach_hang_id")
    private KhachHang khachHang;

    private LocalDate ngayGiaoDich;

    private String loaiDichVu;

    private Double donGia;

    private Double dienTich;

    @Transient
    private String donGiaFormatted;

    @Transient
    private String dienTichFormatted;
}
