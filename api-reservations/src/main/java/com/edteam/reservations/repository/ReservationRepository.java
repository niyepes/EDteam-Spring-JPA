package com.edteam.reservations.repository;

import com.edteam.reservations.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByCreationDate (LocalDate localDate);

    List<Reservation> findByCreationDateAndPassengersFirstName (LocalDate localDate, String firstName);

    List<Reservation> findByCreationDateAndPassengersFirstNameAndPassengersLastName (LocalDate localDate, String firstName, String lastName);


}
