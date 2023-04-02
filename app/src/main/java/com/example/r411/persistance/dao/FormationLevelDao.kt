package com.example.r411.persistance.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.example.r411.persistance.entity.FormationLevel

@Dao
interface FormationLevelDao : DataDao<FormationLevel> {
    @Query("SELECT * FROM formation_level WHERE deleted = 0")
    override fun getAll(): List<FormationLevel>

    @Query("SELECT * FROM formation_level WHERE id IN (:formationLevelIds) and deleted = 0")
    override fun loadAllByIds(formationLevelIds: IntArray): List<FormationLevel>

    @Query("SELECT * FROM formation_level WHERE id = :id LIMIT 1")
    fun getById(id: Int): FormationLevel

    @Insert
    override fun insertAll(vararg formationLevels: FormationLevel)

    @Upsert
    override fun insertOrUpdate(formationLevel: FormationLevel)
}