package com.example.r411.persistance.view

import androidx.room.DatabaseView

@DatabaseView("SELECT student.id, student.name, student.phone, (SELECT count(evaluation.aptitude_id) FROM evaluation WHERE evaluation.student_id = student.id AND evaluation.status = 4) AS aptitudes FROM student WHERE student.deleted = 0 and student.formation_id = :formationId", viewName = "formation_details")
data class StudentDetails(
    val id: Int,
    val name: String,
    val phone: String,
    val aptitudes: Int
)
