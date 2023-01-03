package com.example.roomdatabase12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.RadioGroup.OnCheckedChangeListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdatabase12.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    var veg = true
    lateinit var database:FoodDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = RoomDB.getInstance(applicationContext).getFoodDao()


        binding.veg.setOnCheckedChangeListener(object :OnCheckedChangeListener{
            override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {
                var radioButton = findViewById<RadioButton>(p1)
                if (radioButton.text.toString().equals("Veg")) {
                    veg = true
                } else {
                    veg = false
                }
            }
        })

        binding.btnAdd.setOnClickListener {

            var data = FoodData(binding.name.text.toString(),binding.price.text.toString().toInt(), veg)
            database.addFood(data)

        setList(binding)

        }

        setList(binding)




    }

    fun setList(binding: ActivityMainBinding) {
        var list = database.getFood()
        binding.rcvList.layoutManager = LinearLayoutManager(applicationContext)
        binding.rcvList.adapter = FoodAdapter(list)

    }
}