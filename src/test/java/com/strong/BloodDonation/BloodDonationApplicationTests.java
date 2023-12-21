package com.strong.BloodDonation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.strong.BloodDonation.Model.MedicalHistory;
import com.strong.BloodDonation.Repository.MedicalHisRepo;

@SpringBootTest
class BloodDonationApplicationTests {

	@Autowired
	MedicalHisRepo medicalHisRepo;

	@Test
	public void findByDonorId() {
		MedicalHistory byDonorId = medicalHisRepo.findByDonorId(102);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>."+byDonorId.getAllergies());
	}
}
