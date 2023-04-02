package com.example.r411.persistance.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FormationLevel(
    @PrimaryKey val id: Int,
    val name: String,
    val deleted: Boolean
)
