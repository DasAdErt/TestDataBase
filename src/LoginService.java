import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginService {
    public boolean login(String usernameOrMail, String password) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM users WHERE (login = ? OR email = ?) AND password = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, usernameOrMail);
                preparedStatement.setString(2, usernameOrMail);
                preparedStatement.setString(3, password);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next(); // Если есть соответствующая запись, возвращаем true
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isAdmin(String usernameOrMail) {
        String sql = "SELECT admin FROM users WHERE login = ? OR email = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, usernameOrMail);
            preparedStatement.setString(2, usernameOrMail);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Получаем значение столбца "admin"
                    boolean isAdmin = resultSet.getBoolean("admin");
                    return isAdmin;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Если не удалось получить данные из базы данных, считаем, что пользователь не является администратором
    }
}
