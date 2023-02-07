package com.ejanowski.demofirebase.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ejanowski.demofirebase.databinding.CellHourBinding

class HourAdapter: RecyclerView.Adapter<HourAdapter.HourViewHolder>() {
    class HourViewHolder(binding: CellHourBinding) : RecyclerView.ViewHolder(binding.root) {
        val hourLabel = binding.hourLabel
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourViewHolder {
        val binding = CellHourBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HourViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return (22 - 7 + 1) * 8
    }

    override fun onBindViewHolder(holder: HourViewHolder, position: Int) {
        val hour = position / 8
        val weekDay = position % 8
        val days  = listOf("Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche")
        if (hour == 0 && weekDay != 0) { // Ligne d'affichage legénde Haut
            holder.hourLabel.text = days[weekDay-1]
            holder.hourLabel.rotation = -60F
        } else if(weekDay == 0 && hour != 0) { // Ligne d'affichage legénde Gauche
            holder.hourLabel.text = "${hour.toInt()+8}h-${hour.toInt()+9}h"
        } else  if(hour == 0 && weekDay == 0) {

        } else {
            holder.hourLabel.text = "X"
        }
    }
}