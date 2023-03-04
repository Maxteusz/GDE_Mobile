package com.example.gdemobile.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast

class Utils {
    companion object {
        fun showToast(context: Context,message: String)
        {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }

        fun openActivity(context: Context, activity: Activity, finishActivity: Boolean = false) {
            val intent = Intent(context, activity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent)
            if (finishActivity) {
                val activity = context as Activity
                activity.finish()
            }
        }

    }
}