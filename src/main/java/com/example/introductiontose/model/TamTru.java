package com.example.introductiontose.model;

import java.time.LocalDate;

public class TamTru {
    private String soCCCD;
    private String cccdChuHo;
    private String hoTen, biDanh;
    private String gioiTinh;
    private String nguyenQuan;
    private String danToc;
    private String tonGiao;
    private String ngheNghiep;
    private String noiLamViec;
    private String noiCap;
    private String quanHe;
    private String lyDo;
    private LocalDate ngaysinh;
    private LocalDate ngayCap, ngayKetThuc, ngayBatDau;
    private String trangThai;
    private String soDienThoai;
    private String hoTenChuHo;
    private String noiDangKyTamTru;

    public String getNoiDangKyTamTru(){
        return noiDangKyTamTru;
    }

    public void setNoiDangKyTamTru(String name){
        this.noiDangKyTamTru = name;
    }
    public String getHoTenChuHo(){
        return hoTenChuHo;
    }

    public void setHoTenChuHo(String name){
        this.hoTenChuHo = name;
    }

    public TamTru() {};

    public TamTru(String soCCCD, String cccdChuHo, String hoTen, String biDanh, String gioiTinh, String nguyenQuan, String danToc, String tonGiao, String ngheNghiep, String noiLamViec, String noiCap, String quanHe, String lyDo, LocalDate ngaysinh, LocalDate ngayCap, LocalDate ngayKetThuc, LocalDate ngayBatDau, String trangThai, String soDienThoai) {

        this.soCCCD = soCCCD;
        this.cccdChuHo = cccdChuHo;
        this.hoTen = hoTen;
        this.biDanh = biDanh;
        this.gioiTinh = gioiTinh;
        this.nguyenQuan = nguyenQuan;
        this.danToc = danToc;
        this.tonGiao = tonGiao;
        this.ngheNghiep = ngheNghiep;
        this.noiLamViec = noiLamViec;
        this.noiCap = noiCap;
        this.quanHe = quanHe;
        this.lyDo = lyDo;
        this.ngaysinh = ngaysinh;
        this.ngayCap = ngayCap;
        this.ngayKetThuc = ngayKetThuc;
        this.ngayBatDau = ngayBatDau;
        this.trangThai = trangThai;
        this.soDienThoai = soDienThoai;
    }

    public String getSoCCCD() {
        return soCCCD;
    }

    public void setSoCCCD(String soCCCD) {
        this.soCCCD = soCCCD;
    }

    public String getCccdChuHo() {
        return cccdChuHo;
    }

    public void setCccdChuHo(String cccdChuHo) {
        this.cccdChuHo = cccdChuHo;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getBiDanh() {
        return biDanh;
    }

    public void setBiDanh(String biDanh) {
        this.biDanh = biDanh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getNguyenQuan() {
        return nguyenQuan;
    }

    public void setNguyenQuan(String nguyenQuan) {
        this.nguyenQuan = nguyenQuan;
    }

    public String getDanToc() {
        return danToc;
    }

    public void setDanToc(String danToc) {
        this.danToc = danToc;
    }

    public String getTonGiao() {
        return tonGiao;
    }

    public void setTonGiao(String tonGiao) {
        this.tonGiao = tonGiao;
    }

    public String getNgheNghiep() {
        return ngheNghiep;
    }

    public void setNgheNghiep(String ngheNghiep) {
        this.ngheNghiep = ngheNghiep;
    }

    public String getNoiLamViec() {
        return noiLamViec;
    }

    public void setNoiLamViec(String noiLamViec) {
        this.noiLamViec = noiLamViec;
    }

    public String getNoiCap() {
        return noiCap;
    }

    public void setNoiCap(String noiCap) {
        this.noiCap = noiCap;
    }

    public String getQuanHe() {
        return quanHe;
    }

    public void setQuanHe(String quanHe) {
        this.quanHe = quanHe;
    }

    public String getLyDo() {
        return lyDo;
    }

    public void setLyDo(String lyDo) {
        this.lyDo = lyDo;
    }

    public LocalDate getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(LocalDate ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public LocalDate getNgayCap() {
        return ngayCap;
    }

    public void setNgayCap(LocalDate ngayCap) {
        this.ngayCap = ngayCap;
    }

    public LocalDate getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(LocalDate ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public LocalDate getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(LocalDate ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }


}