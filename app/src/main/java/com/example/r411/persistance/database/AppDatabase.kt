package com.example.r411.persistance.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.r411.persistance.dao.*
import com.example.r411.persistance.entity.*

@Database(entities = arrayOf(
    Session::class,
    Student::class,
    Formation::class,
    FormationLevel::class,
    FormationSkill::class,
    FormationAptitude::class,
    Evaluation::class
), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun sessionDao(): SessionDao
    abstract fun studentDao(): StudentDao
    abstract fun formationDao(): FormationDao
    abstract fun formationLevelDao(): FormationLevelDao
    abstract fun formationSkillDao(): FormationSkillDao
    abstract fun formationAptitudeDao(): FormationAptitudeDao
    abstract fun evaluationDao(): EvaluationDao

    companion object {
        private const val DATABASE_NAME = "r411.db"
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                ).build()
            }
            return INSTANCE!!
        }
    }
}