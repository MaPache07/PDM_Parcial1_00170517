package com.mapache.basketballcounter.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mapache.basketballcounter.database.daos.MatchDao
import com.mapache.basketballcounter.database.entities.Match

@Database(entities = [Match::class], version = 1)
public abstract class RoomDB : RoomDatabase(){

    abstract fun matchDao() : MatchDao

    companion object{

        @Volatile
        private var INSTANCE : RoomDB? = null

        fun getDatabase(context: Context) : RoomDB {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDB::class.java,
                    "Match_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}