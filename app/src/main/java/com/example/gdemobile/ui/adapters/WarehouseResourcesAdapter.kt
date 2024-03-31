package com.example.gdemobile.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.gdemobile.R
import com.example.gdemobile.databinding.CardPrimaryinfwarehouseresourceBinding
import com.example.gdemobile.models.WarehouseResource

class WarehouseResourcesAdapter(private val warehouseResources: List<WarehouseResource>, val warehouseResourceType : WAREHOUSE_RESOURCE_TYPE) : RecyclerView.Adapter<WarehouseResourcesAdapter.WarehouseResourcesAdapterViewHolder>() {
    class WarehouseResourcesAdapterViewHolder (
       val  recyclerviewWarehouseResourceHolder : CardPrimaryinfwarehouseresourceBinding
    ) : RecyclerView.ViewHolder(recyclerviewWarehouseResourceHolder.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WarehouseResourcesAdapterViewHolder {
         when (warehouseResourceType) {
            WAREHOUSE_RESOURCE_TYPE.PRIMARY ->
                return bind(R.layout.card_primaryinfwarehouseresource, parent)
            WAREHOUSE_RESOURCE_TYPE.EXTENSION ->
                return bind(R.layout.card_extensioninfwarehosueresource, parent)
        }
    }

    private fun bind(layout : Int, parent: ViewGroup,) : WarehouseResourcesAdapterViewHolder
    {
        return WarehouseResourcesAdapterViewHolder(
            DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            layout,
            parent,
            false))
    }

   public enum WAREHOUSE_RESOURCE_TYPE {
        PRIMARY, EXTENSION
    }



    override fun getItemCount(): Int {
      return  warehouseResources.size
    }

    override fun onBindViewHolder(holder: WarehouseResourcesAdapterViewHolder, position: Int) {
       holder.recyclerviewWarehouseResourceHolder.warehouseResource = warehouseResources[position]
    }


}