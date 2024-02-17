package com.example.gdemobile.apiConnect.enovaConnect.methods

import com.example.gdemobile.apiConnect.enovaConnect.IDto
import com.example.gdemobile.models.DocumentPosition


//TODO(Adjust later to get cargo by EAN )
class AddDocumentPosition(
    val documentPosition: DocumentPosition
) : IConnectEnovaMethod {
    override val methodName: String
        get() = "DodajPozycjeDokumentuPrzyjeciaZewnetrznegoMagazynowego"
    override val methodService: String
        get() = "GdeApi.IDokumentyPrzyjecMagazynowychService, GdeApi"
    override val dto: IDto
        get() = Dto(documentPosition)

   private class Dto(documentPosition: DocumentPosition) : IDto
}