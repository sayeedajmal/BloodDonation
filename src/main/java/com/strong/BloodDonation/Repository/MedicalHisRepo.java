package com.strong.BloodDonation.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.strong.BloodDonation.Model.MedicalHistory;

public interface MedicalHisRepo extends JpaRepository<MedicalHistory, Integer> {

    @Query("SELECT d FROM MedicalHistory d WHERE d.donor.donorId= :donorId")
    MedicalHistory findByDonorId(Integer donorId);

}
