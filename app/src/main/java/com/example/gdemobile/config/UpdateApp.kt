package com.example.gdemobile.config

import android.os.Environment
import android.util.Base64
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.gdemobile.apiConnect.enovaConnect.daos.Dao
import com.example.gdemobile.apiConnect.enovaConnect.methods.interfaces.IConnectEnovaMethod
import com.example.gdemobile.apiConnect.enovaConnect.methods.interfaces.IDto
import com.example.gdemobile.ui.IStateResponse
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.nio.file.Files
import kotlin.io.path.Path

class UpdateApp(private val fragment: Fragment) {


    private val updateStateResponse: IStateResponse = object : IStateResponse {
        override fun onLoading() {

        }

        override suspend fun onError(message: String) {

        }

        override fun onSuccess() {

        }

    }
    suspend fun saveFile()
    {

        var pathh = Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_DOWNLOADS);
        val file = convertToFile()
        withContext(Dispatchers.IO) {
            Files.write(pathh, file)
        }

    }

    private suspend fun convertToFile(): ByteArray {

        fragment.viewLifecycleOwner.lifecycleScope.launch {
            val text = downloadFile()
            if (text != null) {
                Log.i("Pobrany Text", text)
                Base64.decode(text, Base64.DEFAULT)
            }
        }
        return ByteArray(0)

    }

    private suspend fun downloadFile() : String? {
            return UpdateDao(updateStateResponse).downloadFileInString("dd")



    }


    private class UpdateDao(stateResult: IStateResponse) : Dao(stateResult) {
        suspend fun downloadFileInString(test: String): String? {
            return requestObject<String>(UpdateAppConnection())

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
}