package com.example.introductiontose.controller.trangchu;

import com.example.introductiontose.dao.*;
import com.example.introductiontose.database.SqlConnection;
import com.example.introductiontose.model.*;
import com.example.introductiontose.model.key.HoKhauKey;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.net.URL;
import java.security.spec.RSAOtherPrimeInfo;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TrangChuUserController implements Initializable {
    public static List<DongPhi> danhSachDongPhiUser;
    public static List<KhoanPhi> danhSachKhoanPhi;
    public static List<NhanKhau> thanhVien;
    public static List<NhanKhau> danhSachNhanKhauChuaThem;
    public static List<NhanKhauDaThem> danhSachNhanKhauDaThem;
    public static List<TachKhau> danhSachTachKhau = new ArrayList<>();
    public static List<ThayDoiHoKhau> danhSachDoiChu = new ArrayList<>();
    public static List<ThayDoiNhanKhau> danhSachChuyenDi;
    public static List<TamVang> tamVang;
    public static List<TamTru> tamTru;
    public static List<XYChart.Data<String, Number>> thuPhiData = new ArrayList<>();
    public static List<XYChart.Data<String, Number>> dongGopData = new ArrayList<>();
    Connection connection;
    @FXML
    private StackPane bangThongKe;
    @FXML
    private HBox chuaDong;
    @FXML
    private HBox duTK;
    @FXML
    private HBox hoKhau;
    @FXML
    private VBox danhSachYeuCau, danhSachNhanKhau;
    @FXML
    private Label lsTDNK;
    @FXML
    private Label soNhanKhau;
    @FXML
    private Label chuHo;
    @FXML
    private Label diaChi;
    @FXML
    private Label tenUser;
    @FXML
    private Label tienChuaDong;
    @FXML
    private Label tienDu;
    @FXML
    private Label tkThuPhi;
    @FXML
    private Label xtcDanhSachYeuCau, xtcDanhSachNhanKhau;
    private NhanKhau user;
    private TaiKhoanNhanKhau taiKhoan;
    private HoKhau hoKhauUser;
    private DongPhiDAO dongPhiDAO;
    private TaiKhoanNhanKhauDAO taiKhoanNhanKhauDAO;
    private KhoanPhiDAO khoanPhiDAO;
    private HoKhauDAO hoKhauDAO;
    private NhanKhauDAO nhanKhauDAO;
    private NhanKhauDAO nhanKhauChuaThemDAO;
    private NhanKhauDaThemDAO nhanKhauDaThemDAO;
    private TachKhauDAO tachKhauDAO;
    private ThayDoiHoKhauDAO thayDoiHoKhauDAO;
    private ThayDoiNhanKhauDao thayDoiNhanKhauDao;
    private TamVangDAO tamVangDAO;
    private TamTruDAO tamTruDAO;
    public static BarChart thuPhiChart;
    private VBox danhSachThayDoi = new VBox(10);
    private ScrollPane thayDoiScrollbar = new ScrollPane();
    private Pane paneContent = new Pane();
    private double x = 0, y = 0;
    private int index = 0;
    private int indexNK = 0;
    private Stage stage;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    //    public TrangChuUserController(NhanKhau user) {
//        this.user = user;
//    }
    private void getUser() {
        try {
            nhanKhauDAO = new NhanKhauDAO(connection, NhanKhauDAO.TableType.NHANKHAU);
            user = nhanKhauDAO.get("001075000006").orElse(null);

            taiKhoanNhanKhauDAO = new TaiKhoanNhanKhauDAO(connection);
            taiKhoan = taiKhoanNhanKhauDAO.get(user.getThongTinNhanKhau().getCccd().getSoCccd()).orElse(null);

            if (user == null) System.out.println("user null cmnr");
            System.out.println(user.getThongTinNhanKhau().getHoTen());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void getDanhSachDongPhiUser() {
        try {
            this.dongPhiDAO = new DongPhiDAO(connection);
            danhSachDongPhiUser = dongPhiDAO.getDongPhiUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void getDanhSachDongPhi() {
        try {
            this.khoanPhiDAO = new KhoanPhiDAO(connection);
            danhSachKhoanPhi = khoanPhiDAO.getAll();
        } catch (SQLException e) {
            System.out.println("Lỗi CSDL danh sách đóng phí");
            e.printStackTrace();
        }
    }

    private void getHoKhau() {
        try {
            this.hoKhauDAO = new HoKhauDAO(connection);
            HoKhauKey key = new HoKhauKey(user.getThongTinNhanKhau().getIdHoKhau());
            hoKhauUser = hoKhauDAO.get(key).orElse(null);

            if (hoKhauUser == null) {
                System.out.println("Không tồn tại hộ khẩu phù hợp với user");
                return;
            }
            System.out.println("id Hộ khẩu: " + hoKhauUser.getIdHoKhau());
        } catch (SQLException e) {
            System.out.println("Lỗi kết nối CSDL");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Lỗi tổng hàm getHoKhau");
            e.printStackTrace();
        }
    }

    private void thanhVienGiaDinh() {

        try {
            nhanKhauDAO = new NhanKhauDAO(connection, NhanKhauDAO.TableType.NHANKHAU);
            thanhVien = nhanKhauDAO.getThanhVienGD(user.getThongTinNhanKhau().getIdHoKhau());

            System.out.println(thanhVien);
        } catch (SQLException e) {
            System.out.println("Lỗi CSDL lấy thành viên gia đình");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Lỗi tổng tỏng thành viên gia đình");
            e.printStackTrace();
        }
    }

    private void getThemNhanKhau() {
        try {
            nhanKhauChuaThemDAO = new NhanKhauDAO(connection, NhanKhauDAO.TableType.NHANKHAUCHUATHEM);
            danhSachNhanKhauChuaThem = nhanKhauChuaThemDAO.getThanhVienChuaThem(user.getThongTinNhanKhau().getIdHoKhau());
        } catch (SQLException e) {
            System.out.println("Lỗi CSDL thêm nhân khẩu");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Lỗi tổng thêm nhân khẩu");
            e.printStackTrace();
        }
    }

    private void getDaThemNhanKhau() {
        try {
            nhanKhauDaThemDAO = new NhanKhauDaThemDAO(connection);
            danhSachNhanKhauDaThem = nhanKhauDaThemDAO.getByHoKhau(user.getThongTinNhanKhau().getIdHoKhau());
        } catch (SQLException e) {
            System.out.println("Lỗi CSDL đã thêm nhân khẩu");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Lỗi tổng đã thêm nhân khẩu");
            e.printStackTrace();
        }
    }

    private void getTachKhau() {
        List<TachKhau> tachKhau;

        try {
            tachKhauDAO = new TachKhauDAO(connection);
            tachKhau = tachKhauDAO.getAll();

            for (TachKhau tmp : tachKhau) {
                for (NhanKhau tv : thanhVien) {
                    if (tmp.getSoCccdChuHoMoi().contains(tv.getThongTinNhanKhau().getCccd().getSoCccd())) {
                        danhSachTachKhau.add(tmp);
                        break;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi CSDL tách khẩu");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Lỗi tổng tách khẩu");
            e.printStackTrace();
        }
    }

    private void getDanhSachDoichu() {
        try {
            List<ThayDoiHoKhau> danhSach;

            thayDoiHoKhauDAO = new ThayDoiHoKhauDAO(connection);
            danhSach = thayDoiHoKhauDAO.getAll();

            for (ThayDoiHoKhau tmp : danhSach) {
                if (tmp.getIdHoKhau() == user.getThongTinNhanKhau().getIdHoKhau()) {
                    danhSachDoiChu.add(tmp);
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi CSDL đổi chủ");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Lỗi tổng đổi chủ");
            e.printStackTrace();
        }
    }

    private void getDanhSachChuyenDi() {
        try {
            thayDoiNhanKhauDao = new ThayDoiNhanKhauDao(connection);
            danhSachChuyenDi = thayDoiNhanKhauDao.getCCCD(user.getThongTinNhanKhau().getCccd());
        } catch (SQLException e) {
            System.out.println("Lỗi CSDL chuyển đi");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Lỗi tổng chuyển đi");
            e.printStackTrace();
        }
    }

    private void getDanhSachTamVang() {
        try {
            tamVangDAO = new TamVangDAO(connection);
            tamVang = tamVangDAO.getByCCCD(user.getThongTinNhanKhau().getCccd().getSoCccd());
        } catch (SQLException e) {
            System.out.println("Lỗi CSDL tạm vắng");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Lỗi tổng tạm vắng");
            e.printStackTrace();
        }
    }

    private void getDanhSachTamTru() {
        try {
            tamTruDAO = new TamTruDAO(connection);
            tamTru = tamTruDAO.getByHoKhau(hoKhauUser.getSoCccdChuHo());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static double tongTienChuaDong = 0;
    public static double tongTienDongGop = 0;
    public static List<KhoanPhi> khoanPhiChuaDong = new ArrayList<>();
    private void chuaDong() {
        for (KhoanPhi khoanPhi : danhSachKhoanPhi) {
            boolean daDong = false;

            for (DongPhi dongPhi : danhSachDongPhiUser) {

                if (dongPhi.getIdPhi() == khoanPhi.getIdPhi()) {
                    if (khoanPhi.getKieuphi().equals("đóng góp")) {
                        tongTienDongGop += dongPhi.getSoTien();
                    }
                    daDong = true;
                    break;
                }
            }

            if (!daDong) {
                khoanPhiChuaDong.add(khoanPhi);
                tongTienChuaDong += khoanPhi.getMucphi();
            }
        }
    }

    public void thongKeThuPhi() {
        Map<YearMonth, Integer> thongKeTP = new HashMap<>();
        Map<YearMonth, Integer> thongKeDG = new HashMap<>();

        KhoanPhiDAO khoanPhiDAO = new KhoanPhiDAO(connection);
        Map<Integer, String> danhSachKP = khoanPhiDAO.getDanhSachKhoanPhi();


        int soTien;
        YearMonth thang;

        for (DongPhi dongPhi : danhSachDongPhiUser) {
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        thongKeTP.forEach((month, tongTien) -> {
            thuPhiData.add(new XYChart.Data<>(month.toString(), tongTien));
        });

        thongKeDG.forEach((month, tongTien) -> {
            dongGopData.add(new XYChart.Data<>(month.toString(), tongTien));
        });
    }

    // Phương thức tổng quát để hiển thị danh sách thay đổi
    private void hienThiDanhSachNhanKhau() {
        for (NhanKhau tmp : thanhVien) {
            if (indexNK >= 2) break;
            VBox thayDoi = new VBox(10);
            thayDoi.setStyle("-fx-background-color: #f1f6f6; -fx-background-radius: 10; -fx-padding: 10;");

            Label tenYeuCau = new Label(tmp.getThongTinNhanKhau().getHoTen());
            tenYeuCau.setStyle("-fx-text-fill: #3D5654");
            Label ten = new Label(tmp.getThongTinNhanKhau().getNgaySinh().format(formatter)); // Sử dụng Function để lấy thông tin tên
            ten.setStyle("-fx-text-fill: #3D5654");

            thayDoi.getChildren().addAll(tenYeuCau, ten);
            danhSachNhanKhau.getChildren().add(thayDoi);
            indexNK++;
        }
    }
    private void hienThiDanhSachThayDoi(String tenYeuCauText, String tenS, String noiDungS) {
        VBox thayDoi = new VBox(10);
        thayDoi.setStyle("-fx-background-color: #E1ECEB; -fx-background-radius: 10; -fx-padding: 10;");

        Label tenYeuCau = new Label(tenYeuCauText);
        tenYeuCau.setStyle("-fx-text-fill: #3D5654");
        Label ten = new Label(tenS); // Sử dụng Function để lấy thông tin tên
        ten.setStyle("-fx-text-fill: #3D5654");
        Label noiDung = new Label(noiDungS); // Sử dụng Function để lấy thông tin nội dung
        noiDung.setStyle("-fx-text-fill: #3D5654");

        thayDoi.getChildren().addAll(tenYeuCau, ten, noiDung);
        danhSachThayDoi.getChildren().add(thayDoi);
    }

    private void hienThiDanhSachYeuCau(String tenYeuCauText, String tenS, String noiDungS) {
        if (index >= 3) return;
        VBox thayDoi = new VBox(10);
        thayDoi.setStyle("-fx-background-color: #E1ECEB; -fx-background-radius: 10; -fx-padding: 10;");
        thayDoi.setMinWidth(410);
        thayDoi.setMaxWidth(410);

        Label tenYeuCau = new Label(tenYeuCauText);
        tenYeuCau.setStyle("-fx-text-fill: #3D5654");
        Label ten = new Label(tenS); // Sử dụng Function để lấy thông tin tên
        ten.setStyle("-fx-text-fill: #3D5654");
        Label noiDung = new Label(noiDungS); // Sử dụng Function để lấy thông tin nội dung
        noiDung.setStyle("-fx-text-fill: #3D5654");

        thayDoi.getChildren().addAll(tenYeuCau, ten, noiDung);
        danhSachYeuCau.getChildren().add(thayDoi);
        index++;
    }

    private void hienThiDanhSachChuyenDi() {
        for (ThayDoiNhanKhau thayDoiNhanKhau : danhSachChuyenDi) {
            for (NhanKhau tv : thanhVien) {
                if (tv.getThongTinNhanKhau().getCccd().getSoCccd().equals(thayDoiNhanKhau.getSoCccd())) {
                    if (thayDoiNhanKhau.getTrangthaithaidoi().equals("chưa xác nhận")) {
                        hienThiDanhSachYeuCau("Chuyển đi nơi khác", tv.getThongTinNhanKhau().getHoTen(), thayDoiNhanKhau.getGhichu());
                    }
                    else hienThiDanhSachThayDoi("Chuyển đi nơi khác", tv.getThongTinNhanKhau().getHoTen(), thayDoiNhanKhau.getGhichu());
                }
            }
        }
    }

    private void hienThiDanhSachTamVang() {
        for (TamVang tmp : tamVang) {
            if (tmp.getTrangThai().equals("chưa xác nhận")) {
                hienThiDanhSachYeuCau("Tạm vắng", tmp.getNgayBatDau().format(formatter), tmp.getLyDo());
            }
        }
    }

    private void hienThiDanhSachTamTru() {
        for (TamTru tmp : tamTru) {
            if (tmp.getTrangThai().equals("chưa xác nhận")) {
                hienThiDanhSachYeuCau("Tạm trú", tmp.getHoTen(), tmp.getLyDo());
            }
            //hienThiDanhSachThayDoi("Tạm trú", tmp.getHoTen(), tmp.getLyDo());
        }
    }

    private void hienThiDanhSachTachKhau() {
        for (TachKhau tmp : danhSachTachKhau) {
            if (tmp.getTrangThai().equals("chưa xác nhận")) {
                String ht = String.join(", ", tmp.getDanhSachNhanKhau());
                hienThiDanhSachYeuCau("Tách khẩu", String.valueOf(tmp.getIdHoKhau()), ht);
            }
            String ht = String.join(", ", tmp.getDanhSachNhanKhau());
            hienThiDanhSachThayDoi("Tách khẩu", String.valueOf(tmp.getIdHoKhau()), ht);
        }
    }

    private void hienThiDanhSachDoiChu() {
        for (ThayDoiHoKhau tmp : danhSachDoiChu) {
            for (NhanKhau tv : thanhVien) {
                if (tmp.getSoCccdChuHoMoi().equals(tv.getThongTinNhanKhau().getCccd().getSoCccd())) {
                    if (tmp.getTrangThai().equals("chưa xác nhận")) {
                        hienThiDanhSachYeuCau("Đổi chủ hộ", tv.getThongTinNhanKhau().getHoTen(), tmp.getNoiDung());
                    }
                    else hienThiDanhSachThayDoi("Đổi chủ hộ", tv.getThongTinNhanKhau().getHoTen(), tmp.getNoiDung());
                    break;
                }
            }
        }
    }

    private void hienThiDanhSachThemNhanKhau() {
        for (NhanKhauDaThem tmp : danhSachNhanKhauDaThem) {
            for (NhanKhau tv : thanhVien) {
                if (tmp.getSoCccd().equals(tv.getThongTinNhanKhau().getCccd().getSoCccd())) {
                    hienThiDanhSachThayDoi("Thêm nhân khẩu", tv.getThongTinNhanKhau().getHoTen(), tmp.getNgayThem().format(formatter));
                    break;
                }
            }
        }
    }

    private void hienThiThongKe() {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Tháng");
        yAxis.setLabel("Số Lượng");

        this.thuPhiChart = new BarChart<>(xAxis, yAxis);
        this.thuPhiChart.setMaxHeight(Region.USE_COMPUTED_SIZE);
        this.thuPhiChart.setMinHeight(Region.USE_COMPUTED_SIZE);
        this.thuPhiChart.setPrefHeight(345);
        this.thuPhiChart.setPrefWidth(435);

        XYChart.Series<String, Number> thuPhi = new XYChart.Series<>();
        thuPhi.setName("Thu phí");
        // Thêm dữ liệu vào series
        thuPhi.getData().addAll(thuPhiData);

        XYChart.Series<String, Number> dongGop = new XYChart.Series<>();
        dongGop.setName("Đóng góp");
        // Thêm dữ liệu vào series
        dongGop.getData().addAll(dongGopData);

        thuPhiChart.getData().addAll(thuPhi, dongGop);
        bangThongKe.getChildren().clear();
        bangThongKe.getChildren().add(thuPhiChart);
    }

    private void updateUI() {
        hienThiDanhSachNhanKhau();
        hienThiDanhSachTachKhau();
        hienThiDanhSachThemNhanKhau();
        hienThiDanhSachTamVang();
        hienThiDanhSachTamTru();
        hienThiDanhSachChuyenDi();
        hienThiDanhSachDoiChu();

        thayDoiScrollbar.setContent(danhSachThayDoi);
        thayDoiScrollbar.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        thayDoiScrollbar.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        hienThiThongKe();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.connection = SqlConnection.connect();


        getUser();

        getHoKhau();
        thanhVienGiaDinh();
        getDanhSachDongPhi();
        getDanhSachDongPhiUser();
        getDanhSachTamVang();
        getDanhSachTamTru();
        getTachKhau();
        getDanhSachChuyenDi();
        getDanhSachDoichu();
        getThemNhanKhau();
        getDaThemNhanKhau();
        thongKeThuPhi();

        Platform.runLater(() -> {
            danhSachThayDoi.setPadding(new Insets(0, 15.0, 15.0,15.0));
            tenUser.setText(user.getThongTinNhanKhau().getHoTen());
            diaChi.setText(hoKhauUser.getDiaChiNha());
            tienDu.setText(String.valueOf(taiKhoan.getSoDuTaiKhoan()));
            // Cập nhật UI sau khi tất cả tác vụ hoàn thành

            tienChuaDong.setText(String.valueOf(tongTienChuaDong));

            chuHo.setText("Chủ hộ: " + hoKhauUser.getTenChuHo());
            soNhanKhau.setText("Số nhân khẩu: " + thanhVien.size());
            updateUI();

            if(index == 0) {
                danhSachYeuCau.getChildren().add(new Label("Bạn không có yêu cầu nào chờ phê duyệt"));
                xtcDanhSachYeuCau.setVisible(false);
            } else if (index < 3) {
                xtcDanhSachYeuCau.setVisible(false);
            }

            if (indexNK < 2) {
                xtcDanhSachNhanKhau.setVisible(false);
            }
        });

        SqlConnection.close(connection);

    }

    @FXML
    void onLSTDNKClicked() {
        bangThongKe.getChildren().clear();
        bangThongKe.getChildren().add(thayDoiScrollbar);
    }

    @FXML
    void onTKTPClicked() {
        bangThongKe.getChildren().clear();
        bangThongKe.getChildren().add(thuPhiChart);
    }
}
