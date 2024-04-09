package com.example.introductiontose.util;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * Lớp tiện ích AlertUtils cung cấp các phương thức để hiển thị cửa sổ thông báo trong ứng dụng.
 *
 * <p>Lớp này chứa các phương thức để hiển thị các loại thông báo khác nhau, bao gồm cả thông báo thông thường và
 * thông báo lỗi. Nó cũng hỗ trợ việc thêm hành động khi người dùng nhấn nút "OK" trong cửa sổ thông báo.</p>
 *
 * <p>Các phương thức trong lớp này giúp quản lý giao diện của cửa sổ thông báo và thực hiện các hành động
 * tương ứng khi người dùng tương tác với thông báo.</p>
 *
 * @author Duy
 * @version 1.0
 */
public class AlertUtils {
    /**
     * Hiển thị cửa sổ thông báo thông thường với tiêu đề và nội dung đã chỉ định.
     *
     * @param title   Tiêu đề của thông báo.
     * @param content Nội dung của thông báo.
     */
    public static void showAlert(String title, String content) {
        // Tạo icon
        String imagePath = "/com/example/introductiontose/view/iconImg/icons8-alert-96.png";
        Image image = new Image(Objects.requireNonNull(AlertUtils.class.getResourceAsStream(imagePath)));
        
        // Tạo và hiển thị thông báo
        Alert alert = createAlert(title, content, image);
        addOK(alert);
        alert.showAndWait();
    }
    
    /**
     * Hiển thị cửa sổ thông báo thông thường với tiêu đề, nội dung và hành động đã chỉ định.
     *
     * @param title   Tiêu đề của thông báo.
     * @param content Nội dung của thông báo.
     * @param action  Hành động được thực hiện khi người dùng nhấn nút "OK".
     */
    public static void showAlert(String title, String content, Runnable action) {
        // Tạo icon
        String imagePath = "/com/example/introductiontose/view/iconImg/icons8-alert-96.png";
        Image image = new Image(Objects.requireNonNull(AlertUtils.class.getResourceAsStream(imagePath)));
        
        // Tạo và hiển thị thông báo
        showAlert(title, content, image, action);
    }
    
    /**
     * Hiển thị cửa sổ thông báo lỗi với tiêu đề và nội dung đã chỉ định.
     *
     * @param title   Tiêu đề của thông báo lỗi.
     * @param content Nội dung của thông báo lỗi.
     */
    public static void showAlertError(String title, String content) {
        // Tạo icon
        String imagePath = "/com/example/introductiontose/view/iconImg/icons8-alert-red-100.png";
        Image image = new Image(Objects.requireNonNull(AlertUtils.class.getResourceAsStream(imagePath)));
        
        // Tạo và hiển thị thông báo lỗi
        Alert alert = createAlert(title, content, image);
        addOK(alert);
        alert.showAndWait();
    }
    
    /**
     * Hiển thị cửa sổ thông báo lỗi với tiêu đề, nội dung và hành động đã chỉ định.
     *
     * @param title   Tiêu đề của thông báo lỗi.
     * @param content Nội dung của thông báo lỗi.
     * @param action  Hành động được thực hiện khi người dùng nhấn nút "OK".
     */
    public static void showAlertError(String title, String content, Runnable action) {
        // Tạo icon
        String imagePath = "/com/example/introductiontose/view/iconImg/icons8-alert-red-100.png";
        Image image = new Image(Objects.requireNonNull(AlertUtils.class.getResourceAsStream(imagePath)));
        
        // Tạo và hiển thị thông báo lỗi
        showAlert(title, content, image, action);
    }
    
    /**
     * Hiển thị cửa sổ thông báo với tiêu đề, nội dung và biểu tượng đã chỉ định.
     *
     * @param title   Tiêu đề của thông báo.
     * @param content Nội dung của thông báo.
     * @param image   Biểu tượng của thông báo.
     */
    public static void showAlert(String title, String content, Image image) {
        // Tạo và hiển thị thông báo có biểu tượng
        Alert alert = createAlert(title, content, image);
        addOK(alert);
        alert.showAndWait();
    }
    
    /**
     * Hiển thị cửa sổ thông báo với tiêu đề, nội dung, biểu tượng và hành động đã chỉ định.
     *
     * @param title   Tiêu đề của thông báo.
     * @param content Nội dung của thông báo.
     * @param image   Biểu tượng của thông báo.
     * @param action  Hành động được thực hiện khi người dùng nhấn nút "OK".
     */
    public static void showAlert(String title, String content, Image image, Runnable action) {
        // Tạo và hiển thị thông báo có biểu tượng
        Alert alert = createAlert(title, content, image);
        // Thêm nút "OK" và thực hiện hành động
        addOK(alert, action);
    }
    
    /**
     * Tạo cửa sổ thông báo với tiêu đề và nội dung đã chỉ định.
     *
     * @param title   Tiêu đề của thông báo.
     * @param content Nội dung của thông báo.
     * @return Đối tượng Alert đã được tạo.
     */
    public static Alert createAlert(String title, String content) {
        // Tạo cửa sổ thông báo
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        
        // Xóa mọi phần tử không cần thiết
        alert.getDialogPane().getButtonTypes().clear();
        
        // Tăng kích thước của cửa sổ để vừa với nội dung
        Node graphic = alert.getDialogPane().getGraphic();
        if (graphic instanceof Region) {
            ((Region) graphic).setMaxHeight(Double.MAX_VALUE);
            ((Region) graphic).setMaxWidth(Double.MAX_VALUE);
        }
        
        return alert;
    }
    
    /**
     * Tạo cửa sổ thông báo với tiêu đề, nội dung và biểu tượng đã chỉ định.
     *
     * @param title   Tiêu đề của thông báo.
     * @param content Nội dung của thông báo.
     * @param image   Biểu tượng của thông báo.
     * @return Đối tượng Alert đã được tạo.
     */
    public static Alert createAlert(String title, String content, Image image) {
        // Tạo cửa sổ thông báo
        Alert alert = createAlert(title, content);
        
        // Thêm biểu tượng cho Alert
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(image);
        
        return alert;
    }
    
    /**
     * Thêm nút "OK" vào cửa sổ thông báo.
     *
     * @param alert Đối tượng Alert cần thêm nút "OK".
     */
    private static void addOK(Alert alert) {
        // Thêm nút "OK"
        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().setAll(okButton);
    }
    
    /**
     * Thêm nút "OK" vào cửa sổ thông báo và thực hiện hành động khi nút "OK" được nhấn.
     *
     * @param alert  Đối tượng Alert cần thêm nút "OK".
     * @param action Hành động được thực hiện khi người dùng nhấn nút "OK".
     */
    private static void addOK(Alert alert, Runnable action) {
        // Thêm nút "OK"
        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().setAll(okButton);
        
        // Xử lý sự kiện khi nút "OK" được nhấn
        alert.showAndWait().ifPresent(response -> {
            if (response == okButton) {
                action.run();
            }
        });
    }
}
