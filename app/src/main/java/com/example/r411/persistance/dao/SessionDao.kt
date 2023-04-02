package com.example.r411.persistance.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.example.r411.persistance.entity.Session
import com.example.r411.persistance.view.SessionDetails

@Dao
interface SessionDao : DataDao<Session> {
    @Query("SELECT * FROM session WHERE deleted = 0")
    override fun getAll(): List<Session>

    @Query("SELECT * FROM session WHERE id IN (:sessionIds) and deleted = 0")
    override fun loadAllByIds(sessionIds: IntArray): List<Session>

    @Query("SELECT * FROM session WHERE formation_id LIKE :formationId and deleted = 0")
    fun findByFormationId(formationId: Int): List<Session>

    @Insert
    override fun insertAll(vararg sessions: Session)

    @Upsert
    override fun insertOrUpdate(session: Session)

    @Query("SELECT session.id, session.date, formation.name AS formationName FROM session " +
        "JOIN formation ON session.formation_id = formation.id " +
        "WHERE session.deleted = 0 ORDER BY session.date")
    fun sessionsDetailsView(): LiveData<List<SessionDetails>>
}