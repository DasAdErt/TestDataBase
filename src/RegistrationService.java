import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegistrationService {

    public boolean isLoginUnique(String login) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT COUNT(*) FROM users WHERE login = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, login);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int count = resultSet.getInt(1);
                        return count == 0;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean registerUser(String login, String email, String password, String secretKey) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            // Проверяем уникальность логина
            if (!isLoginUnique(login)) {
                System.out.println("\nПользователь с таким логином уже существует.");
                return false;
            }

            String sql = "INSERT INTO users (login, email, password, secret_key) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, login);
                preparedStatement.setString(2, email);
                preparedStatement.setString(3, password);
                preparedStatement.setString(4, secretKey);

                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
