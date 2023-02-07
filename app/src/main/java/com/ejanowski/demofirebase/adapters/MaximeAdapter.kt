package com.ejanowski.demofirebase.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ejanowski.demofirebase.databinding.CellDayBinding
import java.util.*

class MaximeAdapter: RecyclerView.Adapter<MaximeAdapter.CellDayViewHolder>() {
    val firstDayIndex: Int

    init {
        val calendar: Calendar = Calendar.getInstance()
        val day: Int = calendar.get(Calendar.DAY_OF_WEEK)
        firstDayIndex = day -1
    }

    private val days = listOf("Dimanche", "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi")
    class CellDayViewHolder(binding: CellDayBinding) : RecyclerView.ViewHolder(binding.root) {
        val dayTitle = binding.textViewDayTitle
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CellDayViewHolder {
        val binding = CellDayBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CellDayViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return days.count()
    }

    override fun onBindViewHolder(holder: CellDayViewHolder, position: Int) {
        holder.dayTitle.text = days[(position + firstDayIndex) % days.count()]
    }
}