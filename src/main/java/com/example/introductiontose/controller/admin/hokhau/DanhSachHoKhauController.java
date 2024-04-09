package com.example.introductiontose.controller.admin.hokhau;

import com.example.introductiontose.controller.node.DanhSach;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller quản lý danh sách hộ khẩu trong giao diện admin.
 *
 * <p>Controller này điều khiển hiển thị danh sách hộ khẩu thông qua JavaFX.
 * Cung cấp khả năng hiển thị danh sách hộ khẩu trong một AnchorPane.</p>
 *
 * @author Duy
 * @version 1.0
 */
public class DanhSachHoKhauController implements Initializable {
    @FXML
    private AnchorPane centerAnchorPane;
    @FXML
    private Pane paneContent;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox mainVBox;
    
    /**
     * Phương thức được gọi khi controller được khởi tạo.
     *
     * @param url            Đối tượng URL đại diện cho đường dẫn tới controller.
     * @param resourceBundle Đối tượng ResourceBundle đại diện cho tài nguyên của controller.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        launch();
    }
    
    /**
     * Phương thức khởi chạy hiển thị danh sách hộ khẩu.
     * Tạo một đối tượng DanhSach và thêm nó vào paneContent để hiển thị.
     */
    public void launch() {
        DanhSach danhSach = new DanhSach();
        danhSach.launch();
        this.paneContent.getChildren().clear();
        this.paneContent.getChildren().add(danhSach);
    }
    
}
