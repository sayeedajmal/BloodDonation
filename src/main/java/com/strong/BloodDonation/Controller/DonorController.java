package com.strong.BloodDonation.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.strong.BloodDonation.Model.Donor;
import com.strong.BloodDonation.Service.DonorService;

@RestController
@RequestMapping("v1")
public class DonorController {

    @Autowired
    private DonorService donorService;

    @PostMapping("create")
    public ResponseEntity<String> createDonor(Donor donor) {
        donorService.saveDonor(donor);
        return new ResponseEntity<String>("Created Sucessfully", HttpStatus.ACCEPTED);
    }
}
