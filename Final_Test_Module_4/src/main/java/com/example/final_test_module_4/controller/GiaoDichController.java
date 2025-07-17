package com.example.final_test_module_4.controller;

import com.example.final_test_module_4.dto.GiaoDichDTO;
import com.example.final_test_module_4.model.GiaoDich;
import com.example.final_test_module_4.model.KhachHang;
import com.example.final_test_module_4.service.giao_dich.IGiaoDichService;
import com.example.final_test_module_4.service.khach_hang.IKhachHangService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/giao-dich")
public class GiaoDichController {
    @Autowired
    private IGiaoDichService giaoDichService;

    @Autowired
    private IKhachHangService khachHangService;

    @GetMapping()
    public String listGiaoDich(Model model) {
        List<GiaoDich> giaoDichList = giaoDichService.getAllGiaoDich();
        NumberFormat numberFormat = NumberFormat.getInstance(new Locale("vi", "VN"));
        for (GiaoDich giaoDich : giaoDichList) {
            String donGiaFormatted = numberFormat.format(giaoDich.getDonGia());
            giaoDich.setDonGiaFormatted(donGiaFormatted);
            giaoDich.setDienTichFormatted(String.valueOf(giaoDich.getDienTich().intValue()));
        }
        model.addAttribute("giaoDichList", giaoDichList);
        return "giao_dich/list";
    }

    @GetMapping("/add")
    public String addGiaoDichForm(Model model) {
        model.addAttribute("giaoDichDTO", new GiaoDichDTO());
        model.addAttribute("khachHangList", khachHangService.getAllKhachHang());
        return "giao_dich/add";
    }

    @PostMapping("/add")
    public String saveGiaoDich(@ModelAttribute("giaoDichDTO") @Valid GiaoDichDTO giaoDichDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("khachHangList", khachHangService.getAllKhachHang());
            return "giao_dich/add";
        }

        GiaoDich giaoDich = new GiaoDich();
        giaoDich.setMaGiaoDich(giaoDichDTO.getMaGiaoDich());
        giaoDich.setNgayGiaoDich(giaoDichDTO.getNgayGiaoDich());
        giaoDich.setDonGia(giaoDichDTO.getDonGia());
        giaoDich.setDienTich(giaoDichDTO.getDienTich());
        giaoDich.setLoaiDichVu(giaoDichDTO.getLoaiDichVu());

        KhachHang khachHang = khachHangService.getKhachHangById(giaoDichDTO.getKhachHangId());
        giaoDich.setKhachHang(khachHang);

        giaoDichService.saveGiaoDich(giaoDich);
        return "redirect:/giao-dich";
    }

    @GetMapping("/search")
    public String searchGiaoDich(@RequestParam String tenKhachHang, @RequestParam String loaiDichVu, Model model) {
        List<GiaoDich> giaoDichList = giaoDichService.searchGiaoDich(tenKhachHang, loaiDichVu);
        NumberFormat numberFormat = NumberFormat.getInstance(new Locale("vi", "VN"));
        for (GiaoDich giaoDich : giaoDichList) {
            String donGiaFormatted = numberFormat.format(giaoDich.getDonGia());
            giaoDich.setDonGiaFormatted(donGiaFormatted);
            giaoDich.setDienTichFormatted(String.valueOf(giaoDich.getDienTich().intValue()));
        }
        model.addAttribute("giaoDichList", giaoDichList);
        return "giao_dich/list";
    }

    @GetMapping("details/{id}")
    public String viewGiaoDichDetails(@PathVariable Long id, Model model) {
        GiaoDich giaoDich = giaoDichService.getGiaoDichById(id);
        if (giaoDich == null) {
            return "redirect:/giao-dich";
        }
        NumberFormat numberFormat = NumberFormat.getInstance(new Locale("vi", "VN"));
        String donGiaFormatted = numberFormat.format(giaoDich.getDonGia());
        String dienTichFormatted = String.valueOf(giaoDich.getDienTich().intValue());
        model.addAttribute("giaoDich", giaoDich);
        model.addAttribute("donGiaFormatted", donGiaFormatted);
        model.addAttribute("dienTichFormatted", dienTichFormatted);
        return "giao_dich/details";
    }

    @PostMapping("/{id}/delete")
    public String deleteGiaoDich(@PathVariable Long id) {
        giaoDichService.deleteGiaoDichById(id);
        return "redirect:/giao-dich";
    }
}