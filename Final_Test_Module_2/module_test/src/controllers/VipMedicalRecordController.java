package controllers;

import exception.DuplicateMedicalRecordException;
import models.VipMedicalRecord;
import services.VipMedicalRecordService;
import views.VipMedicalRecordView;

import java.util.List;
import java.util.Scanner;

public class VipMedicalRecordController {
    private final VipMedicalRecordService vipMedicalRecordService = new VipMedicalRecordService();
    private static final Scanner scanner = new Scanner(System.in);

    public void addVipMedicalRecord() {
        try {
            VipMedicalRecord vipMedicalRecord = VipMedicalRecordView.addVipMedicalRecord();
            vipMedicalRecordService.add(vipMedicalRecord);
            System.out.println("Them thanh cong ho so benh an!");
        } catch (DuplicateMedicalRecordException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteVipMedicalRecord() {
        String recordId;
        VipMedicalRecord record;

        while (true) {
            System.out.print("Nhập mã bệnh án: ");
            recordId = scanner.nextLine().trim();
            record = vipMedicalRecordService.findById(recordId);
            if (record != null) {
                break;
            }
        System.out.println("Không tìm thấy hồ sơ bệnh án! Vui lòng nhập lại.");
        }

        while (true) {
            System.out.print("Bạn có chắc muốn xoá hồ sơ bệnh án này không? (Y/N): ");
            String confirmation = scanner.nextLine().trim().toLowerCase();
            if ("Y".equals(confirmation)) {
                vipMedicalRecordService.delete(recordId);
                System.out.println("Xoá thành công!");
                displayAllVipMedicalRecords();
                break;
            } else if ("N".equals(confirmation)) {
                System.out.println("Quay về menu chính");
                break;
            } else {
                System.out.println("Lỗi! Vui lòng chỉ nhập [Y] hoặc [N]!");
            }
        }
    }

    public void displayAllVipMedicalRecords() {
        List<VipMedicalRecord> records = vipMedicalRecordService.findAll();
        if (records.isEmpty()) {
            System.out.println("Hien khong co danh sach benh an!");
        } else {
            for (VipMedicalRecord record : records) {
                System.out.println(record.getDetails());
            }
        }
    }
}