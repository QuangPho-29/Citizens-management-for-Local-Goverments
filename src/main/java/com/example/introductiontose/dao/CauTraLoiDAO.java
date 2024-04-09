package com.example.introductiontose.dao;

import com.example.introductiontose.model.CauTraLoi;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CauTraLoiDAO implements DataAccessObject<CauTraLoi,String> {
    private final Connection connection;

    /**
     * Khởi tạo một đối tượng ThongBaoDAO với kết nối cơ sở dữ liệu được cung cấp.
     *
     * @param connection Kết nối đến cơ sở dữ liệu.
     */

    public CauTraLoiDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * Lấy tất cả các đối tượng từ cơ sở dữ liệu.
     *
     * @return Danh sách chứa tất cả các đối tượng.
     */
    @Override
    public List<CauTraLoi> getAll() throws SQLException {
        return null;
    }

    /**
     * Lấy một đối tượng từ cơ sở dữ liệu dựa trên khóa chính.
     *
     * @param soCccd Khóa chính của đối tượng cần lấy.
     * @return Optional chứa đối tượng nếu tồn tại, hoặc Optional rỗng nếu không.
     */
    @Override
    public Optional<CauTraLoi> get(String soCccd) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM cautraloi WHERE soCccd = ?");
        statement.setString(1, soCccd);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            CauTraLoi cautraloi = _get(resultSet);
            return Optional.of(cautraloi);
        }
        return Optional.empty();
    }

    /**
     * Lưu một đối tượng mới vào cơ sở dữ liệu.
     *
     * @param cauTraLoi Đối tượng cần lưu.
     * @throws IllegalArgumentException Nếu đối tượng đã tồn tại trong cơ sở dữ liệu.
     */
    @Override
    public void save(@NotNull CauTraLoi cauTraLoi) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO cautraloi" +
                "(soCccd, cauHoi , cauTraLoi) " +
                "VALUES (?, ?, ?)");
        statement.setString(1, cauTraLoi.getSoCccd());
        statement.setString(2, cauTraLoi.getCauHoi());
        statement.setString(3, cauTraLoi.getCauTraLoi());
        statement.executeUpdate();
    }

    /**
     * Cập nhật thông tin của một đối tượng trong cơ sở dữ liệu.
     *
     * @param cauTraLoi Đối tượng cần cập nhật.
     * @throws IllegalArgumentException Nếu đối tượng không tồn tại trong cơ sở dữ liệu.
     */
    @Override
    public void update(@NotNull CauTraLoi cauTraLoi) {

    }

    /**
     * Xóa một đối tượng khỏi cơ sở dữ liệu.
     *
     * @param cauTraLoi Đối tượng cần xóa.
     * @throws IllegalArgumentException Nếu đối tượng không tồn tại trong cơ sở dữ liệu.
     */
    @Override
    public void delete(@NotNull CauTraLoi cauTraLoi) {

    }

    /**
     * Phương thức private để chuyển đổi dữ liệu từ ResultSet thành đối tượng CauTraLoi.
     *
     * @param resultSet ResultSet chứa dữ liệu từ cơ sở dữ liệu.
     * @return Đối tượng CauTraLoi được tạo từ dữ liệu ResultSet.
     * @throws SQLException Nếu có lỗi khi truy cập dữ liệu từ ResultSet.
     */
    private CauTraLoi _get(ResultSet resultSet) throws SQLException {
        String soCccd = resultSet.getString("soCccd");
        String cauHoi = resultSet.getString("cauHoi");
        String cauTraLoi = resultSet.getString("cauTraLoi");

        return new CauTraLoi(soCccd, cauHoi, cauTraLoi);
    }
}
