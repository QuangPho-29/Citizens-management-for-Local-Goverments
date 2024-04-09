package com.example.introductiontose.view.icon;

/**
 * Enum ImageType định nghĩa các loại hình ảnh biểu tượng có thể sử dụng trong giao diện người dùng.
 * Mỗi loại hình ảnh liên quan đến một tình trạng cụ thể của biểu tượng, chẳng hạn như khi được chọn hoặc không được chọn.
 * Mỗi hình ảnh được liên kết với một đường dẫn tới tệp ảnh tương ứng.
 *
 * @author Duy
 * @version 1.0
 */
public enum ImageType {
    NHANKHAU_IN_SELECTED("/com/example/introductiontose/view/iconImg/icons8-customer-100.png"),
    NHANKHAU_OUT_SELECTED("/com/example/introductiontose/view/iconImg/icons8-customer-gray-100.png"),
    NHANKHAU_IN_UNSELECTED("/com/example/introductiontose/view/iconImg/icons8-customer-white-100.png"),
    NHANKHAU_OUT_UNSELECTED("/com/example/introductiontose/view/iconImg/icons8-user-100.png"),
    CHUHO_IN_SELECTED("/com/example/introductiontose/view/iconImg/icons8-customer-9FAAE5-100.png"),
    CHUHO_OUT_SELECTED("/com/example/introductiontose/view/iconImg/icons8-customer-color-100.png"),
    CHUHO_IN_UNSELECTED("/com/example/introductiontose/view/iconImg/icons8-customer-white-100.png"),
    CHUHO_OUT_UNSELECTED("/com/example/introductiontose/view/iconImg/icons8-user-color-100.png");
    
    private final String iconPath;
    
    /**
     * Constructor của Enum ImageType.
     *
     * @param iconPath Đường dẫn tới tệp ảnh biểu tượng.
     */
    ImageType(String iconPath) {
        this.iconPath = iconPath;
    }
    
    /**
     * Phương thức để lấy đường dẫn tới tệp ảnh biểu tượng.
     *
     * @return Đường dẫn tới tệp ảnh biểu tượng.
     */
    public String getIconPath() {
        return iconPath;
    }
}
