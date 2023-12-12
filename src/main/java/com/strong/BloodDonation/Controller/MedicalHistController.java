package com.strong.BloodDonation.Controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.strong.BloodDonation.Model.MedicalHistory;
import com.strong.BloodDonation.Service.MedicalHistoryService;
import com.strong.BloodDonation.Utils.BloodException;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api/v1/medicalHistory")
public class MedicalHistController {

    @Autowired
    private MedicalHistoryService medicalHistoryService;

    /* Create History */
    @PostMapping("createHistory")
    public ResponseEntity<?> createHistroy(@RequestBody MedicalHistory medicalHistory) throws BloodException {
        medicalHistoryService.saveHistory(medicalHistory);
        return new ResponseEntity<String>("Created Successfully", HttpStatus.CREATED);
    }

    /* Show History */
    @GetMapping("showHistory")
    public ResponseEntity<?> showHistory() {
        List<MedicalHistory> findAll = medicalHistoryService.findAll();
        if (findAll.isEmpty()) {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NOT_FOUND);
        } else
            return new ResponseEntity<List<MedicalHistory>>(findAll, HttpStatus.OK);
    }

    /* FindByID History */
    @GetMapping("{id}")
    public ResponseEntity<?> showByIdHistroy(Integer historyId) {
        MedicalHistory history = medicalHistoryService.findById(historyId);
        return new ResponseEntity<MedicalHistory>(history, HttpStatus.OK);
    }

    /* Delete History */
    @Transactional
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteHistroy(Integer historyId) throws BloodException {
        medicalHistoryService.deleteHistory(historyId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Transactional
    @PatchMapping("updateHistory")
    public ResponseEntity<?> updateHistory(@RequestBody MedicalHistory history, @RequestParam("id") Integer id) throws BloodException {
        MedicalHistory Existhistory = medicalHistoryService.findById(id);
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