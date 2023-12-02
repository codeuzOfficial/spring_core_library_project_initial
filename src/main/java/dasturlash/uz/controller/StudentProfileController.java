package dasturlash.uz.controller;

import dasturlash.uz.container.ComponentContainer;
import dasturlash.uz.enums.ProfileRole;
import dasturlash.uz.enums.ProfileStatus;
import dasturlash.uz.util.ScannerUtil;

public class StudentProfileController {
    public void start() {
        boolean loop = true;
        while (loop) {
            showMenu();
            int action = ScannerUtil.getAction();
            switch (action) {
                case 1:
                    ComponentContainer.profileService.studentList();
                    break;
                case 2:
                    search();
                    break;
                case 3:
                    blockStudent();
                    break;
                case 4:
                    activeStudent();
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
        System.out.println("*** Student ***");
        System.out.println("1. Student list");
        System.out.println("2. Search student");
        System.out.println("3. Block student");
        System.out.println("3. Activate student");
        System.out.println("4. Student by book");
        System.out.println("0. Exits");
    }


    public void search() {
        System.out.print("Enter query: ");
        String query = ComponentContainer.scannerText.next();
        ComponentContainer.profileService.search(query, ProfileRole.STUDENT);
    }

    public void blockStudent() {
        System.out.print("Enter id: ");
        Integer id = ComponentContainer.scannerNumber.nextInt();
        ComponentContainer.profileService.changeStudentStatus(id, ProfileStatus.BLOCK);
    }

    public void activeStudent() {
        System.out.print("Enter id: ");
        Integer id = ComponentContainer.scannerNumber.nextInt();
        ComponentContainer.profileService.changeStudentStatus(id, ProfileStatus.ACTIVE);
    }

}
