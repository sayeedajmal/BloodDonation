package com.strong.BloodDonation.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.strong.BloodDonation.Model.Appointment;

public interface AppointRepo extends JpaRepository<Appointment, Integer> {

    @Query("SELECT d.donorId FROM Donor d LEFT JOIN Appointment a ON d.donorId = a.donor.donorId WHERE a.donor.donorId IS NULL")
    public List<Integer> doAppointDonor();

    @Query("SELECT e FROM Appointment e WHERE e.appointmentDate= :today AND e.status=SCHEDULED ORDER BY e.appointmentTime DESC")
    public List<Appointment> todayAppointments(@Param("today") LocalDate today);

    @Query("SELECT e FROM Appointment e WHERE e.appointmentDate= :date ORDER BY e.appointmentTime DESC")
    public List<Appointment> findAppointByDate(@Param("date") LocalDate date);
}
