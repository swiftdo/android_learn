package com.example.android_learn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        val extraData = intent.getStringExtra("extra_data")
        Log.d("FirstActivity", "extradta is $extraData")

        val btn = findViewById<Button>(R.id.first_active_back)
        btn.setOnClickListener {
            val intent = Intent()
            intent.putExtra("data_return", "返回数据")
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}