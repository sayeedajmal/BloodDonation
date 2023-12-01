package com.strong.BloodDonation.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.strong.BloodDonation.Model.Donation;

public interface DonationRepo extends JpaRepository<Donation, Integer> {

}
