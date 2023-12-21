package com.strong.BloodDonation.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.strong.BloodDonation.Model.MedicalHistory;
import com.strong.BloodDonation.Repository.MedicalHisRepo;
import com.strong.BloodDonation.Utils.BloodException;

@Service
public class MedicalHistoryService {

    @Autowired
    private MedicalHisRepo medicalHisRepo;

    public void saveHistory(MedicalHistory medicalHistory) throws BloodException {
        if (medicalHistory == null || medicalHistory.getDonor().getFirstName() == null
                || medicalHistory.getMedicalCondition() == null) {
            throw new IllegalArgumentException("Invalid medical history data");
        } else
            try {
                medicalHisRepo.save(medicalHistory);
            } catch (Exception e) {
                throw new BloodException("Duplicate Data...");
            }
    }

    public MedicalHistory findById(Integer id) throws BloodException {
        MedicalHistory history = medicalHisRepo.findById(id).orElse(null);
        if (history != null) {
            return history;
        } else
            throw new BloodException("History Not Avaliable With This " + id + " ID");

    }

    public MedicalHistory findByDonorId(Integer donorId) throws BloodException {
        MedicalHistory byDonorId = medicalHisRepo.findByDonorId(donorId);
        if (byDonorId != null) {
            return byDonorId;
        } else
            throw new BloodException("History Not Available With This Donor " + donorId + " ID");
    }

    public List<MedicalHistory> findAll() {
        return medicalHisRepo.findAll();
    }

    public void deleteHistory(Integer historyId) throws BloodException {
        MedicalHistory history = findById(historyId);
        if (history != null) {
            try {
                medicalHisRepo.delete(history);
            } catch (Exception e) {
                throw new BloodException("Error deleting medical history: " + e.getLocalizedMessage());
            }
        } else {
            throw new BloodException("Medical history not found with ID: " + historyId);
        }
    }

    public void updateHistory(MedicalHistory medicalHistory) throws BloodException {
        if (medicalHistory.getHistoryId() != null) {
            if (medicalHistory.getDonor() == null || medicalHistory.getMedicalCondition() == null) {
                throw new BloodException("Invalid medical history data");
            }
            try {
                medicalHisRepo.save(medicalHistory);
            } catch (Exception e) {
                throw new BloodException("Error updating medical history : " + e.getLocalizedMessage());
            }
        } else {
            throw new BloodException("Invalid Medical History ID: " + medicalHistory.getHistoryId());
        }
    }
}
