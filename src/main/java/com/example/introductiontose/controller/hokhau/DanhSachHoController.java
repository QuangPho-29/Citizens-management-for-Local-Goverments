package com.example.introductiontose.controller.hokhau;

import com.example.introductiontose.model.HoKhau;
import com.example.introductiontose.model.NhanKhau;
import com.example.introductiontose.util.ActionButton;
import com.example.introductiontose.util.AlertUtils;
import com.example.introductiontose.view.icon.IconNhanKhauController;
import com.example.introductiontose.view.icon.IconType;
import com.example.introductiontose.view.icon.IconUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Lớp cơ sở để quản lý hiển thị và xử lý sự kiện cho danh sách Nhân khẩu trong Hộ khẩu.
 * Được sử dụng để tạo các controller cụ thể cho các chức năng như Đổi chủ hộ, Thêm Nhân khẩu, Xóa Nhân khẩu, Tách khẩu, vv.
 *
 * @author Duy
 * @version 1.0
 */
public abstract class DanhSachHoController {
    @FXML
    Text title;
    @FXML
    Button submitButton;
    @FXML
    Button clearButton;
    @FXML
    VBox mainVBox;
    HoKhau hoKhau;
    IconType originalIconType;
    List<NhanKhau> nhanKhauList;
    List<IconNhanKhauController> iconNhanKhauControllerList;
    boolean isAnyObjectSelected = false;
    
    /**
     * Phương thức trừu tượng để xử lý sự kiện khi người dùng nhấn nút "Submit".
     */
    abstract void submit();
    
    /**
     * Phương thức trừu tượng để xử lý sự kiện khi người dùng nhấn vào biểu tượng Nhân khẩu.
     *
     * @param iconNhanKhauController Controller của biểu tượng Nhân khẩu.
     */
    abstract void eventClickIcon(IconNhanKhauController iconNhanKhauController);
    
    /**
     * Phương thức trừu tượng để xử lý sự kiện khi người dùng nhấn nút "Clear".
     */
    abstract void clearSelected();
    
    /**
     * Phương thức xử lý sự kiện khi người dùng nhấn nút "Submit".
     *
     * @param event Sự kiện nhấn nút.
     */
    @FXML
    void submitButtonClick(ActionEvent event) {
        if (!isAnyObjectSelected) return;
        submit();
    }
    
    /**
     * Phương thức xử lý sự kiện khi người dùng nhấn nút "Clear".
     *
     * @param event Sự kiện nhấn nút.
     */
    @FXML
    void clearButtonClick(ActionEvent event) {
        if (!isAnyObjectSelected) return;
        try {
            this.showInVBox(originalIconType);
            ActionButton.hideButtonSubmit(submitButton);
            ActionButton.hideButtonClear(clearButton);
        } catch (Exception e) {
            AlertUtils.showAlertError("Lỗi", "Xảy ra lỗi trong phần mềm.");
        }
    }
    
    /**
     * Phương thức để khởi chạy controller với dữ liệu Hộ khẩu và danh sách Nhân khẩu.
     *
     * @param hoKhau      Hộ khẩu.
     * @param nhanKhauList Danh sách Nhân khẩu.
     * @throws IOException Nếu có lỗi khi load layout.
     */
    public void launch(HoKhau hoKhau, List<NhanKhau> nhanKhauList) throws IOException {
        this.hoKhau = hoKhau;
        this.nhanKhauList = nhanKhauList;
        this.showInVBox(this.originalIconType);
        ActionButton.hideButtonSubmit(submitButton);
        ActionButton.hideButtonClear(clearButton);
    }
    
    /**
     * Phương thức để hiển thị biểu tượng Nhân khẩu trong VBox theo loại biểu tượng.
     *
     * @param iconType Loại biểu tượng.
     * @throws IOException Nếu có lỗi khi load layout.
     */
    void showInVBox(IconType iconType) throws IOException {
        mainVBox.getChildren().clear();
        clearSelected();
        iconNhanKhauControllerList = new ArrayList<>();
        
        HBox currentHBox = new HBox();
        for (NhanKhau nhanKhau : nhanKhauList) {
            if (currentHBox.getChildren().size() == 3) {
                mainVBox.getChildren().add(currentHBox);
                currentHBox = new HBox();
            }
            
            Button button = IconUtils.createButtonIcon(iconType, nhanKhau, iconNhanKhauControllerList, this::eventClickIcon);
            currentHBox.getChildren().add(button);
        }
        if (!currentHBox.getChildren().isEmpty()) {
            mainVBox.getChildren().add(currentHBox);
        }
    }
}
