package com.strong.BloodDonation.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.strong.BloodDonation.Model.BloodBank;
import com.strong.BloodDonation.Repository.BloodBankRepo;
import com.strong.BloodDonation.Utils.BloodException;

import lombok.NonNull;

@Service
public class BloodBankService {

    @Autowired
    private BloodBankRepo bloodBankRepo;

    public void saveBlood(BloodBank bloodBank) throws BloodException {
        if (bloodBank == null) {
            throw new BloodException("Fill All Fields Correctly");
        } else
            bloodBankRepo.save(bloodBank);

    }

    public BloodBank findById(@NonNull Integer bloodBankId) throws BloodException {
        BloodBank bloodBank = bloodBankRepo.findById(bloodBankId).orElse(null);
        if (bloodBank != null) {
            return bloodBank;
        } else
            throw new BloodException("can't find BloodBank By this Id: " + bloodBankId, new Throwable());
    }

    public List<BloodBank> findAll() throws BloodException {
        List<BloodBank> bloodBank = bloodBankRepo.findAll();
        if (bloodBank.size() != 0) {
            return bloodBank;
        } else
            throw new BloodException("There isn't Any BloodBank right Now..");
    }

    public void deleteBlood(Integer bloodBankId) throws BloodException {
        BloodBank bloodBank = findById(bloodBankId);
        if (bloodBank != null) {
            try {
                bloodBankRepo.delete(bloodBank);
            } catch (Exception e) {
                throw new BloodException("Error deleting Blood : " + e.getLocalizedMessage());
            }
        } else
            throw new BloodException("can't find Blood By this Id: " + bloodBankId, new Throwable());

    }

    public void updateBlood(BloodBank updatedBlood) throws BloodException {
        if (updatedBlood.getBloodBankId() != null) {
            try {
                bloodBankRepo.save(updatedBlood);
            } catch (Exception e) {
                throw new BloodException("Error updating Blood : " + e.getLocalizedMessage());
            }
        } else
            throw new BloodException("Invalid Blood Id " + updatedBlood.getBloodBankId(), new Throwable());
    }

}