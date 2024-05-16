package com.dalae37.amazingattendance

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AlarmActivity : AppCompatActivity() {

    private lateinit var alarmManager: AlarmManager
    private lateinit var receiverIntent: Intent
    private lateinit var pendingIntent: PendingIntent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_alarm)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.alarm)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        alarmManager = applicationContext.getSystemService(ALARM_SERVICE) as AlarmManager
        receiverIntent = Intent(applicationContext, AlarmReceiver::class.java)
        pendingIntent = PendingIntent.getBroadcast(
            applicationContext,
            AlarmReceiver.NOTIFICATION_ID, receiverIntent, PendingIntent.FLAG_MUTABLE
        )

        findViewById<Button>(R.id.test).setOnClickListener(View.OnClickListener {
            setAlarm()
        })
    }

    private fun setAlarm() {
        alarmManager.cancel(pendingIntent)
        alarmManager.set(
            AlarmManager.RTC_WAKEUP,
            SystemClock.elapsedRealtime() + 3000,
            pendingIntent
        )
    }
}