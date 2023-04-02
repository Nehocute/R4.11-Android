package com.example.r411.persistance.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Session(
    @PrimaryKey val id: Int,
    val date: String,
    @ColumnInfo(name = "formation_id") val formationId: Int,
    val deleted: Boolean
)
