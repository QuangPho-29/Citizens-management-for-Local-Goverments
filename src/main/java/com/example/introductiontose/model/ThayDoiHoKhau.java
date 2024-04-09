package com.example.introductiontose.model;

import java.time.LocalDateTime;

public class ThayDoiHoKhau {
    private int idThayDoiHoKhau;
    private int idHoKhau;
    private String trangThai;
    private String soCccdChuHoMoi;
    private String noiDung;
    private LocalDateTime ngayThayDoi;

    public ThayDoiHoKhau(int idThayDoiHoKhau, int idHoKhau, String trangThai, String soCccdChuHoMoi, String noiDung, LocalDateTime ngayThayDoi) {
        this.idThayDoiHoKhau = idThayDoiHoKhau;
        this.idHoKhau = idHoKhau;
        this.trangThai = trangThai;
        this.soCccdChuHoMoi = soCccdChuHoMoi;
        this.noiDung = noiDung;
        this.ngayThayDoi = ngayThayDoi;
    }

    public int getIdThayDoiHoKhau() {
        return idThayDoiHoKhau;
    }

    public void setIdThayDoiHoKhau(int idThayDoiHoKhau) {
        this.idThayDoiHoKhau = idThayDoiHoKhau;
    }

    public int getIdHoKhau() {
        return idHoKhau;
    }

    public void setIdHoKhau(int idHoKhau) {
        this.idHoKhau = idHoKhau;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getSoCccdChuHoMoi() {
        return soCccdChuHoMoi;
    }

    public void setSoCccdChuHoMoi(String soCccdChuHoMoi) {
        this.soCccdChuHoMoi = soCccdChuHoMoi;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public LocalDateTime getNgayThayDoi() {
        return ngayThayDoi;
    }

    public void setNgayThayDoi(LocalDateTime ngayThayDoi) {
        this.ngayThayDoi = ngayThayDoi;
    }
}
