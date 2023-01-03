package com.example.roomdatabase12

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food")
data class FoodData(

    @ColumnInfo(name = "name") var name:String,
    @ColumnInfo(name = "price") var price:Int,
    @ColumnInfo(name = "veg") var veg:Boolean
) {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}
