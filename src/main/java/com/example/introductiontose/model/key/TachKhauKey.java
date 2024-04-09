package com.example.introductiontose.model.key;

import java.io.Serializable;

// Class khóa chính
public class TachKhauKey implements Serializable {
    private String soCccdChuHoMoi;
    private int idHoKhau;

    public TachKhauKey(String soCccdChuHoMoi, int idHoKhau) {
        this.soCccdChuHoMoi = soCccdChuHoMoi;
        this.idHoKhau = idHoKhau;
    }

    public String getSoCccdChuHoMoi() {
        return soCccdChuHoMoi;
    }

    public void setSoCccdChuHoMoi(String soCccdChuHoMoi) {
        this.soCccdChuHoMoi = soCccdChuHoMoi;
    }

    public int getIdHoKhau() {
        return idHoKhau;
    }

    public void setIdHoKhau(int idHoKhau) {
        this.idHoKhau = idHoKhau;
    }
}
