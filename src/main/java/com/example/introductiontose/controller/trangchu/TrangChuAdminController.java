package com.example.introductiontose.controller.trangchu;

import com.example.introductiontose.dao.*;
import com.example.introductiontose.database.SqlConnection;
import com.example.introductiontose.model.*;
import com.example.introductiontose.util.AlertUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.YearMonth;
import java.util.*;

public class TrangChuAdminController implements Initializable {
    @FXML
    private Label tenToDanPho;
    @FXML
    private HBox hoKhau;
    @FXML
    private Label soHoKhau;
    @FXML
    private HBox nhanKhau;
    @FXML
    private Label soNhanKhau;
    @FXML
    private HBox thuPhi;
    @FXML
    private Label tienThuPhi;
    @FXML
    private HBox chuaDong;
    @FXML
    private Label tienChuaDong;
    @FXML
    private HBox dongGop;
    @FXML
    private Label tienDongGop;
    @FXML
    private BarChart bangTK;
    @FXML
    private VBox xemTatCa;
    @FXML
    private VBox danhSachYeuCau;

    Connection connection;

    private ThongTinTDP thongTin;

    public void getThongTinTDP () {
        ThongTinTDPDAO ketNoi = new ThongTinTDPDAO(connection);
        try {
            thongTin = ketNoi.get();
        } catch (Exception e) {
            AlertUtils.showAlert("Lỗi", "Đã có lỗi khi gửi yêu cầu này.");
        }
    }

    private DongPhiDAO dongPhiDAO;
    private List<DongPhi> danhSachDongPhi;

