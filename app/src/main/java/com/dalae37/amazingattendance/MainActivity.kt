package com.dalae37.amazingattendance

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<Button>(R.id.application_checking).setOnClickListener(View.OnClickListener {
            val intent : Intent = Intent(this, CheckingActivity::class.java)
            startActivity(intent)
        })

        //findViewById<Button>(R.id.application_setting).setOnClickListener(View.OnClickListener {
        //    val intent : Intent = Intent(this, ApplicationActivity::class.java)
        //    startActivity(intent)
        //})

        findViewById<Button>(R.id.alarm_setting).setOnClickListener(View.OnClickListener {
            val intent : Intent = Intent(this, AlarmActivity::class.java)
            startActivity(intent)
        })
    }
}