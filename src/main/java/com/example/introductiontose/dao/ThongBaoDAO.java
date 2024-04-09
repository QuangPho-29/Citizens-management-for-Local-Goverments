package com.example.introductiontose.dao;

import com.example.introductiontose.model.ThongBao;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class ThongBaoDAO triển khai interface DataAccessObject để thao tác với đối tượng ThongBao trong cơ sở dữ liệu.
 */
public class ThongBaoDAO implements DataAccessObject<ThongBao, Integer> {
    private final Connection connection;
    
    /**
     * Khởi tạo một đối tượng ThongBaoDAO với kết nối cơ sở dữ liệu được cung cấp.
     *
     * @param connection Kết nối đến cơ sở dữ liệu.
     */
    public ThongBaoDAO(Connection connection) {
        this.connection = connection;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ThongBao> getAll() throws SQLException {
        List<ThongBao> danhSachThongBao = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM thongbao");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            ThongBao thongbao = _get(resultSet);
            danhSachThongBao.add(thongbao);
        }
        return danhSachThongBao;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ThongBao> get(Integer idThongBao) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM thongbao WHERE idThongBao = ?");
        statement.setInt(1, idThongBao);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            ThongBao thongbao = _get(resultSet);
            return Optional.of(thongbao);
        }
        return Optional.empty();
        
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void save(@NotNull ThongBao thongbao) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO thongbao" +
                "(idThongBao, soCCCD,tieuDe,noiDung, ngayTao) " +
                "VALUES (?, ?, ?,?,?)");
        _setValuesForStatement(thongbao, statement, 1);
        statement.executeUpdate();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void update(@NotNull ThongBao thongbao) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE thongbao SET" +
                "soCCCD = ?, " +
                "tienDe = ?, " +
                "noiDung = ?, " +
                "ngayTao =?, " +
                "WHERE idThongBao =?");
        int parameterIndex = _setValuesForStatement(thongbao, statement, 1);
        statement.setInt(parameterIndex, thongbao.getIdTHONGBAO());
        statement.executeUpdate();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(@NotNull ThongBao thongbao) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM thongbao WHERE idThongBao = ?");
        statement.setInt(1, thongbao.getIdTHONGBAO());
        statement.executeUpdate();
    }
    
    /**
     * Phương thức private để chuyển đổi dữ liệu từ ResultSet thành đối tượng ThongBao.
     *
     * @param resultSet ResultSet chứa dữ liệu từ cơ sở dữ liệu.
     * @return Đối tượng ThongBao được tạo từ dữ liệu ResultSet.
     * @throws SQLException Nếu có lỗi khi truy cập dữ liệu từ ResultSet.
     */
    private ThongBao _get(ResultSet resultSet) throws SQLException {
        int idThongBao = resultSet.getInt("idThongBao");
        int soCCCD = resultSet.getInt("soCCCD");
        String tieuDe = resultSet.getString("tieuDe");
        String noiDung = resultSet.getString("noiDung");
        LocalDateTime ngayTao = resultSet.getTimestamp("ngayTao").toLocalDateTime();
        
        return new ThongBao(idThongBao, soCCCD, tieuDe, noiDung, ngayTao);
    }
    
    /**
     * Phương thức private để thiết lập giá trị cho PreparedStatement khi thêm hoặc cập nhật ThongBao.
     *
     * @param thongbao  Đối tượng ThongBao cần được thêm hoặc cập nhật.
     * @param statement PreparedStatement đang được chuẩn bị.
     * @param index     Index bắt đầu để thiết lập giá trị trong PreparedStatement.
     * @return Index tiếp theo sẽ được sử dụng cho các giá trị khác nếu cần.
     * @throws SQLException Nếu có lỗi khi thiết lập giá trị trong PreparedStatement.
     */
    private int _setValuesForStatement(ThongBao thongbao, PreparedStatement statement, int index) throws SQLException {
        statement.setInt(index++, thongbao.getSoCCCD());
        statement.setString(index++, thongbao.getTieuDe());
        statement.setString(index++, thongbao.getNoidung());
        statement.setTimestamp(index++, Timestamp.valueOf(thongbao.getNgaytao()));
        
        return index;
    }
    
}
