package com.pucetec.events.exceptions

import org.springframework.web.bind.annotation.RestControllerAdvice
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.ErrorResponse
import org.springframework.web.bind.annotation.ExceptionHandler


@RestControllerAdvice
class GlobalExceptionHandler {
    private val logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    @ExceptionHandler(AttendeeNotFoundException::class)
    fun handleAttendeeNotFoundException(e: AttendeeNotFoundException): ResponseEntity<ExceptionResponse> {
        logger.warn("Excepcion de validacion: AttendeeNotFoundException - Mensaje: ${e.message}")
        return ResponseEntity(ExceptionResponse(e.message ?: "Attendee not found", "Validation"), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(BlankFieldException::class)
    fun handleBlankFieldException(e: BlankFieldException): ResponseEntity<ExceptionResponse> {
        logger.warn("Excepcion de validacion: BlankFieldException - Mensaje: ${e.message}")
        return ResponseEntity(ExceptionResponse(e.message ?: "Field cannot be blank", "Validation"), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(EventNotFoundException::class)
    fun handleEventNotFoundException(e: EventNotFoundException): ResponseEntity<ExceptionResponse> {
        logger.warn("Excepcion de validacion: EventNotFoundException - Mensaje: ${e.message}")
        return ResponseEntity(ExceptionResponse(e.message ?: "Event not found", "Validation"), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(ReservationAlreadyCancelledException::class)
    fun handleReservationAlreadyCancelledException(e: ReservationAlreadyCancelledException): ResponseEntity<ExceptionResponse> {
        logger.warn("Excepcion de validacion: ReservationAlreadyCancelledException - Mensaje: ${e.message}")
        return ResponseEntity(ExceptionResponse(e.message ?: "Reservation already cancelled", "Validation"), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(ReservationLimitExceededException::class)
    fun handleReservationLimitExceededException(e: ReservationLimitExceededException): ResponseEntity<ExceptionResponse> {
        logger.warn("Excepcion de validacion: ReservationLimitExceededException - Mensaje: ${e.message}")
        return ResponseEntity(ExceptionResponse(e.message ?: "Reservation limit exceeded", "Validation"), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(ReservationNotFoundException::class)
    fun handleReservationNotFoundException(e: ReservationNotFoundException): ResponseEntity<ExceptionResponse> {
        logger.warn("Excepcion de validacion: ReservationNotFoundException - Mensaje: ${e.message}")
        return ResponseEntity(ExceptionResponse(e.message ?: "Reservation not found", "Validation"), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(SoldOutException::class)
    fun handleSoldOutException(e: SoldOutException): ResponseEntity<ExceptionResponse> {
        logger.warn("Excepcion de validacion: SoldOutException - Mensaje: ${e.message}")
        return ResponseEntity(ExceptionResponse(e.message ?: "Event is sold out", "Validation"), HttpStatus.BAD_REQUEST)
    }

}









