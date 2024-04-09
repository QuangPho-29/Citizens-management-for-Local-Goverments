package com.example.introductiontose.model;

/**
 * Lớp NhanKhau đại diện cho thông tin của một nhân khẩu trong hệ thống.
 */
public class NhanKhau {
    private ThongTinNhanKhau thongTinNhanKhau;
    
    /**
     * Khởi tạo một đối tượng NhanKhau mới.
     */
    public NhanKhau() {
    }
    
    /**
     * Khởi tạo một đối tượng NhanKhau với các thông tin cơ bản.
     *
     * @param thongTinNhanKhau Thông tin chi tiết về nhân khẩu.
     */
    public NhanKhau(ThongTinNhanKhau thongTinNhanKhau) {
        this.thongTinNhanKhau = thongTinNhanKhau;
    }
    
    /**
     * Trả về thông tin chi tiết về nhân khẩu.
     *
     * @return Thông tin chi tiết về nhân khẩu.
     */
    public ThongTinNhanKhau getThongTinNhanKhau() {
        return thongTinNhanKhau;
    }
    
    /**
     * Thiết lập thông tin chi tiết về nhân khẩu.
     *
     * @param thongTinNhanKhau Thông tin chi tiết mới về nhân khẩu.
     */
    public void setThongTinNhanKhau(ThongTinNhanKhau thongTinNhanKhau) {
        this.thongTinNhanKhau = thongTinNhanKhau;
    }
}
