package com.example.gdemobile.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gdemobile.databinding.CardDocumentBinding
import com.example.gdemobile.models.Document

class DocumentsAdapter(
    private val documents: List<Document>,
    private val listener: CustomViewHolderListener
) :
    RecyclerView.Adapter<DocumentsAdapter.DocumentsViewHolder>() {

    interface CustomViewHolderListener {
        fun onCustomItemClicked(document: Document)
    }

    inner class DocumentsViewHolder(val binding: CardDocumentBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DocumentsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CardDocumentBinding.inflate(inflater, parent, false)
        return DocumentsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DocumentsViewHolder, position: Int) {
        val document = documents[position]
        holder.binding.document = document
        holder.binding.maincard.setOnClickListener {
            listener.onCustomItemClicked(document)
        }
        if (document.contractor?.code.isNullOrBlank())
            holder.binding.contractorTextview.visibility = View.GONE
    }

    override fun getItemCount(): Int {
        return documents.size
    }
}
