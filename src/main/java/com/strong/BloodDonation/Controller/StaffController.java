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

/**
 * StaffController class handles HTTP requests related to staff operations.
 * This controller uses the @RestController annotation to indicate that it's a
 * controller and automatically serializes the returned objects into JSON
 * format.
 */
@RestController
@RequestMapping("/api/v1/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    /**
     * POST endpoint to create a new staff member.
     *
     * @param staff The staff object to be created.
     * @return A response indicating the success or failure of the operation.
     */
    @PostMapping("createStaff")
    public ResponseEntity<?> createStaff(@RequestBody Staff staff) throws BloodException {
        staffService.createStaff(staff);
        return new ResponseEntity<>("Created Successfully", HttpStatus.CREATED);
    }

    /**
     * GET endpoint to retrieve a list of all staff members.
     *
     * @return A list of staff members in JSON format.
     */
    @GetMapping("showStaff")
    public ResponseEntity<?> showStaff() {
        List<Staff> findAll = staffService.findAll();
        return new ResponseEntity<>(findAll, HttpStatus.OK);
    }

    /**
     * GET endpoint to retrieve a specific staff member by ID.
     *
     * @param staffId The unique identifier of the staff member.
     * @return The requested staff member in JSON format.
     */
    @GetMapping("{staffId}")
    public ResponseEntity<Staff> showByIdStaff(@PathVariable("staffId") Integer staffId) throws BloodException {
        Staff byId = staffService.findById(staffId);
        return new ResponseEntity<>(byId, HttpStatus.OK);
    }

    /**
     * DELETE endpoint to delete a staff member by ID.
     *
     * @param staffId The unique identifier of the staff member to be deleted.
     * @return A response indicating the success or failure of the operation.
     */
    @Transactional
    @DeleteMapping("/{staffId}")
    public ResponseEntity<String> deleteStaff(@PathVariable Integer staffId) throws BloodException {
        staffService.deleteStaff(staffId);
        return new ResponseEntity<>("Deleted Successfully", HttpStatus.NO_CONTENT);
    }

    /**
     * PATCH endpoint to update an existing staff member.
     *
     * @param updatedStaff The updated staff member object.
     * @return A response indicating the success or failure of the operation.
     */
    @Transactional
    @PatchMapping("updateStaff")
    public ResponseEntity<String> updateStaff(@RequestBody Staff updatedStaff) throws BloodException {
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

            staffService.updateStaff(existingStaff);
            return new ResponseEntity<>("Updated Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Staff member not found", HttpStatus.NOT_FOUND);
    }
}
