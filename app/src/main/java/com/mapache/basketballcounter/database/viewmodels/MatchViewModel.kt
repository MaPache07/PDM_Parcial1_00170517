package com.mapache.basketballcounter.database.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.mapache.basketballcounter.database.RoomDB
import com.mapache.basketballcounter.database.daos.MatchDao
import com.mapache.basketballcounter.database.entities.Match
import com.mapache.basketballcounter.database.repositories.MatchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MatchViewModel(application: Application) : AndroidViewModel(application) {

    private val repository : MatchRepository
    val allMatch : LiveData<List<Match>>

    init {
        val matchDao : MatchDao = RoomDB.getDatabase(application).matchDao()
        repository = MatchRepository(matchDao)
        allMatch = repository.allMatch
    }

    fun insert(match : Match) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(match)
    }
}