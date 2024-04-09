package com.example.introductiontose.util;

import com.example.introductiontose.database.SqlConnection;
import com.example.introductiontose.model.CCCD;
import com.example.introductiontose.model.NhanKhau;
import com.example.introductiontose.model.ThongTinNhanKhau;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Lớp tiện ích SQLUtils cung cấp các phương thức hỗ trợ thao tác với cơ sở dữ liệu SQL.
 *
 * <p>Lớp này chứa các phương thức để trích xuất thông tin nhân khẩu từ ResultSet, thiết lập giá trị cho
 * PreparedStatement và lấy danh sách nhân khẩu từ một hộ khẩu cụ thể.</p>
 *
 * <p>Thực hiện kết nối và đóng kết nối đến cơ sở dữ liệu thông qua lớp SqlConnection.</p>
 *
 * <p>Các phương thức trong lớp này giúp quản lý truy vấn SQL và chuyển đổi dữ liệu giữa đối tượng Java và cơ sở dữ liệu.</p>
 *
 * @author Duy
 * @version 1.0
 */
public class SQLUtils {
    /**
     * Trích xuất thông tin nhân khẩu từ ResultSet.
     *
     * @param resultSet ResultSet chứa dữ liệu cần trích xuất.
     * @return Thông tin của nhân khẩu.
     * @throws SQLException Nếu có lỗi khi truy cập dữ liệu từ ResultSet.
     */
    public static ThongTinNhanKhau get(ResultSet resultSet) throws SQLException {
        // Trích xuất thông tin từ ResultSet
        String soCccd = resultSet.getString("soCccd");
        LocalDateTime ngayCap = resultSet.getTimestamp("ngayCap").toLocalDateTime();
        String noiCap = resultSet.getString("noiCap");
        
        int idHoKhau = resultSet.getInt("idHoKhau");
        String hoTen = resultSet.getString("hoTen");
        String biDanh = resultSet.getString("biDanh");
        LocalDateTime ngaySinh = resultSet.getTimestamp("ngaySinh").toLocalDateTime();
        String noiSinh = resultSet.getString("noiSinh");
        String nguyenQuan = resultSet.getString("nguyenQuan");
        String danToc = resultSet.getString("danToc");
        String tonGiao = resultSet.getString("tonGiao");
        String ngheNghiep = resultSet.getString("ngheNghiep");
        String noiLamViec = resultSet.getString("noiLamViec");
        LocalDateTime ngayDKTT = resultSet.getTimestamp("ngayDKTT").toLocalDateTime();
        String diaChiCu = resultSet.getString("diaChiCu");
        String quanHe = resultSet.getString("quanHe");
        
        CCCD cccd = new CCCD(soCccd, ngayCap, noiCap);
        
        return new ThongTinNhanKhau(cccd, idHoKhau, hoTen, biDanh, ngaySinh,
                noiSinh, nguyenQuan, danToc, tonGiao, ngheNghiep, noiLamViec, ngayDKTT,
                diaChiCu, quanHe);
    }
    
    /**
     * Thiết lập giá trị cho PreparedStatement từ đối tượng ThongTinNhanKhau.
     *
     * @param thongTinNhanKhau Thông tin nhân khẩu để đưa vào truy vấn.
     * @param statement        PreparedStatement đang được chuẩn bị.
     * @param index            Index bắt đầu để thiết lập giá trị trong PreparedStatement.
     * @return Index tiếp theo sẽ được sử dụng cho các giá trị khác nếu cần.
     * @throws SQLException Nếu có lỗi khi thiết lập giá trị trong PreparedStatement.
     */
    public static int setValuesForStatement(ThongTinNhanKhau thongTinNhanKhau, PreparedStatement statement, int index) throws SQLException {
        // Thiết lập giá trị cho PreparedStatement từ ThongTinNhanKhau
        statement.setTimestamp(index++, Timestamp.valueOf(thongTinNhanKhau.getCccd().getNgayCap()));
        statement.setString(index++, thongTinNhanKhau.getCccd().getNoiCap());
        
        statement.setInt(index++, thongTinNhanKhau.getIdHoKhau());
        statement.setString(index++, thongTinNhanKhau.getHoTen());
        statement.setString(index++, thongTinNhanKhau.getBiDanh());
        statement.setTimestamp(index++, Timestamp.valueOf(thongTinNhanKhau.getNgaySinh()));
        statement.setString(index++, thongTinNhanKhau.getNoiSinh());
        statement.setString(index++, thongTinNhanKhau.getNguyenQuan());
        statement.setString(index++, thongTinNhanKhau.getDanToc());
        statement.setString(index++, thongTinNhanKhau.getTonGiao());
        statement.setString(index++, thongTinNhanKhau.getNgheNghiep());
        statement.setString(index++, thongTinNhanKhau.getNoiLamViec());
        statement.setTimestamp(index++, Timestamp.valueOf(thongTinNhanKhau.getNgayDKTT()));
        statement.setString(index++, thongTinNhanKhau.getDiaChiCu());
        statement.setString(index++, thongTinNhanKhau.getQuanHe());
        
        return index;
    }
    
    /**
     * Lấy danh sách nhân khẩu từ một hộ khẩu cụ thể.
     *
     * @param idHoKhau ID của hộ khẩu cần lấy thông tin nhân khẩu.
     * @return Danh sách nhân khẩu trong hộ khẩu.
     * @throws SQLException Nếu có lỗi khi truy cập dữ liệu từ cơ sở dữ liệu.
     */
    public static List<NhanKhau> getNhanKhauFromHoKhau(int idHoKhau) throws SQLException {
        // Kết nối đến cơ sở dữ liệu
        Connection connection = SqlConnection.connect();
        List<NhanKhau> danhSachNhanKhau = new ArrayList<>();
        
        // Chuẩn bị truy vấn SQL
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM nhankhau WHERE idHoKhau = ?");
        statement.setInt(1, idHoKhau);
        
        // Thực hiện truy vấn và xử lý kết quả
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            NhanKhau nhanKhau = new NhanKhau(get(resultSet));
            danhSachNhanKhau.add(nhanKhau);
        }
        
        // Đóng kết nối
        SqlConnection.close(connection);
        
        return danhSachNhanKhau;
    }
}
