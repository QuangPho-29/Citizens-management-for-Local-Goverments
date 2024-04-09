package com.example.introductiontose.controller.taoKhoanPhi;

import com.example.introductiontose.database.SqlConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class TaoKhoanPhi implements Initializable {

    public static Connection connection = SqlConnection.connect();

    @FXML
    private RadioButton batBuoc;

    @FXML
    private DatePicker dateEnd;

    @FXML
    private DatePicker dateStart;

    @FXML
    private ToggleGroup loaiPhi;

    @FXML
    private TextField mucPhi;

    @FXML
    private TextArea noidung;

    @FXML
    private AnchorPane tieuDeSection;

    @FXML
    private TextField tieude;

    @FXML
    private RadioButton tuNguyen;

    @FXML
    private Spinner<Integer> hourEnd;

    @FXML
    private Spinner<Integer> hourStart;

    @FXML
    private Spinner<Integer> minEnd;

    @FXML
    private Spinner<Integer> minStart;

    @FXML
    private Text loiKhoanPhi;
    @FXML
    private Text loiMucPhi;

    @FXML
    private ToggleGroup kieuPhiBatBuoc;
    @FXML
    private VBox vboxKieuPhiBatBuoc;
    @FXML
    private RadioButton theoDauNguoi;

    @FXML
    private RadioButton theoHoGiaDinh;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateStart.setEditable(false);
        dateEnd.setEditable(false);
        vboxKieuPhiBatBuoc.setVisible(false);
        // Cài đặt khoảng giá trọ cho các spinner giờ và phút
        setPropertyForTime();

        // Cài đặt tác động của radio button đến text mức phí
        tacDongCuaRadioToiMucPhi();

        mucPhi.setOnKeyTyped(event -> {
            if(batBuoc.isSelected()) {
                char character = event.getCharacter().charAt(0);
                if (!Character.isDigit(character) && character != 8 && character != 13) {
                    loiMucPhi.setText("Chỉ được nhập ký tự số!");

                } else {
                    loiMucPhi.setText("");
                }
            }
        });

    }

    /**
     * Phương thức cài đặt giá trị cho các Spinner giờ - phút
     */
    public void setPropertyForTime() {
        SpinnerValueFactory<Integer> valueFactoryHourStart =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23);
        SpinnerValueFactory<Integer> valueFactoryHourEnd =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23);
        SpinnerValueFactory<Integer> valueFactoryMinusStart =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59);
        SpinnerValueFactory<Integer> valueFactoryMinusEnd =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59);

        hourStart.setValueFactory(valueFactoryHourStart);
        hourEnd.setValueFactory(valueFactoryHourEnd);
        minStart.setValueFactory(valueFactoryMinusStart);
        minEnd.setValueFactory(valueFactoryMinusEnd);

        hourStart.setEditable(true);
        hourEnd.setEditable(true);
        minStart.setEditable(true);
        minEnd.setEditable(true);
    }

    /**
     * Phương thức xác định nếu chọn radio button tuNguyen thì ko cho nhập và xóa mức phí
     * nếu chọn radio button batBuoc thì cho phép nhập mức phi
     */
    public void tacDongCuaRadioToiMucPhi() {
        loaiPhi.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (loaiPhi.getSelectedToggle() != null) {
                RadioButton sl = (RadioButton) loaiPhi.getSelectedToggle();
                if(sl.equals(tuNguyen)) {
                    mucPhi.setText("");
                    loiMucPhi.setText("");
                    mucPhi.setEditable(false);
                    vboxKieuPhiBatBuoc.setVisible(false);
                }else {
                    mucPhi.setEditable(true);
                    vboxKieuPhiBatBuoc.setVisible(true);
                }
            }
        });
    }

    /**
     * Phương thức xử lý tạo khỏan phí mới khi click vào button "Tạo khỏan phí"
     * @param event sự kiện click button "Tạo khoản phí"
     * @throws SQLException lỗi truy vấn dữ liệu
     */
    public void taoKhoanPhi(ActionEvent event) throws SQLException {
        String error = datLoiKhoanPhi();
        if(error != null) {
            loiKhoanPhi.setText(error);
            return;
        }

        // Lấy các giá trị của khoản phí
        String tieudeKP = tieude.getText();
        String noidungKP = noidung.getText();
        LocalDateTime timeStart = layThoiGian(dateStart, hourStart, minStart);
        LocalDateTime timeEnd = layThoiGian(dateEnd, hourEnd, minEnd);

        // Kiểm tra thời gian bắt đầu phải trước thời gain kết thúc
        if(!timeStart.isBefore(timeEnd)) {
            loiKhoanPhi.setText("Lỗi! Thời điểm bắt đầu phải nhỏ hơn thời điểm kết thúc!");
            return;
        }

        // Xử lý đối với khoản phí tự nguyện
        if(tuNguyen.isSelected()) {
            luuKhoanPhi("đóng góp", noidungKP, 0, timeStart, timeEnd, tieudeKP);
        }
        // Xử lý đối với khoản phí bắt buộc
        if(batBuoc.isSelected()) {
            // kiểm tra thông tin mức phí
            String mucphiText = mucPhi.getText();
            for (int i = 0; i < mucphiText.length(); i++) {
                if (!Character.isDigit(mucphiText.charAt(i))) {
                    loiMucPhi.setText("mức phí chỉ được chứa các ký tự 0-9!");
                    loiKhoanPhi.setText("");
                    return;
                }
            }
            Integer mucphiKP = Integer.parseInt(mucPhi.getText());
            if(theoDauNguoi.isSelected()) {
                luuKhoanPhi("bắt buộc/người", noidungKP, mucphiKP, timeStart, timeEnd, tieudeKP);
            } else {
                luuKhoanPhi("bắt buộc", noidungKP, mucphiKP, timeStart, timeEnd, tieudeKP);
            }

        }

        // Hiển thị thông báo
        loiKhoanPhi.setText("");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Tạo khoản phí thành công!");
        alert.setTitle("Tạo khoản phí thành công");
        alert.show();
    }

    /**
     * Phương thức cài đặt lỗi để trống các trường
     */
    public String datLoiKhoanPhi() {
        if((!tuNguyen.isSelected()) && (!batBuoc.isSelected())) {
            return("Chưa chọn kiểu của khoản phí!");
        }
        if(batBuoc.isSelected() && ((!theoDauNguoi.isSelected()) && (!theoHoGiaDinh.isSelected()))) {
            return("Chưa chọn kiểu phí của khoản phí bắt buộc!");
        }
        if(tieude.getText().isEmpty()) {
            return("Tiêu đề khoản phí chưa được nhập!");
        }
        if(noidung.getText().isEmpty()) {
            return("Nội dung khoản phí chưa được nhập!");
        }
        if(batBuoc.isSelected() && mucPhi.getText().isEmpty()) {
            return("Mức phí chưa được nhập!");
        }
        if(dateStart.getValue() == null || dateEnd.getValue() == null) {
            return("Thời gian chưa được nhập!");
        }
        return(null);
    }

    /**
     * Phương thức lấy thời gian từ các trường ngày, giừo, phút
     * @param ngay DatePicker chứa ngày
     * @param gio Spinner chứa giờ
     * @param phut Spinner chứa phút
     * @return LocalDateTime chứa giá trị date+time tương ứng
     */
    public LocalDateTime layThoiGian(DatePicker ngay, Spinner<Integer> gio, Spinner<Integer> phut) {
        Integer hour = gio.getValue();
        Integer minutes = phut.getValue();
        LocalDateTime localDateTime = LocalDateTime.of(
                ngay.getValue(),
                LocalTime.of(hour, minutes)
        );
        return localDateTime;
    }

    public void luuKhoanPhi(String kieuphi, String noidung, Integer mucphi,
                            LocalDateTime ngaytao, LocalDateTime ngayketthuc, String tieude) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO khoanphi" +
                "(kieuPhi, noiDungThuPhi, mucPhi, ngayTao, ngayKetThuc, tieuDeKhoanPhi) " +
                "VALUES (?, ?, ?, ?, ?, ?)");
        statement.setString(1, kieuphi);
        statement.setString(2, noidung);
        statement.setInt(3, mucphi);
        statement.setTimestamp(4, Timestamp.valueOf(ngaytao));
        statement.setTimestamp(5, Timestamp.valueOf(ngayketthuc));
        statement.setString(6, tieude);
        statement.executeUpdate();
    }
}
