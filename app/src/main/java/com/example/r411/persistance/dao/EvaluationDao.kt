package com.example.r411.persistance.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.example.r411.persistance.entity.Evaluation

@Dao
interface EvaluationDao : DataDao<Evaluation> {
    @Query("SELECT * FROM evaluation")
    override fun getAll(): List<Evaluation>

    @Query("SELECT * FROM evaluation WHERE id IN (:evaluationIds)")
    override fun loadAllByIds(evaluationIds: IntArray): List<Evaluation>

    @Query("SELECT * FROM evaluation WHERE student_id LIKE :studentId LIMIT 1")
    fun findByStudentId(studentId: Int): Evaluation

    @Query("SELECT * FROM evaluation WHERE aptitude_id LIKE :aptitudeId LIMIT 1")
    fun findByAptitudeId(aptitudeId: Int): Evaluation

    @Insert
    override fun insertAll(vararg evaluations: Evaluation)

    @Upsert
    override fun insertOrUpdate(evaluation: Evaluation)
}