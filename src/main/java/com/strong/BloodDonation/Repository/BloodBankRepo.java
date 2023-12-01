package com.strong.BloodDonation.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.strong.BloodDonation.Model.BloodBank;

public interface BloodBankRepo extends JpaRepository<BloodBank, Integer> {

}
