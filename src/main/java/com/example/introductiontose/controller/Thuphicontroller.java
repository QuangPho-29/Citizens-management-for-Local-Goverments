package com.example.introductiontose.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;


import java.io.IOException;
import  java.net.URL;
import java.util.ResourceBundle;

public class Thuphicontroller implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ChoiceBox<String> mychoicebox;

    private String[] data={null,"Phí Bắt buộc", "Đóng góp"};



    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        mychoicebox= new ChoiceBox<>();
        mychoicebox.getItems().addAll(data);

        mychoicebox.setConverter(new StringConverter<String>() {
            @Override
            public String toString(String s) {
                return (s==null) ?"Box chưa được chọn":s;
            }

            @Override
            public String fromString(String s) {
                return null;
            }
        });
    }


    public void switchToScene1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Scence1.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    public void switchToScence4(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Scence4.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}
