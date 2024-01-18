package com.example.gdemobile.ui.menu

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.gdemobile.R
import com.example.gdemobile.config.Mode

class MenuView : ViewModel() {


    fun openActivity (context : Context, targetActivity: Activity, view : View?)
    {
        val intent = Intent(context, targetActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        when (view?.id)
        {
            R.id.receiving_button -> Mode.mode = Mode.Modes.Receiving
            //R.id.issuing_button -> Mode.mode = Mode.Modes.Issuing
        }
        context.startActivity(intent)


    }
}