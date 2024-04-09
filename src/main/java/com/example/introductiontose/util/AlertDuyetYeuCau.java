package com.example.introductiontose.util;

import com.example.introductiontose.dao.*;
import com.example.introductiontose.database.SqlConnection;
import com.example.introductiontose.model.*;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import static com.example.introductiontose.controller.admin.YeuCauNhanKhauController.danhsachHBox;

public class AlertDuyetYeuCau {
    public static Connection connection = SqlConnection.connect();
    public enum TableTypeThayDoi {
        THAYDOINHANKHAU,
        THAYDOIHOKHAU,
        DANGKYTAMTRU,
        TAMVANG
    }

    public static void showAlertHuyBo(String idHbox, String soCccd1, String soCccd2,  Button buttonHuyBo) {
        // Tạo Alert
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Tại sao không xác nhận!");
        // Tạo Label "Lý do không chấp nhận"
        Label label = new Label("Lý do không chấp nhận");

        // Tạo TextArea
        TextArea textArea = new TextArea();

        // Đặt nội dung cho Alert là VBox chứa Label và TextArea
        alert.getDialogPane().setContent(new javafx.scene.layout.VBox(label, textArea));

        // Thêm nút "Xác nhận"
        ButtonType confirmButton = new ButtonType("Xác nhận hủy bỏ");
        alert.getButtonTypes().add(confirmButton);

        // Tùy chỉnh kích thước của DialogPane
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.getDialogPane().setMinWidth(400);
        alert.getDialogPane().setMaxHeight(Region.USE_PREF_SIZE);
        alert.getDialogPane().setMaxWidth(400);

        // Tùy chỉnh khoảng cách giữa Label và TextArea
        label.setStyle("-fx-padding: 0 0 10 0;");

        // Tùy chỉnh màu nền của nút "Xác nhận"
        alert.getDialogPane().lookupButton(confirmButton).setStyle("-fx-background-color: #BBF7D0;");

        // Xử lý sự kiện khi nút "Xác nhận" được nhấn
        alert.setResultConverter(dialogButton -> {
            if (dialogButton == confirmButton) {
                // System.out.println(textArea.getText());

                // Gui thong bao khong xac nhan cho nguoi yeu cau
                String tieude = "";
                String noidung = textArea.getText();
                if(idHbox.substring(0,2).equals("NK")) {
                    tieude = "Yêu cầu thay đổi nhân khẩu không được xác nhận";
                    Integer idTDNK = Integer.parseInt(idHbox.substring(2));
                    try {
                        guiThongBao(soCccd1, tieude, noidung);
                        updateTrangThai(idTDNK, "không được xác nhận", TableTypeThayDoi.THAYDOINHANKHAU);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }

                if(idHbox.substring(0,2).equals("HK")) {
                    tieude = "Yêu cầu thay đổi hộ khẩu không được xác nhận";
                    Integer idTDHK = Integer.parseInt(idHbox.substring(2));
                    try {
                        guiThongBao(soCccd1, tieude, noidung);
                        guiThongBao(soCccd2, tieude, noidung);
                        updateTrangThai(idTDHK, "không được xác nhận", TableTypeThayDoi.THAYDOIHOKHAU);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }

                if(idHbox.substring(0,2).equals("TT")) {
                    tieude = "Yêu cầu tạm trú không được xác nhận";
                    Integer soCccdDKTT = Integer.parseInt(idHbox.substring(2));
                    try {
                        guiThongBao(soCccd1, tieude, noidung);
                        guiThongBao(soCccd2, tieude, noidung);
                        updateTrangThai(soCccdDKTT, "không được xác nhận", TableTypeThayDoi.DANGKYTAMTRU);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }

                if(idHbox.substring(0,2).equals("TV")) {
                    tieude = "Yêu cầu tạm vắng không được xác nhận";
                    Integer idTamVang = Integer.parseInt(idHbox.substring(2));
                    try {
                        guiThongBao(soCccd1, tieude, noidung);
                        updateTrangThai(idTamVang, "không được xác nhận", TableTypeThayDoi.TAMVANG);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
                hiddenHbox(buttonHuyBo);

            }
            return null;
        });

        // Hiển thị Alert và lấy giá trị từ TextArea khi nút "Xác nhận" được nhấn
        Optional<ButtonType> result = alert.showAndWait();
        result.ifPresent(reason -> {
            System.out.println("Lý do không chấp nhận: " + reason);
            // Thêm xử lý chi tiết ở đây
        });
    }

    public static void showAlertDongY(String idHbox, String soCccd1, String soCccd2,  Button buttonDongY) {
        Alert alertDongY = new Alert(Alert.AlertType.CONFIRMATION);
        alertDongY.setTitle("Xác nhận");
        alertDongY.setHeaderText("Đồng ý xác nhận");
        alertDongY.getButtonTypes().setAll(ButtonType.OK);

        // Hiển thị Alert và chờ người dùng xác nhận
        alertDongY.showAndWait().ifPresent(buttonType -> {
            if (buttonType == ButtonType.OK) {
                String tieude = "";
                String noidung = "";
                if(idHbox.substring(0,2).equals("NK")) {
                    tieude = "Yêu cầu về thay đổi nhân khẩu đã được xác nhận";
                    Integer idTDNK = Integer.parseInt(idHbox.substring(2));
                    try {
                        updateTrangThai(idTDNK, "đã xác nhận", TableTypeThayDoi.THAYDOINHANKHAU);

                        ThayDoiNhanKhauDao thayDoiNhanKhauDAO = new ThayDoiNhanKhauDao(connection);
                        Optional<ThayDoiNhanKhau> resultTDNK = thayDoiNhanKhauDAO.get(idTDNK);
                        if(resultTDNK.isPresent()) {
                            ThayDoiNhanKhau tdnk = resultTDNK.get();
                            if(tdnk.getGhichu().contains("qua đời")) {
                                String soCccdQuaDoi = tdnk.getGhichu().substring(0,12);
                                NhanKhauDAO nhanKhauDAO = new NhanKhauDAO(connection, NhanKhauDAO.TableType.NHANKHAU);
                                Optional<NhanKhau> resultNK = nhanKhauDAO.get(soCccdQuaDoi);
                                if(resultNK.isPresent()) {
                                    noidung = "Yêu cầu khai tử cho nhân khẩu " + resultNK.get().getThongTinNhanKhau().getHoTen()
                                            + " đã được xác nhận";
                                    System.out.println(noidung);
                                }
                                guiThongBao(soCccd1, tieude, noidung);
                                // xử lý các dữ liệu liên quan đến người đã mất
                                // thay doi truong nghe nghiep trong bang nhankhau thanh "qua doi"
                                updateTrangThaiQuaDoi(soCccdQuaDoi);
                                // chuyen toan bo so du ve cho chu ho
                                chuyenTienNguoiMatVeChuHo(soCccd1, soCccdQuaDoi);
                                // xoa tai khoan nhan khau
                                TaiKhoanNhanKhauDAO taiKhoanNhanKhauDAO = new TaiKhoanNhanKhauDAO(connection);
                                taiKhoanNhanKhauDAO.delete(soCccdQuaDoi);
                            } else {
                                noidung = "Yêu cầu thay đổi nơi cư trú đến " + tdnk.getNoichuyenden() + "\n"
                                        + " bắt đầu từ thời gian " + tdnk.getNgaychuyendi() + " đã được xác nhận";
                                guiThongBao(soCccd1, tieude, noidung);
                            }
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }

                if(idHbox.substring(0,2).equals("HK")) {
                    tieude = "Yêu cầu về thay đổi hộ khẩu đã được xác nhận";
                    Integer idTDHK = Integer.parseInt(idHbox.substring(2));
                    ThayDoiHoKhauDAO tdhkDao = new ThayDoiHoKhauDAO(connection);
                    Optional<ThayDoiHoKhau> resultTDHK;
                    try {
                        updateTrangThai(idTDHK, "đã xác nhận", TableTypeThayDoi.THAYDOIHOKHAU);

                        resultTDHK = tdhkDao.get(idTDHK);
                        if(resultTDHK.isPresent()) {
                            ThayDoiHoKhau tdhk = resultTDHK.get();
                            noidung = "Yêu cầu " + tdhk.getNoiDung() + " đã được xác nhận.";
                            System.out.println(noidung);
                            guiThongBao(soCccd1, tieude, noidung);
                            guiThongBao(soCccd2, tieude, noidung);
                            // cap nhat bang ho khau (thay doi chu ho moi)
                            capNhatChuHoMoi(tdhk.getIdHoKhau(), soCccd2);
                            // cap nhat cac quan he
                            updateQuanHeChuHo(soCccd2, "");
                            if(tdhk.getIdHoKhau() == 2) {
                                updateQuanHeChuHo(soCccd1, "vợ/chồng");
                            }
                            if(tdhk.getIdHoKhau() == 3) {
                                updateQuanHeChuHo(soCccd1, "con trai");
                            }

                        }

                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }

                if(idHbox.substring(0,2).equals("TT")) {
                    tieude = "Yêu cầu tạm trú đã được xác nhận";
                    Integer soCccdTamTru = Integer.parseInt(idHbox.substring(2));
                    try {
                        updateTrangThai(soCccdTamTru, "đã xác nhận", TableTypeThayDoi.DANGKYTAMTRU);

                        TamTruDAO tamTruDAO = new TamTruDAO(connection);
                        Optional<TamTru> resultTDNK = tamTruDAO.get(soCccdTamTru);
                        if (resultTDNK.isPresent()) {
                            TamTru tamtru = resultTDNK.get();
                            noidung = "Yêu cầu tạm trú của " + tamtru.getHoTen() + " đã được xác nhận";
                            guiThongBao(soCccd1, tieude, noidung);
                            guiThongBao(soCccd2, tieude, noidung);
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }

                if(idHbox.substring(0,2).equals("TV")) {
                    tieude = "Yêu cầu tạm vắng đã được xác nhận";
                    Integer idTamVang = Integer.parseInt(idHbox.substring(2));
                    try {
                        updateTrangThai(idTamVang, "đã xác nhận", TableTypeThayDoi.TAMVANG);

                        TamVangDAO tamVangDAO = new TamVangDAO(connection);
                        Optional<TamVang> resultTDNK = tamVangDAO.get(idTamVang);
                        if (resultTDNK.isPresent()) {
                            TamVang tamVang = resultTDNK.get();
                            noidung = "Yêu cầu tạm vắng trong khoảng thời gian từ ngày "
                                    + tamVang.getNgayBatDau() + " đến ngày " + tamVang.getNgayKetThuc() + " đã được xác nhận";
                            guiThongBao(soCccd1, tieude, noidung);
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }


                hiddenHbox(buttonDongY);
            }
        });
    }

    public static void guiThongBao(String soCccd, String tieuDe, String noiDung) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO thongbao " +
                "(soCccd, tieuDe, noiDung, ngayTao) " +
                "VALUES (?, ?, ?, ?)");
        statement.setString(1, soCccd);
        statement.setString(2, tieuDe);
        statement.setString(3, noiDung);
        statement.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
        statement.executeUpdate();
    }

    public static void updateTrangThai(int idthaydoi, String trangThai, TableTypeThayDoi table) throws SQLException {
        String tablename = "";
        String typeId = "";
        if(table == TableTypeThayDoi.THAYDOINHANKHAU) {
            tablename = "thaydoinhankhau";
            typeId = "idThayDoi";
        }
        if(table == TableTypeThayDoi.THAYDOIHOKHAU) {
            tablename = "thaydoihokhau";
            typeId = "idThayDoiHoKhau";
        }
        if(table == TableTypeThayDoi.DANGKYTAMTRU) {
            tablename = "dangkytamtru";
            typeId = "soCccd";
        }
        if(table == TableTypeThayDoi.TAMVANG) {
            tablename = "tamvang";
            typeId = "idTamVang";
        }
        PreparedStatement statement = connection.prepareStatement("UPDATE " + tablename + " SET " +
                "trangThai = ? " +
                "WHERE " + typeId + " = ?");
        statement.setString(1, trangThai);
        statement.setInt(2, idthaydoi);
        statement.executeUpdate();
    }

    public static void updateTrangThaiQuaDoi(String soCccd) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE nhankhau SET " +
                "ngheNghiep = ? " +
                "WHERE soCccd = ?");
        statement.setString(1, "qua đời");
        statement.setString(2, soCccd);
        statement.executeUpdate();
    }
    public static void updateQuanHeChuHo(String soCccd, String quanHe) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE nhankhau SET " +
                "quanHe = ? " +
                "WHERE soCccd = ?");
        statement.setString(1, quanHe);
        statement.setString(2, soCccd);
        statement.executeUpdate();
    }

    public static void chuyenTienNguoiMatVeChuHo(String soCccdChuHo, String soCccdNguoiMat) throws SQLException {
        TaiKhoanNhanKhauDAO taiKhoanNhanKhauDAO = new TaiKhoanNhanKhauDAO(connection);
        Optional<TaiKhoanNhanKhau> resultChuHo = taiKhoanNhanKhauDAO.get(soCccdChuHo);
        Optional<TaiKhoanNhanKhau> resultNguoiMat = taiKhoanNhanKhauDAO.get(soCccdNguoiMat);
        if(resultChuHo.isPresent() && resultNguoiMat.isPresent()) {
            TaiKhoanNhanKhau chuHo = resultChuHo.get();
            TaiKhoanNhanKhau nguoiMat = resultNguoiMat.get();
            int soDuMoiChuHo = chuHo.getSoDuTaiKhoan() + nguoiMat.getSoDuTaiKhoan();
            TaiKhoanNhanKhau newChuHo = new TaiKhoanNhanKhau(chuHo.getSoCCCD(), chuHo.getTentaikhoan(), chuHo.getPass(), soDuMoiChuHo);
            taiKhoanNhanKhauDAO.update(newChuHo);
        }
    }

    public static void capNhatChuHoMoi(int idHK, String soCccdChuHoMoi) throws SQLException {
        NhanKhauDAO nhanKhauDAO = new NhanKhauDAO(connection, NhanKhauDAO.TableType.NHANKHAU);
        Optional<NhanKhau> resultNK = nhanKhauDAO.get(soCccdChuHoMoi);
        if(resultNK.isPresent()) {
            String hoTenChuHoMoi = resultNK.get().getThongTinNhanKhau().getHoTen();
            PreparedStatement statement = connection.prepareStatement("UPDATE hokhau SET " +
                    "tenChuHo = ? , " +
                    "soCccdChuHo = ? " +
                    "WHERE idHoKhau = ?");
            statement.setString(1, hoTenChuHoMoi);
            statement.setString(2, soCccdChuHoMoi);
            statement.setInt(3, idHK);
            statement.executeUpdate();
        }
    }

    public static void hiddenHbox(Button button) {
        HBox parentHBox = (HBox) button.getParent();
        parentHBox.setVisible(false);
        parentHBox.setManaged(false);
        if(danhsachHBox.contains(parentHBox)) {
            danhsachHBox.remove(parentHBox);
        }
    }

}