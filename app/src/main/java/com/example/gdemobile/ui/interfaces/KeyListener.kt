package com.example.gdemobile.ui.interfaces

import android.view.KeyEvent

 interface KeyListener {
    fun onKeyDown(keyCode: Int, event: KeyEvent?) : Boolean
}