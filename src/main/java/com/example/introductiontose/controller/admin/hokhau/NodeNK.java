package com.example.introductiontose.controller.admin.hokhau;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class NodeNK extends VBox {
    @FXML
    Label line1;
    @FXML
    Label line2;
    @FXML
    Label line3;
    
    public NodeNK(String line1, String line2, String line3) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/introductiontose/view/admin/hokhau/nodeHK.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        this.line1.setText(line1);
        this.line2.setText(line2);
        this.line3.setText(line3);
    }
}
