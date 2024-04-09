package com.example.introductiontose.view.icon;

import com.example.introductiontose.model.HoKhau;
import com.example.introductiontose.model.NhanKhau;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * Lớp IconUtils cung cấp các phương thức tiện ích để tạo nút biểu tượng (icon) cho các đối tượng nhân khẩu và hộ khẩu.
 * Các biểu tượng này có thể sử dụng trong giao diện người dùng để đại diện cho các đối tượng tương ứng.
 * <p>
 * Các phương thức này giúp giảm lặp code và tăng khả năng tái sử dụng trong việc tạo nút biểu tượng.
 * </p>
 *
 * @author Duy
 * @version 1.0
 */
public class IconUtils {
    
    /**
     * Tạo một nút biểu tượng (icon) cho đối tượng nhân khẩu và thêm vào danh sách quản lý các controller của biểu tượng.
     *
     * @param iconType                   Loại biểu tượng cần tạo.
     * @param nhanKhau                   Đối tượng nhân khẩu liên quan đến biểu tượng.
     * @param iconNhanKhauControllerList Danh sách quản lý các controller của biểu tượng nhân khẩu.
     * @param eventClickIcon             Sự kiện xử lý khi người dùng nhấn vào biểu tượng.
     * @return Nút biểu tượng được tạo.
     * @throws IOException Nếu có lỗi khi tạo biểu tượng từ file FXML.
     */
    public static Button createButtonIcon(IconType iconType, NhanKhau nhanKhau, List<IconNhanKhauController> iconNhanKhauControllerList, Consumer<IconNhanKhauController> eventClickIcon) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(IconUtils.class.getResource("/com/example/introductiontose/view/icon/ho-khau-icon.fxml"));
        
        // Tạo controller và set controller cho fxmlLoader
        IconNhanKhauController iconNhanKhauController = new IconNhanKhauController();
        fxmlLoader.setController(iconNhanKhauController);
        iconNhanKhauControllerList.add(iconNhanKhauController);
        
        // Load fxml
        Button button = fxmlLoader.load();
        HBox.setMargin(button, new Insets(10));
        
        // Set event click cho button
        button.setOnMouseClicked(event -> {
            iconNhanKhauController.setSelected(!iconNhanKhauController.isSelected());
            eventClickIcon.accept(iconNhanKhauController);
        });
        
        // Truyền dữ liệu cho controller
        iconNhanKhauController.setIconType(iconType);
        iconNhanKhauController.setData(nhanKhau);
        
        return button;
    }
    
    /**
     * Tạo một nút biểu tượng (icon) cho đối tượng nhân khẩu và thực hiện sự kiện xử lý khi người dùng nhấn vào biểu tượng.
     *
     * @param nhanKhau       Đối tượng nhân khẩu liên quan đến biểu tượng.
     * @param eventClickIcon Sự kiện xử lý khi người dùng nhấn vào biểu tượng.
     * @return Nút biểu tượng được tạo.
     * @throws IOException Nếu có lỗi khi tạo biểu tượng từ file FXML.
     */
    public static Button createButtonIcon(NhanKhau nhanKhau, Consumer<NhanKhau> eventClickIcon) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(IconUtils.class.getResource("/com/example/introductiontose/view/icon/ho-khau-icon.fxml"));
        
        // Tạo controller và set controller cho fxmlLoader
        IconNhanKhauController iconNhanKhauController = new IconNhanKhauController();
        fxmlLoader.setController(iconNhanKhauController);
        
        // Load fxml
        Button button = fxmlLoader.load();
        HBox.setMargin(button, new Insets(10));
        
        // Set event click cho button
        button.setOnMouseClicked(event -> {
            eventClickIcon.accept(iconNhanKhauController.getData());
        });
        
        // Truyền dữ liệu cho controller
        iconNhanKhauController.setIconType(nhanKhau.getThongTinNhanKhau().getQuanHe().isEmpty() ? IconType.CHUHO : IconType.NHANKHAU);
        iconNhanKhauController.setData(nhanKhau);
        
        return button;
    }
    
    /**
     * Tạo một nút biểu tượng (icon) cho đối tượng hộ khẩu và thực hiện sự kiện xử lý khi người dùng nhấn vào biểu tượng.
     *
     * @param hoKhau          Đối tượng hộ khẩu liên quan đến biểu tượng.
     * @param eventClickIcon  Sự kiện xử lý khi người dùng nhấn vào biểu tượng.
     * @return Nút biểu tượng được tạo.
     * @throws IOException Nếu có lỗi khi tạo biểu tượng từ file FXML.
     */
    public static Button createButtonIcon(HoKhau hoKhau, Consumer<IconHoKhauController> eventClickIcon) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(IconUtils.class.getResource("/com/example/introductiontose/view/icon/ho-khau-icon.fxml"));
        
        // Tạo controller và set controller cho fxmlLoader
        IconHoKhauController iconHoKhauController = new IconHoKhauController();
        fxmlLoader.setController(iconHoKhauController);
        
        // Load fxml
        Button button = fxmlLoader.load();
        HBox.setMargin(button, new Insets(10));
        
        // Set event click cho button
        button.setOnMouseClicked(event -> {
            iconHoKhauController.setSelected(!iconHoKhauController.isSelected());
            eventClickIcon.accept(iconHoKhauController);
        });
        
        // Truyền dữ liệu cho controller
        iconHoKhauController.setIconType(IconType.HOKHAU);
        iconHoKhauController.setData(hoKhau);
        
        return button;
    }
}
