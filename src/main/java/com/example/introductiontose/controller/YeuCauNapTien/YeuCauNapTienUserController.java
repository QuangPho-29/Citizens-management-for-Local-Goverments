package com.example.introductiontose.controller.YeuCauNapTien;

import com.example.introductiontose.dao.NapTienDao;
import com.example.introductiontose.dao.NhanKhauDAO;
import com.example.introductiontose.database.SqlConnection;
import com.example.introductiontose.model.NhanKhau;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class YeuCauNapTienUserController implements Initializable {
    public static String CCCD = "001075000006";
    Connection connection = SqlConnection.connect();
    @FXML
    private TextField onNhapTien;
    @FXML
    private TextField ghiChu;
    @FXML
    private Label ktra;

    public static int countHoKhau(int idHK) throws SQLException {
        int number = 0;
        Connection connection1 = SqlConnection.connect();
        PreparedStatement statement = connection1.prepareStatement("select count(*) as numberNK from nhankhau where idHoKhau = ?");
        statement.setInt(1, idHK);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            number = resultSet.getInt("numberNK");
        }
        return number;
    }

    public static int countHoKhau(String soCccd) throws SQLException {
        Connection connection1 = SqlConnection.connect();
        int number = 0;
        NhanKhauDAO nhanKhauDao = new NhanKhauDAO(connection1, NhanKhauDAO.TableType.NHANKHAU);
        try {
            Optional<NhanKhau> resultNK = nhanKhauDao.get(CCCD);
            if (resultNK.isPresent()) {
                NhanKhau nhankhau = resultNK.get();
                PreparedStatement statement = connection1.prepareStatement("select count(*) as numberNK from nhankhau where idHoKhau = ?");
                statement.setInt(1, nhankhau.getThongTinNhanKhau().getIdHoKhau());
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    number = resultSet.getInt("numberNK");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return number;
    }

    public void naptien(ActionEvent event) throws IOException, SQLException {
        if (onNhapTien.getText().isEmpty()) {
            ktra.setText("ban chua nhap sao tien");

        } else if (onNhapTien.getText().equals("0")) {
            ktra.setText("So tien >0");
        } else {
            NapTienDao naptiendao = new NapTienDao(connection);
            naptiendao.save(CCCD, Integer.parseInt(onNhapTien.getText()), ghiChu.getText());

        }


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        NhanKhauDAO nhanKhauDao = new NhanKhauDAO(connection, NhanKhauDAO.TableType.NHANKHAU);
        try {
            Optional<NhanKhau> resultNK = nhanKhauDao.get(CCCD);
            if (resultNK.isPresent()) {
                NhanKhau nhankhau = resultNK.get();
                System.out.println(nhankhau.getThongTinNhanKhau().getIdHoKhau());
                System.out.println(countHoKhau(nhankhau.getThongTinNhanKhau().getIdHoKhau()));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
