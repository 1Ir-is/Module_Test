package views;

import models.NormalMedicalRecord;
import repositories.NormalMedicalRecordRepository;
import utils.ValidationUtils;

import java.util.Scanner;

public class NormalMedicalRecordView {
    private static final NormalMedicalRecordRepository repository = new NormalMedicalRecordRepository();
    private static final Scanner scanner = new Scanner(System.in);

    public static NormalMedicalRecord addNormalMedicalRecord() {
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
        } while (!ValidationUtils.validateNotEmpty(reason, "Không được để trống!"));

        System.out.print("Nhập phí (VND): ");
        double hospitalFee = ValidationUtils.validatePositiveInt("Lỗi, vui lòng nhập lại!");

        return new NormalMedicalRecord(
                recordNumber, recordId, patientId, patientName, admissionDate, dischargeDate, reason, hospitalFee
        );
    }
}