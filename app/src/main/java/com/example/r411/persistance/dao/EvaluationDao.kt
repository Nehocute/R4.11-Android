package com.example.r411.persistance.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.r411.persistance.entity.Evaluation

@Dao
interface EvaluationDao {
    @Query("SELECT * FROM evaluation WHERE deleted = 0")
    fun getAll(): List<Evaluation>

    @Query("SELECT * FROM evaluation WHERE id IN (:evaluationIds) and deleted = 0")
    fun loadAllByIds(evaluationIds: IntArray): List<Evaluation>

    @Query("SELECT * FROM evaluation WHERE student_id LIKE :studentId and deleted = 0 LIMIT 1")
    fun findByStudentId(studentId: Int): Evaluation

    @Query("SELECT * FROM evaluation WHERE aptitude_id LIKE :aptitudeId LIMIT 1")
    fun findByAptitudeId(aptitudeId: Int): Evaluation

    @Insert
    fun insertAll(vararg evaluations: Evaluation)
}