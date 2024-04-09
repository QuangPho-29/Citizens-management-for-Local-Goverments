package com.example.introductiontose.controller.user.hokhau;

import com.example.introductiontose.controller.node.DanhSach;
import com.example.introductiontose.model.NhanKhau;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.List;

/**
 * Controller cho giao diện hiển thị danh sách nhân khẩu cho người dùng.
 *
 * <p>Controller này quản lý hiển thị danh sách nhân khẩu trong giao diện người dùng.</p>
 *
 * <p>Nó có khả năng đặt tiêu đề cho danh sách và hiển thị danh sách nhân khẩu sử dụng một {@link DanhSach}.</p>
 *
 * @author Duy
 * @version 1.0
 */
public class DSNhanKhauController {
    @FXML
    private Button title;
    @FXML
    private Pane paneContent;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox mainVBox;
    
    /**
     * Đặt tiêu đề cho danh sách nhân khẩu.
     *
     * @param title Tiêu đề cần đặt.
     */
    public void setTitle(String title) {
        this.title.setText(title);
    }
    
    /**
     * Hiển thị danh sách nhân khẩu.
     *
     * @param nhanKhauList Danh sách nhân khẩu cần hiển thị.
     */
    public void launch(List<NhanKhau> nhanKhauList) {
        DanhSach danhSach = new DanhSach();
        danhSach.launch(nhanKhauList);
        this.paneContent.getChildren().clear();
        this.paneContent.getChildren().add(danhSach);
    }
}
