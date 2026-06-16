package com.stoicus.app.core.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import com.stoicus.app.R

object NotificationHelper {

    const val MORNING_CHANNEL_ID = "morning_ritual"
    const val EVENING_CHANNEL_ID = "evening_ritual"
    const val MICRO_CHANNEL_ID = "micro_actions"

    fun createNotificationChannels(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val morningChannel = NotificationChannel(
                MORNING_CHANNEL_ID,
                context.getString(R.string.channel_morning),
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Recordatorios para el ritual matutino"
            }

            val eveningChannel = NotificationChannel(
                EVENING_CHANNEL_ID,
                context.getString(R.string.channel_evening),
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Recordatorios para el ritual nocturno"
            }

            val microChannel = NotificationChannel(
                MICRO_CHANNEL_ID,
                context.getString(R.string.channel_micro),
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = "Recordatorios para micro-acciones"
            }

            manager.createNotificationChannel(morningChannel)
            manager.createNotificationChannel(eveningChannel)
            manager.createNotificationChannel(microChannel)
        }
    }

    fun showMorningNotification(context: Context) {
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notification = NotificationCompat.Builder(context, MORNING_CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_lock_idle_alarm)
            .setContentTitle(context.getString(R.string.morning_reminder_title))
            .setContentText(context.getString(R.string.morning_reminder_text))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .build()

        manager.notify(1001, notification)
    }

    fun showEveningNotification(context: Context) {
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notification = NotificationCompat.Builder(context, EVENING_CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_lock_idle_alarm)
            .setContentTitle(context.getString(R.string.evening_reminder_title))
            .setContentText(context.getString(R.string.evening_reminder_text))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .build()

        manager.notify(1002, notification)
    }

    fun showMicroActionNotification(context: Context) {
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notification = NotificationCompat.Builder(context, MICRO_CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_lock_idle_alarm)
            .setContentTitle(context.getString(R.string.micro_action_title))
            .setContentText(context.getString(R.string.micro_action_text))
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setAutoCancel(true)
            .build()

        manager.notify(1003, notification)
    }
}
