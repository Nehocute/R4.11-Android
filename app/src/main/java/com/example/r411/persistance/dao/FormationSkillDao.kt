package com.example.r411.persistance.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.r411.persistance.entity.FormationSkill

@Dao
interface FormationSkillDao {
    @Query("SELECT * FROM formation_skill WHERE deleted = 0")
    fun getAll(): List<FormationSkill>

    @Query("SELECT * FROM formation_skill WHERE id IN (:formationSkillIds) and deleted = 0")
    fun loadAllByIds(formationSkillIds: IntArray): List<FormationSkill>

    @Query("SELECT * FROM formation_skill WHERE id = :id LIMIT 1")
    fun getById(id: Int): FormationSkill

    @Insert
    fun insertAll(vararg formationSkills: FormationSkill)
}