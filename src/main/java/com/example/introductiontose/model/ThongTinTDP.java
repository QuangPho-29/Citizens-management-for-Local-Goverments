package com.example.introductiontose.model;

public class ThongTinTDP {
    private String ten;
    private double thuPhi;
    private double chuaDong;
    private double dongGop;
    private int soHoKhau;
    private int soNhanKhau;

    // Constructor không tham số
    public ThongTinTDP() {
    }

    // Constructor đầy đủ tham số
    public ThongTinTDP(String ten, double thuPhi, double chuaDong, double dongGop, int soHoKhau, int soNhanKhau) {
        this.ten = ten;
        this.thuPhi = thuPhi;
        this.chuaDong = chuaDong;
        this.dongGop = dongGop;
        this.soHoKhau = soHoKhau;
        this.soNhanKhau = soNhanKhau;
    }

    // Getters và Setters cho mỗi thuộc tính
    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getThuPhi() {
        return String.valueOf(thuPhi);
    }

    public void setThuPhi(double thuPhi) {
        this.thuPhi = thuPhi;
    }

    public String getChuaDong() {
        return String.valueOf(chuaDong);
    }

    public void setChuaDong(double chuaDong) {
        this.chuaDong = chuaDong;
    }

    public String getDongGop() {
        return String.valueOf(dongGop);
    }

    public void setDongGop(double dongGop) {
        this.dongGop = dongGop;
    }

    public String getSoHoKhau() {
        return String.valueOf(soHoKhau);
    }

    public void setSoHoKhau(int soHoKhau) {
        this.soHoKhau = soHoKhau;
    }

    public String getSoNhanKhau() {
        return String.valueOf(soNhanKhau);
    }

    public void setSoNhanKhau(int soNhanKhau) {
        this.soNhanKhau = soNhanKhau;
    }
}
