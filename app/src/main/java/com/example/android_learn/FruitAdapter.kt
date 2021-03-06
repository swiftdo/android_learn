package com.example.android_learn

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView


class FruitAdapter(activity: Activity, private val resourceId: Int, data: List<Fruit>) : ArrayAdapter<Fruit>(activity, resourceId, data){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View = convertView ?: LayoutInflater.from(context).inflate(resourceId, parent, false)
        val fruitDesc: TextView = view.findViewById(R.id.fruitDesc)
        val fruitName: TextView = view.findViewById(R.id.fruitName)
        val fruit = getItem(position)
        if (fruit != null) {
            fruitDesc.text = fruit.desc
            fruitName.text = fruit.name
        }
        return view
    }
}