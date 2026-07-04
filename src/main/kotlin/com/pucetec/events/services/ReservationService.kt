package com.pucetec.events.services

import com.pucetec.events.dto.ReservationRequest
import com.pucetec.events.dto.ReservationResponse
import com.pucetec.events.entities.Reservation
import com.pucetec.events.exceptions.*
import com.pucetec.events.mappers.toResponse
import com.pucetec.events.repositories.AttendeeRepository
import com.pucetec.events.repositories.EventRepository
import com.pucetec.events.repositories.ReservationRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
@Transactional
class ReservationService(
    private val reservationRepository: ReservationRepository,
    private val attendeeRepository: AttendeeRepository,
    private val eventRepository: EventRepository
) {

    companion object {
        private val logger = LoggerFactory.getLogger(ReservationService::class.java)
    }

    fun createReservation(request: ReservationRequest): ReservationResponse {

        logger.info(
            "Creating reservation for attendee={} event={}",
            request.attendeeId,
            request.eventId
        )

        val attendee = attendeeRepository.findById(request.attendeeId)
            .orElseThrow {
                logger.warn("Attendee not found: {}", request.attendeeId)
                AttendeeNotFoundException(": ${request.attendeeId}")
            }

        val event = eventRepository.findById(request.eventId)
            .orElseThrow {
                logger.warn("Event not found: {}", request.eventId)
                EventNotFoundException("Event not found with ID: ${request.eventId}")
            }

        if (event.availableTickets <= 0) {
            logger.warn("Reservation rejected: event {} is sold out", event.id)
            throw SoldOutException()
        }

        val activeReservations =
            reservationRepository.countByAttendeeIdAndStatus(attendee.id, "ACTIVE")

        if (activeReservations >= 4) {
            logger.warn(
                "Reservation rejected: attendee {} reached reservation limit",
                attendee.id
            )
            throw ReservationLimitExceededException()
        }

        event.availableTickets -= 1
        eventRepository.save(event)

        val reservation = Reservation(
            attendee = attendee,
            event = event,
            status = "ACTIVE",
            createdAt = LocalDateTime.now()
        )

        val saved = reservationRepository.save(reservation)

        logger.info(
            "Reservation {} created successfully. Remaining tickets: {}",
            saved.id,
            event.availableTickets
        )

        return saved.toResponse()
    }

    fun cancelReservation(id: Long): ReservationResponse {

        logger.info("Cancelando reservacion {}", id)

        val reservation = reservationRepository.findById(id)
            .orElseThrow {
                logger.warn("Reservation {} not found", id)
                ReservationNotFoundException()
            }

        if (reservation.status == "CANCELLED") {
            logger.warn("La reservacion {} esta cancelada", id)
            throw ReservationAlreadyCancelledException()
        }

        val event = reservation.event

        event.availableTickets += 1
        eventRepository.save(event)

        val updatedReservation = Reservation(
            id = reservation.id,
            attendee = reservation.attendee,
            event = event,
            status = "CANCELLED",
            createdAt = reservation.createdAt
        )

        val saved = reservationRepository.save(updatedReservation)

        logger.info(
            "Reservation {} cancelled successfully. Available tickets: {}",
            id,
            event.availableTickets
        )

        return saved.toResponse()
    }

    @Transactional(readOnly = true)
    fun getAllReservations(): List<ReservationResponse> {

        logger.info("Retrieving all reservations")

        val reservations = reservationRepository.findAll().map { it.toResponse() }

        logger.info("Retrieved {} reservations", reservations.size)

        return reservations
    }
}