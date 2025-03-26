package com.jgm.proyectoparqueounicda.model.businees

import java.time.LocalDateTime

data class User(
    val creationDate: LocalDateTime,
    val fullname: String,
    val password: String,
    val username: String,
    val role: String
)