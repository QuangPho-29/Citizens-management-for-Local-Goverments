package com.example.introductiontose.controller.hokhau;


import com.example.introductiontose.dao.TamVangDAO;
import com.example.introductiontose.database.SqlConnection;
import com.example.introductiontose.model.TamVang;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Duration;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author hokta
 */
public class GuiYeuCauTamVangController {
    @FXML
    private Button sendRequest;
    @FXML
    private DatePicker DateBegin;
    @FXML
    private DatePicker DateEnd;
    @FXML
    private TextField lyDo;
    @FXML
    private TextField noiDangKyTamTru;
    @FXML
    private CheckBox check;
    @FXML
    private Label Hhhh;

    public GuiYeuCauTamVangController() {

    }


    @FXML
    public void handleConfirm() throws SQLException {
        String textReason = lyDo.getText();
        String textLocation = noiDangKyTamTru.getText();
        LocalDate selectedDateBegin = DateBegin.getValue();
        LocalDate selectedDateEnd = DateEnd.getValue();

        if(selectedDateBegin == null) {
            Hhhh.setText("Ngày bắt đầu trống!");
            return;
        }
        if(selectedDateEnd == null) {
            Hhhh.setText("Ngày kết thúc trống!");
            return;
        }
        if(textLocation.isEmpty()) {
            Hhhh.setText("Nơi đăng kí trống!");
            return;
        }
        if(textReason.isEmpty()) {
            Hhhh.setText("Lý do trống!");
            return;
        }
        if (selectedDateBegin.isAfter(selectedDateEnd) || selectedDateBegin.isEqual(selectedDateEnd)) {
            Hhhh.setText("Ngày không hợp lệ!");
            return;
        }

        boolean check_select = check.isSelected();
        if(check_select){
            Connection connection = SqlConnection.connect();
            TamVangDAO tmp = new TamVangDAO(connection);
            TamVang tamvang = new TamVang(0, "03720300XXXX", selectedDateBegin, selectedDateEnd, textReason, textLocation, "chờ xác nhận");
            tmp.save(tamvang);

//            Stage stage = new Stage();
//            Parent root = FXMLLoader.load(getClass().getResource("/FXML/Done.fxml"));
//
//            Scene scene = new Scene(root);
//
//            stage.setScene(scene);
//            stage.setResizable(false);
//            stage.show();
        }
        else{
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(check.opacityProperty(), 1.0)),
                    new KeyFrame(Duration.seconds(0.1), new KeyValue(check.opacityProperty(), 0.3)),
                    new KeyFrame(Duration.seconds(0.2), new KeyValue(check.opacityProperty(), 1.0)),
                    new KeyFrame(Duration.seconds(0.3), new KeyValue(check.opacityProperty(), 0.3)),
                    new KeyFrame(Duration.seconds(0.4), new KeyValue(check.opacityProperty(), 1.0))
            );

            timeline.play();
        }

    }

}
