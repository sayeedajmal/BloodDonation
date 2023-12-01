package com.strong.BloodDonation.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.strong.BloodDonation.Model.Donor;

public interface DonorRepo extends JpaRepository<Donor, Integer> {

}