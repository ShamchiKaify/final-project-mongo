package com.example.finalprojectmongo.controller;

import com.example.finalprojectmongo.model.UserProfile;
import com.example.finalprojectmongo.service.UserProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/user_profile")
public class UserProfileController {
    private UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping("/verify_identity")
    public ResponseEntity verifyCredential(HttpServletRequest httpServletRequest, @RequestParam("userName") String userName, @RequestParam("password") String passwordHash) {
        UserProfile userProfile = this.userProfileService.getUserByUserName(userName, passwordHash);
        System.out.println("UserName: " + userName);
        System.out.println("Password: " + passwordHash);
        System.out.println("Found Profile: " + userProfile);

        HttpStatus httpStatus;

        if(userProfile!=null) {
            httpStatus = HttpStatus.OK;
        } else {
            httpStatus = HttpStatus.NO_CONTENT;
        }

        return new ResponseEntity(userProfile, httpStatus);
    }

    @GetMapping("/get_patient_by_nid")
    public ResponseEntity getPatientByNid(HttpServletRequest httpServletRequest, @RequestParam("nid") String nid) {
        UserProfile userByNid = userProfileService.getUserByNid(nid);
        HttpStatus httpStatus;

        if(userByNid!=null) {
            httpStatus = HttpStatus.OK;
        } else {
            httpStatus = HttpStatus.NO_CONTENT;
        }

        return new ResponseEntity(userByNid, httpStatus);
    }

    @GetMapping("/get_all_by_role/{role}")
    public ResponseEntity getAllDoctorList(HttpServletRequest httpServletRequest, @PathVariable String role) {
        List<UserProfile> allByRole = userProfileService.getAllByRole(role);

        HttpStatus httpStatus;

        if(allByRole!=null) {
            httpStatus = HttpStatus.OK;
        } else {
            httpStatus = HttpStatus.NO_CONTENT;
        }

        return new ResponseEntity(allByRole, httpStatus);
    }
}
