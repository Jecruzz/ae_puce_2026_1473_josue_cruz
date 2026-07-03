package com.pucetec.events.repositories

import com.pucetec.events.entities.Reservation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface ReservationsRepository : JpaRepository<Reservation, Long> {
    fun countByAttendeeIdAndStatus(attendeeID: Long, status : String): Int

}

typealias ReservationRepository = ReservationsRepository
