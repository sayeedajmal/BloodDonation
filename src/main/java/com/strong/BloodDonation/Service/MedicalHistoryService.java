package com.strong.BloodDonation.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.strong.BloodDonation.Model.MedicalHistory;
import com.strong.BloodDonation.Repository.MedicalHisRepo;

@Service
public class MedicalHistoryService {

    @Autowired
    private MedicalHisRepo medicalHisRepo;

    public void saveHistory(MedicalHistory medicalHistory) {
        medicalHisRepo.save(medicalHistory);
    }

    public MedicalHistory findById(Integer id) {
        return medicalHisRepo.findById(id).orElse(null);
    }

    public List<MedicalHistory> findALl() {
        return medicalHisRepo.findAll();
    }

    public void deleteHistory(Integer histroyId) {
        MedicalHistory history = findById(histroyId);
        medicalHisRepo.delete(history);
    }

    public void updateHistory(MedicalHistory medicalHistory) {
        if (medicalHistory.getHistoryId() != null) {
            medicalHisRepo.save(medicalHistory);
        }
    }
}
