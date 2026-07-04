package com.pucetec.events.services

import com.pucetec.events.dto.AttendeeRequest
import com.pucetec.events.entities.Attendee
import com.pucetec.events.exceptions.BlankFieldException
import com.pucetec.events.repositories.AttendeeRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.any
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class AttendeeServiceTest {

    @Mock
    lateinit var attendeeRepository: AttendeeRepository

    @InjectMocks
    lateinit var attendeeService: AttendeeService

    @Test
    fun `createAttendee returns response when valid`() {
        val request = AttendeeRequest("Diego", "diegou243@gmail.com")
        val entity = Attendee(1L, "Diego", "diegou243@gmail.com")

        `when`(attendeeRepository.save(any(Attendee::class.java))).thenReturn(entity)

        val response = attendeeService.createAttendee(request)

        assertEquals(1L, response.id)
        assertEquals("Diego", response.name)
        assertEquals("diegou243@gmail.com", response.email)
    }

    @Test
    fun `createAttendee throws BlankFieldException when name is blank`() {
        val request = AttendeeRequest("", "diegou243@gmail.com")
        assertThrows<BlankFieldException> { attendeeService.createAttendee(request) }
    }
}