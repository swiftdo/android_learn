package com.example.android_learn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class ListActivity : AppCompatActivity() {
    private val fruitList = ArrayList<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        initFruits()
        val listView = findViewById<ListView>(R.id.listView)
        val adapter = FruitAdapter(this, R.layout.fruit_item, fruitList)
        listView.adapter = adapter
    }

    private fun initFruits() {
        repeat(2) {
            fruitList.add(Fruit("Apple", "🍎"))
            fruitList.add(Fruit("Banana", "🍌"))
            fruitList.add(Fruit("Orange", "🍊"))
            fruitList.add(Fruit("Watermelon", "🍉"))
            fruitList.add(Fruit("Pear", "🍐"))
            fruitList.add(Fruit("Strawberry", "🍓"))
            fruitList.add(Fruit("Cherry", "🍒"))
            fruitList.add(Fruit("Mango", "🥭"))
        }
    }
}