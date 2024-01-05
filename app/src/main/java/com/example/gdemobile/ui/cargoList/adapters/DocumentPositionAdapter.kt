package com.example.gdemobile.ui.cargoList.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.gdemobile.R
import com.example.gdemobile.databinding.RecyclerviewCargoBinding
import com.example.gdemobile.models.Document
import com.example.gdemobile.models.DocumentPosition
import com.example.gdemobile.ui.cargoList.InssuingCargoListViewModel
import com.example.gdemobile.ui.cargoList.adapters.DocumentPositionAdapter.CargoViewHolder

class DocumentPositionAdapter(
    private var cargos: MutableList<DocumentPosition>,
    private val listener: DeleteCargoViewHolderListener,
    private val listnerDetailDocumentPostion : DetailCargoViewHolderListener)
    : RecyclerView.Adapter<CargoViewHolder>() {


    inner class CargoViewHolder(
        val recyclerviewCargoHolder: RecyclerviewCargoBinding

    ) : RecyclerView.ViewHolder(recyclerviewCargoHolder.root)

    interface DeleteCargoViewHolderListener {
        fun onDeleteDocumentPositionItemClicked(idDocumentPostion: Int)

    }
    interface DetailCargoViewHolderListener {
        fun onOpenDetailDocumentPostion(documentPosition: DocumentPosition)

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CargoViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.recyclerview_cargo,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: CargoViewHolder, position: Int) {
        holder.recyclerviewCargoHolder.cargo = cargos.get(position)
        holder.itemView.animate().alpha(1.0f).setDuration(3000).start()
        holder.recyclerviewCargoHolder.deleteImage.setOnClickListener {
            listener.onDeleteDocumentPositionItemClicked(cargos.get(position).id)

        }
        holder.recyclerviewCargoHolder.maincard.setOnClickListener {
            listnerDetailDocumentPostion.onOpenDetailDocumentPostion(cargos[position])

        }


    }




    override fun getItemCount(): Int {
        return cargos.size
    }


}


