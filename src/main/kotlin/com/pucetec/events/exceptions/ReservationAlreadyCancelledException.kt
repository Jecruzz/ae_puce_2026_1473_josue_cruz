package com.pucetec.events.exceptions

class ReservationAlreadyCancelledException (message: String = "La reserva ya esta cancelada") :
    RuntimeException(message)