package com.example.introductiontose.dao;

import com.example.introductiontose.model.NhanKhau;
import com.example.introductiontose.model.ThongTinNhanKhau;
import com.example.introductiontose.util.SQLUtils;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class NhanKhauDao triển khai interface DataAccessObject để thao tác với đối tượng NhanKhau trong cơ sở dữ liệu.
 */
public class NhanKhauDAO implements DataAccessObject<NhanKhau, String> {
    private final Connection connection;
    private final String table_name;
    
    public enum TableType {
        NHANKHAU,
        NHANKHAUCHUATHEM
    }
    
    /**
     * Khởi tạo một đối tượng NhanKhauDao với kết nối cơ sở dữ liệu được cung cấp.
     *
     * @param connection Kết nối đến cơ sở dữ liệu.
     */
    public NhanKhauDAO(Connection connection, TableType tableType) {
        this.connection = connection;
        if (tableType == TableType.NHANKHAU) {
            table_name = "nhankhau";
        } else {
            table_name = "themnhankhau";
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<NhanKhau> getAll() throws SQLException {
        List<NhanKhau> danhSachNhanKhau = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + table_name);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            NhanKhau nhanKhau = _get(resultSet);
            danhSachNhanKhau.add(nhanKhau);
        }
        return danhSachNhanKhau;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<NhanKhau> get(String soCccd) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + table_name + " WHERE soCccd = ?;");
        statement.setString(1, soCccd);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            NhanKhau nhanKhau = _get(resultSet);
            return Optional.of(nhanKhau);
        }
        return Optional.empty();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void save(@NotNull NhanKhau nhanKhau) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO " + table_name +
                "+ (soCccd, ngayCap, noiCap, idHoKhau, hoTen, biDanh, ngaySinh, noiSinh, nguyenQuan, " +
                "danToc, tonGiao, ngheNghiep, noiLamViec, ngayDKTT, diaChiCu, quanHe) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        statement.setString(1, nhanKhau.getThongTinNhanKhau().getCccd().getSoCccd());
        _setValuesForStatement(nhanKhau, statement, 2);
        statement.executeUpdate();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void update(@NotNull NhanKhau nhanKhau) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE " + table_name + " SET " +
                "ngayCap = ?, " +
                "noiCap = ?, " +
                "idHoKhau = ?, " +
                "hoTen = ?, " +
                "biDanh = ?, " +
                "ngaySinh = ?, " +
                "noiSinh = ?, " +
                "nguyenQuan = ?, " +
                "danToc = ?, " +
                "tonGiao = ?, " +
                "ngheNghiep = ?, " +
                "noiLamViec = ?, " +
                "ngayDKTT = ?, " +
                "diaChiCu = ?, " +
                "quanHe = ?" +
                "WHERE soCccd = ?");
        int parameterIndex = _setValuesForStatement(nhanKhau, statement, 1);
        statement.setString(parameterIndex, nhanKhau.getThongTinNhanKhau().getCccd().getSoCccd());
        statement.executeUpdate();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(@NotNull NhanKhau nhanKhau) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM " + table_name + " WHERE soCccd = ?");
        statement.setString(1, nhanKhau.getThongTinNhanKhau().getCccd().getSoCccd());
        statement.executeUpdate();
    }
    
    /**
     * Phương thức private để chuyển đổi dữ liệu từ ResultSet thành đối tượng NhanKhau.
     *
     * @param resultSet ResultSet chứa dữ liệu từ cơ sở dữ liệu.
     * @return Đối tượng NhanKhau được tạo từ dữ liệu ResultSet.
     * @throws SQLException Nếu có lỗi khi truy cập dữ liệu từ ResultSet.
     */
    private NhanKhau _get(ResultSet resultSet) throws SQLException {
        ThongTinNhanKhau thongTinNhanKhau = SQLUtils.get(resultSet);
        
        return new NhanKhau(thongTinNhanKhau);
    }
    
    /**
     * Phương thức private để thiết lập giá trị cho PreparedStatement khi thêm hoặc cập nhật NhanKhau.
     *
     * @param nhanKhau  Đối tượng NhanKhau cần được thêm hoặc cập nhật.
     * @param statement PreparedStatement đang được chuẩn bị.
     * @param index     Index bắt đầu để thiết lập giá trị trong PreparedStatement.
     * @return Index tiếp theo sẽ được sử dụng cho các giá trị khác nếu cần.
     * @throws SQLException Nếu có lỗi khi thiết lập giá trị trong PreparedStatement.
     */
    private int _setValuesForStatement(NhanKhau nhanKhau, PreparedStatement statement, int index) throws SQLException {
        return SQLUtils.setValuesForStatement(nhanKhau.getThongTinNhanKhau(), statement, index);
    }

    public int getSoNhanKhau() throws SQLException {
        String sql = "SELECT COUNT(soCccd) AS soNhanKhau FROM nhankhau";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        if (resultSet.next()) {
            return resultSet.getInt("soNhanKhau");
        } else {
            // Xử lý trường hợp không có kết quả
            return 0;
        }
    }

    public List<NhanKhau> getThanhVienGD(int idHoKhau) throws SQLException {
        List<NhanKhau> danhSachThanhVien = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + table_name + " WHERE idHoKhau = ?");
        statement.setInt(1, idHoKhau);

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            NhanKhau nhanKhau = _get(resultSet);
            danhSachThanhVien.add(nhanKhau);
        }
        return danhSachThanhVien;
    }

    public List<NhanKhau> getThanhVienChuaThem(int idHoKhau) throws SQLException {
        List<NhanKhau> danhSachThanhVien = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + table_name + " WHERE idHoKhau = ?");
        statement.setInt(1, idHoKhau);

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            NhanKhau nhanKhau = _get(resultSet);
            danhSachThanhVien.add(nhanKhau);
        }
        return danhSachThanhVien;
    }
}
