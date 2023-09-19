package com.example.gdemobile.ui.cargoList.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.gdemobile.R
import com.example.gdemobile.databinding.RecyclerviewDocumentdefinitionBinding
import com.example.gdemobile.models.DocumentDefinition
import com.example.gdemobile.ui.cargoList.InssuingCargoListViewModel

class DocumentDefinitionAdapter (private val documentDefinitions : List<DocumentDefinition>, private val cargoListView: InssuingCargoListViewModel, private val fragment: Fragment) : RecyclerView.Adapter<DocumentDefinitionAdapter.DocumentDefnitionViewHolder>() {

   inner class DocumentDefnitionViewHolder(val recyclerviewDocumentdefinitionBinding: RecyclerviewDocumentdefinitionBinding
   ) : RecyclerView.ViewHolder(recyclerviewDocumentdefinitionBinding.root){

   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DocumentDefnitionViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.recyclerview_documentdefinition,
            parent,
            false))

    override fun getItemCount(): Int {
        return documentDefinitions.size
    }

    override fun onBindViewHolder(holder: DocumentDefnitionViewHolder, position: Int) {
        holder.recyclerviewDocumentdefinitionBinding.documentDefinition = documentDefinitions[position]
        holder.recyclerviewDocumentdefinitionBinding.maincard.setOnClickListener {
            cargoListView.currentDocument.value?.definition = documentDefinitions[position]
            fragment.findNavController().popBackStack()


        }
    }


}