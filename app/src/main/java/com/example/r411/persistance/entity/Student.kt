package com.example.r411.persistance.entity

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class Student (
    @PrimaryKey val id: Int,
    val name: String,
    @ColumnInfo(name = "formation_id") val formationId: Int,
    val phone: String,
    val deleted: Boolean
)