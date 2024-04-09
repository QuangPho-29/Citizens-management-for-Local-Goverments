package com.example.introductiontose.dao;

import com.example.introductiontose.model.TaiKhoanNhanKhau;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class TaiKhoanNhanKhauDAO triển khai interface DataAccessObject để thao tác với đối tượng TaiKhoanNhanKhau trong cơ sở dữ liệu.
 */
public class TaiKhoanNhanKhauDAO implements DataAccessObject<TaiKhoanNhanKhau, String> {
    private final Connection connection;

    /**
     * Khởi tạo một đối tượng TaiKhoanNhanKhauDAO với kết nối cơ sở dữ liệu được cung cấp.
     *
     * @param connection Kết nối đến cơ sở dữ liệu.
     */
    public TaiKhoanNhanKhauDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TaiKhoanNhanKhau> getAll() throws SQLException {
        List<TaiKhoanNhanKhau> danhSachTaiKhoanNhanKhau = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM taikhoan");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            TaiKhoanNhanKhau taikhoannhankhau = _get(resultSet);
            danhSachTaiKhoanNhanKhau.add(taikhoannhankhau);
        }
        return danhSachTaiKhoanNhanKhau;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<TaiKhoanNhanKhau> get(String soCCCD) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM taikhoan WHERE soCccd = ?");
        statement.setString(1, soCCCD);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            TaiKhoanNhanKhau taikhoannhankhau = _get(resultSet);
            return Optional.of(taikhoannhankhau);
        }
        return Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(@NotNull TaiKhoanNhanKhau taikhoannhankhau) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO taikhoan" +
                "(soCCCD, tenTaiKhoan, matKhau, soDuTaiKhoan) " +
                "VALUES (?, ?, ?, ?)");
        _setValuesForStatement(taikhoannhankhau, statement, 1);
        statement.executeUpdate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(@NotNull TaiKhoanNhanKhau taikhoannhankhau) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE taikhoan SET " +
                "tenTaiKhoan = ?, " +
                "matKhau = ?, " +
                "soDuTaiKhoan = ? " +
                " WHERE soCccd = ?");
        statement.setString(1,taikhoannhankhau.getTentaikhoan());
        statement.setString(2, taikhoannhankhau.getPass());
        statement.setInt(3, taikhoannhankhau.getSoDuTaiKhoan());
        statement.setString(4, taikhoannhankhau.getSoCCCD());
        statement.executeUpdate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(@NotNull TaiKhoanNhanKhau taikhoannhankhau) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM taikhoan WHERE soCCCD = ?");
        statement.setString(1, taikhoannhankhau.getSoCCCD());
        statement.executeUpdate();
    }

    public void delete(String soCccd) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM taikhoan WHERE soCCCD = ?");
        statement.setString(1, soCccd);
        statement.executeUpdate();
    }

    /**
     * Phương thức private để chuyển đổi dữ liệu từ ResultSet thành đối tượng TaiKhoanNhanKhau.
     *
     * @param resultSet ResultSet chứa dữ liệu từ cơ sở dữ liệu.
     * @return Đối tượng TaiKhoanNhanKhau được tạo từ dữ liệu ResultSet.
     * @throws SQLException Nếu có lỗi khi truy cập dữ liệu từ ResultSet.
     */
    private TaiKhoanNhanKhau _get(ResultSet resultSet) throws SQLException {
        String soCCCD = resultSet.getString("soCCCD");
        String tenTaiKhoan = resultSet.getString("tenTaiKhoan");
        String matKhau = resultSet.getString("matKhau");
        Integer soDuTaiKhoan = resultSet.getInt("soDuTaiKhoan");

        return new TaiKhoanNhanKhau(soCCCD, tenTaiKhoan, matKhau, soDuTaiKhoan);
    }

    /**
     * Phương thức private để thiết lập giá trị cho PreparedStatement khi thêm hoặc cập nhật TaiKhoanNhanKhau.
     *
     * @param taikhoannhankhau  Đối tượng TaiKhoanNhanKhau cần được thêm hoặc cập nhật.
     * @param statement PreparedStatement đang được chuẩn bị.
     * @param index     Index bắt đầu để thiết lập giá trị trong PreparedStatement.
     * @return Index tiếp theo sẽ được sử dụng cho các giá trị khác nếu cần.
     * @throws SQLException Nếu có lỗi khi thiết lập giá trị trong PreparedStatement.
     */
    private int _setValuesForStatement(TaiKhoanNhanKhau taikhoannhankhau, PreparedStatement statement, int index) throws SQLException {
        statement.setString(index++, taikhoannhankhau.getSoCCCD());
        statement.setString(index++, taikhoannhankhau.getTentaikhoan());
        statement.setString(index++, taikhoannhankhau.getPass());
        statement.setInt(index++, taikhoannhankhau.getSoDuTaiKhoan());

        return index;
    }


}
