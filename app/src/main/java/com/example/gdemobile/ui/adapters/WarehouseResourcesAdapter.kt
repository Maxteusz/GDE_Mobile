package com.example.gdemobile.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.gdemobile.R
import com.example.gdemobile.databinding.CardExtensioninfwarehosueresourceBinding
import com.example.gdemobile.databinding.CardPrimaryinfwarehouseresourceBinding
import com.example.gdemobile.models.WarehouseResource

class WarehouseResourcesAdapter(
    private val warehouseResources: List<WarehouseResource>,
    private val warehouseResourceType: WarehouseResourceType,
    private val _onClickListener : IOnClickListener? = null
) : RecyclerView.Adapter<WarehouseResourcesAdapter.WarehouseResourcesAdapterViewHolder>() {

    inner class WarehouseResourcesAdapterViewHolder(
        val primaryBinding: CardPrimaryinfwarehouseresourceBinding?,
        val secondaryBinding: CardExtensioninfwarehosueresourceBinding?
    ) : RecyclerView.ViewHolder(
        primaryBinding?.root ?: secondaryBinding?.root ?: error("Binding not found.")
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WarehouseResourcesAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (warehouseResourceType) {
            WarehouseResourceType.PRIMARY -> {
                val primaryBinding =
                    DataBindingUtil.inflate<CardPrimaryinfwarehouseresourceBinding>(
                        layoutInflater,
                        R.layout.card_primaryinfwarehouseresource,
                        parent,
                        false
                    )
                WarehouseResourcesAdapterViewHolder(primaryBinding, null)
            }
            WarehouseResourceType.EXTENSION -> {
                val secondaryBinding =
                    DataBindingUtil.inflate<CardExtensioninfwarehosueresourceBinding>(
                        layoutInflater,
                        R.layout.card_extensioninfwarehosueresource,
                        parent,
                        false
                    )
                WarehouseResourcesAdapterViewHolder(null, secondaryBinding)
            }
        }
    }

    enum class WarehouseResourceType {
        PRIMARY, EXTENSION
    }

    override fun getItemCount(): Int = warehouseResources.size

    interface IOnClickListener
    {
        fun onClick(warehouseResource: WarehouseResource)
    }
    override fun onBindViewHolder(holder: WarehouseResourcesAdapterViewHolder, position: Int) {
        holder.primaryBinding?.warehouseResource = warehouseResources[position]
        holder.primaryBinding?.maincard?.setOnClickListener {
            _onClickListener?.onClick(warehouseResources[position])
        }
        holder.secondaryBinding?.warehouseResource = warehouseResources[position]
    }
}