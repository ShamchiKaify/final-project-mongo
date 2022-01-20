package com.example.finalprojectmongo.service;


import com.example.finalprojectmongo.model.ROLE;
import com.example.finalprojectmongo.model.UserProfile;
import com.example.finalprojectmongo.repository.UserProfileRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.stereotype.Service;
import sun.net.www.content.text.Generic;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserProfileService {
    private UserProfileRepository userProfileRepository;

    public UserProfileService(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    public UserProfile getUserByUserName(String userName, String passwordHash) {
        UserProfile user = this.userProfileRepository.findAllByUserName(userName);

        if(user!=null) {
            if (user.getPassword().equals(passwordHash)) {
//                System.out.println(user);
                return user;
            } else {
//                System.out.println("INVALID");
                return null;
            }
        } else return null;
    }

    public UserProfile getUserByNid(String nid) {
        Optional<UserProfile> byNid = this.userProfileRepository.findByNid(nid);
        return byNid.orElse(null);
    }

    public boolean deleteUserProfile(String userName) {
        Optional<UserProfile> byUserName = userProfileRepository.findByUserName(userName);
        if (byUserName.isPresent()) {
            userProfileRepository.delete(byUserName.get());
            return true;
        }
        return false;
    }

    public UserProfile insertUserProfile(UserProfile userProfile) {
        Optional<UserProfile> byUserName = userProfileRepository.findByUserName(userProfile.getUserName());
        if (!byUserName.isPresent()) {
            return userProfileRepository.save(userProfile);
        }
        return null;
    }

    public UserProfile updateUserProfile(UserProfile userProfile) {
        Optional<UserProfile> byUserName = userProfileRepository.findByUserName(userProfile.getUserName());
        if (byUserName.isPresent()) {
            userProfile.setId(byUserName.get().getId());
            return userProfileRepository.save(userProfile);
        }
        return null;
    }

    public List<UserProfile> getAllByRole(String role) {
        return userProfileRepository.findAllByRoleContaining(role).stream().filter(userProfile -> userProfile.getUserName().charAt(0) >= 'a' && userProfile.getUserName().charAt(0) <= 'z').collect(Collectors.toList());
    }

    public List<UserProfile> getAllUserProfileList() {
        return userProfileRepository.findAll();
    }

    /**
     * Read All From CSV
     */
    public List<UserProfile> insertAllFromCSV() throws IOException, CsvValidationException {
        int limit = 0;
        List<UserProfile> staticList = new ArrayList<>();

        String fileName = "/home/ums/EE/UserProfile.csv";
        /* Name,Email,DOB,NID */

        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            String[] line;
            while((line = reader.readNext())!=null) {
                String[] split = Arrays.toString(line).replaceAll("\\[", "").replaceAll("]", "").split(",");
//                System.out.println(Arrays.toString(split));
                if (!split[0].contains("Name")) {
                    int length = split.length;

                    UserProfile userProfile = new UserProfile();
                    userProfile.setFullName(split[0].trim());
                    userProfile.setUserName(split[1].trim());
                    userProfile.setNid(split[3].trim());
                    userProfile.setPassword("123");
                    userProfile.setDateOfBirth(split[2]);
                    userProfile.setRole(ROLE.PATIENT);
//                    if (++limit <= 1100) {
                        staticList.add(userProfile);
//                    }
                }
            }


        } catch (CsvException e) {
            e.printStackTrace();
        }

        return staticList;
    }

    public List<UserProfile> insertAllAtOnce(List<UserProfile> list) {
         return userProfileRepository.saveAll(list);
    }
}
