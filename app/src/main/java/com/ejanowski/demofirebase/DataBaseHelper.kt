package com.ejanowski.demofirebase

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class DataBaseHelper {
    companion object {
        val database = Firebase.database("https://demofirebase-c9625-default-rtdb.europe-west1.firebasedatabase.app/")
    }
}