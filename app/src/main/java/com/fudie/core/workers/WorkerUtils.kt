package com.fudie.core.workers

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.fudie.core.R
import com.fudie.core.screens.utils.Constants
import com.fudie.core.screens.utils.Constants.INGREDIENTS_CHANNEL_ID

fun makeStatusNotification(message: String, context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name = Constants.INGREDIENTS_CHANNEL_ID
        val description = Constants.INGREDIENTS_CHANNEL_ID
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(INGREDIENTS_CHANNEL_ID, name, importance)
        channel.description = description

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?
        notificationManager?.createNotificationChannel(channel)
    }

    val builder = NotificationCompat.Builder(context, INGREDIENTS_CHANNEL_ID)
        .setSmallIcon(R.drawable.ic_launcher_foreground)
        .setContentTitle("NOTIFICATION_TITLE")
        .setContentText(message)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setVibrate(LongArray(0))

    NotificationManagerCompat.from(context).notify(111, builder.build())
}
