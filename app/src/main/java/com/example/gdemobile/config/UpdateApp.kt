package com.example.gdemobile.config

import android.app.Activity
import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Environment
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.FileProvider

import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.util.jar.Manifest

class UpdateApp(private val context: Context) {

    companion object {
        private const val REQUEST_CODE_STORAGE_PERMISSION = 100
    }

    fun update() {
        if (hasStoragePermission()) {
            downloadAndInstallApk()
        } else {
            requestStoragePermission()
        }
    }

    private fun hasStoragePermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestStoragePermission() {
        ActivityCompat.requestPermissions(
            context as Activity,
            arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
            REQUEST_CODE_STORAGE_PERMISSION
        )
    }

    private fun downloadAndInstallApk() {
        val extStorageDirectory = context.getExternalFilesDir(null)?.absolutePath
        val folder = File(extStorageDirectory ?: return)
        if (!folder.exists()) folder.mkdir()
        val file = File(folder, "ddd.apk")
        try {
            file.createNewFile()
           // downloadFile(file)
        } catch (e: IOException) {
            Log.e("UpdateApp", "IOException: ${e.message}")
        }
    }

     fun downloadFile() {
        try {
           val uri = Uri.parse("https://pdfobject.com/pdf/sample.pdf");

            val request =  DownloadManager.Request(uri);
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);  // Tell on which network you want to download file.
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);  // This will show notification on top when downloading the file.
            request.setTitle("Pobieranie pliku instalacyjnego..."); // Title for notification.
            request.setVisibleInDownloadsUi(true);

            request.setDestinationInExternalFilesDir(context, Environment.getExternalStorageState(), "fff"+".pdf");
            val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
           val file = downloadManager.enqueue(request)
            downloadManager.openDownloadedFile(downloadManager.enqueue(request))
           Log.i("Download Manager", "Download started"+  downloadManager.getUriForDownloadedFile(file))
          // This will start downloading
        }
        catch(_: Exception){

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
}