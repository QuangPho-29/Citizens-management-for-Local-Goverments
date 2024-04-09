package com.example.introductiontose.model;

import java.time.LocalDateTime;

/**
 * Lớp HoKhau đại diện cho thông tin của một hộ khẩu trong hệ thống.
 * Lớp này chứa các thông tin cơ bản về hộ khẩu như ID hộ khẩu, tên chủ hộ,
 * số căn cước công dân của chủ hộ, địa chỉ nhà và ngày tạo hộ khẩu.
 */
public class HoKhau {
    /**
     * ID của hộ khẩu.
     */
    private int idHoKhau;

    /**
     * Tên của chủ hộ.
     */
    private String tenChuHo;

    /**
     * Số căn cước công dân của chủ hộ.
     */
    private String soCccdChuHo;

    /**
     * Địa chỉ nhà của hộ khẩu.
     */
    private String diaChiNha;

    /**
     * Ngày tạo hộ khẩu.
     */
    private LocalDateTime ngayTaoHK;

    /**
     * Constructor mặc định.
     */
    public HoKhau() {
    }

    /**
     * Constructor với đầy đủ thông tin hộ khẩu.
     *
     * @param idHoKhau ID của hộ khẩu
     * @param tenChuHo Tên của chủ hộ
     * @param soCccdChuHo Số căn cước công dân của chủ hộ
     * @param diaChiNha Địa chỉ nhà
     * @param ngayTaoHK Ngày tạo hộ khẩu
     */
    public HoKhau(int idHoKhau, String tenChuHo, String soCccdChuHo, String diaChiNha, LocalDateTime ngayTaoHK) {
        this.idHoKhau = idHoKhau;
        this.tenChuHo = tenChuHo;
        this.soCccdChuHo = soCccdChuHo;
        this.diaChiNha = diaChiNha;
        this.ngayTaoHK = ngayTaoHK;
    }

    // Getters và Setters

    /**
     * Lấy ID của hộ khẩu.
     *
     * @return ID của hộ khẩu.
     */
    public int getIdHoKhau() {
        return idHoKhau;
    }

    /**
     * Thiết lập ID cho hộ khẩu.
     *
     * @param idHoKhau ID mới của hộ khẩu.
     */
    public void setIdHoKhau(int idHoKhau) {
        this.idHoKhau = idHoKhau;
    }

    /**
     * Lấy tên của chủ hộ.
     *
     * @return Tên của chủ hộ.
     */
    public String getTenChuHo() {
        return tenChuHo;
    }

    /**
     * Thiết lập tên cho chủ hộ.
     *
     * @param tenChuHo Tên mới của chủ hộ.
     */
    public void setTenChuHo(String tenChuHo) {
        this.tenChuHo = tenChuHo;
    }

    /**
     * Lấy số căn cước công dân của chủ hộ.
     *
     * @return Số căn cước công dân của chủ hộ.
     */
    public String getSoCccdChuHo() {
        return soCccdChuHo;
    }

    /**
     * Thiết lập số căn cước công dân mới cho chủ hộ.
     *
     * @param soCccdChuHo Số căn cước công dân mới của chủ hộ.
     */
    public void setSoCccdChuHo(String soCccdChuHo) {
        this.soCccdChuHo = soCccdChuHo;
    }

    /**
     * Lấy địa chỉ nhà của hộ khẩu.
     *
     * @return Địa chỉ nhà.
     */
    public String getDiaChiNha() {
        return diaChiNha;
    }

    /**
     * Thiết lập địa chỉ nhà mới cho hộ khẩu.
     *
     * @param diaChiNha Địa chỉ nhà mới.
     */
    public void setDiaChiNha(String diaChiNha) {
        this.diaChiNha = diaChiNha;
    }

    /**
     * Lấy ngày tạo hộ khẩu.
     *
     * @return Ngày tạo của hộ khẩu.
     */
    public LocalDateTime getNgayTaoHK() {
        return ngayTaoHK;
    }

    /**
     * Thiết lập ngày tạo mới cho hộ khẩu.
     *
     * @param ngayTaoHK Ngày tạo mới của hộ khẩu.
     */
    public void setNgayTaoHK(LocalDateTime ngayTaoHK) {
        this.ngayTaoHK = ngayTaoHK;
    }
}

