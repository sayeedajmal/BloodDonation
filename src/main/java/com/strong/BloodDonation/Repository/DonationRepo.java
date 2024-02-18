package com.strong.BloodDonation.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.strong.BloodDonation.Model.Donation;

public interface DonationRepo extends JpaRepository<Donation, Integer> {

    @Query("SELECT d FROM Donation d WHERE d.donationDate= :donationDate")
    public List<Donation> findByDonationDate(@Param("donationDate") LocalDate donationDate);

}
