package com.example.introductiontose.controller.dangkydangnhap;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.introductiontose.controller.dangkydangnhap.dangNhapController.tenTK;

public class trangChuController implements Initializable {

    @FXML
    private Text nameUser;

    public void signOut (ActionEvent e) throws IOException {
        Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/introductiontose/view/dangkydangnhap/dangNhap.fxml"));
        Parent dangxuat = loader.load();
        Scene scene = new Scene(dangxuat);
        stage.setScene(scene);
    }

//    public void display(String username) {
//        nameUser.setText(username);
//    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameUser.setText(tenTK);
    }
}
