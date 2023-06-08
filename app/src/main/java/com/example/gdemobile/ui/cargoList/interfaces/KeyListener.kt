package com.example.gdemobile.ui.cargoList.interfaces

import android.view.KeyEvent

open interface KeyListener {
    fun onKeyDown(keyCode: Int, event: KeyEvent?) : Boolean
}