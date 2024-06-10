package com.example.gdemobile.config

import android.content.Context
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.gdemobile.apiConnect.enovaConnect.daos.Dao
import com.example.gdemobile.apiConnect.enovaConnect.methods.interfaces.IConnectEnovaMethod
import com.example.gdemobile.apiConnect.enovaConnect.methods.interfaces.IDto
import com.example.gdemobile.ui.IStateResponse
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.http.PATCH
import java.io.BufferedReader
import java.io.StringReader
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.io.path.Path
import kotlin.io.path.createFile

class UpdateApp(private val fragment: Fragment) {

val context : Context? = fragment.context
    private val updateStateResponse: IStateResponse = object : IStateResponse {
        override fun onLoading() {

        }

        override suspend fun onError(message: String) {


        }

        override fun onSuccess() {


        }

    }



    suspend fun saveFile() {

      /*  val file = convertToFile().await()
        val filePath = Paths.get(context?.getExternalFilesDir(null).toString() + "/test.apk")

        withContext(Dispatchers.IO) {
            Files.createFile(filePath)
            if (!Files.exists(filePath))
                Files.createFile(filePath)
            Files.write(filePath, file)
        }*/

    withContext(Dispatchers.IO).launch { downloadFile()}



    }


    private suspend fun convertToFile(): Deferred<ByteArray> {

        return fragment.lifecycleScope.async(Dispatchers.IO) {
            val text = downloadFile().toString()
            val decodedBytes = Base64.decode(text, Base64.DEFAULT)
            return@async decodedBytes

        }
    }


    private suspend fun downloadFile(): List<FilePart>? {
        return UpdateDao(updateStateResponse).downloadFileInString("dd")


    }


    private class UpdateDao(stateResult: IStateResponse) : Dao(stateResult) {

        suspend fun downloadFileInString(test: String): List<FilePart>? {
            return requestList<FilePart>(UpdateAppConnection())

        }
    }



    private class UpdateAppConnection : IConnectEnovaMethod {
        override val methodName: String
            get() = "PobierzPlikApk"
        override val methodService: String
            get() = "APIEnova.Services.IAktualizacjaAppServicecs, APIEnova"
        override val dto: IDto = Dto()

        private class Dto : IDto


    }

    class FilePart()
    {
        val fileName : String = ""
        val partIndex : Int = 0;
        val totalParts : Int = 0;
        val content : String = ""
    }
}