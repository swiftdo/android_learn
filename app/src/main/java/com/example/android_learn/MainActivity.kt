package com.example.android_learn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn: Button = findViewById(R.id.main_activity_btn)

        btn.setOnClickListener {
            Toast.makeText(this, "YOU cicked Button", Toast.LENGTH_SHORT).show()
        }
    }
}