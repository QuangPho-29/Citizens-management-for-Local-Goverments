package com.example.introductiontose.controller.guithongbao;

import com.example.introductiontose.dao.TaiKhoanNhanKhauDAO;
import com.example.introductiontose.model.NhanKhau;
import com.example.introductiontose.model.TaiKhoanNhanKhau;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class LuuThongBao {

    /**
     * Phương thức lấy soCccd từ trường dữ liệu ìnoNK: Trinh Van Hau - 0000000000
     * @param infoNK thông tin nhân khẩu có dangj hoten - soCccd(12 số)
     * @return soCccd thu được
     */
    public static String[] laySoTuStringThongTin(String infoNK) {
        String[] parts = infoNK.split(" - ");
        return parts;
    }

    /**
     * Phương thức gửi thông báo tới các nhân khẩu trong danh sách
     * @param danhSachNguoiNhan danh sách nhân khẩu
     * @param tieudeDL tiêu đề thông báo
     * @param noidungDl nội dung thông báo
     * @return error lỗi người nhập thiếu/sai
     * @throws SQLException xảy ra lỗi khi truy vấn dữ liệu
     */
    public static String tbToiNhanKhau(String danhSachNguoiNhan, String tieudeDL, String noidungDl) throws SQLException {
        String error = "";
        String[] listNguoiNhan = danhSachNguoiNhan.split("\n");

        // Kiểm tra lỗi của danh sách nhân khẩu
        List<String> dsnkFull = functionHelpGuiThongBao.getAllNKsoCccd();
        for(int i = 0; i < listNguoiNhan.length; i++) {
            String infoNK = listNguoiNhan[i];
            if(!dsnkFull.contains(infoNK)) {
                error = "Thông tin của nhân khẩu tại dòng thứ " + (i+1) + " bị lỗi!";
                return error;
            }
        }

        for(int i = 0; i < listNguoiNhan.length; i++) {
            String infoNK = listNguoiNhan[i];
            String soCccd = LuuThongBao.laySoTuStringThongTin(infoNK)[1];
            if(functionHelpGuiThongBao.kiemTraTonTaiTK(soCccd)) {
                LocalDateTime ngayTaoTB = LocalDateTime.now();
                functionHelpGuiThongBao.insertThongBao(soCccd, tieudeDL, noidungDl, ngayTaoTB);
            }
        }

        return error;
    }

    public static String tbToiHoKhau(String danhSachHoNhan, String tieude, String noidung) throws SQLException {
        String error = "";
        String[] listHoNhan = danhSachHoNhan.split("\n");

        //Kiểm tra danh sách hộ khẩu
        List<String> dshkFull = functionHelpGuiThongBao.getAllHKhoTenChuHo();
        for(int i = 0; i < listHoNhan.length; i++) {
            String infoHK = listHoNhan[i];
            if(!dshkFull.contains(infoHK)) {
                error = "Thông tin của hộ khẩu tại dòng thứ " + (i+1) + " bị lỗi!";
                return error;
            }
        }

        for(int i = 0; i < listHoNhan.length; i++) {
            String infoHK = listHoNhan[i];
            String idHoKhau = LuuThongBao.laySoTuStringThongTin(infoHK)[0];
            int idHKInt = Integer.parseInt(idHoKhau);
            List<NhanKhau> danhsachnhankhau = functionHelpGuiThongBao.getAllNhanKhauByIdHoKhau(idHKInt);

            LocalDateTime ngayTaoTB = LocalDateTime.now();
            for(NhanKhau nhankhau : danhsachnhankhau) {
                String soCccdNhanKhau = nhankhau.getThongTinNhanKhau().getCccd().getSoCccd();
                if(functionHelpGuiThongBao.kiemTraTonTaiTK(soCccdNhanKhau)) {
                    functionHelpGuiThongBao.insertThongBao(soCccdNhanKhau, tieude, noidung, ngayTaoTB);
                }
            }

        }

        return error;
    }

    /**
     * Phương thức gửi thông báo tới toàn bộ nhân khẩu
     * @param tieudeDL tiêu đề thông báo
     * @param noidungDl nội dung thông báo
     * @throws SQLException xảy ra lỗi truy vấn dư liệu
     */
    public static void tbToiToanBo(String tieudeDL, String noidungDl) throws SQLException {
        TaiKhoanNhanKhauDAO tknkdao = new TaiKhoanNhanKhauDAO(functionHelpGuiThongBao.connection);
        List<TaiKhoanNhanKhau> danhsachtaikhoan = tknkdao.getAll();
        for(TaiKhoanNhanKhau tknk : danhsachtaikhoan) {
            LocalDateTime ngayTaoTB = LocalDateTime.now();
            functionHelpGuiThongBao.insertThongBao(tknk.getSoCCCD(), tieudeDL, noidungDl, ngayTaoTB);
        }
    }
}
