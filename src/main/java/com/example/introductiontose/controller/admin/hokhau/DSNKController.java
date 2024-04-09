package com.example.introductiontose.controller.admin.hokhau;

import com.example.introductiontose.dao.DataAccessObject;
import com.example.introductiontose.dao.HoKhauDAO;
import com.example.introductiontose.database.SqlConnection;
import com.example.introductiontose.model.HoKhau;
import com.example.introductiontose.model.NhanKhau;
import com.example.introductiontose.model.key.HoKhauKey;
import com.example.introductiontose.util.SQLUtils;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class DSNKController implements Initializable {
    @FXML
    private VBox hoKhauVBox;
    
    @FXML
    private TextField keywordHoKhau;
    
    @FXML
    private TextField keywordNhanKhau;
    
    @FXML
    private Label name;
    
    @FXML
    private VBox nhanKhauVBox;
    
    private List<HoKhau> hoKhauList;
    private List<NhanKhau> nhanKhauList;
    
    @FXML
    void searchHoKhau(ActionEvent event) {
        Platform.runLater(() -> {
            String key = keywordHoKhau.getText();
            List<HoKhau> hoKhauSearch = key.isEmpty() ? this.hoKhauList : hoKhauList.stream()
                    .filter(hoKhau -> hoKhau.getSoCccdChuHo().contains(key))
                    .toList();
            createHoKhauList(hoKhauSearch);
        });
        
    }
    
    @FXML
    void searchNhanKhau(ActionEvent event) {
        Platform.runLater(() -> {
            String key = keywordNhanKhau.getText();
            List<NhanKhau> nhanKhauSearch = key.isEmpty() ? this.nhanKhauList : nhanKhauList.stream()
                    .filter(nhanKhau -> nhanKhau.getThongTinNhanKhau().getCccd().getSoCccd().contains(key))
                    .toList();
            createNhanKhauList(nhanKhauSearch);
        });
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Connection connection = SqlConnection.connect();
        DataAccessObject<HoKhau, HoKhauKey> accessObject = new HoKhauDAO(connection);
        
        try {
            this.hoKhauList = accessObject.getAll();
            SqlConnection.close(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        createHoKhauList(this.hoKhauList);
    }
    
    private void createHoKhauList(List<HoKhau> hoKhauList) {
        if (hoKhauList == null) return;
        
        hoKhauVBox.getChildren().clear();
        for (HoKhau hoKhau : hoKhauList) {
            try {
                List<NhanKhau> nhanKhauList = SQLUtils.getNhanKhauFromHoKhau(hoKhau.getIdHoKhau());
                NodeNK node = new NodeNK("Nhà ông " + hoKhau.getTenChuHo(),
                        "Số thành viên: " + nhanKhauList.size(),
                        hoKhau.getDiaChiNha());
                node.setOnMouseClicked(event -> {
                    name.setText("Nhân khẩu nhà ông " + hoKhau.getTenChuHo());
                    keywordNhanKhau.clear();
                    this.nhanKhauList = nhanKhauList;
                    createNhanKhauList(nhanKhauList);
                });
                hoKhauVBox.getChildren().add(node);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    
    private void createNhanKhauList(List<NhanKhau> nhanKhauList) {
        nhanKhauVBox.getChildren().clear();
        for (NhanKhau nhanKhau : nhanKhauList) {
            NodeNK node = new NodeNK(nhanKhau.getThongTinNhanKhau().getHoTen(),
                    "Số căn cước: " + nhanKhau.getThongTinNhanKhau().getCccd().getSoCccd(),
                    !nhanKhau.getThongTinNhanKhau().getQuanHe().isEmpty() ?
                            "Quan hệ với chủ hộ: " + nhanKhau.getThongTinNhanKhau().getQuanHe() :
                            "Chủ hộ");
            nhanKhauVBox.getChildren().add(node);
        }
    }
}
