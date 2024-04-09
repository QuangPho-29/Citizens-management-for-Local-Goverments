package com.example.introductiontose.model;

import java.time.LocalDateTime;

public class ThayDoiNhanKhau {
    private int idThaydoi;
    private String soCccd;
    private String ghichu;
    private String noichuyenden;
    private LocalDateTime ngaychuyendi;
    private String trangthaithaidoi;
    public ThayDoiNhanKhau(int idThaydoi, String soCccd , String trangthaithaidoi, LocalDateTime ngaychuyendi,String noichuyenden, String ghichu ){
        this.idThaydoi=idThaydoi;
        this.soCccd=soCccd;
        this.trangthaithaidoi=trangthaithaidoi;
        this.ngaychuyendi=ngaychuyendi;
        this.noichuyenden=noichuyenden;
        this.ghichu=ghichu;
    }

    public ThayDoiNhanKhau() {

    }

    public int getIdThaydoi() {
        return idThaydoi;
    }
    public String getSoCccd() {
        return soCccd;
    }
    public LocalDateTime getNgaychuyendi() {
        return ngaychuyendi;
    }

    public String getNoichuyenden() {
        return noichuyenden;
    }

    public void setNoichuyenden(String noichuyenden) {
        this.noichuyenden = noichuyenden;
    }

    public String getGhichu() {
        return ghichu;
    }
    public String getTrangthaithaidoi() {
        return trangthaithaidoi;
    }
    public void setNgaychuyendi(LocalDateTime ngaychuyendi) {
        this.ngaychuyendi = ngaychuyendi;
    }

    public void setIdThaydoi(int idThaydoi) {
        this.idThaydoi = idThaydoi;
    }
    public void setSoCccd(String soCccd) {
        this.soCccd = soCccd;
    }
    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }
    public void setTrangthaithaidoi(String trangthaithaidoi) {
        this.trangthaithaidoi = trangthaithaidoi;
    }
}
