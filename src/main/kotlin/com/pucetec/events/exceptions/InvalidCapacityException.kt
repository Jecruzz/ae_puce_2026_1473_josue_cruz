package com.pucetec.events.exceptions

class InvalidCapacityException (message: String = "La capacidad total de tickets debe ser al menos 1") :
    RuntimeException(message)