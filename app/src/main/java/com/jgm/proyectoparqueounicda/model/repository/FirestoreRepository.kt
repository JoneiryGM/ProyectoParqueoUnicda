package com.jgm.proyectoparqueounicda.model.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.jgm.proyectoparqueounicda.model.businees.LoginRequest
import com.jgm.proyectoparqueounicda.model.businees.Parking
import com.jgm.proyectoparqueounicda.model.businees.ParkingConfig
import com.jgm.proyectoparqueounicda.model.businees.User
import com.jgm.proyectoparqueounicda.util.Constants
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class FirestoreRepository {

    private val firestore = FirebaseFirestore.getInstance()

    private val userCollection = firestore.collection(Constants.USER_COLLECT)
    private val parkingCollection = firestore.collection(Constants.PARKING_COLLECT)
    private val settingsCollection = firestore.collection(Constants.SETTINGS_COLLECT)


    //Lista todos los parqueos en la colecion de parking_collect
    fun getParkings(): Flow<List<Parking>> = callbackFlow {
        val listener = parkingCollection.addSnapshotListener { snapshot, error ->
            if (error != null) {
                close(error)
                return@addSnapshotListener
            }
            val parking =
                snapshot?.documents?.mapNotNull { it.toObject(Parking::class.java) } ?: emptyList()
            trySend(parking)
        }
        awaitClose { listener.remove() }
    }

    //Obtiene el indice actual de los parqueos en settings_collect
    fun getIndexParkings(): Flow<ParkingConfig> = callbackFlow {
        val listener = settingsCollection.document(Constants.SETTINGS_DOCUMENT_PARKING).addSnapshotListener { snapshot, error ->
            if (error != null) {
                close(error)
                return@addSnapshotListener
            }
            val parkingConfig = snapshot?.toObject(ParkingConfig::class.java)
            parkingConfig?.let { trySend(it) }
        }
        awaitClose { listener.remove() }
    }

    /*Query para buscar el userCollection el usuario segun username & password (si existe
     y son correctos los datos retorna el objecto de User)
     */
    fun doSignIn(loginRequest: LoginRequest): Flow<User?> = callbackFlow {
        val query = userCollection.whereEqualTo("username", loginRequest.username)
            .whereEqualTo("password", loginRequest.password).limit(1)

        val listener = query.addSnapshotListener { snapshot, error ->
            if (error != null) {
                close(error)
                return@addSnapshotListener
            }
            val user = snapshot?.documents?.firstOrNull()?.toObject(User::class.java)
            trySend(user)
        }
        awaitClose { listener.remove() }
    }

    //Actualiza en la colecion de settings_collect el paremtro para ajustar la cantidad de parqueos
    suspend fun updateSettingsParking(parkingConfig: ParkingConfig) {
        settingsCollection.document(Constants.SETTINGS_DOCUMENT_PARKING).set(parkingConfig).await()
    }

}
