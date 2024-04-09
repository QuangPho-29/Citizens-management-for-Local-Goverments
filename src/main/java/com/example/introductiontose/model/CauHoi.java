package com.example.introductiontose.model;

public class CauHoi {
    private int idCauHoi;
    private String noiDungCauHoi;

    public CauHoi() {

    }
    public CauHoi(int idCauHoi, String noiDungCauHoi) {
        this.idCauHoi = idCauHoi;
        this.noiDungCauHoi = noiDungCauHoi;
    }

    public int getIdCauHoi() {
        return idCauHoi;
    }

    public void setIdCauHoi(int idCauHoi) {
        this.idCauHoi = idCauHoi;
    }

    public String getNoiDungCauHoi() {
        return noiDungCauHoi;
    }

    public void setNoiDungCauHoi(String noiDungCauHoi) {
        this.noiDungCauHoi = noiDungCauHoi;
    }
}
