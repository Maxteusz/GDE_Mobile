package com.example.gdemobile.apiConnect.enovaConnect

import com.google.gson.annotations.SerializedName

abstract class BaseDto(
    @SerializedName("paginacja")
    val paginationDto: PaginationDto = PaginationDto(0,10)
) : IDto {

    class PaginationDto(
        @SerializedName("Pomin") val skip: Int,
        @SerializedName("Pobierz") val take: Int
    )
}