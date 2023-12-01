package com.strong.BloodDonation.Model;

import java.time.LocalDate;
import java.time.LocalTime;

import org.hibernate.annotations.Check;

import com.strong.BloodDonation.Utils.AppointmentStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer appointmentId;
    
    @Column(nullable = false)
    private Donor donor;

    @Column(nullable = false)
    private LocalDate appointmentDate;

    @Column(nullable = false)
    @Check(constraints = "appointment_time >= '09:00:00' AND appointment_time <= '17:00:00'")
    private LocalTime appointmentTime;

    @Column(nullable = false)
    private String location;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AppointmentStatus status;
}
