package com.example.gdemobile.ui.cargoList.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.gdemobile.R
import com.example.gdemobile.databinding.RecyclerviewCargoBinding
import com.example.gdemobile.models.DocumentPosition
import com.example.gdemobile.ui.cargoList.adapters.DocumentPositionAdapter.CargoViewHolder

class DocumentPositionAdapter(private var cargos: MutableList<DocumentPosition>) : RecyclerView.Adapter<CargoViewHolder>() {

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
        holder.recyclerviewCargoHolder.eraseButton.setOnClickListener {
            deleteDocumentPosition(cargos.get(position))
        }
        holder.recyclerviewCargoHolder.eraseButton.setOnLongClickListener {

            deleteDocumentPosition(cargos.get(position) ,true)
            true
        }


    }

    fun filtrElements(chars: String) {
        cargos = tempCargos
        cargos = cargos.filter { it.barcode.contains(chars) || it.name?.lowercase()?.contains(chars.lowercase()) == true } as MutableList<DocumentPosition>
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return cargos.size
    }

    fun deleteDocumentPosition(documentPostion : DocumentPosition, deleteAll : Boolean = false)
    {
        if(deleteAll || documentPostion.amount <= 1)
            cargos.remove(documentPostion)
        else
            documentPostion.amount -= 1
        notifyDataSetChanged()
        Log.i("Sizeeeee", cargos.size.toString())
    }
}