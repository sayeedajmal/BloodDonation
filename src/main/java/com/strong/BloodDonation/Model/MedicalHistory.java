package com.strong.BloodDonation.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class MedicalHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer historyId;
    
    @ManyToOne
    @JoinColumn(name = "donarId", nullable = false)
    private Donor donor;

    @Size(max = 255)
    @Column(nullable = false, columnDefinition = "TEXT")
    private String medicalCondition;

    @Size(max = 255)
    @Column(nullable = false, columnDefinition = "TEXT")
    private String medications;

    @Size(max = 255)
    @Column(nullable = false, columnDefinition = "TEXT")
    private String allergies;
}
