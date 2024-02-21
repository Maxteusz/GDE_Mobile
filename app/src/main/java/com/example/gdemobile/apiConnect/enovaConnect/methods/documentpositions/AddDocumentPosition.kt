package com.example.gdemobile.apiConnect.enovaConnect.methods.documentpositions

import com.example.gdemobile.apiConnect.enovaConnect.methods.interfaces.IConnectEnovaMethod
import com.example.gdemobile.apiConnect.enovaConnect.methods.interfaces.IDto
import com.example.gdemobile.models.DocumentPosition


//TODO(Adjust later to get cargo by EAN )
class AddDocumentPosition(
    val documentPosition: DocumentPosition,
) : IConnectEnovaMethod {
    override val methodName: String
        get() = "DodajPozycjeDokumentu"
    override val methodService: String
        get() = "APIEnova.Services.IPozycjeDokumentuService, APIEnova"
    override val dto: IDto
        get() = Dto(documentPosition)

   private class Dto(documentPosition: DocumentPosition) : IDto
}