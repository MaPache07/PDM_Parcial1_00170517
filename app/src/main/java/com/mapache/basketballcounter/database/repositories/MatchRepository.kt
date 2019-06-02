package com.mapache.basketballcounter.database.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.mapache.basketballcounter.database.daos.MatchDao
import com.mapache.basketballcounter.database.entities.Match

class MatchRepository(private val matchDao: MatchDao) {

    val allMatch : LiveData<List<Match>> = matchDao.getAllMatchs()

    @WorkerThread
    suspend fun insert(match : Match){
        matchDao.insert(match)
    }
}