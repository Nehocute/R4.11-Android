package com.example.r411.persistance.view

import androidx.room.DatabaseView

@DatabaseView("SELECT session.id, session.date, formation.name AS formationName FROM session " +
        "JOIN formation ON session.formation_id = formation.id " +
        "WHERE session.deleted = 0 ORDER BY session.date", viewName = "formation_details")
data class SessionDetails(
    val id: Int,
    val date: String,
    val formationName: String
)
