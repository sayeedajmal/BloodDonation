package com.strong.BloodDonation.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.strong.BloodDonation.Model.Staff;
import com.strong.BloodDonation.Repository.StaffRepo;
import com.strong.BloodDonation.Utils.BloodException;

import lombok.NonNull;

@Service
public class StaffService {

    @Autowired
    private StaffRepo staffRepo;

    public void createStaff(Staff staff) throws BloodException {
        staff.setPassword(staff.getPassword());
        staff.setEnabled(false);
        staff.setUpdatedAt(null);
        staffRepo.saveAndFlush(staff);
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
            existingStaff.setPassword(updatedStaff.getPassword());
            existingStaff.setUpdatedAt(LocalDateTime.now());
        } else
            throw new BloodException("can't find Donor By this Id: ");
    }
}
