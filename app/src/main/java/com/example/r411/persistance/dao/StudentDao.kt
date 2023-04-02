package com.example.r411.persistance.dao

import androidx.room.*
import com.example.r411.persistance.entity.Student

@Dao
interface StudentDao : DataDao<Student> {
    @Query("SELECT * FROM student WHERE deleted = 0")
    override fun getAll(): List<Student>

    @Query("SELECT * FROM student WHERE id IN (:studentIds) and deleted = 0")
    override fun loadAllByIds(studentIds: IntArray): List<Student>

    @Query("SELECT * FROM student WHERE formation_id LIKE :formationId and deleted = 0")
    fun findByFormationId(formationId: Int): List<Student>

    @Insert
    override fun insertAll(vararg students: Student)

    @Upsert
    override fun insertOrUpdate(student: Student)
}