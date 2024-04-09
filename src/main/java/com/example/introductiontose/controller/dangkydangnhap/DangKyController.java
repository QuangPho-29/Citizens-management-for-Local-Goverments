package com.example.introductiontose.controller.dangkydangnhap;

import com.example.introductiontose.dao.CauHoiDAO;
import com.example.introductiontose.dao.CauTraLoiDAO;
import com.example.introductiontose.dao.TaiKhoanNhanKhauDAO;
import com.example.introductiontose.model.CauHoi;
import com.example.introductiontose.model.CauTraLoi;
import com.example.introductiontose.model.TaiKhoanNhanKhau;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import static com.example.introductiontose.controller.dangkydangnhap.functionHelp.connection;


public class DangKyController implements Initializable {
    @FXML
    private ComboBox<String> cauhoixacnhan;

    @FXML
    private TextField cautraloi;

    @FXML
    private TextField hoTen;

    @FXML
    private TextField matKhau;

    @FXML
    private DatePicker ngaySinh;

    @FXML
    private TextField soCccd;

    @FXML
    private TextField tenTaiKhoan;

    @FXML
    private Text textError;
    @FXML
    private AnchorPane thongTinTaiKhoan;
    @FXML
    private Text loiThongTin;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        ngaySinh.setEditable(false);
        thongTinTaiKhoan.setVisible(false);
        soCccd.textProperty().addListener((observable, oldValue, newValue) -> {
            thongTinTaiKhoan.setVisible(false);
        });
        hoTen.textProperty().addListener((observable, oldValue, newValue) -> {
            thongTinTaiKhoan.setVisible(false);
        });
        ngaySinh.valueProperty().addListener((observable, oldValue, newValue) -> {
            thongTinTaiKhoan.setVisible(false);
        });

        try {
            setForComboBox();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void setForComboBox() throws SQLException {
        ObservableList<String> questionList = FXCollections.observableArrayList();
        CauHoiDAO cauhoiDao = new CauHoiDAO(connection);
        List<CauHoi> danhSachCauHoi = cauhoiDao.getAll();
        for(CauHoi cauhoi : danhSachCauHoi) {
            questionList.add(cauhoi.getNoiDungCauHoi());
        }
        cauhoixacnhan.setItems(questionList);
        cauhoixacnhan.setVisibleRowCount(3);
    }

    /**
     * Phương thức kiểm tra thông tin đăng ký tài khoản có khớp với dữ liệu nhân khẩu
     * @return true/fale là kết quả kiểm tra
     * throws SQLException lỗi truy vấn dữ liệu
     */
    public boolean kiemTraDangKy() throws SQLException {

        // Lấy thông tin đăng ký
        String hotenDK = hoTen.getText();
        String ngaysinhDK = ngaySinh.getValue().toString();
        String socccdDK = soCccd.getText();

        return functionHelp.kiemTraThongTinDangKy(socccdDK, hotenDK, ngaysinhDK);
    }

    /**
     * Phương thức đưa ra thông báo khi đăng ký tài khoản mới thành công
     * @param e sự kiện click chuột
     * @throws Exception xảy ra lỗi
     */
    public void signUpSuccess (ActionEvent e) throws Exception {
        // Lưu thông tin tài khoản vào bảng taikhoan
        TaiKhoanNhanKhau tknk = new TaiKhoanNhanKhau(soCccd.getText(), tenTaiKhoan.getText(), matKhau.getText(), 0);
        TaiKhoanNhanKhauDAO tknkDao = new TaiKhoanNhanKhauDAO(connection);
        tknkDao.save(tknk);

        // Lưu câu trả lời cho câu hỏi xác nhận
        String cauHoi = cauhoixacnhan.getSelectionModel().getSelectedItem();
        CauTraLoi cauTraLoi = new CauTraLoi(soCccd.getText(), cauHoi, cautraloi.getText());
        CauTraLoiDAO cauTraLoiDAO = new CauTraLoiDAO(connection);
        cauTraLoiDAO.save(cauTraLoi);

        // Thông báo đăng ký thành công
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Đăng ký thành công\nBạn đã được chuyển về trang đăng nhập" +
                "\nĐăng nhập với số CCCD của bạn: " +  soCccd.getText() +
                "\nMật khẩu đăng nhập của bạn là: " +  matKhau.getText() );
        alert.setTitle("Đăng ký thành công");
        alert.show();

        // Quay trở lại trang đăng nhập
        Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/introductiontose/view/dangkydangnhap/dangNhap.fxml"));
        Parent dangnhap = loader.load();
        Scene scene = new Scene(dangnhap);
        stage.setScene(scene);
    }

    /**
     * Phương thức kiểm tra thông tin đăng ký có chính xác hay không
     * @param e sự kiện click chuột vào button "Tiếp tục"
     * @throws SQLException xảy ra lỗi truy vấn dữ liệu
     */
    public void tiepTucClick (ActionEvent e) throws SQLException {
        // Kiem tra thong tin ca nhan
        if(hoTen.getText().isEmpty() || ngaySinh.getValue() == null
                || soCccd.getText().isEmpty()) {
            loiThongTin.setText("Nhập đầy đủ thông tin cá nhân để được xác nhận!");
            return;
        }

        LocalDateTime currentDateTime = LocalDateTime.now();
        if((currentDateTime.getYear() - ngaySinh.getValue().getYear()) < 16 ) {
            loiThongTin.setText("Chưa đủ tuổi để đăng ký tài khoản!");
            return;
        }

        // Kiem tra tai khoan da ton tai
        TaiKhoanNhanKhauDAO tknkdao = new TaiKhoanNhanKhauDAO(connection);
        Optional<TaiKhoanNhanKhau> tknkSelect = tknkdao.get(soCccd.getText());
        if(tknkSelect.isPresent()) {
            loiThongTin.setText("Tài khoản đã tồn tại!");
            return;
        }

        if(kiemTraDangKy()) {
            loiThongTin.setText("");
            thongTinTaiKhoan.setVisible(true);
        } else {
            loiThongTin.setText("Thông tin cá nhân chưa chính xác! Vui lòng nhập lại!");
        }
    }

    /**
     * Phương thức xử lý sự kiện click chuột vào button đăng ký
     * @param e sự kiện click chuột button "Đăng ký"
     */
    public void signUpClicked (ActionEvent e) throws Exception {

        if(tenTaiKhoan.getText().isEmpty() || matKhau.getText().isEmpty()) {
            textError.setText("Yêu cầu nhập đầy đủ tên tài khoản và mật khẩu!");
            return;
        }
        if(cauhoixacnhan.getSelectionModel().getSelectedItem() == null) {
            textError.setText("Chưa chọn câu hỏi mật khẩu cấp hai!");
            return;
        } else {
            if(cautraloi.getText().isEmpty()) {
                textError.setText("Nhập câu trả lời cho câu hỏi mật khẩu cấp hai!");
                return;
            }
        }

        signUpSuccess(e);
    }

    /**
     * Phương thức quy lại trang đăng nhập khi không đăng ký nữa
     * @param e sự kiện click vào icon Return
     * @throws IOException
     */
    public void returnLogin (ActionEvent e) throws IOException {
        Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/introductiontose/view/dangkydangnhap/dangNhap.fxml"));
        Parent dangnhap = loader.load();
        Scene scene = new Scene(dangnhap);
        stage.setScene(scene);
    }

}
