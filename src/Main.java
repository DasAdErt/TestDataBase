import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LoginService loginService = new LoginService();
        RegistrationService registrationService = new RegistrationService();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Выберите действие:\n1.Войти\n2.Зарегистрироваться\nВаш выбор: ");
        String choiceSign = scanner.nextLine();

        if (Objects.equals(choiceSign, "2")) {
            System.out.println("\n\n\nВы выбрали регистрацию!");

            System.out.print("Введите логин: ");
            String login = scanner.nextLine();

            System.out.print("Введите вашу почту: ");
            String email = scanner.nextLine();

            System.out.print("Введите пароль: ");
            String password = scanner.nextLine();

            System.out.print("Введите Секретный ключ: ");
            String secretKey = scanner.nextLine();

            boolean registrationSuccessful = registrationService.registerUser(login, email, password, secretKey);

            if (registrationSuccessful) {
                System.out.println("Регистрация прошла успешно!");
            } else {
                System.out.println("Ошибка при регистрации. Пожалуйста, проверьте данные.");
            }
        }

        System.out.println("\n\n\nВы выбрали вход в существующий аккаунт!");
        System.out.print("Введите логин или почту: ");
        String usernameOrMail = scanner.nextLine();

        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();

        System.out.println();

        boolean isAdmin = loginService.isAdmin(usernameOrMail);

        if (loginService.login(usernameOrMail, password)) {
            if (isAdmin){
                System.out.println("Вы вошли в профиль Админа!");
            } else {
                System.out.println("Вход выполнен успешно!");
            }
        } else {
            System.out.println("Ошибка входа. Проверьте имя пользователя и пароль.");
        }
    }
}