package com.example.gdemobile.ui.viewmodels

import com.example.gdemobile.helpers.DocumentType
import com.example.gdemobile.ui.IStateResponse

interface IViewModel {
    val stateResponse : IStateResponse?
    val documentType : DocumentType?
}