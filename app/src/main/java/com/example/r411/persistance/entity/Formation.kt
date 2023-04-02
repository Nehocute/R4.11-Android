package com.example.r411.persistance.entity

import androidx.room.*

@Entity(tableName = "formation",
    foreignKeys = [ForeignKey(
        entity = FormationLevel::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("level_id"),
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = ["level_id"], unique = false)]
)
data class Formation (
    @PrimaryKey val id: Int,
    val name: String,
    @ColumnInfo(name = "level_id") val levelId: Int,
    val deleted: Boolean
)