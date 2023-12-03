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
        Donor findByFullName = findByFullName(donor.getFirstName(), donor.getLastName());
        if (findByFullName == null) {
            donorRepo.save(donor);
        } else {
            throw new BloodException("Already Donor Created");
        }
    }

    public Donor findById(Integer id) throws BloodException {
        Donor donor = donorRepo.findAll().get(0);
        if (donor.getDonarId() != null) {
            return donor;
        } else
            throw new BloodException("can't find Donor By this Id: " + donor.getDonarId(), new Throwable());
    }

    public Donor findByFullName(String firstName, String lastName) throws BloodException {
        Donor fullName = donorRepo.findByFirstNameAndLastName(firstName, lastName);
        if (fullName != null) {
            return fullName;
        } else
            throw new BloodException("Cant Find By First and Last Name", new Throwable());
    }

    public List<Donor> findAll() throws BloodException {
        List<Donor> donor = donorRepo.findAll();
        if (!donor.isEmpty()) {
            return donor;
        } else
            throw new BloodException("There isn't Any Donor right Now..");
    }

    public void deleteDonor(Integer id) throws BloodException {
        Donor findById = findById(id);
        if (findById.getDonarId() != null) {
            donorRepo.delete(findById);
        } else
            throw new BloodException("can't find Donor By this Id: " + findById.getDonarId(), new Throwable());

    }

    public Donor updateDonor(Donor updatedDonor) throws BloodException {
        if (updatedDonor.getDonarId() != null) {
            return donorRepo.save(updatedDonor);
        } else
            throw new BloodException("can't find Donor By this Id: " + updatedDonor.getDonarId(), new Throwable());
    }

}
