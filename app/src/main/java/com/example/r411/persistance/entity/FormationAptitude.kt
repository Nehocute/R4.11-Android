package com.example.r411.persistance.entity

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class FormationAptitude(
    @PrimaryKey val id: Int,
    val name: String,
    @ColumnInfo(name = "skill_id") val skillId: Int,
    val deleted: Boolean
)
