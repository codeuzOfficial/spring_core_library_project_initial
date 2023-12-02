package dasturlash.uz.controller;

import dasturlash.uz.container.ComponentContainer;
import dasturlash.uz.dto.Profile;
import dasturlash.uz.service.InitService;
import dasturlash.uz.util.ScannerUtil;

public class MainController {
    // lesson finished
    public void start() {
        InitService initService = new InitService();

        initService.initCreateFile();
        initService.initAdmin();
        initService.initTestStudent();

        boolean loop = true;
        while (loop) {
            showMenu();
            int action = ScannerUtil.getAction();
            switch (action) {
                case 1:
                    ComponentContainer.bookService.all();
                    break;
                case 2:
                    search();
                    break;
                case 3:
                    byCategory();
                    break;
                case 4:
                    login();
                    break;
                case 5:
                    registration();
                    break;
                case 0:
                    loop = false;
                    break;
                default:
                    System.out.println("Mazgi bu hatoku.");
            }

        }

    }

    public void showMenu() {
        System.out.println("*** Main Menu ***");
        System.out.println("1. BookList");
        System.out.println("2. Search");
        System.out.println("3. By category");
        System.out.println("4. Login");
        System.out.println("5. Registration");
        System.out.println("0. Exit");
    }

    public void login() {
        System.out.print("Enter login: ");
        String login = ComponentContainer.scannerText.next();

        System.out.print("scannerText password: ");
        String password = ComponentContainer.scannerText.next();
        //
        ComponentContainer.authService.login(login, password);
    }

    public void registration() {
        System.out.print("Enter name: ");
        String name = ComponentContainer.scannerText.next();

        System.out.print("Enter surname: ");
        String surname = ComponentContainer.scannerText.next();

        System.out.print("Enter phone: ");
        String phone = ComponentContainer.scannerText.next();

        System.out.print("Enter login: ");
        String login = ComponentContainer.scannerText.next();

        System.out.print("Enter password: ");
        String password = ComponentContainer.scannerText.next();

        Profile profile = new Profile();
        profile.setName(name.trim());
        profile.setSurname(surname.trim());
        profile.setPhone(phone.trim());

        profile.setLogin(login.trim()); // valish
        profile.setPassword(password.trim()); // 222

        ComponentContainer.authService.registration(profile);
    }

    public void search() {
        System.out.print("Enter query:");
        String query = ComponentContainer.scannerText.next();
        ComponentContainer.bookService.search(query);
    }

    public void byCategory() {
        ComponentContainer.categoryService.list();
        System.out.print("Enter category id:");
        Integer categoryId = ComponentContainer.scannerNumber.nextInt();
        ComponentContainer.bookService.byCategoryId(categoryId);
    }


}
