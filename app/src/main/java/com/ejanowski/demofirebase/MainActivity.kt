package com.ejanowski.demofirebase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.IgnoreExtraProperties
import java.text.SimpleDateFormat
import java.util.*

lateinit var database: DatabaseReference

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //writeNewUser()
        //createBooking()
        createReservation()
    }

    fun writeNewUser() {
        val user = User("emeric", "janowski.emeric@gmail.com", "aqaqaq",UUID.randomUUID().toString())
        DataBaseHelper.database.child("users").child(user.UUID).setValue(user)
    }

    fun createReservation() {
        val df = SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE)
        val date = df.format(Date())

        val hours = mapOf("12" to "ac4fac74-4872-4553-b2c8-72fa5198c416")
        val reservation = Reservation(date, hours)

        DataBaseHelper.database.child("Reservations").child(reservation.date).setValue(reservation)
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

        DataBaseHelper.database.child("occupationDays").child(occupationDay.date).setValue(occupationDay)
    }
}

@IgnoreExtraProperties
data class User(val username: String? = null, val email: String? = null, val password: String? = null, val UUID: String) {
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