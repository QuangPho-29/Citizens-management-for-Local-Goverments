package com.example.introductiontose.controller.guithongbao;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class GuiThongBao implements Initializable {
    String emptyString = "";

    @FXML
    private RadioButton chonDanCu;

    @FXML
    private RadioButton chonHodan;

    @FXML
    private RadioButton chonToanBo;

    @FXML
    private TextArea dsNguoiNhan;

    @FXML
    private ListView<String> danhSachCoTheChon;

    @FXML
    private AnchorPane listSelect;

    @FXML
    private ToggleGroup selectGroup;

    @FXML
    private AnchorPane thongBao;

    @FXML
    private TextArea noidung;

    @FXML
    private TextField tieude;

    @FXML
    private AnchorPane textReceiver;

    @FXML
    private Label labelListReceiver;

    @FXML
    private AnchorPane tieuDeSection;

    @FXML
    private Text errorThongBao;

    String currentList = "";
    List<String> homeList = functionHelpGuiThongBao.getAllHKhoTenChuHo();
    List<String> userList;
    {
        try {
            userList = functionHelpGuiThongBao.getAllNKsoCccd();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public GuiThongBao() throws SQLException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listSelect.setVisible(false);
        textReceiver.setVisible(true);

        // ẩn phần danh sách có thể chọn khi click vào tieude hoặc noidung
        tieude.setOnMouseClicked(event -> {  listSelect.setVisible(false); });
        noidung.setOnMouseClicked(event -> { listSelect.setVisible(false); });

        // hiện danh sách có thể nhận khi click vào vùng textArea dsNguoiNhan
        dsNguoiNhan.setOnMouseClicked(event -> {
            if(chonDanCu.isSelected() || chonHodan.isSelected()) {
                listSelect.setVisible(true);
            }
        });

        // Lấy dữ liệu về cho danh sách người nhận đã được chọn
        dsNguoiNhan.textProperty().addListener(
                (observable, oldValue, newValue) ->  currentList = dsNguoiNhan.getText());

        // Cập nhật giá trị của dsNguoiNhan và curentList
        capNhatDanhSachNguoiNhan();
    }

    /**
     * Phương thức xử lý khi radiobutton chonDanCu được click
     * @throws SQLException lỗi khi truy vấn dữ liệu nhankhau
     */
    public void selectRadioButtonChonDanCu() throws SQLException {
        if(chonDanCu.isSelected()) {
            textReceiver.setVisible(true);
            labelListReceiver.setText("Danh sách người nhận:");
            dsNguoiNhan.setEditable(true);
            dsNguoiNhan.setText(emptyString);

            danhSachCoTheChon.getItems().clear();
            danhSachCoTheChon.getItems().addAll(userList);

            listSelect.setVisible(true);
        }
    }

    /**
     * Phương thức xử lý khi radiobutton chonHoDan được click
     */
    public void selectRadioButtonChonHoDan() {
        if(chonHodan.isSelected()) {
            textReceiver.setVisible(true);
            labelListReceiver.setText("Danh sách hộ nhận:");
            dsNguoiNhan.setEditable(true);
            dsNguoiNhan.setText(emptyString);

            danhSachCoTheChon.getItems().clear();
            danhSachCoTheChon.getItems().addAll(homeList);

            listSelect.setVisible(true);
        }
    }

    /**
     * Phương thức xử lý khi radiobutton chonToanBo được click
     */
    public void selectRadioButtonChonToanBo() {
        listSelect.setVisible(false);
        dsNguoiNhan.setText("Thông báo này sẽ được gửi tới toàn bộ dân cư!");
        labelListReceiver.setText("Danh sách người nhận:");
        dsNguoiNhan.setEditable(false);
    }

    /**
     * Phương thức cập nhật giá trị dsNguoiNhan
     * khi bảng danhSachCoTheChon được click chọn một giá trị
     */
    public void capNhatDanhSachNguoiNhan() {
        danhSachCoTheChon.setOnMouseClicked(mouseEvent -> {
            String selected = danhSachCoTheChon.getSelectionModel().getSelectedItem();
            if(selected != null) {
                if (!currentList.contains(selected)) {
                    currentList = currentList + selected + "\n";
                    dsNguoiNhan.setText(currentList);
                }
            }
        });
    }

    /**
     * Phương thức thực hiện gửi thông báo khi nhấn button Gửi Thông Báo
     * @param event sự kiện click chuột vào button "Gửi thong báo"
     * @throws SQLException xảy ra lỗi truy vấn dữ liệu
     */
    public void guiThongBao(ActionEvent event) throws SQLException {
        listSelect.setVisible(false);

        // Kiểm tra lỗi để trống của các trường thông tin
        if(!(chonDanCu.isSelected() || chonHodan.isSelected() || chonToanBo.isSelected())) {
            errorThongBao.setText("Kiểu đối tượng người nhận chưa được chọn!");
            return;
        }
        if(dsNguoiNhan.getText().isEmpty()) {
            errorThongBao.setText("Danh sách nhân khẩu chưa được chọn!");
            return;
        }
        if(tieude.getText().isEmpty()) {
            errorThongBao.setText("Tiêu đề thông báo chưa được nhập!");
            return;
        }
        if(noidung.getText().isEmpty()) {
            errorThongBao.setText("Nội dung thông báo chưa được nhập!");
            return;
        }

        // Thực hiện gửi thông báo tương ứng với các radio button
        if(chonDanCu.isSelected()) {
            String error = LuuThongBao.tbToiNhanKhau(dsNguoiNhan.getText(), tieude.getText(), noidung.getText());
            errorThongBao.setText(error);
            if(!error.isEmpty()) return;
        }
        if(chonToanBo.isSelected()) {
            LuuThongBao.tbToiToanBo(tieude.getText(), noidung.getText());
        }
        if(chonHodan.isSelected()) {
            String error = LuuThongBao.tbToiHoKhau(dsNguoiNhan.getText(), tieude.getText(), noidung.getText());
            errorThongBao.setText(error);
            if(!error.isEmpty()) return;
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Gửi thông báo thành công!");
        alert.setTitle("Gửi thông báo thành công");
        alert.show();

        dsNguoiNhan.setText("");
        noidung.setText("");
        tieude.setText("");
        errorThongBao.setText("");
    }
 }
