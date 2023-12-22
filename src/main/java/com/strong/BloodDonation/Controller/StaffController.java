package com.strong.BloodDonation.Controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.strong.BloodDonation.Model.Staff;
import com.strong.BloodDonation.Service.StaffService;
import com.strong.BloodDonation.Utils.BloodException;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api/v1/Staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    /* Create Staff */
    @PostMapping("createStaff")
    public ResponseEntity<?> createStaff(Staff staff) throws BloodException {
        staffService.createStaff(staff);
        return new ResponseEntity<String>("Created Sucessfully", HttpStatus.CREATED);
    }

    /* Show Staff */
    @GetMapping("showStaff")
    public ResponseEntity<?> showStaff() {
        List<Staff> findAll = staffService.findAll();
        return new ResponseEntity<List<Staff>>(findAll, HttpStatus.OK);
    }

    /* FindById Staff */
    @GetMapping("{staffId}")
    public ResponseEntity<?> showByIdStaff(@PathVariable("staffId") Integer staffId) throws BloodException {
        Staff byId = staffService.findById(staffId);
        return new ResponseEntity<Staff>(byId, HttpStatus.OK);
    }

    /* Delete Staff */
    @Transactional
    @DeleteMapping("/{staffId}")
    public ResponseEntity<?> deleteStaff(@PathVariable Integer staffId) throws BloodException {
        staffService.deleteStaff(staffId);
        return new ResponseEntity<>("Deleted Sucessfully", HttpStatus.NO_CONTENT);
    }

    /* Update Staff */
    @Transactional
    @PatchMapping("updateStaff")
    public ResponseEntity<?> updateStaff(@RequestBody Staff updatedStaff) throws BloodException {
        Staff existingStaff = staffService.findById(updatedStaff.getStaffId());
        if (existingStaff != null) {
            existingStaff.setFirstName(updatedStaff.getFirstName());
            existingStaff.setLastName(updatedStaff.getLastName());
            existingStaff.setContactNumber(updatedStaff.getContactNumber());
            existingStaff.setEmail(updatedStaff.getEmail());
            existingStaff.setEnabled(updatedStaff.isEnabled());
            existingStaff.setPassword(updatedStaff.getPassword());
            existingStaff.setPosition(updatedStaff.getPosition());
            existingStaff.setUpdatedAt(LocalDateTime.now());

            staffService.updateStaff(updatedStaff);
            return new ResponseEntity<>("Updated Successfully.", HttpStatus.OK);
        }
        return new ResponseEntity<>("Something Wrong", HttpStatus.FORBIDDEN);
    }
}
