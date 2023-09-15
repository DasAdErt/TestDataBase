import java.util.Scanner;

public class User {
    LoginService loginService = new LoginService();
    RegistrationService registrationService = new RegistrationService();
    Scanner scanner = new Scanner(System.in);

    private void choice(){
        System.out.print("Выберите действие:\n1. Войти\n2. Зарегистрироваться\nВаш выбор: ");
        String choiceSign = scanner.nextLine();

        switch (choiceSign){
            case "1" -> {
                System.out.println("\n\n\nВы выбрали вход в существующий аккаунт!");
                login();
            }
            case "2" -> {
                System.out.println("\n\n\nВы выбрали регистрацию аккаунта!");
                registration();
            }
            default -> System.out.println("Вы не прошли проверку на IQ!");
        }
    }

    private void registration(){
        System.out.print("Введите логин: ");
        String login = scanner.nextLine();

        if (!registrationService.isLoginUnique(login)) {
            System.out.println("\nПользователь с таким логином уже существует.");
            return;
        }

        System.out.print("Введите вашу почту: ");
        String email = scanner.nextLine();

        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();

        System.out.print("Введите Секретный ключ: ");
        String secretKey = scanner.nextLine();

        boolean registrationSuccessful = registrationService.registerUser(login, email, password, secretKey);

        if (registrationSuccessful) {
            System.out.println("Регистрация прошла успешно!");
            System.out.println("\n\nВойдите в только что созданный аккаунт!");
            login();
        }
    }

    private void login(){
        System.out.print("Введите логин или почту: ");
        String usernameOrMail = scanner.nextLine();

        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();

        System.out.println();

        boolean isAdmin = loginService.isAdmin(usernameOrMail);

        if (loginService.login(usernameOrMail, password)) {
            if (isAdmin){
                System.out.println("Вы вошли в профиль Админа!");
                userProfileMenu(usernameOrMail, true);
            } else {
                System.out.println("Вход выполнен успешно!");
                userProfileMenu(usernameOrMail, false);
            }
        } else {
            System.out.println("Ошибка входа. Проверьте имя пользователя и пароль.");
        }
    }

    private void userProfileMenu(String login, boolean isAdmin) {
        System.out.println("\n\n\nДобро пожаловать в меню профиля, " + login + "!");

        if (isAdmin) { // Выбор для админа
            System.out.println("Выберите меню: \n1. Меню Администратора\n2. Меню пользователя\nВыбор: ");

            int choiceMenus = scanner.nextInt();
            scanner.nextLine();

            switch (choiceMenus){
                case 1 -> { //Меню Администратора
                    System.out.print("Меню для администратора:\n1. Посмотреть БД\n" +
                            "2. Сделать запрос в БД\n3. Выход\nВаш выбор: ");

                    int adminChoice = scanner.nextInt();
                    scanner.nextLine();

                    switch (adminChoice) {
                        case 1:
                            viewDatabaseTable();
                            break;
                        case 2:
                            databaseQueryMenu();
                            break;
                        case 3:
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Неверный выбор.");
                            break;
                    }
                }
                case 2 -> { // Меню пользователя в админке
                    System.out.print("Меню пользователя:\n1. Об аккаунте\n2. Поменять почту\n" +
                            "3. Поменять пароль\n4. Выход\nВыбор: ");

                    int userChoice = scanner.nextInt();

                    scanner.nextLine();

                    switch (userChoice) {
                        case 1:
                            viewAccountInfo(login);
                            break;
                        case 2:
                            changeEmail(login);
                            break;
                        case 3:
                            changePassword(login);
                            break;
                        case 4:
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Ошибка, как так то?");
                            break;
                    }
                }
                default -> System.out.println("Ошибка, так получилось!");
            }
        } else { // Меню пользователя без админки
            System.out.print("Меню пользователя:\n1. Об аккаунте\n2. Поменять почту\n" +
                    "3. Поменять пароль\n4. Выход\nВыбор: ");

            int userChoice = scanner.nextInt();

            scanner.nextLine();

            switch (userChoice) {
                case 1:
                    viewAccountInfo(login);
                    break;
                case 2:
                    changeEmail(login);
                    break;
                case 3:
                    changePassword(login);
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Это перебор уже, хватит!");
                    break;
            }
        }
    }

    private void viewAccountInfo(String login) {
    }

    private void changeEmail(String login) {
    }

    private void changePassword(String login) {
    }

    private void viewDatabaseTable() {
    }

    private void databaseQueryMenu() {
    }


    public void run(){
        choice();
    }
}
