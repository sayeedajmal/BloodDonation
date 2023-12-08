package com.strong.BloodDonation.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.strong.BloodDonation.Model.Donor;
import com.strong.BloodDonation.Repository.DonorRepo;
import com.strong.BloodDonation.Utils.BloodException;

@Service
public class DonorService {

    @Autowired
    private DonorRepo donorRepo;

    public void saveDonor(@RequestBody Donor donor) throws BloodException {
        Integer findByFullName = findByFullName(donor.getFirstName(), donor.getLastName());
        if (findByFullName == 0) {
            donorRepo.save(donor);
        } else {
            throw new BloodException("Already Donor Created");
        }
    }

    public Donor findById(Integer id) throws BloodException {
        Donor donor = donorRepo.findById(id).orElse(null);
        if (donor != null) {
            return donor;
        } else
            throw new BloodException("can't find Donor By this Id: " + id, new Throwable());
    }

    public Integer findByFullName(String firstName, String lastName) throws BloodException {
        Donor fullName = donorRepo.findByFirstNameAndLastName(firstName, lastName);
        if (fullName != null) {
            return fullName.getDonarId();
        } else
            return 0;
    }

    public List<Donor> findAll() throws BloodException {
        List<Donor> donor = donorRepo.findAll();
        if (!donor.isEmpty()) {
            return donor;
        } else
            throw new BloodException("There isn't Any Donor right Now..");
    }

    public void deleteDonor(Integer id) throws BloodException {
        Donor donor = findById(id);
        if (donor != null) {
            donorRepo.delete(donor);
        } else
            throw new BloodException("can't find Donor By this Id: " + id, new Throwable());

    }

    public Donor updateDonor(Donor updatedDonor) throws BloodException {
        if (updatedDonor.getDonarId() != null) {
            return donorRepo.save(updatedDonor);
        } else
            throw new BloodException("can't find Donor By this Id: " + updatedDonor.getDonarId(), new Throwable());
    }

}
