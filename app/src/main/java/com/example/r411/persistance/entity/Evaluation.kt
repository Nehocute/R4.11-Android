package com.example.r411.persistance.entity

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class Evaluation(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "aptitude_id") val aptitudeId: Int,
    @ColumnInfo(name = "student_id") val studentId: Int,
    val comment: String,
)
