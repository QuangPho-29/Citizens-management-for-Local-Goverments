package com.example.introductiontose.dao;

import org.jetbrains.annotations.NotNull;
import com.example.introductiontose.model.NapTien;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class NapTienDao implements DataAccessObject<NapTien,Integer> {
    private final Connection connection;

    public NapTienDao(Connection connection) {
        this.connection = connection;
    }


    @Override
    public List<NapTien> getAll() throws SQLException {
        List<NapTien> danhsachNapTien = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM naptien");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                NapTien napTien = _get(resultSet);
                danhsachNapTien.add(napTien);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return danhsachNapTien;
    }

    @Override
    public Optional<NapTien> get(Integer idNapTien) throws SQLException {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM naptien WHERE idNapTien = ?");
            statement.setInt(1, idNapTien);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                NapTien napTien = _get(resultSet);
                return Optional.of(napTien);
            }
        } catch (SQLException e) {

        }
        return Optional.empty();

    }

    @Override
    public void save(@NotNull NapTien napTien) throws SQLException {

    }

    public void save(String soCccd, int soTienNap, String ghiChu) throws SQLException {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO naptien" +
                    "(soCccd,soTienNap,ghiChu) " +
                    "VALUES (?, ?, ?)");
            statement.setString(1, soCccd);
            statement.setInt(2, soTienNap);
            statement.setString(3, ghiChu);
            statement.executeUpdate();
        } catch (SQLException e) {

        }

    }

    @Override
    public void update(@NotNull NapTien napTien) throws SQLException {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE naptien SET" +
                    "soCccd = ?, " +
                    "soTienNap = ?, " +
                    "ghiChu = ?, " +
                    "WHERE idNapTien = ?");
            int parameterIndex = _setValuesForStatement(napTien, statement, 1);
            statement.setInt(parameterIndex, napTien.getIdNapTien());
            statement.executeUpdate();
        } catch (SQLException e) {

        }
    }

    @Override
    public void delete(@NotNull NapTien napTien) throws SQLException {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM naptien WHERE idNapTien = ?");
            statement.setInt(1, napTien.getIdNapTien());
            statement.executeUpdate();
        } catch (SQLException e) {

        }
    }

    private NapTien _get(ResultSet resultSet) throws SQLException {
        int idNapTien = resultSet.getInt("idNapTien");
        String soCccd = resultSet.getString("soCccd");
        int soTienNap = resultSet.getInt("soTienNap");
        String ghiChu = resultSet.getString("ghiChu");

        return new NapTien(idNapTien, soCccd, soTienNap, ghiChu);

    }

    private int _setValuesForStatement(NapTien napTien, PreparedStatement statement, int index) throws SQLException {
        statement.setInt(index, napTien.getIdNapTien());
        statement.setString(index + 1, napTien.getSoCccd());
        statement.setInt(index + 2, napTien.getSoTienNap());
        statement.setString(index + 3, napTien.getGhiChu());

        return index + 4;
    }
}