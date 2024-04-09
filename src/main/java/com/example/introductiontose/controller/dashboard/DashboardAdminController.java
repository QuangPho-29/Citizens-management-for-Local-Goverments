package com.example.introductiontose.controller.dashboard;

import com.example.introductiontose.view.icon.IconUtils;
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
import javafx.scene.control.TitledPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardAdminController implements Initializable, CenterContent {
    @FXML
    private BorderPane Dashboard;
    //Các Pane hoặc FXML loader cho từng chức năng

    public Pane trangChuPane, caiDatPane, thongTinPane;
    public Pane thongKeNKPane, danhSachNKPane, yeuCauNKPane;
    public Pane thongKeTPPane, danhSachTPPane, yeuCauTPPane, taoKhoanPhiPane;

    @FXML
    private Button thoat;
    @FXML
    private Button thuNho;
    @FXML
    private ImageView anhDaiDien;
    @FXML
    private VBox thongTinNguoiDung;
    @FXML
    private Button chiTiet;
    @FXML
    private Button nutThongBao;

    @FXML
    private Button TrangChu;

    @FXML
    private Button thongKeNK, danhSachNK, yeuCauNK;
    @FXML
    private Button thongKeTP, danhSachTP, yeuCauTP, taoKhoanPhi;
    @FXML
    private TitledPane QuanLyThuPhi;
    @FXML
    private Button CaiDat, thongTin;
    @FXML
    private FontIcon trangChuIcon, qlNKIcon, qlTPIcon, tkNKIcon, dsNKIcon, ycNKIcon, tkTPIcon, dsTPIcon, ycTPIcon, taoKPIcon, caiDatIcon, thongTinIcon;

    @FXML
    private VBox quanLyNhanKhauContainer, quanLyNhanKhauContent;
    @FXML
    private Button quanLyNhanKhauHeader;
    private Popup popup;
    private Button buttonHienTai = new Button();
    private FontIcon iconHienTai = new FontIcon();

    private Color clickedColor = Color.web("#425C5A");
    private Color normalColor = Color.web("#FFFFFF");

    @FXML
    public void toggleQuanLyNhanKhauContent() {
        quanLyNhanKhauContent.setVisible(!quanLyNhanKhauContent.isVisible());
        quanLyNhanKhauContent.setManaged(!quanLyNhanKhauContent.isManaged());
    }

    @FXML
    private VBox quanLyThuPhiContent;

    @FXML
    private void toggleQuanLyThuPhiContent() {
        quanLyThuPhiContent.setVisible(!quanLyThuPhiContent.isVisible());
        quanLyThuPhiContent.setManaged(!quanLyThuPhiContent.isManaged());
    }

    @FXML
    private void handleQuanLyThuPhiContainerClick(MouseEvent event) {
        Node source = (Node) event.getSource();
        if (source.getId().equals("quanLyThuPhiContainer")) {
            // Xử lý khi nhấn vào container (ngoại trừ tiêu đề)
        } else if (source.getId().equals("quanLyThuPhiHeader")) {
            // Xử lý khi nhấn vào tiêu đề
            toggleQuanLyThuPhiContent();
        }
    }


    @FXML
    private void handleQuanLyNhanKhauContainerClick(MouseEvent event) {

        Node source = (Node) event.getSource();
        if (source.getId().equals("quanLyNhanKhauContainer")) {
            // Xử lý khi nhấn vào container (ngoại trừ tiêu đề)
        } else if (source.getId().equals("quanLyNhanKhauHeader")) {
            // Xử lý khi nhấn vào tiêu đề
            toggleQuanLyNhanKhauContent();
        }
    }

    // Event handlers
    @FXML
    void onTrangChuClicked() {
        buttonHienTai.getStyleClass().clear();
        buttonHienTai.getStyleClass().add("button");
        iconHienTai.setIconColor(normalColor);

        trangChuIcon.setIconColor(clickedColor);
        iconHienTai = trangChuIcon;
        TrangChu.getStyleClass().clear();
        TrangChu.getStyleClass().add("button-clicked");


        buttonHienTai = TrangChu;
        // Xử lý sự kiện khi "Trang chủ" được nhấn
        if (trangChuPane == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/introductiontose/view/admin/TrangChu.fxml"));
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
    void onThongKeNKClicked() {
        buttonHienTai.getStyleClass().clear();
        buttonHienTai.getStyleClass().add("button");
        thongKeNK.getStyleClass().clear();
        thongKeNK.getStyleClass().add("button-clicked");
        buttonHienTai = thongKeNK;

        iconHienTai.setIconColor(normalColor);

        tkNKIcon.setIconColor(clickedColor);
        iconHienTai = tkNKIcon;
        // Xử lý sự kiện khi "Thống kê" trong "Quản lý nhân khẩu" được nhấn
        if (thongKeNKPane == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/introductiontose/view/admin/ThongKeNhanKhau.fxml"));
                thongKeNKPane = loader.load();
            }
            catch (Exception e) {
                //
            }
            updateCenterContent(thongKeNKPane);
        }
        else updateCenterContent(thongKeNKPane);
    }

    @FXML
    void onDanhSachNKClicked() {
        // Xử lý sự kiện khi "Danh sách" trong "Quản lý nhân khẩu" được nhấn
        buttonHienTai.getStyleClass().clear();
        buttonHienTai.getStyleClass().add("button");
        danhSachNK.getStyleClass().clear();
        danhSachNK.getStyleClass().add("button-clicked");
        buttonHienTai = danhSachNK;

        iconHienTai.setIconColor(normalColor);

        dsNKIcon.setIconColor(clickedColor);
        iconHienTai = dsNKIcon;
        if (danhSachNKPane == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/introductiontose/view/admin/hokhau/DanhSachNhanKhau.fxml"));
                danhSachNKPane = loader.load();
            }
            catch (Exception e) {
                //
            }
            updateCenterContent(danhSachNKPane);
        }
        else updateCenterContent(danhSachNKPane);
    }

    @FXML
    void onYeuCauNKClicked() {
        buttonHienTai.getStyleClass().clear();
        buttonHienTai.getStyleClass().add("button");
        yeuCauNK.getStyleClass().clear();
        yeuCauNK.getStyleClass().add("button-clicked");
        buttonHienTai = yeuCauNK;

        iconHienTai.setIconColor(normalColor);

        ycNKIcon.setIconColor(clickedColor);
        iconHienTai = ycNKIcon;
        // Xử lý sự kiện khi "Duyệt yêu cầu" trong "Quản lý nhân khẩu" được nhấn
        if (yeuCauNKPane == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/introductiontose/view/admin/YeuCauNhanKhau.fxml"));
                yeuCauNKPane = loader.load();
            }
            catch (Exception e) {
                //
            }
            updateCenterContent(yeuCauNKPane);
        }
        else updateCenterContent(yeuCauNKPane);
    }

    @FXML
    void onThongKeTPClicked() {
        buttonHienTai.getStyleClass().clear();
        buttonHienTai.getStyleClass().add("button");
        thongKeTP.getStyleClass().clear();
        thongKeTP.getStyleClass().add("button-clicked");

        buttonHienTai = thongKeTP;

        iconHienTai.setIconColor(normalColor);

        tkTPIcon.setIconColor(clickedColor);
        iconHienTai = tkTPIcon;
        // Xử lý sự kiện khi "Thống kê" trong "Quản lý thu phí" được nhấn
        if (thongKeTPPane == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/introductiontose/view/admin/ThongKeThuPhi.fxml"));
                thongKeTPPane = loader.load();
            }
            catch (Exception e) {
                //
            }
            updateCenterContent(thongKeTPPane);
        }
        else updateCenterContent(thongKeTPPane);
    }

    @FXML
    void onDanhSachTPClicked() {
        buttonHienTai.getStyleClass().clear();
        buttonHienTai.getStyleClass().add("button");

        danhSachTP.getStyleClass().clear();
        danhSachTP.getStyleClass().add("button-clicked");
        buttonHienTai = danhSachTP;

        iconHienTai.setIconColor(normalColor);

        dsTPIcon.setIconColor(clickedColor);
        iconHienTai = dsTPIcon;
        // Xử lý sự kiện khi "Danh sách" trong "Quản lý thu phí" được nhấn
        if (danhSachTPPane == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/introductiontose/view/admin/DanhSachKhoanPhi.fxml"));
                danhSachTPPane = loader.load();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            updateCenterContent(danhSachTPPane);
        }
        else updateCenterContent(danhSachTPPane);

        System.out.println(danhSachTPPane);
    }

    @FXML
    void onYeuCauTPClicked() {
        buttonHienTai.getStyleClass().clear();
        buttonHienTai.getStyleClass().add("button");

        yeuCauTP.getStyleClass().clear();
        yeuCauTP.getStyleClass().add("button-clicked");
        buttonHienTai = yeuCauTP;

        iconHienTai.setIconColor(normalColor);

        ycTPIcon.setIconColor(clickedColor);
        iconHienTai = ycTPIcon;
        // Xử lý sự kiện khi "Duyệt yêu cầu" trong "Quản lý thu phí" được nhấn
        if (yeuCauTPPane == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/introductiontose/view/admin/naptien/YeuCauNapTien.fxml"));
                yeuCauTPPane = loader.load();
            }
            catch (Exception e) {
                //
            }
            updateCenterContent(yeuCauTPPane);
        }
        else updateCenterContent(yeuCauTPPane);

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
        void onTaoKhoanPhiClicked() {
            buttonHienTai.getStyleClass().clear();
            buttonHienTai.getStyleClass().add("button");

            taoKhoanPhi.getStyleClass().clear();
            taoKhoanPhi.getStyleClass().add("button-clicked");

            buttonHienTai = taoKhoanPhi;

            iconHienTai.setIconColor(normalColor);

            taoKPIcon.setIconColor(clickedColor);
            iconHienTai = taoKPIcon;
        // Xử lý sự kiện khi "Tạo khoản phí" trong "Quản lý thu phí" được nhấn
        if (taoKhoanPhiPane == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/introductiontose/view/taokhoanphi/taoKhoanPhi.fxml"));
                taoKhoanPhiPane = loader.load();
            }
            catch (Exception e) {
                //
            }
            updateCenterContent(taoKhoanPhiPane);
        }
        else updateCenterContent(taoKhoanPhiPane);
    }

    @FXML
    void onCaiDatClicked() {
        buttonHienTai.getStyleClass().clear();
        buttonHienTai.getStyleClass().add("button");

        CaiDat.getStyleClass().clear();
        CaiDat.getStyleClass().add("button-clicked");

        buttonHienTai = CaiDat;

        iconHienTai.setIconColor(normalColor);

        caiDatIcon.setIconColor(clickedColor);
        iconHienTai = caiDatIcon;
        // Xử lý sự kiện khi "Cài đặt" được nhấn
        if (caiDatPane == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/introductiontose/view/admin/TrangChu.fxml"));
                caiDatPane = loader.load();
            }
            catch (Exception e) {
                //
            }
            updateCenterContent(caiDatPane);
        }
        else updateCenterContent(caiDatPane);
    }

    @FXML
    void onThongTinClicked() {
        buttonHienTai.getStyleClass().clear();
        buttonHienTai.getStyleClass().add("button");

        thongTin.getStyleClass().clear();
        thongTin.getStyleClass().add("button-clicked");

        buttonHienTai = thongTin;

        iconHienTai.setIconColor(normalColor);

        thongTinIcon.setIconColor(clickedColor);
        iconHienTai = thongTinIcon;
        // Xử lý sự kiện khi "Thông tin" được nhấn
        if (thongTinPane == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/introductiontose/view/admin/TrangChu.fxml"));
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
        VBox user = new VBox();
        user.setStyle("-fx-background-color: #E1ECEB; -fx-background-radius: 0 0 10 10; -fx-spacing: 10");
        Label label = new Label("Tổ trưởng tổ dân phố");
        label.setStyle("-fx-text-fill: #3D5654");
        Label label1 = new Label("SĐT: 0972120123");
        label1.setStyle("-fx-text-fill: #3D5654;");
        user.getChildren().addAll(label, label1);
        // Cấu hình thêm cho label nếu cần
        popup.getContent().add(user);
    }

    @Override
    public void updateCenterContent(Pane pane) {
        Dashboard.setCenter(pane);
    }


    // Di chuyển HBox sang phải 15px khi di chuột vào
//    @FXML
//    private void onMouseEnteredButton(MouseEvent event) {
//        Button source = (Button) event.getSource();
//        source.setStyle("-fx-background-color: #84B1AD");
//    }
//
//    // Quay lại vị trí ban đầu khi di chuột ra
//    @FXML
//    private void onMouseExitedButton(MouseEvent event) {
//        Button source = (Button) event.getSource();
//        source.setStyle("-fx-background-color: #425C5A");
//    }

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
    private Pane loadNewFXML(String fxmlFileName) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName));
        try {
            return loader.load();
        } catch (IOException e) {
            // Xử lý nếu có lỗi khi tải FXML
            e.printStackTrace();
            return null;
        }
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {

        // Đăng ký sự kiện cho VBox
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