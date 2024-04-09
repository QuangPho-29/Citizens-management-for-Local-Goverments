package com.example.introductiontose.controller.hokhau;

import com.example.introductiontose.dao.DataAccessObject;
import com.example.introductiontose.model.ThayDoiNhanKhau;
import com.example.introductiontose.util.AlertUtils;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * Controller để quản lý cửa sổ nhập liệu thông tin về những thay đổi trong nhân khẩu.
 *
 * @author Duy
 * @version 1.0
 */
public class InputDialogController {
    @FXML
    private Text title;
    @FXML
    private TextField noiChuyenDen;
    @FXML
    private TextField ghiChu;
    @FXML
    private CheckBox daChet;
    
    private Stage dialogStage;
    private String soCccd;
    private DataAccessObject<ThayDoiNhanKhau, Integer> accessObject;
    
    /**
     * Thiết lập sân khấu cho cửa sổ dialog.
     *
     * @param dialogStage Sân khấu của cửa sổ dialog.
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    /**
     * Thiết lập tiêu đề của cửa sổ dialog.
     *
     * @param s Tiêu đề của cửa sổ dialog.
     */
    public void setTitle(String s) {
        title.setText(s);
    }
    
    /**
     * Thiết lập số CCCD của nhân khẩu.
     *
     * @param soCccd Số CCCD của nhân khẩu.
     */
    public void setSoCccd(String soCccd) {
        this.soCccd = soCccd;
    }
    
    /**
     * Thiết lập đối tượng truy cập cơ sở dữ liệu.
     *
     * @param accessObject Đối tượng truy cập cơ sở dữ liệu.
     */
    public void setAccessObject(DataAccessObject<ThayDoiNhanKhau, Integer> accessObject) {
        this.accessObject = accessObject;
    }
    
    /**
     * Xử lý sự kiện khi người dùng nhấn vào ô checkbox.
     * Nếu ô checkbox được chọn, vô hiệu hóa ô nhập liệu ghi chú.
     */
    @FXML
    private void clickCheckBox() {
        if (daChet.isSelected()) {
            ghiChu.setText("");
        }
        ghiChu.setDisable(daChet.isSelected());
    }
    
    /**
     * Xử lý sự kiện khi người dùng nhấn nút "OK".
     * Gửi yêu cầu thay đổi thông tin nhân khẩu đến cơ sở dữ liệu và đóng cửa sổ dialog.
     */
    @FXML
    private void handleOKButton() {
        // Xử lý dữ liệu khi người dùng nhấn nút "OK"
        try {
            sendRequire();
        } catch (SQLException e) {
            AlertUtils.showAlertError("Lỗi", "Xóa nhân khẩu thất bại!");
        }
        
        // Đóng dialog
        dialogStage.close();
    }
    
    /**
     * Xử lý sự kiện khi người dùng nhấn nút "Cancel".
     * Đóng cửa sổ dialog mà không thực hiện thay đổi nào.
     */
    @FXML
    private void handleCancelButton() {
        // Đóng dialog
        dialogStage.close();
    }
    
    /**
     * Gửi yêu cầu thay đổi thông tin nhân khẩu đến cơ sở dữ liệu.
     *
     * @throws SQLException Nếu có lỗi trong quá trình thực hiện truy vấn cơ sở dữ liệu.
     */
    private void sendRequire() throws SQLException {
        accessObject.save(new ThayDoiNhanKhau(0, soCccd, "chờ xác nhận",
                LocalDateTime.now(),
                noiChuyenDen.getText(),
                ghiChu.getText()));
    }
}
