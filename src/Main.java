import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LoginService loginService = new LoginService();
        Scanner scanner = new Scanner(System.in);

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