package dasturlash.uz.controller;

import dasturlash.uz.container.ComponentContainer;
import dasturlash.uz.dto.Category;
import dasturlash.uz.util.ScannerUtil;

public class CategoryController {

    public void start() {
        boolean loop = true;
        while (loop) {
            showMenu();
            int action = ScannerUtil.getAction();
            switch (action) {
                case 1:
                    ComponentContainer.categoryService.list();
                    break;
                case 2:
                    deleteCategory();
                    break;
                case 3:
                    addCategory();
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
        System.out.println("*** Category ***");
        System.out.println("1. Category list");
        System.out.println("2. Delete category");
        System.out.println("3. Add category");
        System.out.println("0. Exit");
    }

    public void addCategory() {
        System.out.print("Enter name: ");
        String name = ComponentContainer.scannerText.next();
        //
        Category category = new Category();
        category.setName(name);

        ComponentContainer.categoryService.create(category);
    }

    public void deleteCategory() {
        System.out.print("Enter id: ");
        Integer id = ComponentContainer.scannerNumber.nextInt();

        ComponentContainer.categoryService.delete(id);
    }
}
