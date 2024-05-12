package com.example.gdemobile.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.gdemobile.R
import com.example.gdemobile.databinding.CustomDropdownListBinding


class ListViewAdapter(
     context: Context,
    private val objects: MutableList<String>
) :
    ArrayAdapter<String>(context, R.layout.custom_dropdown_list, objects) {


    override fun getCount(): Int {
        return objects.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding: CustomDropdownListBinding = if (convertView == null) {
            val inflater = LayoutInflater.from(context)
            val itemBinding =
                CustomDropdownListBinding.inflate(inflater, parent, false)
            itemBinding.root.tag = itemBinding
            itemBinding.text = objects[position]
            itemBinding
        } else {
            convertView.tag as CustomDropdownListBinding
        }


        return binding.root
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getView(position, convertView, parent)
    }
}






