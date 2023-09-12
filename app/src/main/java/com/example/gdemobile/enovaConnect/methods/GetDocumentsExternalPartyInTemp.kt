package com.example.gdemobile.enovaConnect.methods

import com.example.gdemobile.enovaConnect.RequestDto

class GetDocumentsExternalPartyInTemp : IConnectEnovaMethod {

    override val methodName: String = "PobierzDokumentyPrzyjeciaZewnetrznegoMagazynowegoWBuforze"
    override val methodService: String  = "GdeApi.IDokumentyPrzyjecMagazynowychService, GdeApi"
    override fun getBody(): RequestDto<RequestDto.PaginationDto> {
        val body = RequestDto<RequestDto.PaginationDto>(this)
        return body;

    }


}