package com.example.gdemobile.config

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Environment
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
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

    private fun downloadFile(file: File) {
        try {
            val outputStream = FileOutputStream(file)
            val url = URL("http://${Config.ip}/mobileAppApk")
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.connect()

            val inputStream = connection.inputStream
            val buffer = ByteArray(1024)
            var len: Int
            while (inputStream.read(buffer).also { len = it } > 0) {
                outputStream.write(buffer, 0, len)
            }
            outputStream.close()

            installApk(file)
        } catch (e: Exception) {
            Log.e("UpdateApp", "Exception in downloadFile: ${e.message}")
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