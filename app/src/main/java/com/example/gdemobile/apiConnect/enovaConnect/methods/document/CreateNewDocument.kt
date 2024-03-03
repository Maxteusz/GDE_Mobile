package com.example.gdemobile.apiConnect.enovaConnect.methods.document

import com.example.gdemobile.apiConnect.enovaConnect.methods.interfaces.IConnectEnovaMethod
import com.example.gdemobile.apiConnect.enovaConnect.methods.interfaces.IDto
import com.example.gdemobile.models.Document
import com.google.gson.annotations.SerializedName

class CreateNewDocument(val document : Document
) : IConnectEnovaMethod {
    override val methodName: String
        get() = "UtworzDokument"
    override val methodService: String
        get() = "APIEnova.Services.IDokumentHandlowyService, APIEnova"
    override val dto: IDto
        get() = Dto(document)

    private class Dto(@SerializedName("dto") val document : Document) :  IDto


}


