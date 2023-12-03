package com.strong.BloodDonation.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.strong.BloodDonation.Model.Donor;

public interface DonorRepo extends JpaRepository<Donor, Integer> {

    @Query("SELECT e FROM Donor e WHERE e.firstName= :firstName AND e.lastName= :lastName")
    public Donor findByFirstNameAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);
}