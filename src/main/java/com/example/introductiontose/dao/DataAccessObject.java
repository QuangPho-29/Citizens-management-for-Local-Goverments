package com.example.introductiontose.dao;

import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Giao diện định nghĩa các phương thức cơ bản để thao tác với cơ sở dữ liệu.
 *
 * @param <T> Loại đối tượng mà DAO đang xử lý.
 * @param <K> Loại dữ liệu của khóa chính của đối tượng.
 */
public interface DataAccessObject<T, K> {
    /**
     * Lấy tất cả các đối tượng từ cơ sở dữ liệu.
     *
     * @return Danh sách chứa tất cả các đối tượng.
     * @throws SQLException Nếu có lỗi khi thao tác với cơ sở dữ liệu.
     */
    List<T> getAll() throws SQLException;
    
    /**
     * Lấy một đối tượng từ cơ sở dữ liệu dựa trên khóa chính.
     *
     * @param id Khóa chính của đối tượng cần lấy.
     * @return Optional chứa đối tượng nếu tồn tại, hoặc Optional rỗng nếu không.
     * @throws SQLException Nếu có lỗi khi thao tác với cơ sở dữ liệu.
     */
    Optional<T> get(K id) throws SQLException;
    
    /**
     * Lưu một đối tượng mới vào cơ sở dữ liệu.
     *
     * @param t Đối tượng cần lưu.
     * @throws SQLException Nếu có lỗi khi thao tác với cơ sở dữ liệu.
     * @throws IllegalArgumentException Nếu đối tượng đã tồn tại trong cơ sở dữ liệu.
     */
    void save(@NotNull T t) throws SQLException, IllegalArgumentException;
    
    /**
     * Cập nhật thông tin của một đối tượng trong cơ sở dữ liệu.
     *
     * @param t Đối tượng cần cập nhật.
     * @throws SQLException Nếu có lỗi khi thao tác với cơ sở dữ liệu.
     * @throws IllegalArgumentException Nếu đối tượng không tồn tại trong cơ sở dữ liệu.
     */
    void update(@NotNull T t) throws SQLException, IllegalArgumentException;
    
    /**
     * Xóa một đối tượng khỏi cơ sở dữ liệu.
     *
     * @param t Đối tượng cần xóa.
     * @throws SQLException Nếu có lỗi khi thao tác với cơ sở dữ liệu.
     * @throws IllegalArgumentException Nếu đối tượng không tồn tại trong cơ sở dữ liệu.
     */
    void delete(@NotNull T t) throws SQLException, IllegalArgumentException;
}
