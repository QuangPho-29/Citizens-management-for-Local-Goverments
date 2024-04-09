package com.example.introductiontose.controller.admin.hokhau;

import com.example.introductiontose.model.NhanKhau;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.format.DateTimeFormatter;

/**
 * Controller quản lý hiển thị thông tin chi tiết về một nhân khẩu trong giao diện admin.
 *
 * <p>Controller này điều khiển hiển thị thông tin chi tiết của một nhân khẩu thông qua JavaFX.
 * Cung cấp khả năng hiển thị thông tin nhân khẩu trong một Pane.</p>
 *
 * <p>Controller này cũng có chức năng chuyển đổi giữa các Scene và Stage trong ứng dụng.</p>
 *
 * @author Duy
 * @version 1.0
 */
public class ThongTinNhanKhauController {
    @FXML
    private Button title;
    @FXML
    private Button prevTitle;
    @FXML
    private Pane paneContent;
    @FXML
    private Text hoTen;
    @FXML
    private Text biDanh;
    @FXML
    private Text ngaySinh;
    @FXML
    private Text noiSinh;
    @FXML
    private Text nguyenQuan;
    @FXML
    private Text danToc;
    @FXML
    private Text tonGiao;
    @FXML
    private Text diaChiCu;
    @FXML
    private Text soCccd;
    @FXML
    private Text ngayCap;
    @FXML
    private Text noiCap;
    @FXML
    private Text ngheNghiep;
    @FXML
    private Text noiLamViec;
    @FXML
    private Text ngayDKTT;
    @FXML
    private Text quanHe;
    @FXML
    private Text idHoKhau;
    private Scene prevPrevScene;
    private Scene prevScene;
    private Stage prevStage;
    
    /**
     * Phương thức xử lý sự kiện khi nút "Quay lại" được nhấn.
     * Chuyển đổi về Scene trước đó.
     */
    @FXML
    private void goBackPrev() {
        if (prevScene == null) return;
        prevStage.setScene(prevScene);
    }
    
    /**
     * Phương thức xử lý sự kiện khi nút "Quay lại" (lần thứ hai) được nhấn.
     * Chuyển đổi về Scene trước trước đó.
     */
    @FXML
    private void goBackPrevPrev() {
        if (prevPrevScene == null) return;
        prevStage.setScene(prevPrevScene);
    }
    
    /**
     * Phương thức thiết lập Scene trước trước đó mà controller sẽ quay lại khi nút "Quay lại" được nhấn.
     *
     * @param prevPrevScene Scene trước trước đó cần quay lại.
     */
    public void setPrevPrevScene(Scene prevPrevScene) {
        this.prevPrevScene = prevPrevScene;
    }
    
    /**
     * Phương thức thiết lập Scene trước đó mà controller sẽ quay lại khi nút "Quay lại" được nhấn.
     *
     * @param prevScene Scene trước đó cần quay lại.
     */
    public void setPrevScene(Scene prevScene) {
        this.prevScene = prevScene;
    }
    
    /**
     * Phương thức thiết lập Stage mà controller đang quản lý.
     *
     * @param prevStage Stage của controller.
     */
    public void setPrevStage(Stage prevStage) {
        this.prevStage = prevStage;
    }
    
    /**
     * Phương thức thiết lập dữ liệu nhân khẩu để hiển thị.
     *
     * @param nhanKhau Đối tượng NhanKhau chứa thông tin nhân khẩu.
     */
    public void setData(NhanKhau nhanKhau) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        hoTen.setText(nhanKhau.getThongTinNhanKhau().getHoTen());
        biDanh.setText(nhanKhau.getThongTinNhanKhau().getBiDanh());
        ngaySinh.setText(nhanKhau.getThongTinNhanKhau().getNgaySinh().format(formatter));
        noiSinh.setText(nhanKhau.getThongTinNhanKhau().getNoiSinh());
        nguyenQuan.setText(nhanKhau.getThongTinNhanKhau().getNguyenQuan());
        danToc.setText(nhanKhau.getThongTinNhanKhau().getDanToc());
        tonGiao.setText(nhanKhau.getThongTinNhanKhau().getTonGiao());
        diaChiCu.setText(nhanKhau.getThongTinNhanKhau().getDiaChiCu());
        soCccd.setText(nhanKhau.getThongTinNhanKhau().getCccd().getSoCccd());
        ngayCap.setText(nhanKhau.getThongTinNhanKhau().getCccd().getNgayCap().format(formatter));
        noiCap.setText(nhanKhau.getThongTinNhanKhau().getCccd().getNoiCap());
        ngheNghiep.setText(nhanKhau.getThongTinNhanKhau().getNgheNghiep());
        noiLamViec.setText(nhanKhau.getThongTinNhanKhau().getNoiLamViec());
        ngayDKTT.setText(nhanKhau.getThongTinNhanKhau().getNgayDKTT().format(formatter));
        quanHe.setText(nhanKhau.getThongTinNhanKhau().getQuanHe());
        idHoKhau.setText(String.valueOf(nhanKhau.getThongTinNhanKhau().getIdHoKhau()));
    }
    
    /**
     * Phương thức thiết lập tiêu đề cho danh sách nhân khẩu.
     *
     * @param nhanKhau Đối tượng NhanKhau chứa thông tin nhân khẩu.
     */
    public void setTitle(NhanKhau nhanKhau) {
        title.setText(nhanKhau.getThongTinNhanKhau().getHoTen());
        prevTitle.setText("Hộ khẩu " + nhanKhau.getThongTinNhanKhau().getIdHoKhau());
    }
}
