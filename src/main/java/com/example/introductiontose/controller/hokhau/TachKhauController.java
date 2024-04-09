package com.example.introductiontose.controller.hokhau;

import com.example.introductiontose.dao.DataAccessObject;
import com.example.introductiontose.dao.TachKhauDAO;
import com.example.introductiontose.database.SqlConnection;
import com.example.introductiontose.model.TachKhau;
import com.example.introductiontose.model.key.TachKhauKey;
import com.example.introductiontose.util.ActionButton;
import com.example.introductiontose.util.AlertUtils;
import com.example.introductiontose.view.icon.IconNhanKhauController;
import com.example.introductiontose.view.icon.IconType;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller để quản lý việc tách khẩu cho hộ khẩu.
 *
 * @author Duy
 * @version 1.0
 */
public class TachKhauController extends DanhSachHoController {
    private final List<IconNhanKhauController> selectedList = new ArrayList<>();
    
    /**
     * Khởi tạo một đối tượng của lớp TachKhauController.
     */
    public TachKhauController() {
        this.originalIconType = IconType.CHUHO;
    }
    
    /**
     * Thực hiện xử lý khi người dùng nhấn nút "Submit".
     * Hiển thị cảnh báo nếu chưa chọn đối tượng, ngược lại, hiển thị xác nhận và gửi yêu cầu tách khẩu.
     */
    @Override
    void submit() {
        if (selectedList.isEmpty()) {
            AlertUtils.showAlert("Chưa chọn đối tượng", "Hãy chọn ít nhất 1 nhân khẩu.");
        } else {
            AlertUtils.showAlert("Xác nhận", "Bạn có chắc chắn tách " +
                    selectedList.get(0).getData().getThongTinNhanKhau().getHoTen() +
                    " và " +
                    (selectedList.size() - 1) +
                    " người khác sang hộ khẩu mới không?", this::sendRequire);
        }
    }
    
    /**
     * Xử lý sự kiện khi người dùng nhấn vào biểu tượng nhân khẩu.
     * Nếu biểu tượng được chọn, thêm vào danh sách chọn, ngược lại, loại bỏ khỏi danh sách chọn.
     * Hiển thị nút "Submit" và "Clear" nếu có ít nhất một đối tượng được chọn.
     *
     * @param iconNhanKhauController Biểu tượng nhân khẩu được nhấn.
     */
    @Override
    void eventClickIcon(IconNhanKhauController iconNhanKhauController) {
        if (iconNhanKhauController.isSelected()) {
            if (selectedList.isEmpty()) {
                ActionButton.showButtonSubmit(submitButton);
                ActionButton.showButtonClear(clearButton);
                isAnyObjectSelected = true;
            }
            selectedList.add(iconNhanKhauController);
        } else {
            if (!selectedList.isEmpty() && selectedList.get(0) == iconNhanKhauController) {
                iconNhanKhauController.setIconType(IconType.NHANKHAU);
            }
            selectedList.remove(iconNhanKhauController);
            
            if (selectedList.isEmpty()) {
                ActionButton.hideButtonSubmit(submitButton);
                ActionButton.hideButtonClear(clearButton);
                isAnyObjectSelected = true;
            }
        }
        
        reload(iconNhanKhauControllerList);
    }
    
    /**
     * Xóa danh sách các đối tượng đã chọn.
     */
    @Override
    void clearSelected() {
        selectedList.clear();
        isAnyObjectSelected = false;
    }
    
    /**
     * Tải lại biểu tượng cho danh sách nhân khẩu.
     *
     * @param iconNhanKhauControllerList Danh sách các biểu tượng nhân khẩu.
     */
    private void reload(List<IconNhanKhauController> iconNhanKhauControllerList) {
        if (selectedList.isEmpty()) {
            changeIconType(iconNhanKhauControllerList, IconType.CHUHO);
        } else {
            if (selectedList.size() == 1) {
                changeIconType(iconNhanKhauControllerList, IconType.NHANKHAU);
            }
            selectedList.get(0).setIconType(IconType.CHUHO);
        }
    }
    
    /**
     * Thay đổi loại biểu tượng cho danh sách nhân khẩu.
     *
     * @param iconNhanKhauControllerList Danh sách các biểu tượng nhân khẩu.
     * @param iconType                  Loại biểu tượng mới.
     */
    private void changeIconType(List<IconNhanKhauController> iconNhanKhauControllerList, IconType iconType) {
        for (IconNhanKhauController iconNhanKhauController : iconNhanKhauControllerList) {
            if (!iconNhanKhauController.isSelected()) {
                iconNhanKhauController.setIconType(iconType);
            }
        }
    }
    
    /**
     * Gửi yêu cầu tách khẩu đến cơ sở dữ liệu.
     */
    private void sendRequire() {
        Connection connection = SqlConnection.connect();
        DataAccessObject<TachKhau, TachKhauKey> accessObject = new TachKhauDAO(connection);
        List<String> danhSachNhanKhau = new ArrayList<>();
        for (int i = 1; i < selectedList.size(); i++) {
            danhSachNhanKhau.add(selectedList.get(i).getData().getThongTinNhanKhau().getCccd().getSoCccd());
        }
        TachKhau change = new TachKhau(selectedList.get(0).getData().getThongTinNhanKhau().getCccd().getSoCccd(),
                hoKhau.getIdHoKhau(),
                danhSachNhanKhau,
                null);
        try {
            accessObject.save(change);
            SqlConnection.close(connection);
        } catch (SQLException e) {
            AlertUtils.showAlertError("Lỗi", "Đã có lỗi khi gửi yêu cầu này.");
        }
    }
}
