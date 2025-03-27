package com.jgm.proyectoparqueounicda.model.businees

import androidx.annotation.Keep
import com.google.firebase.firestore.PropertyName

@Keep
data class ParkingConfig(
    @get:PropertyName("qty") @set:PropertyName("qty")
    var qty: Int
){
    constructor() : this(0)
}
