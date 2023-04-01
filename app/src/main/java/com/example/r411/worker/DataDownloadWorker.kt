package com.example.r411.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import okhttp3.*
import java.io.IOException

class DataDownloadWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    private val client = OkHttpClient()

    override fun doWork(): Result {
        downloadData()
        return Result.success()
    }

    private fun downloadData(): Result {
        // TODO : make it download from API
        val request = Request.Builder()
            .url("https://dev-restandroid.users.info.unicaen.fr/REST/formation/")
            .build()

        var response : Response? = null
        val result : String?
        try {
            response = client.newCall(request).execute()
            result = response?.body()?.string()
        } catch (_:IOException) {
            return Result.failure()
        } finally {
            response?.close()
        }
        return Result.success()
    }
}