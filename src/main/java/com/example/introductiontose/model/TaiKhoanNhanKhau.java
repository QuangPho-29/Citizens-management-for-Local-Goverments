package com.example.introductiontose.model;

/**
 * Lớp TaiKhoanNhanKhau đại diện cho thông tin về tài khoản của nhân khẩu trong hệ thống.
 */
public class TaiKhoanNhanKhau {
    private String tentaikhoan;
    private String pass;
    private String soCCCD;
    private int soDuTaiKhoan;

    /**
     * Khởi tạo một đối tượng TaiKhoanNhanKhau mới với thông tin cung cấp.
     *
     * @param soCCCD       Số CCCD của nhân khẩu.
     * @param tentaikhoan  Tên tài khoản.
     * @param pass         Mật khẩu.
     * @param soDuTaiKhoan Số dư của tài khoản đó
     */
    public TaiKhoanNhanKhau(String soCCCD, String tentaikhoan, String pass, int soDuTaiKhoan) {
        this.soCCCD = soCCCD;
        this.tentaikhoan = tentaikhoan;
        this.pass = pass;
        this.soDuTaiKhoan = soDuTaiKhoan;
    }

    /**
     * Lấy số CCCD của nhân khẩu.
     *
     * @return Số CCCD của nhân khẩu.
     */
    public String getSoCCCD() {
        return soCCCD;
    }

    /**
     * Lấy tên tài khoản.
     *
     * @return Tên tài khoản.
     */
    public String getTentaikhoan() {
        return tentaikhoan;
    }

    /**
     * Lấy mật khẩu.
     *
     * @return Mật khẩu.
     */
    public String getPass() {
        return pass;
    }

    /**
     * Thiết lập mật khẩu mới.
     *
     * @param pass Mật khẩu mới.
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * Thiết lập số CCCD của nhân khẩu.
     *
     * @param soCCCD Số CCCD của nhân khẩu.
     */
    public void setSoCCCD(String soCCCD) {
        this.soCCCD = soCCCD;
    }

    /**
     * Thiết lập tên tài khoản mới.
     *
     * @param tentaikhoan Tên tài khoản mới.
     */
    public void setTentaikhoan(String tentaikhoan) {
        this.tentaikhoan = tentaikhoan;
    }

    public int getSoDuTaiKhoan() {
        return soDuTaiKhoan;
    }

    public void setSoDuTaiKhoan(int soDuTaiKhoan) {
        this.soDuTaiKhoan = soDuTaiKhoan;
    }
}
