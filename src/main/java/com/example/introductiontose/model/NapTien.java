package com.example.introductiontose.model;

public class NapTien {
    private int idNapTien;
    private String soCccd;
    private int soTienNap;
    private String ghiChu;
    
    public NapTien(int idNapTien, String soCccd, int soTienNap, String ghiChu) {
        this.idNapTien = idNapTien;
        this.soCccd = soCccd;
        this.soTienNap = soTienNap;
        this.ghiChu = ghiChu;
        
    }
    
    public int getIdNapTien() {
        return idNapTien;
    }
    
    public void setIdNapTien(int idNapTien) {
        this.idNapTien = idNapTien;
    }
    
    public String getSoCccd() {
        return soCccd;
    }
    
    public void setSoCccd(String soCccd) {
        this.soCccd = soCccd;
    }
    
    public int getSoTienNap() {
        return soTienNap;
    }
    
    public void setSoTienNap(int soTienNap) {
        this.soTienNap = soTienNap;
    }
    
    public String getGhiChu() {
        return ghiChu;
    }
    
    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
}
