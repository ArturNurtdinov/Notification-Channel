package com.example.company.myapplication

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.notify)
        button.setOnClickListener {
            val notification = NotificationCompat.Builder(this, "ID")
                    .setSmallIcon(android.R.drawable.alert_dark_frame)
                    .setContentTitle("Title")
                    .setWhen(System.currentTimeMillis())
                    .setContentText("Text")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)

            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                //important part
                //we must create channel for our notifications on android 8.0+
                //user can change parameters for each channel in app settings
                val channel = NotificationChannel("ID", "NAME", NotificationManager.IMPORTANCE_DEFAULT)
                notificationManager.createNotificationChannel(channel)
            }
            notificationManager.notify(1, notification.build())
        }
    }
}
