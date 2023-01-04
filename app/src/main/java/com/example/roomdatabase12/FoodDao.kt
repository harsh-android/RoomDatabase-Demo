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

    @Query("UPDATE food SET name=:name, price=:price, veg= :veg WHERE id=:id")
    fun updateFood(name: String, price: Int, veg:Boolean, id:Int)

    @Delete
    fun deleteFood(data: FoodData)

    @Query("DELETE FROM food")
    fun deleteAllFood()

}