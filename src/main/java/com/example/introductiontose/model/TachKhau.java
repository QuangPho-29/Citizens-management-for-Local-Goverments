package com.example.introductiontose.model;

import java.util.List;

/**
 * Lớp TachKhau đại diện cho quá trình tách khẩu từ một hộ khẩu hiện tại.
 * Lớp này chứa thông tin về số căn cước công dân của chủ hộ mới, ID hộ khẩu gốc,
 * danh sách nhân khẩu được tách ra và trạng thái của quá trình tách khẩu.
 */
public class TachKhau {
    /**
     * Số căn cước công dân của chủ hộ mới sau khi tách khẩu.
     */
    private String soCccdChuHoMoi;

    /**
     * ID của hộ khẩu gốc mà từ đó nhân khẩu được tách ra.
     */
    private int idHoKhau;

    /**
     * Danh sách các nhân khẩu được tách ra từ hộ khẩu gốc.
     */
    private List<String> danhSachNhanKhau;

    /**
     * Trạng thái của quá trình tách khẩu.
     */
    private String trangThai;

    /**
     * Constructor để khởi tạo một đối tượng TachKhau với thông tin đầy đủ.
     *
     * @param soCccdChuHoMoi Số căn cước công dân của chủ hộ mới.
     * @param idHoKhau ID của hộ khẩu gốc.
     * @param danhSachNhanKhau Danh sách nhân khẩu được tách ra.
     * @param trangThai Trạng thái của quá trình tách khẩu.
     */
    public TachKhau(String soCccdChuHoMoi, int idHoKhau, List<String> danhSachNhanKhau, String trangThai) {
        this.soCccdChuHoMoi = soCccdChuHoMoi;
        this.idHoKhau = idHoKhau;
        this.danhSachNhanKhau = danhSachNhanKhau;
        this.trangThai = trangThai;
    }

    // Các phương thức getter và setter kèm theo chú thích tương ứng.

    /**
     * Lấy số căn cước công dân của chủ hộ mới.
     *
     * @return Số căn cước công dân của chủ hộ mới.
     */
    public String getSoCccdChuHoMoi() {
        return soCccdChuHoMoi;
    }

    /**
     * Thiết lập số căn cước công dân mới cho chủ hộ.
     *
     * @param soCccdChuHoMoi Số căn cước công dân mới của chủ hộ.
     */
    public void setSoCccdChuHoMoi(String soCccdChuHoMoi) {
        this.soCccdChuHoMoi = soCccdChuHoMoi;
    }

    /**
     * Lấy ID của hộ khẩu gốc.
     *
     * @return ID của hộ khẩu gốc.
     */
    public int getIdHoKhau() {
        return idHoKhau;
    }

    /**
     * Thiết lập ID mới cho hộ khẩu gốc.
     *
     * @param idHoKhau ID mới của hộ khẩu gốc.
     */
    public void setIdHoKhau(int idHoKhau) {
        this.idHoKhau = idHoKhau;
    }

    /**
     * Lấy danh sách nhân khẩu được tách ra.
     *
     * @return Danh sách nhân khẩu.
     */
    public List<String> getDanhSachNhanKhau() {
        return danhSachNhanKhau;
    }

    /**
     * Thiết lập danh sách nhân khẩu mới được tách ra.
     *
     * @param danhSachNhanKhau Danh sách nhân khẩu mới.
     */
    public void setDanhSachNhanKhau(List<String> danhSachNhanKhau) {
        this.danhSachNhanKhau = danhSachNhanKhau;
    }

    /**
     * Lấy trạng thái của quá trình tách khẩu.
     *
     * @return Trạng thái của quá trình tách khẩu.
     */
    public String getTrangThai() {
        return trangThai;
    }

    /**
     * Thiết lập trạng thái mới cho quá trình tách khẩu.
     *
     * @param trangThai Trạng thái mới của quá trình tách khẩu.
     */
    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
