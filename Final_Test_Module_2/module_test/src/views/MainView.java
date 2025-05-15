package views;

import utils.ValidationUtils;

public class MainView {
    public static int showMainMenu() {
        System.out.println("CHƯƠNG TRÌNH QUẢN LÝ BỆNH ÁN.");
        System.out.println("1. Thêm mới.");
        System.out.println("2. Xoá.");
        System.out.println("3. Xem danh sách các bệnh án.");
        System.out.println("4. Thoát.");
        System.out.print("Chọn chức năng: ");
        return ValidationUtils.validateMenuChoice(4);
    }
}
