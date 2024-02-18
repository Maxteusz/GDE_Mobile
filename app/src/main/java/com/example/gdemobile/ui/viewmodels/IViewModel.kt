package com.example.gdemobile.ui.viewmodels

import com.example.gdemobile.helpers.DocumentType
import com.example.gdemobile.models.Document
import com.example.gdemobile.ui.IStateResponse

interface IViewModel {
    var stateResponse : IStateResponse?
    var documentType : DocumentType?
    var document : Document

}