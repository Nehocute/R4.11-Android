package com.example.r411.persistance.entity

import androidx.room.*

@Entity(tableName = "formation_aptitude",
    foreignKeys = [ForeignKey(
        entity = FormationSkill::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("skill_id"),
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = ["skill_id"], unique = false)]
)
data class FormationAptitude(
    @PrimaryKey val id: Int,
    val name: String,
    @ColumnInfo(name = "skill_id") val skillId: Int,
    val deleted: Boolean
)
