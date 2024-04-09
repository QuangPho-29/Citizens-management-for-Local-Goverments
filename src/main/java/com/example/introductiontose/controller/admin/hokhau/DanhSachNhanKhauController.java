package com.example.introductiontose.controller.admin.hokhau;

import com.example.introductiontose.controller.node.DanhSach;
import com.example.introductiontose.model.NhanKhau;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

/**
 * Controller quản lý hiển thị danh sách nhân khẩu trong giao diện admin.
 *
 * <p>Controller này điều khiển hiển thị danh sách nhân khẩu thông qua JavaFX.
 * Cung cấp khả năng hiển thị danh sách nhân khẩu trong một Pane.</p>
 *
 * <p>Controller này cũng có chức năng chuyển đổi giữa các Scene và Stage trong ứng dụng.</p>
 *
 * @author Duy
 * @version 1.0
 */
public class DanhSachNhanKhauController {
    @FXML
    private Button title;
    @FXML
    private Pane paneContent;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox mainVBox;
    private Scene prevScene;
    private Stage stage;
    
    /**
     * Phương thức thiết lập tiêu đề cho danh sách nhân khẩu.
     *
     * @param title Tiêu đề cần được hiển thị.
     */
    public void setTitle(String title) {
        this.title.setText(title);
    }
    
    /**
     * Phương thức thiết lập Scene trước đó mà controller sẽ quay lại khi nút "Quay lại" được nhấn.
     *
     * @param prevScene Scene trước đó cần quay lại.
     */
    public void setPrevScene(Scene prevScene) {
        this.prevScene = prevScene;
    }
    
    /**
     * Phương thức thiết lập Stage mà controller đang quản lý.
     *
     * @param stage Stage của controller.
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    /**
     * Phương thức xử lý sự kiện khi nút "Quay lại" được nhấn.
     * Chuyển đổi về Scene trước đó.
     *
     * @param event Sự kiện nhấn nút "Quay lại".
     */
    @FXML
    private void goBack(ActionEvent event) {
        stage.setScene(prevScene);
    }
    
    /**
     * Phương thức khởi chạy hiển thị danh sách nhân khẩu.
     *
     * @param nhanKhauList Danh sách nhân khẩu cần hiển thị.
     */
    public void launch(List<NhanKhau> nhanKhauList) {
        DanhSach danhSach = new DanhSach(prevScene);
        danhSach.launch(nhanKhauList);
        this.paneContent.getChildren().clear();
        this.paneContent.getChildren().add(danhSach);
    }
}
