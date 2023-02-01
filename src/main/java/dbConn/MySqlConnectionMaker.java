package dbConn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnectionMaker implements ConnectionMaker {
    private final String ADDRESS = "jdbc:mysql://localhost/sakila";
    private final String USERNAME = "root";
    private final String PASSWORD = "1111";

    @Override
    public Connection makeConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // 프로그램 내부에서 mysql과 연결하는 드라이버 클래스를 불러들여온것
            connection = DriverManager.getConnection(ADDRESS, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }
}
