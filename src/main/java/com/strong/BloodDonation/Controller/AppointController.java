package com.strong.BloodDonation.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.strong.BloodDonation.Model.Appointment;
import com.strong.BloodDonation.Service.AppointService;
import com.strong.BloodDonation.Utils.BloodException;

@RestController
@RequestMapping("/api/v1/appointments")
public class AppointController {

    @Autowired
    private AppointService appointService;

    /* Create Appointment */
    @PostMapping("createAppointment")
    public ResponseEntity<String> createAppointment(@RequestBody Appointment appointment) {
        appointService.saveAppointment(appointment);
        return new ResponseEntity<String>("Created Successfully", HttpStatus.CREATED);
    }

    /* Show Appointment */
    @GetMapping("showAppointment")
    public ResponseEntity<List<Appointment>> showAppointment() throws BloodException {
        List<Appointment> findAll = appointService.findAll();
        return new ResponseEntity<>(findAll, HttpStatus.OK);
    }

    /* FindById Appointment */
    @GetMapping("{id}")
    public ResponseEntity<Appointment> showByIdAppointment(@PathVariable Integer id) throws BloodException {
        Appointment appointment = appointService.findById(id);
        return new ResponseEntity<Appointment>(appointment, HttpStatus.OK);
    }

    /* Delete Appointment */
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Integer id) throws BloodException {
        appointService.deleteAppointment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /* Update Appointment */
    @PutMapping("updateAppointment")
    public ResponseEntity<String> updateProduct(@RequestBody Appointment updatedAppointment,
            @RequestParam("id") Integer id) throws BloodException {
        Appointment existAppoint = appointService.findById(id);
        if (existAppoint != null) {
            existAppoint.setAppointmentDate(updatedAppointment.getAppointmentDate());
            existAppoint.setAppointmentTime(updatedAppointment.getAppointmentTime());
            existAppoint.setDonor(updatedAppointment.getDonor());
            existAppoint.setLocation(updatedAppointment.getLocation());
            existAppoint.setStatus(updatedAppointment.getStatus());

            appointService.updateAppointment(existAppoint);
        }
        return new ResponseEntity<>("Sucessfully Updated", HttpStatus.ACCEPTED);
    }

}
