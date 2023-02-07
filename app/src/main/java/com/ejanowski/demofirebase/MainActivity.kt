package com.ejanowski.demofirebase

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import java.text.SimpleDateFormat
import java.util.*

lateinit var database: DatabaseReference

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getUser("janowski.emeric@gmail.com", "aqaqaq")
        //writeNewUser()
        //createBooking()
        //createReservation()
    }

    fun getUser(email: String, password: String) {
        DataBaseHelper.database.getReference("users")
            .orderByChild("email")
            .equalTo(email)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    Log.d("dataBase", snapshot.toString())
                    if(snapshot.exists()) {
                        val user = snapshot.children.first().getValue(User::class.java)
                        if(user?.password == password) {
                            Log.d("dataBase","connected")
                            // Connected
                        }
                    }

                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("dataBase", error.toString())
                }

            })
    }

    fun writeNewUser() {
        val user = User("emeric janowski", "emeric.janowski@it-ce.fr", "aqaqaq",UUID.randomUUID().toString())
        DataBaseHelper.database.reference.child("users").child(user.uuid ?: "").setValue(user)
    }

    fun createReservation() {
        val df = SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE)
        val date = df.format(Date())

        val hours = mapOf("12" to "ac4fac74-4872-4553-b2c8-72fa5198c416")
        val reservation = Reservation(date, hours)

        DataBaseHelper.database.reference.child("Reservations").child(reservation.date).setValue(reservation)
    }

    fun createBooking() {
        val df = SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE)
        val date = df.format(Date())

        val occupationDay = OccupationDay(
            date,
            listOf(
                "", // 7
                "", // 8
                "", // 9
                "", // 10
                "", // 11
                "ac4fac74-4872-4553-b2c8-72fa5198c416", // 12
                "", // 13
                "", // 14
                "", // 15
                "", // 16
                "", // 17
                "", // 18
                "", // 19
                "", // 20
                "", // 21
            ))

        DataBaseHelper.database.reference.child("occupationDays").child(occupationDay.date).setValue(occupationDay)
    }
}

@IgnoreExtraProperties
data class User(val username: String? = null,
                val email: String? = null,
                val password: String? = null,
                val uuid: String? = null) {
    // Null default values create a no-argument default constructor, which is needed
    // for deserialization from a DataSnapshot.
}

@IgnoreExtraProperties
data class OccupationDay(val date: String, val hours: List<String>) {
    // Null default values create a no-argument default constructor, which is needed
    // for deserialization from a DataSnapshot.
}

@IgnoreExtraProperties
data class Reservation(val date: String, val hours: Map<String, String>) {
    // Null default values create a no-argument default constructor, which is needed
    // for deserialization from a DataSnapshot.
}