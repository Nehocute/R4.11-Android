package com.example.r411.persistance.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.r411.persistance.entity.Student
import com.example.r411.persistance.view.StudentDetails

@Dao
interface StudentDao : DataDao<Student> {
    @Query("SELECT * FROM student WHERE deleted = 0")
    override fun getAll(): List<Student>

    @Query("SELECT * FROM student WHERE id IN (:studentIds) and deleted = 0")
    override fun loadAllByIds(studentIds: IntArray): List<Student>

    @Query("SELECT * FROM student WHERE formation_id LIKE :formationId and deleted = 0")
    fun findByFormationId(formationId: Int): LiveData<Student>

    @Insert
    override fun insertAll(vararg students: Student)

    @Upsert
    override fun insertOrUpdate(student: Student)

    @Query("SELECT student.id, student.name, student.phone, " +
            "(SELECT count(evaluation.aptitude_id) FROM evaluation WHERE evaluation.student_id = student.id AND evaluation.status = 4) " +
            "AS aptitudes FROM student WHERE student.deleted = 0 and student.formation_id = :formationId")
    fun studentDetailsView(formationId: Int): LiveData<List<StudentDetails>>
}