package com.strong.BloodDonation.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.strong.BloodDonation.Model.Staff;
import com.strong.BloodDonation.Repository.StaffRepo;
import com.strong.BloodDonation.Utils.BloodException;

@Service
public class StaffService {

    @Autowired
    private StaffRepo staffRepo;

    public void createStaff(Staff staff) throws BloodException {
        if (staff != null) {
            staff.setEnabled(false);
            staff.setCreatedAt(LocalDateTime.now());
            staffRepo.save(staff);
        } else
            throw new BloodException("Fill Correct Form");
    }

    public Staff findById(Integer staffId) throws BloodException {
        Staff staff = staffRepo.findById(staffId).orElse(null);
        if (staff != null) {
            return staff;
        } else
            throw new BloodException("Staff Is Not Found with " + staffId + " ID");
    }

    public List<Staff> findAll() {
        List<Staff> staff = staffRepo.findAll();
        if (staff.size() != 0) {
            return staff;
        } else
            throw new IllegalArgumentException("There's No Staff");
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

    public void updateStaff(Staff updatedStaff) {
        if (updatedStaff.getStaffId() != null) {
            try {
                staffRepo.save(updatedStaff);
            } catch (Exception e) {
                throw new IllegalArgumentException("Error updating Donor : " + e.getLocalizedMessage());
            }
        } else
            throw new IllegalArgumentException("Invalid Donor Id " + updatedStaff.getStaffId());
    }
}
