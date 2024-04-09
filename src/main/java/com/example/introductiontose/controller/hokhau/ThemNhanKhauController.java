package com.example.introductiontose.controller.hokhau;

import com.example.introductiontose.dao.DataAccessObject;
import com.example.introductiontose.dao.NhanKhauDAO;
import com.example.introductiontose.database.SqlConnection;
import com.example.introductiontose.model.CCCD;
import com.example.introductiontose.model.NhanKhau;
import com.example.introductiontose.model.ThongTinNhanKhau;
import com.example.introductiontose.util.AlertUtils;
import com.example.introductiontose.util.StringConverterLocalDate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Controller để thêm mới thông tin nhân khẩu.
 *
 * @author Duy
 * @version 1.0
 */
public class ThemNhanKhauController implements Initializable {
    @FXML
    private TextField hoTenText;
    @FXML
    private TextField biDanhText;
    @FXML
    private TextField noiSinhText;
    @FXML
    private TextField nguyenQuanText;
    @FXML
    private TextField danTocText;
    @FXML
    private TextField tonGiaoText;
    @FXML
    private TextField diaChiCuText;
    @FXML
    private TextField soCccdText;
    @FXML
    private TextField noiCapText;
    @FXML
    private TextField ngheNghiepText;
    @FXML
    private TextField noiLamViecText;
    @FXML
    private TextField quanHeText;
    @FXML
    private DatePicker ngaySinhDatePicker;
    @FXML
    private DatePicker ngayCapDatePicker;
    @FXML
    private DatePicker ngayDKTTDatePicker;
    private int idHoKhau;
    
    /**
     * Xử lý sự kiện khi người dùng nhấn nút "Thêm".
     */
    @FXML
    private void addClick(ActionEvent event) {
        submit();
    }
    
    /**
     * Xử lý sự kiện khi người dùng nhấn nút "Điền thông tin".
     * Nếu căn cước tồn tại, điền thông tin vào các trường tương ứng.
     *
     * @param event Sự kiện nhấn nút.
     * @throws SQLException Nếu có lỗi khi truy xuất cơ sở dữ liệu.
     */
    @FXML
    private void fill(ActionEvent event) throws SQLException {
        Connection connection = SqlConnection.connect();
        DataAccessObject<NhanKhau, String> accessObject = new NhanKhauDAO(connection, NhanKhauDAO.TableType.NHANKHAU);
        Optional<NhanKhau> result = accessObject.get(soCccdText.getText());
        if (result.isEmpty()) {
            AlertUtils.showAlertError("Lỗi", "Căn cước không tồn tại hoặc không có dữ liệu.");
        } else {
            _fill(result.get());
        }
    }
    
    /**
     * Khởi tạo các giá trị mặc định cho DatePicker.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ngaySinhDatePicker.setConverter(new StringConverterLocalDate());
        ngayCapDatePicker.setConverter(new StringConverterLocalDate());
        ngayDKTTDatePicker.setConverter(new StringConverterLocalDate());
    }
    
    /**
     * Đặt ID hộ khẩu cho controller.
     *
     * @param idHoKhau ID của hộ khẩu.
     */
    public void setIdHoKhau(int idHoKhau) {
        this.idHoKhau = idHoKhau;
    }
    
    /**
     * Kiểm tra xem các trường bắt buộc đã được điền đầy đủ hay chưa.
     *
     * @return True nếu các trường bắt buộc đã được điền đầy đủ, ngược lại là False.
     */
    private boolean areRequiredFieldsFilled() {
        TextField[] textFields = {hoTenText, noiSinhText, quanHeText};
        DatePicker[] datePickers = {ngaySinhDatePicker, ngayCapDatePicker};
        
        for (TextField textField : textFields) {
            if (textField.getText().isEmpty()) return false;
        }
        for (DatePicker datePicker : datePickers) {
            if (datePicker.getValue() == null) return false;
        }
        
        return true;
    }
    
