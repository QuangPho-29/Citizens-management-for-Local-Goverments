package com.example.introductiontose.controller.thongke;

import com.example.introductiontose.dao.*;
import com.example.introductiontose.database.SqlConnection;
import com.example.introductiontose.model.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;


import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ThongKeTPAdminController implements Initializable {
    @FXML
    private Label chuaDong;

    @FXML
    private Label thuPhi;

    @FXML
    private VBox danhSachNKHK;

    @FXML
    private ScrollPane scrollPaneChuaDong;

    @FXML
    private Label tenTDP;

    @FXML
    private Pane thongKeChart;

    @FXML
    private Label dongGop;

    @FXML
    private Label tkThuPhiDG;

    @FXML
    private VBox xemTatCaDS;

    Connection connection;

    @FXML
    void onDanhSachChuaDongClicked(MouseEvent event) {

    }

    @FXML
    void onThuPhiDGClicked(MouseEvent event) {

    }

    ThongTinTDP thongTin;
    double tienThuPhi = 0, tienDongGop = 0, tienChuaDong = 0;
    List<DongPhi> danhSachDongPhi;
    Map<Integer, KhoanPhi> danhSachKhoanPhi = new HashMap<>();
    Map<Integer, List<HoKhau>> tkThuPhiMap = new HashMap<>();
    Map<Integer, List<HoKhau>> tkDongGopMap = new HashMap<>();
    Map<Integer, List<HoKhau>> tkChuaDongMap = new HashMap<>();

    private DongPhiDAO dongPhiDAO;
    private KhoanPhiDAO khoanPhiDAO;
    private HoKhauDAO hoKhauDAO;
    private NhanKhauDAO nhanKhauDAO;
    private ThongTinTDPDAO thongTinTDPDAO;

    Map<Integer, HoKhau> tatCaHoKhau = new HashMap<>();
    Map<Integer, Integer> soNhanKhauMap = new HashMap<>();

    Map<LocalDate, Double> thuPhiHangThangMap = new HashMap<>();
    Map<LocalDate, Double> dongGopHangThangMap = new HashMap<>();
    Map<LocalDate, Double> chuaDongHangThangMap = new HashMap<>();

    private void initDAOs() throws SQLException {
        dongPhiDAO = new DongPhiDAO(connection);
        khoanPhiDAO = new KhoanPhiDAO(connection);
        hoKhauDAO = new HoKhauDAO(connection);
        nhanKhauDAO = new NhanKhauDAO(connection, NhanKhauDAO.TableType.NHANKHAU);
        thongTinTDPDAO = new ThongTinTDPDAO(connection);
    }

    private void loadDataFromDB() throws SQLException {
        // Lấy dữ liệu đóng phí
        danhSachDongPhi = dongPhiDAO.getAll();

        // Lấy dữ liệu khoản phí
        List<KhoanPhi> khoanPhiList = khoanPhiDAO.getAll();
        for (KhoanPhi khoanPhi : khoanPhiList) {
            danhSachKhoanPhi.put(khoanPhi.getIdPhi(), khoanPhi);
        }

        // Lấy dữ liệu hộ khẩu
        List<HoKhau> hoKhauList = hoKhauDAO.getAll();
        for (HoKhau hoKhau : hoKhauList) {
            tatCaHoKhau.put(hoKhau.getIdHoKhau(), hoKhau);
        }

        // Lấy dữ liệu nhân khẩu
        List<NhanKhau> nhanKhauList = nhanKhauDAO.getAll();
        for (NhanKhau nhanKhau : nhanKhauList) {
            soNhanKhauMap.put(nhanKhau.getThongTinNhanKhau().getIdHoKhau(),
                    soNhanKhauMap.getOrDefault(nhanKhau.getThongTinNhanKhau().getIdHoKhau(), 0) + 1);
        }

        // Lấy thông tin TDP
        thongTin = thongTinTDPDAO.get();
    }

    private void processData() {
        // Xử lý dữ liệu đóng phí
        processDongPhiData();

        // Xử lý dữ liệu chưa đóng phí và tính tổng tiền chưa đóng
        processChuaDongPhiData();
    }

    private void processDongPhiData() {
        for (DongPhi dongPhi : danhSachDongPhi) {
            KhoanPhi matchedKhoanPhi = danhSachKhoanPhi.get(dongPhi.getIdPhi());
            if (matchedKhoanPhi == null) {
                continue; // Nếu không tìm thấy khoản phí tương ứng, bỏ qua và tiếp tục vòng lặp
            }

            HoKhau matchedHoKhau = tatCaHoKhau.get(dongPhi.getIdHoKhau());
            if (matchedHoKhau == null) {
                continue; // Nếu không tìm thấy hộ khẩu tương ứng, bỏ qua và tiếp tục vòng lặp
            }

            LocalDate thang = matchedKhoanPhi.getNgayketthuc().toLocalDate();

            // Xử lý dữ liệu dựa trên loại khoản phí
            if (matchedKhoanPhi.getKieuphi().equals("bắt buộc") || matchedKhoanPhi.getKieuphi().equals("bắt buộc/người")) {
                tienThuPhi += dongPhi.getSoTien();
                tkThuPhiMap.computeIfAbsent(matchedKhoanPhi.getIdPhi(), k -> new ArrayList<>()).add(matchedHoKhau);

                thuPhiHangThangMap.put(thang, thuPhiHangThangMap.getOrDefault(thang, 0.0) + dongPhi.getSoTien());
            } else if (matchedKhoanPhi.getKieuphi().equals("đóng góp")) {
                tienDongGop += dongPhi.getSoTien();
                tkDongGopMap.computeIfAbsent(matchedKhoanPhi.getIdPhi(), k -> new ArrayList<>()).add(matchedHoKhau);

                dongGopHangThangMap.put(thang, dongGopHangThangMap.getOrDefault(thang, 0.0) + dongPhi.getSoTien());
            }
        }
    }

    private void processChuaDongPhiData() {
        for (Map.Entry<Integer, KhoanPhi> entry : danhSachKhoanPhi.entrySet()) {
            int idKhoanPhi = entry.getKey();
            KhoanPhi khoanPhi = entry.getValue();

            if (!khoanPhi.getKieuphi().equals("bắt buộc") && !khoanPhi.getKieuphi().equals("bắt buộc/người")) {
                continue;
            }

            List<HoKhau> hoChuaDongPhi = new ArrayList<>(tatCaHoKhau.values());
            List<HoKhau> hoDaDongPhi = tkThuPhiMap.getOrDefault(idKhoanPhi, new ArrayList<>());
            hoChuaDongPhi.removeAll(hoDaDongPhi);

            tkChuaDongMap.put(idKhoanPhi, hoChuaDongPhi);

            LocalDate thang = khoanPhi.getNgayketthuc().toLocalDate();

            // Tính tổng tiền chưa đóng cho từng khoản phí
            if (khoanPhi.getKieuphi().equals("bắt buộc/người")) {
                for (HoKhau hoKhau : hoChuaDongPhi) {
                    int soNhanKhau = soNhanKhauMap.getOrDefault(hoKhau.getIdHoKhau(), 0);
                    double tien = soNhanKhau * khoanPhi.getMucphi();
                    tienChuaDong += tien;

                    chuaDongHangThangMap.put(thang, chuaDongHangThangMap.getOrDefault(thang, 0.0) + tien);
                }
            } else if (khoanPhi.getKieuphi().equals("bắt buộc")) {
                tienChuaDong += hoChuaDongPhi.size() * khoanPhi.getMucphi();

                chuaDongHangThangMap.put(thang, chuaDongHangThangMap.getOrDefault(thang, 0.0) + hoChuaDongPhi.size() * khoanPhi.getMucphi());
            }
        }
    }

    private void updateUI() {
        // Cập nhật giao diện người dùng với thông tin mới
        tenTDP.setText(thongTin.getTen());
        thuPhi.setText(String.valueOf(tienThuPhi));
        dongGop.setText(String.valueOf(tienDongGop));
        chuaDong.setText(String.valueOf(tienChuaDong));

        updateChart();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.connection = SqlConnection.connect();


        try {
            initDAOs();
            loadDataFromDB();
        } catch (SQLException e) {
            System.out.println("Lỗi SQL");
        } catch (Exception e) {
            System.out.println("Lỗi tổng");
            e.printStackTrace();
        }

        processData();
        updateUI();

        SqlConnection.close(connection);
    }

    private String formatDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
        return "Tháng " + date.format(formatter);
    }

    private XYChart.Series<String, Number> createSeries(Map<LocalDate, Double> data, String seriesName) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName(seriesName);

        LocalDate hienTai = LocalDate.now();
        LocalDate sixMonthsAgo = LocalDate.now().minusMonths(5);

        data.forEach((date, value) -> {
            if (date.isAfter(sixMonthsAgo) && date.isBefore(hienTai) || date.isEqual(hienTai)) {
                series.getData().add(new XYChart.Data<>(formatDate(date), value));
            }
        });

        return series;
    }

    private StackedBarChart<String, Number> createStackedBarChart() {
        CategoryAxis xAxis = new CategoryAxis(); // Trục x cho ngày
        NumberAxis yAxis = new NumberAxis();     // Trục y cho số tiền

        StackedBarChart<String, Number> stackedBarChart = new StackedBarChart<>(xAxis, yAxis);

        XYChart.Series<String, Number> thuPhiSeries = createSeries(thuPhiHangThangMap, "Thu Phí");
        XYChart.Series<String, Number> dongGopSeries = createSeries(dongGopHangThangMap, "Đóng Góp");
        XYChart.Series<String, Number> chuaDongSeries = createSeries(chuaDongHangThangMap, "Chưa Đóng");

        stackedBarChart.getData().addAll(thuPhiSeries, dongGopSeries, chuaDongSeries);
        stackedBarChart.setMaxHeight(Region.USE_COMPUTED_SIZE);
        stackedBarChart.setMinHeight(Region.USE_COMPUTED_SIZE);
        stackedBarChart.setPrefHeight(345);
        stackedBarChart.setPrefWidth(435);

        return stackedBarChart;
    }

    private void updateChart() {
        StackedBarChart<String, Number> stackedBarChart = createStackedBarChart();
        thongKeChart.getChildren().clear();
        thongKeChart.getChildren().add(stackedBarChart);
    }
}
