package com.example.gdemobile.config

class Mode {
    companion object
    {
        var mode: Modes? = null
    }
    enum class Modes {
        Issuing,
        Receiving,
    }
}