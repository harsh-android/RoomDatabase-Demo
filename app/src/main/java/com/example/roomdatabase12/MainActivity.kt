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

    companion object {

        lateinit var binding: ActivityMainBinding
        lateinit var adapter: FoodAdapter
        lateinit var database: FoodDao


        fun Updated() {
            var list = database.getFood()
            adapter.update(list)

        }

        fun setList() {
            var list = database.getFood()
            adapter = FoodAdapter(list)
            binding.rcvList.adapter = adapter

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = RoomDB.getInstance(applicationContext).getFoodDao()

        binding.rcvList.layoutManager = LinearLayoutManager(applicationContext)

        binding.veg.setOnCheckedChangeListener(object : OnCheckedChangeListener {
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

            var data =
                FoodData(binding.name.text.toString(), binding.price.text.toString().toInt(), veg)
            database.addFood(data)

            setList()

        }

        setList()


    }


}