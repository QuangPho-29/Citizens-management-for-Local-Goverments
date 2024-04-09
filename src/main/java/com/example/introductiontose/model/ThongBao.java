package com.example.introductiontose.model;

import java.time.LocalDateTime;

/**
 * Lớp ThongBao đại diện cho thông báo trong hệ thống.
 */
public class ThongBao {
    private LocalDateTime ngaytao;
    private int idTHONGBAO;
    private String tieuDe;
    private String noidung;
    private int soCCCD;
    
    /**
     * Khởi tạo một đối tượng ThongBao mới với thông tin cung cấp.
     *
     * @param idTHONGBAO  ID của thông báo.
     * @param soCCCD      Số CCCD của nhân khẩu liên quan đến thông báo.
     * @param tieuDe      Tiêu đề của thông báo.
     * @param noidung     Nội dung của thông báo.
     * @param ngaytao     Ngày tạo thông báo.
     */
    public ThongBao(int idTHONGBAO, int soCCCD, String tieuDe, String noidung, LocalDateTime ngaytao) {
        this.ngaytao = ngaytao;
        this.idTHONGBAO = idTHONGBAO;
        this.tieuDe = tieuDe;
        this.noidung = noidung;
        this.soCCCD = soCCCD;
    }
    
    /**
     * Lấy ngày tạo của thông báo.
     *
     * @return Ngày tạo của thông báo.
     */
    public LocalDateTime getNgaytao() {
        return ngaytao;
    }
    
    /**
     * Thiết lập ngày tạo của thông báo.
     *
     * @param ngaytao Ngày tạo mới của thông báo.
     */
    public void setNgaytao(LocalDateTime ngaytao) {
        this.ngaytao = ngaytao;
    }
    
    /**
     * Lấy ID của thông báo.
     *
     * @return ID của thông báo.
     */
    public int getIdTHONGBAO() {
        return idTHONGBAO;
    }
    
    /**
     * Thiết lập ID của thông báo.
     *
     * @param idTHONGBAO ID mới của thông báo.
     */
    public void setIdTHONGBAO(int idTHONGBAO) {
        this.idTHONGBAO = idTHONGBAO;
    }
    
    /**
     * Lấy tiêu đề của thông báo.
     *
     * @return Tiêu đề của thông báo.
     */
    public String getTieuDe() {
        return tieuDe;
    }
    
    /**
     * Thiết lập tiêu đề mới của thông báo.
     *
     * @param tieuDe Tiêu đề mới của thông báo.
     */
    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }
    
    /**
     * Lấy nội dung của thông báo.
     *
     * @return Nội dung của thông báo.
     */
    public String getNoidung() {
        return noidung;
    }
    
    /**
     * Thiết lập nội dung mới của thông báo.
     *
     * @param noidung Nội dung mới của thông báo.
     */
    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }
    
    /**
     * Lấy số CCCD liên quan đến thông báo.
     *
     * @return Số CCCD liên quan đến thông báo.
     */
    public int getSoCCCD() {
        return soCCCD;
    }
    
    /**
     * Thiết lập số CCCD mới liên quan đến thông báo.
     *
     * @param soCCCD Số CCCD mới liên quan đến thông báo.
     */
    public void setSoCCCD(int soCCCD) {
        this.soCCCD = soCCCD;
    }
}
