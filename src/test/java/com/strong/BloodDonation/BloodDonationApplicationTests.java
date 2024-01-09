package com.strong.BloodDonation;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.strong.BloodDonation.Model.Staff;
import com.strong.BloodDonation.Repository.StaffRepo;

@SpringBootTest
class BloodDonationApplicationTests {

    @Autowired
    StaffRepo staffRepo;

    @Test
    public void test() {
        List<Staff> byEmail = staffRepo.findByEmail("sayeed@gmail.com");
        System.out.println("<<<<<<<<<<<<<<<<" + byEmail.getFirst().toString());
    }
}
