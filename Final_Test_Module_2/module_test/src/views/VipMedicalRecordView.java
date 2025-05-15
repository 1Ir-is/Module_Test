package views;

import models.VipMedicalRecord;
import repositories.VipMedicalRecordRepository;
import utils.ValidationUtils;

import java.util.Scanner;

public class VipMedicalRecordView {
    private static final VipMedicalRecordRepository repository = new VipMedicalRecordRepository();
    private static final Scanner scanner = new Scanner(System.in);

    public static VipMedicalRecord addVipMedicalRecord() {
        int recordNumber = repository.findAll().size() + 1;
        System.out.print("Nhập mã bệnh án: ");
        String recordId = ValidationUtils.validateInput("BA-\\d{3}", "Lỗi mã bệnh án vui lòng nhập lại theo 'BA-XYZ'.");

        System.out.print("Nhập mã bệnh nhân: ");
        String patientId = ValidationUtils.validateInput("BN-\\d{3}", "Lỗi mã bệnh nhân vui lòng nhập lại theo 'BN-XYZ'.");

        System.out.print("Nhập tên bệnh nhân: ");
        String patientName = ValidationUtils.validateInput("[A-Z][a-z]*(\\s[A-Z][a-z]*)*", "Tên phải bắt đầu bằng chữ hoa.");

        System.out.print("Nhập ngày nhập viện (dd/MM/yyyy): ");
        String admissionDate;
        do {
            admissionDate = scanner.nextLine().trim();
        } while (ValidationUtils.validateDateFormat(admissionDate, "Lỗi định dạng hãy dùng dd/MM/yyyy."));

        System.out.print("Nhập ngày xuất viện (dd/MM/yyyy): ");
        String dischargeDate;
        do {
            dischargeDate = scanner.nextLine().trim();
        } while (ValidationUtils.validateDateFormat(dischargeDate, "Lỗi định dạng hãy dùng dd/MM/yyyy."));

        while (!ValidationUtils.validateStartBeforeEnd(admissionDate, dischargeDate)) {
            System.out.println("Ngày nhập viên không được bé hơn ngày xuất viện");
            System.out.print("Nhập ngày nhập viện (dd/MM/yyyy): ");
            do {
                admissionDate = scanner.nextLine().trim();
            } while (ValidationUtils.validateDateFormat(admissionDate, "Lỗi định dạng hãy dùng dd/MM/yyyy."));

            System.out.print("Nhập ngày xuất viện (dd/MM/yyyy): ");
            do {
                dischargeDate = scanner.nextLine().trim();
            } while (ValidationUtils.validateDateFormat(dischargeDate, "Lỗi định dạng hãy dùng dd/MM/yyyy."));
        }

        System.out.print("Nhập lý do nhập viện: ");
        String reason;
        do {
            reason = scanner.nextLine().trim();
        } while (!ValidationUtils.validateNotEmpty(reason, "Reason cannot be empty!"));

        System.out.print("Nhập loại VIP (VIP I, VIP II, VIP III): ");
        String vipType = ValidationUtils.validateInput("^(VIP I|VIP II|VIP III)$", "Lỗi! Vui lòng chỉ nhập các loại Vip cho phép!");

        System.out.print("Nhập thời hạn VIP (dd/MM/yyyy): ");
        String vipDuration;
        do {
            vipDuration = scanner.nextLine().trim();
        } while (!ValidationUtils.validateDateFormat(vipDuration, "Lỗi định dạng hãy dùng dd/MM/yyyy."));

        return new VipMedicalRecord(
                recordNumber, recordId, patientId, patientName, admissionDate, dischargeDate, reason, vipType, vipDuration
        );
    }
}