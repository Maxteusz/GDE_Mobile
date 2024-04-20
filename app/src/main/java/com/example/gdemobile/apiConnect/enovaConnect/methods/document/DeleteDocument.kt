package com.example.gdemobile.apiConnect.enovaConnect.methods.document

import com.example.gdemobile.apiConnect.enovaConnect.methods.interfaces.IConnectEnovaMethod
import com.example.gdemobile.apiConnect.enovaConnect.methods.interfaces.IDto
import com.google.gson.annotations.SerializedName

class DeleteDocument (val idDocument: Int) : IConnectEnovaMethod {
    override val methodName: String
        get() ="UsunDokument"
    override val methodService: String
        get() = "APIEnova.Services.IDokumentHandlowyService, APIEnova"
    override val dto: IDto
        get() = Dto(idDocument)

    private class Dto (@SerializedName("idDokumentu") val idDocument : Int) : IDto

}