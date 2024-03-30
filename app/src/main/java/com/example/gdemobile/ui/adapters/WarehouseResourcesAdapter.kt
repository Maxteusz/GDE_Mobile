package com.example.gdemobile.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.gdemobile.R
import com.example.gdemobile.databinding.CardPrimaryinfwarehouseresourceBinding
import com.example.gdemobile.models.WarehouseResource

class WarehouseResourcesAdapter(private val warehouseResources: List<WarehouseResource>) : RecyclerView.Adapter<WarehouseResourcesAdapter.WarehouseResourcesAdapterViewHolder>() {
    class WarehouseResourcesAdapterViewHolder (
       val  recyclerviewWarehouseResourceHolder : CardPrimaryinfwarehouseresourceBinding
    ) : RecyclerView.ViewHolder(recyclerviewWarehouseResourceHolder.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WarehouseResourcesAdapterViewHolder
        {
        return WarehouseResourcesAdapterViewHolder(
                val binding DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.card_primaryinfwarehouseresource,
                    parent,
                    false))
        }



    override fun getItemCount(): Int {
      return  warehouseResources.size
    }

    override fun onBindViewHolder(holder: WarehouseResourcesAdapterViewHolder, position: Int) {
       holder.recyclerviewWarehouseResourceHolder.warehouseResource = warehouseResources[position]
    }


}