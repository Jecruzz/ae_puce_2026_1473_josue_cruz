package com.pucetec.events.dto

import java.time.LocalDateTime


data class ReservationRequest (
    var attendeeId: Long,
    val eventId: Long
)


data class ReservationResponse (
    val id: Long,
    val status: String,
    val createdAt: LocalDateTime,
    val event: EventResponse,
    val attendee: AttendeeResponse
)