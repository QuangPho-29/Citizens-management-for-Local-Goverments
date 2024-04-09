package com.example.introductiontose.dao;

import com.example.introductiontose.model.ThongTinTDP;
import org.jetbrains.annotations.NotNull;

import java.sql.*;

public class ThongTinTDPDAO {

    private final Connection connection;
    private final String tableName;
    private final String tenTDP;

    /**
     * Khởi tạo một đối tượng ThongTinTDPDao với kết nối cơ sở dữ liệu được cung cấp.
     *
     * @param connection Kết nối đến cơ sở dữ liệu.
     */
    public ThongTinTDPDAO(Connection connection) {
        this.connection = connection;
        this.tableName = "thongtintodanpho"; // Tên bảng trong cơ sở dữ liệu
        this.tenTDP = "CNPM";
    }

    public ThongTinTDP get() throws SQLException {
        String sql = "SELECT * FROM " + tableName;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String ten = resultSet.getString("ten");
                    double thuPhi = resultSet.getDouble("thuPhi");
                    double chuaDong = resultSet.getDouble("chuaDong");
                    double dongGop = resultSet.getDouble("dongGop");
                    int soHoKhau = resultSet.getInt("soHoKhau");
                    int soNhanKhau = resultSet.getInt("soNhanKhau");

                    return new ThongTinTDP(ten, thuPhi, chuaDong, dongGop, soHoKhau, soNhanKhau);
                }
            }
        }
        return null;
    }

    public void themTien(String column, int money) throws SQLException {
        String sql = "UPDATE " + tableName + "SET " + column + " = " + column + " + ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, money);
            statement.executeUpdate();
        }
    }
}
