package com.example.r411.persistance.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.r411.persistance.entity.Formation

@Dao
interface FormationDao {
    @Query("SELECT * FROM formation WHERE deleted = 0")
    fun getAll(): List<Formation>

    @Query("SELECT * FROM formation WHERE id IN (:formationIds) and deleted = 0")
    fun loadAllByIds(formationIds: IntArray): List<Formation>

    @Query("SELECT * FROM formation WHERE name LIKE :name LIMIT 1")
    fun findByName(name: String): Formation

    @Query("SELECT * FROM formation WHERE level_id LIKE :levelId and deleted = 0")
    fun findByLevelId(levelId: Int): List<Formation>

    @Insert
    fun insertAll(vararg formations: Formation)
}