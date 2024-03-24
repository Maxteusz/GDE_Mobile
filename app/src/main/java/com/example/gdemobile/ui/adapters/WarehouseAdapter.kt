package com.example.gdemobile.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.gdemobile.R
import com.example.gdemobile.databinding.CardWarehouseBinding
import com.example.gdemobile.models.Warehouse

class WarehouseAdapter (private val warehouses: List<Warehouse>, private var listener : WarehouseAdapter.ViewHolderListener)
    : RecyclerView.Adapter<WarehouseAdapter.WarehouseViewHolder>() {
    interface  ViewHolderListener {
        fun onItemClicked(warehouse: Warehouse)
    }

    inner class WarehouseViewHolder (val recyclerviewWarehouseHolder : CardWarehouseBinding
    ) : RecyclerView.ViewHolder(recyclerviewWarehouseHolder.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WarehouseViewHolder =
        WarehouseViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.card_warehouse,
            parent,
            false))

    override fun getItemCount(): Int {
       return  warehouses.size
    }

    override fun onBindViewHolder(holder: WarehouseViewHolder, position: Int) {
        holder.recyclerviewWarehouseHolder.warehouse = warehouses[position]
        holder.recyclerviewWarehouseHolder.maincard.setOnClickListener {
            listener.onItemClicked(warehouses.get(position))
        }
    }


}