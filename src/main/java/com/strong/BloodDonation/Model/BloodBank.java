package com.strong.BloodDonation.Model;

import java.util.List;

import com.strong.BloodDonation.Utils.BloodType;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ElementCollection(targetClass = BloodType.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "blood_bank_available_groups", joinColumns = @JoinColumn(name = "bloodBankId"))
    @Column(name = "available_blood_group")
    private List<BloodType> availableBloodGroups;

    /*
     * - The availableBloodGroups field is a Set of BloodType enums.
     * - The @ElementCollection annotation is used to indicate that
     * availableBloodGroups is a collection of simple types (enums in this case).
     * - The @Enumerated(EnumType.STRING) annotation specifies that the enums should
     * be stored as strings in the database.
     * - The @CollectionTable annotation is used to specify the name of the table that
     * will store the blood groups, and the @Column annotation specifies the name of
     * the column.
     */
}
