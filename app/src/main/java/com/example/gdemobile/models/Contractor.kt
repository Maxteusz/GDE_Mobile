package com.example.gdemobile.models

import com.google.gson.annotations.SerializedName


class Contractor(
    @SerializedName("Id")
    val id: Int,
    @SerializedName("Kod")
    val code: String?,
    @SerializedName("Nazwa")
    val name: String?
)

