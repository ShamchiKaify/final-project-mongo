package com.example.finalprojectmongo.service;

import com.example.finalprojectmongo.model.ROLE;
import com.example.finalprojectmongo.model.UserProfile;
import com.example.finalprojectmongo.repository.UserProfileRepository;
import com.opencsv.exceptions.CsvValidationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserProfileServiceTest {
    @Autowired
    UserProfileService userProfileService;
    @Autowired
    UserProfileRepository userProfileRepository;

    @Test
    public void testInsertUserProfile() {
        UserProfile userProfile = new UserProfile();
        userProfile.setUserName("kaify");
        userProfile.setPassword("123");
        userProfile.setNid("44448");
        userProfile.setRole(ROLE.DOCTOR);


        UserProfile profile = userProfileService.insertUserProfile(userProfile);
        System.out.println(profile);
    }

    @Test
    public void testDeleteUserProfile() {
        String userName = "Email";
        boolean b = userProfileService.deleteUserProfile(userName);
        System.out.println(b ? "Done" : "No User Found");
    }

    @Test
    public void testUpdateUserProfile() {
        UserProfile userProfile = new UserProfile();
        userProfile.setUserName("shamin.asfaq");
        userProfile.setPassword("123");
        userProfile.setNid("12345");
        userProfile.setRole(ROLE.DOCTOR);

        UserProfile profile = userProfileService.updateUserProfile(userProfile);
        System.out.println(profile);
    }

    @Test
    public void testGetAllUserList() {
        long startTime = System.currentTimeMillis();

        List<UserProfile> list = userProfileService.getAllUserProfileList();
        System.out.println(list.size());
        list.forEach(item -> {
            System.out.println(item.getUserName() + "," + item.getNid());
        });

        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("Elapsed Time: " + estimatedTime + " ms");
    }

    @Test
    public void testInsertAll() throws CsvValidationException, IOException {
        long startTime = System.currentTimeMillis();

        List<UserProfile> all = userProfileService.insertAllFromCSV();
        System.out.println(all.size());

        userProfileService.insertAllAtOnce(all);

        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("Elapsed Time: " + estimatedTime + " ms");
    }

    @Test
    public void testFullNameStartingWith() {
        long startTime = System.currentTimeMillis();

        List<UserProfile> list = userProfileRepository.findAllByFullNameStartingWith("Shamin");
        System.out.println(list.size());

        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("Elapsed Time: " + estimatedTime + " ms");
    }

    @Test
    public void testDeleteConditionally() {
        long startTime = System.currentTimeMillis();

        List<UserProfile> list = userProfileRepository.findAllByFullNameStartingWith("M");
        System.out.println(list.size());

        userProfileRepository.deleteAll(list);

        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("Elapsed Time: " + estimatedTime + " ms");
    }

    @Test
    public void testGetAllByRole() {
        String role = "DOCTOR";
        List<UserProfile> allByRole = userProfileService.getAllByRole(role);
        System.out.println(allByRole.size());

        allByRole.forEach(System.out::println);
    }

    @Test
    public void testDeleteAll() {
        long startTime = System.currentTimeMillis();

        userProfileRepository.deleteAll();

        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("Elapsed Time: " + estimatedTime + " ms");
    }
}