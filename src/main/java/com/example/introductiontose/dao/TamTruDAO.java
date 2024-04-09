package com.example.introductiontose.dao;

import com.example.introductiontose.model.TamTru;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class TamTruDAO triển khai interface DataAccessObject để thao tác với đối tượng TamTru trong cơ sở dữ liệu.
 */
public class TamTruDAO implements DataAccessObject<TamTru, Integer> {
    private final Connection connection;

    /**
     * Khởi tạo một đối tượng TamTruDAO với kết nối cơ sở dữ liệu được cung cấp.
     *
     * @param connection Kết nối đến cơ sở dữ liệu.
     */
    public TamTruDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TamTru> getAll() {
        List<TamTru> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement("SELECT * FROM dangkytamtru");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String soCCCD = rs.getString("soCccd");
                String cccdChuHo = rs.getString("soCccdChuHo");
                String hoTen = rs.getString("hoTen");
                String biDanh = rs.getString("biDanh");
                String gioiTinh = rs.getString("gioiTinh");
                String soDienthoai = rs.getString("soDienThoai");
                String trangThai = rs.getString("trangThai");
                String nguyenQuan = rs.getString("nguyenQuan");
                String noiLamViec = rs.getString("noiLamViec");
                String noiCap = rs.getString("noiCap");
                String quanHe = rs.getString("quanHe");
                String danToc = rs.getString("danToc");
                String tonGiao = rs.getString("tonGiao");
                String ngheNghiep = rs.getString("ngheNghiep");
                LocalDate ngayBatDau = rs.getTimestamp("ngayBatDau").toLocalDateTime().toLocalDate();
                LocalDate ngayKetThuc = rs.getTimestamp("ngayKetThuc").toLocalDateTime().toLocalDate();
                LocalDate ngaysinh = rs.getTimestamp("ngaysinh").toLocalDateTime().toLocalDate();
                LocalDate ngayCap = rs.getTimestamp("ngayCap").toLocalDateTime().toLocalDate();
                String lyDo = rs.getString("lyDo");
                String noiDangKyTamTru = rs.getString("noiDangKyTamTru");

                TamTru tamTru = new TamTru(soCCCD, cccdChuHo, hoTen, biDanh,gioiTinh, nguyenQuan, danToc, tonGiao, ngheNghiep, noiLamViec, noiCap, quanHe, lyDo, ngaysinh, ngayCap,ngayBatDau, ngayKetThuc, trangThai, soDienthoai);
                getName(tamTru);
                tamTru.setNoiDangKyTamTru(noiDangKyTamTru);
                // Set other properties if necessary

                list.add(tamTru);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi ở phương thức getAll trong TamTruDAO");
            throw new UnsupportedOperationException("Error while retrieving data from the database.");
        }

        return list;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void save(@NotNull TamTru t) {
        try {
            String sql = "INSERT INTO dangkytamtru (soCCCD, cccdChuHo, hoTen, biDanh, gioiTinh, soDienThoai, nguyenQuan, danToc, tonGiao, ngheNghiep, noiLamViec, noiCap, quanHe, lyDo, trangThai, ngaysinh, ngayCap,ngayBatDau, ngayKetThuc) VALUES (?, ?, ?, ?,? ,?,?,?,?,?,?,?,?,?,?,?,?,?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, t.getSoCCCD());
            st.setString(2, t.getCccdChuHo());
            st.setString(3, t.getHoTen());
            st.setString(4, t.getBiDanh());
            st.setString(5, t.getGioiTinh());
            st.setString(6, t.getSoDienThoai());
            st.setString(7, t.getNguyenQuan());
            st.setString(8, t.getDanToc());
            st.setString(9, t.getTonGiao());
            st.setString(11, t.getNoiLamViec());
            st.setString(10, t.getNgheNghiep());
            st.setString(12, t.getNoiCap());
            st.setString(13, t.getQuanHe());
            st.setString(14, t.getLyDo());
            st.setString(15, t.getTrangThai());
            Date ngaycap = java.sql.Date.valueOf(t.getNgayCap());
            Date ngaysinh = java.sql.Date.valueOf(t.getNgaysinh());
            Date ngaybatdau = java.sql.Date.valueOf(t.getNgayBatDau());
            Date ngayketthuc = java.sql.Date.valueOf(t.getNgayKetThuc());
            st.setDate(16, ngaysinh);
            st.setDate(17, ngaycap);
            st.setDate(18, ngaybatdau);
            st.setDate(19, ngayketthuc);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Lỗi ở phương thức Save trong TamTruDAO");
            throw new UnsupportedOperationException("Error while saving data to the database.");
        }
    }

