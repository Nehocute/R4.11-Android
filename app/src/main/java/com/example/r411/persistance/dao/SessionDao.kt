package com.example.r411.persistance.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.r411.persistance.entity.Session

@Dao
interface SessionDao {
    @Query("SELECT * FROM session WHERE deleted = 0")
    fun getAll(): List<Session>

    @Query("SELECT * FROM session WHERE id IN (:sessionIds) and deleted = 0")
    fun loadAllByIds(sessionIds: IntArray): List<Session>

    @Query("SELECT * FROM session WHERE formation_id LIKE :formationId and deleted = 0")
    fun findByFormationId(formationId: Int): List<Session>

    @Insert
    fun insertAll(vararg sessions: Session)
}