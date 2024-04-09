package com.example.introductiontose.model.key;

import java.io.Serializable;

// Class khóa chính
public class HoKhauKey implements Serializable {
    private int idHoKhau;

    public HoKhauKey(int idHoKhau) {
        this.idHoKhau = idHoKhau;
    }

    public int getIdHoKhau() {
        return idHoKhau;
    }

    public void setIdHoKhau(int idHoKhau) {
        this.idHoKhau = idHoKhau;
    }
}
