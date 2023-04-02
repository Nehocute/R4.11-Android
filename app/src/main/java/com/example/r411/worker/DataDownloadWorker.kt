package com.example.r411.worker

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.r411.persistance.dao.DataDao
import com.example.r411.persistance.database.AppDatabase
import com.example.r411.persistance.entity.*
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class DataDownloadWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    companion object {
        const val API = "https://dev-restandroid.users.info.unicaen.fr/REST/"
    }

    override fun doWork(): Result {
        val database = AppDatabase.getInstance(applicationContext)
        downloadData("level", database.formationLevelDao()) {
            FormationLevel(
                it.getInt("id"),
                it.getString("name"),
                it.getBoolean("deleted"))
        }
        downloadData("skill", database.formationSkillDao()) {
            FormationSkill(
                it.getInt("id"),
                it.getString("name"),
                it.getInt("levelId"),
                it.getBoolean("deleted"))
        }
        downloadData("aptitude", database.formationAptitudeDao()) {
            FormationAptitude(
                it.getInt("id"),
                it.getString("name"),
                it.getInt("skillId"),
                it.getBoolean("deleted"))
        }
        downloadData("formation", database.formationDao()) {
            Formation(
                it.getInt("id"),
                it.getString("name"),
                it.getInt("levelId"),
                it.getBoolean("deleted"))
        }
        downloadData("student", database.studentDao()) {
            Student(
                it.getInt("id"),
                it.getString("name"),
                it.getInt("formationId"),
                it.getString("phone"),
                it.getBoolean("deleted"))
        }
        return Result.success()
    }

    private fun <T> downloadData(url: String, dao: DataDao<T>, itemParser: (JSONObject) -> T) {
        val target = URL(API + url)
        with(target.openConnection() as HttpURLConnection) {
            println("\nSent 'GET' request to URL : $target; Response Code : $responseCode")

            val stringBuilder = StringBuilder()
            inputStream.bufferedReader().use {
                it.lines().forEach { line ->
                    stringBuilder.append(line)
                }
            }

            val data = JSONArray(stringBuilder.toString())

            //println(data.toString(3))

            for (i in 0 until data.length()) {
                val item = data.getJSONObject(i)
                try {
                    dao.insertOrUpdate(itemParser(item))
                } catch (e: SQLiteConstraintException) {
                    println("Item ${item.getInt("id")} skipped because of foreign key constraint")
                }
            }
            // only shows the number of rows inserted in the database that are given by the DAO
            // this may vary from the number of rows in the JSON file because of DAO filtering
            println("${dao.getAll().count()} rows inserted in ${dao::class.simpleName}")
        }
    }
}