package dasturlash.uz.controller;

import dasturlash.uz.container.ComponentContainer;
import dasturlash.uz.dto.Profile;
import dasturlash.uz.enums.ProfileRole;
import dasturlash.uz.util.ScannerUtil;

public class ProfileController {
    public void start() {
        boolean loop = true;
        while (loop) {
            showMenu();
            int action = ScannerUtil.getAction();
            switch (action) {
                case 1:
                    ComponentContainer.profileService.list();
                    break;
                case 2:
                    search();
                    break;
                case 3:
                    changeStatus();
                    break;
                case 4:
                    addProfile();
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
        System.out.println("*** Profile ***");
        System.out.println("1. Profile list");
        System.out.println("2. Search profile");
        System.out.println("3. Change profile status");
        System.out.println("4. Profile add");
        System.out.println("0. Exits");
    }


    public void addProfile() {
        System.out.print("Enter name: ");
        String name = ComponentContainer.scannerText.next();

        System.out.print("Enter surname: ");
        String surname = ComponentContainer.scannerText.next();

        System.out.print("Enter login: ");
        String login = ComponentContainer.scannerText.next();

        System.out.print("Enter password: ");
        String password = ComponentContainer.scannerText.next();

        System.out.print("Enter phone (9989x1234567): ");
        String phone = ComponentContainer.scannerText.next();

        System.out.print("Enter role (STAFF,ADMIN) : ");
        String role = ComponentContainer.scannerText.next();
        ProfileRole profileRole;
        try {
            profileRole = ProfileRole.valueOf(role);
        } catch (RuntimeException e) {
            System.out.println("Mazgi to'g'ri qiymat kiriting. Possible values (STAFF,ADMIN) ");
            return;
        }

        Profile profile = new Profile();
        profile.setName(name.trim());
        profile.setSurname(surname.trim());
        profile.setLogin(login.trim()); // valish
        profile.setPassword(password.trim()); // 222
        profile.setPhone(phone.trim());
        profile.setRole(profileRole);
        ComponentContainer.profileService.addProfile(profile);
    }

    public void search() {
        System.out.print("Enter query: ");
        String query = ComponentContainer.scannerText.next();
        ComponentContainer.profileService.search(query, ProfileRole.ADMIN, ProfileRole.STAFF);
    }

    private void changeStatus() {
        System.out.print("Enter Id: ");
        Integer id = ComponentContainer.scannerNumber.nextInt();
        ComponentContainer.profileService.changeStatus(id);
    }

}
