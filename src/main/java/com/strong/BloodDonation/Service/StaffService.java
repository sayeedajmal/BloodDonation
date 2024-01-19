package com.strong.BloodDonation.Service;

import com.strong.BloodDonation.Model.Staff;
import com.strong.BloodDonation.Repository.StaffRepo;
import com.strong.BloodDonation.Utils.BloodException;

import lombok.NonNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StaffService {

    @Autowired
    private StaffRepo staffRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void createStaff(Staff staff) throws BloodException {
        List<Staff> byEmail = staffRepo.findByEmail(staff.getEmail());
        if (byEmail.isEmpty()) {
            staff.setPosition(null);
            staff.setPassword(passwordEncoder.encode(staff.getPassword()));
            staff.setEnabled(false);
            staff.setCreatedAt(LocalDateTime.now());
            staffRepo.saveAndFlush(staff);
        } else
            throw new BloodException("Email Already Used");

    }

    public Staff findById(@NonNull Integer staffId) throws BloodException {
        Staff staff = staffRepo.findById(staffId).orElse(null);
        if (staff != null) {
            return staff;
        } else
            throw new BloodException("Staff Is Not Found with " + staffId);
    }

    public List<Staff> findAll() throws BloodException {
        List<Staff> staff = staffRepo.findAll();
        if (!staff.isEmpty()) {
            return staff;
        } else
            throw new BloodException("There's No Staff");
    }

    public void deleteStaff(Integer staffId) throws BloodException {
        Staff staff = findById(staffId);
        if (staff != null) {
            try {
                staffRepo.delete(staff);
            } catch (Exception e) {
                throw new BloodException("Error deleting Donor : " + e.getLocalizedMessage());
            }
        } else
            throw new BloodException("can't find Donor By this Id: " + staffId, new Throwable());
    }

    public void updateStaff(Staff updatedStaff) throws BloodException {
        Staff existingStaff = findById(updatedStaff.getStaffId());
        if (existingStaff != null) {
            existingStaff.setStaffName(updatedStaff.getStaffName());
            existingStaff.setContactNumber(updatedStaff.getContactNumber());
            existingStaff.setEmail(updatedStaff.getEmail());
            existingStaff.setPassword(passwordEncoder.encode(updatedStaff.getPassword()));
            existingStaff.setUpdatedAt(LocalDateTime.now());
        } else
            throw new BloodException("can't find Donor By this Id: ");
    }
}
