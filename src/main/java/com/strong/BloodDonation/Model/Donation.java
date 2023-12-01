package com.strong.BloodDonation.Model;

import java.time.LocalDate;

import com.strong.BloodDonation.Utils.DonationStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer donationId;

    @ManyToOne
    @JoinColumn(name = "donarId", nullable = false)
    private Donor donor;

    @Column(nullable = false)
    private LocalDate donationDate;

    @Column(nullable = false)
    private String donationLocation;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DonationStatus donationStatus;

    @Column(nullable = false)
    private String bloodType;

    @Column(nullable = false)
    private Integer quantity;
}
