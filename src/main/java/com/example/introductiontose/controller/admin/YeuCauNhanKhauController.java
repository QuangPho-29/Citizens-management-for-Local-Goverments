package com.example.introductiontose.controller.admin;

import com.example.introductiontose.dao.*;
import com.example.introductiontose.database.SqlConnection;
import com.example.introductiontose.model.*;
import com.example.introductiontose.model.key.HoKhauKey;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import static com.example.introductiontose.util.AlertDuyetYeuCau.showAlertDongY;
import static com.example.introductiontose.util.AlertDuyetYeuCau.showAlertHuyBo;

public class YeuCauNhanKhauController implements Initializable {
    @FXML
    private VBox VBoxList;
    @FXML
    private Text hienThiChiTiet;
    @FXML
    private TextField noidungtimkiem;
    @FXML
    private HBox hBoxTimKiem;

    List<TamVang> tamvangList;
    List<TamTru> tamtruList;
    public static List<HBox> danhsachHBox = new ArrayList<>();


    public void trangchuContructor(String HBoxID){

    }
    Connection connection = SqlConnection.connect();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ThayDoiNhanKhauDao tdnkDao = new ThayDoiNhanKhauDao(connection);
        List<ThayDoiNhanKhau> danhsachThayDoiNK = new ArrayList<>();
        try {
            danhsachThayDoiNK = tdnkDao.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for(ThayDoiNhanKhau tdnk : danhsachThayDoiNK) {
            if(tdnk.getTrangthaithaidoi().equals("chờ xác nhận")) {
                List<String> thongtinhankhauthaydoi = LayThongTinNhanKhauThayDoi(tdnk);
                String idHBox = thongtinhankhauthaydoi.get(0);
                String kieuYeuCau = thongtinhankhauthaydoi.get(1);
                String nguoiGuiYeuCau = thongtinhankhauthaydoi.get(2);
                String ghiChu = thongtinhankhauthaydoi.get(3);

                HBox hbox = initHBox(idHBox, kieuYeuCau, nguoiGuiYeuCau, ghiChu);
                Insets hboxMargin = new Insets(10, 10, 0, 10);
                VBoxList.getChildren().add(hbox);
                VBoxList.setMargin(hbox, hboxMargin);
                danhsachHBox.add(hbox);
            }
        }

        ///////////////////////////////////////////////////

        ThayDoiHoKhauDAO tdhokhauDao = new ThayDoiHoKhauDAO(connection);
        List<ThayDoiHoKhau> danhsachThayDoiHK = new ArrayList<>();
        try {
            danhsachThayDoiHK = tdhokhauDao.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for(ThayDoiHoKhau tdhk : danhsachThayDoiHK) {
            if(tdhk.getTrangThai().equals("chờ xác nhận")) {
                List<String> thongtinhokhauthaydoi = LayThongTinHoKhauThayDoi(tdhk);
                String idHBox = thongtinhokhauthaydoi.get(0);
                String kieuYeuCau = thongtinhokhauthaydoi.get(1);
                String nguoiGuiYeuCau = thongtinhokhauthaydoi.get(2);
                String ghiChu = thongtinhokhauthaydoi.get(3);

                HBox hbox = initHBox(idHBox, kieuYeuCau, nguoiGuiYeuCau, ghiChu);
                Insets hboxMargin = new Insets(10, 10, 0, 10);
                VBoxList.getChildren().add(hbox);
                VBoxList.setMargin(hbox, hboxMargin);
                danhsachHBox.add(hbox);
            }
        }

        ///////////////////////////////////////////////////////

        TamVangDAO tamVangDAO = new TamVangDAO(connection);
        try {
            this.tamvangList = tamVangDAO.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for(TamVang tv : tamvangList) {
            if(tv.getTrangThai().equals("chờ xác nhận")) {
                String idHBox = "TV" + tv.getIdTamVang();
                String kieuYeuCau = "Yêu cầu: tạm vắng";
                String nguoiGuiYeuCau = "Người yêu cầu: " + tv.getTen();
                String ghiChu ="Lý do: " + tv.getLyDo();

                HBox hbox = initHBox(idHBox, kieuYeuCau, nguoiGuiYeuCau, ghiChu);
                Insets hboxMargin = new Insets(10, 10, 0, 10);
                VBoxList.getChildren().add(hbox);
                VBoxList.setMargin(hbox, hboxMargin);
                danhsachHBox.add(hbox);
            }
        }

        ///////////////////////////////////////////////////


        TamTruDAO tamtruDAO = new TamTruDAO(connection);
        this.tamtruList = tamtruDAO.getAll();

        for(TamTru tt : tamtruList) {
            if(tt.getTrangThai().equals("chờ xác nhận")) {
                String idHBox = "TT" + tt.getSoCCCD();
                String kieuYeuCau = "Yêu cầu: tạm trú";
                String nguoiGuiYeuCau = "Người yêu cầu: " + tt.getHoTen();
                String ghiChu ="Lý do: " + tt.getLyDo();

                HBox hbox = initHBox(idHBox, kieuYeuCau, nguoiGuiYeuCau, ghiChu);
                Insets hboxMargin = new Insets(10, 10, 0, 10);
                VBoxList.getChildren().add(hbox);
                VBoxList.setMargin(hbox, hboxMargin);
                danhsachHBox.add(hbox);
            }
        }

    }
    public Button initButtonDongY() {
        Button buttonDongY = new Button(" V ");
        buttonDongY.setStyle("-fx-background-color: #18B3A7; -fx-background-radius: 5; -fx-text-fill: #3D5654");
        return buttonDongY;
    }
    public Button initButtonHuyBo() {
        Button buttonHuyBo = new Button(" X ");
        buttonHuyBo.setStyle("-fx-background-color: #ED6A5AFF; -fx-background-radius: 5; -fx-text-fill: #3D5654");
        return buttonHuyBo;
    }
    public VBox initVBox(String kieuYeuCau, String nguoiGuiYeuCau, String ghiChu) {
        VBox vbox = new VBox();
        vbox.setPrefHeight(74.5);
        vbox.setPrefWidth(225.0);
        vbox.setSpacing(8);

        // Thêm 3 Label vào VBox
        Label kieuYeuCauLabel = new Label(kieuYeuCau);
        Label thongTinNguoiGuiLabel = new Label(nguoiGuiYeuCau);
        Label ghiChuLabel = new Label(ghiChu);

        vbox.getChildren().addAll(kieuYeuCauLabel, thongTinNguoiGuiLabel, ghiChuLabel);
        vbox.setAlignment(Pos.CENTER_LEFT);

        return vbox;
    }

    public HBox initHBox(String idHBox, String kieuYeuCau, String nguoiGuiYeuCau, String ghiChu ) {
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setPrefHeight(80.0);
        hbox.setPrefWidth(355.5);
        hbox.setStyle("-fx-background-color: #f9f9f9; -fx-background-radius: 10;");
        Insets hboxPadding = new Insets(18);
        hbox.setPadding(hboxPadding);
        Button buttonYes = initButtonDongY();
        Button buttonNo = initButtonHuyBo();
        VBox vbox = initVBox(kieuYeuCau, nguoiGuiYeuCau, ghiChu);
        Insets buttonMargin = new Insets(0, 10, 0, 10); // Margin bên phải của buttonDongY là 10px
        HBox.setMargin(buttonYes, buttonMargin);
        hbox.getChildren().addAll(vbox, buttonYes, buttonNo);

        hbox.setId(idHBox);
        vbox.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // Gọi hàm ChiTietThongTin khi click vào VBox
                ChiTietThongTin(vbox);
            }
        });

