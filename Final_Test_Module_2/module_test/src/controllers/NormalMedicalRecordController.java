package controllers;

import exception.DuplicateMedicalRecordException;
import models.NormalMedicalRecord;
import services.NormalMedicalRecordService;
import views.NormalMedicalRecordView;

import java.util.List;
import java.util.Scanner;

public class NormalMedicalRecordController {
    private final NormalMedicalRecordService normalMedicalRecordService = new NormalMedicalRecordService();
    private static final Scanner scanner = new Scanner(System.in);

    public void addNormalMedicalRecord() {
        try {
            NormalMedicalRecord normalMedicalRecord = NormalMedicalRecordView.addNormalMedicalRecord();
            normalMedicalRecordService.add(normalMedicalRecord);
            System.out.println("Them thanh cong ho so benh an!");
        } catch (DuplicateMedicalRecordException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteNormalMedicalRecord() {
        String recordId;
        NormalMedicalRecord record;

        while (true) {
            System.out.print("Nhập mã bệnh án: ");
            recordId = scanner.nextLine().trim();
            record = normalMedicalRecordService.findById(recordId);
            if (record != null) {
                break;
            }
            System.out.println("Không tìm thấy hồ sơ bệnh án! Vui lòng nhập lại.");
        }

        while (true) {
            System.out.print("Bạn có chắc muốn xoá hồ sơ bệnh án này không? (Y/N): ");
            String confirmation = scanner.nextLine().trim().toLowerCase();
            if ("Y".equals(confirmation)) {
                normalMedicalRecordService.delete(recordId);
                System.out.println("Xoá thành công!");
                displayAllNormalMedicalRecords();
                break;
            } else if ("N".equals(confirmation)) {
                System.out.println("Quay về menu chính");
                break;
            } else {
                System.out.println("Lỗi! Vui lòng chỉ nhập [Y] hoặc [N]!");
            }
        }
    }


    public void displayAllNormalMedicalRecords() {
        List<NormalMedicalRecord> records = normalMedicalRecordService.findAll();
        if (records.isEmpty()) {
            System.out.println("Hien khong co danh sach benh an!");
        } else {
            for (NormalMedicalRecord record : records) {
                System.out.println(record.getDetails());
            }
        }
    }
}