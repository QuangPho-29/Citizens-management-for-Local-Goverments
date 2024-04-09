package com.example.introductiontose.controller.dangkydangnhap;

import com.example.introductiontose.database.SqlConnection;
import com.example.introductiontose.model.NhanKhau;
import com.example.introductiontose.model.ThongTinNhanKhau;
import com.example.introductiontose.util.SQLUtils;
import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class functionHelp {

    public static Connection connection = SqlConnection.connect();
    /**
     * Phưuong thức lấy dữ liệu cuar một nhân khẩu dựa trên soCccd từ CSDL
     * @param soCccd số CCCD của nhân khẩu
     * @return trả về nhân khẩu có số CCCD đó
     * @throws SQLException xảy ra lỗi
     */
    public static Optional<NhanKhau> getNhanKhau(String soCccd) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM nhankhau where soCccd= ?");
        statement.setString(1, soCccd);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            ThongTinNhanKhau thongTinNhanKhau = SQLUtils.get(resultSet);
            NhanKhau nhanKhau = new NhanKhau(thongTinNhanKhau);
            return Optional.of(nhanKhau);
        }
        return  Optional.empty();
    }

    /**
     * Phương thức static thực hiện chuyển kiểu LocalDateTime sang String định dạng yyyy-MM-dd
     * @param lct giá trị LocalDateTime đưa vào
     * @return chuỗi định dạng yyyy-MM-dd
     */
    public static String LocalDateTimetoDateString(LocalDateTime lct) {
        // Định dạng ngày tháng năm
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Chuyển đổi thành chuỗi
        String formattedDate = lct.format(formatter);

        return formattedDate;
    }

    /**
     * Phương thức static thực hiện chuyển đổi kiểu int sang String
     * @param kieuInt giá trị số nguyên đưa vào
     * @return giá trị chuỗi biểu diễn số nguyên
     */
    public static String intToString(int kieuInt) {
        return ((Integer)kieuInt).toString();
    }

    /**
     * Phương thức static kiểm tra thông tin tài khoản đăng ký
     * @param soCccdDK số CCCD của người đăng ký
     * @param hoTenDK họ tên người đăng ký
     * @param ngaySinhDK ngày sinh của người đăng ký
     * @return true/false Kiem tra thong tin cua nguoi dang ky
     */
    public static Boolean kiemTraThongTinDangKy(String soCccdDK, String hoTenDK, String ngaySinhDK) throws SQLException {

        // Lấy thông tin từ cơ sở dữ liệu đối chiếu
        NhanKhau nhankhau = new NhanKhau();
        Optional<NhanKhau> selectNK = functionHelp.getNhanKhau(soCccdDK);

        if(selectNK.isPresent()) {
            nhankhau = selectNK.get();

            // kiem tra truong hoTen
            if(!nhankhau.getThongTinNhanKhau().getHoTen().equals(hoTenDK)) {
                return false;
            }

            // kiem tra truong ngaySinh
            LocalDateTime lct = nhankhau.getThongTinNhanKhau().getNgaySinh();
            if(!functionHelp.LocalDateTimetoDateString(lct).equals(ngaySinhDK)) {
                return false;
            }

        } else {
            return false;
        }

        return true;
    }

    /**
     * Phương thức thực hiện ẩn/hiện trường nhập mật khẩu
     * @param e sự kiện click chuọt checkbox
     * @param cb checkbox lựa chọn ẩn/hiện
     * @param pw TextField hiển thị mật khẩu
     * @param pwHidden PasswordField ẩn mật khẩu
     */
    public static  void changeVisibilityPassword(ActionEvent e, CheckBox cb, TextField pw, PasswordField pwHidden) {
        if (cb.isSelected()) {
            pw.setText(pwHidden.getText());
            pw.setVisible(true);
            pwHidden.setVisible(false);
            return;
        }
        pwHidden.setText(pw.getText());
        pwHidden.setVisible(true);
        pw.setVisible(false);
    }

    /**
     * Phương thứuc lấy mật khẩu từ password/passwordHidden
     * @param password TextField chứa mật khẩu dạng Text
     * @param passwordHidden PasswordField chứa mật khẩu dạng Hidden
     * @return
     */
    public static String layMatKhau(CheckBox cb, TextField password, PasswordField passwordHidden) {
        if (cb.isSelected()) {
            return password.getText();
        }
        return passwordHidden.getText();
    }
}
