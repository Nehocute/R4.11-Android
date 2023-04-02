package com.example.r411.ui.home

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Looper
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.Operation
import androidx.work.WorkManager
import com.example.r411.worker.DataDownloadWorker

class HomeViewModel : ViewModel() {

    private var databaseSynced = false
    fun syncDatabase(context: Context) {
        /*
        if (databaseSynced || WorkManager.getInstance(context.applicationContext)
                .getWorkInfosForUniqueWork("sync_data").get().size > 0) {
            return
        }
        */
        val op = WorkManager.getInstance(context.applicationContext)
            .beginUniqueWork(
                "sync_data",
                ExistingWorkPolicy.KEEP,
                OneTimeWorkRequest.from(DataDownloadWorker::class.java)
            ).enqueue()


        op.result.addListener({
            if(op.result.isDone) {
                Looper.prepare()
                Toast.makeText(context, "Database synced", Toast.LENGTH_SHORT).show()
            } else {
                Looper.prepare()
                Toast.makeText(context, "Database failed to sync", Toast.LENGTH_SHORT).show()
            }
        }, {})
    }

}