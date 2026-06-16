package com.stoicus.app.core.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            "MORNING_RITUAL" -> NotificationHelper.showMorningNotification(context)
            "EVENING_RITUAL" -> NotificationHelper.showEveningNotification(context)
            "MICRO_ACTION" -> NotificationHelper.showMicroActionNotification(context)
        }
    }
}
