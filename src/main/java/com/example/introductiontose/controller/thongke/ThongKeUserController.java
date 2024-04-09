package com.example.introductiontose.controller.thongke;

import com.example.introductiontose.controller.trangchu.TrangChuUserController;
import com.example.introductiontose.dao.KhoanPhiDAO;
import com.example.introductiontose.database.SqlConnection;
import com.example.introductiontose.model.DongPhi;
import com.example.introductiontose.model.KhoanPhi;
import com.example.introductiontose.model.NhanKhau;
import com.example.introductiontose.model.TaiKhoanNhanKhau;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.Connection;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class ThongKeUserController implements Initializable {
    @FXML
    private StackPane bangThongKe;

    @FXML
    private VBox danhSachChuaDong;

    @FXML
    private VBox lichSu;

    @FXML
    private Label soDuTK;

    @FXML
    private Label tenTDP;

    @FXML
    private Label tenUser;

    @FXML
    private Label tienChuaDong;

    @FXML
    private Label tienDongGop;

    @FXML
    private Label xtcKP;

    @FXML
    private Label xtcLichSu;


    private TaiKhoanNhanKhau taiKhoan;
    Connection connection;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public void setTaiKhoan(TaiKhoanNhanKhau taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    private int indexKP = 0, indexLS = 0;

    private void hienThiDanhSachChuaDong () {
        for (KhoanPhi tmp : TrangChuUserController.khoanPhiChuaDong) {
            if (indexKP >= 3) break;
            VBox thayDoi = new VBox(10);
            thayDoi.setStyle("-fx-background-color: #f1f6f6; -fx-background-radius: 10; -fx-padding: 10;");

            Label tenYeuCau = new Label(tmp.getTieudephi());
            tenYeuCau.setStyle("-fx-text-fill: #3D5654");
            Label ten = new Label(tmp.getKieuphi()); // Sử dụng Function để lấy thông tin tên
            ten.setStyle("-fx-text-fill: #3D5654");
            Label tien = new Label(String.valueOf(tmp.getMucphi())); // Sử dụng Function để lấy thông tin tên
            tien.setStyle("-fx-text-fill: #3D5654");
            Label noidung = new Label(tmp.getNoidungphi()); // Sử dụng Function để lấy thông tin tên
            noidung.setStyle("-fx-text-fill: #3D5654");

            thayDoi.getChildren().addAll(tenYeuCau, ten, tien, noidung);
            danhSachChuaDong.getChildren().add(thayDoi);
            indexKP++;
        }
    }

    private void hienThiLichSu() {
        KhoanPhiDAO khoanPhiDAO = new KhoanPhiDAO(connection);
        Map<Integer, String> danhSachKP = khoanPhiDAO.getDanhSachTenKhoanPhi();

        for (DongPhi tmp : TrangChuUserController.danhSachDongPhiUser) {
            if (indexLS >= 4) break;
            VBox thayDoi = new VBox(10);
            thayDoi.setStyle("-fx-background-color: #f1f6f6; -fx-background-radius: 10; -fx-padding: 10;");

            Label tenYeuCau = new Label(danhSachKP.get(tmp.getIdPhi()));
            tenYeuCau.setStyle("-fx-text-fill: #3D5654");
            Label ten = new Label(String.valueOf(tmp.getSoTien())); // Sử dụng Function để lấy thông tin tên
            ten.setStyle("-fx-text-fill: #3D5654");
            Label tien = new Label(tmp.getNgayDong().format(formatter)); // Sử dụng Function để lấy thông tin tên
            tien.setStyle("-fx-text-fill: #3D5654");

            thayDoi.getChildren().addAll(tenYeuCau, ten, tien);
            danhSachChuaDong.getChildren().add(thayDoi);
            indexLS++;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.connection = SqlConnection.connect();

        tenTDP.setText("CNPM");
        tenUser.setText(taiKhoan.getTentaikhoan());
        soDuTK.setText(String.valueOf(taiKhoan.getSoDuTaiKhoan()));
        tienChuaDong.setText(String.valueOf(TrangChuUserController.tongTienChuaDong));
        tienDongGop.setText(String.valueOf(TrangChuUserController.tongTienDongGop));

        hienThiDanhSachChuaDong();
        hienThiLichSu();

        if(indexKP == 0) {
            danhSachChuaDong.getChildren().add(new Label("Thật tuyệt, bạn đã đóng toàn bộ khoản phí"));
            xtcKP.setVisible(false);
        } else if (indexKP < 3) {
            xtcKP.setVisible(false);
        }

        if(indexLS == 0) {
            lichSu.getChildren().add(new Label("Bạn chưa đóng khoản phí nào"));
            xtcKP.setVisible(false);
        }
        if (indexLS < 4) {
            xtcLichSu.setVisible(false);
        }

        bangThongKe.getChildren().clear();
        bangThongKe.getChildren().add(TrangChuUserController.thuPhiChart);

        SqlConnection.close(connection);
    }
}
