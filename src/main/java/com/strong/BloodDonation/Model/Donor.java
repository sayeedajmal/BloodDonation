package com.strong.BloodDonation.Model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.strong.BloodDonation.Utils.BloodType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Donor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer donorId;

    @OneToMany(mappedBy = "donor", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<MedicalHistory> medicalHistoryList = new ArrayList<>();

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private Date DOB;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BloodType BloodType;

    @Column(nullable = false)
    private String contactNumber;

    @Column(nullable = false)
    @Email
    private String email;

    @Column(nullable = false)
    private String address;

    @Column(nullable = true)
    private Date lastDonationDate;
}
