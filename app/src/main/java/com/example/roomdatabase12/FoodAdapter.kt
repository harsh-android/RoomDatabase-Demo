package com.example.roomdatabase12

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FoodAdapter(list: List<FoodData>) :
    RecyclerView.Adapter<FoodAdapter.UserDataHolder>() {
    var modellist = list
    lateinit var context: Context
    lateinit var view: View
    lateinit var database:FoodDao

    class UserDataHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var foodName = itemView.findViewById<TextView>(R.id.foodName)
        var foodPrice = itemView.findViewById<TextView>(R.id.foodPrice)
        var btnUpdate = itemView.findViewById<Button>(R.id.btnUpdate)
        var btnDelete = itemView.findViewById<Button>(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserDataHolder {
        context = parent.context
        view = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return UserDataHolder(view)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: UserDataHolder, position: Int) {
        holder.foodName.text = modellist.get(position).name
        holder.foodPrice.text = modellist.get(position).price.toString()
        database = RoomDB.getInstance(context).getFoodDao()
        if (modellist.get(position).veg) {
            holder.foodName.setTextColor(context.resources.getColor(android.R.color.holo_green_dark))
        } else {
            holder.foodName.setTextColor(Color.RED)
        }

        holder.btnUpdate.setOnClickListener {
            var dialog = Dialog(context)
            var veg = modellist.get(position).veg
            dialog.setContentView(R.layout.update_dialog)
            dialog.window?.setBackgroundDrawable(ColorDrawable(android.R.color.transparent))
            var idd = dialog.findViewById<TextView>(R.id.txtId)
            var edtName = dialog.findViewById<TextView>(R.id.name)
            var edtPrice = dialog.findViewById<TextView>(R.id.price)
            var rgVeges = dialog.findViewById<RadioGroup>(R.id.veges)
            var rbVeg = dialog.findViewById<RadioButton>(R.id.veg)
            var rbNonVeg = dialog.findViewById<RadioButton>(R.id.nonVeg)
            var btnUpdates = dialog.findViewById<Button>(R.id.btnUpdate)

            idd.text = modellist.get(position).id.toString()
            edtName.text = modellist.get(position).name.toString()
            edtPrice.text = modellist.get(position).price.toString()

            if (modellist.get(position).veg) {
                rbVeg.isChecked = true
            } else {
                rbNonVeg.isChecked = true
            }

            rgVeges.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
                override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {
                    var radioButton = view.findViewById<RadioButton>(p1)
                    if (radioButton.text.toString().equals("Veg")) {
                        veg = true
                    } else {
                        veg = false
                    }
                }
            })
            btnUpdates.setOnClickListener {

                database.updateFood(edtName.text.toString(),edtPrice.text.toString().toInt(), veg,modellist.get(position).id)

                MainActivity.Updated()
                dialog.dismiss()

            }

            dialog.show()

        }

        holder.btnDelete.setOnClickListener {
            database.deleteFood(modellist.get(position))
            MainActivity.Updated()
        }

    }

    override fun getItemCount(): Int {
        return modellist.size
    }

    fun update(list: List<FoodData>) {
        modellist = list
        notifyDataSetChanged()
    }

}