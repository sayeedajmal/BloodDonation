package com.strong.BloodDonation.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.strong.BloodDonation.Model.Appointment;

public interface AppointRepo extends JpaRepository<Appointment, Integer> {

    //Appointment findByAppointMentDateTime(Date dateTime);
}
