package com.example.introductiontose.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Lớp SqlConnection cung cấp phương thức để kết nối đến cơ sở dữ liệu MySQL.
 */
public class SqlConnection {
    private final static String URL = "jdbc:mysql://db4free.net:3306/qlthuphidb";
    private final static String USERNAME = "sqladminsql";
    private final static String PASSWORD = "Mk123456";

    private static final HikariDataSource dataSource;

    // Khởi tạo HikariCP DataSource
    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(URL);
        config.setUsername(USERNAME);
        config.setPassword(PASSWORD);
        config.setMaximumPoolSize(10); // Số lượng kết nối tối đa trong pool

        dataSource = new HikariDataSource(config);
    }

    /**
     * Phương thức này trả về một đối tượng Connection từ pool để kết nối đến cơ sở dữ liệu MySQL.
     *
     * @return Đối tượng Connection cho kết nối đến cơ sở dữ liệu.
     */
    public static Connection connect() {
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            System.out.println("\u001B[32mKết nối thành công đến cơ sở dữ liệu!\u001B[0m");
        } catch (SQLException e) {
            System.err.println("Lỗi kết nối đến cơ sở dữ liệu: " + e.getMessage());
        }

        return connection;
    }

    /**
     * Phương thức này đóng đối tượng Connection và trả về nó vào pool.
     *
     * @param connection Đối tượng Connection cần đóng.
     */
    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("\u001B[34mĐã đóng kết nối đến cơ sở dữ liệu!\u001B[0m");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}