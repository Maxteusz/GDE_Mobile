package com.example.gdemobile.apiConnect.enovaConnect.methods.documentpositions

import com.example.gdemobile.apiConnect.enovaConnect.methods.interfaces.IConnectEnovaMethod
import com.example.gdemobile.apiConnect.enovaConnect.methods.interfaces.IDto
import com.example.gdemobile.models.DocumentPosition
import com.google.gson.annotations.SerializedName


//TODO(Adjust later to get cargo by EAN )
class AddDocumentPosition(
    val documentPosition: DocumentPosition,
    val documentID : Int
) : IConnectEnovaMethod {
    override val methodName: String
        get() = "DodajPozycjeDokumentu"
    override val methodService: String
        get() = "APIEnova.Services.IPozycjeDokumentuService, APIEnova"
    override val dto: IDto
        get() = Dto(documentPosition, documentID)

   private class Dto(@SerializedName("pozycjaDokumentu") val documentPosition: DocumentPosition, @SerializedName("dokumentID") val documentID : Int) : IDto
}