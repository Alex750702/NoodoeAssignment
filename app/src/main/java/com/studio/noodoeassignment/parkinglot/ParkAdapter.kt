package com.studio.noodoeassignment.parkinglot

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.studio.noodoeassignment.data.ParkInfoUI
import com.studio.noodoeassignment.databinding.LayoutParkBinding

class ParkAdapter : RecyclerView.Adapter<ParkViewHolder>() {

    private var parkData = arrayListOf<ParkInfoUI>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParkViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: LayoutParkBinding =
            LayoutParkBinding.inflate(layoutInflater, parent, false)
        return ParkViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ParkViewHolder, position: Int) {
        val item = parkData[holder.adapterPosition]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return parkData.size
    }

    fun setPark(it: Map<String, ParkInfoUI>) {
        parkData.clear()
        parkData.addAll(it.values.toList())
        notifyDataSetChanged()
    }

}

class ParkViewHolder(var binding: LayoutParkBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(parkInfoUI: ParkInfoUI) {
        binding.parkInfoUI = parkInfoUI
        binding.executePendingBindings()
    }
}
