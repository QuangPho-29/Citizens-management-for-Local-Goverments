package com.example.introductiontose.dao;

import com.example.introductiontose.model.CauHoi;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CauHoiDAO implements DataAccessObject<CauHoi, Integer>{
    private final Connection connection;

    /**
     * Khởi tạo một đối tượng ThongBaoDAO với kết nối cơ sở dữ liệu được cung cấp.
     *
     * @param connection Kết nối đến cơ sở dữ liệu.
     */
    public CauHoiDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * Lấy tất cả các đối tượng từ cơ sở dữ liệu.
     *
     * @return Danh sách chứa tất cả các đối tượng.
     */
    @Override
    public List<CauHoi> getAll() throws SQLException {
        List<CauHoi> danhSachCauHoi = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM cauhoi");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            CauHoi cauhoi = _get(resultSet);
            danhSachCauHoi.add(cauhoi);
        }
        return danhSachCauHoi;
    }

    /**
     * Lấy một đối tượng từ cơ sở dữ liệu dựa trên khóa chính.
     *
     * @param id Khóa chính của đối tượng cần lấy.
     * @return Optional chứa đối tượng nếu tồn tại, hoặc Optional rỗng nếu không.
     */
    @Override
    public Optional<CauHoi> get(Integer id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM cauhoi WHERE idCauHoi = ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            CauHoi cauhoi = _get(resultSet);
            return Optional.of(cauhoi);
        }
        return Optional.empty();
    }

    /**
     * Lưu một đối tượng mới vào cơ sở dữ liệu.
     *
     * @param cauHoi Đối tượng cần lưu.
     * @throws IllegalArgumentException Nếu đối tượng đã tồn tại trong cơ sở dữ liệu.
     */
    @Override
    public void save(@NotNull CauHoi cauHoi) {

    }

    /**
     * Cập nhật thông tin của một đối tượng trong cơ sở dữ liệu.
     *
     * @param cauHoi Đối tượng cần cập nhật.
     * @throws IllegalArgumentException Nếu đối tượng không tồn tại trong cơ sở dữ liệu.
     */
    @Override
    public void update(@NotNull CauHoi cauHoi) {

    }

    /**
     * Xóa một đối tượng khỏi cơ sở dữ liệu.
     *
     * @param cauHoi Đối tượng cần xóa.
     * @throws IllegalArgumentException Nếu đối tượng không tồn tại trong cơ sở dữ liệu.
     */
    @Override
    public void delete(@NotNull CauHoi cauHoi) {

    }

    /**
     * Phương thức private để chuyển đổi dữ liệu từ ResultSet thành đối tượng CauHoi.
     *
     * @param resultSet ResultSet chứa dữ liệu từ cơ sở dữ liệu.
     * @return Đối tượng CauHoi được tạo từ dữ liệu ResultSet.
     * @throws SQLException Nếu có lỗi khi truy cập dữ liệu từ ResultSet.
     */
    private CauHoi _get(ResultSet resultSet) throws SQLException {
        int idCauHoi = resultSet.getInt("idCauHoi");
        String noiDungCauHoi = resultSet.getString("noiDungCauHoi");

        return new CauHoi(idCauHoi, noiDungCauHoi);
    }
}
