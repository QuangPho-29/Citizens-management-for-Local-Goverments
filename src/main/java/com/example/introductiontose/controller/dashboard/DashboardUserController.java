package com.example.introductiontose.controller.dashboard;

import com.example.introductiontose.controller.YeuCauNapTien.YeuCauNapTienUserController;
import com.example.introductiontose.controller.hokhau.YeuCauNhanKhauController;
import com.example.introductiontose.controller.thongke.ThongKeUserController;
import com.example.introductiontose.controller.trangchu.TrangChuUserController;
import com.example.introductiontose.dao.HoKhauDAO;
import com.example.introductiontose.database.SqlConnection;
import com.example.introductiontose.model.HoKhau;
import com.example.introductiontose.model.NhanKhau;
import com.example.introductiontose.model.TaiKhoanNhanKhau;
import com.example.introductiontose.model.key.HoKhauKey;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class DashboardUserController implements Initializable, CenterContent {

    @FXML
    private BorderPane Dashboard;

    @FXML
    private ImageView anhDaiDien;

    @FXML
    private Button chiTiet;

    @FXML
    private Button dongPhiButton;

    @FXML
    private Button hoKhauButton;

    @FXML
    private Button napTienButton;

    @FXML
    private Button nutThongBao;

    @FXML
    private Button thoat;

    @FXML
    private HBox thongTin;

    @FXML
    private VBox thongTinNguoiDung;
    @FXML
    private Label userName;
    @FXML
    private Label ngaySinh;

    @FXML
    private Button thuNho;

    @FXML
    private Button thuTucButton;

    @FXML
    private Button tkDongPhiButton;

    @FXML
    private Button trangChu;
    private Pane trangChuPane, caiDatPane, thongTinPane;
    private Pane hoKhauPane, thuTucHanhChinhhPane;
    private Pane thongKeTPPane, dongPhiPane, napTienPane;

    private TaiKhoanNhanKhau taikhoan;

    private Popup popup;
    
    Connection connection;
    NhanKhau nhanKhau;
    HoKhau hoKhau;

    public void setNhanKhau(NhanKhau nhanKhauInp, TaiKhoanNhanKhau taikhoan, HoKhau hoKhau) {
        this.nhanKhau = nhanKhauInp;
        this.taikhoan = taikhoan;
        userName.setText(nhanKhau.getThongTinNhanKhau().getHoTen());
        ngaySinh.setText(nhanKhau.getThongTinNhanKhau().getNgaySinh().toLocalDate().toString());

        this.hoKhau = hoKhau;
    }



    // Event handlers
    @FXML
    void conThuTucClicked() {
        // Xử lý sự kiện khi "Duyệt yêu cầu" trong "Quản lý nhân khẩu" được nhấn
        if (thuTucHanhChinhhPane == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/introductiontose/view/user/YeuCauNhanKhau.fxml"));
                thuTucHanhChinhhPane = loader.load();
                YeuCauNhanKhauController controller = loader.getController();
                controller.setHoKhau(hoKhau);

            }
            catch (Exception e) {
                e.printStackTrace();
            }
            updateCenterContent(thuTucHanhChinhhPane);
        }
        else updateCenterContent(thuTucHanhChinhhPane);
    }

    @FXML
    void onButtonEntered() {

    }

    @FXML
    void onDongPhiClicked() {
        if (dongPhiPane == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/introductiontose/view/user/DanhSachKhoanPhi.fxml"));
                dongPhiPane = loader.load();
            }
            catch (Exception e) {
                //
            }
            updateCenterContent(dongPhiPane);
        }
        else updateCenterContent(dongPhiPane);
    }

    @FXML
    void onHoKhauClicked() {
        if (hoKhauPane == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/introductiontose/view/user/hokhau/danh-sach-nhan-khau.fxml"));
                hoKhauPane = loader.load();
            }
            catch (Exception e) {
                //
            }
            updateCenterContent(hoKhauPane);
        }
        else updateCenterContent(hoKhauPane);
    }

    @FXML
    void onNapTienClicked() {
        if (napTienPane == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/introductiontose/view/user/YeuCauNapTien.fxml"));
                napTienPane = loader.load();
            }
            catch (Exception e) {
                //
            }
            updateCenterContent(napTienPane);
        }
        else updateCenterContent(napTienPane);
    }

    @FXML
    void onThongKeClicked() {
        // Xử lý sự kiện khi "Thống kê" trong "Quản lý thu phí" được nhấn
        if (thongKeTPPane == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/introductiontose/view/user/ThongKeThuPhi.fxml"));
                thongKeTPPane = loader.load();
                ThongKeUserController controller = loader.getController();
                controller.setTaiKhoan(taikhoan);
            }
            catch (Exception e) {
                //
            }
            updateCenterContent(thongKeTPPane);
        }
        else updateCenterContent(thongKeTPPane);
    }

    @FXML
    void signOut (MouseEvent e) throws IOException {
        System.out.println("hnhbg");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/introductiontose/view/dangkydangnhap/dangNhap.fxml"));
        Parent dangxuat = loader.load();
        Stage oldStage = (Stage)((Node) e.getSource()).getScene().getWindow();
        oldStage.close();
        Stage stage = new Stage();
        Scene scene = new Scene(dangxuat);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }
    @FXML
    void onTrangChuClicked() {
        if (trangChuPane == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/introductiontose/view/user/TrangChu.fxml"));
                trangChuPane = loader.load();
                System.out.println("load trang chu thanh cong");
            }
            catch (Exception e) {
                //
            }
            updateCenterContent(trangChuPane);
        }
        else updateCenterContent(trangChuPane);
    }

    @FXML
    void onThongTinClicked() {
        // Xử lý sự kiện khi "Thông tin" được nhấn
        if (thongTinPane == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/introductiontose/view/user/Personal_detail.fxml"));
                thongTinPane = loader.load();
            }
            catch (Exception e) {
                //
            }
            updateCenterContent(thongTinPane);
        }
        else updateCenterContent(thongTinPane);
    }

    private void createPopup() {
        popup = new Popup();
        Label label = new Label("Thông tin chi tiết người dùng");
        // Cấu hình thêm cho label nếu cần
        popup.getContent().add(label);
    }

    @Override
    public void updateCenterContent(Pane pane) {
        Dashboard.setCenter(pane);
    }


    // Hiển thị popup thông tin người dùng
    @FXML
    private void onMouseEnteredVBox(MouseEvent event) {
        // Tính toán vị trí của popup
        Node source = (Node) event.getSource();
        Window theStage = source.getScene().getWindow();
        popup.show(theStage, theStage.getX() + source.localToScene(0, 0).getX() + source.getScene().getX(),
                theStage.getY() + source.localToScene(0, 0).getY() + source.getScene().getY() + source.getBoundsInParent().getHeight());

    }
    // Ẩn popup khi di chuột ra
    @FXML
    private void onMouseExitedVBox(MouseEvent event) {
        popup.hide();
    }
    // Hiển thị danh sách thông báo
    @FXML
    private void onButtonClicked() {
        // Logic để hiển thị danh sách thông báo
    }

    @FXML
    private void onExitClicked() {
        Platform.exit();
    }

    @FXML
    private void onMinimizeClicked() {
        Stage stage = (Stage) thuNho.getScene().getWindow(); // 'thuNho' là fx:id của nút thu nhỏ
        // Thu nhỏ cửa sổ
        stage.setIconified(true);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connection = SqlConnection.connect();

        this.nhanKhau = new NhanKhau();

        if(nhanKhau == null) {
            System.out.println("Chưa có tk");
            return;
        }
        thongTinNguoiDung.setOnMouseEntered(this::onMouseEnteredVBox);
        thongTinNguoiDung.setOnMouseExited(this::onMouseExitedVBox);

        // Đăng ký sự kiện cho nút
        chiTiet.setOnAction(e -> onButtonClicked());
        nutThongBao.setOnAction(e -> onButtonClicked());
        thoat.setOnAction(e -> onExitClicked());
        thuNho.setOnAction((e -> onMinimizeClicked()));

        // Tải pane
        // Tạo popup
        createPopup();

        onTrangChuClicked();
    }
}
