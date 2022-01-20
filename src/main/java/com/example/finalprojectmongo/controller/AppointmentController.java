package com.example.finalprojectmongo.controller;

import com.example.finalprojectmongo.model.Appointment;
import com.example.finalprojectmongo.service.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    private AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/get_doctor_appointment_list/{userName}")
    public ResponseEntity getDoctorAppointmentList(HttpServletRequest httpServletRequest, @PathVariable String userName) {
        List<Appointment> list = appointmentService.getAllDoctorAppointment(userName);

        HttpStatus httpStatus;

        if(list!=null) {
            httpStatus = HttpStatus.OK;
        } else {
            httpStatus = HttpStatus.NO_CONTENT;
        }

        return new ResponseEntity(list, httpStatus);
    }

    @PostMapping("/save")
    public ResponseEntity saveAppiontment(HttpServletRequest httpServletRequest, @RequestBody Appointment appointment) {
        Appointment foundAppointment = appointmentService.saveAppointment(appointment);

        HttpStatus httpStatus;

        if(foundAppointment!=null) {
            httpStatus = HttpStatus.OK;
        } else {
            httpStatus = HttpStatus.NO_CONTENT;
        }

        return new ResponseEntity(foundAppointment, httpStatus);
    }
}
