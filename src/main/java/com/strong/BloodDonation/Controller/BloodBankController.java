package com.strong.BloodDonation.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.strong.BloodDonation.Model.BloodBank;
import com.strong.BloodDonation.Service.BloodBankService;
import com.strong.BloodDonation.Utils.BloodException;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api/v1/bloodBank")
public class BloodBankController {
    @Autowired
    private BloodBankService bloodBankService;

    /**
     *
     * POST endpoint to create a new BloodBank.
     *
     * @param bloodBank   The BloodBank object to be created.
     * @param bloodBankId The unique identifier of the bloodBank associated with the
     *                    BloodBank.
     * @return A response indicating the success or failure of the operation.
     * @throws BloodException
     */
    public ResponseEntity<String> createBank(BloodBank bloodBank) throws BloodException {
        bloodBankService.saveBlood(bloodBank);
        return new ResponseEntity<>("Created Successfully", HttpStatus.CREATED);
    }

    /**
     * GET endpoint to retrieve a list of all BloodBanks.
     *
     * @return A list of BloodBanks in JSON format.
     */
    @GetMapping("showBank")
    public ResponseEntity<List<BloodBank>> showBloodBank() throws BloodException {
        List<BloodBank> findAll = bloodBankService.findAll();
        return new ResponseEntity<>(findAll, HttpStatus.OK);
    }

    /**
     * GET endpoint to retrieve a specific BloodBank by ID.
     *
     * @param bloodBankId The unique identifier of the BloodBank.
     * @return The requested BloodBank in JSON format.
     */
    @GetMapping("{bloodBankId}")
    public ResponseEntity<BloodBank> showByIdBloodBank(@PathVariable("bloodBankId") Integer bloodBankId)
            throws BloodException {
        BloodBank BloodBank = bloodBankService.findById(bloodBankId);
        return new ResponseEntity<>(BloodBank, HttpStatus.OK);
    }

    /**
     * PATCH endpoint to update an existing BloodBank.
     *
     * @param updatedBloodBank The updated BloodBank object.
     * @param bloodBankId      The unique identifier of the BloodBank to be
     *                         updated.
     * @return A response indicating the success or failure of the operation.
     */
    @Transactional
    @PatchMapping("updateBloodBank")
    public ResponseEntity<String> updatebloodBank(@RequestBody BloodBank updatedBloodBank,
            @RequestParam("bloodBankId") Integer bloodBankId) throws BloodException {
        BloodBank existBank = bloodBankService.findById(bloodBankId);
        if (existBank != null) {
            existBank.setAvailableBloodGroups(updatedBloodBank.getAvailableBloodGroups());
            existBank.setBloodBankName(updatedBloodBank.getBloodBankName());
            existBank.setContactNumber(updatedBloodBank.getContactNumber());
            existBank.setEmail(updatedBloodBank.getEmail());
            existBank.setLocation(updatedBloodBank.getLocation());
            bloodBankService.updateBlood(existBank);
            return new ResponseEntity<>("Updated Successfully", HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("BloodBank not found", HttpStatus.NOT_FOUND);
    }
}
