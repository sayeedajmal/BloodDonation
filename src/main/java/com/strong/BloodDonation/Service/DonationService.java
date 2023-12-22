package com.strong.BloodDonation.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.strong.BloodDonation.Model.Donation;
import com.strong.BloodDonation.Repository.DonationRepo;
import com.strong.BloodDonation.Utils.BloodException;

@Service
public class DonationService {

    @Autowired
    private DonationRepo donationRepo;

    public void saveDonation(Donation donation) throws BloodException {
        if (donation == null) {
            throw new BloodException("Fill All Fields Correctly");
        } else
            donationRepo.save(donation);
    }

    public Donation findById(Integer donationId) throws BloodException {
        Donation donation = donationRepo.findById(donationId).orElse(null);
        if (donation != null) {
            return donation;
        } else
            throw new BloodException("can't find Donation By this Id: " + donationId, new Throwable());
    }

    public List<Donation> findAll() throws BloodException {
        List<Donation> donation = donationRepo.findAll();
        if (donation.size() != 0) {
            return donation;
        } else
            throw new BloodException("There isn't Any Donation right Now..");
    }

    public void deleteDonation(Integer donationId) throws BloodException {
        Donation donation = findById(donationId);
        if (donation != null) {
            try {
                donationRepo.delete(donation);
            } catch (Exception e) {
                throw new BloodException("Error deleting Donation : " + e.getLocalizedMessage());
            }
        } else
            throw new BloodException("can't find Donation By this Id: " + donationId, new Throwable());

    }

    public void updateDonation(Donation donation) throws BloodException {
        if (donation.getDonationId() != null) {
            try {
                donationRepo.save(donation);
            } catch (Exception e) {
                throw new BloodException("Error updating Donation : " + e.getLocalizedMessage());
            }
        } else
            throw new BloodException("Invalid Blood Id " + donation.getDonationId(), new Throwable());
    }
}
