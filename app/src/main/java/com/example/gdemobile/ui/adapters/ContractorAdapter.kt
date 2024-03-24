package com.example.gdemobile.ui.adapters


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.gdemobile.R
import com.example.gdemobile.databinding.CardContractorBinding

import com.example.gdemobile.models.Contractor


class ContractorAdapter (private val contractors : List<Contractor>, private var listener : ViewHolderListener)
    : RecyclerView.Adapter<ContractorAdapter.ContractorViewHolder>() {


    inner class ContractorViewHolder (val recyclerviewContractorHolder : CardContractorBinding
    ) : RecyclerView.ViewHolder(recyclerviewContractorHolder.root)


    interface ViewHolderListener {
        fun onItemClicked(contractor: Contractor)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ContractorViewHolder(
       DataBindingUtil.inflate(
           LayoutInflater.from(parent.context),
           R.layout.card_contractor,
           parent,
           false))



    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: ContractorViewHolder, position: Int) {
      holder.recyclerviewContractorHolder.contractor = contractors[position]
        holder.recyclerviewContractorHolder.maincard.setOnClickListener {
            listener.onItemClicked(contractors.get(position))



        }


    }

    override fun getItemCount(): Int {
       return contractors.size
    }
}