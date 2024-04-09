package com.example.introductiontose.controller.admin.naptien;

import com.example.introductiontose.dao.DataAccessObject;
import com.example.introductiontose.dao.NapTienDao;
import com.example.introductiontose.dao.NhanKhauDAO;
import com.example.introductiontose.database.SqlConnection;
import com.example.introductiontose.model.NapTien;
import com.example.introductiontose.model.NhanKhau;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Controller quản lý hiển thị danh sách yêu cầu nạp tiền trong giao diện admin.
 *
 * <p>Controller này kết nối với cơ sở dữ liệu để lấy danh sách yêu cầu nạp tiền và hiển thị chúng trong VBox.</p>
 *
 * <p>Controller này sử dụng các đối tượng DataAccessObject để truy cập cơ sở dữ liệu liên quan đến yêu cầu nạp tiền và thông tin nhân khẩu.</p>
 *
 * @author Duy
 * @version 1.0
 */
public class YeuCauNapTienController implements Initializable {
    
    @FXML
    private VBox yeuCauList;
    
    /**
     * Phương thức khởi tạo của controller, được gọi khi controller được tạo ra.
     * Lấy danh sách yêu cầu nạp tiền từ cơ sở dữ liệu và hiển thị chúng trong VBox.
     *
     * @param url Địa chỉ URL của tài nguyên được tải.
     * @param resourceBundle Đối tượng ResourceBundle chứa các tài nguyên cần thiết.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Connection connection = SqlConnection.connect();
            DataAccessObject<NapTien, Integer> accessNapTien = new NapTienDao(connection);
            DataAccessObject<NhanKhau, String> accessNhanKhau = new NhanKhauDAO(connection, NhanKhauDAO.TableType.NHANKHAU);
            List<NapTien> danhSachNapTien = accessNapTien.getAll();
            
            yeuCauList.getChildren().clear();
            for (NapTien napTien : danhSachNapTien) {
                accessNhanKhau.get(napTien.getSoCccd()).ifPresent(nhanKhau -> {
                    NapTienNode node = new NapTienNode(napTien.getSoCccd(), nhanKhau.getThongTinNhanKhau().getHoTen(), napTien.getSoTienNap(), napTien.getGhiChu());
                    node.setNapTien(napTien);
                    yeuCauList.getChildren().add(node);
                });
            }
            
            SqlConnection.close(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
