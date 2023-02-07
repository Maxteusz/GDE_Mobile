package com.example.gdemobile.ui.menu

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel

class MenuView : ViewModel() {
    fun openActivity (context : Context, activity: Activity, view : View?)
    {
        val intent = Intent(context, activity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent)

    }
}