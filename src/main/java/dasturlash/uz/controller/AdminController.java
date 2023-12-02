package dasturlash.uz.controller;


import dasturlash.uz.container.ComponentContainer;
import dasturlash.uz.util.ScannerUtil;

public class AdminController {
    public void start() {
        boolean loop = true;
        while (loop) {
            showMenu();
            int action = ScannerUtil.getAction();
            switch (action) {
                case 1:
                    ComponentContainer.bookController.start();
                    break;
                case 2:
                    ComponentContainer.categoryController.start();
                    break;
                case 3:
                    ComponentContainer.studentProfileController.start();
                    break;
                case 4:
                    ComponentContainer.profileController.start();
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
        System.out.println("*** Menu ***");
        System.out.println("1. Book");
        System.out.println("2. Category");
        System.out.println("3. Student");
        System.out.println("4. Profile");
        System.out.println("0. Exit");
    }
}
