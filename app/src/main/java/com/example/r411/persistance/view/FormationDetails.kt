package com.example.r411.persistance.view

import androidx.room.DatabaseView

@DatabaseView("SELECT formation.id, formation.name, formation_level.name AS levelName FROM formation " +
        "JOIN formation_level ON formation.level_id = formation_level.id " +
        "WHERE formation.deleted = 0 ORDER BY formation.id", viewName = "formation_details")
data class FormationDetails(
    val id: Int,
    val name: String,
    val levelName: String
)
