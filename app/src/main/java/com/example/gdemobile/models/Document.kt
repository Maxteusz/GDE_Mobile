package com.example.gdemobile.models

class Document {

    private var  _contractor : Contractor = Contractor()
    private lateinit var _definition : String
    val contractor : Contractor get() = _contractor

    fun setContractor(contractor: Contractor)
    {
        _contractor = contractor
    }
}