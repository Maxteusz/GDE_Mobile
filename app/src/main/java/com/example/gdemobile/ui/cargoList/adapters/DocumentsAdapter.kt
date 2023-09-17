package com.example.gdemobile.ui.cargoList.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.gdemobile.R
import com.example.gdemobile.databinding.RecyclerviewDocumentBinding
import com.example.gdemobile.models.Document

class DocumentsAdapter(private val documents : List<Document>, private val listener: CustomViewHolderListener)
    :  RecyclerView.Adapter<DocumentsAdapter.DocumentsViewHolder>() {

    interface CustomViewHolderListener{
        fun onCustomItemClicked(x: Document)
    }

    inner class DocumentsViewHolder(
        val recyclerviewDocumentBinding: RecyclerviewDocumentBinding
    ) : RecyclerView.ViewHolder(recyclerviewDocumentBinding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DocumentsViewHolder = DocumentsViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.recyclerview_document,
            parent,
            false
        )
    )



    override fun onBindViewHolder(holder: DocumentsViewHolder, position: Int) {
        holder.recyclerviewDocumentBinding.document = documents.get(position)
        holder.recyclerviewDocumentBinding.maincard.setOnClickListener {
            listener.onCustomItemClicked(documents.get(position))
        }
    }



    override fun getItemCount(): Int {
      return documents.size
    }
}