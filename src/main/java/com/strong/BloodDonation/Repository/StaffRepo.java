package com.strong.BloodDonation.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.strong.BloodDonation.Model.Staff;

import java.util.List;

public interface StaffRepo extends JpaRepository<Staff, Integer> {

    Staff findByStaffName(String staffName);

    List<Staff> findByEmail(String email);
}
