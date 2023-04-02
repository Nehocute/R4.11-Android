package com.example.r411.persistance.dao

import androidx.room.*
import com.example.r411.persistance.entity.Evaluation
import com.example.r411.persistance.entity.FormationAptitude

@Dao
interface FormationAptitudeDao : DataDao<FormationAptitude> {
    @Query("SELECT * FROM formation_aptitude WHERE deleted = 0")
    override fun getAll(): List<FormationAptitude>

    @Query("SELECT * FROM formation_aptitude WHERE id IN (:formationAptitudeIds) and deleted = 0")
    override fun loadAllByIds(formationAptitudeIds: IntArray): List<FormationAptitude>

    @Insert
    override fun insertAll(vararg formationAptitudes: FormationAptitude)

    @Upsert
    override fun insertOrUpdate(formationAptitude: FormationAptitude)
}