package com.example.introductiontose.model;

import java.time.LocalDate;

/**
 * Lớp TamVang đại diện cho thông tin về việc tạm vắng của một cư dân trong hệ thống.
 */
public class TamVang {
    private int idTamVang;
    private String soCccd;
    private LocalDate ngayBatDau, ngayKetThuc;
    private String lyDo;
    private String noiDangKyTamTru;
    private String trangThai;
    private String ten;

    /**
     * Khởi tạo một đối tượng TamVang mới với thông tin mặc định.
     */
    public String getTen() {
        return this.ten;
    }

    public void setTen(String name){
        this.ten = name;
    }


    public String getTrangThai() {
        return trangThai;
    }

    public TamVang(int idTamVang, String soCccd, LocalDate ngayBatDau, LocalDate ngayKetThuc, String lyDo, String noiDangKyTamTru, String trangThai) {
        this.idTamVang = idTamVang;
        this.soCccd = soCccd;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.lyDo = lyDo;
        this.noiDangKyTamTru = noiDangKyTamTru;
        this.trangThai = trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getNoiDangKyTamTru() {
        return noiDangKyTamTru;
    }

    public void setNoiDangKyTamTru(String noiDangKyTamTru) {
        this.noiDangKyTamTru = noiDangKyTamTru;
    }

    /**
     * Lấy ID của thông tin tạm vắng.
     *
     * @return ID của thông tin tạm vắng.
     */
    public int getIdTamVang() {
        return idTamVang;
    }

    /**
     * Đặt ID mới cho thông tin tạm vắng.
     *
     * @param idTamVang ID mới cho thông tin tạm vắng.
     */
    public void setIdTamVang(int idTamVang) {
        this.idTamVang = idTamVang;
    }

    /**
     * Lấy số chứng minh nhân dân của cư dân.
     *
     * @return Số chứng minh nhân dân của cư dân.
     */
    public String getSoCccd() {
        return soCccd;
    }

    /**
     * Đặt số chứng minh nhân dân mới cho thông tin tạm vắng.
     *
     * @param soCccd Số chứng minh nhân dân mới cho thông tin tạm vắng.
     */
    public void setSoCccd(String soCccd) {
        this.soCccd = soCccd;
    }

    /**
     * Lấy ngày bắt đầu của thời gian tạm vắng.
     *
     * @return Ngày bắt đầu của thời gian tạm vắng.
     */
    public LocalDate getNgayBatDau() {
        return ngayBatDau;
    }

    /**
     * Đặt ngày bắt đầu mới cho thông tin tạm vắng.
     *
     * @param ngayBatDau Ngày bắt đầu mới cho thông tin tạm vắng.
     */
    public void setNgayBatDau(LocalDate ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    /**
     * Lấy ngày kết thúc của thời gian tạm vắng.
     *
     * @return Ngày kết thúc của thời gian tạm vắng.
     */
    public LocalDate getNgayKetThuc() {
        return ngayKetThuc;
    }

    /**
     * Đặt ngày kết thúc mới cho thông tin tạm vắng.
     *
     * @param ngayKetThuc Ngày kết thúc mới cho thông tin tạm vắng.
     */
    public void setNgayKetThuc(LocalDate ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    /**
     * Lấy lý do của việc tạm vắng.
     *
     * @return Lý do của việc tạm vắng.
     */
    public String getLyDo() {
        return lyDo;
    }

    /**
     * Đặt lý do mới cho thông tin tạm vắng.
     *
     * @param lyDo Lý do mới cho thông tin tạm vắng.
     */
    public void setLyDo(String lyDo) {
        this.lyDo = lyDo;
    }
}