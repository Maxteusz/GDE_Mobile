package com.example.gdemobile.ui.cargoList.adapters


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.gdemobile.R
import com.example.gdemobile.databinding.RecyclerviewContractorBinding
import com.example.gdemobile.models.Contractor
import com.example.gdemobile.ui.cargoList.InssuingCargoListViewModel


class ContractorAdapter (private val contractors : List<Contractor>, private val cargoListView: InssuingCargoListViewModel, private val fragment: Fragment)
    : RecyclerView.Adapter<ContractorAdapter.ContractorViewHolder>() {


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
        holder.recyclerviewContractorHolder.maincard.setOnClickListener {
            cargoListView.document.value?.contractor = contractors[position]
            Log.i("Set Contractor", cargoListView.document.value?.contractor?.name!!)
            fragment.findNavController().popBackStack()

           // notifyDataSetChanged()
        }


    }

    override fun getItemCount(): Int {
       return contractors.size
    }
}