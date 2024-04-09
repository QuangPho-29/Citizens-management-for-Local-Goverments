package com.example.introductiontose.model;

public class CauTraLoi {
    private String soCccd;
    private String cauHoi;
    private String cauTraLoi;

    public CauTraLoi() {

    }

    public CauTraLoi(String soCccd, String cauHoi, String cauTraLoi) {
        this.soCccd = soCccd;
        this.cauHoi = cauHoi;
        this.cauTraLoi = cauTraLoi;
    }

    public String getSoCccd() {
        return soCccd;
    }

    public void setSoCccd(String soCccd) {
        this.soCccd = soCccd;
    }

    public String getCauHoi() {
        return cauHoi;
    }

    public void setCauHoi(String cauHoi) {
        this.cauHoi = cauHoi;
    }

    public String getCauTraLoi() {
        return cauTraLoi;
    }

    public void setCauTraLoi(String cauTraLoi) {
        this.cauTraLoi = cauTraLoi;
    }
}
