package com.example.introductiontose.controller.taikhoan;

import com.example.introductiontose.dao.DataAccessObject;
import com.example.introductiontose.dao.TaiKhoanNhanKhauDAO;
import com.example.introductiontose.database.SqlConnection;
import com.example.introductiontose.model.NhanKhau;
import com.example.introductiontose.model.TaiKhoanNhanKhau;
import com.example.introductiontose.util.AlertUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

public class TaiKhoanController {
    @FXML
    private Text hoTen;
    @FXML
    private Text ngaySinh;
    @FXML
    private Text tenTaiKhoan;
    @FXML
    private Text soCccd;
    @FXML
    private Button resetButton;
    @FXML
    private Button deleteButton;
    
    private TaiKhoanNhanKhau taiKhoan;
    private NhanKhau nhanKhau;
    
    public void setData(TaiKhoanNhanKhau taiKhoan, NhanKhau nhanKhau) {
        this.taiKhoan = taiKhoan;
        this.nhanKhau = nhanKhau;
        showData();
        setTextButton();
    }
    
    public String getSoCccd() {
        return soCccd.getText();
    }
    
    public TaiKhoanNhanKhau getTaiKhoan() {
        return taiKhoan;
    }
    
    public NhanKhau getNhanKhau() {
        return nhanKhau;
    }
    
    @FXML
    private void reset(ActionEvent event) {
        AlertUtils.showAlert("Xác nhận", "Bạn có chắc chắn muốn reset lại tài khoản này không?",
                () -> {
                    String oldPass = taiKhoan.getPass();
                    
                    Connection connection = SqlConnection.connect();
                    DataAccessObject<TaiKhoanNhanKhau, String> accessObject = new TaiKhoanNhanKhauDAO(connection);
                    
                    try {
                        taiKhoan.setPass(taiKhoan.getSoCCCD());
                        accessObject.update(taiKhoan);
                    } catch (SQLException e) {
                        taiKhoan.setPass(oldPass);
                        AlertUtils.showAlertError("Lỗi", "Không thể reset tài khoản!");
                    }
                    
                    SqlConnection.close(connection);
                });
    }
    
    @FXML
    private void delete(ActionEvent event) {
        AlertUtils.showAlertError("Xác nhận", "Các dữ liệu về tài khoản này sẽ mất hết.\n" +
                        "Bạn có chắc chắn muốn xóa tài khoản này không?",
                () -> {
                    Connection connection = SqlConnection.connect();
                    DataAccessObject<TaiKhoanNhanKhau, String> accessObject = new TaiKhoanNhanKhauDAO(connection);
                    
                    try {
                        accessObject.delete(taiKhoan);
                    } catch (SQLException e) {
                        AlertUtils.showAlertError("Lỗi", "Không thể xóa tài khoản!");
                    }
                    
                    SqlConnection.close(connection);
                });
    }
    
    private void showData() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        hoTen.setText(nhanKhau.getThongTinNhanKhau().getHoTen());
        ngaySinh.setText(nhanKhau.getThongTinNhanKhau().getNgaySinh().format(formatter));
        tenTaiKhoan.setText(taiKhoan.getTentaikhoan());
        soCccd.setText(nhanKhau.getThongTinNhanKhau().getCccd().getSoCccd());
    }
    
    private void setTextButton() {
        resetButton.setText("Reset");
        deleteButton.setText("Xóa tài khoản");
    }
}
