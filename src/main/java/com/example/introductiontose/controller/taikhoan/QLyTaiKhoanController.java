package com.example.introductiontose.controller.taikhoan;

import com.example.introductiontose.dao.DataAccessObject;
import com.example.introductiontose.dao.NhanKhauDAO;
import com.example.introductiontose.dao.TaiKhoanNhanKhauDAO;
import com.example.introductiontose.database.SqlConnection;
import com.example.introductiontose.model.NhanKhau;
import com.example.introductiontose.model.TaiKhoanNhanKhau;
import com.example.introductiontose.view.icon.IconUtils;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class QLyTaiKhoanController implements Initializable {
    @FXML
    private VBox vBoxTaiKhoan;
    @FXML
    private TextField searchTextField;
    
    private List<TaiKhoanController> controllers;
    
    @FXML
    private void search(ActionEvent event) {
        Platform.runLater(this::_search);
    }
    
    @FXML
    private void pressKey(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            Platform.runLater(this::_search);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        launch();
    }
    
    private void launch() {
        List<TaiKhoanNhanKhau> taiKhoanList = getListFromDatabase();
        controllers = new ArrayList<>();
        if (taiKhoanList == null) return;
        
        Connection connection = SqlConnection.connect();
        DataAccessObject<NhanKhau, String> accessObject = new NhanKhauDAO(connection, NhanKhauDAO.TableType.NHANKHAU);
        
        vBoxTaiKhoan.getChildren().clear();
        boolean isOdd = true;
        
        for (TaiKhoanNhanKhau taiKhoan : taiKhoanList) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(IconUtils.class.getResource("/com/example/introductiontose/view/qltaikhoan/row-tk.fxml"));
                HBox row = fxmlLoader.load();
                row.getStyleClass().add(isOdd ? "hbox-odd" : "hbox-even");
                
                // get controller
                TaiKhoanController controller = fxmlLoader.getController();
                accessObject.get(taiKhoan.getSoCCCD()).ifPresent(nhanKhau -> {
                    controller.setData(taiKhoan, nhanKhau);
                    controllers.add(controller);
                });
                
                vBoxTaiKhoan.getChildren().add(row);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            
            isOdd = !isOdd;
        }
        
        SqlConnection.close(connection);
    }
    
    private List<TaiKhoanNhanKhau> getListFromDatabase() {
        try {
            Connection connection = SqlConnection.connect();
            DataAccessObject<TaiKhoanNhanKhau, String> accessObject = new TaiKhoanNhanKhauDAO(connection);
            List<TaiKhoanNhanKhau> result = accessObject.getAll();
            SqlConnection.close(connection);
            return result;
        } catch (SQLException e) {
            // lỗi kết nối
        }
        return null;
    }
    
    private void _search() {
        String s = searchTextField.getText();
        if (s.isEmpty()) {
            launch();
            return;
        }
        
        vBoxTaiKhoan.getChildren().clear();
        boolean isOdd = true;
        
        for (TaiKhoanController controller : controllers) {
            if (controller.getSoCccd().equals(s)) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(IconUtils.class.getResource("/com/example/introductiontose/view/qltaikhoan/row-tk.fxml"));
                    HBox row = fxmlLoader.load();
                    row.getStyleClass().add(isOdd ? "hbox-odd" : "hbox-even");
                    
                    // get controller
                    TaiKhoanController taiKhoanController = fxmlLoader.getController();
                    taiKhoanController.setData(controller.getTaiKhoan(), controller.getNhanKhau());
                    
                    vBoxTaiKhoan.getChildren().add(row);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                
                isOdd = !isOdd;
            }
        }
    }
}
