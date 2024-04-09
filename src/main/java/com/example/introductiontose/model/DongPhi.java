package com.example.introductiontose.model;

import java.time.LocalDateTime;

/**
 * Lớp DongPhi đại diện cho thông tin về việc đóng phí trong hệ thống.
 */
public class DongPhi {
    private int idPhi;
    private int idHoKhau;
    private LocalDateTime ngayDong;
    private int soTien;
    
    /**
     * Khởi tạo một đối tượng DongPhi mới.
     */
    public DongPhi() {
    }
    
    /**
     * Khởi tạo một đối tượng DongPhi với thông tin cung cấp.
     *
     * @param idPhi     ID của đợt đóng phí.
     * @param idHoKhau  ID của hộ khẩu liên quan đến đợt đóng phí.
     * @param soTien    Số tiền đã thanh toán.
     * @param ngayDong  Ngày và giờ khi thanh toán được thực hiện.
     */
    public DongPhi(int idPhi, int idHoKhau, int soTien, LocalDateTime ngayDong) {
        this.idPhi = idPhi;
        this.idHoKhau = idHoKhau;
        this.soTien = soTien;
        this.ngayDong = ngayDong;
    }
    
    /**
     * Lấy ID của đợt đóng phí.
     *
     * @return ID của đợt đóng phí.
     */
    public int getIdPhi() {
        return idPhi;
    }
    
    /**
     * Thiết lập ID của đợt đóng phí.
     *
     * @param idPhi ID của đợt đóng phí.
     */
    public void setIdPhi(int idPhi) {
        this.idPhi = idPhi;
    }
    
    /**
     * Lấy ID của hộ khẩu liên quan đến đợt đóng phí.
     *
     * @return ID của hộ khẩu liên quan đến đợt đóng phí.
     */
    public int getIdHoKhau() {
        return idHoKhau;
    }
    
    /**
     * Thiết lập ID của hộ khẩu liên quan đến đợt đóng phí.
     *
     * @param idHoKhau ID của hộ khẩu liên quan đến đợt đóng phí.
     */
    public void setIdHoKhau(int idHoKhau) {
        this.idHoKhau = idHoKhau;
    }
    
    /**
     * Lấy ngày và giờ khi thanh toán được thực hiện.
     *
     * @return Ngày và giờ khi thanh toán được thực hiện.
     */
    public LocalDateTime getNgayDong() {
        return ngayDong;
    }
    
    /**
     * Thiết lập ngày và giờ khi thanh toán được thực hiện.
     *
     * @param ngayDong Ngày và giờ khi thanh toán được thực hiện.
     */
    public void setNgayDong(LocalDateTime ngayDong) {
        this.ngayDong = ngayDong;
    }
    
    /**
     * Thiết lập số tiền đã thanh toán.
     *
     * @param soTien Số tiền đã thanh toán.
     */
    public void setSoTien(int soTien) {
        this.soTien = soTien;
    }
    
    /**
     * Lấy số tiền đã thanh toán.
     *
     * @return Số tiền đã thanh toán.
     */
    public int getSoTien() {
        return soTien;
    }
}
