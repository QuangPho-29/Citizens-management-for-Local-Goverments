package com.example.introductiontose.dao;

import java.sql.SQLException;

import com.example.introductiontose.model.CCCD;
import com.example.introductiontose.model.ThayDoiNhanKhau;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
public class ThayDoiNhanKhauDao implements DataAccessObject<ThayDoiNhanKhau,Integer> {
    private final Connection connection;


    public ThayDoiNhanKhauDao(Connection connection) {
        this.connection = connection;
    }
    @Override
    public List<ThayDoiNhanKhau> getAll() throws SQLException {
        List<ThayDoiNhanKhau> danhSachThayDoiNhanKhau = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM thaydoinhankhau");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            ThayDoiNhanKhau thayDoiNhanKhau = _get(resultSet);
            danhSachThayDoiNhanKhau.add(thayDoiNhanKhau);
        }
        return danhSachThayDoiNhanKhau;
    }

    @Override
    public Optional<ThayDoiNhanKhau> get(Integer idThaydoi) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM thaydoinhankhau WHERE idThayDoi = ?");
        statement.setInt(1, idThaydoi);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            ThayDoiNhanKhau thayDoiNhanKhau = _get(resultSet);
            return Optional.of(thayDoiNhanKhau);
        }
        return Optional.empty();
    }

    @Override
    public void save(@NotNull ThayDoiNhanKhau thayDoiNhanKhau) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO thaydoinhankhau" +
                " (idThayDoi, soCccd, trangThai, ngayChuyenDi, noiChuyenDen, ghiChu " +
                "VALUES (?, ?, ?, ?, ?, ?)");
        _setValuesForStatement(thayDoiNhanKhau, statement, 1);
        statement.executeUpdate();

    }

    @Override
    public void update(@NotNull ThayDoiNhanKhau thayDoiNhanKhau) throws SQLException{
        PreparedStatement statement = connection.prepareStatement("UPDATE thaydoinhankhau SET" +
                "soCccd = ?, " +
                "trangThai = ?, " +
                "ngayChuyenDi= ?, " +
                "noiChuyenDen = ?, " +
                "ghiChu = ?, " +
                "WHERE idThayDoi = ?");
        int parameterIndex = _setValuesForStatement(thayDoiNhanKhau, statement, 1);
        statement.setInt(parameterIndex, thayDoiNhanKhau.getIdThaydoi());
        statement.executeUpdate();

    }

    @Override
    public void delete(@NotNull ThayDoiNhanKhau thayDoiNhanKhau) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM thaydoinhankhau WHERE idThayDoi = ?");
        statement.setInt(1, thayDoiNhanKhau.getIdThaydoi());
        statement.executeUpdate();

    }
    private ThayDoiNhanKhau _get(ResultSet resultSet) throws SQLException {
        ThayDoiNhanKhau thayDoiNhanKhau = new ThayDoiNhanKhau();
        thayDoiNhanKhau.setIdThaydoi(resultSet.getInt("idThayDoi"));
        thayDoiNhanKhau.setSoCccd(resultSet.getString("soCccd"));
        thayDoiNhanKhau.setTrangthaithaidoi(resultSet.getString("trangThai"));
        thayDoiNhanKhau.setNgaychuyendi(resultSet.getTimestamp("ngayChuyenDi").toLocalDateTime());
        thayDoiNhanKhau.setNoichuyenden(resultSet.getString("noiChuyenDen"));
        thayDoiNhanKhau.setGhichu(resultSet.getString("ghiChu"));
        return thayDoiNhanKhau;
    }
    private int _setValuesForStatement(ThayDoiNhanKhau thayDoiNhanKhau, PreparedStatement statement, int index) throws SQLException {
        statement.setInt(index, thayDoiNhanKhau.getIdThaydoi());
        statement.setString(index + 1, thayDoiNhanKhau.getSoCccd());
        statement.setString(index + 2, thayDoiNhanKhau.getTrangthaithaidoi());
        statement.setTimestamp(index + 3, Timestamp.valueOf(thayDoiNhanKhau.getNgaychuyendi()));
        statement.setString(index + 4, thayDoiNhanKhau.getNoichuyenden());
        statement.setString(index + 5, thayDoiNhanKhau.getGhichu());
        return index + 6;
    }

    public List<ThayDoiNhanKhau> getDaThayDoi() throws SQLException {
        List<ThayDoiNhanKhau> danhSachThayDoiNhanKhau = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM thaydoinhankhau WHERE trangThai = ?");
        statement.setString(1, "đã xác nhận");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            ThayDoiNhanKhau thayDoiNhanKhau = _get(resultSet);
            danhSachThayDoiNhanKhau.add(thayDoiNhanKhau);
        }
        return danhSachThayDoiNhanKhau;
    }

    public List<ThayDoiNhanKhau> getCCCD(CCCD cccd) throws SQLException {
        List<ThayDoiNhanKhau> tmp = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM thaydoinhankhau WHERE soCccd = ?");
        statement.setString(1, cccd.getSoCccd());
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            ThayDoiNhanKhau thayDoiNhanKhau = _get(resultSet);
            tmp.add(thayDoiNhanKhau);
        }
        return tmp;
    }
}
