package com.example.introductiontose.controller.dangkydangnhap;

import com.example.introductiontose.controller.dashboard.DashboardAdminController;
import com.example.introductiontose.controller.dashboard.DashboardUserController;
import com.example.introductiontose.dao.HoKhauDAO;
import com.example.introductiontose.dao.TaiKhoanNhanKhauDAO;
import com.example.introductiontose.model.HoKhau;
import com.example.introductiontose.model.NhanKhau;
import com.example.introductiontose.model.TaiKhoanNhanKhau;
import com.example.introductiontose.model.key.HoKhauKey;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

import static com.example.introductiontose.controller.dangkydangnhap.functionHelp.connection;

public class dangNhapController implements Initializable {
    public static String tenTK;
    public static String CCCD;

    @FXML
    private TextField soCccdDangNhap;
    @FXML
    private  TextField password;
    @FXML
    private Text errorLogin;
    @FXML
    private PasswordField passwordHidden;
    @FXML
    private CheckBox checkBoxPassword;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(connection);
    }

    /**
     * phương thức xử lý sự kiện ẩn hiện password
     * @param event sự kiện click checkbox
     */
    public void changeVisibilityPasswordDN (ActionEvent event) {
        functionHelp.changeVisibilityPassword(event, checkBoxPassword, password, passwordHidden);
    }

    /**
     * Phương thức xử lý sự kiện chuyển đến trang đăng ký khi nhấn vào button "Đăng ký"
     * @param e sự kiện nhấn chuột
     * @throws IOException xảy ra lỗi
     */
    public void signUp (ActionEvent e) throws IOException {
        Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/introductiontose/view/dangkydangnhap/dangKy.fxml"));
        Parent dangky = loader.load();
        Scene scene = new Scene(dangky);
        stage.setScene(scene);
    }

    /**
     * Phương thức xử lý sự kiện chuyển đến trang đổi mật khẩu khi nhấn vào button "Quên mật khẩu"
     * @param e sự kiện nhấn chuột
     * @throws IOException xảy ra lỗi
     */
    public void quenMatKhau (ActionEvent e) throws IOException {
        Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/introductiontose/view/dangkydangnhap/quenMatKhau.fxml"));
        Parent dangky = loader.load();
        Scene scene = new Scene(dangky);
        stage.setScene(scene);
    }

    /**
     * Phương thức sự kiện đăng nhập thành công
     * @param e sự kiện click chuột button Đăng nhập
     * @throws Exception xảy ra lỗi
     */
    public void loginSuccess(ActionEvent e) throws Exception {
        FXMLLoader loader;
        Parent home;
        if(soCccdDangNhap.getText().equals("admin")) {
            System.out.println("admin ok");
            loader = new FXMLLoader(getClass().getResource("/com/example/introductiontose/view/admin/home.fxml"));
            home = loader.load();
        } else {
            Optional<NhanKhau> selectNK = functionHelp.getNhanKhau(soCccdDangNhap.getText());

            if(selectNK.isEmpty()) {
                return;
            }

            String tenTaiKhoan = selectNK.get().getThongTinNhanKhau().getHoTen();
            NhanKhau nhanKhauDangNhap = selectNK.get();

            CCCD = soCccdDangNhap.getText();
            System.out.println("CCCD dang nhap la" + CCCD);
            tenTK = tenTaiKhoan;

            TaiKhoanNhanKhauDAO  taiKhoanNhanKhauDAO = new TaiKhoanNhanKhauDAO(connection);
            TaiKhoanNhanKhau taikhoan = taiKhoanNhanKhauDAO.get(CCCD).orElse(null);

            HoKhauDAO hoKhauDAO = new HoKhauDAO(connection);
            HoKhauKey key = new HoKhauKey(nhanKhauDangNhap.getThongTinNhanKhau().getIdHoKhau());
            HoKhau hoKhau = hoKhauDAO.get(key).orElse(null);

            loader = new FXMLLoader(getClass().getResource("/com/example/introductiontose/view/user/home.fxml"));
            home = loader.load();
            DashboardUserController controller = loader.getController();
            controller.setNhanKhau(nhanKhauDangNhap, taikhoan, hoKhau);
        }

        Stage oldStage = (Stage)((Node) e.getSource()).getScene().getWindow();
        oldStage.close();
        Stage stage = new Stage();
        Scene scene = new Scene(home);

        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    /**
     * Sự kiện kiểm tra thông tin đăng nhập và chuyển đến trang chủ khi nhấn vào button "Đăng nhập"
     * @param e sự kiện nhấn chuột
     * @throws Exception xảy ra lỗi
     */
    public  void login (ActionEvent e) throws Exception {
        if (soCccdDangNhap.getText().isEmpty() || (password.getText().isEmpty() && passwordHidden.getText().isEmpty())) {
            errorLogin.setText("Hãy nhập đầy đủ thông tin");
            return;
        }
        // Lấy thông tin đăng nhập
        String cccd = soCccdDangNhap.getText();

        // Lấy thông tin mật khẩu
        String matkhau = functionHelp.layMatKhau(checkBoxPassword, password,passwordHidden);

        // Lấy thông tin từ CSDL để đối chiếu
        TaiKhoanNhanKhauDAO tknkDao = new TaiKhoanNhanKhauDAO(connection);
        Optional<TaiKhoanNhanKhau> selectTK = tknkDao.get(cccd);

        System.out.println(cccd + " " + matkhau);
        System.out.println(selectTK);
        System.out.println(soCccdDangNhap.getText() + " " + soCccdDangNhap.getText().equals("admin"));
        if(selectTK.isPresent()) {
            TaiKhoanNhanKhau tknk = selectTK.get();
            System.out.println(tknk.getSoCCCD()+ " " + tknk.getPass());
            if(tknk.getPass().equals(matkhau)) {
                loginSuccess(e);
            } else {
                errorLogin.setText("Mã số đăng nhập hoặc \nmật khẩu chưa đúng");
            }
        } else {
            errorLogin.setText("Mã số đăng nhập hoặc \nmật khẩu chưa đúng");
        }

    }

}