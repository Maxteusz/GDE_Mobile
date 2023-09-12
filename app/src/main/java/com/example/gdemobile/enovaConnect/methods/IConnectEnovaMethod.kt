package com.example.gdemobile.enovaConnect.methods

import com.example.gdemobile.enovaConnect.RequestDto

interface IConnectEnovaMethod {

    fun getBody() : RequestDto<RequestDto.PaginationDto>
    val methodName : String
    val methodService : String

}