package com.example.introductiontose.controller.danhsachkhoanphi;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import com.example.introductiontose.database.SqlConnection;
import com.example.introductiontose.dao.DongPhiDAO;
import com.example.introductiontose.dao.KhoanPhiDAO;
import com.example.introductiontose.dao.NhanKhauDAO;
import com.example.introductiontose.dao.TaiKhoanNhanKhauDAO;
import com.example.introductiontose.model.DongPhi;
import com.example.introductiontose.model.KhoanPhi;
import com.example.introductiontose.model.NhanKhau;
import com.example.introductiontose.model.TaiKhoanNhanKhau;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import static com.example.introductiontose.controller.YeuCauNapTien.YeuCauNapTienUserController.CCCD;
import static com.example.introductiontose.controller.YeuCauNapTien.YeuCauNapTienUserController.countHoKhau;

public class DanhSachKhoanPhiUserController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    Label mucphi;
    @FXML
    TextField sotien;
    @FXML
    CheckBox mycheckbox;
    @FXML
    Label ktracheckbox;

    @FXML
    private VBox VBoxlist;
    @FXML
    private Text hienThiChiTiet;
    @FXML
    private TextField noidungtim;
    @FXML
    private HBox hBoxTimKiem;
    @FXML
    private  VBox VBoxlichsudongphi;
    @FXML
    private TextField noidungtimnoptien;
    @FXML
    private HBox hBoxtiemkiemnoptien;

    public static List<HBox> danhsachHBox = new ArrayList<>();
    public  static  List<HBox> danhsachHoxdongphi = new ArrayList<>();
    public static int idHK;
    Connection connection = SqlConnection.connect();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        NhanKhauDAO nhanKhauDao = new NhanKhauDAO(connection, NhanKhauDAO.TableType.NHANKHAU);
        try {
            Optional<NhanKhau> resultNK = nhanKhauDao.get(CCCD);
            if(resultNK.isPresent()) {
                NhanKhau nhankhau = resultNK.get();
                idHK = nhankhau.getThongTinNhanKhau().getIdHoKhau();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        KhoanPhiDAO khoanPhiDAO = new KhoanPhiDAO(connection);
        List<KhoanPhi> danhsachKhoanPhi = new ArrayList<>();
        DongPhiDAO dongPhiDAO = new DongPhiDAO(connection);
        List<DongPhi> danhsachDongPhi = new ArrayList<>();
        try {
            danhsachKhoanPhi = khoanPhiDAO.getAll();
            danhsachDongPhi = dongPhiDAO.getAll();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        String idHBox;
        String idPhi;
        for (KhoanPhi khoanPhi : danhsachKhoanPhi) {
            List<String> thongtinKhoanPhi = null;
            try {
                thongtinKhoanPhi = LayThongTinKhoanPhi(khoanPhi);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            idHBox = thongtinKhoanPhi.get(0);
            idPhi = String.valueOf(thongtinKhoanPhi.get(1));
            String mucPhi = String.valueOf(thongtinKhoanPhi.get(2));
            String ngayBatDau = thongtinKhoanPhi.get(3);
            String ngayKetThuc =thongtinKhoanPhi.get(4);
            String tieudePhi = thongtinKhoanPhi.get(5);
            String kieuPhi = thongtinKhoanPhi.get(6);
            String noiDung = thongtinKhoanPhi.get(7);


            HBox hBox = initHBox(idHBox, idPhi, mucPhi,ngayBatDau,ngayKetThuc, tieudePhi, kieuPhi, noiDung);
            Insets hboxMargin = new Insets(10, 10, 0, 10);
            VBoxlist.getChildren().add(hBox);
            VBoxlist.setMargin(hBox, hboxMargin);
            danhsachHBox.add(hBox);
        }

        for(DongPhi dongPhi : danhsachDongPhi){
            List<String> thongtinDongPhi = LayThongTinDongPhi(dongPhi);
            String idHBoxDongPhi = thongtinDongPhi.get(0);
            String idPhiDongPhi = String.valueOf(thongtinDongPhi.get(1));
            String idHoKhau = String.valueOf(thongtinDongPhi.get(2));
            String soTien = String.valueOf(thongtinDongPhi.get(3));
            String ngaydong = thongtinDongPhi.get(4);
            if(idHoKhau.substring(12).equals(String.valueOf(idHK))) {
                HBox hboxDongPhi = initHBox1(idHBoxDongPhi, idPhiDongPhi, idHoKhau,soTien,ngaydong);
                Insets hboxMargin = new Insets(10, 10, 0, 10);
                VBoxlichsudongphi.getChildren().add(hboxDongPhi);
                VBoxlichsudongphi.setMargin(hboxDongPhi, hboxMargin);
                danhsachHoxdongphi.add(hboxDongPhi);
            }
        }


    }

    private List<String> LayThongTinDongPhi(DongPhi dongPhi) {
        List<String> thongtinDongPhi = new ArrayList<>();
        String idHBox = String.valueOf(dongPhi.getIdPhi());
        String idPhi = "ID Phí: "+dongPhi.getIdPhi();
        String idHoKhau ="ID Hộ Khẩu: " +dongPhi.getIdHoKhau();
        String soTien = "Số Tiền : "+ dongPhi.getSoTien();
        String ngaydong = "Ngày đóng: "+dongPhi.getNgayDong();
        thongtinDongPhi.add(idHBox);
        thongtinDongPhi.add(idPhi);
        thongtinDongPhi.add(idHoKhau);
        thongtinDongPhi.add(soTien);
        thongtinDongPhi.add(ngaydong);
        return thongtinDongPhi;
    }

    private HBox initHBox1(String idHBoxDongPhi, String idPhiDongPhi, String idHoKhau, String soTien, String ngaydong) {
        HBox hboxDongPhi = new HBox();
        hboxDongPhi.setAlignment(Pos.CENTER_LEFT);
        hboxDongPhi.setPrefHeight(80.0);
        hboxDongPhi.setPrefWidth(355.5);
        hboxDongPhi.setStyle("-fx-background-color: #E1ECEB; -fx-background-radius: 10;");
        Insets hboxPadding = new Insets(18);
        hboxDongPhi.setPadding(hboxPadding);
        VBox vboxDongPhi = initVBox1(idPhiDongPhi,idHoKhau,soTien,ngaydong);
        hboxDongPhi.getChildren().addAll(vboxDongPhi);

        hboxDongPhi.setId(idHBoxDongPhi);
        return hboxDongPhi;

    }

    private VBox initVBox1(String idPhiBangDongPhi, String idHoKhau, String soTien, String ngaydong) {
        VBox vboxDongPhi = new VBox();
        vboxDongPhi.setPrefHeight(74.5);
        vboxDongPhi.setPrefWidth(225.0);
        vboxDongPhi.setSpacing(8);

        Label idPhiLabel = new Label(idPhiBangDongPhi);
        Label idHoKhauLabel = new Label(idHoKhau);
        Label soTienLabel = new Label(soTien);
        Label ngaydongLabel = new Label(ngaydong);

        vboxDongPhi.getChildren().addAll(idPhiLabel,idHoKhauLabel,soTienLabel,ngaydongLabel);
        vboxDongPhi.setAlignment(Pos.CENTER_LEFT);

        return vboxDongPhi;
    }

    public VBox initVBox (String kieuPhi, String mucPhi, String tieudePhi ){
        VBox vbox = new VBox();
        vbox.setPrefHeight(74.5);
        vbox.setPrefWidth(225.0);
        vbox.setSpacing(8);

        Label kieuPhiLabel = new Label(kieuPhi);
        Label mucPhiLabel = new Label(String.valueOf(mucPhi));
        Label tieuDeLabel = new Label(tieudePhi);

        vbox.getChildren().addAll(kieuPhiLabel, mucPhiLabel, tieuDeLabel);
        vbox.setAlignment(Pos.CENTER_LEFT);

        return vbox;

    }
    private HBox initHBox(String idHBox, String idPhi, String mucPhi,String ngayBatDau,String ngayKetThuc, String tieudePhi, String kieuPhi, String noiDung) {
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setPrefHeight(80.0);
        hbox.setPrefWidth(355.5);
        hbox.setStyle("-fx-background-color: #E1ECEB; -fx-background-radius: 10;");
        Insets hboxPadding = new Insets(18);
        hbox.setPadding(hboxPadding);
        VBox vbox = initVBox(kieuPhi,mucPhi, tieudePhi);
        hbox.getChildren().addAll(vbox);

        hbox.setId(idHBox);
        vbox.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // Gọi hàm ChiTietThongTin khi click vào VBox
                try {
                    ChiTietThongTin(vbox);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        return hbox;
    }
    public void ChiTietThongTin(VBox vbox) throws SQLException {
        HBox parentHBox = (HBox) vbox.getParent();
        String idHBox = parentHBox.getId();
        String idPhiString = idHBox;
        Integer idPhi =Integer.parseInt(idPhiString);
        KhoanPhiDAO khoanPhiDAO = new KhoanPhiDAO(connection);
        Optional<KhoanPhi> resultKhoanPhi;
        try{
            resultKhoanPhi = khoanPhiDAO.get(idPhi);

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        if(resultKhoanPhi.isPresent()){
            KhoanPhi khoanPhi =resultKhoanPhi.get();
            List<String> thongtinKhoanPhi = LayThongTinKhoanPhi(khoanPhi);
            String chitiet ="";
            chitiet =thongtinKhoanPhi.get(1)+ "\n\n"
                    +thongtinKhoanPhi.get(2)+ "\n\n"
                    +thongtinKhoanPhi.get(3)+ "\n\n"
                    +thongtinKhoanPhi.get(4)+ "\n\n"
                    +thongtinKhoanPhi.get(5)+ "\n\n"
                    +thongtinKhoanPhi.get(6)+ "\n\n"
                    +thongtinKhoanPhi.get(7);

            hienThiChiTiet.setText(chitiet);
        }

    }
    public List<String> LayThongTinKhoanPhi(KhoanPhi khoanPhi) throws SQLException {
        List<String> thongtinKhoanPhi = new ArrayList<>();
        String idHBox = String.valueOf(khoanPhi.getIdPhi());
        String idPhi = "Id phí: "+ khoanPhi.getIdPhi();
        String mucPhi = "Mức phí: ";
        if(khoanPhi.getKieuphi().equals("bắt buộc/người")){
            mucPhi = mucPhi + khoanPhi.getMucphi() * countHoKhau(CCCD);
        } else {
            mucPhi = "Mức phí: "+khoanPhi.getMucphi();
        }

        String ngayBatdau = "Ngày bắt đầu: "+khoanPhi.getNgaytao();
        String ngayKetThuc = "Ngày kết thúc: "+khoanPhi.getNgayketthuc();
        String tieudePhi= "Tiêu đề phí: " + khoanPhi.getTieudephi();
        String kieuPhi = "Kiểu phí: "+ khoanPhi.getKieuphi();
        String noiDUng= "Nội dung: "+"\n"+ khoanPhi.getNoidungphi();
        if(khoanPhi.getMucphi()==0){
            sotien.setVisible(true);
            mucphi.setVisible(false);



        }else {
            sotien.setVisible(false);
            mucphi.setVisible(true);

        }
        thongtinKhoanPhi.add(idHBox);
        thongtinKhoanPhi.add(idPhi);
        thongtinKhoanPhi.add(mucPhi);
        thongtinKhoanPhi.add(ngayBatdau);
        thongtinKhoanPhi.add(ngayKetThuc);
        thongtinKhoanPhi.add(tieudePhi);
        thongtinKhoanPhi.add(kieuPhi);
        thongtinKhoanPhi.add(noiDUng);
        return thongtinKhoanPhi;
    }
    public void timkiem(ActionEvent event) {
        String yeucautimkiem = noidungtim.getText();
        if(!yeucautimkiem.isEmpty()) {
            VBoxlist.getChildren().clear();
            VBoxlist.getChildren().add(hBoxTimKiem);
            for(HBox hbox : danhsachHBox) {
                for (var nodeHbox : hbox.getChildren()) {
                    if(nodeHbox instanceof VBox) {
                        VBox vbox = (VBox) nodeHbox;
                        for(var nodeVbox : vbox.getChildren()) {
                            if (nodeVbox instanceof Label) {
                                Label label = (Label) nodeVbox;
                                if(label.getText().toLowerCase().contains(yeucautimkiem.toLowerCase())) {
                                    VBoxlist.getChildren().add(hbox);
                                    break;
                                }
                            }
                        }
                        break;
                    }

                }
            }
        } else {
            VBoxlist.getChildren().clear();
            VBoxlist.getChildren().add(hBoxTimKiem);
            for(HBox hbox : danhsachHBox) {
                VBoxlist.getChildren().add(hbox);
            }
        }

    }
    public void timkiemnoptien(ActionEvent event) {
        String yeucautimkiemnoptien = noidungtimnoptien.getText();
        if(!yeucautimkiemnoptien.isEmpty()) {
            VBoxlichsudongphi.getChildren().clear();
            VBoxlichsudongphi.getChildren().add(hBoxtiemkiemnoptien);
            for(HBox hbox : danhsachHoxdongphi) {
                for (var nodeHbox : hbox.getChildren()) {
                    if(nodeHbox instanceof VBox) {
                        VBox vbox = (VBox) nodeHbox;
                        for(var nodeVbox : vbox.getChildren()) {
                            if (nodeVbox instanceof Label) {
                                Label label = (Label) nodeVbox;
                                if(label.getText().toLowerCase().contains(yeucautimkiemnoptien.toLowerCase())) {
                                    VBoxlichsudongphi.getChildren().add(hbox);
                                    break;
                                }
                            }
                        }
                        break;
                    }

                }
            }
        } else {
            VBoxlichsudongphi.getChildren().clear();
            VBoxlichsudongphi.getChildren().add(hBoxtiemkiemnoptien);
            for(HBox hbox : danhsachHoxdongphi) {
                VBoxlichsudongphi.getChildren().add(hbox);
            }
        }

    }
    public void switchToScene5(ActionEvent event) throws IOException, SQLException {
        if(hienThiChiTiet.getText().isEmpty()) {
            ktracheckbox.setText("Chưa chọn khoản phí!");
            return;
        }
        if(sotien.isVisible()) {


            if (sotien.getText().isEmpty()) {
                ktracheckbox.setText("Bạn hãy nhập số tiền");

            } else if (sotien.getText().equals("0")) {
                ktracheckbox.setText("Số tiền phải lớn hơn 0");
            } else {
                if (mycheckbox.isSelected()) {
                    DongPhiDAO dongPhi = new DongPhiDAO(connection);
                    String noidungkhoanphi = hienThiChiTiet.getText();
                    List<String> noidung = List.of(noidungkhoanphi.split("\n\n"));
                    String thongtinIDphi = noidung.get(0);
                    int idPhi = Integer.parseInt(thongtinIDphi.substring(8));
                    LocalDateTime ngaydong = LocalDateTime.now();
                    TaiKhoanNhanKhauDAO taiKhoanNhanKhauDAO = new TaiKhoanNhanKhauDAO(connection);
                    Optional<TaiKhoanNhanKhau> resultTaiKhoan= taiKhoanNhanKhauDAO.get(CCCD);
                    int soDu = 0;
                    TaiKhoanNhanKhau taiKhoanNhanKhau = null;
                    if(resultTaiKhoan.isPresent()){
                        taiKhoanNhanKhau = resultTaiKhoan.get();
                        soDu = taiKhoanNhanKhau.getSoDuTaiKhoan();
                        if(soDu >= Integer.valueOf(sotien.getText())) {
                            soDu = soDu - Integer.valueOf(sotien.getText());
                            taiKhoanNhanKhauDAO.update(new TaiKhoanNhanKhau(CCCD,taiKhoanNhanKhau.getTentaikhoan(),taiKhoanNhanKhau.getPass(),soDu));
                            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("/sample/User/Scrence5.fxml"));
                            Parent khoanphi = loader.load();
                            scene = new Scene(khoanphi);
                            stage.setScene(scene);


                            dongPhi.save1(idPhi, idHK, ngaydong, sotien.getText());
                        }else{
                            ktracheckbox.setText("Số dư tài khoản không đủ");
                        }
                    }


                } else {
                    ktracheckbox.setText("Bạn chưa đồng ý nộp phí");
                }
            }
        }else{
            if (mycheckbox.isSelected()) {
                DongPhiDAO dongPhi = new DongPhiDAO(connection);
                String noidungkhoanphi = hienThiChiTiet.getText();
                List<String> noidung = List.of(noidungkhoanphi.split("\n\n"));
                String thongtinIDphi = noidung.get(0);
                String thongtinMucPhi = noidung.get(1);
                int idPhi = Integer.parseInt(thongtinIDphi.substring(8));
                int getmucPhi = Integer.parseInt(thongtinMucPhi.substring(9));
                LocalDateTime ngaydong = LocalDateTime.now();
                TaiKhoanNhanKhauDAO taiKhoanNhanKhauDAO = new TaiKhoanNhanKhauDAO(connection);
                Optional<TaiKhoanNhanKhau> resultTaiKhoan= taiKhoanNhanKhauDAO.get(CCCD);
                int soDu = 0;
                TaiKhoanNhanKhau taiKhoanNhanKhau = null;
                if(resultTaiKhoan.isPresent()){
                    taiKhoanNhanKhau = resultTaiKhoan.get();
                    soDu = taiKhoanNhanKhau.getSoDuTaiKhoan();
                    if(soDu >= getmucPhi) {
                        soDu = soDu - getmucPhi;
                        taiKhoanNhanKhauDAO.update(new TaiKhoanNhanKhau(CCCD,taiKhoanNhanKhau.getTentaikhoan(),taiKhoanNhanKhau.getPass(),soDu));
                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("/sample/User/Scrence5.fxml"));
                        Parent khoanphi = loader.load();
                        scene = new Scene(khoanphi);
                        stage.setScene(scene);


                        dongPhi.save(idPhi, idHK, ngaydong, getmucPhi);
                    }else{
                        ktracheckbox.setText("Số dư tài khoản không đủ");
                    }
                }


            } else {
                ktracheckbox.setText("Bạn chưa đồng ý nộp phí");
            }
        }




        

    }

}
