package com.strong.BloodDonation.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.strong.BloodDonation.Model.Staff;

public interface StaffRepo extends JpaRepository<Staff, Integer> {

    Staff findByStaffName(String staffName);

    Optional<Staff> findByEmail(String email);
}
