package com.hana.vehicleappproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hana.vehicleappproject.R

class JadwalAdapter(private val jadwalList: List<String>) :
    RecyclerView.Adapter<JadwalAdapter.JadwalViewHolder>() {

    inner class JadwalViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val jadwalText: TextView = view.findViewById(R.id.tv_jadwal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JadwalViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_jadwal_perawatan, parent, false)
        return JadwalViewHolder(view)
    }

    override fun onBindViewHolder(holder: JadwalViewHolder, position: Int) {
        holder.jadwalText.text = jadwalList[position]
    }

    override fun getItemCount(): Int = jadwalList.size
}
