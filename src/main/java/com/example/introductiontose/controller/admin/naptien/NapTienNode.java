package com.example.introductiontose.controller.admin.naptien;

import com.example.introductiontose.dao.DataAccessObject;
import com.example.introductiontose.dao.NapTienDao;
import com.example.introductiontose.dao.TaiKhoanNhanKhauDAO;
import com.example.introductiontose.database.SqlConnection;
import com.example.introductiontose.model.NapTien;
import com.example.introductiontose.model.TaiKhoanNhanKhau;
import com.example.introductiontose.util.AlertUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Node hiển thị thông tin nạp tiền trong giao diện admin.
 *
 * <p>Node này là một thành phần của giao diện, thể hiện thông tin cần nhập tiền cho một người dùng.</p>
 *
 * <p>Node này được tạo bằng cách sử dụng một file FXML để định nghĩa cấu trúc và giao diện của nó.</p>
 *
 * <p>Thông tin hiển thị bao gồm số CCCD, tên, số tiền cần nạp, và ghi chú.</p>
 *
 * @author Duy
 * @version 1.0
 */
public class NapTienNode extends HBox {
    private NapTien napTien;
    
    @FXML
    private Label cccd;
    
    @FXML
    private Label ghiChu;
    
    @FXML
    private Label money;
    
    @FXML
    private Label name;
    
    /**
     * Constructor của NapTienNode.
     *
     * @param soCccd Số CCCD của người cần nạp tiền.
     * @param name Tên của người cần nạp tiền.
     * @param money Số tiền cần nạp.
     * @param ghiChu Ghi chú liên quan đến nạp tiền.
     */
    public NapTienNode(String soCccd, String name, int money, String ghiChu) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/introductiontose/view/admin/naptien/NapTienNode.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        this.cccd.setText(soCccd);
        this.name.setText(name);
        this.money.setText(String.valueOf(money));
        this.ghiChu.setText(ghiChu);
    }
    
    /**
     * Trả về đối tượng NapTien liên kết với Node này.
     *
     * @return Đối tượng NapTien.
     */
    public NapTien getNapTien() {
        return napTien;
    }
    
    /**
     * Thiết lập đối tượng NapTien liên kết với Node này.
     *
     * @param napTien Đối tượng NapTien.
     */
    public void setNapTien(NapTien napTien) {
        this.napTien = napTien;
    }
    
    /**
     * Xử lý sự kiện khi nút "Hủy" được nhấn.
     *
     * @param event Sự kiện nhấn nút "Hủy".
     */
    @FXML
    void cancel(ActionEvent event) {
        Connection connection = SqlConnection.connect();
        DataAccessObject<NapTien, Integer> accessNapTien = new NapTienDao(connection);
        
        try {
            accessNapTien.delete(napTien);
        } catch (SQLException e) {
            AlertUtils.showAlertError("Lỗi", "Xử lí yêu cầu thất bại!");
        }
        
        SqlConnection.close(connection);
    }
    
    /**
     * Xử lý sự kiện khi nút "Nạp tiền" được nhấn.
     *
     * @param event Sự kiện nhấn nút "Nạp tiền".
     */
    @FXML
    void submit(ActionEvent event) {
        Connection connection = SqlConnection.connect();
        DataAccessObject<NapTien, Integer> accessNapTien = new NapTienDao(connection);
        DataAccessObject<TaiKhoanNhanKhau, String> accessTaiKhoan = new TaiKhoanNhanKhauDAO(connection);
        
        try {
            accessNapTien.delete(napTien);
            accessTaiKhoan.get(napTien.getSoCccd()).ifPresent(taiKhoanNhanKhau -> {
                taiKhoanNhanKhau.setSoDuTaiKhoan(taiKhoanNhanKhau.getSoDuTaiKhoan() + napTien.getSoTienNap());
                try {
                    accessTaiKhoan.update(taiKhoanNhanKhau);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (SQLException e) {
            AlertUtils.showAlertError("Lỗi", "Xử lí yêu cầu thất bại!");
        }
        
        SqlConnection.close(connection);
    }
}
