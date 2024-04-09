package com.example.introductiontose.dao;

import com.example.introductiontose.model.DongPhi;
import com.example.introductiontose.model.NhanKhau;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class DongPhiDAO triển khai interface DataAccessObject để thao tác với đối tượng DongPhi trong cơ sở dữ liệu.
 */
public class DongPhiDAO implements DataAccessObject<DongPhi, Integer> {
    private final Connection connection;

    /**
     * Khởi tạo một đối tượng DongPhiDAO với kết nối cơ sở dữ liệu được cung cấp.
     *
     * @param connection Kết nối đến cơ sở dữ liệu.
     */
    public DongPhiDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DongPhi> getAll() throws SQLException {
        List<DongPhi> danhSachDongPhi = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM dongphi");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            DongPhi dongphi = _get(resultSet);
            danhSachDongPhi.add(dongphi);
        }
        return danhSachDongPhi;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<DongPhi> get(Integer idPhi) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM dongphi WHERE idPhi = ?");
        statement.setInt(1, idPhi);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            DongPhi dongphi = _get(resultSet);
            return Optional.of(dongphi);
        }
        return Optional.empty();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(@NotNull DongPhi dongphi) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO dongphi" +
                "(idPhi, idHoKhau,soTien, ngayDong) " +
                "VALUES (?, ?, ?,?)");
        _setValuesForStatement(dongphi, statement, 1);
        statement.executeUpdate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(@NotNull DongPhi dongphi) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE dongphi SET" +
                "idNhanKhau = ?, " +
                "soTien = ?, " +
                "ngayDong = ?, " +
                "WHERE idPhi = ?");
        int parameterIndex = _setValuesForStatement(dongphi, statement, 1);
        statement.setInt(parameterIndex, dongphi.getIdPhi());
        statement.executeUpdate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(@NotNull DongPhi dongphi) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM dongphi WHERE idPhi = ?");
        statement.setInt(1, dongphi.getIdPhi());
        statement.executeUpdate();
    }

    /**
     * Phương thức private để chuyển đổi dữ liệu từ ResultSet thành đối tượng DongPhi.
     *
     * @param resultSet ResultSet chứa dữ liệu từ cơ sở dữ liệu.
     * @return Đối tượng DongPhi được tạo từ dữ liệu ResultSet.
     * @throws SQLException Nếu có lỗi khi truy cập dữ liệu từ ResultSet.
     */
    private DongPhi _get(ResultSet resultSet) throws SQLException {
        int idPhi = resultSet.getInt("idPhi");
        int idHoKhau = resultSet.getInt("idHoKhau");
        int soTien = resultSet.getInt("soTien");
        LocalDateTime ngayDong = resultSet.getTimestamp("ngayDong").toLocalDateTime();

        return new DongPhi(idPhi, idHoKhau, soTien, ngayDong);
    }

    /**
     * Phương thức private để thiết lập giá trị cho PreparedStatement khi thêm hoặc cập nhật DongPhi.
     *
     * @param dongphi  Đối tượng DongPhi cần được thêm hoặc cập nhật.
     * @param statement PreparedStatement đang được chuẩn bị.
     * @param index     Index bắt đầu để thiết lập giá trị trong PreparedStatement.
     * @return Index tiếp theo sẽ được sử dụng cho các giá trị khác nếu cần.
     * @throws SQLException Nếu có lỗi khi thiết lập giá trị trong PreparedStatement.
     */
    private int _setValuesForStatement(DongPhi dongphi, PreparedStatement statement, int index) throws SQLException {
        statement.setInt(index++, dongphi.getIdHoKhau());
        statement.setInt(index++, dongphi.getSoTien());
        statement.setTimestamp(index++, Timestamp.valueOf(dongphi.getNgayDong()));

        return index;
    }

    public void save(int idPhi, int idHoKhau, LocalDateTime ngayDong, int soTien) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO dongphi" +
                    "(idPhi, idHoKhau,soTien, ngayDong) " +
                    "VALUES (?, ?, ?,?)");
            statement.setInt(1,idPhi);
            statement.setInt(2,idHoKhau);
            statement.setTimestamp(4, Timestamp.valueOf(ngayDong));
            statement.setInt(3,soTien);
            statement.executeUpdate();
        } catch (SQLException e) {

        }
    }
    public void save1(int idPhi, int idHoKhau, LocalDateTime ngayDong, String soTien) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO dongphi" +
                    "(idPhi, idHoKhau,soTien, ngayDong) " +
                    "VALUES (?, ?, ?,?)");
            statement.setInt(1,idPhi);
            statement.setInt(2,idHoKhau);
            statement.setTimestamp(4, Timestamp.valueOf(ngayDong));
            statement.setString(3,soTien);
            statement.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public List<DongPhi> getDongPhiUser (NhanKhau user) throws SQLException{
        List<DongPhi> danhSachDongPhi = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM dongphi WHERE idHoKhau = ?");
        statement.setInt(1, user.getThongTinNhanKhau().getIdHoKhau());
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            DongPhi dongphi = _get(resultSet);
            danhSachDongPhi.add(dongphi);
        }
        return danhSachDongPhi;
    }
}