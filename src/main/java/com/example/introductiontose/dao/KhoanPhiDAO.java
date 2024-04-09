package com.example.introductiontose.dao;

import com.example.introductiontose.model.KhoanPhi;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.*;

/**
 * Class KhoanPhiDAO triển khai interface DataAccessObject để thao tác với đối tượng KhoanPhi trong cơ sở dữ liệu.
 */
public class KhoanPhiDAO implements DataAccessObject<KhoanPhi, Integer> {
    private final Connection connection;
    /**
     * Khởi tạo một đối tượng KhoanPhiDAO với kết nối cơ sở dữ liệu được cung cấp.
     *
     * @param connection Kết nối đến cơ sở dữ liệu.
     */
    public KhoanPhiDAO(Connection connection) {
        this.connection = connection;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<KhoanPhi> getAll() throws SQLException {
        List<KhoanPhi> danhSachKhoanPhi = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM khoanphi");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            KhoanPhi khoanphi = _get(resultSet);
            danhSachKhoanPhi.add(khoanphi);
        }
        return danhSachKhoanPhi;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<KhoanPhi> get(Integer idPhi) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM khoanphi WHERE idPhi = ?");
        statement.setInt(1, idPhi);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            KhoanPhi khoanphi = _get(resultSet);
            return Optional.of(khoanphi);
        }
        return Optional.empty();
        
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void save(@NotNull KhoanPhi khoanphi) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO khoanphi" +
                " (idPhi, kieuPhi, noiDungThuPhi, mucPhi, ngayTao, ngayKetThuc, tieuDeKhoanPhi " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)");
        _setValuesForStatement(khoanphi, statement, 1);
        statement.executeUpdate();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void update(@NotNull KhoanPhi khoanphi) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE khoanphi SET" +
                "kieuPhi = ?, " +
                "noiDungThuPhi = ?, " +
                "mucPhi= ?, " +
                "ngayTao = ?, " +
                "ngayKetThuc = ?, " +
                "tieuDeKhoanPhi = ?, " +
                "WHERE idPhi = ?");
        int parameterIndex = _setValuesForStatement(khoanphi, statement, 1);
        statement.setInt(parameterIndex, khoanphi.getIdPhi());
        statement.executeUpdate();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(@NotNull KhoanPhi khoanphi) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM khoanphi WHERE idPhi = ?");
        statement.setInt(1, khoanphi.getIdPhi());
        statement.executeUpdate();
    }
    
    /**
     * Phương thức private để chuyển đổi dữ liệu từ ResultSet thành đối tượng KhoanPhi.
     *
     * @param resultSet ResultSet chứa dữ liệu từ cơ sở dữ liệu.
     * @return Đối tượng KhoanPhi được tạo từ dữ liệu ResultSet.
     * @throws SQLException Nếu có lỗi khi truy cập dữ liệu từ ResultSet.
     */
    private KhoanPhi _get(ResultSet resultSet) throws SQLException {
        KhoanPhi khoanphi = new KhoanPhi();
        khoanphi.setIdPhi(resultSet.getInt("idPhi"));
        khoanphi.setKieuphi(resultSet.getString("kieuPhi"));
        khoanphi.setNoidungphi(resultSet.getString("noiDungThuPhi"));
        khoanphi.setMucphi(resultSet.getInt("mucPhi"));
        khoanphi.setNgaytao(resultSet.getTimestamp("ngayTao").toLocalDateTime());
        khoanphi.setNgayketthuc(resultSet.getTimestamp("ngayKetThuc").toLocalDateTime());
        khoanphi.setTieudephi(resultSet.getString("tieuDeKhoanPhi"));
        return khoanphi;
    }
    
    /**
     * Phương thức private để thiết lập giá trị cho PreparedStatement khi thêm hoặc cập nhật KhoanPhi.
     *
     * @param khoanphi  Đối tượng KhoanPhi cần được thêm hoặc cập nhật.
     * @param statement PreparedStatement đang được chuẩn bị.
     * @param index     Index bắt đầu để thiết lập giá trị trong PreparedStatement.
     * @return Index tiếp theo sẽ được sử dụng cho các giá trị khác nếu cần.
     * @throws SQLException Nếu có lỗi khi thiết lập giá trị trong PreparedStatement.
     */
    private int _setValuesForStatement(KhoanPhi khoanphi, PreparedStatement statement, int index) throws SQLException {
        statement.setInt(index, khoanphi.getIdPhi());
        statement.setString(index + 1, khoanphi.getKieuphi());
        statement.setString(index + 2, khoanphi.getNoidungphi());
        statement.setInt(index + 3, khoanphi.getMucphi());
        statement.setTimestamp(index + 4, Timestamp.valueOf(khoanphi.getNgaytao()));
        statement.setTimestamp(index + 5, Timestamp.valueOf(khoanphi.getNgayketthuc()));
        statement.setString(index + 6, khoanphi.getTieudephi());
        return index + 7;
    }


    /**
     * Lấy danh sách các khoản phí từ cơ sở dữ liệu và trả về dưới dạng Map.
     * <p>
     * Phương thức này thực hiện truy vấn SQL để lấy tất cả các bản ghi từ bảng 'khoanphi',
     * sau đó lưu trữ id và loại phí của mỗi khoản phí vào một Map. Trong Map này, id của
     * mỗi khoản phí được sử dụng làm key, và loại phí tương ứng được sử dụng làm value.
     * </p>
     *
     * @return Map<Integer, String> với key là id của khoản phí và value là loại phí.
     *         Trả về Map rỗng nếu không có bản ghi nào hoặc có lỗi xảy ra.
     * @throws SQLException Nếu có lỗi xảy ra trong quá trình truy vấn cơ sở dữ liệu.
     */
    public Map<Integer, String> getDanhSachKhoanPhi() {
        Map<Integer, String> danhSachKP = new HashMap<>();
        String sql = "SELECT idPhi, kieuPhi FROM khoanphi";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("idPhi");
                String loaiPhi = resultSet.getString("kieuPhi");
                danhSachKP.put(id, loaiPhi);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Hoặc xử lý lỗi theo nhu cầu
        }
        return danhSachKP;
    }

    public Map<Integer, String> getDanhSachTenKhoanPhi() {
        Map<Integer, String> danhSachKP = new HashMap<>();
        String sql = "SELECT idPhi, tieuDeKhoanPhi FROM khoanphi";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("idPhi");
                String tenPhi = resultSet.getString("tieuDeKhoanPhi");
                danhSachKP.put(id, tenPhi);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Hoặc xử lý lỗi theo nhu cầu
        }
        return danhSachKP;
    }
}
