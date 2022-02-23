package com.programmerworld.choruschecker.Object

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.programmerworld.choruschecker.ActivityClass.MediaControlsActivity
import com.programmerworld.choruschecker.SupportClass.NotificationReceiver
import com.programmerworld.choruschecker.R

object Notifications {

    const val MC_ID = "MEDIA_CONTROL_ID"
    const val BM_ID = "BUBBLES_MESSAGES_ID"

    fun setUpNotificationChannels(context: Context) {

        lateinit var name: String
        var importance: Int = NotificationManager.IMPORTANCE_DEFAULT
        val notificationManager = context.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            name = "Media Control"
            val mChannel1 = NotificationChannel(MC_ID, name, importance)

            name = "Bubbles messages"
            val mChannel2 = NotificationChannel(BM_ID, name, importance)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
               mChannel2.setAllowBubbles(true)
            }

            notificationManager.createNotificationChannel(mChannel1)
            notificationManager.createNotificationChannel(mChannel2)
        }
    }

    fun showMCNotification (context: Context, playPauseBtn: Int, title: String, author: String, logo:Int){

        val notificationManager = context.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager

        var intent : Intent = Intent(context, MediaControlsActivity::class.java)
        var contentIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        var playIntent : Intent = Intent(context, NotificationReceiver::class.java).setAction(Variables.ACTION_PLAY)
        var playPendingIntent: PendingIntent = PendingIntent.getBroadcast(context, 0, playIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        var forwardIntent : Intent = Intent(context, NotificationReceiver::class.java).setAction(Variables.ACTION_FORWARD)
        var forwardPendingIntent: PendingIntent = PendingIntent.getBroadcast(context, 0, forwardIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        var backwardIntent : Intent = Intent(context, NotificationReceiver::class.java).setAction(Variables.ACTION_BACKWARD)
        var backwardPendingIntent: PendingIntent = PendingIntent.getBroadcast(context, 0, backwardIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        var picture : Bitmap = BitmapFactory.decodeResource(context.resources, logo)

        var notification: Notification? = NotificationCompat.Builder(context, MC_ID)
                .setSmallIcon(R.drawable.ic_play)
                .setLargeIcon(picture)
                .setContentTitle(title)
                .setContentText(author)
                .addAction(R.drawable.ic_baseline_replay_10_24, "Backward", backwardPendingIntent)
                .addAction(playPauseBtn, "Play", playPendingIntent)
                .addAction(R.drawable.ic_baseline_forward_30_24, "Forward", forwardPendingIntent)
                .setStyle(androidx.media.app.NotificationCompat.MediaStyle())
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setContentIntent(contentIntent)
                .setOnlyAlertOnce(true)
                .setCategory(Notification.CATEGORY_SERVICE)
                .setOngoing(true)
                .build()

        notificationManager.notify(0, notification)
    }



}