package com.example.r411.persistance.dao

import androidx.room.*
import com.example.r411.persistance.entity.FormationSkill

@Dao
interface FormationSkillDao : DataDao<FormationSkill> {
    @Query("SELECT * FROM formation_skill WHERE deleted = 0")
    override fun getAll(): List<FormationSkill>

    @Query("SELECT * FROM formation_skill WHERE id IN (:formationSkillIds) and deleted = 0")
    override fun loadAllByIds(formationSkillIds: IntArray): List<FormationSkill>

    @Query("SELECT * FROM formation_skill WHERE id = :id LIMIT 1")
    fun getById(id: Int): FormationSkill

    @Insert
    override fun insertAll(vararg formationSkills: FormationSkill)

    @Upsert
    override fun insertOrUpdate(formationSkill: FormationSkill)
}