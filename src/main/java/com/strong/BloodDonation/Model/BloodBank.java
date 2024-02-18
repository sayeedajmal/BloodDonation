package com.strong.BloodDonation.Model;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;

import com.strong.BloodDonation.Utils.BloodType;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.MapKeyEnumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class BloodBank {

    @Id
    @Value("${bloodDonation.organisation.Id}")
    private Integer bloodBankId;

    @Column(nullable = false)
    private String bloodBankName;

    @Column(nullable = false)
    private String location;

    @Pattern(regexp = "\\d{10}", message = "Contact number must be 10 digits")
    @Column(nullable = false)
    private String contactNumber;

    @Column(nullable = false)
    @Email
    private String email;

    @Column(nullable = false)
    private LocalDate donationDate;

    @ElementCollection
    @CollectionTable(name = "blood_bank_available_blood", joinColumns = @JoinColumn(name = "bloodBankId"))
    @MapKeyEnumerated(EnumType.STRING)
    @MapKeyColumn(name = "blood_type")
    @Column(name = "quantity")
    private Map<BloodType, Double> availableBloodGroups;
}
