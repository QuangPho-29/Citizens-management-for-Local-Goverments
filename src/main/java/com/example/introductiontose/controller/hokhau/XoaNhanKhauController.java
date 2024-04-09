package com.example.introductiontose.controller.hokhau;

import com.example.introductiontose.dao.DataAccessObject;
import com.example.introductiontose.dao.ThayDoiNhanKhauDao;
import com.example.introductiontose.database.SqlConnection;
import com.example.introductiontose.model.NhanKhau;
import com.example.introductiontose.model.ThayDoiNhanKhau;
import com.example.introductiontose.util.ActionButton;
import com.example.introductiontose.util.AlertUtils;
import com.example.introductiontose.view.icon.IconController;
import com.example.introductiontose.view.icon.IconNhanKhauController;
import com.example.introductiontose.view.icon.IconType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Controller để xóa nhân khẩu khỏi hộ khẩu.
 *
 * @author Duy
 * @version 1.0
 */
public class XoaNhanKhauController extends DanhSachHoController {
    private final List<IconNhanKhauController> selectedList = new ArrayList<>();
    
    /**
     * Khởi tạo controller.
     */
    public XoaNhanKhauController() {
        this.originalIconType = IconType.NHANKHAU;
    }
    
    /**
     * Xử lý sự kiện khi người dùng nhấn nút "Xác nhận".
     * Hiển thị cảnh báo nếu không có nhân khẩu nào được chọn, ngược lại hiển thị xác nhận và gửi yêu cầu xóa nhân khẩu.
     */
    @Override
    void submit() {
        if (selectedList.isEmpty()) {
            // create alert
            AlertUtils.showAlert("Chưa chọn đối tượng", "Hãy chọn ít nhất 1 nhân khẩu.");
        } else {
            // create alert
            AlertUtils.showAlert("Xác nhận", "Bạn có chắc chắn xóa " +
                    selectedList.size() +
                    " người khỏi hộ khẩu không?", this::sendRequire);
        }
    }
    
    /**
     * Xử lý sự kiện khi người dùng click vào biểu tượng nhân khẩu.
     * Thêm hoặc xóa biểu tượng khỏi danh sách đã chọn và hiển thị hoặc ẩn nút "Xác nhận".
     *
     * @param iconNhanKhauController Controller của biểu tượng nhân khẩu.
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
            selectedList.remove(iconNhanKhauController);
            if (selectedList.isEmpty()) {
                ActionButton.hideButtonSubmit(submitButton);
                ActionButton.hideButtonClear(clearButton);
                isAnyObjectSelected = false;
            }
        }
    }
    
    /**
     * Xóa danh sách đã chọn.
     */
    @Override
    void clearSelected() {
        selectedList.clear();
        isAnyObjectSelected = false;
    }
    
    /**
     * Gửi yêu cầu xóa nhân khẩu đến cơ sở dữ liệu.
     */
    private void sendRequire() {
        Connection connection = SqlConnection.connect();
        DataAccessObject<ThayDoiNhanKhau, Integer> accessObject = new ThayDoiNhanKhauDao(connection);
        for (IconController<NhanKhau> iconController : selectedList) {
            NhanKhau nhanKhau = iconController.getData();
            Stage dialogStage = createInputDialog(nhanKhau, accessObject);
            if (dialogStage != null) {
                dialogStage.showAndWait();
            }
        }
    }
    
    /**
     * Tạo cửa sổ nhập thông tin khi xóa nhân khẩu.
     *
     * @param nhanKhau     Thông tin nhân khẩu cần xóa.
     * @param accessObject Đối tượng truy cập cơ sở dữ liệu.
     * @return Cửa sổ nhập thông tin.
     */
    private Stage createInputDialog(NhanKhau nhanKhau, DataAccessObject<ThayDoiNhanKhau, Integer> accessObject) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/introductiontose/view/hokhau/InputDialog.fxml"));
            Stage dialogStage = new Stage();
            dialogStage.initOwner(submitButton.getScene().getWindow());
            
            Scene scene = new Scene(loader.load());
            dialogStage.setScene(scene);
            
            InputDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setTitle(nhanKhau.getThongTinNhanKhau().getHoTen());
            controller.setSoCccd(nhanKhau.getThongTinNhanKhau().getCccd().getSoCccd());
            controller.setAccessObject(accessObject);
            
            return dialogStage;
        } catch (IOException e) {
            AlertUtils.showAlertError("Lỗi", "Xảy ra lỗi trong phần mềm.");
        }
        
        return null;
    }
}
