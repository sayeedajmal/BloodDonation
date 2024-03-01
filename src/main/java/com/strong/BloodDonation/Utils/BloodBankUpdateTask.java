package com.strong.BloodDonation.Utils;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.strong.BloodDonation.Controller.BloodBankController;
import com.strong.BloodDonation.Model.BloodBank;
import com.strong.BloodDonation.Model.Donation;
import com.strong.BloodDonation.Repository.DonationRepo;

@Component
public class BloodBankUpdateTask {

    @Autowired
    private DonationRepo donationRepo;

    @Autowired
    private BloodBankController bankController;

    @Value("${bloodDonation.organisation.Id}")
    private Integer OrganisationId;

    @Value("${bloodDonation.organisation.name}")
    private String OrganisationName;

    @Value("${bloodDonation.organisation.Location}")
    private String OrganisationLocation;

    @Value("${bloodDonation.organisation.PhoneNumber}")
    private String OrganisationPhone;

    @Value("${bloodDonation.organisation.Email}")
    private String OrganisationEmail;

    Map<BloodType, Double> list = new HashMap<>();

    @Scheduled(cron = "0 55 16 * * *")
    public void UpdateBank() throws BloodException {
        List<Donation> byDonationDate = donationRepo.findByDonationDate(LocalDate.now());

        for (Donation donation : byDonationDate) {
            list.put(donation.getDonor().getBloodType(), donation.getQuantity());
            BloodBank bloodBank = new BloodBank();
            bloodBank.setBloodBankId(OrganisationId);
            bloodBank.setAvailableBloodGroups(list);
            bloodBank.setBloodBankName(OrganisationName);
            bloodBank.setLocation(OrganisationLocation);
            bloodBank.setContactNumber(OrganisationPhone);
            bloodBank.setEmail(OrganisationEmail);

            bankController.createBank(bloodBank);
        }
    }
}
