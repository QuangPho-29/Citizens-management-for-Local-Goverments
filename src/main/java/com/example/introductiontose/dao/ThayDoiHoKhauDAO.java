package com.example.introductiontose.dao;

import com.example.introductiontose.model.ThayDoiHoKhau;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class ThayDoiHoKhauDAO triển khai interface DataAccessObject để thao tác với đối tượng ThayDoiHoKhau trong cơ sở dữ liệu.
 */
public class ThayDoiHoKhauDAO implements DataAccessObject<ThayDoiHoKhau, Integer> {
    private final Connection connection;
    
    /**
     * Khởi tạo một đối tượng ThayDoiHoKhauDAO với kết nối cơ sở dữ liệu được cung cấp.
     *
     * @param connection Kết nối đến cơ sở dữ liệu.
     */
    public ThayDoiHoKhauDAO(Connection connection) {
        this.connection = connection;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ThayDoiHoKhau> getAll() throws SQLException {
        List<ThayDoiHoKhau> danhSachThayDoi = new ArrayList<>();
        String sql = "SELECT * FROM thaydoihokhau";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            ThayDoiHoKhau thayDoi = new ThayDoiHoKhau(
                    resultSet.getInt("idThayDoiHoKhau"),
                    resultSet.getInt("idHoKhau"),
                    resultSet.getString("trangThai"),
                    resultSet.getString("soCCCDChuHoMoi"),
                    resultSet.getString("noiDung"),
                    resultSet.getTimestamp("ngayThayDoi").toLocalDateTime());
            danhSachThayDoi.add(thayDoi);
        }
        return danhSachThayDoi;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ThayDoiHoKhau> get(Integer id) throws SQLException {
        String sql = "SELECT * FROM thaydoihokhau WHERE idThayDoiHoKhau = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            ThayDoiHoKhau thayDoi = new ThayDoiHoKhau(
                    resultSet.getInt("idThayDoiHoKhau"),
                    resultSet.getInt("idHoKhau"),
                    resultSet.getString("trangThai"),
                    resultSet.getString("soCCCDChuHoMoi"),
                    resultSet.getString("noiDung"),
                    resultSet.getTimestamp("ngayThayDoi").toLocalDateTime());
            return Optional.of(thayDoi);
        }
        return Optional.empty();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void save(@NotNull ThayDoiHoKhau thayDoi) throws SQLException {
        String sql = "INSERT INTO thaydoihokhau (idHoKhau, trangThai, soCCCDChuHoMoi, noiDung, ngayThayDoi) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, thayDoi.getIdHoKhau());
        statement.setString(2, thayDoi.getTrangThai());
        statement.setString(3, thayDoi.getSoCccdChuHoMoi());
        statement.setString(4, thayDoi.getNoiDung());
        statement.setObject(5, Timestamp.valueOf(thayDoi.getNgayThayDoi()));
        statement.executeUpdate();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void update(@NotNull ThayDoiHoKhau thayDoi) {
        String sql = "UPDATE thaydoihokhau SET idHoKhau = ?, trangThai = ?, soCCCDChuHoMoi = ?, noiDung = ?, ngayThayDoi = ? WHERE idThayDoiHoKhau = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, thayDoi.getIdHoKhau());
            statement.setString(2, thayDoi.getTrangThai());
            statement.setString(3, thayDoi.getSoCccdChuHoMoi());
            statement.setString(4, thayDoi.getNoiDung());
            statement.setObject(5, Timestamp.valueOf(thayDoi.getNgayThayDoi()));
            statement.setInt(6, thayDoi.getIdThayDoiHoKhau());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(@NotNull ThayDoiHoKhau thayDoi) throws SQLException {
        String sql = "DELETE FROM thaydoihokhau WHERE idThayDoiHoKhau = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, thayDoi.getIdThayDoiHoKhau());
        statement.executeUpdate();
    }
}
