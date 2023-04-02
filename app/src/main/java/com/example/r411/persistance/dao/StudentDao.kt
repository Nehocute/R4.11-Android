package com.example.r411.persistance.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.r411.persistance.entity.Student

@Dao
interface StudentDao {
    @Query("SELECT * FROM student WHERE deleted = 0")
    fun getAll(): List<Student>

    @Query("SELECT * FROM student WHERE id IN (:studentIds) and deleted = 0")
    fun loadAllByIds(studentIds: IntArray): List<Student>

    @Query("SELECT * FROM student WHERE formation_id LIKE :formationId and deleted = 0")
    fun findByFormationId(formationId: Int): List<Student>

    @Query("SELECT * FROM student WHERE email LIKE :email LIMIT 1")
    fun findByEmail(email: String): Student

    @Insert
    fun insertAll(vararg students: Student)
}