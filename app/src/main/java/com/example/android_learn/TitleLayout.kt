package com.example.android_learn

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast

class TitleLayout(context: Context, attrs: AttributeSet): LinearLayout(context, attrs) {
    init {
        // 动态载入
        LayoutInflater.from(context).inflate(R.layout.title, this)
        val titleBack  = findViewById<Button>(R.id.titleBack)
        val titleEdit = findViewById<Button>(R.id.titleEdit)

        titleBack.setOnClickListener {
            val activity = context as Activity
            val intent = Intent(activity, ListActivity::class.java)
            activity.startActivity(intent)
        }

        titleEdit.setOnClickListener {
            Toast.makeText(context, "edit button clicked ", Toast.LENGTH_SHORT).show()
        }
    }
}