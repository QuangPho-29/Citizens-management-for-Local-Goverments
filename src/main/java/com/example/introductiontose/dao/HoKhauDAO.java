package com.example.introductiontose.dao;

import com.example.introductiontose.model.HoKhau;
import com.example.introductiontose.model.key.HoKhauKey;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class HoKhauDao triển khai interface DataAccessObject để thao tác với đối tượng HoKhau trong cơ sở dữ liệu.
 */

public class HoKhauDAO implements DataAccessObject<HoKhau, HoKhauKey> {
    
    private final Connection connection;
    
    /**
     * Khởi tạo một đối tượng HoKhauDao với kết nối cơ sở dữ liệu được cung cấp.
     *
     * @param connection Kết nối đến cơ sở dữ liệu.
     */
    public HoKhauDAO(Connection connection) {
        this.connection = connection;
    }
    
    /**
     * * {@inheritDoc}
     */
    @Override
    public List<HoKhau> getAll() throws SQLException {
        List<HoKhau> danhSachHoKhau = new ArrayList<>();
        String sql = "SELECT * FROM hokhau";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            HoKhau hoKhau = new HoKhau(
                    resultSet.getInt("idHoKhau"),
                    resultSet.getString("tenChuHo"),
                    resultSet.getString("soCccdChuHo"),
                    resultSet.getString("diaChiNha"),
                    resultSet.getTimestamp("ngayTaoHK").toLocalDateTime());
            danhSachHoKhau.add(hoKhau);
        }
        return danhSachHoKhau;
    }
    
    /**
     * * {@inheritDoc}
     */
    @Override
    public Optional<HoKhau> get(HoKhauKey key) throws SQLException {
        String sql = "SELECT * FROM hokhau WHERE idHoKhau = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, key.getIdHoKhau());
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            HoKhau hoKhau = new HoKhau(
                    resultSet.getInt("idHoKhau"),
                    resultSet.getString("tenChuHo"),
                    resultSet.getString("soCccdChuHo"),
                    resultSet.getString("diaChiNha"),
                    resultSet.getTimestamp("ngayTaoHK").toLocalDateTime());
            return Optional.of(hoKhau);
        }
        return Optional.empty();
    }
    
    /**
     * * {@inheritDoc}
     */
    @Override
    public void save(@NotNull HoKhau hoKhau) throws SQLException {
        String sql = "INSERT INTO hokhau (idHoKhau, tenChuHo, soCccdChuHo, diaChiNha, ngayTaoHK) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, hoKhau.getIdHoKhau());
        statement.setString(2, hoKhau.getTenChuHo());
        statement.setString(3, hoKhau.getSoCccdChuHo());
        statement.setString(4, hoKhau.getDiaChiNha());
        statement.setObject(5, Timestamp.valueOf(hoKhau.getNgayTaoHK()));
        statement.executeUpdate();
    }
    
    /**
     * * {@inheritDoc}
     */
    @Override
    public void update(@NotNull HoKhau hoKhau) throws SQLException {
        String sql = "UPDATE hokhau SET tenChuHo = ?, diaChiNha = ?, ngayTaoHK = ?, soCccdChuHo = ? WHERE idHoKhau = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(5, hoKhau.getIdHoKhau());
        statement.setString(1, hoKhau.getTenChuHo());
        statement.setString(4, hoKhau.getSoCccdChuHo());
        statement.setString(2, hoKhau.getDiaChiNha());
        statement.setObject(3, Timestamp.valueOf(hoKhau.getNgayTaoHK()));
        statement.executeUpdate();
    }
    
    /**
     * * {@inheritDoc}
     */
    @Override
    public void delete(@NotNull HoKhau hoKhau) throws SQLException {
        String sql = "DELETE FROM hokhau WHERE idHoKhau = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, hoKhau.getIdHoKhau());
        statement.executeUpdate();
    }

    public int getSoHoKhau() throws SQLException {
        String sql = "SELECT COUNT(idHoKhau) AS soHoKhau FROM hokhau";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        if (resultSet.next()) {
            return resultSet.getInt("soHoKhau");
        } else {
            // Xử lý trường hợp không có kết quả
            return 0;
        }
    }
}
