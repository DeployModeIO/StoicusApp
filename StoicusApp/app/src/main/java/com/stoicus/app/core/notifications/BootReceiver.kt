package com.stoicus.app.core.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.stoicus.app.core.notifications.Scheduler.scheduleNotifications

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            scheduleNotifications(context)
        }
    }
}
