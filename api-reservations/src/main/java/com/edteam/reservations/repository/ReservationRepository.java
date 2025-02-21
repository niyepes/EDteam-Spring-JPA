package com.edteam.reservations.repository;

import com.edteam.reservation.model.Reservation
import org.springframework.data.jpa.repository.JpaRepository

public interface ReservationRepository extends JpaRepository<Reservation,Long> {

}
