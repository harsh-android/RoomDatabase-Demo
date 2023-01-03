package com.example.roomdatabase12

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [FoodData::class], version = 1)
abstract class RoomDB : RoomDatabase() {
    companion object {
        var DB_NAME = "resort"
        fun getInstance(context: Context): RoomDB {

            var room = Room.databaseBuilder(context,RoomDB::class.java, DB_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
            return room
        }
    }
    abstract fun getFoodDao(): FoodDao
}