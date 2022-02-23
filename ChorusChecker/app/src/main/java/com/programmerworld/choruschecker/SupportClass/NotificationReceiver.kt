package com.programmerworld.choruschecker.SupportClass

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.programmerworld.choruschecker.Object.Variables.ACTION_BACKWARD
import com.programmerworld.choruschecker.Object.Variables.ACTION_FORWARD
import com.programmerworld.choruschecker.Object.Variables.ACTION_PLAY


class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val intent1 = Intent(context, MusicService::class.java)
        if (intent.action != null) {
            when (intent.action) {
                ACTION_PLAY -> {
                    intent1.putExtra("INTENT", intent.action)
                    context.startService(intent1)
                }
                ACTION_BACKWARD -> {
                    intent1.putExtra("INTENT", intent.action)
                    context.startService(intent1)
                }
                ACTION_FORWARD -> {
                    intent1.putExtra("INTENT", intent.action)
                    context.startService(intent1)
                }
            }
        }
    }
}