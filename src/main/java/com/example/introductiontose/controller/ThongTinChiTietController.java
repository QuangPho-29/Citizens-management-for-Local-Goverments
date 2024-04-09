package com.example.introductiontose.controller;

//import stat

import com.example.introductiontose.model.ThongTinNhanKhau;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ThongTinChiTietController implements Initializable {
    @FXML
    private Label hoTen, soCCCD, noiCap, noiSinh, nguyenQuan, ngheNghiep, chuHo, ngayDangKyThuongTru, danToc,tonGiao, ngayCap,ngaySinh, biDanh, noilamViec, quanHeVoiChuHo;

    private ThongTinNhanKhau current_user = null;

    public ThongTinChiTietController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.hoTen.setText(current_user.getHoTen());
        this.chuHo.setText("");
        this.biDanh.setText(current_user.getBiDanh());
        this.danToc.setText(current_user.getDanToc());
        this.ngayCap.setText("");
        this.ngaySinh.setText(current_user.getNgaySinh().toString());
        this.ngheNghiep.setText(current_user.getNgheNghiep());
        this.nguyenQuan.setText(current_user.getNguyenQuan());
        this.noiCap.setText(current_user.getCccd().getNoiCap());
        this.ngayDangKyThuongTru.setText(current_user.getNgayDKTT().toString());
        this.noilamViec.setText(current_user.getNoiLamViec());
        this.noiSinh.setText(current_user.getNoiSinh());
        this.quanHeVoiChuHo.setText(current_user.getQuanHe());
        this.soCCCD.setText(current_user.getCccd().getSoCccd());
        this.tonGiao.setText(current_user.getTonGiao());
    }
}