    /**
     * {@inheritDoc}
     * Phương thức update ở đây chỉ mục đích thay đổi trạng thái của TamTru từ chưa phê duyệt thành đã phê duyệt
     */
    @Override
    public void update(@NotNull TamTru t) {
        try {
            String sql = "UPDATE dangkytamtru SET trangThai = 'đã xác nhận' WHERE soCccd = ?";
            PreparedStatement st = connection.prepareStatement(sql);

            st.setString(1, t.getSoCCCD());
            // Thực hiện lệnh
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Lỗi ở phương thức update trong lớp TamTruDAO");
            throw new UnsupportedOperationException("Error while updating data in the database.");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(@NotNull TamTru t) {
        try {
            String sql = "DELETE FROM dangkytamtru WHERE soCccd = ?";
            PreparedStatement st = connection.prepareStatement(sql);

            st.setString(1, t.getSoCCCD());

            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Lỗi ở hàm delete trong class TamTruDAO");
            throw new UnsupportedOperationException("Error while deleting data from the database.");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<TamTru> get(Integer id) throws SQLException {
            String sql = "SELECT * FROM dangkytamtru WHERE soCccd = ?";
            PreparedStatement st = connection.prepareStatement("SELECT * FROM dangkytamtru WHERE soCccd = ?");

            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                TamTru tamtru = new TamTru();
                tamtru.setSoCCCD(rs.getString("soCccd"));
                tamtru.setNgayBatDau(rs.getTimestamp("ngayBatDau").toLocalDateTime().toLocalDate());
                tamtru.setNgayKetThuc(rs.getTimestamp("ngayKetThuc").toLocalDateTime().toLocalDate());
                tamtru.setNgaysinh(rs.getTimestamp("ngaySinh").toLocalDateTime().toLocalDate());
                tamtru.setNgayCap(rs.getTimestamp("ngayCap").toLocalDateTime().toLocalDate());
                tamtru.setCccdChuHo(rs.getString("soCccdChuHo"));
                tamtru.setHoTen(rs.getString("hoten"));
                tamtru.setBiDanh(rs.getString("biDanh"));
                tamtru.setSoDienThoai(rs.getString("soDienThoai"));
                tamtru.setNguyenQuan(rs.getString("nguyenQuan"));
                tamtru.setDanToc(rs.getString("danToc"));
                tamtru.setTonGiao(rs.getString("tonGiao"));
                tamtru.setNgheNghiep(rs.getString("ngheNghiep"));
                tamtru.setNoiLamViec(rs.getString("noiLamViec"));
                tamtru.setQuanHe(rs.getString("quanHe"));
                tamtru.setLyDo(rs.getString("lyDo"));
                tamtru.setTrangThai(rs.getString("trangThai"));
                tamtru.setGioiTinh(rs.getString("gioiTinh"));
                tamtru.setNoiCap(rs.getString("noiCap"));
                tamtru.setTrangThai(rs.getString("trangThai"));
                getName(tamtru);
                tamtru.setNoiDangKyTamTru(rs.getString("noiDangKyTamTru"));
                return Optional.of(tamtru);
            }

        return Optional.empty();
    }

    public void getName(TamTru tamVang) throws SQLException {
        String sql = "SELECT hoten FROM nhankhau WHERE nhankhau.soCccd = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, tamVang.getCccdChuHo());
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    tamVang.setHoTenChuHo(rs.getString("hoTen"));
                } else {
                    // Handle the case when no rows are returned
                    tamVang.setHoTenChuHo("N/A");
                }
            }
        }
    }

    public List<TamTru> getByHoKhau(String CccdChuHo) {
        List<TamTru> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement("SELECT * FROM dangkytamtru WHERE soCccdChuHo = ?");
            st.setString(1, CccdChuHo);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String soCCCD = rs.getString("soCccd");
                String cccdChuHo = rs.getString("soCccdChuHo");
                String hoTen = rs.getString("hoTen");
                String biDanh = rs.getString("biDanh");
                String gioiTinh = rs.getString("gioiTinh");
                String soDienthoai = rs.getString("soDienThoai");
                String trangThai = rs.getString("trangThai");
                String nguyenQuan = rs.getString("nguyenQuan");
                String noiLamViec = rs.getString("noiLamViec");
                String noiCap = rs.getString("noiCap");
                String quanHe = rs.getString("quanHe");
                String danToc = rs.getString("danToc");
                String tonGiao = rs.getString("tonGiao");
                String ngheNghiep = rs.getString("ngheNghiep");
                LocalDate ngayBatDau = rs.getTimestamp("ngayBatDau").toLocalDateTime().toLocalDate();
                LocalDate ngayKetThuc = rs.getTimestamp("ngayKetThuc").toLocalDateTime().toLocalDate();
                LocalDate ngaysinh = rs.getTimestamp("ngaysinh").toLocalDateTime().toLocalDate();
                LocalDate ngayCap = rs.getTimestamp("ngayCap").toLocalDateTime().toLocalDate();
                String lyDo = rs.getString("lyDo");
                String noiDangKyTamTru = rs.getString("noiDangKyTamTru");

                TamTru tamTru = new TamTru(soCCCD, cccdChuHo, hoTen, biDanh,gioiTinh, nguyenQuan, danToc, tonGiao, ngheNghiep, noiLamViec, noiCap, quanHe, lyDo, ngaysinh, ngayCap,ngayBatDau, ngayKetThuc, trangThai, soDienthoai);
                getName(tamTru);
                tamTru.setNoiDangKyTamTru(noiDangKyTamTru);
                // Set other properties if necessary

                list.add(tamTru);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi ở phương thức getAll trong TamTruDAO");
            throw new UnsupportedOperationException("Error while retrieving data from the database.");
        }

        return list;
    }
}
