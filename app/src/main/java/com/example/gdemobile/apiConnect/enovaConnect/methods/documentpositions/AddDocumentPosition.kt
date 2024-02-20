package com.example.gdemobile.apiConnect.enovaConnect.methods.documentpositions

import com.example.gdemobile.apiConnect.enovaConnect.methods.interfaces.IConnectEnovaMethod
import com.example.gdemobile.apiConnect.enovaConnect.methods.interfaces.IDto
import com.example.gdemobile.models.Document
import com.example.gdemobile.models.DocumentPosition


//TODO(Adjust later to get cargo by EAN )
class AddDocumentPosition(
    val documentPosition: DocumentPosition,
    val document: Document
) : IConnectEnovaMethod {
    override val methodName: String
        get() = "DodajPozycjeDokumentuPrzyjeciaZewnetrznegoMagazynowego"
    override val methodService: String
        get() = "GdeApi.IDokumentyPrzyjecMagazynowychService, GdeApi"
    override val dto: IDto
        get() = Dto(documentPosition,document.id)

   private class Dto(documentPosition: DocumentPosition, documentId: Int) : IDto
}