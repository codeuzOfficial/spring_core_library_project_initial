package dasturlash.uz.service;

import dasturlash.uz.container.ComponentContainer;
import dasturlash.uz.dto.Profile;
import dasturlash.uz.repository.ProfileRepository;
import dasturlash.uz.controller.AdminController;
import dasturlash.uz.controller.StaffController;
import dasturlash.uz.controller.StudentController;
import dasturlash.uz.enums.ProfileRole;
import dasturlash.uz.enums.ProfileStatus;
import dasturlash.uz.util.MD5Util;
import dasturlash.uz.util.ProfileValidationUtil;

import java.time.LocalDateTime;

public class AuthService {

    public void login(String login, String password) {
        Profile profile = ComponentContainer.profileRepository.getByLogin(login);
        if (profile == null) {
            System.out.println("Login or Password wrong.");
            return;
        }
        String md5Hash = MD5Util.encode(password);
        if (!md5Hash.equals(profile.getPassword())) {
            System.out.println("Login or Password wrong.");
            return;
        }
        if (!profile.getStatus().equals(ProfileStatus.ACTIVE)) {
            System.out.println("Wrong status mazgi.");
            return;
        }
        System.out.println("** Welcome to library project **");
        // redirect
        ComponentContainer.currentProfile = profile;
        if (profile.getRole().equals(ProfileRole.STUDENT)) {
            ComponentContainer.studentController.start();
        } else if (profile.getRole().equals(ProfileRole.ADMIN)) {
            ComponentContainer.adminController.start();
        } else if (profile.getRole().equals(ProfileRole.STAFF)) {
            ComponentContainer.staffController.start();
        }
    }

    public void registration(Profile profile) {
        // check
        if (!ProfileValidationUtil.isValid(profile)) {
            return;
        }
        // check login
        Profile existProfile = ComponentContainer.profileRepository.getByLogin(profile.getLogin());
        if (existProfile != null) {
            System.out.println("Login exists. Please choose other login. Mazgi");
            return;
        }
        // save
        profile.setCreatedDate(LocalDateTime.now());
        profile.setRole(ProfileRole.STUDENT);
        profile.setStatus(ProfileStatus.ACTIVE);
        profile.setPassword(MD5Util.encode(profile.getPassword()));
        int effectedRow = ComponentContainer.profileRepository.create(profile);
        if (effectedRow == 1) {
            System.out.println("Registration completed.");
        }
    }

}
