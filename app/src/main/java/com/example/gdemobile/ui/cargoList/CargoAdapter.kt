package com.example.gdemobile.ui.cargoList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.gdemobile.R
import com.example.gdemobile.databinding.RecyclerviewCargoBinding
import com.example.gdemobile.databinding.RecyclerviewContractorBinding
import com.example.gdemobile.models.Cargo
import com.example.gdemobile.ui.cargoList.CargoAdapter.CargoViewHolder

class CargoAdapter(private val cargos: List<Cargo>): RecyclerView.Adapter<CargoViewHolder>() {

    inner class CargoViewHolder (val recyclerviewCargoHolder : RecyclerviewCargoBinding
    ) : RecyclerView.ViewHolder(recyclerviewCargoHolder.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CargoViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.recyclerview_cargo,
            parent,
            false))

    override fun onBindViewHolder(holder: CargoViewHolder, position: Int) {
        holder.recyclerviewCargoHolder.cargo= cargos.get(position)
    }

    override fun getItemCount(): Int {
        return cargos.size
    }
}