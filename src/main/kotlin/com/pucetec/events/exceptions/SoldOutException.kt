package com.pucetec.events.exceptions

class SoldOutException (message: String = "Sold out") :
    RuntimeException(message)