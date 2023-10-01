package com.example.gdemobile.ui.cargoList.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.gdemobile.R
import com.example.gdemobile.databinding.RecyclerviewCargoBinding
import com.example.gdemobile.models.DocumentPosition
import com.example.gdemobile.ui.cargoList.InssuingCargoListViewModel
import com.example.gdemobile.ui.cargoList.adapters.DocumentPositionAdapter.CargoViewHolder

class DocumentPositionAdapter(private var cargos: MutableList<DocumentPosition>, private var viewModel: InssuingCargoListViewModel)
    : RecyclerView.Adapter<CargoViewHolder>() {

    private val tempCargos: MutableList<DocumentPosition> = cargos
    inner class CargoViewHolder(
        val recyclerviewCargoHolder: RecyclerviewCargoBinding

    ) : RecyclerView.ViewHolder(recyclerviewCargoHolder.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CargoViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.recyclerview_cargo,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: CargoViewHolder, position: Int) {
        holder.recyclerviewCargoHolder.cargo = cargos.get(position)


    }




    override fun getItemCount(): Int {
        return cargos.size
    }


}