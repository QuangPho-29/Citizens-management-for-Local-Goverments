package com.example.introductiontose.util;

import javafx.scene.control.Button;

import java.util.Objects;

/**
 * Lớp tiện ích ActionButton chứa các phương thức hỗ trợ thay đổi giao diện của Button.
 *
 * <p>Lớp này cung cấp các phương thức để hiển thị hoặc ẩn Button theo các trạng thái cụ thể
 * như "Submit" hoặc "Clear". Các phương thức này giúp quản lý việc thay đổi giao diện của
 * Button dễ dàng trong ứng dụng.</p>
 *
 * <p>Lớp này chỉ chứa các phương thức tĩnh và không thể được khởi tạo để sử dụng như một đối
 * tượng. Tất cả các phương thức đều là các phương thức tĩnh, nên không cần tạo đối tượng mới để
 * sử dụng chúng.</p>
 *
 * <p>Sử dụng các phương thức của lớp này để áp dụng các hiệu ứng hiển thị hoặc ẩn Button tùy
 * thuộc vào trạng thái cụ thể trong ứng dụng.</p>
 *
 * @author Duy
 * @version 1.0
 */
public final class ActionButton {
    private ActionButton() {
    }
    
    /**
     * Hiển thị Button với giao diện "Submit".
     *
     * @param button Button cần hiển thị.
     */
    public static void showButtonSubmit(Button button) {
        button.getStyleClass().clear();
        button.getStyleClass().add("btn-blue");
    }
    
    /**
     * Ẩn Button với giao diện "Submit".
     *
     * @param button Button cần ẩn.
     */
    public static void hideButtonSubmit(Button button) {
        button.getStyleClass().clear();
        button.getStyleClass().add("btn-blue-hidden");
    }
    
    /**
     * Hiển thị Button với giao diện "Clear".
     *
     * @param button Button cần hiển thị.
     */
    public static void showButtonClear(Button button) {
        button.getStyleClass().clear();
        button.getStyleClass().add("btn-red");
    }
    
    /**
     * Ẩn Button với giao diện "Clear".
     *
     * @param button Button cần ẩn.
     */
    public static void hideButtonClear(Button button) {
        button.getStyleClass().clear();
        button.getStyleClass().add("btn-red-hidden");
    }
}
