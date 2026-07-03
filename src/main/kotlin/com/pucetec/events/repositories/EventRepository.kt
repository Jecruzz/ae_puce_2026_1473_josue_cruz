package com.pucetec.events.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import com.pucetec.events.entities.Event


@Repository
interface EventsRepository : JpaRepository<Event, Long>

typealias EventRepository = EventsRepository