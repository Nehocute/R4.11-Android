package com.example.r411.persistance.entity

import androidx.room.*

@Entity(tableName = "student",
    foreignKeys = [ForeignKey(
        entity = Formation::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("formation_id"),
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = ["formation_id"], unique = false)]
)
data class Student (
    @PrimaryKey val id: Int,
    val name: String,
    @ColumnInfo(name = "formation_id") val formationId: Int,
    val phone: String,
    val deleted: Boolean
)