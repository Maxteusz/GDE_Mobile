package com.example.gdemobile.config.updateApp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.pm.PackageManager
import android.os.Build
import android.util.Base64
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.gdemobile.BuildConfig
import com.example.gdemobile.R
import com.example.gdemobile.ui.IStateResponse
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import java.nio.file.Files
import java.nio.file.Paths

class UpdateViewModel(private val fragment: Fragment) {

    //TODO() Poprawić powiadomienia
    val context: Context? = fragment.context
    private val updateStateResponse: IStateResponse = object : IStateResponse {
        override fun onLoading() {

            if(context != null) {
                createNotificationChannel()

                val builder = NotificationCompat.Builder(context, CHANNEL_ID.toString())
                    .setContentTitle("textTitle")
                    .setContentText("textContent")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)

                val notificationManager =
                    context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.notify(NOTIFICATION_ID, builder.build())
            }


        }

        override suspend fun onError(message: String) {



        }

        override fun onSuccess() {



        }

    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is not in the Support Library.
        val name = Companion.NOTIFICACTION_NAME

        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(CHANNEL_ID.toString(), name, importance).apply {
            description = "hghg"
        }
        // Register the channel with the system.
        val notificationManager: NotificationManager =
            context?.getSystemService(NOTIFICATION_SERVICE)  as NotificationManager

        notificationManager.createNotificationChannel(channel)

    }


    suspend fun update() {
        val file = convertToFile().await()
        val filePath = Paths.get(context?.getExternalFilesDir(null).toString() + "/test.apk")
        // val filePath = Paths.get(Environment.DIRECTORY_DOWNLOADS + "/test.apk")

        withContext(Dispatchers.IO) {
            if (!Files.exists(filePath))
                Files.createFile(filePath)
            Files.write(filePath, file)
        }

    }


    private suspend fun convertToFile(): Deferred<ByteArray> =
        fragment.lifecycleScope.async(Dispatchers.IO) {
            val text = downloadFile().toString()
            val decodedBytes = Base64.decode(text, Base64.DEFAULT)
            return@async decodedBytes

        }


    private suspend fun downloadFile(): String? =
        UpdateDao(updateStateResponse).downloadFileInString(BuildConfig.VERSION_CODE.toString())

    companion object {
        private const val NOTIFICATION_ID = 101
        private const val NOTIFICACTION_NAME = "Powiadomienia o stanie aktualizacji"
        private const val CHANNEL_ID = 101
    }


}