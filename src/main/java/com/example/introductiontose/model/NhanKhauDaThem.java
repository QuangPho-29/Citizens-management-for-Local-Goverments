package com.example.introductiontose.model;

import java.time.LocalDateTime;

/**
 * Lớp NhanKhauDaThem đại diện cho thông tin của một nhân khẩu mới đã được thêm vào hệ thống.
 */
public class NhanKhauDaThem {
    private int id;
    private String soCccd;
    private int idHoKhau;
    private LocalDateTime ngayThem;
    
    /**
     * Khởi tạo một đối tượng NhanKhauDaThem mới.
     */
    public NhanKhauDaThem() {
    }
    
    /**
     * Khởi tạo một đối tượng NhanKhauDaThem với các thông tin cụ thể.
     *
     * @param id        ID của bản ghi.
     * @param soCccd    Số căn cước công dân của nhân khẩu liên quan.
     * @param idHoKhau  ID của hộ khẩu liên quan.
     * @param ngayThem  Thời điểm nhân khẩu được thêm vào hệ thống.
     */
    public NhanKhauDaThem(int id, String soCccd, int idHoKhau, LocalDateTime ngayThem) {
        this.id = id;
        this.soCccd = soCccd;
        this.idHoKhau = idHoKhau;
        this.ngayThem = ngayThem;
    }
    
    /**
     * Trả về ID của bản ghi.
     *
     * @return ID của bản ghi.
     */
    public int getId() {
        return id;
    }
    
    /**
     * Đặt giá trị ID cho bản ghi.
     *
     * @param id Giá trị ID mới.
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Trả về số căn cước công dân của nhân khẩu liên quan.
     *
     * @return Số căn cước công dân của nhân khẩu liên quan.
     */
    public String getSoCccd() {
        return soCccd;
    }
    
    /**
     * Đặt Số căn cước công dân của nhân khẩu liên quan.
     *
     * @param soCccd Số căn cước công dân mới của nhân khẩu liên quan.
     */
    public void setSoCccd(String soCccd) {
        this.soCccd = soCccd;
    }
    
    /**
     * Trả về ID của hộ khẩu liên quan.
     *
     * @return ID của hộ khẩu liên quan.
     */
    public int getIdHoKhau() {
        return idHoKhau;
    }
    
    /**
     * Đặt giá trị ID của hộ khẩu liên quan.
     *
     * @param idHoKhau Giá trị ID mới của hộ khẩu liên quan.
     */
    public void setIdHoKhau(int idHoKhau) {
        this.idHoKhau = idHoKhau;
    }
    
    /**
     * Trả về thời điểm nhân khẩu được thêm vào hệ thống.
     *
     * @return Thời điểm nhân khẩu được thêm vào hệ thống.
     */
    public LocalDateTime getNgayThem() {
        return ngayThem;
    }
    
    /**
     * Đặt giá trị thời điểm nhân khẩu được thêm vào hệ thống.
     *
     * @param ngayThem Giá trị thời điểm mới.
     */
    public void setNgayThem(LocalDateTime ngayThem) {
        this.ngayThem = ngayThem;
    }
}
