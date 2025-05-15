package controllers;

import utils.ValidationUtils;
import views.MainView;

import java.util.Scanner;

public class MainController {
    private static final Scanner scanner = new Scanner(System.in);
    private final NormalMedicalRecordController normalMedicalRecordController = new NormalMedicalRecordController();
    private final VipMedicalRecordController vipMedicalRecordController = new VipMedicalRecordController();

    public void displayMainMenu() {
        boolean exit = false;

        while (!exit) {
            int choice = MainView.showMainMenu();
            switch (choice) {
                case 1:
                    handleAddRecord();
                    break;
                case 2:
                    handleDeleteRecord();
                    break;
                case 3:
                    handleDisplayRecords();
                    break;
                case 4:
                    System.out.print("Bạn có chắc chắn muốn thoát? (y/n): ");
                    String confirmation = scanner.nextLine().trim().toLowerCase();
                    if ("y".equals(confirmation)) {
                        exit = true;
                        System.out.println("Thoát chương trình!");
                    }
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        }
    }

    private void handleAddRecord() {
        System.out.println("1. Thêm bệnh án thường.");
        System.out.println("2. Thêm bệnh án VIP.");
        System.out.println("3. Quay về.");
        System.out.print("Chọn loại bệnh án: ");
        int type = ValidationUtils.validateMenuChoice(2);
        switch (type) {
            case 1:
                normalMedicalRecordController.addNormalMedicalRecord();
                break;
            case 2:
                vipMedicalRecordController.addVipMedicalRecord();
                break;
            case 3:
                return;
            default:
                System.out.println("Lựa chọn không hợp lệ.");
        }
    }

    private void handleDeleteRecord() {
        System.out.println("1. Xoá bệnh án thường.");
        System.out.println("2. Xoá bệnh án VIP.");
        System.out.println("3. Quay về.");
        System.out.print("Chọn loại bệnh án: ");
        int type = ValidationUtils.validateMenuChoice(2);
        switch (type) {
            case 1:
                normalMedicalRecordController.deleteNormalMedicalRecord();
                break;
            case 2:
                vipMedicalRecordController.deleteVipMedicalRecord();
                break;
            case 3:
                return;
            default:
                System.out.println("Lựa chọn không hợp lệ.");
        }
    }

    private void handleDisplayRecords() {
        System.out.println("1. Xem danh sách bệnh án thường.");
        System.out.println("2. Xem danh sách bệnh án VIP.");
        System.out.println("3. Quay về.");
        System.out.print("Chọn loại bệnh án: ");
        int type = ValidationUtils.validateMenuChoice(2);
        switch (type) {
            case 1:
                normalMedicalRecordController.displayAllNormalMedicalRecords();
                break;
            case 2:
                vipMedicalRecordController.displayAllVipMedicalRecords();
                break;
            case 3:
                return;
            default:
                System.out.println("Lựa chọn không hợp lệ.");
        }
    }
}