package com.example.r411.persistance.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "formation_level")
data class FormationLevel(
    @PrimaryKey val id: Int,
    val name: String,
    val deleted: Boolean
)
