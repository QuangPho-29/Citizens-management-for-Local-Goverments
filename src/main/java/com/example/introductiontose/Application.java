package com.example.introductiontose;

import com.example.introductiontose.controller.user.hokhau.DSNhanKhauController;
import com.example.introductiontose.dao.DataAccessObject;
import com.example.introductiontose.util.SQLUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Lớp Application là lớp chính của ứng dụng, thừa kế từ javafx.application.Application.
 * Điều này là lớp khởi động ứng dụng JavaFX và chứa phương thức start() để cấu hình giao diện đồ họa.
 */
public class Application extends javafx.application.Application {
    private double x = 0;
    private double y= 0;

    /**
     * Phương thức start() được gọi khi ứng dụng được khởi chạy.
     *
     * @param stage Stage là cửa sổ chính của ứng dụng.
     * @throws IOException Nếu có lỗi khi load file FXML.
     */
    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("/com/example/introductiontose/view/dangkydangnhap/dangNhap.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
//        stage.setScene(scene);
//        stage.initStyle(StageStyle.UNDECORATED);
//        scene.setFill(Color.TRANSPARENT);
//        stage.initStyle(StageStyle.TRANSPARENT);
//        stage.show();

        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("/com/example/introductiontose/view/dangkydangnhap/dangNhap.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);

        //fxmlLoader.setController(stage);


        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * Phương thức main() là điểm bắt đầu của ứng dụng.
     *
     * @param args Tham số dòng lệnh được truyền vào khi chạy ứng dụng.
     */
    public static void main(String[] args) {
        // Gọi phương thức launch() để bắt đầu ứng dụng
        launch();
    }
}
