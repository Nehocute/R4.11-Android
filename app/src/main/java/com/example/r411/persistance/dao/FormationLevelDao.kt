package com.example.r411.persistance.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.r411.persistance.entity.FormationLevel

@Dao
interface FormationLevelDao {
    @Query("SELECT * FROM formation_level WHERE deleted = 0")
    fun getAll(): List<FormationLevel>

    @Query("SELECT * FROM formation_level WHERE id IN (:formationLevelIds) and deleted = 0")
    fun loadAllByIds(formationLevelIds: IntArray): List<FormationLevel>

    @Query("SELECT * FROM formation_level WHERE id = :id LIMIT 1")
    fun getById(id: Int): FormationLevel

    @Insert
    fun insertAll(vararg formationLevels: FormationLevel)
}