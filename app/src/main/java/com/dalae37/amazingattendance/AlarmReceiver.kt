package com.dalae37.amazingattendance

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat

class AlarmReceiver : BroadcastReceiver() {
    companion object {
        const val NOTIFICATION_ID = 37
        const val NOTIFICATION_CHANNEL_ID = "AmazingAttendance"
        const val NOTIFICATION_CHANNEL_NAME = "AmazingAttendance"
    }

    private lateinit var notificationManager: NotificationManager
    private lateinit var notificationBuilder: NotificationCompat.Builder

    override fun onReceive(context: Context?, intent: Intent?) {
        notificationManager =
            context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationBuilder =
            NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)

        createNotificationChannel(context)
        deliverNotification(context)
    }

    private fun createNotificationChannel(context: Context) {
        val notificationChannel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            NOTIFICATION_CHANNEL_NAME,
            NotificationManager.IMPORTANCE_HIGH
        )

        notificationChannel.enableVibration(true)
        notificationChannel.enableLights(true)
        notificationChannel.lightColor = R.color.background

        notificationChannel.description = context.getString(R.string.app_name)
        notificationManager.createNotificationChannel(notificationChannel)
    }

    private fun deliverNotification(context: Context) {
        val intent = Intent(context, CheckingActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            context,
            NOTIFICATION_ID,
            intent,
            PendingIntent.FLAG_MUTABLE
        )
        notificationBuilder.setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("출석")
            .setContentText("TEST")
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setDefaults(NotificationCompat.DEFAULT_ALL)

        notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build())
    }
}