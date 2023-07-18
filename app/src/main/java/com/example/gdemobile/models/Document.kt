package com.example.gdemobile.models

import com.example.gdemobile.utils.DateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

class Document {

    var contractor: Contractor? = null
    var definition: DocumentDefinition? = null
    var date = LocalDate.now()





}