    /**
     * Xử lý sự kiện khi người dùng nhấn nút "Thêm".
     * Hiển thị cảnh báo nếu thông tin không đầy đủ, ngược lại hiển thị xác nhận và gửi yêu cầu thêm nhân khẩu.
     */
    private void submit() {
        if (areRequiredFieldsFilled()) {
            AlertUtils.showAlert("Xác nhận", "Bạn có chắc chắn muốn thêm nhân khẩu " +
                    hoTenText.getText() +
                    " không?", this::sendRequire);
        } else {
            AlertUtils.showAlertError("Thiếu thông tin", "Các thông tin có đánh dấu (*) là bắt buộc.");
        }
    }
    
    /**
     * Lấy thông tin từ các trường nhập liệu để tạo đối tượng ThongTinNhanKhau.
     *
     * @return Đối tượng ThongTinNhanKhau được tạo từ các trường nhập liệu.
     */
    private ThongTinNhanKhau getInfo() {
        return new ThongTinNhanKhau(new CCCD(soCccdText.getText(),
                (ngayCapDatePicker.getValue() == null) ? LocalDateTime.now() : ngayCapDatePicker.getValue().atStartOfDay(),
                noiCapText.getText()),
                idHoKhau,
                hoTenText.getText(),
                biDanhText.getText(),
                (ngaySinhDatePicker.getValue() == null) ? LocalDateTime.now() : ngaySinhDatePicker.getValue().atStartOfDay(),
                noiSinhText.getText(),
                nguyenQuanText.getText(),
                danTocText.getText(),
                tonGiaoText.getText(),
                ngheNghiepText.getText(),
                noiLamViecText.getText(),
                (ngayDKTTDatePicker.getValue() == null) ? LocalDateTime.now() : ngayDKTTDatePicker.getValue().atStartOfDay(),
                diaChiCuText.getText(),
                quanHeText.getText());
    }
    
    /**
     * Gửi yêu cầu thêm mới nhân khẩu đến cơ sở dữ liệu.
     */
    private void sendRequire() {
        Connection connection = SqlConnection.connect();
        DataAccessObject<NhanKhau, String> accessObject = new NhanKhauDAO(connection, NhanKhauDAO.TableType.NHANKHAUCHUATHEM);
        NhanKhau change = new NhanKhau(this.getInfo());
        try {
            accessObject.save(change);
            SqlConnection.close(connection);
        } catch (Exception e) {
            AlertUtils.showAlertError("Lỗi", "Đã có lỗi khi gửi yêu cầu này.");
        }
    }
    
    /**
     * Điền thông tin từ đối tượng NhanKhau vào các trường nhập liệu.
     *
     * @param nhanKhau Đối tượng NhanKhau chứa thông tin cần điền.
     */
    private void _fill(NhanKhau nhanKhau) {
        hoTenText.setText(nhanKhau.getThongTinNhanKhau().getHoTen());
        biDanhText.setText(nhanKhau.getThongTinNhanKhau().getBiDanh());
        ngaySinhDatePicker.setValue(nhanKhau.getThongTinNhanKhau().getNgaySinh().toLocalDate());
        noiSinhText.setText(nhanKhau.getThongTinNhanKhau().getNoiSinh());
        nguyenQuanText.setText(nhanKhau.getThongTinNhanKhau().getNguyenQuan());
        danTocText.setText(nhanKhau.getThongTinNhanKhau().getDanToc());
        tonGiaoText.setText(nhanKhau.getThongTinNhanKhau().getTonGiao());
        diaChiCuText.setText(nhanKhau.getThongTinNhanKhau().getDiaChiCu());
        ngayCapDatePicker.setValue(nhanKhau.getThongTinNhanKhau().getCccd().getNgayCap().toLocalDate());
        noiCapText.setText(nhanKhau.getThongTinNhanKhau().getCccd().getNoiCap());
        ngheNghiepText.setText(nhanKhau.getThongTinNhanKhau().getNgheNghiep());
        noiLamViecText.setText(nhanKhau.getThongTinNhanKhau().getNoiLamViec());
        ngayDKTTDatePicker.setValue(nhanKhau.getThongTinNhanKhau().getNgayDKTT().toLocalDate());
    }
}
