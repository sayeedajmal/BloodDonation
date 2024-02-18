package com.strong.BloodDonation;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.strong.BloodDonation.Model.Donation;
import com.strong.BloodDonation.Repository.DonationRepo;

@SpringBootTest
public class BloodDonationApplicationTest {

    @Autowired
    private DonationRepo donationRepo;

    @Test
    public void Test() {
        List<Donation> byDonationDate = donationRepo.findByDonationDate(LocalDate.now());
        for (Donation donation : byDonationDate) {
            System.out.println(donation.getDonor().getFirstName());
        }
    }
}
