package com.example.gdemobile.ui.cargoList.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.gdemobile.R
import com.example.gdemobile.databinding.RecyclerviewDocumentdefinitionBinding
import com.example.gdemobile.models.DocumentDefinition

class DocumentDefinitionAdapter(
    private val documentDefinitions: List<DocumentDefinition>,
    private val listener: DocumentDefinitionAdapter.ViewHolderListener
) : RecyclerView.Adapter<DocumentDefinitionAdapter.DocumentDefnitionViewHolder>() {

    inner class DocumentDefnitionViewHolder(
        val recyclerviewDocumentdefinitionBinding: RecyclerviewDocumentdefinitionBinding
    ) : RecyclerView.ViewHolder(recyclerviewDocumentdefinitionBinding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DocumentDefnitionViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.recyclerview_documentdefinition,
            parent,
            false
        )
    )

    interface ViewHolderListener {
        fun onItemClicked(documentDefinition: DocumentDefinition)
    }

    override fun getItemCount(): Int {
        return documentDefinitions.size
    }

    override fun onBindViewHolder(holder: DocumentDefnitionViewHolder, position: Int) {
        holder.recyclerviewDocumentdefinitionBinding.documentDefinition =
            documentDefinitions.get(position)
        holder.recyclerviewDocumentdefinitionBinding.maincard.setOnClickListener {
            listener.onItemClicked(documentDefinitions.get(position))

        }

    }


}