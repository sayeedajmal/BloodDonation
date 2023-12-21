package com.strong.BloodDonation.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.strong.BloodDonation.Model.Donor;
import com.strong.BloodDonation.Model.MedicalHistory;
import com.strong.BloodDonation.Service.DonorService;
import com.strong.BloodDonation.Service.MedicalHistoryService;
import com.strong.BloodDonation.Utils.BloodException;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api/v1/medicalHistory")
public class MedicalHistController {

    @Autowired
    private MedicalHistoryService medicalHistoryService;
    @Autowired
    private DonorService donorService;

    /* Create History */
    @PostMapping("createHistory")
    public ResponseEntity<String> createHistory(@ModelAttribute MedicalHistory medicalHistory,
            @RequestParam("donorId") Integer donorId) throws BloodException {
        Donor donor = donorService.findById(donorId);
        if (donor != null) {
            medicalHistory.setDonor(donor);
            medicalHistoryService.saveHistory(medicalHistory);
            return new ResponseEntity<>("Created Successfully", HttpStatus.CREATED);
        } else {
            throw new BloodException("Can't Find Donor with ID: " + donorId);
        }
    }

    /* Show History */
    @GetMapping("showHistory")
    public ResponseEntity<?> showHistory() {
        List<MedicalHistory> findAll = medicalHistoryService.findAll();
        if (findAll.isEmpty()) {
            return new ResponseEntity<>("Empty History", HttpStatus.NOT_FOUND);
        } else
            return new ResponseEntity<List<MedicalHistory>>(findAll, HttpStatus.OK);
    }

    /* FindByID History */
    @GetMapping("{historyId}")
    public ResponseEntity<?> showByIdHistroy(@PathVariable("historyId") Integer historyId) throws BloodException {
        MedicalHistory history = medicalHistoryService.findById(historyId);
        return new ResponseEntity<MedicalHistory>(history, HttpStatus.OK);
    }

    @GetMapping("findByDonor/{donorId}")
    public ResponseEntity<?> findByDonorId(@PathVariable("donorId") Integer donorId) throws BloodException {
        MedicalHistory history = medicalHistoryService.findByDonorId(donorId);
        return new ResponseEntity<MedicalHistory>(history, HttpStatus.OK);
    }

    /* Delete History */
    @Transactional
    @DeleteMapping("{historyId}")
    public ResponseEntity<?> deleteHistroy(@PathVariable("historyId") Integer historyId) throws BloodException {
        medicalHistoryService.deleteHistory(historyId);
        return new ResponseEntity<>("Deleted", HttpStatus.NOT_FOUND);
    }

    @Transactional
    @PatchMapping("updateHistory")
    public ResponseEntity<?> updateHistory(@RequestBody MedicalHistory history)
            throws BloodException {
        MedicalHistory Existhistory = medicalHistoryService.findById(history.getHistoryId());
        if (Existhistory != null) {
            Existhistory.setAllergies(history.getAllergies());
            Existhistory.setMedicalCondition(history.getMedicalCondition());
            Existhistory.setDonor(history.getDonor());
            Existhistory.setMedications(history.getMedications());

            medicalHistoryService.updateHistory(Existhistory);
        }
        return new ResponseEntity<>("Successfully Updated", HttpStatus.ACCEPTED);
    }

}