package com.example.gdemobile.config

import android.app.Activity
import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Environment
import android.util.Base64
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.FileProvider
import com.example.gdemobile.apiConnect.enovaConnect.daos.Dao
import com.example.gdemobile.apiConnect.enovaConnect.methods.interfaces.IConnectEnovaMethod
import com.example.gdemobile.apiConnect.enovaConnect.methods.interfaces.IDto
import com.example.gdemobile.ui.IStateResponse

import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.util.jar.Manifest

class UpdateApp(private val context: Context)   {



    private suspend fun downloadFileInString(test: String) : String? {
        return requestObject<String>(UpdateAppConnection())

    }

    private val updateStateResponse  = object : IStateResponse
    {
        override fun onLoading() {
            TODO("Not yet implemented")
        }

        override suspend fun onError(message: String) {
            TODO("Not yet implemented")
        }

        override fun onSuccess() {
            TODO("Not yet implemented")
        }

    }
    fun downloadFile() {
        try {
            val uri = Uri.parse("https://pdfobject.com/pdf/sample.pdf");
            Base64.decode("ddd", Base64.DEFAULT)
            val request = DownloadManager.Request(uri);
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);  // Tell on which network you want to download file.
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);  // This will show notification on top when downloading the file.
            request.setTitle("Pobieranie pliku instalacyjnego..."); // Title for notification.
            request.setVisibleInDownloadsUi(true);

            request.setDestinationInExternalFilesDir(
                context,
                Environment.getExternalStorageState(),
                "fff" + ".pdf"
            );
            val downloadManager =
                context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            val file = downloadManager.enqueue(request)
            downloadManager.openDownloadedFile(downloadManager.enqueue(request))
            Log.i(
                "Download Manager",
                "Download started" + downloadManager.getUriForDownloadedFile(file)
            )
            // This will start downloading
        } catch (_: Exception) {

        }
    }

    private fun installApk(file: File) {
        val apkUri = FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",
            file
        )
        val intent = Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(apkUri, "application/vnd.android.package-archive")
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        context.startActivity(intent)
    }

    private class UpdateDao(stateResult : IStateResponse) : Dao(stateResult)
    {

    }
    private class UpdateAppConnection : IConnectEnovaMethod {
        override val methodName: String
            get() = "PobierzPlikApk"
        override val methodService: String
            get() = "APIEnova.Services.IAktualizacjaAppServicecs, APIEnova"
        override val dto: IDto = Dto()

        private class Dto : IDto


    }
}