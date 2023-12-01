package com.strong.BloodDonation.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.strong.BloodDonation.Model.Donor;
import com.strong.BloodDonation.Repository.DonorRepo;

@Service
public class DonorService {

    @Autowired
    private DonorRepo donorRepo;

    public void saveDonor(Donor donor) {
        donorRepo.save(donor);
    }

    public Donor findById(Integer id) {
        return donorRepo.findById(id).get();
    }

    public void deleteDonor(Integer id) {
        Donor findById = findById(id);
        donorRepo.delete(findById);
    }

    public void updateDonor(Integer id) {
        Donor findById = findById(id);
        donorRepo.save(findById);
    }

}
