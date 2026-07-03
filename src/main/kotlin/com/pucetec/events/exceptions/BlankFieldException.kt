package com.pucetec.events.exceptions

class BlankFieldException(message: String = "El campo obligatorio no puede estar vacio") :
    RuntimeException(message)
