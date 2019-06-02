package com.mapache.basketballcounter.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "match_table")
data class Match (
    var teamA : String,
    var teamB : String,
    var scoreA : Int,
    var scoreB : Int,
    var winner : String
) {@PrimaryKey(autoGenerate = true) var id : Long = 0}