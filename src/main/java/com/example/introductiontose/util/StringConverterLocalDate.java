package com.example.introductiontose.util;

import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Lớp StringConverterLocalDate là một StringConverter được sử dụng để chuyển đổi giữa đối tượng LocalDate và chuỗi String.
 *
 * <p>Đối tượng này được sử dụng để định dạng ngày thành chuỗi và ngược lại dựa trên định dạng "dd/MM/yyyy".</p>
 *
 * <p>Sử dụng StringConverterLocalDate trong các thành phần của giao diện người dùng JavaFX để hiển thị và nhập liệu ngày tháng.</p>
 *
 * <p>Chú ý: Lớp này được thiết kế để sử dụng chung trong các ứng dụng JavaFX và hỗ trợ quá trình chuyển đổi chuỗi ngày giữa
 * người dùng và ứng dụng.</p>
 *
 * @author Duy
 * @version 1.0
 */
public class StringConverterLocalDate extends StringConverter<LocalDate> {
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    /**
     * Chuyển đổi đối tượng LocalDate thành chuỗi String.
     *
     * @param localDate Đối tượng LocalDate cần chuyển đổi.
     * @return Chuỗi String biểu diễn ngày tháng theo định dạng "dd/MM/yyyy".
     */
    @Override
    public String toString(LocalDate localDate) {
        if (localDate != null) {
            return dateFormatter.format(localDate);
        }
        return "";
    }
    
    /**
     * Chuyển đổi chuỗi String thành đối tượng LocalDate.
     *
     * @param s Chuỗi String cần chuyển đổi thành đối tượng LocalDate.
     * @return Đối tượng LocalDate biểu diễn ngày tháng được chuyển từ chuỗi.
     */
    @Override
    public LocalDate fromString(String s) {
        if (s != null && !s.isEmpty()) {
            return LocalDate.parse(s, dateFormatter);
        }
        return null;
    }
    
    /**
     * Trả về một đối tượng StringConverterLocalDate để sử dụng trong các thành phần của giao diện người dùng.
     *
     * @return Đối tượng StringConverterLocalDate.
     */
    public StringConverterLocalDate localDate() {
        return this;
    }
}
