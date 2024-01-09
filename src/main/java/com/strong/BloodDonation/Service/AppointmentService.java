package com.strong.BloodDonation.Service;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.strong.BloodDonation.Model.Appointment;
import com.strong.BloodDonation.Repository.AppointRepo;
import com.strong.BloodDonation.Utils.BloodException;

@Service
public class AppointmentService {

    @Autowired
    private AppointRepo appointRepo;

    public void saveAppointment(Appointment appointment) throws BloodException {
        if (!isWithinBusinessHours(appointment.getAppointmentTime())) {
            throw new BloodException("Appointment time must be within business hours.");
        }
        try {
            appointRepo.save(appointment);
        } catch (Exception e) {
            throw new BloodException("Error saving appointment", e);
        }
    }

    public Appointment findById(Integer id) throws BloodException {
        return appointRepo.findById(id)
                .orElseThrow(() -> new BloodException("Can't Find Appointment with " + id + " Appointment ID"));
    }

    public List<Appointment> findAll() throws BloodException {
        List<Appointment> findAll = appointRepo.findAll();
        if (findAll.isEmpty()) {
            throw new BloodException("There isn't Any Appointment List");
        }
        return findAll;
    }

    public void deleteAppointment(Integer appointId) throws BloodException {
        Appointment findById = findById(appointId);
        try {
            appointRepo.delete(findById);
        } catch (Exception e) {
            throw new BloodException("Error deleting appointment", e);
        }
    }

    public void updateAppointment(Appointment appointment) throws BloodException {
        if (appointment.getAppointmentId() != null) {
            if (appointment.getAppointmentTime() == null) {
                throw new BloodException("Invalid appointment data");
            }
            try {
                appointRepo.save(appointment);
            } catch (Exception e) {
                throw new BloodException("Error updating appointment", e);
            }
        } else {
            throw new BloodException("Invalid Appointment ID: ");
        }
    }

    /* ====================LOGIC Timing For Appointments====================== */
    private boolean isWithinBusinessHours(LocalTime appointmentTime) {
        LocalTime businessStart = LocalTime.of(9, 0, 0);
        LocalTime businessEnd = LocalTime.of(17, 0, 0);
        return !appointmentTime.isBefore(businessStart) &&
                !appointmentTime.isAfter(businessEnd);
    }
}
