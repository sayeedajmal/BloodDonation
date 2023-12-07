package com.strong.BloodDonation.Service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.strong.BloodDonation.Model.Appointment;
import com.strong.BloodDonation.Repository.AppointRepo;

@Service
public class AppointService {

    @Autowired
    private AppointRepo appointRepo;

    public void saveAppointment(Appointment appointment) {
        appointRepo.save(appointment);
    }

    public Appointment findById(Integer id) {
        return appointRepo.findById(id).get();
    }

    public List<Appointment> findAll(){
        return appointRepo.findAll();
    }

  /*   public Appointment findByName(Date dateTime) {
        return appointRepo.findByAppointMentDateTime(dateTime);
    } */

    public void deleteAppointment(Integer appointId) {
        Appointment findById = findById(appointId);
        appointRepo.delete(findById);
    }

    public void updateAppointment(Appointment appointment) {
        if (appointment.getAppointmentId() != null) {
            appointRepo.save(appointment);
        }
    }

}
