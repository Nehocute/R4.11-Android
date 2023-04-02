package com.example.r411.persistance.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.r411.persistance.entity.FormationAptitude

@Dao
interface FormationAptitudeDao {
    @Query("SELECT * FROM formation_aptitude WHERE deleted = 0")
    fun getAll(): List<FormationAptitude>

    @Query("SELECT * FROM formation_aptitude WHERE id IN (:formationAptitudeIds) and deleted = 0")
    fun loadAllByIds(formationAptitudeIds: IntArray): List<FormationAptitude>

    @Insert
    fun insertAll(vararg formationAptitudes: FormationAptitude)
}