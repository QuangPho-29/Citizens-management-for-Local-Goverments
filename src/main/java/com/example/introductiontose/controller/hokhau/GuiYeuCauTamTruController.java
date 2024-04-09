package com.example.introductiontose.controller.hokhau;

import com.example.introductiontose.dao.TamTruDAO;
import com.example.introductiontose.database.SqlConnection;
import com.example.introductiontose.model.TamTru;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Duration;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class GuiYeuCauTamTruController implements Initializable {
    @FXML
    private Button sendRequest;
    @FXML
    private DatePicker dateBegin, dateEnd, ngayCap, ngaySinh;
    @FXML
    private TextField lyDo, Name, soCCCD, soDienThoai, biDanh, ngheNghiep, danToc, noiCap, queQuan, noiLamViec, quanHeVoiChuHo, choOHienNay, tonGiao;
    @FXML
    private CheckBox checkBox;
    @FXML
    private Label errorLabel;
    @FXML
    ComboBox<String> Sex;


    @FXML
    public void handleConfirm() {
        errorLabel.setText("");
        String lyDoString = lyDo.getText();
        String hoTenString = Name.getText();
        String soCCCDString = soCCCD.getText();
        String danTocString = danToc.getText();
        String tonGiaoString = tonGiao.getText();
        String noiCapString = noiCap.getText();
        String queQuanString = queQuan.getText();
        String noiLamViecString = noiLamViec.getText();
        String biDanhString = biDanh.getText();
        String soDienThoaiString = soDienThoai.getText();
        String ngheNghiepString = ngheNghiep.getText();
        String quanHeString = quanHeVoiChuHo.getText();
        String choOHienNayString = choOHienNay.getText();
        LocalDate selectedNgayCap = ngayCap.getValue();
        LocalDate selectedNgaySinh = ngaySinh.getValue();
        LocalDate selectedDateBegin = dateBegin.getValue();
        LocalDate selectedDateEnd = dateEnd.getValue();
        // Clear previous error messages
        String sexString = Sex.getValue();

        // Validate input fields
        if(hoTenString == null){
            errorLabel.setText("Chưa nhập họ tên!");
            return;
        }
        if(sexString == null){
            errorLabel.setText("Chưa chọn giới tính!");
            return;
        }
        if(soCCCDString == null){
            errorLabel.setText("Chưa nhập số CCCD");
            return;
        }
        if(!isNumeric(soCCCDString)){
            errorLabel.setText("Số CCCD sai");
            return;
        }
        if(danTocString == null){
            errorLabel.setText("Chưa điền dân tộc!");
            return;
        }
        if(tonGiaoString == null){
            errorLabel.setText("Chưa điền tôn giáo!");
            return;
        }
        if(noiCapString == null){
            errorLabel.setText("Chưa điền nơi cấp");
            return;
        }
        if(selectedNgayCap == null){
            errorLabel.setText("Chưa điền ngày cấp");
            return;
        }
        if(selectedNgaySinh == null){
            errorLabel.setText("Chưa điền ngày sinh");
            return;
        }
        if(soDienThoaiString == null){
            errorLabel.setText("Chưa điền số điện thoại");
            return;
        }
        if(!isNumeric(soDienThoaiString)){
            errorLabel.setText("Số điện thoại phải từ 0-9");
            return;
        }
        if(biDanhString == null){
            errorLabel.setText("Chưa nhập bí danh");
            return;
        }

        if(queQuanString == null){
            errorLabel.setText("Chưa nhập quê quán");
            return;
        }
        if(ngheNghiepString == null){
            errorLabel.setText("Chưa nhập nghề nghiệp");
            return;
        }
        if(noiLamViecString == null){
            errorLabel.setText("Chưa nhập nơi làm việc");
            return;
        }

        if(selectedDateBegin == null) {
            errorLabel.setText("Ngày bắt đầu trống!");
            return;
        }
        if(selectedDateEnd == null) {
            errorLabel.setText("Ngày kết thúc trống!");
            return;
        }
        if(quanHeString == null){
            errorLabel.setText("Chưa điền quan hệ");
            return;
        }

        if(lyDoString.isEmpty()) {
            errorLabel.setText("Lý do trống!");
            return;
        }

        if (selectedDateBegin.isAfter(selectedDateEnd) || selectedDateBegin.isEqual(selectedDateEnd)) {
            errorLabel.setText("Ngày không hợp lệ!");
            return;
        }

        if(choOHienNayString == null){
            errorLabel.setText("Chưa nhập chỗ ở hiện nay");
            return;
        }
        System.out.println(hoTenString + biDanhString +lyDoString + choOHienNayString + soCCCDString );
        // Handle checkbox selection
        if (checkBox.isSelected()) {
            Connection connection = SqlConnection.connect();
            TamTruDAO tamtruDAO = new TamTruDAO(connection);
            System.out.println("handleSelectedCheckBox\n");
            TamTru tamtru = new TamTru(soCCCDString, "0327327627844", hoTenString,biDanhString,sexString, queQuanString, danTocString, tonGiaoString, ngheNghiepString, noiLamViecString, noiCapString, quanHeString, lyDoString, selectedNgaySinh, selectedNgayCap, selectedDateEnd, selectedDateBegin, "chờ xác nhận", soDienThoaiString );
            tamtru.setNoiDangKyTamTru(choOHienNayString);
            tamtruDAO.save(tamtru);
            SqlConnection.close(connection);
        } else {
            animateCheckbox();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize gender ComboBox
        Sex.setItems(FXCollections.observableArrayList("Nam", "Nữ"));
    }

    // Other methods...

    private void animateCheckbox() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(checkBox.opacityProperty(), 1.0)),
                new KeyFrame(Duration.seconds(0.1), new KeyValue(checkBox.opacityProperty(), 0.3)),
                new KeyFrame(Duration.seconds(0.2), new KeyValue(checkBox.opacityProperty(), 1.0)),
                new KeyFrame(Duration.seconds(0.3), new KeyValue(checkBox.opacityProperty(), 0.3)),
                new KeyFrame(Duration.seconds(0.4), new KeyValue(checkBox.opacityProperty(), 1.0))
        );
        timeline.play();
    };

    // Helper methods...
    private boolean isNumeric(@NotNull String str) {
        if (!str.matches("\\d+")) {
            return false;
        }
        return true;
    }
}
