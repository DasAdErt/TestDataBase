import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/mysql"; // URL для подключения к базе данных
    private static final String USERNAME = "ssdghb"; // Имя пользователя базы данных
    private static final String PASSWORD = "1589101100Lura!"; // Пароль пользователя базы данных

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}