package com.example.introductiontose.view.icon;

import com.example.introductiontose.model.HoKhau;

/**
 * Lớp IconHoKhauController là một lớp controller để hiển thị biểu tượng cho đối tượng Hộ khẩu trong giao diện người dùng.
 * Kế thừa từ lớp cha IconController và triển khai các phương thức cần thiết để hiển thị thông tin và hiệu ứng cho biểu tượng Hộ khẩu.
 *
 * @version 1.0
 */
public class IconHoKhauController extends IconController<HoKhau> {
    private HoKhau hoKhau;
    
    /**
     * Phương thức để thiết lập dữ liệu của biểu tượng Hộ khẩu.
     *
     * @param hoKhau Đối tượng Hộ khẩu chứa thông tin cần hiển thị.
     */
    @Override
    public void setData(HoKhau hoKhau) {
        this.hoKhau = hoKhau;
        buttonIcon.setText(String.valueOf(hoKhau.getIdHoKhau()) + " - " + hoKhau.getTenChuHo());
    }
    
    /**
     * Phương thức để lấy dữ liệu của biểu tượng Hộ khẩu.
     *
     * @return Đối tượng Hộ khẩu chứa thông tin.
     */
    @Override
    public HoKhau getData() {
        return hoKhau;
    }
    
    /**
     * Phương thức để xử lý hiệu ứng chuột cho biểu tượng Hộ khẩu.
     * Hiển thị hình ảnh tương ứng với trạng thái chuột hover hoặc không hover.
     */
    @Override
    public void mouseEffect() {
        String path = "/com/example/introductiontose/view/iconImg/";
        String name;
        if (buttonIcon.isHover()) {
            name = "icons8-house-fill-100.png";
        } else {
            name = "icons8-house-100.png";
        }
        imageIcon.setImage(imageFromResourceAsStream(path + name));
    }
}
