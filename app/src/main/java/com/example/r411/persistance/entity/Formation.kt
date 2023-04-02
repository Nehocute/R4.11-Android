package com.example.r411.persistance.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity
data class Formation (
    @PrimaryKey val id: Int,
    val name: String,
    @ColumnInfo(name = "level_id") val levelId: Int,
    val deleted: Boolean
)