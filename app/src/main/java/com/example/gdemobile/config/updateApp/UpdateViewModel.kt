package com.example.gdemobile.config.updateApp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
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
import com.example.gdemobile.utils.CustomToast
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import java.nio.file.Files
import java.nio.file.Paths

class UpdateViewModel(private val fragment: Fragment) {

    //TODO() Poprawić powiadomienia
    val context: Context = fragment.requireActivity()
    private val updateStateResponse: IStateResponse = object : IStateResponse {
        override fun onLoading() =
          showNotifyAboutStartingDownload()


        override suspend fun onError(message: String) =
           showNotifyAboutErrorDownload()

        override fun onSuccess() =
            showNotifyAboutFinishedDownload()

        private fun showNotifyAboutStartingDownload() {
                val builder = NotificationCompat.Builder(context, CHANNEL_ID.toString())
                    .setContentTitle("Pobieranie pliku")
                    .setContentText("Trwa pobieranie pliku instalacyjnego")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setColorized(true)
                    .setAutoCancel(true)
                    .setSmallIcon(R.drawable.cargo_icon)


                val notificationManager =
                    context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.notify(NOTIFICATION_ID, builder.build())
        }
    }

    private fun showNotifyAboutErrorDownload() {
        val builder = NotificationCompat.Builder(context, CHANNEL_ID.toString())
            .setContentTitle("Błąd pobierania pliku")
            .setContentText("Pobiernie pliku nie powiodło się")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setColorized(true)
            .setAutoCancel(true)
            .setSmallIcon(R.drawable.error_icon)

        val notificationManager =
            context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(NOTIFICATION_ID, builder.build())
    }


    private fun showNotifyAboutFinishedDownload() {
        val builder = NotificationCompat.Builder(context, CHANNEL_ID.toString())
            .setContentTitle("Plik pobrany")
            .setContentText("Zakończono pobieranie pliku instalacyjnego")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setSmallIcon(R.drawable.cargo_icon)

        val notificationManager =
            context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(NOTIFICATION_ID, builder.build())

}

    suspend fun update() {
        val file = convertToFile().await()
        val filePath =
            Paths.get(context.getExternalFilesDir(null).toString() + "/test.apk")

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