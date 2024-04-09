package com.example.introductiontose.view.icon;

/**
 * Enum IconType định nghĩa các loại biểu tượng có thể hiển thị trong giao diện người dùng.
 * Mỗi loại biểu tượng được liên kết với một tên lớp CSS để thiết lập kiểu cho nút biểu tượng tương ứng.
 *
 * @author Duy
 * @version 1.0
 */
public enum IconType {
    HOKHAU("btn-ho-khau"),
    NHANKHAU("btn-nhan-khau"),
    CHUHO("btn-chu-ho");
    
    private final String className;
    
    /**
     * Constructor của Enum IconType.
     *
     * @param className Tên lớp CSS liên kết với loại biểu tượng.
     */
    IconType(String className) {
        this.className = className;
    }
    
    /**
     * Phương thức để lấy tên lớp CSS liên kết với loại biểu tượng.
     *
     * @return Tên lớp CSS của loại biểu tượng.
     */
    public String getClassName() {
        return className;
    }
}
