package com.strong.BloodDonation.Controller;

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

import com.strong.BloodDonation.Model.Donor;
import com.strong.BloodDonation.Service.DonorService;
import com.strong.BloodDonation.Utils.BloodException;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api/v1/donor")
public class DonorController {

    @Autowired
    private DonorService donorService;

    /* Create Donor */
    @PostMapping("createDonor")
    public ResponseEntity<?> createDonor(Donor donor) throws BloodException {
        donorService.saveDonor(donor);
        return new ResponseEntity<String>("Created Sucessfully", HttpStatus.CREATED);
    }

    /* Show Donor */
    @GetMapping("showDonor")
    public ResponseEntity<?> showDonor() throws BloodException {
        List<Donor> findAll = donorService.findAll();
        return new ResponseEntity<List<Donor>>(findAll, HttpStatus.OK);
    }

    /* FindById Donor */
    @GetMapping("{id}")
    public ResponseEntity<?> showByIdDonor(@PathVariable("id") Integer id) throws BloodException {
        return new ResponseEntity<Donor>(donorService.findById(id), HttpStatus.OK);
    }

    /* Delete Donor */
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDonor(@PathVariable Integer id) throws BloodException {
        donorService.deleteDonor(id);
        return new ResponseEntity<>("Deleted Sucessfully", HttpStatus.NO_CONTENT);
    }

    /* Update Donor */
    @Transactional
    @PatchMapping("updateDonor")
    public ResponseEntity<?> updateProduct(@RequestBody Donor updatedDonor) throws BloodException {
        Donor existingDonor = donorService.findById(updatedDonor.getDonorId());
        if (existingDonor != null) {
            existingDonor.setFirstName(updatedDonor.getFirstName());
            existingDonor.setLastName(updatedDonor.getLastName());
            existingDonor.setAddress(updatedDonor.getAddress());
            existingDonor.setBloodGroup(updatedDonor.getBloodGroup());
            existingDonor.setContactNumber(updatedDonor.getContactNumber());
            existingDonor.setDOB(updatedDonor.getDOB());
            existingDonor.setEmail(updatedDonor.getEmail());
            existingDonor.setLastDonationDate(updatedDonor.getLastDonationDate());
            existingDonor.setMedicalHistoryList(updatedDonor.getMedicalHistoryList());

            donorService.updateDonor(existingDonor);
            return new ResponseEntity<>("Updated Successfully.", HttpStatus.OK);
        }
        return new ResponseEntity<>("Something Wrong", HttpStatus.FORBIDDEN);
    }

}
