package com.example.introductiontose.model;

import java.time.LocalDateTime;

/**
 * Lớp ThongTinNhanKhauController đại diện cho thông tin chi tiết của một nhân khẩu.
 */
public class ThongTinNhanKhau {
    private CCCD cccd;
    private int idHoKhau;
    private String hoTen;
    private String biDanh;
    private LocalDateTime ngaySinh;
    private String noiSinh;
    private String nguyenQuan;
    private String danToc;
    private String tonGiao;
    private String ngheNghiep;
    private String noiLamViec;
    private LocalDateTime ngayDKTT;
    private String diaChiCu;
    private String quanHe;
    
    /**
     * Khởi tạo một đối tượng ThongTinNhanKhauController mới không có tham số.
     */
    public ThongTinNhanKhau() {
    }
    
    /**
     * Khởi tạo một đối tượng ThongTinNhanKhauController với các thông tin cơ bản.
     *
     * @param cccd        Thông tin về Chứng minh nhân dân của nhân khẩu.
     * @param idHoKhau   ID hoặc số nhà của hộ khẩu mà nhân khẩu thuộc về.
     * @param hoTen      Họ và tên của nhân khẩu.
     * @param biDanh      Bí danh của nhân khẩu (nếu có).
     * @param ngaySinh    Ngày sinh của nhân khẩu.
     * @param noiSinh     Nơi sinh của nhân khẩu.
     * @param nguyenQuan  Nguyên quán của nhân khẩu.
     * @param danToc      Dân tộc của nhân khẩu.
     * @param tonGiao     Tôn giáo của nhân khẩu.
     * @param ngheNghiep  Nghề nghiệp của nhân khẩu.
     * @param noiLamViec  Nơi làm việc của nhân khẩu.
     * @param ngayDKTT    Ngày đăng ký tạm trú của nhân khẩu.
     * @param diaChiCu    Địa chỉ cũ của nhân khẩu (nếu có).
     * @param quanHe      Mối quan hệ với chủ hộ hoặc người nào đó trong hộ khẩu.
     */
    public ThongTinNhanKhau(CCCD cccd, int idHoKhau, String hoTen, String biDanh, LocalDateTime ngaySinh, String noiSinh, String nguyenQuan, String danToc, String tonGiao, String ngheNghiep, String noiLamViec, LocalDateTime ngayDKTT, String diaChiCu, String quanHe) {
        this.cccd = cccd;
        this.idHoKhau = idHoKhau;
        this.hoTen = hoTen;
        this.biDanh = biDanh;
        this.ngaySinh = ngaySinh;
        this.noiSinh = noiSinh;
        this.nguyenQuan = nguyenQuan;
        this.danToc = danToc;
        this.tonGiao = tonGiao;
        this.ngheNghiep = ngheNghiep;
        this.noiLamViec = noiLamViec;
        this.ngayDKTT = ngayDKTT;
        this.diaChiCu = diaChiCu;
        this.quanHe = quanHe;
    }
    
    public CCCD getCccd() {
        return cccd;
    }
    
    public void setCccd(CCCD cccd) {
        this.cccd = cccd;
    }
    
    public int getIdHoKhau() {
        return idHoKhau;
    }

    public void setIdHoKhau(int idHoKhau) {
        this.idHoKhau = idHoKhau;
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

    public LocalDateTime getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(LocalDateTime ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getNoiSinh() {
        return noiSinh;
    }

    public void setNoiSinh(String noiSinh) {
        this.noiSinh = noiSinh;
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

    public LocalDateTime getNgayDKTT() {
        return ngayDKTT;
    }

    public void setNgayDKTT(LocalDateTime ngayDKTT) {
        this.ngayDKTT = ngayDKTT;
    }

    public String getDiaChiCu() {
        return diaChiCu;
    }

    public void setDiaChiCu(String diaChiCu) {
        this.diaChiCu = diaChiCu;
    }

    public String getQuanHe() {
        return quanHe;
    }

    public void setQuanHe(String quanHe) {
        this.quanHe = quanHe;
    }
}
