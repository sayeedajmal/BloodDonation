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

import com.strong.BloodDonation.Model.Donor;
import com.strong.BloodDonation.Service.DonorService;
import com.strong.BloodDonation.Utils.BloodException;

@RestController
@RequestMapping("/api/v1/donors")
public class DonorController {

    @Autowired
    private DonorService donorService;

    /* Create Donor */
    @PostMapping("createDonor")
    public ResponseEntity<String> createDonor(Donor donor) throws BloodException {
        donorService.saveDonor(donor);
        return new ResponseEntity<String>("Created Sucessfully", HttpStatus.CREATED);
    }

    /* Show Donor */
    @GetMapping("createDonor")
    public ResponseEntity<List<Donor>> showDonor() throws BloodException {
        List<Donor> findAll = donorService.findAll();
        return new ResponseEntity<List<Donor>>(findAll, HttpStatus.OK);
    }

    /* FindById Donor */
    @GetMapping("/{id}")
    public ResponseEntity<Donor> showByIdDonor(@PathVariable Integer id) throws BloodException {
        Donor donor = donorService.findById(id);
        return new ResponseEntity<Donor>(donor, HttpStatus.OK);
    }

    /* Delete Donor */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDonor(@PathVariable Integer id) throws BloodException {
        donorService.deleteDonor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /* Update Donor */
    @PutMapping("/UpdateDonor")
    public String UpdateProduct(@RequestBody Donor updatedDonor,
            @RequestParam("id") Integer id) throws BloodException {
        Donor existingDonor = donorService.findById(id);
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
        }
        return "redirect:/manage/ShowCategories";
    }

}
