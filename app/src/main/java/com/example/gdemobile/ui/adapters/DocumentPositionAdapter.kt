package com.example.gdemobile.ui.adapters


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gdemobile.R
import com.example.gdemobile.databinding.CardDocumentpositionBinding
import com.example.gdemobile.models.DocumentPosition

class DocumentPositionAdapter(
    private val cargos: List<DocumentPosition>,
    private val deleteListener: DeleteCargoViewHolderListener,
    private val detailListener: DetailCargoViewHolderListener
) : RecyclerView.Adapter<DocumentPositionAdapter.CargoViewHolder>() {

    inner class CargoViewHolder(
        private val binding: CardDocumentpositionBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.deleteImage.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    deleteListener.onDeleteDocumentPositionItemClicked(cargos[position].id)
                }
            }
            binding.maincard.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    detailListener.onOpenDetailDocumentPosition(cargos[position])
                }
            }
        }


        @SuppressLint("ResourceAsColor")
        fun bind(documentPosition: DocumentPosition) {
            binding.documentposition = documentPosition
            if (documentPosition.valuePerUnit.value == 0.0) {
              val colorDarkRed =  itemView.context.resources.getColor(R.color.darkRed)
                binding.maincard.setCardBackgroundColor(colorDarkRed)

            }
            binding.executePendingBindings()
        }
    }

    interface DeleteCargoViewHolderListener {
        fun onDeleteDocumentPositionItemClicked(idDocumentPosition: Int)
    }

    interface DetailCargoViewHolderListener {
        fun onOpenDetailDocumentPosition(documentPosition: DocumentPosition)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CargoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CardDocumentpositionBinding.inflate(inflater, parent, false)
        return CargoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CargoViewHolder, position: Int) =
        holder.bind(cargos[position])




    override fun getItemCount(): Int = cargos.size
}