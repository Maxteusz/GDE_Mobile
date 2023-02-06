package com.example.gdemobile.ui.cargoList

import androidx.lifecycle.ViewModel
import com.example.gdemobile.models.Contractor

class CargoListView : ViewModel() {
companion object {
    fun listContractor(): List<Contractor> {
        var list = listOf<Contractor>(
            Contractor("ffdfd"),
            Contractor("vcvbb"),
            Contractor("vcvbb"),
            Contractor("vcvbb"),
            Contractor("vcvbb"),
            Contractor("vcvbb"),
            Contractor("vcvbb"),
            Contractor("vcvbb"),
        )
        return list;
    }
}
}