        buttonNo.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    clickButton(buttonNo);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        buttonYes.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    clickButton(buttonYes);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        return hbox;
    }

    public void ChiTietThongTin(VBox vbox) {
        HBox parentHBox = (HBox) vbox.getParent();
        String idHBox = parentHBox.getId();
        if(idHBox.substring(0,2).equals("NK")) {
            String idThayDoiString = idHBox.substring(2);
            Integer idThayDoi = Integer.parseInt(idThayDoiString);
            ThayDoiNhanKhauDao tdnkDao = new ThayDoiNhanKhauDao(connection);
            Optional<ThayDoiNhanKhau> resultTDNK;
            try {
                resultTDNK = tdnkDao.get(idThayDoi);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if(resultTDNK.isPresent()) {
                ThayDoiNhanKhau tdnk = resultTDNK.get();
                List<String> thongtinnhankhauthaydoi = LayThongTinNhanKhauThayDoi(tdnk);
                String chitiet = "";
                if(tdnk.getGhichu().contains("qua đời")) {
                    chitiet = thongtinnhankhauthaydoi.get(1) + "\n\n"
                            + thongtinnhankhauthaydoi.get(2) + "\n\n"
                            + thongtinnhankhauthaydoi.get(3) + "\n\n"
                            + "Thông tin của người mất: \n"
                            + thongtinnhankhauthaydoi.get(5) + "\n\n";
                } else {
                    chitiet = thongtinnhankhauthaydoi.get(1) + "\n\n"
                            + thongtinnhankhauthaydoi.get(2) + "\n\n"
                            + "Thông tin cá nhân cơ bản: \n"
                            + thongtinnhankhauthaydoi.get(5) + "\n\n"
                            + thongtinnhankhauthaydoi.get(3) + "\n\n"
                            + thongtinnhankhauthaydoi.get(4) + "\n\n";
                }

                hienThiChiTiet.setText(chitiet);
            }

        }

        if(idHBox.substring(0,2).equals("HK")) {
            String idThayDoiString = idHBox.substring(2);
            Integer idThayDoi = Integer.parseInt(idThayDoiString);
            ThayDoiHoKhauDAO tdhkDao = new ThayDoiHoKhauDAO(connection);
            Optional<ThayDoiHoKhau> resultTDHK;
            try {
                resultTDHK = tdhkDao.get(idThayDoi);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if(resultTDHK.isPresent()) {
                ThayDoiHoKhau tdhk = resultTDHK.get();
                List<String> thongtinhokhauthaydoi = LayThongTinHoKhauThayDoi(tdhk);
                String chitiet = thongtinhokhauthaydoi.get(1) + "\n\n"
                        + thongtinhokhauthaydoi.get(2) + "\n\n"
                        + thongtinhokhauthaydoi.get(3) + "\n\n"
                        + thongtinhokhauthaydoi.get(5) + "\n\n"
                        + thongtinhokhauthaydoi.get(4) + "\n\n";
                hienThiChiTiet.setText(chitiet);
            }
        }

        if(idHBox.substring(0,2).equals("TV")){
            TamVang tmp = null;
            int idTamVang = Integer.parseInt(idHBox.substring(2));
            for(TamVang tv : tamvangList){
                if(tv.getIdTamVang() == idTamVang){
                    tmp = tv;
                    break;
                }
            }
            String chitiet = "Yêu cầu: xin tạm vắng" + "\n\n"
                    + "Người yêu cầu: " + tmp.getTen() + "\n"
                    + "Số CCCD: " + tmp.getSoCccd() + "\n\n"
                    + "Ngày bắt đầu: " + tmp.getNgayBatDau() +"\n"
                    + "Ngày kết thúc: " + tmp.getNgayKetThuc() + "\n\n"
                    + "Nơi đăng ký tạm trú: " + tmp.getNoiDangKyTamTru() + "\n\n"
                    + "Lý do: " + tmp.getLyDo() +"\n";

            hienThiChiTiet.setText(chitiet);
        }

        if(idHBox.substring(0,2).equals("TT")){
            TamTru tmp = null;
            String idTamTru = idHBox.substring(2);
            for(TamTru tv : tamtruList){
                if(tv.getSoCCCD().equals(idTamTru)){
                    tmp = tv;
                    break;
                }
            }

            String chitiet = "Yêu cầu: xin tạm trú" + "\n\n"
                    + "Chi tiết người đăng kí tạm trú: " +"\n\n"
                    + "\tHọ tên: " + tmp.getHoTen() + "\n"
                    + "\tBí danh: " + tmp.getBiDanh() + "\n"
                    + "\tSố CCCD: " + tmp.getSoCCCD() +"\n"
                    + "\tNơi cấp: " + tmp.getNoiCap() + "\n"
                    + "\tQuê quán: " + tmp.getNguyenQuan() + "\n"
                    + "\tNgày sinh: " + tmp.getNgaysinh() + "\n"
                    + "\tGiới tính: " + tmp.getGioiTinh() + "\n"
                    + "\tDân tộc: " + tmp.getDanToc() + "\n"
                    + "\tSố điện thoại: " + tmp.getSoDienThoai() + "\n"
                    + "\tNghề nghiệp: " +tmp.getNgheNghiep() + "\n"
                    + "\tNơi làm việc: " +tmp.getNoiLamViec() + "\n"
                    + "\tNgày bắt đầu: " + tmp.getNgayBatDau() +"\n"
                    + "\tNgày kết thúc: " + tmp.getNgayKetThuc() + "\n\n"
                    + "Chủ hộ: " + tmp.getHoTenChuHo() + "\n"
                    + "Quan hệ với chủ hộ: " + tmp.getQuanHe() + "\n\n"
                    + "Lý do: " + tmp.getLyDo() +"\n";

            hienThiChiTiet.setText(chitiet);
        }
    }

    public List<String> LayThongTinNhanKhauThayDoi(ThayDoiNhanKhau tdnk) {
        List<String> thongtinnhankhau = new ArrayList<>();
        String idHBox = "NK" + String.valueOf(tdnk.getIdThaydoi());
        String YeuCau = "Yêu cầu: Thay đổi nhân khẩu";
        String nguoiGuiYeuCau = "";
        String ghiChu = "";
        String chiTietChuyenDi = "";
        String thongTinCaNhan = "";

        String soCccd = tdnk.getSoCccd();
        NhanKhauDAO nkDao = new NhanKhauDAO(connection, NhanKhauDAO.TableType.NHANKHAU);
        Optional<NhanKhau> resultNhanKhau = null;
        NhanKhau nhankhau = new NhanKhau();
        try {
            resultNhanKhau = nkDao.get(soCccd);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(resultNhanKhau.isPresent()) {
            nhankhau = resultNhanKhau.get();
            nguoiGuiYeuCau = "Người gửi yêu cầu: " + nhankhau.getThongTinNhanKhau().getHoTen();
        }

        int idHoKhau = nhankhau.getThongTinNhanKhau().getIdHoKhau();
        HoKhauDAO hoKhauDAO = new HoKhauDAO(connection);
        HoKhau hoKhau = new HoKhau();
        Optional<HoKhau> resultHK = null;
        try {
            resultHK = hoKhauDAO.get(new HoKhauKey(idHoKhau));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(resultHK.isPresent()) {
            hoKhau = resultHK.get();
        }

        if(tdnk.getGhichu().contains("qua đời")) {
            System.out.println(tdnk.getGhichu());
            String soCccdQuaDoi = tdnk.getGhichu().substring(0, 12);
            System.out.println(soCccdQuaDoi);
            Optional<NhanKhau> resultNhanKhauQuaDoi = null;
            try {
                resultNhanKhauQuaDoi = nkDao.get(soCccdQuaDoi);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if(resultNhanKhauQuaDoi.isPresent()) {
                NhanKhau nhankhauQuaDoi = resultNhanKhauQuaDoi.get();
                String nguoiQuaDoi = nhankhauQuaDoi.getThongTinNhanKhau().getHoTen();
                System.out.println(nguoiQuaDoi);
                ghiChu = "Ghi chú: Khai tử cho nhân khẩu " + nguoiQuaDoi;
                thongTinCaNhan = " + Họ và tên: " + nhankhauQuaDoi.getThongTinNhanKhau().getHoTen() + "\n" +
                        " + Số CCCD: " + nhankhauQuaDoi.getThongTinNhanKhau().getCccd().getSoCccd() + "\n"
                        + " + Ngày sinh: " + nhankhauQuaDoi.getThongTinNhanKhau().getNgaySinh().toLocalDate() + "\n"
                        + " + Địa chỉ nhà: " + hoKhau.getDiaChiNha();
            }
        } else {
            ghiChu = "Ghi chú: chuyển đi nơi khác";
            chiTietChuyenDi = "Ngày chuyển đi: " + tdnk.getNgaychuyendi().toLocalDate() + "\n"
                    + "Nơi chuyển đến: " + tdnk.getNoichuyenden() + "\n";
            thongTinCaNhan = " + Số CCCD: " + nhankhau.getThongTinNhanKhau().getCccd().getSoCccd() + "\n"
                    + " + Ngày sinh: " + nhankhau.getThongTinNhanKhau().getNgaySinh().toLocalDate() + "\n"
                    + " + Địa chỉ nhà: " + hoKhau.getDiaChiNha();
        }

        thongtinnhankhau.add(idHBox);
        thongtinnhankhau.add(YeuCau);
        thongtinnhankhau.add(nguoiGuiYeuCau);
        thongtinnhankhau.add(ghiChu);
        thongtinnhankhau.add(chiTietChuyenDi);
        thongtinnhankhau.add(thongTinCaNhan);
        return thongtinnhankhau;
    }

    public List<String> LayThongTinHoKhauThayDoi(ThayDoiHoKhau tdhk) {
        List<String> thongtinhokhau = new ArrayList<>();

        String idHBox = "HK" + String.valueOf(tdhk.getIdThayDoiHoKhau());
        String kieuYeuCau = "Yêu cầu: Thay đổi hộ khẩu";
        String nguoiGuiYeuCau = "";
        String ghiChu = "Nội dung thay đổi: " + tdhk.getNoiDung();
        String thongTinChuHoMoi = "";
        String diaChiNha = "";
        String soCccdChuHoCu = "";
        String soCccdChuHoMoi = "";

        NhanKhauDAO nhanKhauDAO = new NhanKhauDAO(connection, NhanKhauDAO.TableType.NHANKHAU);
        NhanKhau chuHoMoi = new NhanKhau();
        Optional<NhanKhau> resultChuHoMoi = null;
        try {
            resultChuHoMoi = nhanKhauDAO.get(tdhk.getSoCccdChuHoMoi());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(resultChuHoMoi.isPresent()) {
            chuHoMoi = resultChuHoMoi.get();
            thongTinChuHoMoi = "Thông tin chủ hộ mới: \n" +
                    " + Họ và tên: " + chuHoMoi.getThongTinNhanKhau().getHoTen() + "\n" +
                    " + Số CCCD: " + chuHoMoi.getThongTinNhanKhau().getCccd().getSoCccd() + "\n" +
                    " + Nghề nghiệp: " + chuHoMoi.getThongTinNhanKhau().getNgheNghiep()  + "\n" +
                    " + Quan hệ với chủ hộ cũ: " + chuHoMoi.getThongTinNhanKhau().getQuanHe() + "\n";
        } else {
            thongTinChuHoMoi = "Địa chỉ nhà mới: " + tdhk.getSoCccdChuHoMoi();
        }

        int idHoKhau = tdhk.getIdHoKhau();
        HoKhauDAO hoKhauDAO = new HoKhauDAO(connection);
        HoKhau hokhau = new HoKhau();
        Optional<HoKhau> resultHoKhau = null;
        try {
            resultHoKhau = hoKhauDAO.get(new HoKhauKey(idHoKhau));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(resultHoKhau.isPresent()) {
            hokhau = resultHoKhau.get();
            nguoiGuiYeuCau = "Hộ yêu cầu thay đổi:  " + hokhau.getIdHoKhau() + " - " + hokhau.getTenChuHo();
            diaChiNha = "Địa chỉ nhà: " + hokhau.getDiaChiNha();
            soCccdChuHoCu = hokhau.getSoCccdChuHo();
            soCccdChuHoMoi = tdhk.getSoCccdChuHoMoi();
        }

        thongtinhokhau.add(idHBox);
        thongtinhokhau.add(kieuYeuCau);
        thongtinhokhau.add(nguoiGuiYeuCau);
        thongtinhokhau.add(ghiChu);
        thongtinhokhau.add(thongTinChuHoMoi);
        thongtinhokhau.add(diaChiNha);
        thongtinhokhau.add(soCccdChuHoCu);
        thongtinhokhau.add(soCccdChuHoMoi);
        return thongtinhokhau;
    }

    public void clickButton(Button button) throws SQLException {
        HBox parentHBox = (HBox) button.getParent();
        String idHBox = parentHBox.getId();

        if(idHBox.substring(0,2).equals("NK")) {
            String idThayDoiString = idHBox.substring(2);
            Integer idThayDoi = Integer.parseInt(idThayDoiString);
            ThayDoiNhanKhauDao tdnkDao = new ThayDoiNhanKhauDao(connection);
            Optional<ThayDoiNhanKhau> resultTDNK;
            try {
                resultTDNK = tdnkDao.get(idThayDoi);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if(resultTDNK.isPresent()) {
                ThayDoiNhanKhau tdnk = resultTDNK.get();
                String soCccd = tdnk.getSoCccd();
                if(button.getText().equals("Hủy bỏ")) {
                    showAlertHuyBo(idHBox, soCccd, null, button);
                }
                if(button.getText().equals("Đồng ý")) {
                    showAlertDongY(idHBox, soCccd, null, button);
                }
            }
        }

        if(idHBox.substring(0,2).equals("HK")) {
            String idThayDoiString = idHBox.substring(2);
            Integer idThayDoi = Integer.parseInt(idThayDoiString);
            ThayDoiHoKhauDAO tdhkDao = new ThayDoiHoKhauDAO(connection);
            Optional<ThayDoiHoKhau> resultTDHK;
            try {
                resultTDHK = tdhkDao.get(idThayDoi);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if(resultTDHK.isPresent()) {
                ThayDoiHoKhau tdhk = resultTDHK.get();
                List<String> thongtinhokhauthaydoi = LayThongTinHoKhauThayDoi(tdhk);
                String soCccd1 = thongtinhokhauthaydoi.get(6);
                String soCccd2 = thongtinhokhauthaydoi.get(7);
                if(button.getText().equals("Hủy bỏ")) {
                    showAlertHuyBo(idHBox, soCccd1, soCccd2, button);
                }
                if(button.getText().equals("Đồng ý")) {
                    showAlertDongY(idHBox, soCccd1, soCccd2, button);
                }
            }
        }

        if(idHBox.substring(0,2).equals("TT")) {
            String soCccdTamTruString = idHBox.substring(2);
            Integer soCccdTamTru = Integer.parseInt(soCccdTamTruString);
            TamTruDAO tamTruDAO = new TamTruDAO(connection);
            Optional<TamTru> resultTDHK;
            resultTDHK = tamTruDAO.get(soCccdTamTru);
            if(resultTDHK.isPresent()) {
                TamTru tamtru = resultTDHK.get();
                String soCccd1 = tamtru.getSoCCCD();
                String soCccd2 = tamtru.getCccdChuHo();
                if(button.getText().equals("Hủy bỏ")) {
                    showAlertHuyBo(idHBox, soCccd1, soCccd2, button);
                }
                if(button.getText().equals("Đồng ý")) {
                    showAlertDongY(idHBox, soCccd1, soCccd2, button);
                }
            }
        }

        if(idHBox.substring(0,2).equals("TV")) {
            String idTamVangString = idHBox.substring(2);
            Integer idTamVang = Integer.parseInt(idTamVangString);
            TamVangDAO tamVangDAO = new TamVangDAO(connection);
            Optional<TamVang> resultTDHK;
            resultTDHK = tamVangDAO.get(idTamVang);
            if(resultTDHK.isPresent()) {
                TamVang tamVang = resultTDHK.get();
                String soCccd1 = tamVang.getSoCccd();
                String soCccd2 = null;
                if(button.getText().equals("Hủy bỏ")) {
                    showAlertHuyBo(idHBox, soCccd1, soCccd2, button);
                }
                if(button.getText().equals("Đồng ý")) {
                    showAlertDongY(idHBox, soCccd1, soCccd2, button);
                }
            }
        }

    }

    public void timkiem(ActionEvent event) {
        String yeucautimkiem = noidungtimkiem.getText();
        if(!yeucautimkiem.isEmpty()) {
            VBoxList.getChildren().clear();
            VBoxList.getChildren().add(hBoxTimKiem);
            for(HBox hbox : danhsachHBox) {
                for (var nodeHbox : hbox.getChildren()) {
                    if(nodeHbox instanceof VBox) {
                        VBox vbox = (VBox) nodeHbox;
                        for(var nodeVbox : vbox.getChildren()) {
                            if (nodeVbox instanceof Label) {
                                Label label = (Label) nodeVbox;
                                if(label.getText().toLowerCase().contains(yeucautimkiem.toLowerCase())) {
                                    VBoxList.getChildren().add(hbox);
                                    break;
                                }
                            }
                        }
                        break;
                    }

                }
            }
        } else {
            VBoxList.getChildren().clear();
            VBoxList.getChildren().add(hBoxTimKiem);
            for(HBox hbox : danhsachHBox) {
                VBoxList.getChildren().add(hbox);
            }
        }

    }
}
