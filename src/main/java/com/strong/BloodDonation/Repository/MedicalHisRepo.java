package com.strong.BloodDonation.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.strong.BloodDonation.Model.MedicalHistory;

public interface MedicalHisRepo extends JpaRepository<MedicalHistory, Integer> {

}
