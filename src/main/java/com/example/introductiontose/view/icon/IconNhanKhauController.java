package com.example.introductiontose.view.icon;

import com.example.introductiontose.model.NhanKhau;

import java.time.format.DateTimeFormatter;

/**
 * Lớp IconNhanKhauController là một lớp controller để hiển thị biểu tượng cho đối tượng Nhân khẩu trong giao diện người dùng.
 * Kế thừa từ lớp cha IconController và triển khai các phương thức cần thiết để hiển thị thông tin và hiệu ứng cho biểu tượng Nhân khẩu.
 *
 * @version 1.0
 */
public class IconNhanKhauController extends IconController<NhanKhau> {
    private NhanKhau nhanKhau;
    
    /**
     * Phương thức để lấy dữ liệu của biểu tượng Nhân khẩu.
     *
     * @return Đối tượng Nhân khẩu chứa thông tin.
     */
    @Override
    public NhanKhau getData() {
        return nhanKhau;
    }
    
    /**
     * Phương thức để thiết lập dữ liệu của biểu tượng Nhân khẩu.
     *
     * @param nhanKhau Đối tượng Nhân khẩu chứa thông tin cần hiển thị.
     */
    @Override
    public void setData(NhanKhau nhanKhau) {
        this.nhanKhau = nhanKhau;
        // Định dạng ngày giờ theo "dd/MM/yyyy"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        buttonIcon.setText(nhanKhau.getThongTinNhanKhau().getHoTen() + "\n" +
                nhanKhau.getThongTinNhanKhau().getNgaySinh().format(formatter));
    }
    
    /**
     * Phương thức để xử lý hiệu ứng chuột cho biểu tượng Nhân khẩu.
     * Hiển thị hình ảnh tương ứng với trạng thái chuột hover và không hover, cũng như trạng thái được chọn hoặc không được chọn.
     */
    @Override
    public void mouseEffect() {
        ImageType imageType = null;
        
        if (buttonIcon.isHover()) {
            if (isSelected && iconType == IconType.CHUHO) {
                imageType = ImageType.CHUHO_IN_SELECTED;
            } else if (isSelected && iconType == IconType.NHANKHAU) {
                imageType = ImageType.NHANKHAU_IN_SELECTED;
            } else if (!isSelected && iconType == IconType.CHUHO) {
                imageType = ImageType.CHUHO_IN_UNSELECTED;
            } else if (!isSelected && iconType == IconType.NHANKHAU) {
                imageType = ImageType.NHANKHAU_IN_UNSELECTED;
            }
        } else {
            if (isSelected && iconType == IconType.CHUHO) {
                imageType = ImageType.CHUHO_OUT_SELECTED;
            } else if (isSelected && iconType == IconType.NHANKHAU) {
                imageType = ImageType.NHANKHAU_OUT_SELECTED;
            } else if (!isSelected && iconType == IconType.CHUHO) {
                imageType = ImageType.CHUHO_OUT_UNSELECTED;
            } else if (!isSelected && iconType == IconType.NHANKHAU) {
                imageType = ImageType.NHANKHAU_OUT_UNSELECTED;
            }
        }
        
        if (imageType != null) {
            imageIcon.setImage(imageFromResourceAsStream(imageType.getIconPath()));
        }
    }
}
