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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne
    @JoinColumn(name = "donarId", nullable = false)
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

    /* SERICE LAYER */
    /*
     * public void createAppointment(Appointment appointment) {
     * validateTimeRange(appointment.getAppointmentTime());
     * // Other business logic
     * // Save the appointment to the database
     * }
     * 
     * private void validateTimeRange(LocalTime appointmentTime) {
     * // Implement your time range validation logic here
     * if (!isWithinBusinessHours(appointmentTime)) {
     * throw new
     * IllegalArgumentException("Appointment time must be within business hours.");
     * }
     * }
     * 
     * private boolean isWithinBusinessHours(LocalTime appointmentTime) {
     * LocalTime businessStart = LocalTime.of(9, 0, 0);
     * LocalTime businessEnd = LocalTime.of(17, 0, 0);
     * return !appointmentTime.isBefore(businessStart) &&
     * !appointmentTime.isAfter(businessEnd);
     * }
     */
}
