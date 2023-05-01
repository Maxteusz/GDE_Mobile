package com.example.gdemobile.ui.cargoList.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.gdemobile.R
import com.example.gdemobile.databinding.RecyclerviewContractorBinding
import com.example.gdemobile.models.Contractor
import com.example.gdemobile.ui.cargoList.CargoListView

class ContractorAdapter (private val contractors : List<Contractor>, private val cargoListView: CargoListView) : RecyclerView.Adapter<ContractorAdapter.ContractorViewHolder>() {


    inner class ContractorViewHolder (val recyclerviewContractorHolder : RecyclerviewContractorBinding
    ) : RecyclerView.ViewHolder(recyclerviewContractorHolder.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ContractorViewHolder(
       DataBindingUtil.inflate(
           LayoutInflater.from(parent.context),
           R.layout.recyclerview_contractor,
           parent,
           false))



    override fun onBindViewHolder(holder: ContractorViewHolder, position: Int) {
      holder.recyclerviewContractorHolder.contractor = contractors[position]
        holder.itemView.setOnClickListener {
            cargoListView.document.value?.contractor = contractors[position]
        }
    }

    override fun getItemCount(): Int {
       return contractors.size
    }
}