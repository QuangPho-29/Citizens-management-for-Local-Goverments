package com.example.introductiontose.view.icon;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Lớp IconController là một lớp trừu tượng được sử dụng để định nghĩa các phương thức và thuộc tính chung cho các
 * controller của biểu tượng trong giao diện người dùng.
 *
 * <p>Các lớp con của IconController cần triển khai các phương thức abstract và có thể cung cấp dữ liệu và hiệu ứng
 * tương tác cho biểu tượng cụ thể.</p>
 *
 * <p>Lớp này cung cấp một số phương thức chung như thiết lập loại biểu tượng, xử lý sự kiện chuột và các phương thức
 * truy cập dữ liệu của biểu tượng.</p>
 *
 * @param <T> Kiểu dữ liệu của đối tượng dữ liệu biểu tượng.
 * @version 1.0
 */
public abstract class IconController<T> implements Initializable {
    @FXML
    protected Button buttonIcon;
    @FXML
    protected ImageView imageIcon;
    protected boolean isSelected = false;
    protected IconType iconType;
    
    /**
     * Phương thức khởi tạo controller biểu tượng, được gọi khi controller được khởi tạo.
     *
     * @param url            URL tới tài nguyên FXML.
     * @param resourceBundle ResourceBundle để cung cấp nguồn dữ liệu local.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setIconType(IconType.NHANKHAU);
    }
    
    /**
     * Thiết lập loại biểu tượng và áp dụng CSS tương ứng.
     *
     * @param iconType Loại biểu tượng.
     */
    public void setIconType(IconType iconType) {
        this.iconType = iconType;
        setCssButton(iconType);
        mouseEffect();
    }
    
    /**
     * Phương thức abstract để lấy dữ liệu từ biểu tượng.
     *
     * @return Đối tượng dữ liệu của biểu tượng.
     */
    abstract public T getData();
    
    /**
     * Phương thức abstract để thiết lập dữ liệu cho biểu tượng cụ thể.
     *
     * @param t Đối tượng dữ liệu của biểu tượng.
     */
    abstract public void setData(T t);
    
    /**
     * Kiểm tra xem biểu tượng có được chọn hay không.
     *
     * @return True nếu biểu tượng được chọn, False nếu không.
     */
    public boolean isSelected() {
        return isSelected;
    }
    
    /**
     * Thiết lập trạng thái được chọn của biểu tượng.
     *
     * @param selected True nếu biểu tượng được chọn, False nếu không.
     */
    public void setSelected(boolean selected) {
        isSelected = selected;
        mouseEffect();
    }
    
    /**
     * Phương thức abstract để xử lý hiệu ứng chuột cho biểu tượng.
     */
    abstract public void mouseEffect();
    
    /**
     * Thiết lập CSS cho nút biểu tượng dựa trên loại biểu tượng.
     *
     * @param iconType Loại biểu tượng.
     */
    private void setCssButton(IconType iconType) {
        // Xóa tất cả các style class hiện tại
        buttonIcon.getStyleClass().clear();
        
        // Set Css
        buttonIcon.getStyleClass().add(iconType.getClassName());
    }
    
    /**
     * Tạo đối tượng hình ảnh từ tài nguyên được xác định bởi tên.
     *
     * @param name Tên của tài nguyên hình ảnh.
     * @return Đối tượng hình ảnh được tạo từ tài nguyên.
     */
    protected Image imageFromResourceAsStream(String name) {
        return new Image(Objects.requireNonNull(getClass().getResourceAsStream(name)));
    }
}
