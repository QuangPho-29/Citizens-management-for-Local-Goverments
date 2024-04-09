package com.example.introductiontose.controller.hokhau;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class User_YeuCauNhanKhauController {
    @FXML
    AnchorPane anchorPane;
    @FXML
    HBox TamVangHBox, TamTruHBox;

    @FXML
    public void setTamVangAction() throws IOException {
        // Load the FXML file
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/introductiontose/view/user/YeuCauTamVang.fxml"));
        AnchorPane loadedPane = fxmlLoader.load();

        // Clear existing children and add the loaded FXML content
        anchorPane.getChildren().setAll(loadedPane);
    }
    @FXML
    public void setTamTruAction() throws IOException {
        // Load the FXML file
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/introductiontose/view/user/Tamtru.fxml"));
        AnchorPane loadedPane = fxmlLoader.load();

        // Clear existing children and add the loaded FXML content
        anchorPane.getChildren().setAll(loadedPane);
    }


}
