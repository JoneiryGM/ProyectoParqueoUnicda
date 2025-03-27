package com.jgm.proyectoparqueounicda.model.businees

import androidx.annotation.Keep
import com.google.firebase.firestore.PropertyName

@Keep
data class Parking(
    @get:PropertyName("id") @set:PropertyName("id") var id: String,
    @get:PropertyName("index") @set:PropertyName("index") var index: Int,
    @get:PropertyName("isAvailable") @set:PropertyName("isAvailable") var isAvailable: Boolean
) {

    constructor() : this("", 0, true)
}