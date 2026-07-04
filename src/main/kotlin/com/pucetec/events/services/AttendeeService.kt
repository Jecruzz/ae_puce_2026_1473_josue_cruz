package com.pucetec.events.services

import com.pucetec.events.dto.AttendeeRequest
import com.pucetec.events.dto.AttendeeResponse
import com.pucetec.events.exceptions.BlankFieldException
import com.pucetec.events.mappers.toEntity
import com.pucetec.events.mappers.toResponse
import com.pucetec.events.repositories.AttendeeRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class AttendeeService(
    private val attendeeRepository: AttendeeRepository
) {

    companion object {
        private val logger = LoggerFactory.getLogger(AttendeeService::class.java)
    }

    fun createAttendee(request: AttendeeRequest): AttendeeResponse {
        logger.info("Creando con email={}", request.email)

        if (request.name.isBlank() || request.email.isBlank()) {
            logger.warn("Validacion fallida: Nombre o email en blanco")
            throw BlankFieldException()
        }

        val saved = attendeeRepository.save(request.toEntity())

        logger.info("Attendee creado con exito with id={}", saved.id)

        return saved.toResponse()
    }
}