package com.mapache.basketballcounter.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mapache.basketballcounter.database.entities.Match

@Dao
interface MatchDao {

    @Query("SELECT * FROM match_table")
    fun getAll() : LiveData<List<Match>>

    @Insert
    suspend fun insert(match : Match)

}