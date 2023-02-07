package com.ejanowski.demofirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.ejanowski.demofirebase.adapters.MaximeAdapter
import com.ejanowski.demofirebase.databinding.ActivityMaximeBinding

class MaximeActivity : AppCompatActivity() {
    lateinit var binding: ActivityMaximeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMaximeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = MaximeAdapter(this) { date, hour, terrain ->
            Toast.makeText(this, "${date} ${hour+7}h terrain : ${terrain}", Toast.LENGTH_LONG).show()
        }
    }
}