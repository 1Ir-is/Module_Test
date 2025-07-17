package com.example.final_test_module_4.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

@Data
public class GiaoDichDTO {
    @NotNull(message = "Mã giao dịch không được để trống")
    @Pattern(regexp = "MGD-\\d{4}", message = "Mã giao dịch phải có định dạng MGD-XXXX")
    private String maGiaoDich;

    @NotNull(message = "Khách hàng không được để trống")
    private Long khachHangId;

    @NotNull(message = "Ngày giao dịch không được để trống")
    @Future(message = "Ngày giao dịch phải lớn hơn ngày hiện tại")
    private LocalDate ngayGiaoDich;

    @NotNull(message = "Đơn giá không được để trống")
    @Min(value = 500000, message = "Đơn giá phải lớn hơn 500,000 VND")
    private Double donGia;

    @NotNull(message = "Diện tích không được để trống")
    @Min(value = 20, message = "Diện tích phải lớn hơn 20 m2")
    private Double dienTich;

    @NotNull(message = "Loại dịch vụ không được để trống")
    private String loaiDichVu;
}