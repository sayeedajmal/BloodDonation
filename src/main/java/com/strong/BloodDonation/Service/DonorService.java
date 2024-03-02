package com.strong.BloodDonation.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.strong.BloodDonation.Model.Donor;
import com.strong.BloodDonation.Repository.DonorRepo;
import com.strong.BloodDonation.Utils.BloodException;

import lombok.NonNull;

@Service
@Validated
public class DonorService {

    @Autowired
    private DonorRepo donorRepo;

    public void saveDonor(Donor donor) throws BloodException {
        Integer existingDonorId = findByFullName(donor.getFirstName(), donor.getLastName());
        if (existingDonorId == null) {
            donorRepo.save(donor);
        } else {
            throw new BloodException("Donor with the same name already exists");
        }
    }

    public Donor findById(@NonNull Integer id) throws BloodException {
        Donor donor = donorRepo.findById(id).orElse(null);
        if (donor != null) {
            return donor;
        } else
            throw new BloodException("can't find Donor By this Id: " + id, new Throwable());
    }

    public Integer findByFullName(String firstName, String lastName) {
        Donor fullName = donorRepo.findByFirstNameAndLastName(firstName, lastName);
        return (fullName != null) ? fullName.getDonorId() : null;
    }

    public List<Donor> findAll() throws BloodException {
        List<Donor> donor = donorRepo.findAll();
        if (donor.size() != 0) {
            return donor;
        } else
            throw new BloodException("There isn't Any Donor right Now..");
    }

    public void deleteDonor(Integer id) throws BloodException {
        Donor donor = findById(id);
        if (donor != null) {
            try {
                donorRepo.delete(donor);
            } catch (Exception e) {
                throw new BloodException("Error deleting Donor : " + e.getLocalizedMessage());
            }
        } else
            throw new BloodException("can't find Donor By this Id: " + id, new Throwable());

    }

    public void updateDonor(Donor updatedDonor) throws BloodException {
        if (updatedDonor.getDonorId() != null) {
            try {
                donorRepo.save(updatedDonor);
            } catch (Exception e) {
                throw new BloodException("Error updating Donor : " + e.getLocalizedMessage());
            }
        } else
            throw new BloodException("Invalid Donor Id " + updatedDonor.getDonorId(), new Throwable());
    }

}
