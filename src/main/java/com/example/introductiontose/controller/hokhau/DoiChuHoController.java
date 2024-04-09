package com.example.introductiontose.controller.hokhau;

import com.example.introductiontose.dao.DataAccessObject;
import com.example.introductiontose.dao.ThayDoiHoKhauDAO;
import com.example.introductiontose.database.SqlConnection;
import com.example.introductiontose.model.ThayDoiHoKhau;
import com.example.introductiontose.util.ActionButton;
import com.example.introductiontose.util.AlertUtils;
import com.example.introductiontose.view.icon.IconNhanKhauController;
import com.example.introductiontose.view.icon.IconType;
import javafx.scene.image.Image;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Controller để quản lý việc đổi chủ hộ trong Hộ khẩu.
 * Kế thừa từ {@link DanhSachHoController}.
 *
 * @author Duy
 * @version 1.0
 */
public class DoiChuHoController extends DanhSachHoController {
    private IconNhanKhauController selectedController;
    
    /**
     * Constructor của lớp DoiChuHoController.
     * Thiết lập loại biểu tượng mặc định là CHUHO.
     */
    public DoiChuHoController() {
        this.originalIconType = IconType.CHUHO;
    }
    
    /**
     * Phương thức xử lý sự kiện khi người dùng nhấn nút "Submit".
     * Kiểm tra xem đã chọn đối tượng chưa, nếu chưa thì hiển thị cảnh báo, ngược lại, hiển thị xác nhận.
     */
    @Override
    void submit() {
        
        if (selectedController == null) {
            // create alert
            AlertUtils.showAlertError("Chưa chọn đối tượng", "Hãy chọn ít nhất 1 nhân khẩu.");
        } else {
            // create alert
            AlertUtils.showAlert("Xác nhận", "Bạn có chắc chắn thay đổi " +
                    selectedController.getData().getThongTinNhanKhau().getHoTen() +
                    " làm chủ hộ không?", this::sendRequire);
        }
    }
    
    /**
     * Phương thức xử lý sự kiện khi người dùng nhấn vào biểu tượng Nhân khẩu.
     * Quản lý việc chọn/deselect biểu tượng, hiển thị/ẩn các nút "Submit" và "Clear".
     *
     * @param iconNhanKhauController Controller của biểu tượng Nhân khẩu.
     */
    @Override
    void eventClickIcon(IconNhanKhauController iconNhanKhauController) {
        if (iconNhanKhauController.isSelected()) {
            if (selectedController != null) {
                selectedController.setSelected(false);
            } else {
                ActionButton.showButtonSubmit(submitButton);
                ActionButton.showButtonClear(clearButton);
                isAnyObjectSelected = true;
            }
            selectedController = iconNhanKhauController;
        } else {
            selectedController = null;
            ActionButton.hideButtonSubmit(submitButton);
            ActionButton.hideButtonClear(clearButton);
            isAnyObjectSelected = false;
        }
    }
    
    /**
     * Phương thức để xóa chọn đối tượng khi nhấn nút "Clear".
     */
    @Override
    void clearSelected() {
        selectedController = null;
        isAnyObjectSelected = false;
    }
    
    /**
     * Phương thức để gửi yêu cầu đổi chủ hộ đến cơ sở dữ liệu.
     * Gửi yêu cầu thông qua {@link ThayDoiHoKhauDAO}.
     */
    private void sendRequire() {
        Connection connection = SqlConnection.connect();
        DataAccessObject<ThayDoiHoKhau, Integer> accessObject = new ThayDoiHoKhauDAO(connection);
        ThayDoiHoKhau change = new ThayDoiHoKhau(0,
                hoKhau.getIdHoKhau(),
                "chờ xác nhận",
                selectedController.getData().getThongTinNhanKhau().getCccd().getSoCccd(),
                "thay đổi chủ hộ (idHoKhau = " + hoKhau.getIdHoKhau() + ") từ ông " + hoKhau.getTenChuHo() + " sang ông " +
                        selectedController.getData().getThongTinNhanKhau().getHoTen(),
                LocalDateTime.now());
        try {
            accessObject.save(change);
            SqlConnection.close(connection);
        } catch (Exception e) {
            AlertUtils.showAlert("Lỗi", "Đã có lỗi khi gửi yêu cầu này.");
        }
    }
}
