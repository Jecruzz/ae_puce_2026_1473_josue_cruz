package com.pucetec.events.exceptions

class ReservationLimitExceededException (message: String = "Se ha superado el limite maximo de 4 reservas") :
    RuntimeException(message)