    private void getDanhSachDongPhi() {
        try {
            this.dongPhiDAO = new DongPhiDAO(connection);
            danhSachDongPhi = dongPhiDAO.getAll();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    private List<XYChart.Data<String, Number>> thuPhiData = new ArrayList<>();
    private List<XYChart.Data<String, Number>> dongGopData = new ArrayList<>();

    public void thongKeThuPhi() {
        Map<YearMonth, Integer> thongKeTP = new HashMap<>();
        Map<YearMonth, Integer> thongKeDG = new HashMap<>();

        KhoanPhiDAO khoanPhiDAO = new KhoanPhiDAO(connection);
        Map<Integer, String> danhSachKP = khoanPhiDAO.getDanhSachKhoanPhi();


        int soTien;
        YearMonth thang;

        for (DongPhi dongPhi : danhSachDongPhi) {
            thang = YearMonth.from(dongPhi.getNgayDong());
            soTien = dongPhi.getSoTien();

            try {
                if (danhSachKP.get(dongPhi.getIdPhi()) == null) {
                    System.out.println("Khoan phi null");
                } else if (danhSachKP.get(dongPhi.getIdPhi()).equals("bắt buộc")) {
                    //System.out.println("bb");
                    thongKeTP.merge(thang, soTien, Integer::sum);
                } else if (danhSachKP.get(dongPhi.getIdPhi()).equals("đóng góp")) {
                    //System.out.println("đg");
                    thongKeDG.merge(thang, soTien, Integer::sum);
                }
            }
            catch (Exception e) {
                //
            }
        }

        thongKeTP.forEach((month, tongTien) -> {
            thuPhiData.add(new XYChart.Data<>(month.toString(), tongTien));
        });

        thongKeDG.forEach((month, tongTien) -> {
            dongGopData.add(new XYChart.Data<>(month.toString(), tongTien));
        });
    }

    public VBox createYeuCau(String loai, String label1, String label2) {
        VBox yeuCau = new VBox();

        // Cài đặt các thuộc tính cho VBox
        yeuCau.setMaxHeight(Double.NEGATIVE_INFINITY);
        yeuCau.setMinHeight(50);
        yeuCau.setPrefHeight(100.0);
        yeuCau.setPrefWidth(300.0);
        yeuCau.setSpacing(5);
        yeuCau.setStyle("-fx-background-radius: 10; -fx-background-color: #EFF8F7FF;");
        yeuCau.setPadding(new Insets(10, 10, 10, 10));

        Label loaiYeuCau = new Label(loai);
        Label tieuDe = new Label(label1);
        Label noiDung = new Label(label2);

        yeuCau.getChildren().addAll(loaiYeuCau, tieuDe, noiDung);

        return yeuCau;
    }

    private List<TamTru> danhSachTamTru;
    private List<TamVang> danhSachTamVang;
    private List<TachKhau> danhSachTachKhau;
    private List<ThayDoiHoKhau> danhSachTDHoKhau;
    private List<ThayDoiNhanKhau> danhSachTDNhanKhau;
    public void setDanhSachYeuCau () {
        //danhSachYeuCau = new VBox(10);

        TamTruDAO ttDAO = new TamTruDAO(connection);

        try {
            danhSachTamTru = ttDAO.getAll();
        }
        catch (Exception e) {
            //
        }

        int index = 0;
        for (TamTru tamTru : danhSachTamTru) {
            System.out.println(tamTru.getTrangThai());
            if (tamTru.getTrangThai().equals("chờ xác nhận")) {
                if (index >= 3) break;
                VBox tt = createYeuCau("Tạm trú", tamTru.getHoTen(), tamTru.getLyDo());
                index++;
                danhSachYeuCau.getChildren().add(tt);
            }
        }

        System.out.println("Xong tam tru");
        ////////////////////////////////////////////////

        TamVangDAO tvDAO = new TamVangDAO(connection);

        try {
            danhSachTamVang = tvDAO.getAll();
        }
        catch (Exception e) {
            //
        }

        for (TamVang tamVang : danhSachTamVang) {
            System.out.println(tamVang.getTrangThai());
            if (tamVang.getTrangThai().equals("chờ xác nhận")) {
                if (index >= 3) break;
                VBox tv = createYeuCau("Tạm vắng", tamVang.getSoCccd(), tamVang.getLyDo());
                index++;
                System.out.println(index);
                danhSachYeuCau.getChildren().add(tv);
            }
        }
        System.out.println("Xong tam vang");
        /////////////////////////////////////////////////////

        TachKhauDAO tkDAO = new TachKhauDAO(connection);
        try {
            danhSachTachKhau = tkDAO.getAll();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(danhSachTachKhau.size());
        for (TachKhau tachKhau : danhSachTachKhau) {
            if (tachKhau.getTrangThai().equals("chờ xác nhận")) {
                if (index >= 3) break;
                VBox tk = createYeuCau("Tách khẩu", tachKhau.getSoCccdChuHoMoi(), String.valueOf(tachKhau.getIdHoKhau()));
                index ++;
                System.out.println(index);
                danhSachYeuCau.getChildren().add(tk);
            }
        }

        System.out.println("Xong tach khau");
        ////////////////////////////////////////////////////////////////

        ThayDoiHoKhauDAO tdhkDAO = new ThayDoiHoKhauDAO(connection);

        try {
            danhSachTDHoKhau = tdhkDAO.getAll();
        }
        catch (Exception e) {
            //
        }

        for (ThayDoiHoKhau thayDoiHoKhau : danhSachTDHoKhau) {
            if (thayDoiHoKhau.getTrangThai().equals("chờ xác nhận")) {
                if (index >= 3) break;
                VBox tdhk = createYeuCau("Thay đổi hộ khẩu", String.valueOf(thayDoiHoKhau.getIdHoKhau()), thayDoiHoKhau.getNoiDung());
                index++;
                System.out.println(index);
                danhSachYeuCau.getChildren().add(tdhk);
            }
        }

        System.out.println("Xong tdhk");
        //////////////////////////////////////////////////////////////////////

        ThayDoiNhanKhauDao tdnkDAO = new ThayDoiNhanKhauDao(connection);

        try {
            danhSachTDNhanKhau = tdnkDAO.getAll();
        }
        catch (Exception e) {
            //
        }

        for (ThayDoiNhanKhau thayDoiNhanKhau : danhSachTDNhanKhau) {
            if (thayDoiNhanKhau.getTrangthaithaidoi().equals("chờ xác nhận")) {
                if (index >= 3) break;
                VBox tdnk = createYeuCau("Thay đổi nhân khẩu", thayDoiNhanKhau.getSoCccd(), thayDoiNhanKhau.getGhichu());
                index++;
                System.out.println(index);
                danhSachYeuCau.getChildren().add(tdnk);
            }
        }
        System.out.println("Xong tdnk");

        System.out.println(danhSachYeuCau.getChildren().size());
    }

    public void setBangTK(boolean check) {
        if(check) {
            XYChart.Series<String, Number> thuPhi = new XYChart.Series<>();
            thuPhi.setName("Thu phí");
            // Thêm dữ liệu vào series
            thuPhi.getData().addAll(thuPhiData);

            XYChart.Series<String, Number> dongGop = new XYChart.Series<>();
            dongGop.setName("Đóng góp");
            // Thêm dữ liệu vào series
            dongGop.getData().addAll(dongGopData);

            bangTK.getData().addAll(thuPhi, dongGop);
        }
        else {

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.connection = SqlConnection.connect();

        getThongTinTDP();

        tenToDanPho.setText(thongTin.getTen());
        soHoKhau.setText(thongTin.getSoHoKhau());
        soNhanKhau.setText(thongTin.getSoNhanKhau());
        tienThuPhi.setText(thongTin.getThuPhi());
        tienDongGop.setText(thongTin.getDongGop());
        tienChuaDong.setText(thongTin.getChuaDong());

        getDanhSachDongPhi();
        thongKeThuPhi();
        setBangTK(true);
        setDanhSachYeuCau();

        SqlConnection.close(connection);
    }
}
