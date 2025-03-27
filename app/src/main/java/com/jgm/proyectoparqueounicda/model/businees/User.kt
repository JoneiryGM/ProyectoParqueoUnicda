package com.jgm.proyectoparqueounicda.model.businees

import androidx.annotation.Keep
import com.google.firebase.firestore.PropertyName

@Keep
data class User(
    @get:PropertyName("creationDate") @set:PropertyName("creationDate") var creationDate: String,
    @get:PropertyName("fullname") @set:PropertyName("fullname") var fullname: String,
    @get:PropertyName("password") @set:PropertyName("password") var password: String,
    @get:PropertyName("username") @set:PropertyName("username") var username: String,
    @get:PropertyName("role") @set:PropertyName("role") var role: String
) {

    constructor() : this("", "", "", "", "")
}