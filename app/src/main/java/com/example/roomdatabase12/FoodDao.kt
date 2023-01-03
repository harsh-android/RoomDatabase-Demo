package com.example.roomdatabase12

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface FoodDao {

    @Insert()
    fun addFood(data : FoodData)

    @Query("SELECT * FROM food")
    fun getFood() : List<FoodData>

    @Update
    fun updateFood(data: FoodData)

    @Delete
    fun deleteFood(data: FoodData)

    @Query("DELETE FROM food")
    fun deleteAllFood()

}