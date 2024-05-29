package csdltrongjava;

import java.sql.*;
import java.util.*;

public class TableViewer {
    // Thông tin kết nối MySQL
    final static String jdbcURL = "jdbc:mysql://localhost:3306/StudentDB"; // Thay đổi StudentDB thành tên database của bạn
    final static String jdbcDriver = "com.mysql.cj.jdbc.Driver"; // Driver cho MySQL
    final static String table = "STUDENT"; // Tên bảng

    public static void main(String[] args) {
        System.out.println("--Table Viewer--");

        // Thông tin đăng nhập MySQL
        String user = "root"; // Thay đổi root thành tên người dùng MySQL của bạn
        String password = "dinhthai2004"; // Thay đổi password thành mật khẩu của người dùng MySQL của bạn

        Connection connection = null;
        Statement statement = null;

        try {
            // Tải driver MySQL
            Class.forName(jdbcDriver);

            // Tạo kết nối tới MySQL
            connection = DriverManager.getConnection(jdbcURL, user, password);
            System.out.println("Kết nối thành công tới cơ sở dữ liệu!");

            // Tạo statement để truy vấn dữ liệu
            statement = connection.createStatement();
            String query = "SELECT * FROM " + table;
            ResultSet resultSet = statement.executeQuery(query);

            // Lấy metadata để in tên cột
            ResultSetMetaData rsMetaData = resultSet.getMetaData();
            int columnCount = rsMetaData.getColumnCount();

            // In tên cột
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(rsMetaData.getColumnName(i) + "\t");
            }
            System.out.println();

            // In dữ liệu từ bảng
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(resultSet.getString(i) + "\t");
                }
                System.out.println();
            }

            // Đóng resultSet
            resultSet.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Không tìm thấy driver JDBC.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Lỗi khi kết nối tới cơ sở dữ liệu.");
            e.printStackTrace();
        } finally {
            // Đóng statement và connection
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
