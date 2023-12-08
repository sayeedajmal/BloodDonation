package com.strong.BloodDonation.Service;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.strong.BloodDonation.Model.Appointment;
import com.strong.BloodDonation.Repository.AppointRepo;
import com.strong.BloodDonation.Utils.BloodException;

@Service
public class AppointService {

    @Autowired
    private AppointRepo appointRepo;

    public void saveAppointment(Appointment appointment) {
        if (!isWithinBusinessHours(appointment.getAppointmentTime())) {
            throw new IllegalArgumentException("Appointment time must be within business hours.");
        } else
            appointRepo.save(appointment);
    }

    public Appointment findById(Integer id) throws BloodException {
        Appointment byId = appointRepo.findById(id).orElse(null);
        if (byId != null) {
            return byId;
        } else
            throw new BloodException("Can't Find Appointment with " + id + " Appointment ID");

    }

    public List<Appointment> findAll() throws BloodException {
        List<Appointment> findAll = appointRepo.findAll();
        if (findAll.isEmpty()) {
            throw new BloodException("There isn't Any Appointment List");
        } else
            return findAll;
    }

    public void deleteAppointment(Integer appointId) throws BloodException {
        Appointment findById = findById(appointId);
        appointRepo.delete(findById);
    }

    /* Timing For AppointMents */
    public void updateAppointment(Appointment appointment) {
        if (appointment.getAppointmentId() != null) {
            appointRepo.save(appointment);
        }
    }

    private boolean isWithinBusinessHours(LocalTime appointmentTime) {
        LocalTime businessStart = LocalTime.of(9, 0, 0);
        LocalTime businessEnd = LocalTime.of(17, 0, 0);
        return !appointmentTime.isBefore(businessStart) &&
                !appointmentTime.isAfter(businessEnd);
    }

}
