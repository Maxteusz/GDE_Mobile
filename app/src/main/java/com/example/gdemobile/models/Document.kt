package com.example.gdemobile.models

import com.example.gdemobile.utils.DateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar

class Document {

    var contractor: Contractor? = null
    var definition: DocumentDefinition? = null
    var date = LocalDateTime.parse(LocalDateTime.now().toString(),DateTimeFormatter.ofPattern(DateFormat.SIMPLE_DATE))

}